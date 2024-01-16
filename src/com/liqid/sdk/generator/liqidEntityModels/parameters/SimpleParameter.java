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
 * Describes an SDK parameter of a general nature which we use for internal purposes.
 * For HardCodedFunctions, it describes a parameter of that function merely for documentation purposes.
 * For FilterFunctions it describes the parameter which the client uses to request a particular entity from
 *      a potential list of entities.
 * For update functions, it represents a value which will be applied to a retrieved entity prior to
 * performing a POST to effect the update.
 */
public class SimpleParameter extends Parameter {

    private SimpleParameter(String baseName,
                            Description description,
                            ImportRequirements importRequirements,
                            LanguageId specificLanguageId,
                            DataDescriptor dataDescriptor,
                            boolean isOptional) {
        super(baseName, description, importRequirements, specificLanguageId, dataDescriptor, isOptional);
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class SimpleParameter";

        private String _baseName = null;
        private DataDescriptor _dataDescriptor = null;
        private final List<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isOptional = false;
        private LanguageId _specificLanguageId = null;

        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setDataDescriptor(DataDescriptor value) { _dataDescriptor = value; return this; }
        public Builder setIsOptional(boolean value) { _isOptional = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public SimpleParameter build() {
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_dataDescriptor == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setDataDescriptor"));
            }

            if (_description.isEmpty()) {
                throw new RuntimeException("addDescription");
            }

            return new SimpleParameter(_baseName,
                                       new Description(_description),
                                       _importRequirements,
                                       _specificLanguageId,
                                       _dataDescriptor,
                                       _isOptional);
        }
    }
}
