//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

import com.liqid.sdk.generator.liqidEntityModels.StructId;

public class StructDataDescriptor extends DataDescriptor {

    private final StructId _structId;

    public StructDataDescriptor(StructId structId) {
        super(false);
        _structId = structId;
    }

    public StructDataDescriptor(StructId structId, boolean isArray) {
        super(isArray);
        _structId = structId;
    }

    public final DataTypeId getDataTypeId() { return DataTypeId.STRUCT; }
    public final StructId getStructId() { return _structId; }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof StructDataDescriptor sdd) && (sdd._structId == _structId);
    }
}
