//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

/**
 * Currently unused - this is for a function which returns nothing.
 */
public class VoidDataDescriptor extends DataDescriptor {

    public VoidDataDescriptor() {
        super(false);
    }

    public final DataTypeId getDataTypeId() { return DataTypeId.VOID; }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof VoidDataDescriptor);
    }
}
