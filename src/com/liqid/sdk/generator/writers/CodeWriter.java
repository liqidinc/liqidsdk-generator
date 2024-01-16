//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.DataTypeId;
import com.liqid.sdk.generator.discreteDataModels.IntrinsicDataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.PresetId;
import com.liqid.sdk.generator.liqidEntityModels.functions.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.PathParameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.QueryParameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructMemberTarget;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CodeWriter extends Writer {

    protected static final String[] COPYRIGHT_TEXT = {
        "Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.",
        "",
        "Redistribution and use in source and binary forms, with or without",
        "modification, are not permitted without prior consent."
    };

    protected static class PathComponents {
        public String _partialPath = null;
        public Collection<PathParameter> _pathParameters = new LinkedList<>();
        public Collection<QueryParameter> _queryParameters = new LinkedList<>();
        public Collection<PresetId> _pathPresets = new LinkedList<>();
    }

    public abstract void writeCode() throws IOException;

    /**
     * Replaces all occurrences of something like {struct Foo} with a language-appropriate entity name.
     */
    @Override
    String translate(String text) {
        var tokens = tokenizeDescription(text);
        for (var tx = 0; tx < tokens.length; ++tx) {
            var token = tokens[tx];
            if (token.charAt(0) == '{' && token.charAt(token.length() - 1) == '}') {
                var subTokes = token.split(" ");
                if (subTokes.length == 2) {
                    var type = subTokes[0].substring(1);
                    var name = subTokes[1].substring(0, subTokes[1].length() - 1);

                    switch (type) {
                        case "category" -> {
                            var category = Catalog.getCategory(name);
                            tokens[tx] = convertCategoryName(category);
                        }
                        case "enum" -> {
                            var enumerator = Catalog.getEnumerator(name);
                            tokens[tx] = convertEnumName(enumerator);
                        }
                        case "function" -> {
                            var function = Catalog.getFunction(name);
                            tokens[tx] = convertFunctionName(function);
                        }
                        case "struct" -> {
                            var struct = Catalog.getStruct(name);
                            tokens[tx] = convertStructName(struct);
                        }
                        case "typedef" -> {
                            var typedef = Catalog.getTypedef(name);
                            tokens[tx] = convertTypedefName(typedef);
                        }
                    }
                }
            }
        }

        return String.join("", tokens);
    }

    // Presuming the data descriptor is an intrinsic INTnn_AS_STRING type,
    // we return the *actual* number of corresponding bits for the SDK type integer.
    static public int getIntAsStringBits(DataDescriptor dataDescriptor) {
        if (dataDescriptor.getDataTypeId() == DataTypeId.INTRINSIC) {
            var idd = (IntrinsicDataDescriptor) dataDescriptor;
            return switch (idd.getIntrinsicTypeId()) {
                case INT16_AS_STRING -> 16;
                case INT24_AS_STRING -> 24;
                case INT32_AS_STRING, INT32_AS_DECIMAL_STRING -> 32;
                case INT64_AS_STRING -> 64;
                default -> 0;
            };
        }
        return 0;
    }

    // Presuming the data descriptor is an intrinsic INTnn_AS_STRING type, we return the number of
    // corresponding hex digits needed to represent the value as a string.
    static public int getIntAsStringHexDigits(DataDescriptor dataDescriptor) {
        return getIntAsStringBits(dataDescriptor) / 4;
    }

    // Presuming the data descriptor is an intrinsic INTnn_AS_STRING type, we return the number of
    // bits for the integer type required to contain the value - this will correspond either to
    // int32 or int64.
    static public int getIntAsStringContainerSize(DataDescriptor dataDescriptor) {
        if (dataDescriptor.getDataTypeId() == DataTypeId.INTRINSIC) {
            var idd = (IntrinsicDataDescriptor) dataDescriptor;
            return switch (idd.getIntrinsicTypeId()) {
                case INT16_AS_STRING, INT24_AS_STRING, INT32_AS_STRING, INT32_AS_DECIMAL_STRING -> 32;
                case INT64_AS_STRING -> 64;
                default -> 0;
            };
        }
        return 0;
    }

    protected PathComponents getPathComponents(Function function) {
        var result = new PathComponents();

        //  extract the partial path and path presets
        result._partialPath = function.getPartialPath();
        if (function instanceof WiredFunction f) {
            result._pathPresets = f.getPathPresets();
        }

        //  extract query and path parameters
        for (var p : function.getParameters()) {
            if (p instanceof PathParameter pathParameter) {
                result._pathParameters.add(pathParameter);
            } else if (p instanceof QueryParameter queryParameter) {
                result._queryParameters.add(queryParameter);
            }
        }

        return result;
    }

    /**
     * struct member targets have a base name and potentially one or more parent names.
     * A simple concatenation would produce a string of dot-delimited base names, which we don't want.
     * We need dot-delimited converted names (converted according to the language).
     */
    protected String getCompositeTargetName(StructMemberTarget target) {
        var temp =
            target.getParentMemberNames().stream()
                  .map(this::convertStructDataMemberName)
                  .collect(Collectors.toCollection(LinkedList::new));
        temp.add(convertStructDataMemberName(target.getMemberName()));
        return String.join(".", temp);
    }

    /**
     * There are situations where the API type of an item is not what it should be.
     * Currently this applies to integer values which are serialized as hex strings.
     * The intrinsic type id of these animals is INTnn_AS_STRING, where nn indicates the number of significant
     * bits in the integer - generally, the actual SDK type is either int32 or int64 (or whatever corresponds
     * to these), but they are serialized as "0xn..n" where the number of hex digits ('n') corresponds to
     * the number of significant bits divided by 4.
     * --
     * This raises all sorts of complications, which are dealt with piece-meal throughout the code.
     * Some of these pieces of meal involve accepting data entities as integers, and passing them to the
     * API as strings. This should be done by invoking this code, which will write something like:
     * newParam := conversion(givenParam)
     * and then creating a map entry which maps the givenParam to the newParam.
     * Invoking code should subsequently consult the map we return in order to determine whether to pass
     * the regular parameter name or the converted parameter name to the API.
     * --
     * Both the key and the value in the map are properly converted to language-specific names.
     * Because of the language-specific nature, this is abstract and must be implemented by the
     * various language writers.
     */
    protected abstract Map<String, String> writeParameterTranslations(
        BufferedWriter writer,
        Collection<Parameter> parameters
    ) throws IOException;
}
