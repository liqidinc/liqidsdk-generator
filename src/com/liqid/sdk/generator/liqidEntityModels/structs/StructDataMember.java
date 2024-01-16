//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.structs;

import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.EnumeratorDataDescriptor;
import com.liqid.sdk.generator.discreteDataModels.IntrinsicDataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.LinkedList;
import java.util.List;

public class StructDataMember extends StructMember {

    private final DataDescriptor _dataDescriptor;
    private final Object _defaultValue;
    private final boolean _isOptional;
    private final String _jsonTag;

    public StructDataMember(String baseName,
                            Description description,
                            ImportRequirements importRequirements,
                            LanguageId specificLanguageId,
                            DataDescriptor dataDescriptor,
                            String jsonTag,
                            boolean isOptional,
                            Object defaultValue) {
        super(baseName, description, importRequirements, specificLanguageId);
        _dataDescriptor = dataDescriptor;
        _defaultValue = defaultValue;
        _isOptional = isOptional;
        _jsonTag = jsonTag;
    }

    public final DataDescriptor getDataDescriptor() {
        return _dataDescriptor;
    }

    public final Object getDefaultValue() {
        return _defaultValue;
    }

    public final String getJsonTag() {
        return _jsonTag;
    }

    public final boolean hasDefaultValue() { return _defaultValue != null; }

    public final boolean isOptional() {
        return _isOptional;
    }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class StructDataMember";

        private String _baseName = null;
        private DataDescriptor _dataDescriptor = null;
        private Object _defaultValue = null;
        private final List<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private boolean _isOptional = false;
        private String _jsonTag = null;
        private LanguageId _specificLanguageId = null;

        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirements(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setDataDescriptor(DataDescriptor value) { _dataDescriptor = value; return this; }

        /**
         * We can only provide default values for intrinsics and for enumerators. And never for arrays.
         * For enumerators, we must provide exactly the value which corresponds to the enum instance value.
         * For intrinsic values, provide the literal that you would code, excepting that we do not want
         * double-quote delimiters for strings.
         */
        public Builder setDefaultValue(Object value) { _defaultValue = value; return this; }

        public Builder setIsOptional(boolean value) { _isOptional = value; return this; }
        public Builder setJsonTag(String value) { _jsonTag = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public StructDataMember build() {
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }

            if (_dataDescriptor == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setDataDescriptor"));
            }

            if (_defaultValue != null
                && !(_dataDescriptor instanceof IntrinsicDataDescriptor)
                && !(_dataDescriptor instanceof EnumeratorDataDescriptor)) {
                throw new RuntimeException("StructDataMember of this type cannot have a default value");
            }

            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }

            return new StructDataMember(_baseName,
                                        new Description(_description),
                                        _importRequirements,
                                        _specificLanguageId,
                                        _dataDescriptor,
                                        _jsonTag,
                                        _isOptional,
                                        _defaultValue);
        }
    }
}
