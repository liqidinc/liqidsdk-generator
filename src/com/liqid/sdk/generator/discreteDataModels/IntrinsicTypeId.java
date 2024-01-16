//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

public enum IntrinsicTypeId {
    BOOLEAN,
    FLOAT,
    INT32,
    INT16_AS_STRING, // API presents or expects a string coded as 0xnnnn which we expose as a 32-bit int
    INT24_AS_STRING, // API presents or expects a string coded as 0xnnnnnn which we expose as a 32-bit int
    INT32_AS_STRING, // API presents or expects a string coded as 0xnnnnnnnn which we expose as a 32-bit int
    INT32_AS_DECIMAL_STRING, // API presents or expects a string containing a decimal number
    INT64,
    INT64_AS_STRING, // API presents or expects a string coded as 0xnnnnnnnnnnnnnnnn which we expose as a 64-bit int
    STRING,
    STRING_ARRAY,
    STRING_ARRAY_MAP,
}
