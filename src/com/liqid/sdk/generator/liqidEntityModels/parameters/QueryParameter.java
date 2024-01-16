//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.parameters;

import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.LinkedList;
import java.util.List;

/**
 * Describes an SDK parameter which is used to populate the query portion of the REST URI.
 * This is usually (but not necessarily always) provided for GET requests.
 */
public class QueryParameter extends Parameter {

    private final String _queryTag;

    private QueryParameter(String baseName,
                           Description description,
                           ImportRequirements importRequirements,
                           LanguageId specificLanguageId,
                           DataDescriptor dataDescriptor,
                           boolean isOptional,
                           String queryTag) {
        super(baseName, description, importRequirements, specificLanguageId, dataDescriptor, isOptional);
        _queryTag = queryTag;
    }

    public String getQueryTag() { return _queryTag; }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class QueryParameter";

        private String _baseName = null;
        private DataDescriptor _dataDescriptor = null;
        private final List<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isOptional = false;
        private String _queryTag = null;
        private LanguageId _specificLanguageId = null;

        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setDataDescriptor(DataDescriptor value) { _dataDescriptor = value; return this; }
        public Builder setIsOptional(boolean value) { _isOptional = value; return this; }
        public Builder setQueryTag(String value) { _queryTag = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public QueryParameter build() {
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_dataDescriptor == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setDataDescriptor"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            if (_queryTag == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setQueryTag"));
            }

            return new QueryParameter(_baseName,
                                      new Description(_description),
                                      _importRequirements,
                                      _specificLanguageId,
                                      _dataDescriptor,
                                      _isOptional,
                                      _queryTag);
        }
    }
}
