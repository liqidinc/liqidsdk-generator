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
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;

/**
 *  Describes a regular SDK function or method (depending upon the language).
 *  Parameters appear in the parameter list for the SDK function call in the order in which they are specified,
 *  and consist of the following types:
 *      Path Parameters (0 or more) identify a particular endpoint for the operation. The parameter values are
 *          concatenated to the partial path in the order in which they are specified in the function description.
 *          These may be specified for any of the four rest methods.
 *          Generally, these are non-optional parameters; however, we don't impose that requirement in code.
 *      Query Parameters (0 or more) are composed into the query portion of the URI following the path parameters.
 *          These parameters should only be specified for GET methods. Any of them can be optional
 *      Body Parameters (0 or 1) represent data structures which we json-serialize into a string which is presented
 *          as the request body. These (this?) should only be specified for POST and PUT methods.
 */
public class RegularFunction extends Function {

    /**
     * Do not use the constructor - use the builder
     */
    private RegularFunction(FunctionId functionId,
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
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class RegularFunction";

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
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setFunctionId(FunctionId value) { _functionId = value; return this; }
        public Builder setIsAsynchronous(boolean value) { _isAsynchronous = value; return this; }
        public Builder setPartialPath(String value) { _partialPath = value; return this; }
        public Builder setRestMethod(HttpMethod value) { _restMethod = value; return this; }
        public Builder setResultDataDescriptor(DataDescriptor value) { _resultDataDescriptor = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public Function build() {
            if (_functionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setFunctionId"));
            }

            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            if (_resultDataDescriptor != null && _resultDescription.isEmpty()) {
                throw new RuntimeException("Function result was specified, but no description was provided");
            }

            if (_partialPath == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setPartialPath"));
            }

            if (_restMethod == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setRestMethod"));
            }

            var counters = countParameters(_parameters);
            if (counters._bodyParameterCount > 1) {
                throw new RuntimeException("Too many BodyParameter specifications");
            }
            if (counters._wiredParameterCount > 0) {
                throw new RuntimeException("WiredParameter specifications are not allowed in a RegularFunction");
            }

            return new RegularFunction(_functionId,
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
                                       _queryPresets);
        }
    }
}
