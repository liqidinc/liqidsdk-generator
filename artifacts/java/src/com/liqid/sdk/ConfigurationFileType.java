// File: ConfigurationFileType.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.4
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ConfigurationFileType
 * Describes a type of configuration file
 */
public enum ConfigurationFileType {
    @JsonProperty("slurm") SLURM("slurm"),
    @JsonProperty("kubernetes") KUBERNETES("kubernetes"),
    @JsonProperty("redfish") REDFISH("redfish"),
    @JsonProperty("vapi") VAPI("vapi"),
    @JsonProperty("ipmi") IPMI("ipmi");

    private final String _value;

    ConfigurationFileType(String value) {
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
