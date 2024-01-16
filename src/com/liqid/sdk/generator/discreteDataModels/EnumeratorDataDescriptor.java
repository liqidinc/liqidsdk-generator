//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

import com.liqid.sdk.generator.liqidEntityModels.EnumeratorId;

public class EnumeratorDataDescriptor extends DataDescriptor {

    private final EnumeratorId _enumeratorId;

    public EnumeratorDataDescriptor(EnumeratorId enumeratorId) {
        super(false);
        _enumeratorId = enumeratorId;
    }

    public EnumeratorDataDescriptor(EnumeratorId EnumeratorId, boolean isArray) {
        super(isArray);
        _enumeratorId = EnumeratorId;
    }

    public final DataTypeId getDataTypeId() { return DataTypeId.ENUMERATOR; }
    public final EnumeratorId getEnumeratorId() { return _enumeratorId; }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof EnumeratorDataDescriptor edd) && (edd._enumeratorId == _enumeratorId);
    }
}
