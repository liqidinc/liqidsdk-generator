//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

public abstract class DataDescriptor {

    private final boolean _isArray;

    public DataDescriptor(boolean isArray) {
        _isArray = isArray;
    }

    public abstract DataTypeId getDataTypeId();

    @Override
    public abstract boolean equals(Object obj);

    public final boolean isArray() { return _isArray; }
}
