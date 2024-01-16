//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.functions;

import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.FunctionId;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.liqidEntityModels.parameters.*;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;

public abstract class Function extends LiqidEntity {

    /**
     * If this function is deprecated, a message indicating the reasoning for the deprecation and/or
     * instructions for alternates will be provided by these messages.
     * Ignored if _isDeprecated is false.
     */
    private final Description _deprecatedMessage;

    /**
     * Uniquely identifies this function
     */
    private final FunctionId _functionId;

    /**
     * This function can return 202 indicating that the requested operation has been queued
     * or started, but not necessarily completed.
     */
    private final boolean _isAsynchronous;

    /**
     * A list of Parameter objects which describe the parameters to be generated for the function.
     * Various implementors of this base class will likely have restrictions as to the type and number of
     * Parameter objects which are allowed for the particular functions.
     */
    private final Collection<Parameter> _parameters = new LinkedList<>();

    /**
     * Indicates the portion of the URI which follows any host information and API pathing,
     * and which precedes any query parameters.
     * E.g., in the URI https://www.pho79.com/foodapi/v1/pho?include=beef the partial path would be "pho",
     * presuming the base path for the entire API is /foodapi/v1.
     */
    private final String _partialPath;

    /**
     * A list of zero or more PresetId values which describe actual values to be pasted into the URI
     * following any specified path parameters. One use for these might be for specifying rack, shelf, and node
     * using the current default coordinates which the SDK client is not expected to have to care about.
     * The order of appearance in this array dictates the order in which the values are presented in the path.
     */
    private final Collection<PresetId> _pathPresets = new LinkedList<>();

    /**
     * A list of zero or more PresetId values which describe actual values to be pasted into the query section
     * of the URI. One use for these might be for specifying rack, shelf, and node using the current default
     * coordinates which the SDK client is not expected to have to care about.
     * The key value is the query tag; the value is the preset ID
     */
    private final Map<String, PresetId> _queryPresets = new LinkedHashMap<>();

    /**
     * Indicates the REST method to be employed by this function.
     * This can be any of DELETE, GET, POST, or PUT.
     */
    private final HttpMethod _restMethod;

    /**
     *  Indicates the data type of the result for this function.
     */
    private final DataDescriptor _resultDataDescriptor;

    /**
     * Contains commentary which describes the value(s) returned by this function.
     * It serves only as a source for the language writer to use when constructing function commentary.
     */
    private final Description _resultDescription;

    protected Function(FunctionId functionId,
                       String baseName,
                       boolean isAsynchronous,
                       Description description,
                       Description deprecatedMessage,
                       ImportRequirements importRequirements,
                       LanguageId specificLanguageId,
                       HttpMethod restMethod,
                       String partialPath,
                       DataDescriptor resultDataDescriptor,
                       Description resultDescription,
                       Collection<Parameter> parameters,
                       Collection<PresetId> pathPresets,
                       Map<String, PresetId> queryPresets) {
        super(baseName, description, importRequirements, specificLanguageId);
        _deprecatedMessage = deprecatedMessage;
        _isAsynchronous = isAsynchronous;
        _functionId = functionId;
        _parameters.addAll(parameters);
        _partialPath = partialPath;
        _pathPresets.addAll(pathPresets);
        _queryPresets.putAll(queryPresets);
        _restMethod = restMethod;
        _resultDataDescriptor = resultDataDescriptor == null ? new VoidDataDescriptor() : resultDataDescriptor;
        _resultDescription = resultDescription;
    }

    public final Description getDeprecatedMessage() { return _deprecatedMessage; }
    public final FunctionId getFunctionId() { return _functionId; }
    public final Collection<Parameter> getParameters() { return _parameters; }
    public String getPartialPath() { return _partialPath; }
    public Collection<PresetId> getPathPresets() { return _pathPresets; }
    public Map<String, PresetId> getQueryPresets() { return _queryPresets; }
    public HttpMethod getRestMethod() { return _restMethod; }
    public DataDescriptor getResultDataDescriptor() { return _resultDataDescriptor; }
    public final Description getResultDescription() { return _resultDescription; }
    public boolean hasParameters() { return _parameters.size() > 0; }
    public final boolean hasResult() { return _resultDataDescriptor.getDataTypeId() != DataTypeId.VOID; }
    public final boolean isAsynchronous() { return _isAsynchronous; }
    public final boolean isDeprecated() { return !_deprecatedMessage.isEmpty(); }

    protected static class ParameterCounters {
        public int _bodyParameterCount = 0;
        public int _pathParameterCount = 0;
        public int _queryParameterCount = 0;
        public int _simpleParameterCount = 0;
        public int _wiredParameterCount = 0;
    }

    protected static ParameterCounters countParameters(Collection<Parameter> parameters) {
        var result = new ParameterCounters();
        for (var p : parameters) {
            if (p instanceof BodyParameter) { result._bodyParameterCount++; }
            if (p instanceof PathParameter) { result._pathParameterCount++; }
            if (p instanceof QueryParameter) { result._queryParameterCount++; }
            if (p instanceof SimpleParameter) { result._simpleParameterCount++; }
            if (p instanceof WiredParameter) { result._wiredParameterCount++; }
        }
        return result;
    }
}
