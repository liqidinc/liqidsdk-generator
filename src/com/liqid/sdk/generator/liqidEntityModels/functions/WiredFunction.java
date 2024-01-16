//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.functions;

import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.HttpMethod;
import com.liqid.sdk.generator.discreteDataModels.PresetId;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructMemberTarget;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;

/**
 *  Describes an SDK function which maps directly to a REST API endpoint.
 *  The REST API endpoint requires some subset of a defined struct as its body parameter.
 *  We do not wish for the SDK client to have to provide this entire struct, while guessing which struct members are
 *  relevant to the particular SDK function.
 *  Thus, we describe only the relevant discrete parameters which are translated into the SDK function signature,
 *  and build them into a struct which we then pass to the REST API.
 * <p>
 * Parameters appear in the parameter list for the SDK function call in the order in which they are specified,
 * and consist of the following types:
 *      Path Parameters (0 or more) identify a particular endpoint for the operation. The parameter values are
 *          concatenated to the partial path in the order in which they are specified in the function description.
 *          These may be specified for any of the four rest methods.
 *          Generally, these are non-optional parameters; however, we don't impose that requirement in code.
 *      Wired Parameters (0 or more) represent values which are part of the SDK function signature, and which
 *      	are used to populate individual fields in a particular API struct. The parameter has a list of baseName
 *      	specifications which indicate the struct member(s) to be populated from the parameter.
 *          It should be noted that a wired parameter might map to a member of a struct which is itself a member
 *          of some higher-level struct. Thus, any particular baseName specification *might* be a qualified name.
 *          For example, a parameter wired to the node value of the location member (a Coordinates struct) of
 *          the apiParameter would contain a baseName of "Location.Node". It is incumbent upon the SDK designer
 *          to ensure that "Location" is a valid struct member baseName within the apiParameter struct,
 *          and that "Node" is a valid struct member baseName within the Coordinates struct.
 */
public class WiredFunction extends Function {

    /**
     * Indicates the struct which we are to create, populate, and pass to the REST API.
     */
    private final StructId _apiStructId;

    /**
     * A map indicating that certain members within the struct indicated by _apiStructId are to be
     * populated with presets according to the indicated PresetId.
     * The key is the baseName associated with the struct member; the value indicates which preset is to be used.
     */
    private final Map<StructMemberTarget, PresetId> _structPresets = new LinkedHashMap<>();

    /**
     * Do not use the constructor - use the builder
     */
    private WiredFunction(FunctionId functionId,
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
                          Map<String, PresetId> queryPresets,
                          StructId apiStructId,
                          Map<StructMemberTarget, PresetId> structPresets) {
        super(functionId,
              baseName,
              isAsynchronous,
              description,
              deprecatedMessage,
              importRequirements,
              specificLanguageId,
              restMethod,
              partialPath,
              resultDataDescriptor,
              resultDescription,
              parameters,
              pathPresets,
              queryPresets);

        _apiStructId = apiStructId;
        _structPresets.putAll(structPresets);
    }

    public StructId getApiStructId() { return _apiStructId; }
    public Map<StructMemberTarget, PresetId> getStructPresets() { return _structPresets; }

    public boolean hasApiStructId() { return _apiStructId != null; }
    public boolean hasStructPresets() { return !_structPresets.isEmpty(); }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class WiredFunction for %s";

        private StructId _apiStructId = null;
        private String _baseName = null;
        private final List<String> _description = new LinkedList<>();
        private final List<String> _deprecatedMessage = new LinkedList<>();
        private FunctionId _functionId = null;
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isAsynchronous = false;
        private final List<Parameter> _parameters = new LinkedList<>();
        private String _partialPath = null;
        private final List<PresetId> _pathPresets = new LinkedList<>();
        private final Map<String, PresetId> _queryPresets = new LinkedHashMap<>();
        private HttpMethod _restMethod = null;
        private DataDescriptor _resultDataDescriptor = null;
        private final List<String> _resultDescription = new LinkedList<>();
        private final Map<StructMemberTarget, PresetId> _structPresets = new LinkedHashMap<>();
        private LanguageId _specificLanguageId = null;

        public Builder addDeprecatedMessage(String value) { _deprecatedMessage.add(value); return this; }
        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder addParameter(Parameter value) { _parameters.add(value); return this; }
        public Builder addPathPreset(PresetId value) { _pathPresets.add(value); return this; }
        public Builder addQueryPreset(String queryTag, PresetId value) { _queryPresets.put(queryTag, value); return this; }
        public Builder addResultDescription(String value) { _resultDescription.add(value); return this; }
        public Builder addStructPresetId(StructMemberTarget target, PresetId presetId) { _structPresets.put(target, presetId); return this; }
        public Builder setApiStructId(StructId value) { _apiStructId = value; return this; }
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setFunctionId(FunctionId value) { _functionId = value; return this; }
        public Builder setIsAsynchronous(boolean value) { _isAsynchronous = value; return this; }
        public Builder setPartialPath(String value) { _partialPath = value; return this; }
        public Builder setRestMethod(HttpMethod value) { _restMethod = value; return this; }
        public Builder setResultDataDescriptor(DataDescriptor value) { _resultDataDescriptor = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public Function build() {
            if (_functionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setFunctionId", _baseName));
            }

            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName", "?"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription", _baseName));
            }

            if (_resultDataDescriptor != null && _resultDescription.isEmpty()) {
                throw new RuntimeException(String.format("Function %s returns an improperly-described result", _baseName));
            }

            if (_partialPath == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setPartialPath", _baseName));
            }

            if (_restMethod == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setRestMethod", _baseName));
            }

            var counters = countParameters(_parameters);
            if (counters._queryParameterCount > 0) {
                throw new RuntimeException("QueryParameter specifications are not allowed in WiredFunction " + _baseName);
            }
            if (counters._bodyParameterCount > 0) {
                throw new RuntimeException("BodyParameter specifications are not allowed in WiredFunction " + _baseName);
            }

            if (_apiStructId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setApiStructId", _baseName));
            }

            return new WiredFunction(_functionId,
                                     _baseName,
                                     _isAsynchronous,
                                     new Description(_description),
                                     new Description(_deprecatedMessage),
                                     _importRequirements,
                                     _specificLanguageId,
                                     _restMethod,
                                     _partialPath,
                                     _resultDataDescriptor,
                                     new Description(_resultDescription),
                                     _parameters,
                                     _pathPresets,
                                     _queryPresets,
                                     _apiStructId,
                                     _structPresets);
        }
    }
}
