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
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.writers.LanguageId;

public abstract class Parameter extends LiqidEntity {

    private final DataDescriptor _dataDescriptor;
    private final boolean _isOptional;

    protected Parameter(String baseName,
                        Description description,
                        ImportRequirements importRequirements,
                        LanguageId specificLanguageId,
                        DataDescriptor dataDescriptor,
                        boolean isOptional) {
        super(baseName, description, importRequirements, specificLanguageId);
        _dataDescriptor = dataDescriptor;
        _isOptional = isOptional;
    }

    public final DataDescriptor getDataDescriptor() { return _dataDescriptor; }
    public final boolean isOptional() { return _isOptional; }
}
