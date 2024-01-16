//
// Copyright (c) 2022,2023 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.writers.python;

import com.liqid.sdk.generator.Main;
import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.liqidEntityModels.StructId;
import com.liqid.sdk.generator.liqidEntityModels.functions.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.BodyParameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.parameters.WiredParameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.Struct;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructDataMember;
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

public class PythonCodeWriter extends CodeWriter {

    private static final String PYTHON_CODE_BASE_DIRECTORY = ARTIFACTS_DIRECTORY + "/python/liqidsdk";

    private static final Map<HttpMethod, String> REST_METHOD_MAP = new HashMap<>();
    static {
        REST_METHOD_MAP.put(HttpMethod.DELETE, "base_client.HttpDelete");
        REST_METHOD_MAP.put(HttpMethod.GET, "base_client.HttpGet");
        REST_METHOD_MAP.put(HttpMethod.POST, "base_client.HttpPost");
        REST_METHOD_MAP.put(HttpMethod.PUT, "base_client.HttpPut");
    }

    private static final Map<PresetId, String> PRESET_EXPRESSIONS = new HashMap<>();
    static {
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_RACK, "self._get_preset_rack()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_SHELF, "self._get_preset_shelf()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_COORDINATES_NODE, "self._get_preset_node()");
        PRESET_EXPRESSIONS.put(PresetId.CURRENT_FABRIC_ID, "self._get_preset_fabric_id()");
        PRESET_EXPRESSIONS.put(PresetId.ALWAYS_FALSE, "False");
        PRESET_EXPRESSIONS.put(PresetId.ALWAYS_TRUE, "True");
    }

    private static final Map<String, String> NAME_CONVERSION_OVERRIDES = new HashMap<>();
    static {
        NAME_CONVERSION_OVERRIDES.put("SetP2PEnabled", "set_p2p_enabled");
        NAME_CONVERSION_OVERRIDES.put("P2PEnabled", "p2p_enabled");
        NAME_CONVERSION_OVERRIDES.put("get_p2_p_enabled", "get_p2p_enabled");
        NAME_CONVERSION_OVERRIDES.put("set_p2_p_enabled", "set_p2p_enabled");
    }

    @Override
    public final LanguageId getLanguageId() {
        return LanguageId.PYTHON;
    }

    @Override
    public final void writeCode() throws IOException {
        writeEnumerators();
        writeClasses();
        writeLiqidClient();
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
            return toSnakeCase(baseName);
        }
    }

    @Override
    public final String convertParameterName(
        final String baseName
    ) {
        if (NAME_CONVERSION_OVERRIDES.containsKey(baseName)) {
            return NAME_CONVERSION_OVERRIDES.get(baseName);
        } else{
            return toSnakeCase(baseName);
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
        return Writer.initialCharacterToUpperCase(baseName);
    }

    @Override
    public final String convertTypedefName(
        final String baseName
    ) {
        return convertStructName(baseName);
    }

    private String toGetterName(
        final String baseName
    ) {
        var name = "get_" + toSnakeCase(baseName);
        if (NAME_CONVERSION_OVERRIDES.containsKey(name)) {
            name = NAME_CONVERSION_OVERRIDES.get(name);
        }
        return name;
    }

    private String toSetterName(
        final String baseName
    ) {
        var name = "set_" + toSnakeCase(baseName);
        if (NAME_CONVERSION_OVERRIDES.containsKey(name)) {
            name = NAME_CONVERSION_OVERRIDES.get(name);
        }
        return name;
    }

    //  ------------------------------------------------------------------------
    //  Writers for enumerator classes
    //  These are implemented as valued enumerations.
    //  ------------------------------------------------------------------------

    private void writeEnumerators() throws IOException {
        var fileName = "constants.py";
        var fullName = PYTHON_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        for (var enumerator : LiqidEntity.sort(Catalog.getEnumerators())) {
            writeEnumeratorClass(w, (Enumerator) enumerator);
        }

        w.close();
    }

    private void writeEnumeratorClass(
        final BufferedWriter writer,
        final Enumerator enumerator
    ) throws IOException {
        var enumName = convertEnumName(enumerator);
        writer.write("\n");
        writer.write("\"\"\"\n");
        writer.write(enumName + " enumeration constants\n");
        for (var s : translate(enumerator.getDescription())) {
            writer.write(s + "\n");
        }
        writer.write("\"\"\"\n");

        var comps = enumerator.getComponents();
        for (var component : comps) {
            writer.write(convertEnumComponentName(enumerator, component)
                             + " = "
                             + component.getLiteralString()
                             + "\n");
        }
    }

    //  ------------------------------------------------------------------------
    //  Write separate module for LiqidClient class
    //  ------------------------------------------------------------------------

    private void writeLiqidClient() throws IOException {
        var fileName = "liqid_client.py";
        var fullName = PYTHON_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        var lcStruct = Catalog.getStruct(StructId.LIQID_CLIENT);

        w.write("from abc import ABC\n");
        w.write("import json\n");
        w.write("from liqidsdk import base_client, exceptions\n");
        w.write("from liqidsdk.classes import *\n");

        writeClientNewFunctions(w);

        var className = convertStructName(lcStruct.getBaseName());

        w.write("\n\n");
        var sb = new StringBuilder();
        sb.append("class ").append(className);
        if (className.equals("LiqidClient")) {
            sb.append("(base_client.LiqidClientBase, ABC)");
        } else {
            if (lcStruct.hasBaseStruct()) {
                sb.append("(").append(convertStructName(lcStruct.getBaseStruct())).append(")");
            }
        }

        sb.append(":\n");
        w.write(sb.toString());

        var baseMembers = lcStruct.getBaseDataMembersFor(LanguageId.PYTHON);
        var localMembers = lcStruct.getDataMembersFor(LanguageId.PYTHON);

        writeClassDescription(w, lcStruct.getDescription());
        writeStructClassInitializer(w, lcStruct, localMembers);
        writeStructClassStr(w, localMembers, baseMembers);
        w.write("\n");
        writeClientFunctions(w, lcStruct);

        w.close();
    }

    private void writeClientFunction(
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

    private void writeClientFunctions(
        final BufferedWriter writer,
        final Struct struct
    ) throws IOException {
        for (var member : struct.getFunctionMembersFor(LanguageId.PYTHON)) {
            var func = Catalog.getFunction(member.getFunctionId());
            writeClientFunction(writer, func);
        }
    }

    private void writeClientNewFunctions(
        final BufferedWriter writer
    ) throws IOException {
        writer.write("\n");
        writer.write("\n");
        writer.write("def new_client(host_string, port_number=base_client.DefaultPort, timeout=base_client.DefaultTimeout):\n");
        writer.write("    lc = LiqidClient()\n");
        writer.write("    lc.SecureHTTP = False\n");
        writer.write("    lc.HostAddress = host_string\n");
        writer.write("    lc.PortNumber = port_number\n");
        writer.write("    lc.TimeoutInSeconds = timeout\n");
        writer.write("    lc.setup_connection()\n");
        writer.write("    return lc\n");
        writer.write("\n");
        writer.write("\n");
        writer.write("def new_secure_client(host_string, port_number=base_client.DefaultSecurePort, timeout=base_client.DefaultTimeout):\n");
        writer.write("    lc = LiqidClient()\n");
        writer.write("    lc.SecureHTTP = True\n");
        writer.write("    lc.HostAddress = host_string\n");
        writer.write("    lc.PortNumber = port_number\n");
        writer.write("    lc.TimeoutInSeconds = timeout\n");
        writer.write("    lc.setup_connection()\n");
        writer.write("    return lc\n");
    }

    //  ------------------------------------------------------------------------
    //  Function writers
    //  ------------------------------------------------------------------------

    private void writeCompositeStructFunction(
        final BufferedWriter writer,
        final CompositeStructFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function);

        writeFunctionSignature(writer, function, funcName);
        writeFunctionDocs(writer, function);
        writeFunctionEntryTrace(writer, function, funcName);
        writeParameterTypeChecks(writer, function);
        writeCompositeStructFunctionGet(writer, function);
        if (function.getRestMethod() != null) {
            writeCompositeStructFunctionPush(writer, function);
            writeFunctionReturn(writer, function, funcName);
        } else {
            writer.write("            return composite\n");
        }
        writeFunctionCatch(writer, funcName);
        writer.write("\n");
    }

    private void writeCompositeStructFunctionGet(
        final BufferedWriter writer,
        final CompositeStructFunction function
    ) throws IOException {
        writer.write("        try:\n");
        var gx = 0;
        for (var getter : function.getGetterFunctions()) {
            var getterParamNames = new LinkedList<String>();
            for (var gParam : getter.getParameters()) {
                getterParamNames.add(convertParameterName(gParam.getBaseName()));
            }
            var paramString = String.join(", ", getterParamNames);
            var getterFuncName = convertFunctionName(getter);
            writer.write("            obj" + gx + " = self." + getterFuncName + "(" + paramString + ")\n");
            gx++;
        }

        var compositeStruct = function.getCompositeStruct();
        var compositeStructName = convertStructName(compositeStruct);
        writer.write("            composite = " + compositeStructName + "()\n");
        for (var component : compositeStruct.getDataMembersFor(LanguageId.PYTHON)) {
            var gy = 0;
            for (var getterFunc : function.getGetterFunctions()) {
                if (component.getDataDescriptor().equals(getterFunc.getResultDataDescriptor())) {
                    var componentName = convertStructDataMemberName(component);
                    writer.write("            composite." + componentName + " = obj" + gy + "\n");
                    break;
                }
                gy++;
            }
        }
    }

    private void writeCompositeStructFunctionPush(
        final BufferedWriter writer,
        final CompositeStructFunction function
    ) throws IOException {
        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);
        var methodStr = REST_METHOD_MAP.get(function.getRestMethod());
        writer.write("            content_type = 'application/json'\n");
        writer.write("            req_body = json.dumps(composite.to_dictionary())\n");
        writer.write("            headers = {'content-type': content_type}\n");
        writer.write("            response, resp_body = self.send(" + methodStr + ", path, headers=headers, request_body=req_body)\n");
    }

    private void writeFilterFunction(
        final BufferedWriter writer,
        final FilterFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function);

        writeFunctionSignature(writer, function, funcName);
        writeFunctionDocs(writer, function);
        writeFunctionEntryTrace(writer, function, funcName);
        writeParameterTypeChecks(writer, function);
        writer.write("\n");

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
                                               toGetterName(baseName)));
            } else {
                otherParamNames.add(convertParameterName(baseName));
            }
        }

        var otherParamsString = String.join(", ", otherParamNames);
        String funcStr = "self."
            + convertFunctionName(function.getBaseFunction())
            + "("
            + otherParamsString
            + ")";

        writer.write("        for item in " + funcStr + ":\n");
        var expression = String.join(" and ", idParamNames);
        writer.write("            if " + expression + ":\n");
        writer.write("                self.logger.debug(\"" + funcName + " returning \" + str(item))\n");
        writer.write("                return item\n");

        writer.write("        ex = exceptions.LiqidError(\"Entity not found:\")\n");
        writer.write("        self.logger.debug(\"" + funcName + " raising \" + str(ex))\n");
        writer.write("        raise ex\n");

        writer.write("\n");
    }

    private void writeGetPostPutFunction(
        final BufferedWriter writer,
        final GetPostPutFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function);

        writeFunctionSignature(writer, function, funcName);
        writeFunctionDocs(writer, function);
        writeFunctionEntryTrace(writer, function, funcName);
        writeParameterTypeChecks(writer, function);

        writer.write("\n");
        writer.write("        try:\n");
        writeGetPostPutFunctionGetter(writer, function);

        //	Update the various entities per the given parameters.
        for (var param : function.getParameters()) {
            if (param instanceof BodyParameter bp) {
                var paramName = convertParameterName(bp.getBaseName());
                var code = new StringBuilder();
                code.append("obj.")
                        .append(convertStructDataMemberName(bp.getBaseName()))
                        .append(" = ").append(paramName);

                if (bp.isOptional()) {
                    writer.write(String.format("            if %s is not None:\n", paramName));
                    writer.write(String.format("                %s\n", code));
                } else {
                    writer.write(String.format("            %s\n", code));
                }
            }
        }

        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);
        writer.write("            req_body = json.dumps(obj.to_dictionary())\n");
        writer.write("            headers = {'content-type': 'application/json'}\n");
        writer.write("            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)\n");
        if (function.hasResult()) {
            writeFunctionReturn(writer, function, funcName);
        }

        writeFunctionCatch(writer, funcName);
        writer.write("\n");
    }

    private void writeGetPostPutFunctionGetter(
        final BufferedWriter writer,
        final GetPostPutFunction function
    ) throws IOException {
        var getter = function.getGetterFunction();
        var getterParamNames = getter.getParameters()
                                     .stream()
                                     .map(this::convertParameterName)
                                     .collect(Collectors.toList());

        var paramList = String.join(", ", getterParamNames);
        var methodName = convertFunctionName(getter);
        writer.write("            obj = self." + methodName + "(" + paramList + ")\n");
    }

    private void writeRegularFunction(
        final BufferedWriter writer,
        final RegularFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function);

        writeFunctionSignature(writer, function, funcName);
        writeFunctionDocs(writer, function);
        writeFunctionEntryTrace(writer, function, funcName);
        writeParameterTypeChecks(writer, function);
        writeRegularFunctionInvocation(writer, function);
        writeFunctionReturn(writer, function, funcName);
        writeFunctionCatch(writer, funcName);
        writer.write("\n");
    }

    private void writeRegularFunctionInvocation(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        writer.write("\n");
        writer.write("        try:\n");
        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);

        BodyParameter rbParam;
        var bodyStr = "";
        var headerStr = "";
        for (var param : function.getParameters()) {
            if (param instanceof BodyParameter bp) {
                //	body parameter will either be octet stream which we can just pass along,
                //	or a struct which we have to convert to a json-encoded string
                rbParam = bp;
                var paramName = convertParameterName(rbParam);
                if (rbParam.getDataDescriptor().getDataTypeId() == DataTypeId.OCTET_STREAM) {
                    writer.write("        content_type = 'application/octet-stream'\n");
                    writer.write("        req_body = " + paramName + "\n");
                } else {
                    writer.write("        content_type = 'application/json'\n");
                    writer.write("        req_body = json.dumps(" + paramName + ".to_dictionary())\n");
                }

                writer.write("        headers = {'content-type': content_type}\n");
                headerStr = ", headers=headers";
                bodyStr = ", request_body=req_body";
                break;
            }
        }

        var methodStr = REST_METHOD_MAP.get(function.getRestMethod());
        writer.write("            response, resp_body = self.send(" + methodStr + ", path" + headerStr + bodyStr + ")\n");
    }

    private void writeWiredFunction(
        final BufferedWriter writer,
        final WiredFunction function
    ) throws IOException {
        var funcName = convertFunctionName(function);

        writeFunctionSignature(writer, function, funcName);
        writeFunctionDocs(writer, function);
        writeFunctionEntryTrace(writer, function, funcName);
        writeParameterTypeChecks(writer, function);

        writeWiredFunctionSend(writer, function);
        if (function.hasResult()) {
            writeFunctionReturn(writer, function, "result");
        }

        writeFunctionCatch(writer, funcName);
        writer.write("\n");
    }

    private void writeWiredFunctionSend(
        final BufferedWriter writer,
        final WiredFunction function
    ) throws IOException {
        writer.write("\n");
        writer.write("        try:\n");
        var apiStruct = Catalog.getStruct(function.getApiStructId());
        writer.write("            headers = {'content-type': 'application/json'}\n");
        writer.write("            obj = " + convertStructName(apiStruct) + "()\n");

        for (var param : function.getParameters()) {
            if (param instanceof WiredParameter wp) {
                var paramName = convertParameterName(param);
                var indent = "            ";
                if (param.isOptional()) {
                    writer.write(indent + "if " + paramName + " is not None:\n");
                    indent += "    ";
                }

                for (var target : (wp.getTargets())) {
                    writer.write(indent + "obj." + getCompositeTargetName(target) + " = " + paramName + "\n");
                }
            }
        }

        for (var entry : function.getStructPresets().entrySet()) {
            var target = entry.getKey();
            var presetId = entry.getValue();

            writer.write("            obj."
                             + getCompositeTargetName(target)
                             + " = "
                             + PRESET_EXPRESSIONS.get(presetId)
                             + "\n");
        }

        var methodStr = REST_METHOD_MAP.get(function.getRestMethod());
        writer.write("\n");
        var translatedParams = writeParameterTranslations(writer, function.getParameters());
        writeFunctionPathCode(writer, function, translatedParams);
        writer.write("            req_body = json.dumps(obj.to_dictionary())\n");
        writer.write("            response, resp_body = self.send(" + methodStr + ", path, headers=headers, request_body=req_body)\n");
    }

    //  ------------------------------------------------------------------------
    //  General function-writer helpers
    //  ------------------------------------------------------------------------

    private void writeFunctionDocs(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        writer.write("        \"\"\"\n");
        for (var str : translate(function.getDescription())) {
            writer.write("        " + str + "\n");
        }

        for (var param : function.getParameters()) {
            writer.write(String.format("        %s: %s %s\n",
                                       convertParameterName(param.getBaseName()),
                                       PythonCommon.getExposedDataTypeString(param.getDataDescriptor()),
                                       param.isOptional() ? " (optional - can be None)" : ""));
            for (var str : translate(param.getDescription())) {
                writer.write("            " + str + "\n");
            }
        }

        if (function.isDeprecated()) {
            writer.write("        Deprecated:\n");
            for (var str : translate(function.getDeprecatedMessage())) {
                writer.write("        " + str + "\n");
            }
        }

        writer.write("        \"\"\"\n");
    }

    private void writeFunctionEntryTrace(
        final BufferedWriter writer,
        final Function function,
        final String functionName
    ) throws IOException {
        var fmtStrings = new LinkedList<String>();
        var params = new StringBuilder();
        for (var param : function.getParameters()) {
            fmtStrings.add("%s");
            params.append(", ").append("str(").append(convertParameterName(param)).append(")");
        }

        writer.write(String.format("        self.logger.debug('entering %s(%s)'%s)\n",
                                   functionName,
                                   String.join(", ", fmtStrings),
                                   params));
    }

    private void writeFunctionPathCode(
        final BufferedWriter writer,
        final Function function,
        final Map<String, String> translatedParams
    ) throws IOException {
        var pc = getPathComponents(function);

        writer.write("            path = '" + pc._partialPath + "'\n");
        for (var param : pc._pathParameters) {
            var paramName = convertParameterName(param);
            paramName = translatedParams.getOrDefault(paramName, paramName);
            if (param.isOptional()) {
                writer.write("            path += '/'\n");
                writer.write("            if " + paramName + " is not None:\n");
                writer.write("                path += str(" + paramName + ")\n");
            } else {
                writer.write("            path += '/' + str(" + paramName + ")\n");
            }
        }

        for (var pp : pc._pathPresets) {
            writer.write("            path += " + PRESET_EXPRESSIONS.get(pp) + "\n");
        }

        if (pc._queryParameters.size() + function.getQueryPresets().size() > 0) {
            writer.write("            param_list = []\n");
            for (var param : pc._queryParameters) {
                var paramName = convertParameterName(param.getBaseName());
                var queryTag = param.getQueryTag();
                var indent = "            ";
                if (param.isOptional()) {
                    writer.write(indent + "if " + paramName + " is not None:\n");
                    indent += "    ";
                }
                writer.write(indent + "param_list.append('" + queryTag + "=' + str(" + paramName + "))\n");
            }

            for (var entry : function.getQueryPresets().entrySet()) {
                var queryTag = entry.getKey();
                var presetExpr = PRESET_EXPRESSIONS.get(entry.getValue());
                writer.write("            param_list.append('" + queryTag + "=' + str(" + presetExpr + "))\n");
            }
            writer.write("            path += '?' + '&'.join(param_list)\n");
        }
    }

    private void writeFunctionCatch(
        final BufferedWriter writer,
        final String functionName
    ) throws IOException {
        writer.write("        except exceptions.LiqidError as ex:\n");
        writer.write("            self.logger.debug('" + functionName + " raising ' + str(ex))\n");
        writer.write("            raise ex\n");
        writer.write("        except Exception as ex:\n");
        writer.write("            self.logger.debug('" + functionName + " raising ' + str(ex))\n");
        writer.write("            raise exceptions.LiqidError('" + functionName + " caught ' + str(ex))\n");
    }

    private void writeFunctionReturn(
        final BufferedWriter writer,
        final Function function,
        final String functionName
    ) throws IOException {
        if (function.hasResult()) {
            var resultDesc = function.getResultDataDescriptor();
            if (function.getResultDataDescriptor().getDataTypeId() == DataTypeId.OCTET_STREAM) {
                //	if the result is an octet stream (actually, a pointer thereto)...
                writer.write("            self.logger.debug('" + functionName + " returning %s', str(resp_body.encode()))\n");
                writer.write("            return resp_body\n");
            } else {
                writer.write("            self.logger.debug('<===%s', resp_body)\n");
                writer.write("            wrapper = json.loads(resp_body)\n");
                var allowEmptyStr = resultDesc.isArray() ? "True" : "False";
                writer.write("            super().check_wrapper(wrapper, " + allowEmptyStr + ")\n");

                var indexStr = "";
                if (!resultDesc.isArray() && (resultDesc.getDataTypeId() != DataTypeId.OCTET_STREAM)) {
                    indexStr = "[0]";
                }

                writer.write("            data = wrapper['response']['data']\n");
                if (resultDesc instanceof StructDataDescriptor sdd) {
                    var sddDesc = Catalog.getStruct(sdd.getStructId());
                    var className = convertStructName(sddDesc);
                    if (resultDesc.isArray()) {
                        writer.write("            result = []\n");
                        writer.write("            for item in data:\n");
                        writer.write("                if item is not None:\n");
                        writer.write("                    result.append(" + className + "().from_dictionary(item))\n");
                        writer.write("            self.logger.debug('"
                                         + functionName
                                         + " returning %s', self._list_str(result))\n");
                        writer.write("            return result\n");
                    } else {
                        writer.write("            result = " + className + "().from_dictionary(data[0])\n");
                        writer.write("            self.logger.debug('"
                                         + functionName
                                         + " returning %s', str(result))\n");
                        writer.write("            return result\n");
                    }
                } else if (resultDesc instanceof TypedefDataDescriptor tdd ) {
                    var tddDesc = Catalog.getTypedef(tdd.getTypedefId());
                    var className = convertTypedefName(tddDesc);
                    if (resultDesc.isArray()) {
                        writer.write("            result = []\n");
                        writer.write("            for item in data:\n");
                        writer.write("                result.append(" + className + "().from_dictionary(item))\n");
                        writer.write("            self.logger.debug('"
                                         + functionName
                                         + " returning %s', self._list_str(result))\n");
                        writer.write("            return result\n");
                    } else {
                        writer.write("            result = " + className + "().from_dictionary(data[0])\n");
                        writer.write("            self.logger.debug('"
                                         + functionName
                                         + " returning %s', str(result))\n");
                        writer.write("            return result\n");
                    }
                } else {
                    writer.write("            self.logger.debug('"
                                     + functionName
                                     + " returning %s', str(data" + indexStr + "))\n");
                    var result = "data" + indexStr;
                    if (resultDesc.getDataTypeId() == DataTypeId.INTRINSIC) {
                        var idd = (IntrinsicDataDescriptor) resultDesc;
                        switch (idd.getIntrinsicTypeId()) {
                            case INT16_AS_STRING,
                                INT24_AS_STRING,
                                INT32_AS_STRING,
                                INT64_AS_STRING:
                                result = String.format("int(%s[2:], 16)", result);
                            case INT32_AS_DECIMAL_STRING:
                                result = String.format("int(%s)", result);
                        }
                    }
                    writer.write(String.format("            return %s\n", result));
                }
            }
        } else {
            writer.write("            self.logger.debug('" + functionName + " returning')\n");
        }
    }

    private void writeFunctionSignature(
        final BufferedWriter writer,
        final Function function,
        final String functionName
    ) throws IOException {
        var params = new LinkedList<String>();
        params.add("self");
        for (var param : function.getParameters()) {
            params.add(convertParameterName(param));
        }

        var retStr = "";
        if (function.hasResult()) {
            var retType = PythonCommon.getExposedDataTypeString(function.getResultDataDescriptor());
            retStr = " -> " + retType;
        }

        var code = String.format("    def %s(%s)%s:",
                                 functionName,
                                 String.join(", ", params),
                                 retStr);
        writer.write(code + "\n");
    }

    //  ------------------------------------------------------------------------
    //  Class writers
    //  We write members, initializers, JSON serializers/deserializers,
    //  and custom string converters.
    //  Structs and Typedefs are both implemented as classes.
    //  ------------------------------------------------------------------------

    private void writeClasses() throws IOException {
        var fileName = "classes.py";
        var fullName = PYTHON_CODE_BASE_DIRECTORY + "/" + fileName;
        System.out.println("Writing " + fullName + "...");
        var w = new BufferedWriter(new FileWriter(fullName));
        writeFileHeader(w, fileName);

        var structs = Catalog.getStructsFor(LanguageId.PYTHON);
        var typedefs = Catalog.getTypedefsFor(LanguageId.PYTHON);

        w.write("\n");
        w.write("from liqidsdk.constants import *\n");

        var sorter = new Struct.DependencySorter(structs);
        var sortedStructs = sorter.sort();
        for (var struct : sortedStructs) {
            if (!struct.isHardCoded() && struct.getStructId() != StructId.LIQID_CLIENT) {
                writeStructClass(w, struct);
            }
        }

        for (var typedef : Typedef.sort(typedefs)) {
            writeTypedefClass(w, (Typedef) typedef);
        }

        w.close();
    }

    private void writeClassDescription(
        final BufferedWriter writer,
        final Description description
    ) throws IOException {
        writer.write("    \"\"\"\n");
        for (var s : translate(description)) {
            writer.write("    " + s + "\n");
        }
        writer.write("    \"\"\"\n");
    }

    private void writeStructClass(
        final BufferedWriter writer,
        final Struct struct
    ) throws IOException {
        var className = convertStructName(struct.getBaseName());

        writer.write("\n\n");
        var sb = new StringBuilder();
        sb.append("class ").append(className);
        if (struct.hasBaseStruct()) {
            sb.append("(").append(convertStructName(struct.getBaseStruct())).append(")");
        }
        sb.append(":\n");
        writer.write(sb.toString());

        var baseMembers = struct.getBaseDataMembersFor(LanguageId.PYTHON);
        var localMembers = struct.getDataMembersFor(LanguageId.PYTHON);

        writeClassDescription(writer, struct.getDescription());
        writeStructClassInitializer(writer, struct, localMembers);
        writeStructClassStr(writer, localMembers, baseMembers);
        writeStructClassAccessors(writer, localMembers);
        if (struct.isSerializable()) {
            writeStructClassDeserializer(writer, struct, localMembers);
            writeStructClassSerializer(writer, struct, localMembers);
        }
    }

    private void writeStructClassAccessors(
        final BufferedWriter writer,
        final Collection<StructDataMember> localMembers
    ) throws IOException {
        for (var member : localMembers) {
            writer.write("\n");

            var exposedType = "";
            var iasDigits = 0;
            var forceType = false;
            IntrinsicDataDescriptor idd = null;
            if (member.getDataDescriptor().isArray()) {
                exposedType = "list";
            } else if (member.getDataDescriptor().getDataTypeId() == DataTypeId.INTRINSIC) {
                idd = (IntrinsicDataDescriptor) member.getDataDescriptor();
                var iType = idd.getIntrinsicTypeId();
                if (iType == IntrinsicTypeId.STRING_ARRAY) {
                    exposedType = "list";
                } else if (iType == IntrinsicTypeId.STRING_ARRAY_MAP) {
                    exposedType = "dict";
                } else if (iType == IntrinsicTypeId.INT16_AS_STRING) {
                    exposedType = "int";
                    iasDigits = 4;
                    forceType = true;
                } else if (iType == IntrinsicTypeId.INT24_AS_STRING) {
                    exposedType = "int";
                    iasDigits = 6;
                    forceType = true;
                } else if (iType == IntrinsicTypeId.INT32_AS_STRING) {
                    exposedType = "int";
                    iasDigits = 8;
                    forceType = true;
                } else if (iType == IntrinsicTypeId.INT64_AS_STRING) {
                    exposedType = "int";
                    iasDigits = 16;
                    forceType = true;
                } else if (iType == IntrinsicTypeId.INT32_AS_DECIMAL_STRING) {
                    exposedType = "int";
                    forceType = true;
                } else {
                    exposedType = PythonCommon.getTypeString(member.getDataDescriptor());
                }
            } else {
                exposedType = PythonCommon.getTypeString(member.getDataDescriptor());
            }

            // getters
            var dataName = convertStructDataMemberName(member.getBaseName());
            var getterName = toGetterName(member.getBaseName());
            writer.write(String.format("    def %s(self) -> %s:\n", getterName, exposedType));

            if (idd != null) {
                for (var t : idd.getTranslations()) {
                    writer.write(String.format("        if self.%s == %s:\n", dataName, t._apiConstant));
                    writer.write(String.format("            return %s\n", t._sdkConstant));
                }
            }

            var value = "self." + dataName;
            if (forceType) {
                if (iasDigits > 0) {
                    value = String.format("int(%s[2:], 16)", value);
                } else {
                    value = String.format("int(%s, 10)", value);
                }
            }
            writer.write(String.format("        return %s\n", value));

            // setters
            writer.write("\n");
            var setterName = toSetterName(member.getBaseName());
            writer.write(String.format("    def %s(self, value: %s):\n", setterName, exposedType));
            value = "value";
            if (forceType) {
                if (iasDigits > 0) {
                    value = String.format("'0x{:0%dx}'.format(int(value))", iasDigits);
                } else {
                    value = "str(value)";
                }
            }

            if ((idd != null) && (idd.hasTranslations())) {
                var if_elif = "if";
                for (var t : idd.getTranslations()) {
                    writer.write(String.format("        %s value == %s:\n", if_elif, t._sdkConstant));
                    writer.write(String.format("            self.%s = %s\n", dataName, t._apiConstant));
                    if_elif = "elif";
                }
                writer.write("        else:\n");
                writer.write(String.format("            self.%s = %s\n", dataName, value));
            } else {
                writer.write(String.format("        self.%s = %s\n", dataName, value));
            }
        }
    }

    private void writeStructClassDeserializer(
        final BufferedWriter writer,
        final Struct struct,
        final Collection<StructDataMember> localMembers
    ) throws IOException {
        writer.write("\n");
        writer.write("    def from_dictionary(self, source):\n");
        writer.write("        \"\"\"\n");
        writer.write("        Populates an object from the given dictionary\n");
        writer.write("        :param source: Dictionary containing a representation of a JSON object\n");
        writer.write("        :return: This object after being populated from the source dictionary\n");
        writer.write("        \"\"\"\n");
        if (struct.hasBaseStruct()) {
            writer.write("        super().from_dictionary(source)\n");
        }

        for (var m : localMembers) {
            var name = convertStructDataMemberName(m);
            var selfName = "self." + name;
            var jsonTag = m.getJsonTag();
            var sourceStr = "source['" + jsonTag + "']";
            var singleEntitySourceStr = sourceStr;
            var arrayEntitySourceStr = sourceStr;
            var dd = m.getDataDescriptor();
            if (dd instanceof StructDataDescriptor sdd) {
                var subStruct = Catalog.getStruct(sdd.getStructId());
                var subClassName = convertStructName(subStruct);
                singleEntitySourceStr = subClassName + "().from_dictionary(" + sourceStr + ")";
                arrayEntitySourceStr = subClassName + "().from_dictionary(item)";
            } else if (dd instanceof TypedefDataDescriptor tdd) {
                var subTypedef = Catalog.getTypedef(tdd.getTypedefId());
                var subClassName = convertTypedefName(subTypedef);
                singleEntitySourceStr = subClassName + "().from_dictionary(" + sourceStr + ")";
                arrayEntitySourceStr = subClassName + "().from_dictionary(item)";
            }

            if (dd.isArray()) {
                //  An array of discrete or struct entities - either way, arrayEntitySourceStr has us covered.
                //  Note that we make no provision for an array of arrays.
                //  We currently have no requirement to do so, but if we end up in that
                //  dumpster fire, this is where we need to look at some additional code.
                writer.write("        " + selfName + " = []\n");
                writer.write("        if '" + jsonTag + "' in source and source['" + jsonTag + "'] is not None:\n");
                writer.write("            for item in source['" + jsonTag + "']:\n");
                writer.write("                " + selfName + ".append(" + arrayEntitySourceStr + ")\n");
            } else if (dd instanceof StructDataDescriptor || dd instanceof TypedefDataDescriptor) {
                //  a singular item of a struct
                writer.write("        if '" + jsonTag + "' in source and source['" + jsonTag + "'] is not None:\n");
                writer.write("            " + selfName + " = " + singleEntitySourceStr + "\n");
            } else {
                //  A singular discrete entity
                writer.write("        if '" + jsonTag + "' in source:\n");
                writer.write("            " + selfName + " = " + singleEntitySourceStr + "\n");
            }
        }

        writer.write("        return self\n");
    }

    private void writeStructClassInitializer(
        final BufferedWriter writer,
        final Struct struct,
        final Collection<StructDataMember> localMembers
    ) throws IOException {
        if (!struct.getDataMembersFor(LanguageId.PYTHON).isEmpty()) {
            writer.write("\n");
            writer.write("    def __init__(self):\n");
            if (struct.hasBaseStruct()) {
                writer.write("        super().__init__()\n");
            }

            for (var m : localMembers) {
                var name = convertStructDataMemberName(m);
                var desc = m.getDataDescriptor();
                if (desc.isArray()) {
                    writer.write("        self." + name + " = []\n");
                } else if (m.hasDefaultValue()) {
                    var exp = getDefaultValueString(m);
                    writer.write("        self." + name + " = " + exp + "\n");
                } else if (m.getDataDescriptor() instanceof StructDataDescriptor sdd) {
                    var base = Catalog.getStruct(sdd.getStructId());
                    writer.write("        self." + name + " = " + convertStructName(base) + "()\n");
                } else if (m.getDataDescriptor() instanceof TypedefDataDescriptor tdd) {
                    var base = Catalog.getTypedef(tdd.getTypedefId());
                    writer.write("        self." + name + " = " + convertTypedefName(base) + "()\n");
                } else {
                    writer.write(String.format("        self.%s = None\n", name));
                }
            }
        }
    }

    private void writeStructClassSerializer(
        final BufferedWriter writer,
        final Struct struct,
        final Collection<StructDataMember> localMembers
    ) throws IOException {
        writer.write("\n");
        writer.write("    def to_dictionary(self):\n");
        writer.write("        \"\"\"\n");
        writer.write("        Creates a dictionary and populates it from this object in a manner\n");
        writer.write("        consistent with JSON conventions\n");
        writer.write("        :return: dictionary object\n");
        writer.write("        \"\"\"\n");

        if (struct.hasBaseStruct()) {
            writer.write("        result = super().to_dictionary()\n");
        } else {
            writer.write("        result = dict()\n");
        }

        for (var m : localMembers) {
            var jsonTag = m.getJsonTag();
            var destStr = "result['" + jsonTag + "']";
            var selfSourceStr = "self." + convertStructDataMemberName(m);
            var dd = m.getDataDescriptor();

            if (dd.isArray()) {
                writer.write("        container = []\n");
                writer.write("        for item in " + selfSourceStr + ":\n");
                var subSourceStr = "item";
                if (dd instanceof StructDataDescriptor || dd instanceof TypedefDataDescriptor) {
                    subSourceStr += ".to_dictionary()";
                }
                writer.write("            container.append(" + subSourceStr + ")\n");
                writer.write("        " + destStr + " = container\n");
            } else {
                if (dd instanceof StructDataDescriptor || dd instanceof TypedefDataDescriptor) {
                    selfSourceStr += ".to_dictionary()";
                }
                writer.write("        " + destStr + " = " + selfSourceStr + "\n");
            }
        }

        writer.write("        return result\n");
    }

    private void writeStructClassStr(
        final BufferedWriter writer,
        final Collection<StructDataMember> localMembers,
        final Collection<StructDataMember> baseMembers
    ) throws IOException {
        writer.write("\n");
        writer.write("    def __str__(self):\n");
        writer.write("        s = '{'\n");

        var allMembers = new LinkedList<>(localMembers);
        allMembers.addAll(baseMembers);
        var first = true;
        for (var m : allMembers) {
            var name = convertStructDataMemberName(m);
            var sb = new StringBuilder();
            sb.append("        s += ");
            if (!first) {
                sb.append("', ' + ");
            }
            sb.append("'").append(name).append(": ' + str(self.").append(name).append(")\n");
            writer.write(sb.toString());
            first = false;
        }

        writer.write("        s += '}'\n");
        writer.write("        return s\n");

        writer.write("\n");
        writer.write("    def __repr__(self):\n");
        writer.write("        return self.__str__()\n");
    }

    private void writeTypedefClass(
        final BufferedWriter writer,
        final Typedef typedef
    ) throws IOException {
        var className = convertTypedefName(typedef.getBaseName());

        writer.write("\n\n");
        writer.write("class " + className + ":\n");


        writeClassDescription(writer, typedef.getDescription());
        writeTypedefClassInitializer(writer, typedef);
        writeTypedefClassStr(writer);
        writeTypedefClassDeserializer(writer, typedef);
        writeTypedefClassSerializer(writer, typedef);
    }

    private void writeTypedefClassDeserializer(
        final BufferedWriter writer,
        final Typedef typedef
    ) throws IOException {
        writer.write("\n");
        writer.write("    def from_dictionary(self, source):\n");
        writer.write("        \"\"\"\n");
        writer.write("        Populates this object from the given dictionary\n");
        writer.write("        :param source: Dictionary containing a representation of a JSON object\n");
        writer.write("        :return: This object after being populated from the source dictionary\n");
        writer.write("        \"\"\"\n");

        //  We implement a typedef as a class which encapsulates an instance of the typedef'd object
        //  (or an array thereof).
        var typeName = PythonCommon.getDataTypeString(typedef.getSourceDataType());
        if (typedef.getSourceDataType().isArray()) {
            writer.write("        content = list()\n");
            writer.write("        for item in source:\n");
            writer.write("            if item is not None:\n");
            writer.write("                content.append(" + typeName + "().from_dictionary(item))\n");
        } else {
            writer.write("        content.append(" + typeName + "().from_dictionary(item))\n");
        }
        writer.write("        return self\n");
    }

    private void writeTypedefClassInitializer(
        final BufferedWriter writer,
        final Typedef typedef
    ) throws IOException {
        writer.write("\n");
        writer.write("    def __init__(self):\n");
        var dt = typedef.getSourceDataType();
        if (dt.isArray()) {
            writer.write("        self.content = []\n");
        } else if (dt instanceof StructDataDescriptor sdd) {
            var base = Catalog.getStruct(sdd.getStructId());
            writer.write("        self.content = " + convertStructName(base) + "()\n");
        } else if (dt instanceof TypedefDataDescriptor tdd) {
            var base = Catalog.getTypedef(tdd.getTypedefId());
            writer.write("        self.content = " + convertTypedefName(base) + "()\n");
        } else {
            writer.write("        self.content = None\n");
        }
    }

    private void writeTypedefClassSerializer(
        final BufferedWriter writer,
        final Typedef typedef
    ) throws IOException {
        writer.write("\n");
        writer.write("    def to_dictionary(self):\n");
        writer.write("        \"\"\"\n");
        writer.write("        Creates a dictionary and populates it from this object in a manner\n");
        writer.write("        consistent with JSON conventions\n");
        writer.write("        :return: dictionary object\n");
        writer.write("        \"\"\"\n");

        //  We implement a typedef as a class which encapsulates an instance of the typedef'd object
        //  (or an array thereof).
        if (typedef.getSourceDataType().isArray()) {
            writer.write("        result = []\n");
            writer.write("        for entity in self.content:\n");
            writer.write("            list.append(result, entity.to_dictionary())\n");
            writer.write("        return result\n");
        } else {
            writer.write("        return self.content.to_dictionary()\n");
        }
    }

    private void writeTypedefClassStr(
        final BufferedWriter writer
    ) throws IOException {
        writer.write("\n");
        writer.write("    def __str__(self):\n");
        writer.write("        return '{' + str(self.content) + '}'\n");
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
                    return convertEnumComponentName(enumerator, comp);
                }
            }
        }

        throw new RuntimeException("Default value can only be set for intrinsic or enum data descriptors");
    }

    //  writes the first few lines of every .python file
    private void writeFileHeader(
        final BufferedWriter writer,
        final String fileName
    ) throws IOException {
        writer.write(String.format("# File: %s\n", fileName));
        writer.write("#\n");
        for (var s : COPYRIGHT_TEXT) {
            writer.write(String.format("# %s\n", s));
        }
        writer.write("#\n");
        writer.write("# Liqid SDK - Version " + Main.VERSION + "\n");
        writer.write("# This file was automatically generated - do not modify it directly.\n");
        writer.write("#\n");
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
                var translatedName = paramName + "_translated";
                writer.write(String.format("            %s = '0x{:0%dx}'.format(%s)\n",
                                           translatedName,
                                           hexDigits,
                                           paramName));
                translatedParams.put(paramName, translatedName);
            }
        }

        return translatedParams;
    }

    private void writeParameterTypeChecks(
        final BufferedWriter writer,
        final Function function
    ) throws IOException {
        for (var param : function.getParameters()) {
            var paramName = convertParameterName(param.getBaseName());
            var typeStr = PythonCommon.getExposedDataTypeString(param.getDataDescriptor());
            writer.write(String.format("        self.check_parameter_type('%s', %s, '%s', %s)\n",
                                       paramName, paramName, typeStr, param.isOptional() ? "True" : "False"));
        }
    }
}
