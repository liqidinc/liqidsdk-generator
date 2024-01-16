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
import com.liqid.sdk.generator.discreteDataModels.VoidDataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;

/**
 * Describes an SDK function or method (depending upon the language) which needs to be referenced within the
 * generator system and made available to SDK users, but which is, for some particular reason, hard-coded rather than generated.
 */
public class HardCodedFunction extends Function {

    /**
     * Do not use the constructor - use the builder
     */
    private HardCodedFunction(FunctionId functionId,
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

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class HardCodedFunction";

        private String _baseName = null;
        private final List<String> _deprecatedMessage = new LinkedList<>();
        private final List<String> _description = new LinkedList<>();
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

            if (_resultDataDescriptor != null
                && !(_resultDataDescriptor instanceof VoidDataDescriptor)
                && _resultDescription.isEmpty()) {
                throw new RuntimeException("Function result was specified, but no description was provided");
            }

            var counters = countParameters(_parameters);
            if (counters._bodyParameterCount > 1) {
                throw new RuntimeException("Too many BodyParameter specifications");
            }
            if (counters._wiredParameterCount > 0) {
                throw new RuntimeException("WiredParameter specifications are not allowed in a HardCodedFunction");
            }

            return new HardCodedFunction(_functionId,
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
