//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.functions;

import com.liqid.sdk.generator.catalog.Catalog;
import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.HttpMethod;
import com.liqid.sdk.generator.discreteDataModels.PresetId;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.Parameter;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.*;

/**
 * Describes a correlation between an imperative SDK function which updates a Liqid entity, and the corresponding
 * REST API. This exists in order to simplify the use of REST APIs which require the presentation of an entire
 * entity in order to update a single attribute of said entity.
 * <p>
 * We provide an SDK function which accepts a unique object name along with whichever parameters are to be updated
 * - the generated code for the function performs a GET to retrieve the existing entity, updates the relevant
 * attributes of that entity, then performs a POST with the updated content.
 * <p>
 * Another use of this is for certain REST API calls which require a complete struct to be presented via PUT
 * even though only a few fields need to be populated - power management comes to mind. These are implemented
 * via GET by identifier, followed by a PUT.
 * <p>
 * The impetus for this mechanism lies in the mis-juxtaposition of a declarative (as opposed to imperative)
 * API with an object model which contains a large number of attributes - the declarative API does not support
 * the ability to update discrete attributes; an implementation should provide path and sub-path endpoints for
 * all independently-settable attributes for an object. But the Liqid REST API does not do that; thus we must
 * re-specify all the attributes of the entity when we wish to change only one, or a few.
 */
public class GetPostPutFunction extends Function {

    /**
     * This is a reference to the function we invoke to perform the GET operation.
     */
    private final FunctionId _getterFunctionId;

    /**
     * Do not use the constructor - use the builder
     */
    private GetPostPutFunction(FunctionId functionId,
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
                               FunctionId getterFunctionId) {
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

        _getterFunctionId = getterFunctionId;
    }

    public Function getGetterFunction() {
        return Catalog.getFunction(_getterFunctionId);
    }

    @Override
    public boolean hasParameters() {
        return Catalog.getFunction(_getterFunctionId).hasParameters();
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class GetPostPutFunction";

        private String _baseName = null;
        private final List<String> _description = new LinkedList<>();
        private final List<String> _deprecatedMessage = new LinkedList<>();
        private FunctionId _functionId = null;
        private FunctionId _getterFunctionId = null;
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
        public Builder setGetterFunctionId(FunctionId value) { _getterFunctionId = value; return this; }
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
                throw new RuntimeException("Function returns an improperly-described result");
            }

            if (_getterFunctionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setGetterFunctionId"));
            }

            if (_partialPath == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setPartialPath"));
            }

            if (_restMethod == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setRestMethod"));
            } else if (_restMethod != HttpMethod.POST && _restMethod != HttpMethod.PUT) {
                throw new RuntimeException("RestMethod must either be POST or PUT");
            }

            var counters = countParameters(_parameters);
            if (counters._bodyParameterCount > 1) {
                throw new RuntimeException("Too many BodyParameter specifications");
            }
            if (counters._wiredParameterCount > 0) {
                throw new RuntimeException("WiredParameter specifications are not allowed in a RegularFunction");
            }

            return new GetPostPutFunction(_functionId,
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
                                          _getterFunctionId);
        }
    }
}
