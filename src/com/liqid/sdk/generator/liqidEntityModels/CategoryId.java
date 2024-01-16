//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels;

public enum CategoryId {
    BACKUP("backup"),
    CLIENT("client"),
    COORDINATES("coordinates"),
    DEPLOYMENT("deployment"),
    DEVICE("device"),
    DEVICE_DISCOVERY("deviceDiscovery"),
    DEVICE_INFO("deviceInfo"),
    DEVICE_STATUS("deviceStatus"),
    ENCLOSURE("enclosure"),
    FABRIC("fabric"),
    GROUP("group"),
    GROUP_DEVICE_RELATOR("groupDeviceRelator"),
    GROUP_POOL("groupPool"),
    HEALTH_CHECK("healthCheck"),
    IMAGE("image"),
    LOG_COLLECTOR("logCollector"),
    MACHINE("machine"),
    MACHINE_DEVICE_RELATOR("machineDeviceRelator"),
    MANAGER("manager"),
    MESSAGE_DIGEST("messageDigest"),
    NODE_CONFIGURATION("nodeConfiguration"),
    NODE_STATUS("nodeStatus"),
    NOTIFICATION("notification"),
    POWER_MANAGEMENT("powerManagement"),
    ROLES("roles"),
    SSH("ssh"),
    STATE("state"),
    SYSTEM("system"),
    UPGRADE("upgrade"),
    VERSION("version");

    private final String _value;

    CategoryId(String value) {
        _value = value;
    }

    public String getValue() {
        return _value;
    }
}
