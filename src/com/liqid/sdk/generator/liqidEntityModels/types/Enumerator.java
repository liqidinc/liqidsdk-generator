//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.types;

import com.liqid.sdk.generator.discreteDataModels.IntrinsicTypeId;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.EnumeratorId;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.LinkedList;

/**
 * Describes an API-defined enumerator.
 * Languages which do not support enumerators should implement this as a series of constants.
 */
public class Enumerator extends LiqidEntity {

    /**
     * Unique identifier for this enumerator
     */
    private final EnumeratorId _enumeratorId;

    /**
     * Indicates the underlying data type of the various enum instances.
     * Generally this will be INT32, but it could be BOOL or STRING.
     */
    private final IntrinsicTypeId _intrinsicTypeId;

    /**
     * Describes the various instances of this enumerator.
     */
    private final EnumComponent[] _components;

    public Enumerator(EnumeratorId enumeratorId,
                      String baseName,
                      Description description,
                      ImportRequirements importRequirements,
                      LanguageId specificLanguageId,
                      IntrinsicTypeId intrinsicTypeId,
                      EnumComponent[] components) {
        super(baseName, description, importRequirements, specificLanguageId);
        _enumeratorId = enumeratorId;
        _components = components;
        _intrinsicTypeId = intrinsicTypeId;
    }

    public EnumComponent[] getComponents() { return _components; }
    public EnumeratorId getEnumeratorId() { return _enumeratorId; }
    public IntrinsicTypeId getIntrinsicTypeId() { return _intrinsicTypeId; }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class Enumerator";

        private String _baseName = null;
        private final LinkedList<EnumComponent> _components = new LinkedList<>();
        private final LinkedList<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private EnumeratorId _enumeratorId = null;
        private IntrinsicTypeId _intrinsicTypeId = null;
        private LanguageId _specificLanguageId = null;

        public Builder addComponent(EnumComponent value) { _components.add(value); return this; }
        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirement(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setEnumeratorId(EnumeratorId value) { _enumeratorId = value; return this; }
        public Builder setIntrinsicTypeId(IntrinsicTypeId value) { _intrinsicTypeId = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }

        public Enumerator build() {
            if (_enumeratorId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setEnumeratorId"));
            }
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }
            if (_components.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addComponent"));
            }
            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }
            if (_intrinsicTypeId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setIntrinsicTypeId"));
            }

            return new Enumerator(_enumeratorId,
                                  _baseName,
                                  new Description(_description),
                                  _importRequirements,
                                  _specificLanguageId,
                                  _intrinsicTypeId,
                                  _components.toArray(new EnumComponent[]{}));
        }
    }
}
