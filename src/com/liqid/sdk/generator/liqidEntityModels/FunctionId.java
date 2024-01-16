//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.liqidEntityModels;

public enum FunctionId {
    ADD_COMPUTE_DEVICE_TO_GROUP,
    ADD_COMPUTE_DEVICE_TO_MACHINE,
    ADD_DEVICE_TO_GROUP,
    ADD_DEVICE_TO_MACHINE,
    ADD_FPGA_DEVICE_TO_GROUP,
    ADD_FPGA_DEVICE_TO_MACHINE,
    ADD_GPU_DEVICE_TO_GROUP,
    ADD_GPU_DEVICE_TO_MACHINE,
    ADD_MEMORY_DEVICE_TO_GROUP,
    ADD_MEMORY_DEVICE_TO_MACHINE,
    ADD_NETWORK_DEVICE_TO_GROUP,
    ADD_NETWORK_DEVICE_TO_MACHINE,
    ADD_STORAGE_DEVICE_TO_GROUP,
    ADD_STORAGE_DEVICE_TO_MACHINE,
    APPLY_FABRIC_CHANGES,
    BACKUP_DIRECTOR,
    CANCEL_EDIT_FABRIC,
    CANCEL_GROUP_POOL_EDIT,
    CANCEL_REPROGRAM_FABRIC,
    CLEAR_CREDENTIALS,
    CLEAR_GROUPS,
    CREATE_DEVICE_DESCRIPTION,
    CREATE_GROUP,
    CREATE_GROUP_WITH_ID,
    CREATE_MACHINE,
    CREATE_MACHINE_WITH_ID,
    CREATE_MESSAGE_DIGEST,
    DELETE_DEVICE_DESCRIPTION,
    DELETE_GROUP,
    DELETE_MACHINE,
    DELETE_MESSAGE_DIGEST,
    EDIT_FABRIC,
    GET_ALL_DEVICES_STATUS,
    GET_AVAILABLE_COORDINATES,
    GET_COMPUTE_DEVICES_STATUS,
    GET_COMPUTE_DEVICE_INFO,
    GET_COMPUTE_DEVICE_INFO_BY_NAME,
    GET_COMPUTE_DEVICE_STATUS,
    GET_COMPUTE_DEVICES_WITH_MULTIPLE_PORTS_STATUS,
    GET_CURRENT_FABRIC_ID,
    GET_DEFAULT_COORDINATES,
    GET_DEVICE_ATTRIBUTES,
    GET_DEVICE_COUNTERS,
    GET_DEVICE_STATUS,
    GET_PRE_DEVICES,
    GET_DISCOVERY_TOKENS,
    GET_DISCOVERY_TOKENS_BY_TYPE,
    GET_FABRIC_TOPOLOGY,
    GET_FABRIC_TYPES,
    GET_FPGA_DEVICES_STATUS,
    GET_FPGA_DEVICE_INFO,
    GET_FPGA_DEVICE_INFO_BY_NAME,
    GET_FPGA_DEVICE_STATUS,
    GET_FREE_COMPUTE_DEVICES_STATUS,
    GET_FREE_FPGA_DEVICES_STATUS,
    GET_FREE_GPU_DEVICES_STATUS,
    GET_FREE_MEMORY_DEVICES_STATUS,
    GET_FREE_NETWORK_DEVICES_STATUS,
    GET_FREE_STORAGE_DEVICES_STATUS,
    GET_GROUP_COMPUTE_DEVICE_RELATOR,
    GET_GROUP_FPGA_DEVICE_RELATOR,
    GET_GROUP_GPU_DEVICE_RELATOR,
    GET_GROUP_MEMORY_DEVICE_RELATOR,
    GET_GROUP_NETWORK_DEVICE_RELATOR,
    GET_GROUP_STORAGE_DEVICE_RELATOR,
    GET_GPU_DEVICES_STATUS,
    GET_GPU_DEVICE_INFO,
    GET_GPU_DEVICE_INFO_BY_NAME,
    GET_GPU_DEVICE_STATUS,
    GET_GROUP,
    GET_GROUPS,
    GET_GROUP_DETAILS,
    GET_GROUP_ID_BY_NAME,
    GET_MACHINES,
    GET_MACHINES_AT_COORDINATES,
    GET_MACHINES_BY_GROUP_ID,
    GET_MACHINE,
    GET_MACHINE_BY_NAME,
    GET_MACHINE_DETAILS,
    GET_MANAGED_ENTITIES,
    GET_MANAGED_ENTITY,
    GET_MEMORY_DEVICES_STATUS,
    GET_MEMORY_DEVICE_INFO,
    GET_MEMORY_DEVICE_INFO_BY_NAME,
    GET_MEMORY_DEVICE_STATUS,
    GET_MESSAGE_DIGEST_LABELS,
    GET_NETWORK_DEVICES_STATUS,
    GET_NETWORK_DEVICE_INFO,
    GET_NETWORK_DEVICE_INFO_BY_NAME,
    GET_NETWORK_DEVICE_STATUS,
    GET_NETWORK_IPMI_MANAGED_CPU,
    GET_NETWORK_IPMI_MANAGED_CPUS,
    GET_NETWORK_IPMI_MANAGED_ENCLOSURE,
    GET_NETWORK_IPMI_MANAGED_ENCLOSURES,
    GET_NEXT_GROUP_ID,
    GET_NEXT_MACHINE_ID,
    GET_NODES_STATUS,
    GET_SECURE_ERASE_STATUS,
    GET_SSH_STATUS,
    GET_STORAGE_DEVICES_STATUS,
    GET_STORAGE_DEVICE_INFO,
    GET_STORAGE_DEVICE_INFO_BY_NAME,
    GET_STORAGE_DEVICE_STATUS,
    GET_TOGGLE_P2P_FLAG_STATUS,
    GET_UNATTACHED_DEVICES_FOR_GROUP,
    GET_VERSIONS,
    HAS_CREDENTIALS,
    INITIALIZE,
    IS_LOGGED_IN,
    GROUP_POOL_DONE,
    GROUP_POOL_EDIT,
    LOGIN,
    LOGOUT,
    REBOOT_NODE,
    REMOVE_COMPUTE_DEVICE_FROM_GROUP,
    REMOVE_COMPUTE_DEVICE_FROM_MACHINE,
    REMOVE_DEVICE_FROM_MACHINE,
    REMOVE_DEVICE_FROM_GROUP,
    REMOVE_FPGA_DEVICE_FROM_GROUP,
    REMOVE_FPGA_DEVICE_FROM_MACHINE,
    REMOVE_GPU_DEVICE_FROM_GROUP,
    REMOVE_GPU_DEVICE_FROM_MACHINE,
    REMOVE_MEMORY_DEVICE_FROM_GROUP,
    REMOVE_MEMORY_DEVICE_FROM_MACHINE,
    REMOVE_NETWORK_DEVICE_FROM_GROUP,
    REMOVE_NETWORK_DEVICE_FROM_MACHINE,
    REMOVE_STORAGE_DEVICE_FROM_GROUP,
    REMOVE_STORAGE_DEVICE_FROM_MACHINE,
    REPROGRAM_FABRIC,
    RESET_STATE,
    RESTART_FABRIC,
    RESTART_HIERARCHY,
    RESTART_NODE,
    RESTART_SWITCH,
    SECURE_ERASE,
    SET_CREDENTIALS,
    SET_DEFAULT_COORDINATES,
    SET_LOGGING,
    SET_SSH_STATUS,
    SHUTDOWN,
    SHUTDOWN_AT,
    SHUTDOWN_NODE,
    START_NODE,
    SET_P2P_ENABLED,
//TODO    TOGGLE_DEVICE_FLAG_STATE,
}
