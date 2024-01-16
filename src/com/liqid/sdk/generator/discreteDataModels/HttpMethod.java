//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.discreteDataModels;

public enum HttpMethod {
    DELETE("DELETE"),
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private final String _value;

    HttpMethod(String value) {
        _value = value;
    }

    public String getValue() { return _value; }
}
