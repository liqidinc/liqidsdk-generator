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

public class FilterFunction extends Function {

    /**
     * The bulk getter function which we invoke to obtain a list of entities which we filter
     */
    private final FunctionId _baseFunctionId;

    /**
     * Base name of the member(s) in the entity returned by the base function, upon which we filter
     */
    private final Collection<String> _memberBaseNames = new LinkedList<>();

    protected FilterFunction(FunctionId functionId,
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
                             FunctionId baseFunctionId,
                             Collection<String> memberBaseNames) {
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

        _baseFunctionId = baseFunctionId;
        _memberBaseNames.addAll(memberBaseNames);
    }

    public Function getBaseFunction() {
        return Catalog.getFunction(_baseFunctionId);
    }

    public Collection<String> getMemberBaseNames() { return new LinkedList<>(_memberBaseNames); }

    @Override
    public ImportRequirements getImportRequirements() { return getBaseFunction().getImportRequirements(); }

    @Override
    public LanguageId getSpecificLanguageId() { return getBaseFunction().getSpecificLanguageId(); }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class FilterFunction";

        private FunctionId _baseFunctionId = null;
        private String _baseName = null;
        private final List<String> _description = new LinkedList<>();
        private final List<String> _deprecatedMessage = new LinkedList<>();
        private FunctionId _functionId = null;
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isAsynchronous = false;
        private final List<String> _memberBaseNames = new LinkedList<>();
        private final List<Parameter> _parameters = new LinkedList<>();
        private final List<PresetId> _pathPresets = new LinkedList<>();
        private final Map<String, PresetId> _queryPresets = new LinkedHashMap<>();
        private DataDescriptor _resultDataDescriptor = null;
        private final List<String> _resultDescription = new LinkedList<>();
        private LanguageId _specificLanguageId = null;

        public Builder addDeprecatedMessage(String value) { _deprecatedMessage.add(value); return this; }
        public Builder addDescription(String value) { _description.add(value); return this; }
        public Builder addMemberBaseName(String value) { _memberBaseNames.add(value); return this; }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder addParameter(Parameter value) { _parameters.add(value); return this; }
        public Builder addPathPreset(PresetId value) { _pathPresets.add(value); return this; }
        public Builder addQueryPreset(String queryTag, PresetId value) { _queryPresets.put(queryTag, value); return this; }
        public Builder addResultDescription(String value) { _resultDescription.add(value); return this; }

        public Builder setBaseFunctionId(FunctionId value) { _baseFunctionId = value; return this; }
        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setFunctionId(FunctionId value) { _functionId = value; return this; }
        public Builder setIsAsynchronous(boolean value) { _isAsynchronous = value; return this; }
        public Builder setResultDataDescriptor(DataDescriptor value) { _resultDataDescriptor = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public Function build() {
            if (_functionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setFunctionId"));
            }

            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_baseFunctionId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseFunction"));
            }

            if (_memberBaseNames.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addMemberBaseName"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            if (_resultDataDescriptor == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setResultDataDescriptor"));
            }

            if (_resultDescription.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addResultDescription"));
            }

            return new FilterFunction(_functionId,
                                      _baseName,
                                      _isAsynchronous,
                                      new Description(_description),
                                      new Description(_deprecatedMessage),
                                      _importRequirements,
                                      _specificLanguageId,
                                      HttpMethod.GET,
                                      "",
                                      _resultDataDescriptor,
                                      new Description(_resultDescription),
                                      _parameters,
                                      _pathPresets,
                                      _queryPresets,
                                      _baseFunctionId,
                                      _memberBaseNames);
        }
    }
}
