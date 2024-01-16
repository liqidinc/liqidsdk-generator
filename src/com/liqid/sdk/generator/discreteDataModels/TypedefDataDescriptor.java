//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

import com.liqid.sdk.generator.liqidEntityModels.TypedefId;

public class TypedefDataDescriptor extends DataDescriptor {

    private final TypedefId _typedefId;

    public TypedefDataDescriptor(TypedefId typedefId) {
        super(false);
        _typedefId = typedefId;
    }

    public TypedefDataDescriptor(TypedefId typedefId, boolean isArray) {
        super(isArray);
        _typedefId = typedefId;
    }

    public final DataTypeId getDataTypeId() { return DataTypeId.TYPEDEF; }
    public final TypedefId getTypedefId() { return _typedefId; }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof TypedefDataDescriptor tdd) && (tdd._typedefId == _typedefId);
    }
}
