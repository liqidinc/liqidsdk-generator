//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.catalog;

import com.liqid.sdk.generator.discreteDataModels.StructDataDescriptor;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.types.Typedef;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.LinkedHashMap;
import java.util.Map;

class Typedefs {

    private static final Typedef FabricTopology =
        new Typedef.Builder()
            .setTypedefId(TypedefId.FABRIC_TOPOLOGY)
            .setBaseName("FabricTopology")
            .setSourceDataType(new StructDataDescriptor(StructId.FABRIC_ITEM, true))
            .addDescription("An array of {struct FabricItem} entities")
            .addImportRequirement(LanguageId.JAVA, "java.util.LinkedList")
            .build();

    public static final Map<TypedefId, Typedef> CONTENT = new LinkedHashMap<>();
    static {
        CONTENT.put(TypedefId.FABRIC_TOPOLOGY, FabricTopology);
    }
}
