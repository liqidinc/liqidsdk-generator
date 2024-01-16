//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

/**
 * Describes certain presets which can be obtained on behalf of the SDK client,
 * in order that the client would not have to query for these before using functions which need them.
 */
public enum PresetId {
    CURRENT_COORDINATES_RACK,
    CURRENT_COORDINATES_SHELF,
    CURRENT_COORDINATES_NODE,
    CURRENT_FABRIC_ID,
    ALWAYS_FALSE,
    ALWAYS_TRUE,
}
