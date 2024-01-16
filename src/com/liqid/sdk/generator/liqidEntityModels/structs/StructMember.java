//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels.structs;

import com.liqid.sdk.generator.liqidEntityModels.Description;
import com.liqid.sdk.generator.liqidEntityModels.ImportRequirements;
import com.liqid.sdk.generator.liqidEntityModels.LiqidEntity;
import com.liqid.sdk.generator.writers.LanguageId;

public abstract class StructMember extends LiqidEntity {

    public StructMember(String baseName,
                        Description description,
                        ImportRequirements importRequirements,
                        LanguageId languageSpecificId) {
        super(baseName, description, importRequirements, languageSpecificId);
    }
}
