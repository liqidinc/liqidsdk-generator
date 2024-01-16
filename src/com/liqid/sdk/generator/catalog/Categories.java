//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.catalog;

import com.liqid.sdk.generator.liqidEntityModels.CategoryId;
import com.liqid.sdk.generator.liqidEntityModels.Category;
import com.liqid.sdk.generator.liqidEntityModels.FunctionId;
import java.util.LinkedHashMap;
import java.util.Map;

class Categories {

    public static final Category Backup =
        new Category.Builder()
            .setCategoryId(CategoryId.BACKUP)
            .addDescription("Relates to backing up the Director configuration")
            .addFunction(FunctionId.BACKUP_DIRECTOR)
            .build();

    public static final Category Client =
        new Category.Builder()
            .setCategoryId(CategoryId.CLIENT)
            .addDescription("Functions related to the state of the client struct/object, or to its state.")
            .addDescription("This includes logging in and out, and any settings for SDK logging.")
            .addFunction(FunctionId.CLEAR_CREDENTIALS)
            .addFunction(FunctionId.HAS_CREDENTIALS)
            .addFunction(FunctionId.INITIALIZE)
            .addFunction(FunctionId.IS_LOGGED_IN)
            .addFunction(FunctionId.LOGIN)
            .addFunction(FunctionId.LOGOUT)
            .addFunction(FunctionId.SET_CREDENTIALS)
            .addFunction(FunctionId.SET_LOGGING)
            .build();

    public static final Category Coordinates =
        new Category.Builder()
            .setCategoryId(CategoryId.COORDINATES)
            .addDescription("These functions relate to internal API mechanisms, and are not particularly useful for the SDK client.")
            .addDescription("They are presented here for completeness, and due to the possibility that one or another of them might eventually be needed.")
            .addFunction(FunctionId.GET_AVAILABLE_COORDINATES)
            .addFunction(FunctionId.GET_DEFAULT_COORDINATES)
            .addFunction(FunctionId.SET_DEFAULT_COORDINATES)
            .build();

    public static final Category Device =
        new Category.Builder()
            .setCategoryId(CategoryId.DEVICE)
            .addDescription("Function generally related to devices")
            .addFunction(FunctionId.CREATE_DEVICE_DESCRIPTION)
            .addFunction(FunctionId.DELETE_DEVICE_DESCRIPTION)
            .addFunction(FunctionId.GET_DEVICE_ATTRIBUTES)
            .addFunction(FunctionId.GET_SECURE_ERASE_STATUS)
            .addFunction(FunctionId.SECURE_ERASE)
            .build();

    public static final Category DeviceDiscovery =
        new Category.Builder()
            .setCategoryId(CategoryId.DEVICE_DISCOVERY)
            .addDescription("Functions relating to discovery of PCI devices")
            .addFunction(FunctionId.GET_DEVICE_COUNTERS)
            .build();

    public static final Category DeviceInfo =
        new Category.Builder()
            .setCategoryId(CategoryId.DEVICE_INFO)
            .addDescription("Functions which retrieve miscellaneous device information")
            .addFunction(FunctionId.GET_COMPUTE_DEVICE_INFO)
            .addFunction(FunctionId.GET_COMPUTE_DEVICE_INFO_BY_NAME)
            .addFunction(FunctionId.GET_FPGA_DEVICE_INFO)
            .addFunction(FunctionId.GET_FPGA_DEVICE_INFO_BY_NAME)
            .addFunction(FunctionId.GET_GPU_DEVICE_INFO)
            .addFunction(FunctionId.GET_GPU_DEVICE_INFO_BY_NAME)
            .addFunction(FunctionId.GET_MEMORY_DEVICE_INFO)
            .addFunction(FunctionId.GET_MEMORY_DEVICE_INFO_BY_NAME)
            .addFunction(FunctionId.GET_NETWORK_DEVICE_INFO)
            .addFunction(FunctionId.GET_NETWORK_DEVICE_INFO_BY_NAME)
            .addFunction(FunctionId.GET_STORAGE_DEVICE_INFO)
            .addFunction(FunctionId.GET_STORAGE_DEVICE_INFO_BY_NAME)
            .build();

    public static final Category DeviceStatus =
        new Category.Builder()
            .setCategoryId(CategoryId.DEVICE_STATUS)
            .addDescription("Functions which retrieve the status of various devices")
            .addFunction(FunctionId.GET_DEVICE_STATUS)
            .addFunction(FunctionId.GET_ALL_DEVICES_STATUS)
            .addFunction(FunctionId.GET_COMPUTE_DEVICE_STATUS)
            .addFunction(FunctionId.GET_COMPUTE_DEVICES_STATUS)
            .addFunction(FunctionId.GET_COMPUTE_DEVICES_WITH_MULTIPLE_PORTS_STATUS)
            .addFunction(FunctionId.GET_FPGA_DEVICE_STATUS)
            .addFunction(FunctionId.GET_FPGA_DEVICES_STATUS)
            .addFunction(FunctionId.GET_FREE_COMPUTE_DEVICES_STATUS)
            .addFunction(FunctionId.GET_FREE_FPGA_DEVICES_STATUS)
            .addFunction(FunctionId.GET_FREE_GPU_DEVICES_STATUS)
            .addFunction(FunctionId.GET_FREE_MEMORY_DEVICES_STATUS)
            .addFunction(FunctionId.GET_FREE_NETWORK_DEVICES_STATUS)
            .addFunction(FunctionId.GET_FREE_STORAGE_DEVICES_STATUS)
            .addFunction(FunctionId.GET_GPU_DEVICE_STATUS)
            .addFunction(FunctionId.GET_GPU_DEVICES_STATUS)
            .addFunction(FunctionId.GET_MEMORY_DEVICE_STATUS)
            .addFunction(FunctionId.GET_MEMORY_DEVICES_STATUS)
            .addFunction(FunctionId.GET_NETWORK_DEVICE_STATUS)
            .addFunction(FunctionId.GET_NETWORK_DEVICES_STATUS)
            .addFunction(FunctionId.GET_STORAGE_DEVICE_STATUS)
            .addFunction(FunctionId.GET_STORAGE_DEVICES_STATUS)
            .build();

    public static final Category Fabric =
        new Category.Builder()
            .setCategoryId(CategoryId.FABRIC)
            .addDescription("These functions relate to the full configuration (fabric) which is managed by the currently-connected Director.")
            .addFunction(FunctionId.APPLY_FABRIC_CHANGES)
            .addFunction(FunctionId.CANCEL_EDIT_FABRIC)
            .addFunction(FunctionId.CANCEL_REPROGRAM_FABRIC)
            .addFunction(FunctionId.EDIT_FABRIC)
            .addFunction(FunctionId.GET_CURRENT_FABRIC_ID)
            .addFunction(FunctionId.GET_FABRIC_TOPOLOGY)
            .addFunction(FunctionId.GET_FABRIC_TYPES)
            .addFunction(FunctionId.REPROGRAM_FABRIC)
            .build();

    public static final Category Group =
        new Category.Builder()
            .setCategoryId(CategoryId.GROUP)
            .addDescription("Includes functions related to the configuration of a group.")
            .addFunction(FunctionId.CLEAR_GROUPS)
            .addFunction(FunctionId.CREATE_GROUP)
            .addFunction(FunctionId.CREATE_GROUP_WITH_ID)
            .addFunction(FunctionId.DELETE_GROUP)
            .addFunction(FunctionId.GET_GROUP)
            .addFunction(FunctionId.GET_GROUPS)
            .addFunction(FunctionId.GET_GROUP_DETAILS)
            .addFunction(FunctionId.GET_GROUP_ID_BY_NAME)
            .addFunction(FunctionId.GET_NEXT_GROUP_ID)
            .build();

    public static final Category GroupDeviceRelator =
        new Category.Builder()
            .setCategoryId(CategoryId.GROUP_DEVICE_RELATOR)
            .addDescription("Functions which move devices into and out of group free pools")
            .addFunction(FunctionId.ADD_COMPUTE_DEVICE_TO_GROUP)
            .addFunction(FunctionId.ADD_DEVICE_TO_GROUP)
            .addFunction(FunctionId.ADD_FPGA_DEVICE_TO_GROUP)
            .addFunction(FunctionId.ADD_GPU_DEVICE_TO_GROUP)
            .addFunction(FunctionId.ADD_MEMORY_DEVICE_TO_GROUP)
            .addFunction(FunctionId.ADD_NETWORK_DEVICE_TO_GROUP)
            .addFunction(FunctionId.ADD_STORAGE_DEVICE_TO_GROUP)
            .addFunction(FunctionId.GET_PRE_DEVICES)
            .addFunction(FunctionId.GET_GROUP_COMPUTE_DEVICE_RELATOR)
            .addFunction(FunctionId.GET_GROUP_FPGA_DEVICE_RELATOR)
            .addFunction(FunctionId.GET_GROUP_GPU_DEVICE_RELATOR)
            .addFunction(FunctionId.GET_GROUP_MEMORY_DEVICE_RELATOR)
            .addFunction(FunctionId.GET_GROUP_NETWORK_DEVICE_RELATOR)
            .addFunction(FunctionId.GET_GROUP_STORAGE_DEVICE_RELATOR)
            .addFunction(FunctionId.GET_UNATTACHED_DEVICES_FOR_GROUP)
            .addFunction(FunctionId.REMOVE_COMPUTE_DEVICE_FROM_GROUP)
            .addFunction(FunctionId.REMOVE_DEVICE_FROM_GROUP)
            .addFunction(FunctionId.REMOVE_FPGA_DEVICE_FROM_GROUP)
            .addFunction(FunctionId.REMOVE_GPU_DEVICE_FROM_GROUP)
            .addFunction(FunctionId.REMOVE_MEMORY_DEVICE_FROM_GROUP)
            .addFunction(FunctionId.REMOVE_NETWORK_DEVICE_FROM_GROUP)
            .addFunction(FunctionId.REMOVE_STORAGE_DEVICE_FROM_GROUP)
            .build();

    public static final Category GroupPool =
        new Category.Builder()
            .setCategoryId(CategoryId.GROUP_POOL)
            .addDescription("Functions which manage edit mode for the group pool")
            .addFunction(FunctionId.CANCEL_GROUP_POOL_EDIT)
            .addFunction(FunctionId.GROUP_POOL_DONE)
            .addFunction(FunctionId.GROUP_POOL_EDIT)
            .build();

    public static final Category Machine =
        new Category.Builder()
            .setCategoryId(CategoryId.MACHINE)
            .addDescription("General functions related to configured machines")
            .addFunction(FunctionId.CREATE_MACHINE)
            .addFunction(FunctionId.CREATE_MACHINE_WITH_ID)
            .addFunction(FunctionId.DELETE_MACHINE)
            .addFunction(FunctionId.GET_MACHINE)
            .addFunction(FunctionId.GET_MACHINES)
            .addFunction(FunctionId.GET_MACHINES_AT_COORDINATES)
            .addFunction(FunctionId.GET_MACHINES_BY_GROUP_ID)
            .addFunction(FunctionId.GET_MACHINE_BY_NAME)
            .addFunction(FunctionId.GET_MACHINE_DETAILS)
            .addFunction(FunctionId.GET_NEXT_MACHINE_ID)
            .addFunction(FunctionId.SET_P2P_ENABLED)
            .build();

    public static final Category MachineDeviceRelator =
        new Category.Builder()
            .setCategoryId(CategoryId.MACHINE_DEVICE_RELATOR)
            .addDescription("Functions which attach devices to and detach them from machines")
            .addFunction(FunctionId.ADD_COMPUTE_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.ADD_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.ADD_FPGA_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.ADD_GPU_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.ADD_MEMORY_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.ADD_NETWORK_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.ADD_STORAGE_DEVICE_TO_MACHINE)
            .addFunction(FunctionId.REMOVE_COMPUTE_DEVICE_FROM_MACHINE)
            .addFunction(FunctionId.REMOVE_DEVICE_FROM_MACHINE)
            .addFunction(FunctionId.REMOVE_FPGA_DEVICE_FROM_MACHINE)
            .addFunction(FunctionId.REMOVE_GPU_DEVICE_FROM_MACHINE)
            .addFunction(FunctionId.REMOVE_MEMORY_DEVICE_FROM_MACHINE)
            .addFunction(FunctionId.REMOVE_NETWORK_DEVICE_FROM_MACHINE)
            .addFunction(FunctionId.REMOVE_STORAGE_DEVICE_FROM_MACHINE)
            .build();

    public static final Category Manager =
        new Category.Builder()
            .setCategoryId(CategoryId.MANAGER)
            .addDescription("These functions manage the managed-entity descriptors which are used for discovering devices.")
            .addFunction(FunctionId.GET_DISCOVERY_TOKENS)
            .addFunction(FunctionId.GET_DISCOVERY_TOKENS_BY_TYPE)
            .addFunction(FunctionId.GET_NETWORK_IPMI_MANAGED_CPU)
            .addFunction(FunctionId.GET_NETWORK_IPMI_MANAGED_CPUS)
            .addFunction(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURE)
            .addFunction(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURES)
            .addFunction(FunctionId.GET_MANAGED_ENTITIES)
            .addFunction(FunctionId.GET_MANAGED_ENTITY)
            .build();

    public static final Category MessageDigest =
        new Category.Builder()
            .setCategoryId(CategoryId.MESSAGE_DIGEST)
            .addDescription("These functions relate to secure authentication of REST API function calls.")
            .addDescription("They are not of use to the SDK clients, as the SDK provides wrapper methods for these functions.")
            .addDescription("See commentary on authentication models and functions related to logging in and logging out.")
            //TODO put links to login and logout functions in the above commentary
            //    (which means those functions need to be described as hard-coded functions)
            .addFunction(FunctionId.CREATE_MESSAGE_DIGEST)
            .addFunction(FunctionId.DELETE_MESSAGE_DIGEST)
            .addFunction(FunctionId.GET_MESSAGE_DIGEST_LABELS)
            .build();

    public static final Category NodeStatus =
        new Category.Builder()
            .setCategoryId(CategoryId.NODE_STATUS)
            .addDescription("Retrieves NodeStatus entities describing the various nodes")
            .addFunction(FunctionId.GET_NODES_STATUS)
            .build();

    public static final Category PowerManagement =
        new Category.Builder()
            .setCategoryId(CategoryId.POWER_MANAGEMENT)
            .addDescription("Manages power states for a node")
            .addFunction(FunctionId.REBOOT_NODE)
            .addFunction(FunctionId.RESTART_NODE)
            .addFunction(FunctionId.SHUTDOWN_NODE)
            .addFunction(FunctionId.START_NODE)
            .build();

    public static final Category SSH =
        new Category.Builder()
            .setCategoryId(CategoryId.SSH)
            .addDescription("Includes functions which get and set SSH status")
            .addFunction(FunctionId.GET_SSH_STATUS)
            .addFunction(FunctionId.SET_SSH_STATUS)
            .build();

    public static final Category System =
        new Category.Builder()
            .setCategoryId(CategoryId.SYSTEM)
            .addDescription("Functions which control system power")
            .addFunction(FunctionId.RESET_STATE)
            .addFunction(FunctionId.RESTART_FABRIC)
            .addFunction(FunctionId.RESTART_HIERARCHY)
            .addFunction(FunctionId.RESTART_SWITCH)
            .addFunction(FunctionId.SHUTDOWN)
            .addFunction(FunctionId.SHUTDOWN_AT)
            .build();

    public static final Category Version =
        new Category.Builder()
            .setCategoryId(CategoryId.VERSION)
            .addDescription("Includes functions related to reporting software component version information.")
            .addFunction(FunctionId.GET_VERSIONS)
            .build();

    public static final Map<CategoryId, Category> CONTENT = new LinkedHashMap<>();
    static {
        CONTENT.put(CategoryId.BACKUP, Backup);
        CONTENT.put(CategoryId.CLIENT, Client);
        CONTENT.put(CategoryId.COORDINATES, Coordinates);
        CONTENT.put(CategoryId.DEVICE, Device);
        CONTENT.put(CategoryId.DEVICE_DISCOVERY, DeviceDiscovery);
        CONTENT.put(CategoryId.DEVICE_INFO, DeviceInfo);
        CONTENT.put(CategoryId.DEVICE_STATUS, DeviceStatus);
        CONTENT.put(CategoryId.FABRIC, Fabric);
        CONTENT.put(CategoryId.GROUP, Group);
        CONTENT.put(CategoryId.GROUP_DEVICE_RELATOR, GroupDeviceRelator);
        CONTENT.put(CategoryId.GROUP_POOL, GroupPool);
        CONTENT.put(CategoryId.MACHINE, Machine);
        CONTENT.put(CategoryId.MACHINE_DEVICE_RELATOR, MachineDeviceRelator);
        CONTENT.put(CategoryId.MANAGER, Manager);
        CONTENT.put(CategoryId.MESSAGE_DIGEST, MessageDigest);
        CONTENT.put(CategoryId.NODE_STATUS, NodeStatus);
        CONTENT.put(CategoryId.POWER_MANAGEMENT, PowerManagement);
        CONTENT.put(CategoryId.SSH, SSH);
        CONTENT.put(CategoryId.SYSTEM, System);
        CONTENT.put(CategoryId.VERSION, Version);
    };
}
