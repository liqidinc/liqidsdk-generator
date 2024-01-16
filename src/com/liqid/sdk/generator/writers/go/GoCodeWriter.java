//
// Copyright (c) 2022,2023 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.go;

import com.liqid.sdk.generator.Main;
import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.BodyParameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.QueryParameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.WiredParameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructDataMember;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;
import com.liqid.sdk.generator.writers.CodeWriter;
import com.liqid.sdk.generator.writers.LanguageId;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * To keep things simple, we break convention and put everything into a single package.
 */
public class GoCodeWriter extends CodeWriter {

    private static final String GO_CODE_BASE_DIRECTORY = ARTIFACTS_DIRECTORY + "/go/liqidsdk";
    private static final String GO_LIQID_CLIENT_NAME = "LiqidClient";

    private static final Map<PresetId, String> PRESET_EXPRESSIONS = new HashMap<>();
    static {
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_RACK, "client.getCurrentRack()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_SHELF, "client.getCurrentShelf()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_NODE, "client.getCurrentNode()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_FABRIC_ID, "client.getCurrentFabricId()");
        PRESET_EXPRESSIONS.put(PresetId.ALWAYS_FALSE, "false");
        PRESET_EXPRESSIONS.put(PresetId.ALWAYS_TRUE, "true");
    }

    private static final Map<String, String> NAME_CONVERSION_OVERRIDES = new HashMap<>();
    static {
        NAME_CONVERSION_OVERRIDES.put("SetP2PEnabled", "SetP2PEnabled");
        NAME_CONVERSION_OVERRIDES.put("P2PEnabled", "p2pEnabled");
    }

    @Override
    public final void writeCode() throws IOException {
        writeCategories();
        writeEnums();
        writeTypes();
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
        return initialCharacterToUpperCase(baseName);
    }

    @Override
    public final String convertEnumComponentName(
        final String baseName
    ) {
        return initialCharacterToUpperCase(baseName);
    }

    @Override
    public final String convertEnumComponentName(
        final String enumBaseName,
        final String componentBaseName
    ) {
        return initialCharacterToUpperCase(enumBaseName) + initialCharacterToUpperCase(componentBaseName);
    }

    @Override
    public final String convertFunctionName(
        final String baseName
    ) {
        if (NAME_CONVERSION_OVERRIDES.containsKey(baseName)) {
            return NAME_CONVERSION_OVERRIDES.get(baseName);
        } else {
            return initialCharacterToUpperCase(baseName);
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
        return initialCharacterToUpperCase(baseName);
    }

    @Override
    public final String convertStructDataMemberName(
        final String baseName
    ) {
        // We don't WANT to export these, but apparently it is required for the idiot json implementation.
        return initialCharacterToUpperCase(baseName);
    }

    @Override
    public final String convertTypedefName(
        final String baseName
    ) {
        return initialCharacterToUpperCase(baseName);
    }

    @Override
    public final LanguageId getLanguageId() {
        return LanguageId.GO;
    }

    private String getIntAsStringExposedType(
        final DataDescriptor desc
    ) {
        return String.format("int%d", getIntAsStringContainerSize(desc));
    }

    //  ------------------------------------------------------------------------
    //  Categories
    //  ------------------------------------------------------------------------

    private void writeCategories() throws IOException {
        for (var c : Catalog.getCategories()) {
            if (c.hasGeneratedCode())
                writeCategory(c);
        }
    }

    private void writeCategory(
        final Category category
    ) throws IOException {
        var fileName = initialTokenToLowerCase(category.getBaseName()) + "_gen.go";
        var fullName = GO_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);
        var imports = new LinkedList<String>();
        imports.add("fmt");
        for (var fid : category.getFunctionIds()) {
            var func = Catalog.getFunction(fid);
            imports.addAll(func.getImportRequirementsFor(LanguageId.GO));
        }
        writeImportStatements(w, imports);

        w.write("\n");
        w.write("// Category: " + initialCharacterToUpperCase(category.getBaseName()) + "\n");
        for (var s : translate(category.getDescription())) {
            w.write("// " + s + "\n");
        }

        for (var fid : category.getFunctionIds()) {
            var func = Catalog.getFunction(fid);
            if (func.matches(LanguageId.GO) && !(func instanceof HardCodedFunction)) {
                writeFunction(w, func);
            }
        }

        w.close();
    }

    //  ------------------------------------------------------------------------
    //  Enumerators
    //  ------------------------------------------------------------------------

    /**
     * writes a Go-equivalent to our defined enums.
     * Go does not have enumerators, they are generally implemented as constants.
     * We *can* type them, so the actual enum becomes a type, and the values are constants
     */
    private void writeEnums() throws IOException {
        var fileName = "enums_gen.go";
        var fullName = GO_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        for (var enumerator : LiqidEntity.sort(Catalog.getEnumeratorsFor(LanguageId.GO))) {
            writeEnumerator(w, (Enumerator) enumerator);
        }

        w.close();
    }

    private void writeEnumerator(
        final BufferedWriter writer,
        final Enumerator enumerator
    ) throws IOException {
        var enumName = convertEnumName(enumerator);
        var enumType = GoCommon.getDataTypeString(new IntrinsicDataDescriptor(enumerator.getIntrinsicTypeId()));

        writer.write("\n");
        writer.write("// " + enumName + "\n");
        for (var s : translate(enumerator.getDescription())) {
            writer.write("// " + s + "\n");
        }
        writer.write("type " + enumName + " " + enumType + "\n");

        writer.write("const (\n");
        var first = true;
        for (var comp : enumerator.getComponents()) {
            var compName = enumName + convertEnumComponentName(comp);
            if (!first) {
                writer.write("\n");
            }

            writer.write("\t// " + compName + "\n");
            for (var s : translate(comp.getDescription())) {
                writer.write("\t// " + s + "\n");
            }
            writer.write("\t" + compName + " " + enumName + " = " + comp.getLiteralString() + "\n");

            first = false;
        }
        writer.write(")\n");
    }

    //  ------------------------------------------------------------------------
    //  Functions
    //  ------------------------------------------------------------------------

    private void writeFunction(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        if (function instanceof CompositeStructFunction cf) {
            writeCompositeStructFunction(writer, cf);
        } else if (function instanceof FilterFunction ff) {
            writeFilterFunction(writer, ff);
        } else if (function instanceof GetPostPutFunction gf) {
            writeGetPostPutFunction(writer, gf);
        } else if (function instanceof HardCodedFunction) {
            //  nothing to do
            System.out.println("Skipping hard-coded function " + function.getBaseName());
        } else if (function instanceof RegularFunction rf) {
            writeRegularFunction(writer, rf);
        } else if (function instanceof WiredFunction wf) {
            writeWiredFunction(writer, wf);
        }
    }

    //  ------------------------------------------------------------------------
    //  Composite Struct Function writer
    //  ------------------------------------------------------------------------

    private void writeCompositeStructFunction(
        final BufferedWriter writer,
        final CompositeStructFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());
        var compositeObjectName = "composite";

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function);
        writeFunctionLogEntryTrace(writer, function);
        writeCompositeStructFunctionGetters(writer, function, compositeObjectName);

        if (function.getRestMethod() == null) {
            //  this is a getter only, so it definitely has a result
            writer.write("    return &composite, nil\n");
        } else {
            writeFunctionPathCode(writer, function, Collections.emptyMap());
            writeFunctionInvokeWith(writer, function, Collections.emptyList(), "composite");
            writeFunctionReturn(writer, function);
        }
        writer.write("}\n");
    }

    private void writeCompositeStructFunctionGetters(
        final BufferedWriter writer,
        final CompositeStructFunction function,
        final String destinationName
    ) throws IOException {
        writer.write("\tvar "
                         + destinationName
                         + " = "
                         + convertStructName(function.getCompositeStruct())
                         + "{}\n");

        var getters = Function.sort(function.getGetterFunctions());
        var fx = 0;
        for (var g : getters) {
            var getter = (Function) g;
            var destMemberName = function.getStructMemberBaseNameFor(getter.getFunctionId());
            var tempName = "value" + fx;
            var errName = "err" + fx;

            var paramList = getter.getParameters()
                                  .stream()
                                  .filter(p -> !p.isOptional())
                                  .map(this::convertParameterName)
                                  .collect(Collectors.toCollection(LinkedList::new));

            writer.write(String.format("\t%s, %s := client.%s(%s)\n",
                                       tempName,
                                       errName,
                                       convertFunctionName(getter),
                                       String.join(", ", paramList)));

            writer.write(String.format("\tif %s != nil {\n", errName));
            writer.write(String.format("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, nil, %s)\n", errName));
            writer.write(String.format("\t\treturn nil, %s\n", errName));
            writer.write("\t}\n");

            writer.write(String.format("\t%s.%s = *%s\n", destinationName, destMemberName, tempName));
            fx++;
        }
    }

    //  ------------------------------------------------------------------------
    //  Filter Function writer
    //  ------------------------------------------------------------------------

    private void writeFilterFunction(
        final BufferedWriter writer,
        final FilterFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function);
        writeFunctionLogEntryTrace(writer, function);

        var nilRetStr = getNilReturnValueFor(function.getResultDataDescriptor());

        //  Parameters will include (first) the identifier(s) we're looking for,
        //  followed by zero or more parameters which are simply passed to the base getter.
        var idParamCount = function.getMemberBaseNames().size();
        var idParamNames = new LinkedList<String>();
        var otherParamNames = new LinkedList<String>();
        for (Parameter param : function.getParameters()) {
            var baseName = param.getBaseName();
            if (idParamNames.size() < idParamCount) {
                idParamNames.add(String.format("%s == item.%s()",
                                               convertParameterName(baseName),
                                               GoCommon.toGetterName(baseName)));
            } else {
                otherParamNames.add(convertParameterName(baseName));
            }
        }

        var otherParamsString = String.join(", ", otherParamNames);
        var funcStr = "client."
            + convertFunctionName(function.getBaseFunction())
            + "("
            + otherParamsString
            + ")";
        writer.write(String.format("\tlist, getErr := %s\n", funcStr));

        writer.write("\tif getErr != nil {\n");
        writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, " + nilRetStr + ", getErr)\n");
        writer.write("\t\treturn " + nilRetStr + ", getErr\n");
        writer.write("\t}\n");

        writer.write("\tfor _, item := range list {\n");

        var expression = String.join(" && ", idParamNames);
        writer.write("\t\tif " + expression + " {\n");
        writer.write("\t\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, item, nil)\n");
        writer.write(String.format("\t\t\treturn %sitem, nil\n",
                                   GoCommon.weShouldReturnAPointerFor(function.getResultDataDescriptor()) ? "&" : ""));
        writer.write("\t\t}\n");
        writer.write("\t}\n");

        writer.write("\terr := fmt.Errorf(\"cannot find requested item\")\n");
        writer.write("\tclient.traceLogger.Printf(LogFmtReturn2, fn, nil, err)\n");
        writer.write("\treturn nil, err\n");
        writer.write("}\n");
    }

    //  ------------------------------------------------------------------------
    //  Get-Post-Put Function writer
    //  ------------------------------------------------------------------------

    private void writeGetPostPutFunction(
        final BufferedWriter writer,
        final GetPostPutFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function);
        writeFunctionLogEntryTrace(writer, function);
        writeGetPostPutFunctionBody(writer, function);
        writeFunctionReturn(writer, function);
        writer.write("}\n");
    }

    private void writeGetPostPutFunctionBody(
        final BufferedWriter writer,
        final GetPostPutFunction function
    ) throws IOException {
        //  getter code
        var getter = function.getGetterFunction();
        List<String> getterParamNames =
            getter.getParameters()
                  .stream()
                  .map(this::convertParameterName)
                  .collect(Collectors.toList());

        var paramList = String.join(", ", getterParamNames);
        var methodName = convertFunctionName(getter);
        writer.write("\tobject, getErr := client." + methodName + "(" + paramList + ")\n");
        writer.write("\tif getErr != nil {\n");
        writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, nil, getErr)\n");
        writer.write("\t\treturn nil, getErr\n");
        writer.write("\t}\n");

        //	Update the various entities per the given parameters.
        for (var param : function.getParameters()) {
            if (param instanceof BodyParameter bp) {
                var paramName = convertParameterName(bp.getBaseName());
                var code = new StringBuilder();
                code.append("object.")
                        .append(convertStructDataMemberName(bp.getBaseName()))
                        .append(" = ").append(paramName);

                if (bp.isOptional()) {
                    writer.write(String.format("\tif %s != nil {\n", paramName));
                    writer.write(String.format("\t\t%s\n", code));
                    writer.write("\t}\n");
                } else {
                    writer.write(String.format("\t%s\n", code));
                }
            }
        }

        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);
        writeFunctionInvokeWith(writer, function, Collections.emptyList(), "object");
    }

    //  ------------------------------------------------------------------------
    //  Regular Function writer
    //  ------------------------------------------------------------------------

    private void writeRegularFunction(
        final BufferedWriter writer,
        final RegularFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function);
        writeFunctionLogEntryTrace(writer, function);
        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);

        var queryParams = new LinkedList<QueryParameter>();
        BodyParameter bodyParam = null;

        for (var param : function.getParameters()) {
            if (param instanceof QueryParameter qp) {
                queryParams.add(qp);
            } else if (param instanceof BodyParameter bp) {
                bodyParam = bp;
            }
        }

        var bodyParamStr = (bodyParam != null) ? convertParameterName(bodyParam) : null;
        writeFunctionInvokeWith(writer, function, queryParams, bodyParamStr);
        writeFunctionReturn(writer, function);
        writer.write("}\n");
    }

    //  ------------------------------------------------------------------------
    //  Wired Function writer
    //  ------------------------------------------------------------------------

    private void writeWiredFunction(
        final BufferedWriter writer,
        final WiredFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());
        var params = function.getParameters();

        writeFunctionHeader(writer, function, funcName);
        writeFunctionSignature(writer, function);
        writeFunctionLogEntryTrace(writer, function);
        var translatedParams = writeParameterTranslations(writer, params);
        writeFunctionPathCode(writer, function, translatedParams);
        writeWiredFunctionBody(writer, function, translatedParams);
        writeFunctionReturn(writer, function);
        writer.write("}\n");
    }

    private void writeWiredFunctionBody(
        final BufferedWriter writer,
        final WiredFunction function,
        final Map<String, String> translatedParams
    ) throws IOException {
        var methodStr = function.getRestMethod().getValue();
        writer.write("\tcliReq := NewClientRequest().SetMethod(\"" + methodStr + "\").SetPartialPath(path)\n");

        if (function.hasApiStructId()) {
            var apiStruct = Catalog.getStruct(function.getApiStructId());
            var initName = "New" + GoCommon.toStructName(apiStruct.getBaseName());
            writer.write("\tapiParam := " + initName + "()\n");
            for (var p : function.getParameters()) {
                if (p instanceof WiredParameter wp) {
                    var paramName = convertParameterName(wp.getBaseName());
                    paramName = translatedParams.getOrDefault(paramName, paramName);
                    if (p.isOptional()) {
                        writer.write("\tif " + paramName + " != nil {\n");
                        writeTargetAssignments(writer, "\t\t", wp, paramName);
                        writer.write("\t}\n");
                    } else {
                        writeTargetAssignments(writer, "\t", wp, paramName);
                    }
                }
            }

            if (function.hasStructPresets()) {
                writer.write("\tvar presetErr error\n");
                for (var entry : function.getStructPresets().entrySet()) {
                    var target = entry.getKey();
                    var memberName = "apiParam." + getCompositeTargetName(target);
                    var presetId = entry.getValue();
                    var expression = PRESET_EXPRESSIONS.get(presetId);
                    writer.write("\t" + memberName + ", presetErr = " + expression + "\n");
                    writer.write("\tif presetErr != nil {\n");
                    writer.write("\t\treturn nil, presetErr\n");
                    writer.write("\t}\n");
                }
            }

            writer.write("\tcliReq.SetJSONBody(apiParam)\n");
        }

        var nilRetStr = getNilReturnValueFor(function.getResultDataDescriptor());

        writer.write("\n");
        writer.write("\tcliErr := cliReq.Invoke(client)\n");
        writer.write("\tif cliErr != nil {\n");
        writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, " + nilRetStr + ", cliErr)\n");
        writer.write("\t\treturn " + nilRetStr + ", cliErr\n");
        writer.write("\t}\n");
    }

    //  ------------------------------------------------------------------------
    //  Function writer general helpers
    //  ------------------------------------------------------------------------

    private void writeFunctionHeader(
        final BufferedWriter writer,
        final Function function,
        final String funcName
    ) throws IOException {
        writer.write("\n");
        var prefix = convertFunctionName(funcName) + " ";
        var suffixes = translate(function.getDescription());
        writeBlock(writer, "// ", prefix, suffixes);

        if (function.hasParameters()) {
            writer.write("//\n");
            for (var param : function.getParameters()) {
                prefix = "Param " + convertParameterName(param.getBaseName()) + ": ";
                suffixes = translate(param.getDescription());
                writeBlock(writer, "// ", prefix, suffixes);
            }
        }

        if (function.hasResult()) {
            prefix = "Returns: ";
            suffixes = translate(function.getResultDescription());
            writeBlock(writer, "// ", prefix, suffixes);
        }

        if (function.isDeprecated()) {
            writer.write("//\n");
            prefix = "Deprecated: ";
            suffixes = translate(function.getDeprecatedMessage());
            writeBlock(writer, "// ", prefix, suffixes);
        }
    }

    private void writeFunctionInvokeWith(
        final BufferedWriter writer,
        final Function function,
        final Collection<QueryParameter> queryParams,
        final String bodyName
    ) throws IOException {
        writer.write("\tcliReq := NewClientRequest()\n");
        writer.write("\tcliReq.SetMethod(\"" + function.getRestMethod().getValue() + "\")\n");
        writer.write("\tcliReq.SetPartialPath(path)\n");

        if (queryParams.size() + function.getQueryPresets().size() > 0) {
            writer.write("\tqueryParams := make(map[string]string)\n");
            for (var queryParameter : queryParams) {
                var paramName = convertParameterName(queryParameter.getBaseName());
                if (queryParameter.isOptional()) {
                    writer.write("\tif " + paramName + " != nil {\n");
                    writer.write(String.format("\t\tqueryParams[\"%s\"] = fmt.Sprintf(\"%%v\", *%s)\n",
                                               queryParameter.getQueryTag(), paramName));
                    writer.write("\t}\n");
                } else {
                    writer.write(String.format("\tqueryParams[\"%s\"] = fmt.Sprintf(\"%%v\", %s)\n",
                                               queryParameter.getQueryTag(), paramName));
                }
            }

            for (var entry : function.getQueryPresets().entrySet()) {
                var queryTag = entry.getKey();
                var presetExpr = PRESET_EXPRESSIONS.get(entry.getValue());
                writer.write("\tqueryParams[\"" + queryTag + "\"] = \"" + presetExpr + "\"\n");
            }
            writer.write("\tcliReq.SetParameters(queryParams)\n");
        }

        if (bodyName != null) {
            writer.write("\tcliReq.SetJSONBody(" + bodyName + ")\n");
        }
        writer.write("\tcliErr := cliReq.Invoke(client)\n");
        writer.write("\tif cliErr != nil {\n");
        writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)\n");
        writer.write("\t\treturn " + getNilReturnValueFor(function.getResultDataDescriptor()) + ", cliErr\n");
        writer.write("\t}\n");
    }

    private void writeFunctionLogEntryTrace(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());
        writer.write("\tvar fn = \"" + funcName + "\"\n");
        var sb = new StringBuilder();
        var params = function.getParameters();
        sb.append("LogFmtEnter").append(params.size()).append(", fn");
        for (var param : params) {
            sb.append(", ").append(convertParameterName(param.getBaseName()));
        }
        writer.write("\tclient.traceLogger.Printf(" + sb + ")\n");
    }

    /**
     * generates the method code which is responsible for evaluating the results
     * of the lower-level method invocation, and returning that to the SDK caller
     */
    private void writeFunctionReturn(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        if (function.hasResult()) {
            var desc = function.getResultDataDescriptor();
            if (desc.getDataTypeId() == DataTypeId.OCTET_STREAM) {
                // The result is a (pointer to) an octet stream - simply return the address of cliReq []byte body
                writer.write("\tclient.traceLogger.Printf(LogFmtReturn2, fn, cliReq.body, nil)\n");
                writer.write("\treturn &cliReq.body, nil\n");
            } else {
                var defaultStr = getNilReturnValueFor(desc);

                // convert the resulting byte stream to json, and deal with any consequent errors
                writer.write("\n");
                writer.write(String.format("\tvar respWrapper %s\n", getResultWrapperName(desc)));

                writer.write("\tjsonErr := cliReq.GetJSONResponse(&respWrapper)\n");
                writer.write("\tif jsonErr != nil {\n");
                writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, " + defaultStr + ", jsonErr)\n");
                writer.write("\t\treturn " + defaultStr + ", jsonErr\n");
                writer.write("\t}\n");

                writer.write("\n");
                writer.write("\tif (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {\n");
                writer.write("\t\tfmtErr := fmt.Errorf(\"%v\", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))\n");
                writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, " + defaultStr + ", fmtErr)\n");
                writer.write("\t\treturn " + defaultStr + ", fmtErr\n");
                writer.write("\t}\n");

                var indexStr = "";
                if (!desc.isArray()) {
                    indexStr = "[0]";
                    writer.write("\n");
                    writer.write("\tif len(respWrapper.Response.Data) != 1 {\n");
                    writer.write("\t\tfmtErr := fmt.Errorf(\"malformed data encountered - we should have exactly one data entity\")\n");
                    writer.write("\t\tclient.traceLogger.Printf(LogFmtReturn2, fn, " + defaultStr + ", fmtErr)\n");
                    writer.write("\t\treturn " + defaultStr + ", fmtErr\n");
                    writer.write("\t}\n");
                }

                var ptr = GoCommon.weShouldReturnAPointerFor(desc);
                var addrStr = ptr ? "&" : "";
                var derefStr = ptr ? "*" : "";
                writer.write("\n");
                writer.write("\tvar result = " + addrStr + "respWrapper.Response.Data" + indexStr + "\n");
                writer.write("\tclient.traceLogger.Printf(LogFmtReturn2, fn, " + derefStr + "result, nil)\n");

                var iasBits = getIntAsStringBits(desc);
                if (iasBits > 0) {
                    var idd = (IntrinsicDataDescriptor) desc;
                    if (idd.getIntrinsicTypeId() == IntrinsicTypeId.INT32_AS_DECIMAL_STRING) {
                        writer.write("\tiResult, _ := strconv.ParseInt(result, 10, 32)\n");
                        writer.write("\treturn int32(iResult), nil\n");
                    } else if (iasBits == 64) {
                        writer.write("\tiResult, _ := strconv.ParseInt(result[2:], 16, 64)\n");
                        writer.write("\treturn int64(iResult), nil\n");
                    } else {
                        writer.write("\tiResult, _ := strconv.ParseInt(result[2:], 16, 32)\n");
                        writer.write("\treturn int32(iResult), nil\n");
                    }
                } else {
                    writer.write("\treturn result, nil\n");
                }
            }
        } else {
            writer.write("\treturn nil\n");
        }
    }

    private void writeFunctionSignature(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        var funcName = convertFunctionName(function.getBaseName());
        var receiverStr = "(client *" + GO_LIQID_CLIENT_NAME + ")";

        //  parameters
        var sbParameters = new StringBuilder();
        sbParameters.append("(");
        var first = true;
        for (var p : function.getParameters()) {
            if (!first) {
                sbParameters.append(", ");
            }

            var typeStr = GoCommon.getExposedTypeString(p.getDataDescriptor());
            var pName = convertParameterName(p.getBaseName());
            var pType = (p.isOptional() ? "*" : "") + typeStr;
            sbParameters.append(pName).append(" ").append(pType);
            first = false;
        }
        sbParameters.append(")");

        //  returns
        var sbReturn = new StringBuilder();
        if (function.hasResult()) {
            var desc = function.getResultDataDescriptor();
            sbReturn.append("(")
                    .append(GoCommon.weShouldReturnAPointerFor(desc) ? "*" : "")
                    .append(GoCommon.getExposedTypeString(desc))
                    .append(", error)");
        } else {
            sbReturn.append("error");
        }

        writer.write(String.format("func %s %s%s %s {\n", receiverStr, funcName, sbParameters, sbReturn));
    }

    private void writeFunctionPathCode(
        final BufferedWriter writer,
        final Function function,
        final Map<String, String> translatedParams
    ) throws IOException {
        var components = getPathComponents(function);
        writer.write("\n");
        writer.write("\tpath := \"" + components._partialPath + "\"\n");

        for (var param : components._pathParameters) {
            var paramName = convertParameterName(param.getBaseName());
            paramName = translatedParams.getOrDefault(paramName, paramName);
            if (param.isOptional()) {
                writer.write("\tpath += \"/\"\n");
                writer.write("\tif " + paramName + " != nil {\n");
                writer.write("\t\tpath += fmt.Sprintf(\"%v\", " + paramName + ")\n");
                writer.write("\t}\n");
            } else {
                writer.write("\tpath += \"/\" + fmt.Sprintf(\"%v\", " + paramName + ")\n");
            }
        }

        if (components._pathPresets.size() > 0) {
            var px = 0;
            for (var preset : components._pathPresets) {
                var presetName = "preset" + px;
                writer.write(String.format("\t%s, ppErr := %s\n", presetName, PRESET_EXPRESSIONS.get(preset)));
                writer.write("\tif ppErr != nil {\n");
                writer.write("\t\treturn nil, ppErr\n");
                writer.write("\t}\n");
                writer.write("\tpath += \"/\" + string(" + presetName + ")\n");
                px++;
            }
        }

        //	We don't add query params to the path code here.
        //	They are applied elsewhere, via ClientRequest.SetParameters()
    }

    private void writeTargetAssignments(
        final BufferedWriter writer,
        final String tabPrefix,
        final WiredParameter wp,
        final String paramName
    ) throws IOException {
        for (var target : wp.getTargets()) {
            writer.write(tabPrefix
                             + "apiParam."
                             + getCompositeTargetName(target)
                             + " = "
                             + (wp.isOptional() ? "*" : "")
                             + paramName
                             + "\n");
        }
    }

    //  ------------------------------------------------------------------------
    //  Types
    //  ------------------------------------------------------------------------

    private void writeTypes() throws IOException {
        var fileName = "types_gen.go";
        var fullName = GO_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        var structs = Catalog.getStructsFor(LanguageId.GO);

        var imports = new TreeMap<String, Object>();
        imports.put("fmt", null);
        imports.put("strconv", null);
        for (var struct : structs) {
            for (var ir : struct.getImportRequirementsFor(LanguageId.GO)) {
                imports.put(ir, null);
            }
            for (var m : struct.getMembersFor(LanguageId.GO)) {
                for (var ir : m.getImportRequirementsFor(LanguageId.GO)) {
                    imports.put(ir, null);
                }
            }
        }
        writeImportStatements(w, imports.keySet());

        for (var struct : structs) {
            //  This is a silly hack - we don't want to generate the struct definition for LiqidClient
            //  for Go... because it is already generated. But we can't mark it hardcoded or else it
            //  won't generate for other languages.
            if (struct.getBaseName().equalsIgnoreCase(GO_LIQID_CLIENT_NAME)) {
                continue;
            }

            if (!struct.isHardCoded()) {
                writeStruct(w, struct);
            }
        }
        for (var typedef : Catalog.getTypedefsFor(LanguageId.GO)) {
            writeTypedef(w, typedef);
        }

        w.close();
    }

    private void writeStruct(
        final BufferedWriter writer,
        final Struct struct
    ) throws IOException {
        var aggregate = struct.getAggregateDataMembersFor(LanguageId.GO);
        writeStructContent(writer, struct, aggregate);
        writeStructInitializer(writer, struct, aggregate);
        writeStructAccessors(writer, struct, aggregate);
        writeStructWrapper(writer, struct);
    }

    private void writeStructAccessors(
        final BufferedWriter writer,
        final Struct struct,
        final Collection<StructDataMember> members
    ) throws IOException {
        var structName = convertStructName(struct.getBaseName());

        for (var member : members) {
            var getterName = GoCommon.toGetterName(member.getBaseName());
            var setterName = GoCommon.toSetterName(member.getBaseName());
            var memberName = convertStructDataMemberName(member.getBaseName());

            var dataDesc = member.getDataDescriptor();
            var iasBits = CodeWriter.getIntAsStringBits(dataDesc);
            var iasType = getIntAsStringExposedType(dataDesc);
            var memberTypeStr = GoCommon.getExposedTypeString(member.getDataDescriptor());

            writer.write("\n");
            writer.write("// " + getterName + " retrieves the value as the preferred SDK type\n");
            writer.write(String.format("func (s *%s) %s() %s {\n", structName, getterName, memberTypeStr));
            //TODO do translations
            if (iasBits == 0) {
                writer.write(String.format("    return s.%s\n", memberName));
            } else {
                writer.write(String.format("    result, _ := strconv.ParseInt(s.%s[2:], 16, %d)\n", memberName, iasBits));
                writer.write(String.format("    return %s(result)\n", iasType));
            }
            writer.write("}\n");

            writer.write("\n");
            writer.write("// " + setterName + " sets the value given a source of the preferred SDK type\n");
            writer.write(String.format("func (s *%s) %s(value %s) {\n", structName, setterName, memberTypeStr));
            //TODO do translations
            if (iasBits == 0) {
                writer.write(String.format("    s.%s = value\n", memberName));
            } else {
                writer.write(String.format("    s.%s = fmt.Sprintf(\"%%d\", value)\n", memberName));
            }
            writer.write("}\n");
        }
    }

    private void writeStructContent(
        final BufferedWriter writer,
        final Struct struct,
        final Collection<StructDataMember> members
    ) throws IOException {
        var typeName = convertStructName(struct);

        writer.write("\n");
        writer.write("// " + typeName + "\n");
        for (var s : translate(struct.getDescription())) {
            writer.write("// " + s + "\n");
        }
        writer.write("type " + typeName + " struct {\n");

        var first = true;
        for (var m : members) {
            if (m.matches(LanguageId.GO)) {
                if (!first) {
                    writer.write("\n");
                }
                var nameStr = convertStructDataMemberName(m.getBaseName());
                var typeStr = GoCommon.getTypeString(m.getDataDescriptor());
                writer.write("\t// " + nameStr + "\n");
                for (var s : translate(m.getDescription())) {
                    writer.write("\t// " + s + "\n");
                }
                var jsonStr = String.format("`json:\"%s\"`", m.getJsonTag());
                writer.write("\t" + nameStr + " " + typeStr + " " + jsonStr + "\n");
                first = false;
            }
        }

        writer.write("}\n");
    }

    private void writeStructInitializer(
        final BufferedWriter writer,
        final Struct struct,
        final Collection<StructDataMember> members
    ) throws IOException {
        var structName = GoCommon.toStructName(struct.getBaseName());
        var initName = "New" + structName;
        writer.write("\n");
        writer.write("// " + initName + " initializer for " + structName + " struct\n");
        writer.write("func " + initName + "() " + structName + " {\n");
        writer.write("\tobj := " + structName + "{}\n");

		for (var m : members) {
            var memberName = convertStructDataMemberName(m.getBaseName());
            var memberDesc = m.getDataDescriptor();
            var lhs = "\tobj." + memberName + " = ";
            if (m.hasDefaultValue()) {
                writer.write(lhs + getDefaultValueString(m) + "\n");
            } else if (memberDesc.isArray() || (memberDesc.getDataTypeId() == DataTypeId.STRUCT)) {
                writer.write(lhs + GoCommon.getTypeString(m.getDataDescriptor()) + "{}\n");
            }
        }

        writer.write("\treturn obj\n");
        writer.write("}\n");
    }

    private void writeStructWrapper(
        final BufferedWriter writer,
        final Struct struct
    ) throws IOException {
        var wrapperName = getWrapperNameFor(struct);

        writer.write("\n");
        writer.write("// " + wrapperName + " JSON body wrapper for " + struct.getBaseName() + "\n");
        writer.write("type " + wrapperName + " struct {\n");
        writer.write("\tResponse struct {\n");
        writer.write("\t\tCode   int            `json:\"code\"`\n");
        writer.write("\t\tErrors []ErrorMessage `json:\"errors\"`\n");
        writer.write("\t\tData   []" + convertStructName(struct.getBaseName())+ " `json:\"data\"`\n");
        writer.write("\t} `json:\"response\"`\n");
        writer.write("}\n");
    }

    private void writeTypedef(
        final BufferedWriter writer,
        final Typedef typedef
    ) throws IOException {
        var typeName = convertTypedefName(typedef);
        var baseType = GoCommon.getTypeString(typedef.getSourceDataType());

        writer.write("\n");
        writer.write("// " + typeName + "\n");
        for (var s : translate(typedef.getDescription())) {
            writer.write("// " + s + "\n");
        }
        writer.write("type " + typeName + " " + baseType + "\n");
        writeTypedefWrapper(writer, typedef);
    }

    private void writeTypedefWrapper(
        final BufferedWriter writer,
        final Typedef typedef
    ) throws IOException {
        var wrapperName = getWrapperNameFor(typedef);
        writer.write("\n");
        writer.write("// " + wrapperName + " JSON body wrapper for " + typedef.getBaseName() + "\n");
        writer.write("type " + wrapperName + " struct {\n");
        writer.write("\tResponse struct {\n");
        writer.write("\t\tCode   int            `json:\"code\"`\n");
        writer.write("\t\tErrors []ErrorMessage `json:\"errors\"`\n");
        writer.write("\t\tData   []" + convertStructName(typedef.getBaseName())+ " `json:\"data\"`\n");
        writer.write("\t} `json:\"response\"`\n");
        writer.write("}\n");
    }

    //  ------------------------------------------------------------------------
    //  Helper methods
    //  ------------------------------------------------------------------------

    /**
     * Returns a nil-type value of the appropriate type, for the given data descriptor.
     * We presume the data type of the nil should correspond to the SDK type of the entity.
     */
    private static String getNilReturnValueFor(
        final DataDescriptor descriptor
    ) {
        if (descriptor.isArray()) {
            return "nil";
        } else if (descriptor.getDataTypeId() == DataTypeId.INTRINSIC) {
            return switch (((IntrinsicDataDescriptor) descriptor).getIntrinsicTypeId()) {
                case BOOLEAN -> "false";
                case FLOAT -> "0.0";
                case INT32,
                    INT64,
                    INT16_AS_STRING,
                    INT24_AS_STRING,
                    INT32_AS_STRING,
                    INT32_AS_DECIMAL_STRING,
                    INT64_AS_STRING -> "0";
                case STRING -> "\"\"";
                case STRING_ARRAY, STRING_ARRAY_MAP -> "nil";
            };
        } else {
            return "nil";
        }
    }

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
                    return convertEnumName(enumerator) + convertEnumComponentName(comp);
                }
            }
        }

        throw new RuntimeException("Default value can only be set for intrinsic or enum data descriptors");
    }

    //  produces a struct name to represent the wrapper which should be created
    //  for deserialization of a JSON message containing the struct.
    private static String getResultWrapperName(
        final DataDescriptor descriptor
    ) {
        if (descriptor.getDataTypeId() == DataTypeId.OCTET_STREAM) {
            throw new RuntimeException("getResultWrapperName invoked for octet stream data type");
        }
        var dtStr = initialCharacterToUpperCase(GoCommon.getDataTypeString(descriptor));
        return dtStr + "Wrapper";
    }

    private static String getWrapperNameFor(
        final Struct struct
    ) {
        return GoCommon.toStructName(struct.getBaseName()) + "Wrapper";
    }

    private static String getWrapperNameFor(
        final Typedef typedef
    ) {
        return GoCommon.toStructName(typedef.getBaseName()) + "Wrapper";
    }

    /**
     * writes the first few lines of every .go file
     */
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
        writer.write("\n");
        writer.write("package " + GoCommon.GO_PACKAGE_NAME + "\n");
    }

    /**
     * imports are very touchy in Go - we are not allowed to import something we do not use.
     */
    private void writeImportStatements(
        final BufferedWriter writer,
        final Collection<String> importStrings
    ) throws IOException {
        if (!importStrings.isEmpty()) {
            writer.write("\n");
            writer.write("import (\n");
            for (var s : importStrings) {
                writer.write("\t\"" + s + "\"\n");
            }
            writer.write(")\n");
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
                writer.write(String.format("    var %s = fmt.Sprintf(\"0x%%0%dx\", %s)\n",
                                           translatedName,
                                           hexDigits,
                                           paramName));
                translatedParams.put(paramName, translatedName);
            }
        }

        return translatedParams;
    }
}
