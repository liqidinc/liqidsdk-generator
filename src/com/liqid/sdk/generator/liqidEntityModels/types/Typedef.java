//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.types;

import com.liqid.sdk.generator.discreteDataModels.DataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.liqidEntityModels.TypedefId;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Describes a type which, while not necessarily API-defined, is nevertheless useful for implementing
 * the bridge between the SDK and the API.
 * Languages which do not support typedefs should implement this in some reasonably useful manner.
 */
public class Typedef extends LiqidEntity {

    /**
     * Unique identifier
     */
    private final TypedefId _typedefId;

    /**
     * Source data type
     */
    private final DataDescriptor _sourceDataType;

    public Typedef(TypedefId typedefId,
                   String baseName,
                   Description description,
                   ImportRequirements importRequirements,
                   LanguageId specificLanguageId,
                   DataDescriptor sourceDataType) {
        super(baseName, description, importRequirements, specificLanguageId);
        _typedefId = typedefId;
        _sourceDataType = sourceDataType;
    }

    public DataDescriptor getSourceDataType() { return _sourceDataType; }
    public TypedefId getTypedefId() { return _typedefId; }

    public static class Builder {

        private static final String ExceptionFmt = "%s() was not invoked in Builder for class Typedef";

        private String _baseName = null;
        private final LinkedList<String> _description = new LinkedList<>();
        private final ImportRequirements _importRequirements = new ImportRequirements();
        private DataDescriptor _sourceDataType = null;
        private LanguageId _specificLanguageId = null;
        private TypedefId _typedefId = null;

        public Builder addDescription(String value) { _description.add(value); return this; }

        public Builder addImportRequirement(LanguageId languageId, String importString) {
            _importRequirements.addRequirement(languageId, importString);
            return this;
        }

        public Builder setBaseName(String value) { _baseName = value; return this; }
        public Builder setSourceDataType(DataDescriptor value) { _sourceDataType = value; return this; }
        public Builder setSpecificLanguageId(LanguageId value) { _specificLanguageId = value; return this; }
        public Builder setTypedefId(TypedefId value) { _typedefId = value; return this; }

        public Typedef build() {
            if (_typedefId == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setTypedefId"));
            }
            if (_baseName == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setBaseName"));
            }
            if (_description.isEmpty()) {
                throw new RuntimeException(String.format(ExceptionFmt, "addDescription"));
            }
            if (_sourceDataType == null) {
                throw new RuntimeException(String.format(ExceptionFmt, "setSourceDataType"));
            }

            return new Typedef(_typedefId,
                               _baseName,
                               new Description(_description),
                               _importRequirements,
                               _specificLanguageId,
                               _sourceDataType);
        }
    }

//    public static Collection<Typedef> sort(Collection<Typedef> source) {
//        return source.stream()
//                     .collect(Collectors.toMap(LiqidEntity::getBaseName, t -> t, (a, b) -> b, TreeMap::new))
//                     .values();
//    }
}
