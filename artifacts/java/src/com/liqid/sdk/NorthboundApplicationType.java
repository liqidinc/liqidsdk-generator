// File: NorthboundApplicationType.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.3.0
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * NorthboundApplicationType
 * Describes a northbound application type
 */
public enum NorthboundApplicationType {
    @JsonProperty("slurm") SLURM("slurm"),
    @JsonProperty("kubernetes") KUBERNETES("kubernetes"),
    @JsonProperty("redfish") REDFISH("redfish"),
    @JsonProperty("vapi") VAPI("vapi"),
    @JsonProperty("ipmi") IPMI("ipmi");

    private final String _value;

    NorthboundApplicationType(String value) {
        _value = value;
    }

    public String getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return _value;
    }
}
