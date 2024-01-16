//
// Copyright (c) 2022,2023 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.java;

import com.liqid.sdk.generator.Main;
import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.BodyParameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.WiredParameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructDataMember;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructFunctionMember;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructMember;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;
import com.liqid.sdk.generator.writers.CodeWriter;
import com.liqid.sdk.generator.writers.LanguageId;
import com.liqid.sdk.generator.writers.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JavaCodeWriter extends CodeWriter {

    private static final String JAVA_CODE_BASE_DIRECTORY =
        ARTIFACTS_DIRECTORY + "/java/src/com/liqid/sdk";

    private static final String[] CLASS_IMPORTS = {
        "com.fasterxml.jackson.annotation.JsonAutoDetect",
        "com.fasterxml.jackson.annotation.JsonIgnoreProperties",
        "com.fasterxml.jackson.annotation.JsonProperty",
        "com.fasterxml.jackson.annotation.PropertyAccessor",
        };
    private static final String[] ENUM_IMPORTS = {
        "com.fasterxml.jackson.annotation.JsonProperty",
        };

    private static final String SLASH_LITERAL = "\"/\"";

    private static final Map<PresetId, String> PRESET_EXPRESSIONS = new HashMap<>();
    static {
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_RACK, "getPresetRack()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_SHELF, "getPresetShelf()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_NODE, "getPresetNode()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_FABRIC_ID, "getPresetFabricId()");
        PRESET_EXPRESSIONS.put(PresetId.ALWAYS_FALSE, "false");
        PRESET_EXPRESSIONS.put(PresetId.ALWAYS_TRUE, "true");
    }

    private static final Map<String, String> NAME_CONVERSION_OVERRIDES = new HashMap<>();
    static {
        NAME_CONVERSION_OVERRIDES.put("SetP2PEnabled", "setP2PEnabled");
        NAME_CONVERSION_OVERRIDES.put("P2PEnabled", "p2pEnabled");
    }

    @Override
    public final LanguageId getLanguageId() {
        return LanguageId.JAVA;
    }

    @Override
    public final void writeCode() throws IOException {
        writeEnumerators();
        writeTypedefClasses();
        writeStructClasses();
    }

    @Override
    public final String convertCategoryName(
        final String baseName
    ) {
        return initialCharacterToUpperCase(baseName);
    }

    @Override
    public final String convertEnumName(
        final String baseName
    ) {
        return JavaCommon.toClassName(baseName);
    }

    @Override
    public final String convertEnumComponentName(
        final String baseName
    ) {
        var tokens  = Writer.splitTextByToken(baseName);
        for (var tx = 0; tx < tokens.length; ++tx) {
            tokens[tx] = tokens[tx].toUpperCase();
        }
        return String.join("_", tokens);
    }

    @Override
    public final String convertEnumComponentName(
        final String enumBaseName,
        final String componentBaseName
    ) {
        return convertEnumName(enumBaseName) + "." + convertEnumComponentName(componentBaseName);
    }

    @Override
    public final String convertFunctionName(
        final String baseName
    ) {
        if (NAME_CONVERSION_OVERRIDES.containsKey(baseName)) {
            return NAME_CONVERSION_OVERRIDES.get(baseName);
        } else {
            return initialTokenToLowerCase(baseName);
        }
    }

    @Override
    public final String convertParameterName(
        final String baseName
    ) {
        if (NAME_CONVERSION_OVERRIDES.containsKey(baseName)) {
            return NAME_CONVERSION_OVERRIDES.get(baseName);
        } else {
            return initialTokenToLowerCase(baseName);
        }
    }

    @Override
    public final String convertStructName(
        final String baseName
    ) {
        return JavaCommon.toClassName(baseName);
    }

    @Override
    public final String convertStructDataMemberName(
        final String baseName
    ) {
        return "_" + Writer.initialTokenToLowerCase(baseName);
    }

    @Override
    public final String convertTypedefName(
        final String baseName
    ) {
        return JavaCommon.toClassName(baseName);
    }

    //  ------------------------------------------------------------------------
    //  Function writers
    //  We write these as methods on the LiqidClient class
    //  ------------------------------------------------------------------------

    //  Top-level function writer methods --------------------------------------

    private void writeFunction(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        if (function instanceof RegularFunction rf) {
                writeRegularFunction(writer, rf);
        } else if (function instanceof CompositeStructFunction csf) {
            writeCompositeStructFunction(writer, csf);
        } else if (function instanceof FilterFunction ff) {
            writeFilterFunction(writer, ff);
        } else if (function instanceof GetPostPutFunction gpf) {
            writeGetPostPutFunction(writer, gpf);
        } else if (function instanceof HardCodedFunction) {
            //  nothing to do
            System.out.println("Skipping hard-coded function " + function.getBaseName());
        } else if (function instanceof WiredFunction wf) {
            writeWiredFunction(writer, wf);
        }
    }

    //  Composite struct function writers --------------------------------------

    private void writeCompositeStructFunction(
        final BufferedWriter writer,
        final CompositeStructFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());
        var compositeObjectName = "composite";

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function, funcName);
        writeFunctionLogEntryTrace(writer, function, funcName);
        writeFunctionNullCheck(writer, function, funcName);

        writer.write("        try {\n");
        writeCompositeStructFunctionGetters(writer, function, compositeObjectName);

        var resultName = "";
        if (function.getRestMethod() == null) {
            resultName = compositeObjectName;
        } else {
            writer.write("\n");
            writeFunctionPathCode(writer, function, Collections.emptyMap());
            writeFunctionSend(writer, function, compositeObjectName);
            writeFunctionHandleResult(writer, function);
            resultName = "result";
        }

        writeFunctionReturn(writer, function, resultName);
        writeFunctionCatch(writer);
        writer.write("    }\n");
    }

    private void writeCompositeStructFunctionGetters(
        final BufferedWriter writer,
        final CompositeStructFunction function,
        final String destinationName
    ) throws IOException {
        writer.write("            var "
                         + destinationName
                         + " = new "
                         + convertStructName(function.getCompositeStruct())
                         + "();\n");

        var destStruct = function.getCompositeStruct();
        for (var g : Function.sort(function.getGetterFunctions())) {
            var getter = (Function) g;
            var destMemberName = function.getStructMemberBaseNameFor(getter.getFunctionId());
            var destMember = (StructDataMember) destStruct.findAggregateDataMember(destMemberName);

            var sb = new StringBuilder();
            sb.append("            ")
              .append(destinationName)
              .append(".")
              .append(JavaCommon.toSetterName(destMember.getBaseName()))
              .append("(")
              .append(convertFunctionName(getter))
              .append("(");

            var first = true;
            for (var p : getter.getParameters()) {
                if (!p.isOptional()) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(convertParameterName(p));
                    first = false;
                }
            }

            sb.append("));\n");
            writer.write(sb.toString());
        }
    }

    //  Filter function writers ------------------------------------------------

    private void writeFilterFunction(
        final BufferedWriter writer,
        final FilterFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function, funcName);
        writeFunctionLogEntryTrace(writer, function, funcName);
        writeFunctionNullCheck(writer, function, funcName);

        //  Parameters will include (first) the identifier(s) we're looking for,
        //  followed by zero or more parameters which are simply passed to the base getter.
        var idParamCount = function.getMemberBaseNames().size();
        var idParamNames = new LinkedList<String>();
        var otherParamNames = new LinkedList<String>();
        for (Parameter param : function.getParameters()) {
            var baseName = param.getBaseName();
            if (idParamNames.size() < idParamCount) {
                idParamNames.add(String.format("%s.equals(item.%s())",
                                               convertParameterName(baseName),
                                               JavaCommon.toGetterName(baseName)));
            } else {
                otherParamNames.add(convertParameterName(baseName));
            }
        }

        var otherParamsString = String.join(", ", otherParamNames);
        String funcStr = convertFunctionName(function.getBaseFunction())
            + "("
            + otherParamsString
            + ")";

        writer.write("        for (var item : " + funcStr + ") {\n");
        var expression = String.join(" && ", idParamNames);
        writer.write("            if (" + expression + ") {\n");
        writer.write("                _logger.trace(\"%%s returning %%s\", fn, item);\n");
        writer.write("                return item;\n");
        writer.write("            }\n");
        writer.write("        }\n");

        writer.write("\n");
        writer.write("        var ex = new LiqidException(\"Entity not found\");\n");
        writer.write("        _logger.throwing(ex);\n");
        writer.write("        throw ex;\n");

        writer.write("    }\n");
    }

    //  Regular function writers -----------------------------------------------

    private void writeRegularFunction(
        final BufferedWriter writer,
        final RegularFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function, funcName);
        writeFunctionLogEntryTrace(writer, function, funcName);
        writeFunctionNullCheck(writer, function, funcName);
        var translatedParams = writeParameterTranslations(writer, function.getParameters());

        writer.write("\n");
        writer.write("        try {\n");
        writeFunctionPathCode(writer, function, translatedParams);
        writeFunctionSend(writer, function, null);
        if (function.hasResult()) {
            writeFunctionHandleResult(writer, function);
        }

        writeFunctionReturn(writer, function, "result");
        writeFunctionCatch(writer);
        writer.write("    }\n");
    }

    //  GET+POST function writers -----------------------------------------

    private void writeGetPostPutFunction(
        final BufferedWriter writer,
        final GetPostPutFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function, funcName);
        writeFunctionLogEntryTrace(writer, function, funcName);
        writeFunctionNullCheck(writer, function, funcName);

        writer.write("        try {\n");
        writeGetPostPutFunctionGetter(writer, function);

        //	Update the various entities per the given parameters.
        for (var param : function.getParameters()) {
            if (param instanceof BodyParameter bp) {
                var paramName = convertParameterName(bp.getBaseName());
                var code = new StringBuilder();

                if (bp.isOptional()) {
                    code.append("if (").append(paramName).append(" != null) ");
                }
                code.append("object.")
                    .append(JavaCommon.toSetterName(bp.getBaseName()))
                    .append("(").append(paramName).append(")");
                writer.write(String.format("            %s;\n", code));
            }
        }

        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);
        writer.write("            var httpResponse = send(\"POST\", path, HttpBodyType.Json, object, HttpBodyType.Json);\n");
        if (function.hasResult()) {
            writeFunctionHandleResult(writer, function);
        }

        writeFunctionReturn(writer, function, "result");
        writeFunctionCatch(writer);
        writer.write("    }\n");
    }

    private void writeGetPostPutFunctionGetter(
        final BufferedWriter writer,
        final GetPostPutFunction function
    ) throws IOException {
        var getter = function.getGetterFunction();
        var getterParamNames=
            getter.getParameters()
                  .stream()
                  .map(this::convertParameterName)
                  .collect(Collectors.toList());

        var paramList = String.join(", ", getterParamNames);
        var methodName = convertFunctionName(getter);
        writer.write("            var object = " + methodName + "(" + paramList + ");\n");
    }

    //  Wired function writers -------------------------------------------------

    private void writeWiredFunction(
        final BufferedWriter writer,
        final WiredFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function, funcName);
        writeFunctionLogEntryTrace(writer, function, funcName);
        writeFunctionNullCheck(writer, function, funcName);
        var translatedParams = writeParameterTranslations(writer, function.getParameters());

        writer.write("\n");
        writer.write("        try {\n");
        writeFunctionPathCode(writer, function, translatedParams);
        writeWiredFunctionSend(writer, function);
        if (function.hasResult()) {
            writeFunctionHandleResult(writer, function);
        }

        writeFunctionReturn(writer, function, "result");
        writeFunctionCatch(writer);
        writer.write("    }\n");
    }

    private void writeWiredFunctionSend(
        final BufferedWriter writer,
        final WiredFunction function
    ) throws IOException {
        //  Check request body type
        var reqType = "HttpBodyType.None";
        var reqName = "null";
        var apiStruct = Catalog.getStruct(function.getApiStructId());
        writer.write(String.format("            var obj = new %s();\n", JavaCommon.toClassName(apiStruct.getBaseName())));

        for (var p : function.getParameters()) {
            if (p instanceof WiredParameter wp) {
                var paramName = convertParameterName(wp.getBaseName());
                for (var target : wp.getTargets()) {
                    var code = new StringBuilder();

                    if (p.isOptional()) {
                        code.append("if (").append(paramName).append(" != null) ");
                    }
                    code.append("obj.");
                    for (var mn : target.getParentMemberNames()) {
                        code.append(JavaCommon.toGetterName(mn)).append("().");
                    }
                    code.append(JavaCommon.toSetterName(target.getMemberName())).append("(").append(paramName).append(")");
                    writer.write(String.format("            %s;\n", code));
                }
            }
        }

        for (var entry : function.getStructPresets().entrySet()) {
            var target = entry.getKey();
            var presetId = entry.getValue();

            var code = new StringBuilder();
            code.append("obj.");
            for (var mn : target.getParentMemberNames()) {
                code.append(JavaCommon.toGetterName(mn)).append("().");
            }
            code.append(JavaCommon.toSetterName(target.getMemberName()));
            code.append("(").append(PRESET_EXPRESSIONS.get(presetId)).append(")");
            writer.write(String.format("            %s;\n", code));
        }

        reqType = "HttpBodyType.Json";
        reqName = "obj";

        //	Almost all returns are JSON wrapper structs, even for APIs which do not return any meaningful data.
        //	The exception is when the API returns an octet stream. So...
        String respType;
        if (function.hasResult() && function.getResultDataDescriptor().getDataTypeId() == DataTypeId.OCTET_STREAM) {
            respType = "HttpBodyType.OctetStream";
        } else {
            respType = "HttpBodyType.Json";
        }

        var method = function.getRestMethod();
        writer.write(String.format("            var httpResponse = send(\"%s\", path, %s, %s, %s);\n",
                                   method, reqType, reqName, respType));
    }

    //  Generally-useful function writer helpers -------------------------------

    private void writeFunctionCatch(
        final BufferedWriter writer
    ) throws IOException {
        writer.write("        } catch (LiqidException ex) {\n");
        writer.write("            _logger.throwing(ex);\n");
        writer.write("            throw ex;\n");

        writer.write("        } catch (Exception ex) {\n");
        writer.write("            _logger.throwing(ex);\n");
        writer.write("            throw new LiqidException(\"Caught exception:\", ex);\n");
        writer.write("        }\n");
    }

    private void writeFunctionHandleResult(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        if (function.getResultDataDescriptor().getDataTypeId() == DataTypeId.OCTET_STREAM) {
            //	if the result is an octet stream then simply return the byte body.
            writer.write("            var result = (byte[]) httpResponse.body();\n");
        } else {
            var wrapperClassName = JavaCommon.toQualifiedWrapperName(function.getResultDataDescriptor());
            writer.write("            var mapper = new ObjectMapper();\n");
            writer.write("            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);\n");
            writer.write("            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);\n");
            writer.write(String.format("            var wrapper = mapper.readValue((String) httpResponse.body(), %s.class);\n",
                                       wrapperClassName));
            writer.write("            wrapper.check();\n");

            String suffix = "";
            var rdd = function.getResultDataDescriptor();
            if (!rdd.isArray() && rdd.getDataTypeId() != DataTypeId.OCTET_STREAM) {
                suffix = ".getFirst()";
            }
            writer.write(String.format("            var result = wrapper.getResponse().getData()%s;\n", suffix));
        }
    }

    private void writeFunctionReturn(
        final BufferedWriter writer,
        final Function function,
        final String resultName
    ) throws IOException {
        writer.write("\n");
        if (function.hasResult()) {
            writer.write(String.format("            _logger.trace(\"%%s returning %%s\", fn, %s);\n", resultName));
            writer.write(String.format("            return %s;\n", resultName));
        } else {
            writer.write("            _logger.trace(\"%%s returning\", fn);\n");
        }
    }

    private void writeFunctionSend(
        final BufferedWriter writer,
        final Function function,
        final String bodyObjectName
    ) throws IOException {
        //  Check request body type
        var reqType = "HttpBodyType.None";
        var reqName = "null";
        if (bodyObjectName != null) {
            //  TODO need to find a way to set HttpBodyType.OctetStream when appropriate
            reqType = "HttpBodyType.Json";
            reqName = bodyObjectName;
        }

        //  Check response body type
        //	Almost all returns are JSON wrapper structs, even for APIs which do not return any meaningful data.
        //	The exception is when the API returns an octet stream. So...
        String respType;
        if (function.hasResult() && function.getResultDataDescriptor().getDataTypeId() == DataTypeId.OCTET_STREAM) {
            respType = "HttpBodyType.OctetStream";
        } else {
            respType = "HttpBodyType.Json";
        }

        var method = function.getRestMethod();
        writer.write(String.format("            var httpResponse = send(\"%s\", path, %s, %s, %s);\n",
                                   method, reqType, reqName, respType));
    }

    private void writeFunctionHeader(
        final BufferedWriter writer,
        final Function function,
        final String functionName
    ) throws IOException {
        var category = Catalog.getCategoryFor(function.getFunctionId());

        writer.write("\n");
        writer.write("    /**\n");
        writer.write(String.format("     * %s()\n", functionName));
        writer.write(String.format("     * Category: %s\n", convertCategoryName(category)));

        for (var s : translate(function.getDescription())) {
            writer.write(String.format("     * %s\n", s));
        }

        var parameters = function.getParameters();
        if (!parameters.isEmpty() || function.hasResult()) {
            //  parameters
            for (var param : parameters) {
                var prefix = String.format("     * @param %s: ", convertParameterName(param.getBaseName()));
                var doc = translate(param.getDescription());
                if (param.isOptional()) {
                    doc.add("This parameter is optional, and should be set to null if it is to remain unspecified.");
                }
                writeBlock(writer, prefix, doc);
            }

            //  result
            if (function.hasResult()) {
                var prefix = "     * @return ";
                writeBlock(writer, prefix, translate(function.getResultDescription()));
            }
        }

        writer.write("     * @throws LiqidException if anything goes wrong\n");

        if (function.isDeprecated()) {
            writer.write("     * Deprecated:\n");
            for (var s : translate(function.getDeprecatedMessage())) {
                writer.write("     *   " + s + "\n");
            }
        }

        writer.write("     */\n");
    }

    private void writeFunctionLogEntryTrace(
        final BufferedWriter writer,
        final Function function,
        final String funcName
    ) throws IOException {
        writer.write(String.format("        var fn = \"%s\";\n", funcName));

        var sbLiteral = new StringBuilder();
        var sbParams = new StringBuilder();
        sbLiteral.append("Entering %s");
        sbParams.append("fn");
        for (var param : function.getParameters()) {
            var paramName = convertParameterName(param.getBaseName());
            sbLiteral.append(" ").append(paramName).append(":%s");
            sbParams.append(", ").append(paramName);
        }

        writer.write(String.format("        _logger.trace(\"%s\", %s);\n", sbLiteral, sbParams));
    }

    private void writeFunctionNullCheck(
        final BufferedWriter writer,
        final Function function,
        final String funcName
    ) throws IOException {
        for (var param : function.getParameters()) {
            if (!param.isOptional()) {
                var paramName = convertParameterName(param.getBaseName());
                writer.write(String.format("        checkParameterNotNull(%s, \"%s\", \"%s\");\n", paramName, paramName, funcName));
            }
        }
    }

    /**
     * Writes code to generate the path value.
     * Included are the partial path from the function descriptor, followed by the values of any path parameters,
     * then by the values of any preset parameters, then by any existing query parameters.
     * It should be noted that we generate indentation assuming that the code is wrapped within a single level try/catch.
     */
    private void writeFunctionPathCode(
        final BufferedWriter writer,
        final Function function,
        final Map<String, String> translatedParams
    ) throws IOException {
        var pathComponents = getPathComponents(function);

        //	We don't try to use StringBuilder() - since this will generate a lot of string concatenations in one place,
        //	we rely on the compiler's judgement with respect to potential optimizations.
        writer.write(String.format("            var path = \"%s\";\n", pathComponents._partialPath));

        //  Path presets come first.
        if (pathComponents._pathPresets.size() > 0) {
            writer.write("            path += ");
            for (var presetId : pathComponents._pathPresets) {
                writer.write(String.format("%s + %s", SLASH_LITERAL, PRESET_EXPRESSIONS.get(presetId)));
            }
            writer.write(";\n");
        }

        //  Now do path parameters
        if (pathComponents._pathParameters.size() > 0) {
            for (var param : pathComponents._pathParameters) {
                var name = convertParameterName(param.getBaseName());
                name = translatedParams.getOrDefault(name, name);
                var prefix = "            path += ";
                if (param.isOptional()) {
                    var ternary = String.format("(%s == null ? \"\" : %s.toString());\n", name, name);
                    writer.write(String.format("%s%s + %s", prefix, SLASH_LITERAL, ternary));
                } else {
                    writer.write(String.format("%s%s + %s.toString();\n", prefix, SLASH_LITERAL, name));
                }
            }
        }

        //  Query parameters - these can be a little trickier when optional
        if (pathComponents._queryParameters.size() + function.getQueryPresets().size() > 0) {
            writer.write("            var qpList = new LinkedList<String>();\n");
            for (var qp : pathComponents._queryParameters) {
                var paramName = convertParameterName(qp.getBaseName());
                var code = "qpList.add(\"" + qp.getQueryTag() + "=\" + " + paramName + ".toString());";
                if (qp.isOptional()) {
                    writer.write(String.format("            if (%s != null) {\n", paramName));
                    writer.write(String.format("                %s\n", code));
                    writer.write("            }\n");
                } else {
                    writer.write(String.format("            %s\n", code));
                }
            }
            for (var entry : function.getQueryPresets().entrySet()) {
                var queryTag = entry.getKey();
                var presetExpr = PRESET_EXPRESSIONS.get(entry.getValue());
                writer.write("            qpList.add(\"" + queryTag + "=\" + " + presetExpr + ");\n");
            }
            writer.write("            path += \"?\" + String.join(\"&\", qpList);\n");
        }
    }

    private void writeFunctionSignature(
        final BufferedWriter writer,
        final Function function,
        final String funcName
    ) throws IOException {
        if (function.isDeprecated()) {
            writer.write("    @Deprecated\n");
        }

        var typeStr = JavaCommon.getTypeString(function.getResultDataDescriptor());
        var prefix = "    public " + typeStr + " " + funcName + "(";

        var parameterStrings = new LinkedList<String>();
        var parameters = function.getParameters();
        int px = parameters.size();
        for (var param : parameters) {
            var type = JavaCommon.getExposedDataTypeString(param.getDataDescriptor());
            var name = convertParameterName(param.getBaseName());

            var paramStr = type + " " + name;
            if (px > 1) {
                paramStr += ",";
            } else {
                paramStr += ") throws LiqidException {";
            }
            parameterStrings.add(paramStr);
            px--;
        }

        if (parameterStrings.isEmpty()) {
            parameterStrings.add(") throws LiqidException {");
        }

        writeBlock(writer, prefix, parameterStrings);
    }

    //  ------------------------------------------------------------------------
    //  Writers for enumerator classes
    //  These are implemented as valued enumerations.
    //  ------------------------------------------------------------------------

    private void writeEnumerators() throws IOException {
        for (var enumerator : Catalog.getEnumeratorsFor(LanguageId.JAVA)) {
            writeEnumeratorClass(enumerator);
        }
    }

    private void writeEnumeratorClass(
        final Enumerator enumerator
    ) throws IOException {
        var className = convertEnumName(enumerator);
        var fileName = className + ".java";
        var fullName = JAVA_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        w.write("\n");
        w.write(String.format("package %s;\n", JavaCommon.JAVA_PACKAGE_NAME));

        Set<String> aggregate = new TreeSet<>();
        Collections.addAll(aggregate, ENUM_IMPORTS);
        aggregate.addAll(enumerator.getImportRequirementsFor(LanguageId.JAVA));
        writeImportStatements(w, aggregate);

        writeClassHeader(w, className, enumerator.getDescription());

        w.write(String.format("public enum %s {\n", className));

        var comps = enumerator.getComponents();
        var delimiter = ",";
        for (var cx = 0; cx < comps.length; ++cx) {
            var comp = comps[cx];
            var name = convertEnumComponentName(comp);
            var literal = comp.getLiteralString();
            if (cx == comps.length - 1) {
                delimiter = ";";
            }

            if (enumerator.getIntrinsicTypeId() == IntrinsicTypeId.STRING) {
                w.write(String.format("    @JsonProperty(%s) %s(%s)%s\n", literal, name, literal, delimiter));
            } else {
                w.write(String.format("    %s(%s)%s\n", name, literal, delimiter));
            }
        }

        w.write("\n");
        var type = JavaCommon.getDataTypeString(new IntrinsicDataDescriptor(enumerator.getIntrinsicTypeId()));
        w.write(String.format("    private final %s _value;\n", type));

        w.write("\n");
        w.write(String.format("    %s(%s value) {\n", className, type));
        w.write("        _value = value;\n");
        w.write("    }\n");

        w.write("\n");
        w.write(String.format("    public %s getValue() {\n", type));
        w.write("        return _value;\n");
        w.write("    }\n");

        w.write("\n");
        w.write("    @Override\n");
        w.write("    public String toString() {\n");
        if (enumerator.getIntrinsicTypeId() == IntrinsicTypeId.STRING) {
            w.write("        return _value;\n");
        } else {
            w.write("        return _value.toString();\n");
        }
        w.write("    }\n");

        w.write("}\n");
        w.close();
    }

    //  ------------------------------------------------------------------------
    //  Struct writers
    //  We write these as POJO classes, with custom toString()s and Builder()s
    //  ------------------------------------------------------------------------

    private void writeStructClasses() throws IOException {
        for (var struct : Catalog.getStructs()) {
            if (!struct.isHardCoded()) {
                writeStructClass(struct);
            }
        }
    }

    private void writeStructClass(
        final Struct struct
    ) throws IOException {
        var className = JavaCommon.toClassName(struct.getBaseName());
        var fileName = className + ".java";
        var fullName = JAVA_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        w.write("\n");
        w.write(String.format("package %s;\n", JavaCommon.JAVA_PACKAGE_NAME));
        writeStructImports(w, struct);
        writeClassHeader(w, className, struct.getDescription());

        w.write("@JsonIgnoreProperties(ignoreUnknown = true)\n");

        var sb = new StringBuilder();
        sb.append("public class ").append(className);
        if (struct.hasBaseStruct()) {
            var baseClassName = JavaCommon.toClassName(struct.getBaseStruct().getBaseName());
            sb.append(" extends ").append(baseClassName);
        }
        sb.append(" {\n");
        w.write(sb.toString());

        if (struct.isSerializable()) {
            w.write("\n");
            w.write("    public static class " + className + "Wrapper extends Wrapper<" + className + ">{}\n");
        }

        var sorted =
            struct.getMembersFor(LanguageId.JAVA).stream()
                  .sorted((a, b) -> a.getBaseName().compareToIgnoreCase(b.getBaseName()))
                  .toList();
        for (var m : sorted) {
            writeStructMember(w, m);
        }

        var aggregateMembers = struct.getAggregateDataMembersFor(LanguageId.JAVA);
        writeStructConstructors(w, struct, className, aggregateMembers);
        writeStructToString(w, aggregateMembers);
        if (struct.isBuildable()) {
            writeStructBuilder(w, className, aggregateMembers);
        }

        w.write("}\n");
        w.close();
    }

    //  Writes a Builder for the struct class.
    //  Not all structs need one, but some do, so we'll just write one for every class.
    private void writeStructBuilder(
        final BufferedWriter writer,
        final String className,
        final Collection<StructDataMember> aggregateMembers
    ) throws IOException {
        writer.write("\n");
        writer.write("    /**\n");
        writer.write("     * Builder class\n");
        writer.write("     */\n");
        writer.write("    public static class Builder {\n");

        writeStructBuilderMembers(writer, aggregateMembers);
        writeStructBuilderSetters(writer, aggregateMembers);
        writeStructBuilderBuild(writer, className, aggregateMembers);
        writer.write("    }\n");
    }

    private void writeStructBuilderBuild(
        final BufferedWriter writer,
        final String className,
        final Collection<StructDataMember> aggregateMembers
    ) throws IOException {
        writer.write("\n");
        writer.write(String.format("        public %s build() {\n", className));

        for (var m : aggregateMembers) {
            if (!m.isOptional()) {
                var memberName = convertStructDataMemberName(m.getBaseName());
                if (m.getDataDescriptor().isArray()) {
                    writer.write(String.format("            if (%s.isEmpty()) {\n", memberName));
                } else {
                    writer.write(String.format("            if (%s == null) {\n", memberName));
                }

                var setterName = JavaCommon.toSetterName(m.getBaseName());
                writer.write(String.format("                throw new RuntimeException(\"%s() was not invoked in Builder for class %s\");\n",
                                           setterName, className));
                writer.write("            }\n");
            }
        }

        var prefix = "            return new " + className + "(";
        var suffixes = new LinkedList<String>();
        var mx = 0;
        for (var member : aggregateMembers) {
            var last = mx == aggregateMembers.size() - 1;
            suffixes.add(convertStructDataMemberName(member.getBaseName()) + (last ? ");" : ","));
            mx++;
        }
        writeBlock(writer, prefix, suffixes);
        writer.write("        }\n");
    }

    private void writeStructBuilderMembers(
        final BufferedWriter writer,
        final Collection<StructDataMember> aggregateMembers
    ) throws IOException {
        writer.write("\n");
        for (var m : aggregateMembers) {
            var name = convertStructDataMemberName(m.getBaseName());
            var desc = m.getDataDescriptor();
            if (desc.isArray()) {
                var type = JavaCommon.getTypeString(desc);
                writer.write(String.format("        private %s %s = new %s();\n", type, name, type));
            } else if (m.hasDefaultValue()) {
                var type = JavaCommon.getTypeString(desc);
                var exp = getDefaultValueString(m);
                writer.write(String.format("        private %s %s = %s;\n", type, name, exp));
            } else {
                var type = JavaCommon.getTypeString(desc);
                writer.write(String.format("        private %s %s = null;\n", type, name));
            }
        }
    }

    private void writeStructBuilderSetters(
        final BufferedWriter writer,
        final Collection<StructDataMember> aggregateMembers
    ) throws IOException {
        writer.write("\n");
        for (var m : aggregateMembers) {
            var sb = new StringBuilder();
            sb.append("        public Builder ");
            if (m.getDataDescriptor().isArray()) {
                sb.append(JavaCommon.toAdderName(m.getBaseName()));
            } else {
                sb.append(JavaCommon.toSetterName(m.getBaseName()));
            }

            var type = JavaCommon.getDataTypeString(m.getDataDescriptor());
            sb.append("(").append(type).append(" value)");

            sb.append(" { ");
            sb.append(convertStructDataMemberName(m.getBaseName()));
            if (m.getDataDescriptor().isArray()) {
                sb.append(".add(value);");
            } else {
                sb.append(" = value;");
            }
            sb.append(" return this; }\n");

            writer.write(sb.toString());
        }
    }

    //  Writes a parameterized constructor for the struct class.
    private void writeStructConstructors(
        final BufferedWriter writer,
        final Struct struct,
        final String className,
        final Collection<StructDataMember> aggregateMembers
    ) throws IOException {
        writer.write("\n");

        //  We need a default public constructor if this class is serializable
        if (struct.isSerializable()) {
            writer.write("    /**\n");
            writer.write("     * Default Constructor\n");
            writer.write("     * Any contained objects are also default-constructed\n");
            writer.write("     */\n");
            writer.write(String.format("    public %s() {\n", className));

            for (var m : struct.getMembers()) {
                if (m instanceof StructDataMember sdm && sdm.getDataDescriptor() instanceof StructDataDescriptor sdd) {
                    var memberName = convertStructDataMemberName(m.getBaseName());
                    var subClassName = JavaCommon.getTypeString(Catalog.getStruct(sdd.getStructId()), sdd.isArray());
                    writer.write(String.format("        %s = new %s();\n", memberName, subClassName));
                }
            }

            writer.write("    }\n");
        }

        writer.write("\n");
        writer.write("    /**\n");
        writer.write("     * Parameterized Constructor\n");
        writer.write("     */\n");

        var prefix = "    protected " + className + "(";
        var suffixes = new LinkedList<String>();
        var mx = 0;
        for (var member : aggregateMembers) {
            var last = mx == aggregateMembers.size() - 1;
            String suffix = JavaCommon.getTypeString(member.getDataDescriptor())
                + " "
                + convertParameterName(member.getBaseName())
                + (last ? ") {" : ",");
            suffixes.add(suffix);
            mx++;
        }
        writeBlock(writer, prefix, suffixes);

        //  split members into base-struct-members and not-base-struct-members
        var baseMembers = struct.getBaseDataMembersFor(LanguageId.JAVA);
        var localMembers = struct.getDataMembersFor(LanguageId.JAVA);

        //  Do we need to write a super() ?
        if (!baseMembers.isEmpty()) {
            var paramNameList =
                baseMembers.stream()
                           .map(member -> convertParameterName(member.getBaseName()))
                           .collect(Collectors.toCollection(LinkedList::new));

            writer.write("        super(" + String.join(", ", paramNameList) + ");\n");
        }

        //  write initializers for the members of this class, but not the ancestor classes
        for (var member : localMembers) {
            var memberName = convertStructDataMemberName(member.getBaseName());
            var parameterName = convertParameterName(member.getBaseName());
            writer.write(String.format("        %s = %s;\n", memberName, parameterName));
        }

        writer.write("    }\n");
    }

    private void writeStructDataMember(
        final BufferedWriter writer,
        final StructDataMember member
    ) throws IOException {
        writer.write("\n");
        writer.write("    /**\n");
        for (var s : translate(member.getDescription())) {
            writer.write(String.format("     * %s\n", s));
        }
        writer.write("     */\n");
        writer.write(String.format("    @JsonProperty(\"%s\")\n", member.getJsonTag()));

        var dataDesc = member.getDataDescriptor();
        var typeStr = JavaCommon.getTypeString(dataDesc);
        var name = convertStructDataMemberName(member.getBaseName());
        var value = member.hasDefaultValue() ? getDefaultValueString(member) : "null";

        writer.write(String.format("    private %s %s = %s;\n", typeStr, name, value));

        // If the JSON format is wrong, we need to write a custom getter and setter to right the wrong.
        var getterName = JavaCommon.toGetterName(member.getBaseName());
        var setterName = JavaCommon.toSetterName(member.getBaseName());

        var sdkTypeString = JavaCommon.getExposedDataTypeString(dataDesc);
        if (dataDesc.isArray()) {
            sdkTypeString = String.format("LinkedList<%s>", sdkTypeString);
        }

        writer.write("\n");
        writer.write(String.format("    public %s %s() {\n", sdkTypeString, getterName));
        if (dataDesc.getDataTypeId() == DataTypeId.INTRINSIC) {
            var idd = (IntrinsicDataDescriptor) dataDesc;
            if (idd.hasTranslations()) {
                writer.write("        // check for null - jackson likes to use beans, and beans like to use\n");
                writer.write("        // getters and setters, and sometimes the field is null.\n");
                writer.write(String.format("        if (%s == null) return null;\n", name));
                for (var t : idd.getTranslations()) {
                    writer.write(String.format("        if (%s.equals(%s)) {\n", name, t._apiConstant));
                    writer.write(String.format("            return %s;\n", t._sdkConstant));
                    writer.write("        }\n");
                }
            }

            if ((idd.getIntrinsicTypeId() == IntrinsicTypeId.INT16_AS_STRING)
                || (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT24_AS_STRING)
                || (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_STRING)) {
                writer.write(String.format("        return LiqidClientBase.hexStringToInteger(%s);\n", name));
            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT64_AS_STRING) {
                writer.write(String.format("        return LiqidClientBase.hexStringToLong(%s);\n", name));
            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_DECIMAL_STRING) {
                writer.write(String.format("        return Integer.parseInt(%s);\n", name));
            } else {
                writer.write(String.format("        return %s;\n", name));
            }
        } else {
            writer.write(String.format("        return %s;\n", name));
        }
        writer.write("    }\n");

        writer.write("\n");
        writer.write(String.format("    public void %s(%s value) {\n", setterName, sdkTypeString));
        if (dataDesc.getDataTypeId() == DataTypeId.INTRINSIC) {
            var idd = (IntrinsicDataDescriptor) dataDesc;
            for (var t : idd.getTranslations()) {
                writer.write(String.format("        if (value.equals(%s)) {\n", t._sdkConstant));
                writer.write(String.format("            %s = %s;\n", name, t._apiConstant));
                writer.write("            return;\n");
                writer.write("        }\n");
            }

            if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT16_AS_STRING) {
                writer.write(String.format("        %s = String.format(\"0x%%04x\", value);\n", name));
            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT24_AS_STRING) {
                writer.write(String.format("        %s = String.format(\"0x%%06x\", value);\n", name));
            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_STRING) {
                writer.write(String.format("        %s = String.format(\"0x%%08x\", value);\n", name));
            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT64_AS_STRING) {
                writer.write(String.format("        %s = String.format(\"0x%%016x\", value);\n", name));
            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_DECIMAL_STRING) {
                writer.write(String.format("        %s = String.format(\"%%d\", value);\n", name));
            } else {
                writer.write(String.format("        %s = value;\n", name));
            }
        } else {
            writer.write(String.format("        %s = value;\n", name));
        }
        writer.write("    }\n");

//        var done = false;
//        if (dataDesc.getDataTypeId() == DataTypeId.INTRINSIC) {
//            var idd = (IntrinsicDataDescriptor) dataDesc;
//            if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT16_AS_STRING) {
//                writer.write(String.format("    public Integer %s() { return LiqidClientBase.hexStringToInteger(%s); }\n",
//                                           getterName,
//                                           name));
//                writer.write(String.format("    public void %s(Integer value) { %s = String.format(\"0x%%04x\", value); }\n",
//                                           setterName,
//                                           name));
//                done = true;
//            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT24_AS_STRING) {
//                writer.write(String.format("    public Integer %s() { return LiqidClientBase.hexStringToInteger(%s); }\n",
//                                           getterName,
//                                           name));
//                writer.write(String.format("    public void %s(Integer value) { %s = String.format(\"0x%%06x\", value); }\n",
//                                           setterName,
//                                           name));
//                done = true;
//            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_STRING) {
//                writer.write(String.format("    public Integer %s() { return LiqidClientBase.hexStringToInteger(%s); }\n",
//                                           getterName,
//                                           name));
//                writer.write(String.format("    public void %s(Integer value) { %s = String.format(\"0x%%08x\", value); }\n",
//                                           setterName,
//                                           name));
//                done = true;
//            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT64_AS_STRING) {
//                writer.write(String.format("    public Long %s() { return LiqidClientBase.hexStringToLong(%s); }\n",
//                                           getterName,
//                                           name));
//                writer.write(String.format("    public void %s(Long value) { %s = String.format(\"0x%%016x\", value); }\n",
//                                           setterName,
//                                           name));
//                done = true;
//            } else if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_DECIMAL_STRING) {
//                writer.write(String.format("    public Integer %s() { return Integer.parseInt(%s); }\n",
//                                           getterName,
//                                           name));
//                writer.write(String.format("    public void %s(Integer value) { %s = String.format(\"%%d\", value); }\n",
//                                           setterName,
//                                           name));
//                done = true;
//            }
//        }
//
//        if (!done) {
//            writer.write(String.format("    public %s %s() { return %s; }\n", type, getterName, name));
//            writer.write(String.format("    public void %s(%s value) { %s = value; }\n", setterName, type, name));
//        }
    }

    //  writes the imports for POJO classes and enumerators
    private void writeStructImports(
        final BufferedWriter writer,
        final Struct struct
    ) throws IOException {
        Set<String> aggregate = new TreeSet<>();
        Collections.addAll(aggregate, CLASS_IMPORTS);
        aggregate.addAll(struct.getImportRequirementsFor(LanguageId.JAVA));
        for (var m : struct.getMembers()) {
            aggregate.addAll(m.getImportRequirementsFor(LanguageId.JAVA));
        }

        writer.write("\n");
        for (var s : aggregate) {
            writer.write("import " + s + ";\n");
        }
    }

    private void writeStructMember(
        final BufferedWriter writer,
        final StructMember member
    ) throws IOException {
        if (member instanceof StructDataMember sdm) {
            writeStructDataMember(writer, sdm);
        } else if (member instanceof StructFunctionMember sfm) {
            writeFunction(writer, Catalog.getFunction(sfm.getFunctionId()));
        }
    }

    private void writeStructToString(
        final BufferedWriter writer,
        final Collection<StructDataMember> aggregateMembers
    ) throws IOException {
        writer.write("\n");
        writer.write("    /**\n");
        writer.write("     * String-izer\n");
        writer.write("     */\n");
        writer.write("    @Override\n");
        writer.write("    public String toString() {\n");
        writer.write("        var sb = new StringBuilder();\n");
        writer.write("        sb.append(\"{\");\n");

        var first = true;
        for (var m : aggregateMembers) {
            writer.write("        sb.");
            if (!first) {
                writer.write("append(\", \").");
            }
            var name = convertStructDataMemberName(m.getBaseName());
            var getter = JavaCommon.toGetterName(m.getBaseName());
            writer.write(String.format("append(\"%s:\").append(%s());\n", name, getter));
            first = false;
        }

        writer.write("        sb.append(\"}\");\n");
        writer.write("        return sb.toString();\n");
        writer.write("    }\n");
    }

    //  ------------------------------------------------------------------------
    //  Typedef writers
    //  We write these as simple classes which extend the base type
    //  ------------------------------------------------------------------------

    private void writeTypedefClasses() throws IOException {
        for (var typedef : Catalog.getTypedefs()) {
            writeTypedefClass(typedef);
        }
    }

    private void writeTypedefClass(
        final Typedef typedef
    ) throws IOException {
        //  Java does not have typedefs - so we use subclassing to achieve this purpose.
        var className = JavaCommon.toClassName(typedef.getBaseName());
        var fileName = className + ".java";
        var fullName = JAVA_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        w.write("\n");
        w.write(String.format("package %s;\n", JavaCommon.JAVA_PACKAGE_NAME));
        writeImportStatements(w, typedef.getImportRequirementsFor(LanguageId.JAVA));

        writeTypedefClassHeader(w, className, typedef.getDescription());

        w.write(String.format("public class %s extends %s {\n", className, JavaCommon.getTypeString(typedef.getSourceDataType())));
        w.write(String.format("    public static class %sWrapper extends Wrapper<%s> {}\n", className, className));

        //  For non-array source classes, we will need a constructor that invokes super() properly.
        //  We do not need to deal with this at this point in time, but maybe some day we will.
        w.write("}\n");
        w.close();
    }

    private void writeTypedefClassHeader(
        final BufferedWriter writer,
        final String className,
        final Description description
    ) throws IOException {
        writeClassHeader(writer, className, description);
    }

    //  ------------------------------------------------------------------------
    //  Things which are generally useful
    //  ------------------------------------------------------------------------

    /**
     * We are going to print a default value - but we need to handle intrinsic strings specially
     * (i.e., delimit them with quotes) as well as enumerators.
     * Other types are not permitted to have default values.
     */
    private String getDefaultValueString(
        final StructDataMember member
    ) {
        var desc = member.getDataDescriptor();
        var value = member.getDefaultValue();
        if (desc instanceof IntrinsicDataDescriptor idd) {
            if (idd.getIntrinsicTypeId() == IntrinsicTypeId.STRING) {
                return "\"" + value.toString() + "\"";
            } else {
                return value.toString();
            }
        } else if (desc instanceof EnumeratorDataDescriptor edd) {
            //  find the enumerator instance with the value matching the given default value
            var valueStr = value.toString();
            var enumerator = Catalog.getEnumerator(edd.getEnumeratorId());
            for (var comp : enumerator.getComponents()) {
                var compValueStr = comp.getValue().toString();
                if (compValueStr.equals(valueStr)) {
                    return convertEnumName(enumerator) + "." + convertEnumComponentName(comp);
                }
            }
        }

        throw new RuntimeException("Default value can only be set for intrinsic or enum data descriptors");
    }

    //  writes the class header for most of the classes we write
    private void writeClassHeader(
        final BufferedWriter writer,
        final String className,
        final Description description
    ) throws IOException {
        writer.write("\n");
        writer.write("/**\n");
        writer.write(String.format(" * %s\n", className));
        for (var s : translate(description)) {
            writer.write(String.format(" * %s\n", s));
        }
        writer.write(" */\n");
    }

    //  writes the first few lines of every .java file
    private void writeFileHeader(
        final BufferedWriter writer,
        final String fileName
    ) throws IOException {
        writer.write(String.format("// File: %s\n", fileName));
        writer.write("//\n");
        for (var s : COPYRIGHT_TEXT) {
            writer.write(String.format("// %s\n", s));
        }
        writer.write("//\n");
        writer.write("// Liqid SDK - Version " + Main.VERSION + "\n");
        writer.write("// This file was automatically generated - do not modify it directly.\n");
        writer.write("//\n");
    }

    private void writeImportStatements(
        final BufferedWriter writer,
        final Collection<String> importStrings
    ) throws IOException {
        writer.write("\n");
        for (var s : importStrings) {
            writer.write("import " + s + ";\n");
        }
    }

    /**
     * See the comments on the abstract function declaration.
     */
    @Override
    protected Map<String, String> writeParameterTranslations(
        final BufferedWriter writer,
        final Collection<Parameter> parameters
    ) throws IOException {
        var translatedParams = new HashMap<String, String>();
        for (var param : parameters) {
            var hexDigits = CodeWriter.getIntAsStringHexDigits(param.getDataDescriptor());
            if (hexDigits > 0) {
                var paramName = convertParameterName(param);
                var translatedName = paramName + "Translated";
                writer.write(String.format("        var %s = String.format(\"0x%%0%dx\", %s);\n",
                                           translatedName,
                                           hexDigits,
                                           paramName));
                translatedParams.put(paramName, translatedName);
            }
        }

        return translatedParams;
    }
}
