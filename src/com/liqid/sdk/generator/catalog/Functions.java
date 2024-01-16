//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.catalog;

import com.liqid.sdk.generator.discreteDataModels.*;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.functions.*;
import com.liqid.sdk.generator.liqidEntityModels.parameters.*;
import com.liqid.sdk.generator.liqidEntityModels.structs.StructMemberTarget;
import com.liqid.sdk.generator.writers.LanguageId;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  A static class which describes all the functions in the SDK.
 *  Adding a function requires the following steps:
 *      Add a corresponding FunctionId to FunctionId.java
 *      Add a private static Function here, describing the function.
 *      Add an entry to the CONTENT map in this class.
 *      Add a reference to the function (via FunctionId) to the LiqidClient struct in Structs.java
 *      Add a reference to the function (via FunctionId) to the containing Category definition in Categories.java
 */
class Functions {

    private Functions() {}

    //  Backup functions

    private static final Function BackupDirector =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.BACKUP_DIRECTOR)
            .setBaseName("BackupDirector")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("Destination")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.BACKUP_DESTINATION))
                              .addDescription("")
                              .build())
            .setResultDataDescriptor(new StructDataDescriptor(StructId.BACKUP_RESULT))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("backup")
            .addDescription("Retrieves the result of the backup process")
            .addResultDescription("Result of the backup process")
            .build();

    //  Client functions

    private static final Function ClearCredentials =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.CLEAR_CREDENTIALS)
            .setBaseName("ClearCredentials")
            .addDescription("Clears credentials set by a previous call to {function SetCredentials}.")
            .setResultDataDescriptor(new VoidDataDescriptor())
            .build();

    private static final Function HasCredentials =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.HAS_CREDENTIALS)
            .setBaseName("HasCredentials")
            .addDescription("Indicates whether credentials have been set by a previous call to {function SetCredentials}.")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.BOOLEAN))
            .addResultDescription("True if credentials have been set, false if they have not, or if they have been cleared.")
            .build();

    private static final Function Initialize =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.INITIALIZE)
            .setBaseName("Initialize")
            .addDescription("Initializes the internal state of the client struct.")
            .addDescription("This function must be invoked before calling any other client functions.")
            .setResultDataDescriptor(new VoidDataDescriptor())
            .setSpecificLanguageId(LanguageId.GO)
            .build();

    private static final Function IsLoggedIn =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.IS_LOGGED_IN)
            .setBaseName("IsLoggedIn")
            .addDescription("Indicates whether the client has successfully invoked {function Login}.")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.BOOLEAN))
            .addResultDescription("True if the client is logged in, else false.")
            .build();

    private static final Function LogIn =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.LOGIN)
            .setBaseName("Login")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("label")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("A client-unique identifier to be used in future API invocations.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("username")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("User name portion of the credentials to be used for logging in.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("password")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Password portion of the credentials to be used for logging in.")
                              .build())
            .addDescription("Establishes a session with the director, using the given credentials.")
            .addDescription("Subsequent REST API invocations will be accompanied by a security token which the director associates with this session.")
            .setResultDataDescriptor(new VoidDataDescriptor())
            .build();

    private static final Function LogOut =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.LOGOUT)
            .setBaseName("LogOut")
            .addDescription("Clears session information created during a previous invocation of {function LogIn}.")
            .setResultDataDescriptor(new VoidDataDescriptor())
            .build();

    private static final Function SetCredentials =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.SET_CREDENTIALS)
            .setBaseName("SetCredentials")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("username")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("User name portion of the credentials to be set")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("password")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Password portion of the credentials to be set")
                              .build())
            .addDescription("Establishes credentials to be used for all subsequent REST API invocations.")
            .setResultDataDescriptor(new VoidDataDescriptor())
            .build();

    private static final Function SetLogging =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.SET_LOGGING)
            .setBaseName("SetLogging")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("file")
                              .setDataDescriptor(new LanguageIntrinsicDataDescriptor(LanguageId.GO, "io.Writer"))
                              .addDescription("Destination for log entries - any io.Writer will do")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("flags")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Various flags supported by the go logging module.")
                              .addDescription("Values include log.Ldate, log.Ltime, etc")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("level")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Indicates the level for logging.")
                              .addDescription("This may be one of the following values, from the client module:")
                              .addDescription("  LogError, LogWarn, LogInfo, LogDebug, LogTrace")
                              .build())
            .addDescription("Establishes SDK logging attributes.")
            .setResultDataDescriptor(new VoidDataDescriptor())
            .setSpecificLanguageId(LanguageId.GO)
            .build();

    //  Coordinates functions

    private static final Function GetAvailableCoordinates =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_AVAILABLE_COORDINATES)
            .setBaseName("GetAvailableCoordinates")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.AVAILABLE_COORDINATES, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("coordinates/available")
            .addDescription("Retrieves a list of entities indicating the known values which may be employed for identifying particular Liqid entities")
            .addDescription("within a configuration. As a general rule, there will be only one such entry.")
            .addResultDescription("Array of all known available Liqid coordinates to which REST API invocations may be directed")
            .addImportRequirements(LanguageId.JAVA, "java.util.LinkedList")
            .build();

    private static final Function GetDefaultCoordinates =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_DEFAULT_COORDINATES)
            .setBaseName("GetDefaultCoordinates")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COORDINATES))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("coordinates")
            .addDescription("Retrieves the current default coordinates used for the various operations which are initiated by the REST API for the SDK.")
            .addResultDescription("Liqid coordinates to which all REST API invocations are directed")
            .build();

    private static final Function SetDefaultCoordinates =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.SET_DEFAULT_COORDINATES)
            .setBaseName("SetDefaultCoordinates")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COORDINATES))
            .setApiStructId(StructId.COORDINATES)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Rack")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .setIsOptional(true)
                              .addTarget(new StructMemberTarget("Rack"))
                              .addDescription("Rack component of the liqid coordinates for the REST API endpoint")
                              .addDescription("Should always be zero.")
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Shelf")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .setIsOptional(true)
                              .addTarget(new StructMemberTarget("Shelf"))
                              .addDescription("Shelf component of the liqid coordinates for the REST API endpoint")
                              .addDescription("Should always be zero.")
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Node")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addTarget(new StructMemberTarget("Node"))
                              .addDescription("Node component of the liqid coordinates for the REST API endpoint")
                              .build())
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("coordinates")
            .addDescription("Sets the default coordinates used for subsequent operations initiated via the SDK.")
            .addDescription("Specifying any set of values apart from what is available from {function GetAvailableCoordinates} produces undefined behavior.")
            .addResultDescription("Liqid coordinates to which all future REST API invocations are directed")
            .build();

    //  Device functions

    private static final Function CreateDeviceDescription =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.CREATE_DEVICE_DESCRIPTION)
            .setBaseName("CreateDeviceDescription")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DEVICE_USER_DESCRIPTION))
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("device/udesc")
            .setApiStructId(StructId.DEVICE_USER_DESCRIPTION)
            .addParameter(new PathParameter.Builder()
                              .setBaseName("QueryDeviceType")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.QUERY_DEVICE_TYPE))
                              .addDescription("Device type of the device to which a description should be applied")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32_AS_STRING))
                              .addDescription("Device ID of the device")
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Description")
                              .addTarget(new StructMemberTarget("UserDescription"))
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Description to be applied to the device")
                              .build())
            .addDescription("Creates a user-supplied device description for a particular device")
            .addResultDescription("User-supplied device description")
            .build();

    private static final Function DeleteDeviceDescription =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.DELETE_DEVICE_DESCRIPTION)
            .setBaseName("DeleteDeviceDescription")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DEVICE_USER_DESCRIPTION))
            .setRestMethod(HttpMethod.DELETE)
            .setPartialPath("device/udesc")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("QueryDeviceType")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.QUERY_DEVICE_TYPE))
                              .addDescription("Device type of the device to which a description should be applied")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32_AS_STRING))
                              .addDescription("Device ID")
                              .build())
            .addDescription("Deletes a user-supplied device description for a particular device")
            .addResultDescription("Deleted user-supplied device description")
            .build();

    private static final Function GetDeviceAttributes =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_DEVICE_ATTRIBUTES)
            .setBaseName("GetDeviceAttributes")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DEVICE_TYPE_AND_ATTRIBUTES, true))
            .addResultDescription("An array of {struct DeviceTypeAndAttributes} entities")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/attributes")
            .addDescription("Retrieves available device attribute names for all device types")
            .build();

    private static final Function GetSecureEraseStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_SECURE_ERASE_STATUS)
            .setBaseName("GetSecureEraseStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.ASYNCHRONOUS_STATUS))
            .addResultDescription("A {struct AsynchronousStatus} entity describing the state of the indicated operation")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/erase/")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32_AS_STRING))
                              .addDescription("Device identifier returned by {function SecureErase}")
                              .build())
            .addDescription("Retrieves the status of an asynchronous secure erase operation")
            .build();

    private static final Function SecureErase =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.SECURE_ERASE)
            .setBaseName("SecureErase")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.ASYNCHRONOUS_INFO))
            .addResultDescription("Describes the asynchronous identifier associated with this operation")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("device")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32_AS_STRING))
                              .addDescription("The identifier of the drive to be securely erased.")
                              .addDescription("This is the hexadecimal identifier prefixed with '0x'")
                              .build())
            .addDescription("Securely erases a particular storage device.")
            .addDescription("This is an asynchronous operation - the function returns before the process is complete.")
            .build();

    //  TODO ToggleDeviceFlagState()

    //	Device discovery functions

    private static final Function GetDeviceCounters =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_AVAILABLE_COORDINATES)
            .setBaseName("GetDeviceCounters")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DEVICE_COUNTERS, false))
            .addResultDescription("contains counters per device type")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("devices/count")
            .addDescription("Retrieves counters of devices for each type of device")
            .build();

    //	Device info functions

    private static final Function GetComputeDeviceInfo =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_COMPUTE_DEVICE_INFO)
            .setBaseName("GetComputeDeviceInfo")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COMPUTE_DEVICE_INFO, true))
            .addResultDescription("An array of {struct ComputeDeviceInfo} entities describing all known compute devices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/cpu")
            .addDescription("Retrieves additional information for all known compute devices")
            .build();

    private static final Function GetComputeDeviceInfoByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_COMPUTE_DEVICE_INFO_BY_NAME)
            .setBaseName("GetComputeDeviceInfoByName")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COMPUTE_DEVICE_INFO))
            .addResultDescription("{struct ComputeDeviceInfo} entity for the requested compute device")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/cpu")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the device in question")
                              .build())
            .addDescription("Retrieves additional information for a particular compute device")
            .build();

    private static final Function GetFPGADeviceInfo =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_FPGA_DEVICE_INFO)
            .setBaseName("GetFPGADeviceInfo")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.FPGA_DEVICE_INFO, true))
            .addResultDescription("An array of {struct FPGADeviceInfo} entities describing all known FPGA devices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/fpga")
            .addDescription("Retrieves additional information for all known FPGA devices")
            .build();

    private static final Function GetFPGADeviceInfoByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_FPGA_DEVICE_INFO_BY_NAME)
            .setBaseName("GetFPGADeviceInfoByName")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.FPGA_DEVICE_INFO))
            .addResultDescription("{struct FPGADeviceInfo} entity for the requested device")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/fpga")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the device in question")
                              .build())
            .addDescription("Retrieves additional information for a particular FPGA device")
            .build();

    private static final Function GetGPUDeviceInfo =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_GPU_DEVICE_INFO)
            .setBaseName("GetGPUDeviceInfo")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GPU_DEVICE_INFO, true))
            .addResultDescription("An array of {struct GPUDeviceInfo} entities describing all known GPU devices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/gpu")
            .addDescription("Retrieves additional information for all known GPU devices")
            .build();

    private static final Function GetGPUDeviceInfoByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_GPU_DEVICE_INFO_BY_NAME)
            .setBaseName("GetGPUDeviceInfoByName")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GPU_DEVICE_INFO))
            .addResultDescription("{struct GPUDeviceInfo} entity for the requested device")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/gpu")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the device in question")
                              .build())
            .addDescription("Retrieves additional information for a particular GPU device")
            .build();

    private static final Function GetMemoryDeviceInfo =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MEMORY_DEVICE_INFO)
            .setBaseName("GetMemoryDeviceInfo")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MEMORY_DEVICE_INFO, true))
            .addResultDescription("An array of {struct MemoryDeviceInfo} entities describing all known Memory devices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/mem")
            .addDescription("Retrieves additional information for all known Memory devices")
            .build();

    private static final Function GetMemoryDeviceInfoByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MEMORY_DEVICE_INFO_BY_NAME)
            .setBaseName("GetMemoryDeviceInfoByName")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MEMORY_DEVICE_INFO))
            .addResultDescription("{struct MemoryDeviceInfo} entity for the requested device")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/mem")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the device in question")
                              .build())
            .addDescription("Retrieves additional information for a particular Memory device")
            .build();

    private static final Function GetNetworkDeviceInfo =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_DEVICE_INFO)
            .setBaseName("GetNetworkDeviceInfo")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_DEVICE_INFO, true))
            .addResultDescription("An array of {struct NetworkDeviceInfo} entities describing all known Network devices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/link")
            .addDescription("Retrieves additional information for all known Network devices")
            .build();

    private static final Function GetNetworkDeviceInfoByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_DEVICE_INFO_BY_NAME)
            .setBaseName("GetNetworkDeviceInfoByName")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_DEVICE_INFO))
            .addResultDescription("{struct NetworkDeviceInfo} entity for the requested device")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/link")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the device in question")
                              .build())
            .addDescription("Retrieves additional information for a particular Network device")
            .build();

    private static final Function GetStorageDeviceInfo =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_STORAGE_DEVICE_INFO)
            .setBaseName("GetStorageDeviceInfo")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.STORAGE_DEVICE_INFO, true))
            .addResultDescription("An array of {struct StorageDeviceInfo} entities describing all known Storage devices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/ssd")
            .addDescription("Retrieves additional information for all known Storage devices")
            .build();

    private static final Function GetStorageDeviceInfoByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_STORAGE_DEVICE_INFO_BY_NAME)
            .setBaseName("GetStorageDeviceInfoByName")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.STORAGE_DEVICE_INFO))
            .addResultDescription("{struct StorageDeviceInfo} entity for the requested device")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("device/info/ssd")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the device in question")
                              .build())
            .addDescription("Retrieves additional information for a particular Storage device")
            .build();

    //	Device status functions

    private static final Function GetDeviceStatus =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.GET_DEVICE_STATUS)
            .setBaseName("GetDeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DEVICE_STATUS))
            .addResultDescription("{struct DeviceStatus} entity for the requested device")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the requested device")
                              .build())
            .addDescription("Retrieves status for a particular device")
            .build();

    private static final Function GetAllDevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_ALL_DEVICES_STATUS)
            .setBaseName("GetAllDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status")
            .addDescription("Retrieves status for all devices")
            .addResultDescription("An array of {struct DeviceStatus} entities describing the status of all devices in the system")
            .build();

    private static final Function GetComputeDeviceStatus =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_COMPUTE_DEVICE_STATUS)
            .setBaseName("GetComputeDeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COMPUTE_DEVICE_STATUS))
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device in question")
                              .build())
            .addDescription("Retrieves status for a particular compute device")
            .addResultDescription("A {struct ComputeDeviceStatus} entity describing the status of the requested device")
            .setBaseFunctionId(FunctionId.GET_COMPUTE_DEVICES_STATUS)
            .addMemberBaseName("DeviceId")
            .build();

    private static final Function GetComputeDevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_COMPUTE_DEVICES_STATUS)
            .setBaseName("GetComputeDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COMPUTE_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/compute")
            .addQueryPreset("filter", PresetId.ALWAYS_FALSE)
            .addDescription("Retrieves status for compute devices")
            .addResultDescription("An array of {struct ComputeDeviceStatus} entities describing the status of compute devices in the system")
            .build();

    private static final Function GetComputeDevicesWithMultiplePortsStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_COMPUTE_DEVICES_WITH_MULTIPLE_PORTS_STATUS)
            .setBaseName("GetComputeDevicesStatusWithMultiplePortsStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COMPUTE_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/compute/parents")
            .addDescription("Retrieves status for compute devices")
            .addResultDescription("An array of {struct ComputeDeviceStatus} entities describing the status of compute devices in the system")
            .build();

    private static final Function GetFPGADeviceStatus =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_FPGA_DEVICE_STATUS)
            .setBaseName("GetFPGADeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.FPGA_DEVICE_STATUS))
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device in question")
                              .build())
            .addDescription("Retrieves status for a particular FPGA device")
            .addResultDescription("A {struct FPGADeviceStatus} entity describing the status of the requested device")
            .setBaseFunctionId(FunctionId.GET_FPGA_DEVICES_STATUS)
            .addMemberBaseName("DeviceId")
            .build();

    private static final Function GetFPGADevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_FPGA_DEVICES_STATUS)
            .setBaseName("GetFPGADevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.FPGA_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/fpga")
            .addQueryPreset("filter", PresetId.ALWAYS_FALSE)
            .addDescription("Retrieves status for FPGA devices")
            .addResultDescription("An array of {struct FPGADeviceStatus} entities describing the status of FPGA devices in the system")
            .build();

    private static final Function GetFreeComputeDevicesStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GET_FREE_COMPUTE_DEVICES_STATUS)
            .setBaseName("GetFreeComputeDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COMPUTE_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setApiStructId(StructId.COMPUTE_DEVICE_STATUS)
            .setPartialPath("status/compute")
            .addQueryPreset("filter", PresetId.ALWAYS_TRUE)
            .addDescription("Retrieves status for compute devices which are not assigned to a group or machine")
            .addResultDescription("An array of {struct ComputeDeviceStatus} entities describing the status of compute devices in the system")
            .build();

    private static final Function GetFreeFPGADevicesStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GET_FREE_FPGA_DEVICES_STATUS)
            .setBaseName("GetFreeFPGADevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.FPGA_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setApiStructId(StructId.FPGA_DEVICE_STATUS)
            .setPartialPath("status/fpga")
            .addQueryPreset("filter", PresetId.ALWAYS_TRUE)
            .addDescription("Retrieves status for compute FPGA which are not assigned to a group or machine")
            .addResultDescription("An array of {struct FPGADeviceStatus} entities describing the status of FPGA devices in the system")
            .build();

    private static final Function GetFreeGPUDevicesStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GET_FREE_GPU_DEVICES_STATUS)
            .setBaseName("GetFreeGPUDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GPU_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setApiStructId(StructId.GPU_DEVICE_STATUS)
            .setPartialPath("status/gpu")
            .addQueryPreset("filter", PresetId.ALWAYS_TRUE)
            .addDescription("Retrieves status for compute GPU which are not assigned to a group or machine")
            .addResultDescription("An array of {struct GPUDeviceStatus} entities describing the status of GPU devices in the system")
            .build();

    private static final Function GetFreeMemoryDevicesStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GET_FREE_MEMORY_DEVICES_STATUS)
            .setBaseName("GetFreeMemoryDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MEMORY_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setApiStructId(StructId.MEMORY_DEVICE_STATUS)
            .setPartialPath("status/mem")
            .addQueryPreset("filter", PresetId.ALWAYS_TRUE)
            .addDescription("Retrieves status for memory devices which are not assigned to a group or machine")
            .addResultDescription("An array of {struct MemoryDeviceStatus} entities describing the status of memory devices in the system")
            .build();

    private static final Function GetFreeNetworkDevicesStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GET_FREE_NETWORK_DEVICES_STATUS)
            .setBaseName("GetFreeNetworkDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setApiStructId(StructId.NETWORK_DEVICE_STATUS)
            .setPartialPath("status/network")
            .addQueryPreset("filter", PresetId.ALWAYS_TRUE)
            .addDescription("Retrieves status for network devices which are not assigned to a group or machine")
            .addResultDescription("An array of {struct NetworkDeviceStatus} entities describing the status of network devices in the system")
            .build();

    private static final Function GetFreeStorageDevicesStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GET_FREE_STORAGE_DEVICES_STATUS)
            .setBaseName("GetFreeStorageDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.STORAGE_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setApiStructId(StructId.STORAGE_DEVICE_STATUS)
            .setPartialPath("status/storage")
            .addQueryPreset("filter", PresetId.ALWAYS_TRUE)
            .addDescription("Retrieves status for storage devices which are not assigned to a group or machine")
            .addResultDescription("An array of {struct StorageDeviceStatus} entities describing the status of storage devices in the system")
            .build();

    private static final Function GetGPUDeviceStatus =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_GPU_DEVICE_STATUS)
            .setBaseName("GetGPUDeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GPU_DEVICE_STATUS))
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device in question")
                              .build())
            .addDescription("Retrieves status for a particular GPU device")
            .addResultDescription("A {struct GPUDeviceStatus} entity describing the status of the requested device")
            .setBaseFunctionId(FunctionId.GET_GPU_DEVICES_STATUS)
            .addMemberBaseName("DeviceId")
            .build();

    private static final Function GetGPUDevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_GPU_DEVICES_STATUS)
            .setBaseName("GetGPUDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GPU_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/gpu")
            .addQueryPreset("filter", PresetId.ALWAYS_FALSE)
            .addDescription("Retrieves status for GPU devices")
            .addResultDescription("An array of {struct GPUDeviceStatus} entities describing the status of GPU devices in the system")
            .build();

    private static final Function GetMemoryDeviceStatus =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_MEMORY_DEVICE_STATUS)
            .setBaseName("GetMemoryDeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MEMORY_DEVICE_STATUS))
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device in question")
                              .build())
            .addDescription("Retrieves status for a particular memory device")
            .addResultDescription("A {struct MemoryDeviceStatus} entity describing the status of the requested device")
            .setBaseFunctionId(FunctionId.GET_MEMORY_DEVICES_STATUS)
            .addMemberBaseName("DeviceId")
            .build();

    private static final Function GetMemoryDevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MEMORY_DEVICES_STATUS)
            .setBaseName("GetMemoryDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MEMORY_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/mem")
            .addQueryPreset("filter", PresetId.ALWAYS_FALSE)
            .addDescription("Retrieves status for memory devices")
            .addResultDescription("An array of {struct MemoryDeviceStatus} entities describing the status of memory devices in the system")
            .build();

    private static final Function GetNetworkDeviceStatus =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_DEVICE_STATUS)
            .setBaseName("GetNetworkDeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_DEVICE_STATUS))
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device in question")
                              .build())
            .addDescription("Retrieves status for a particular network device")
            .addResultDescription("A {struct NetworkDeviceStatus} entity describing the status of the requested device")
            .setBaseFunctionId(FunctionId.GET_NETWORK_DEVICES_STATUS)
            .addMemberBaseName("DeviceId")
            .build();

    private static final Function GetNetworkDevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_DEVICES_STATUS)
            .setBaseName("GetNetworkDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/network")
            .addQueryPreset("filter", PresetId.ALWAYS_FALSE)
            .addDescription("Retrieves status for network devices")
            .addResultDescription("An array of {struct NetworkDeviceStatus} entities describing the status of network devices in the system")
            .build();

    private static final Function GetStorageDeviceStatus =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_STORAGE_DEVICE_STATUS)
            .setBaseName("GetStorageDeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.STORAGE_DEVICE_STATUS))
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device in question")
                              .build())
            .addDescription("Retrieves status for a particular storage device")
            .addResultDescription("A {struct StorageDeviceStatus} entity describing the status of the requested device")
            .setBaseFunctionId(FunctionId.GET_STORAGE_DEVICES_STATUS)
            .addMemberBaseName("DeviceId")
            .build();

    private static final Function GetStorageDevicesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_STORAGE_DEVICES_STATUS)
            .setBaseName("GetStorageDevicesStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.STORAGE_DEVICE_STATUS, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("status/storage")
            .addQueryPreset("filter", PresetId.ALWAYS_FALSE)
            .addDescription("Retrieves status for all storage devices")
            .addResultDescription("An array of {struct StorageDeviceStatus} entities describing the status of storage devices in the system")
            .build();

    //	Enclosure functions
    //  TODO

    //  Fabric functions

    private final static Function ApplyFabricChanges =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.APPLY_FABRIC_CHANGES)
            .setBaseName("ApplyFabricChanges")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.FABRIC_TOGGLE_COMPOSITE))
            .addResultDescription("Describes the new toggle state")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("fabric")
            .setApiStructId(StructId.FABRIC_TOGGLE_COMPOSITE)
            .addStructPresetId(new StructMemberTarget("coordinates", "Rack"),
                               PresetId.CURRENT_COORDINATES_RACK)
            .addStructPresetId(new StructMemberTarget("coordinates", "Shelf"),
                               PresetId.CURRENT_COORDINATES_SHELF)
            .addStructPresetId(new StructMemberTarget("coordinates", "Node"),
                               PresetId.CURRENT_COORDINATES_NODE)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("FlagName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addTarget(new StructMemberTarget("ControlFlag", "Name"))
                              .addDescription("The name of the flag to be added")
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("FlagValue")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addTarget(new StructMemberTarget("ControlFlag", "ValueString"))
                              .addDescription("The value for the flag to be added")
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Operation")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.FABRIC_TOGGLE_COMPOSITE_OPTION))
                              .addTarget(new StructMemberTarget("Options"))
                              .addDescription("Currently, only ADD is supported")
                              .build())
            .addDescription("applies changes to the fabric - currently one may only add a particular flag with a value")
            .build();

    private static final Function CancelEditFabric =
        new GetPostPutFunction.Builder()
            .setFunctionId(FunctionId.CANCEL_EDIT_FABRIC)
            .setBaseName("CancelEditFabric")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription(("Unique id of the machine for which edit mode is to be canceled."))
                              .build())
            .setGetterFunctionId(FunctionId.GET_MACHINE)
            .setPartialPath("fabric/edit/cancel")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the machine of interest")
            .addDescription("Cancels fabric edit mode - reverts all pending changes made to device connections")
            .build();

    private static final Function CancelReprogramFabric =
        new GetPostPutFunction.Builder()
            .setFunctionId(FunctionId.CANCEL_REPROGRAM_FABRIC)
            .setBaseName("CancelReprogramFabric")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription(("Unique id of the machine for which fabric reprogramming is to be canceled."))
                              .build())
            .setGetterFunctionId(FunctionId.GET_MACHINE)
            .setPartialPath("fabric/reprogram/cancel")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the machine of interest")
            .addDescription("Cancels fabric reprogram mode")
            .build();

    private static final Function EditFabric =
        new GetPostPutFunction.Builder()
            .setFunctionId(FunctionId.EDIT_FABRIC)
            .setBaseName("EditFabric")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription(("Unique id of the machine for which edit mode is to be established."))
                              .build())
            .setGetterFunctionId(FunctionId.GET_MACHINE)
            .setPartialPath("fabric/edit")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the machine of interest")
            .addDescription("enters fabric edit mode which allows the fabric to be electrically connected")
            .addDescription(" - the system must be put into edit mode before a device is added to a machine")
            .build();

    private static final Function GetCurrentFabricId =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_CURRENT_FABRIC_ID)
            .setBaseName("GetCurrentFabricId")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("fabric/id")
            .addDescription("Retrieves the fabric id associated with the current default coordinates.")
            .addDescription("This is not generally needed for SDK interactions, but may be useful for troubleshooting.")
            .addResultDescription("The fabric id which is managed by the connected Director.")
            .build();

    private static final Function GetFabricTopology =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_FABRIC_TOPOLOGY)
            .setBaseName("GetFabricTopology")
            .setResultDataDescriptor(new TypedefDataDescriptor(TypedefId.FABRIC_TOPOLOGY))
            .addResultDescription("A {typedef FabricTopology} entity")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("fabric/topology")
            .addDescription("Returns the current fabric topology")
            .build();

    private static final Function GetFabricTypes =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_FABRIC_TYPES)
            .setBaseName("GetFabricTypes")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NAME_VALUE_PAIR, true))
            .addResultDescription("An array of fabric types and identifiers")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("fabric/types")
            .addDescription("Returns all known fabric types and associated id values")
            .build();

    private static final Function ReprogramFabric =
        new GetPostPutFunction.Builder()
            .setFunctionId(FunctionId.REPROGRAM_FABRIC)
            .setBaseName("ReprogramFabric")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription(("Unique id of the machine for which fabric reprogramming is to be accomplished."))
                              .build())
            .setGetterFunctionId(FunctionId.GET_MACHINE)
            .setPartialPath("fabric/reprogram")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity")
            .addDescription("Reprogram the fabric. This will result in devices associated with a machine being electrically connected to the machine.")
            .addDescription("This step MUST be done in order for a device to be added to a machine.")
            .build();

    //  Group functions

    private static final Function ClearGroups =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.CLEAR_GROUPS)
            .setBaseName("ClearGroups")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.BOOLEAN))
            .addResultDescription("Returns true if successful, throws an error otherwise.")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("group/clear")
            .addDescription("Deletes all groups and all machines within those groups.")
            .addDescription("Restores all devices to the system free pool.")
            .addDescription("This is effectively a soft configuration reset which does not rediscover devices.")
            .build();

    private static final Function CreateGroup =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.CREATE_GROUP)
            .setBaseName("CreateGroup")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP, false))
            .addResultDescription("A {struct Group} entity which describes the newly-created group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Unique name of the group")
                              .build())
            .addDescription("Creates a new group within the current fabric.")
            .build();

    private static final Function CreateGroupWithId =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.CREATE_GROUP_WITH_ID)
            .setBaseName("CreateGroupWithId")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP, false))
            .addResultDescription("A {struct Group} entity which describes the newly-created group")
            .setRestMethod(HttpMethod.POST)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("GroupId")
                              .addTarget(new StructMemberTarget("GroupId"))
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier to be associated with the group")
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("GroupName")
                              .addTarget(new StructMemberTarget("GroupName"))
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Unique name of the group")
                              .build())
            .addStructPresetId(new StructMemberTarget("FabricId"), PresetId.CURRENT_FABRIC_ID)
            .setApiStructId(StructId.GROUP)
            .setPartialPath("group")
            .addDescription("Creates a new group within the current fabric.")
            .addDeprecatedMessage("SDK Clients should use {function CreateGroup} instead, which does not require that the client specify a group id.")
            .build();

    private static final Function DeleteGroup =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.DELETE_GROUP)
            .setBaseName("DeleteGroup")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP))
            .addResultDescription("Returns a {struct Group} item describing the deleted group")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to be deleted")
                              .build())
            .setRestMethod(HttpMethod.DELETE)
            .setPartialPath("group")
            .addDescription("Deletes a configured group along with all the machines in the group.")
            .addDescription("Returns all devices related to the group or to the machines in the group to the system free pool.")
            .build();

    private static final Function GetGroup =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP)
            .setBaseName("GetGroup")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP))
            .addResultDescription("A {struct Group} entity describing the indicated group")
            .setBaseFunctionId(FunctionId.GET_GROUPS)
            .addMemberBaseName("GroupId")
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group in question")
                              .build())
            .addDescription("Retrieves a particular group given its identifier")
            .build();

    private static final Function GetGroups =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUPS)
            .setBaseName("GetGroups")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP, true))
            .addResultDescription("An array of {struct Group} entities describing the configured groups")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("group")
            .addDescription("Retrieves all configured groups")
            .build();

    private static final Function GetGroupDetails =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_DETAILS)
            .setBaseName("GetGroupDetails")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_DETAILS))
            .addResultDescription("A {struct GroupDetails} entity containing details for the indicated group")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group of interest")
                              .build())
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("group/details")
            .addDescription("Retrieves details regarding a particular configured group")
            .build();

    private static final Function GetGroupIdByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_ID_BY_NAME)
            .setBaseName("GetGroupIdByName")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
            .addResultDescription("Returns the unique identifier of the indicated group")
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("GroupName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .setQueryTag("group-name")
                              .addDescription("Name of the group of interest")
                              .build())
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("group/mapper")
            .addDescription("Retrieves the unique identifier of a configured group given its name")
            .build();

    private static final Function GetNextGroupId =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NEXT_GROUP_ID)
            .setBaseName("GetNextGroupId")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
            .addResultDescription("The next sequential unused group identifier")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("group/nextid")
            .addDescription("Retrieves the next sequential unused group identifier")
            .addDeprecatedMessage("Use of this function in conjunction with {function CreateGroupWithId} opens a potential race condition.")
            .addDeprecatedMessage("This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.")
            .addDeprecatedMessage("A future version of the Director will provide a means of creating a group whereby the group id is internally generated.")
            .addDeprecatedMessage("For this reason, SDK clients are encouraged to use the {function CreateGroup} function which wraps the Get/Create mechanism in one SDK function call.")
            .addDeprecatedMessage("For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of {function CreateGroupWithId} and {function GetNextGroupId}.")
            .build();

    //	Group device relator functions

    private static final Function AddComputeDeviceToGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_COMPUTE_DEVICE_TO_GROUP)
            .setBaseName("AddComputeDeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be added to a group free pool.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the device is to be added.")
                              .build())
            .setCompositeStructId(StructId.GROUP_COMPUTE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_COMPUTE_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/compute")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_COMPUTE_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function AddDeviceToGroup =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.ADD_DEVICE_TO_GROUP)
            .setBaseName("AddDeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the device is to be added")
                              .build())
            .build();

    private static final Function AddFPGADeviceToGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_FPGA_DEVICE_TO_GROUP)
            .setBaseName("AddFPGADeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be added to a group free pool.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the device is to be added.")
                              .build())
            .setCompositeStructId(StructId.GROUP_FPGA_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_FPGA_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/fpga")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_FPGA_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .setRestMethod(HttpMethod.POST)
            .build();

    private static final Function AddGPUDeviceToGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_GPU_DEVICE_TO_GROUP)
            .setBaseName("AddGPUDeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be added to a group free pool.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the device is to be added.")
                              .build())
            .setCompositeStructId(StructId.GROUP_GPU_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GPU_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/gpu")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_GPU_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .setRestMethod(HttpMethod.POST)
            .build();

    private static final Function AddMemoryDeviceToGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_MEMORY_DEVICE_TO_GROUP)
            .setBaseName("AddMemoryDeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be added to a group free pool.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the device is to be added.")
                              .build())
            .setCompositeStructId(StructId.GROUP_MEMORY_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_MEMORY_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/mem")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_MEMORY_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .setRestMethod(HttpMethod.POST)
            .build();

    private static final Function AddNetworkDeviceToGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_NETWORK_DEVICE_TO_GROUP)
            .setBaseName("AddNetworkDeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be added to a group free pool.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the device is to be added.")
                              .build())
            .setCompositeStructId(StructId.GROUP_NETWORK_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_NETWORK_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/network")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_NETWORK_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .setRestMethod(HttpMethod.POST)
            .build();

    private static final Function AddStorageDeviceToGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_STORAGE_DEVICE_TO_GROUP)
            .setBaseName("AddStorageDeviceToGroup")
            .addDescription("Moves a device from the system free pool to the group free pool for the indicated group")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be added to a group free pool.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the device is to be added.")
                              .build())
            .setCompositeStructId(StructId.GROUP_STORAGE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_STORAGE_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/storage")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_STORAGE_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .setRestMethod(HttpMethod.POST)
            .build();

    private static final Function GetDevices =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_DEVICES)
            .setBaseName("GetDevices")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("predevice")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.PRE_DEVICE, true))
            .addResultDescription("An array of {struct PreDevice} entities describing the various devices in the configuration")
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("QueryDeviceType")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.QUERY_DEVICE_TYPE))
                              .setQueryTag("dev_type")
                              .setIsOptional(true)
                              .addDescription("Limits the device type of the devices to be queried")
                              .build())
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .setQueryTag("grp_id")
                              .setIsOptional(true)
                              .addDescription("Only return devices associated with the indicated group")
                              .build())
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .setQueryTag("mach_id")
                              .setIsOptional(true)
                              .addDescription("Only return devices associated with the indicated machine")
                              .build())
            .addDescription("Returns information regarding all devices (or a subset thereof) for the system")
            .build();

    private static final Function GetGroupComputeDeviceRelator =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_COMPUTE_DEVICE_RELATOR)
            .setBaseName("GetGroupComputeDeviceRelator")
            .addDescription("This function is required by the SDK architecture.")
            .addDescription("It is not intended for use by the SDK client.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related group")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related device")
                              .build())
            .setCompositeStructId(StructId.GROUP_COMPUTE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .addGetterLink(FunctionId.GET_COMPUTE_DEVICE_STATUS, "DeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_COMPUTE_DEVICE_RELATOR))
            .addResultDescription("A {struct GroupComputeDeviceRelator} entity")
            .build();

    private static final Function GetGroupFPGADeviceRelator =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_FPGA_DEVICE_RELATOR)
            .setBaseName("GetGroupFPGADeviceRelator")
            .addDescription("This function is required by the SDK architecture.")
            .addDescription("It is not intended for use by the SDK client.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related group")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related device")
                              .build())
            .setCompositeStructId(StructId.GROUP_FPGA_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .addGetterLink(FunctionId.GET_FPGA_DEVICE_STATUS, "DeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_FPGA_DEVICE_RELATOR))
            .addResultDescription("A {struct GroupFPGADeviceRelator} entity")
            .build();

    private static final Function GetGroupGPUDeviceRelator =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_GPU_DEVICE_RELATOR)
            .setBaseName("GetGroupGPUDeviceRelator")
            .addDescription("This function is required by the SDK architecture.")
            .addDescription("It is not intended for use by the SDK client.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related group")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related device")
                              .build())
            .setCompositeStructId(StructId.GROUP_GPU_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .addGetterLink(FunctionId.GET_GPU_DEVICE_STATUS, "DeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_GPU_DEVICE_RELATOR))
            .addResultDescription("A {struct GroupGPUDeviceRelator} entity")
            .build();

    private static final Function GetGroupMemoryDeviceRelator =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_MEMORY_DEVICE_RELATOR)
            .setBaseName("GetGroupMemoryDeviceRelator")
            .addDescription("This function is required by the SDK architecture.")
            .addDescription("It is not intended for use by the SDK client.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related group")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related device")
                              .build())
            .setCompositeStructId(StructId.GROUP_MEMORY_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .addGetterLink(FunctionId.GET_MEMORY_DEVICE_STATUS, "DeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_MEMORY_DEVICE_RELATOR))
            .addResultDescription("A {struct GroupMemoryDeviceRelator} entity")
            .build();

    private static final Function GetGroupNetworkDeviceRelator =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_NETWORK_DEVICE_RELATOR)
            .setBaseName("GetGroupNetworkDeviceRelator")
            .addDescription("This function is required by the SDK architecture.")
            .addDescription("It is not intended for use by the SDK client.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related group")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related device")
                              .build())
            .setCompositeStructId(StructId.GROUP_NETWORK_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .addGetterLink(FunctionId.GET_NETWORK_DEVICE_STATUS, "DeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_NETWORK_DEVICE_RELATOR))
            .addResultDescription("A {struct GroupNetworkDeviceRelator} entity")
            .build();

    private static final Function GetGroupStorageDeviceRelator =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.GET_GROUP_STORAGE_DEVICE_RELATOR)
            .setBaseName("GetGroupStorageDeviceRelator")
            .addDescription("This function is required by the SDK architecture.")
            .addDescription("It is not intended for use by the SDK client.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related group")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the related device")
                              .build())
            .setCompositeStructId(StructId.GROUP_STORAGE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .addGetterLink(FunctionId.GET_STORAGE_DEVICE_STATUS, "DeviceStatus")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_STORAGE_DEVICE_RELATOR))
            .addResultDescription("A {struct GroupStorageDeviceRelator} entity")
            .build();

    private static final Function GetUnattachedDevicesForGroup =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.GET_UNATTACHED_DEVICES_FOR_GROUP)
            .setBaseName("GetUnattachedDevicesForGroup")
            .addDescription("Retrieves a list of devices which are assigned to a group free pool.")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceStatusType")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.DEVICE_TYPE))
                              .addDescription("Indicates the type of the devices of interest.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group of interest.")
                              .build())
            .build();

    private static final Function RemoveComputeDeviceFromGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_COMPUTE_DEVICE_FROM_GROUP)
            .setBaseName("RemoveComputeDeviceFromGroup")
            .addDescription("Moves a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .setCompositeStructId(StructId.GROUP_COMPUTE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_COMPUTE_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/compute")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_COMPUTE_DEVICE_RELATOR))
            .addResultDescription("A description of the deleted relation")
            .build();

    private static final Function RemoveDeviceFromGroup =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_DEVICE_FROM_GROUP)
            .setBaseName("RemoveDeviceFromGroup")
            .addDescription("Removes a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .build();

    private static final Function RemoveFPGADeviceFromGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_FPGA_DEVICE_FROM_GROUP)
            .setBaseName("RemoveFPGADeviceFromGroup")
            .addDescription("Moves a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .setCompositeStructId(StructId.GROUP_FPGA_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_FPGA_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/fpga")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_FPGA_DEVICE_RELATOR))
            .addResultDescription("A description of the deleted relation")
            .build();

    private static final Function RemoveGPUDeviceFromGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_GPU_DEVICE_FROM_GROUP)
            .setBaseName("RemoveGPUDeviceFromGroup")
            .addDescription("Moves a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .setCompositeStructId(StructId.GROUP_GPU_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GPU_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/gpu")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_GPU_DEVICE_RELATOR))
            .addResultDescription("A description of the deleted relation")
            .build();

    private static final Function RemoveMemoryDeviceFromGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_MEMORY_DEVICE_FROM_GROUP)
            .setBaseName("RemoveMemoryDeviceFromGroup")
            .addDescription("Moves a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .setCompositeStructId(StructId.GROUP_MEMORY_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_MEMORY_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/mem")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_MEMORY_DEVICE_RELATOR))
            .addResultDescription("A description of the deleted relation")
            .build();

    private static final Function RemoveNetworkDeviceFromGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_NETWORK_DEVICE_FROM_GROUP)
            .setBaseName("RemoveNetworkDeviceFromGroup")
            .addDescription("Moves a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .setCompositeStructId(StructId.GROUP_NETWORK_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_NETWORK_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/network")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_NETWORK_DEVICE_RELATOR))
            .addResultDescription("A description of the deleted relation")
            .build();

    private static final Function RemoveStorageDeviceFromGroup =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_STORAGE_DEVICE_FROM_GROUP)
            .setBaseName("RemoveStorageDeviceFromGroup")
            .addDescription("Moves a device from the group free pool to the system free pool")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the device to be removed from the group.")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group from which the device is to be removed.")
                              .build())
            .setCompositeStructId(StructId.GROUP_STORAGE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_STORAGE_DEVICE_STATUS, "DeviceStatus")
            .addGetterLink(FunctionId.GET_GROUP, "Group")
            .setPartialPath("predevice/storage")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_STORAGE_DEVICE_RELATOR))
            .addResultDescription("A description of the deleted relation")
            .build();

    //	Group pool functions

    private static final Function CancelGroupPoolEdit =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.CANCEL_GROUP_POOL_EDIT)
            .setBaseName("CancelGroupPoolEdit")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("pool/cancel")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_POOL))
            .addResultDescription("A {struct Group} entity describing the group pool")
            .setApiStructId(StructId.GROUP_POOL)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addTarget(new StructMemberTarget("GroupId"))
                              .addDescription("Identifier of the group for which edit mode is to be canceled")
                              .build())
            .addStructPresetId(new StructMemberTarget("Coordinates", "Rack"), PresetId.CURRENT_COORDINATES_RACK)
            .addStructPresetId(new StructMemberTarget("Coordinates", "Shelf"), PresetId.CURRENT_COORDINATES_SHELF)
            .addStructPresetId(new StructMemberTarget("Coordinates", "Node"), PresetId.CURRENT_COORDINATES_NODE)
            .addStructPresetId(new StructMemberTarget("FabricId"), PresetId.CURRENT_FABRIC_ID)
            .addDescription("Cancels a pending group pool edit operation.")
            .addDescription("All pending device attachments or detachments will be canceled.")
            .build();

    private static final Function GroupPoolDone =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GROUP_POOL_DONE)
            .setBaseName("GroupPoolDone")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("pool/done")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_POOL))
            .addResultDescription("A {struct Group} entity describing the group pool")
            .setApiStructId(StructId.GROUP_POOL)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addTarget(new StructMemberTarget("GroupId"))
                              .addDescription("Identifier of the group for which edit mode is to be completed")
                              .build())
            .addStructPresetId(new StructMemberTarget("Coordinates", "Rack"), PresetId.CURRENT_COORDINATES_RACK)
            .addStructPresetId(new StructMemberTarget("Coordinates", "Shelf"), PresetId.CURRENT_COORDINATES_SHELF)
            .addStructPresetId(new StructMemberTarget("Coordinates", "Node"), PresetId.CURRENT_COORDINATES_NODE)
            .addStructPresetId(new StructMemberTarget("FabricId"), PresetId.CURRENT_FABRIC_ID)
            .addDescription("Completes a pending group pool edit operation.")
            .addDescription("All pending device attachments or detachments are finalized.")
            .build();

    private static final Function GroupPoolEdit =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.GROUP_POOL_EDIT)
            .setBaseName("GroupPoolEdit")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("pool/edit")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_POOL))
            .addResultDescription("A {struct Group} entity describing the group pool")
            .setApiStructId(StructId.GROUP_POOL)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addTarget(new StructMemberTarget("GroupId"))
                              .addDescription("Identifier of the group for which edit mode is to be established")
                              .build())
            .addStructPresetId(new StructMemberTarget("Coordinates", "Rack"), PresetId.CURRENT_COORDINATES_RACK)
            .addStructPresetId(new StructMemberTarget("Coordinates", "Shelf"), PresetId.CURRENT_COORDINATES_SHELF)
            .addStructPresetId(new StructMemberTarget("Coordinates", "Node"), PresetId.CURRENT_COORDINATES_NODE)
            .addStructPresetId(new StructMemberTarget("FabricId"), PresetId.CURRENT_FABRIC_ID)
            .addDescription("Establishes edit mode for a particular group pool.")
            .addDescription("Must be invoked before adding devices to or removing devices from a group free pool.")
            .build();

    //	Health check functions
    //  TODO

    //	Image controller functions
    //  TODO

    //	kubernetes management controller API will not be implemented

    //	license controller will not be implemented

    //  Deployment functions
    //  TODO

    //  Roles functions
    //  TODO

    //  Log collector functions
    //  TODO

    //  Machine functions
    private static final Function CreateMachine =
            new HardCodedFunction.Builder()
                    .setFunctionId(FunctionId.CREATE_MACHINE)
                    .setBaseName("CreateMachine")
                    .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE, false))
                    .addResultDescription("A {struct Machine} entity which describes the newly-created machine")
                    .addParameter(new SimpleParameter.Builder()
                            .setBaseName("GroupId")
                            .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                            .addDescription("Unique identifier of the group to which the machine should belong")
                            .build())
                    .addParameter(new SimpleParameter.Builder()
                            .setBaseName("MachineName")
                            .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                            .addDescription("Unique name of the machine")
                            .build())
                    .addDescription("Creates a new machine within the current fabric and group.")
                    .build();

    private static final Function CreateMachineWithId =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.CREATE_MACHINE_WITH_ID)
            .setBaseName("CreateMachineWithId")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("machine")
            .setApiStructId(StructId.MACHINE)
            .addStructPresetId(new StructMemberTarget("FabricId"), PresetId.CURRENT_FABRIC_ID)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the created machine")
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier of the group to which the machine should be attached")
                              .addTarget(new StructMemberTarget("GroupId"))
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier to be applied to the machine")
                              .addTarget(new StructMemberTarget("MachineId"))
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("MachineName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name for the newly-created machine")
                              .addTarget(new StructMemberTarget("MachineName"))
                              .addTarget(new StructMemberTarget("ComputeName"))
                              .build())
            .addDescription("Creates a new machine for a particular group")
            .addDeprecatedMessage("SDK Clients should use {function CreateMachine} instead, which does not require that the client specify a machine id.")
            .build();

    private static final Function DeleteMachine =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.DELETE_MACHINE)
            .setBaseName("DeleteMachine")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("a {struct Machine} entity describing the deleted machine")
            .setRestMethod(HttpMethod.DELETE)
            .setPartialPath("machine")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to be deleted")
                              .build())
            .addDescription("Deletes a configured machine, returning its attached devices to the containing group free pool")
            .build();

    private static final Function GetMachines =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MACHINES)
            .setBaseName("GetMachines")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE, true))
            .addResultDescription("An array of {struct Machine} entities")
            .addDescription("Retrieves information about all the configured machines")
            .build();

    private static final Function GetMachinesAtCoordinates =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MACHINES)
            .setBaseName("GetMachinesAtCoordinates")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE, true))
            .addResultDescription("An array of {struct Machine} entities")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("RackId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Rack component of Liqid Coordinates of interest")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("ShelfId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Shelf component of Liqid Coordinates of interest")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("NodeId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Node component of Liqid Coordinates of interest")
                              .build())
            .addDescription("Retrieves information about all the configured machines")
            .build();

    private static final Function GetMachinesByGroupId =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MACHINES_BY_GROUP_ID)
            .setBaseName("GetMachinesByGroupId")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE, true))
            .addResultDescription("An array of {struct Machine} entities")
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .setQueryTag("grp_id")
                              .addDescription("Unique group identifier")
                              .build())
            .addDescription("Retrieves information about all the configured machines in a particular group")
            .build();

    private static final Function GetMachine =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MACHINE)
            .setBaseName("GetMachine")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity")
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .setQueryTag("mach_id")
                              .addDescription("Unique machine identifier")
                              .build())
            .addDescription("Retrieves information about a configured machine given the machine identifier")
            .build();

    private static final Function GetMachineByName =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MACHINE_BY_NAME)
            .setBaseName("GetMachineByName")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity")
            .addParameter(new QueryParameter.Builder()
                              .setBaseName("MachineName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .setQueryTag("mach_name")
                              .addDescription("Unique machine name")
                              .build())
            .addDescription("Retrieves information about a configured machine given the machine name")
            .build();

    private static final Function GetMachineDetails =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MACHINE_DETAILS)
            .setBaseName("GetMachineDetails")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE_DETAILS))
            .addResultDescription("A {struct MachineDetails} entity describing details for the machine")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine/details")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Identifier for the machine")
                              .build())
            .addDescription("Retrieves details for a particular machine")
            .build();

    private static final Function GetNextMachineId =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NEXT_MACHINE_ID)
            .setBaseName("GetNextMachineId")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32_AS_DECIMAL_STRING))
            .addResultDescription("The next sequential unused machine identifier")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("machine/nextid")
            .addDescription("Retrieves the next sequential unused machine identifier")
            .addDeprecatedMessage("Use of this function in conjunction with {function CreateMachineWithId} opens a potential race condition.")
            .addDeprecatedMessage("This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.")
            .addDeprecatedMessage("A future version of the Director will provide a means of creating a machine whereby the machine id is internally generated.")
            .addDeprecatedMessage("For this reason, SDK clients are encouraged to use the {function CreateMachine} function which wraps the Get/Create mechanism in one SDK function call.")
            .addDeprecatedMessage("For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of {function CreateMachineWithId} and {function GetNextMachineId}.")
            .addImportRequirements(LanguageId.GO, "strconv")
            .build();

    private static final Function SetP2PEnabled =
        new GetPostPutFunction.Builder()
            .setFunctionId(FunctionId.SET_P2P_ENABLED)
            .setBaseName("SetP2PEnabled")
            .setGetterFunctionId(FunctionId.GET_MACHINE)
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription(("Unique id of the machine for which P2P is to be enabled or disabled."))
                              .build())
            .addParameter(new BodyParameter.Builder()
                    .setBaseName("P2PEnabled")
                    .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.P2P_TYPE))
                    .addDescription("Value to be set for the P2P flag.")
                    .build())
            .setPartialPath("machine/p2p")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("a {struct Machine} entity describing the affected machine")
            .addDescription("Sets or clears the P2P flag for the indicated machine.")
            .addDescription("Only effective for powered-on machines having at least two GPUs.")
            .build();

    //  Machine device relator functions

    private static final Function AddComputeDeviceToMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_COMPUTE_DEVICE_TO_MACHINE)
            .setBaseName("AddComputeDeviceToMachine")
            .addDescription("Attaches a particular device to a machine")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .setCompositeStructId(StructId.MACHINE_COMPUTE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_COMPUTE_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/compute")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_COMPUTE_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function AddDeviceToMachine =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.ADD_DEVICE_TO_MACHINE)
            .setBaseName("AddDeviceToMachine")
            .addDescription("Adds a device to the indicated machine from the group to which the machine belongs")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .build();

    private static final Function AddFPGADeviceToMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_FPGA_DEVICE_TO_MACHINE)
            .setBaseName("AddFPGADeviceToMachine")
            .addDescription("Attaches a particular device to a machine")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .setCompositeStructId(StructId.MACHINE_FPGA_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_FPGA_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/fpga")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_FPGA_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function AddGPUDeviceToMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_GPU_DEVICE_TO_MACHINE)
            .setBaseName("AddGPUDeviceToMachine")
            .addDescription("Attaches a particular device to a machine")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .setCompositeStructId(StructId.MACHINE_GPU_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_GPU_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/gpu")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_GPU_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function AddMemoryDeviceToMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_MEMORY_DEVICE_TO_MACHINE)
            .setBaseName("AddMemoryDeviceToMachine")
            .addDescription("Attaches a particular device to a machine")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .setCompositeStructId(StructId.MACHINE_MEMORY_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_MEMORY_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/memory")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_MEMORY_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function AddNetworkDeviceToMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_NETWORK_DEVICE_TO_MACHINE)
            .setBaseName("AddNetworkDeviceToMachine")
            .addDescription("Attaches a particular device to a machine")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .setCompositeStructId(StructId.MACHINE_NETWORK_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_NETWORK_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/network")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_NETWORK_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function AddStorageDeviceToMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.ADD_STORAGE_DEVICE_TO_MACHINE)
            .setBaseName("AddStorageDeviceToMachine")
            .addDescription("Attaches a particular device to a machine")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be added")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine to which the device is to be added")
                              .build())
            .setCompositeStructId(StructId.MACHINE_STORAGE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_STORAGE_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/storage")
            .setRestMethod(HttpMethod.POST)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_STORAGE_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being created")
            .build();

    private static final Function RemoveComputeDeviceFromMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_COMPUTE_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveComputeDeviceFromMachine")
            .addDescription("Detaches a particular device from a machine, returning it to the group free pool")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to which the containing machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the machine from which the device is to be removed")
                              .build())
            .setCompositeStructId(StructId.MACHINE_COMPUTE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_COMPUTE_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/compute")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_COMPUTE_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being removed")
            .build();

    private static final Function RemoveDeviceFromMachine =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveDeviceFromMachine")
            .addDescription("Removes a device from the machine to which it is attached")
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the group to which the machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier for the machine from which the device is to be removed")
                              .build())
            .build();

    private static final Function RemoveFPGADeviceFromMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_FPGA_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveFPGADeviceFromMachine")
            .addDescription("Detaches a particular device from a machine, returning it to the group free pool")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to which the containing machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the machine from which the device is to be removed")
                              .build())
            .setCompositeStructId(StructId.MACHINE_FPGA_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_FPGA_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/fpga")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_FPGA_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being removed")
            .build();

    private static final Function RemoveGPUDeviceFromMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_GPU_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveGPUDeviceFromMachine")
            .addDescription("Detaches a particular device from a machine, returning it to the group free pool")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to which the containing machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the machine from which the device is to be removed")
                              .build())
            .setCompositeStructId(StructId.MACHINE_GPU_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_GPU_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/gpu")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_GPU_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being removed")
            .build();

    private static final Function RemoveMemoryDeviceFromMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_MEMORY_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveMemoryDeviceFromMachine")
            .addDescription("Detaches a particular device from a machine, returning it to the group free pool")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to which the containing machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the machine from which the device is to be removed")
                              .build())
            .setCompositeStructId(StructId.MACHINE_MEMORY_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_MEMORY_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/memory")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_MEMORY_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being removed")
            .build();

    private static final Function RemoveNetworkDeviceFromMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_NETWORK_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveNetworkDeviceFromMachine")
            .addDescription("Detaches a particular device from a machine, returning it to the group free pool")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to which the containing machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the machine from which the device is to be removed")
                              .build())
            .setCompositeStructId(StructId.MACHINE_NETWORK_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_NETWORK_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/network")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_NETWORK_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being removed")
            .build();

    private static final Function RemoveStorageDeviceFromMachine =
        new CompositeStructFunction.Builder()
            .setFunctionId(FunctionId.REMOVE_STORAGE_DEVICE_FROM_MACHINE)
            .setBaseName("RemoveStorageDeviceFromMachine")
            .addDescription("Detaches a particular device from a machine, returning it to the group free pool")
            // for documentation only
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("DeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the device to be removed")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group to which the containing machine belongs")
                              .build())
            .addParameter(new SimpleParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the machine from which the device is to be removed")
                              .build())
            .setCompositeStructId(StructId.MACHINE_STORAGE_DEVICE_RELATOR)
            .addGetterLink(FunctionId.GET_GROUP_STORAGE_DEVICE_RELATOR, "GroupDeviceRelator")
            .addGetterLink(FunctionId.GET_MACHINE, "Machine")
            .setPartialPath("relate/storage")
            .setRestMethod(HttpMethod.DELETE)
            .setResultDataDescriptor(new StructDataDescriptor(StructId.GROUP_STORAGE_DEVICE_RELATOR))
            .addResultDescription("A description of the relation being removed")
            .build();

    //  Manager functions

    private static final Function GetDiscoveryTokens =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_DISCOVERY_TOKENS)
            .setBaseName("GetDiscoveryTokens")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DISCOVERY_TOKEN, true))
            .addResultDescription("An array of {struct DiscoveryToken} entities")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("manager/discovery")
            .addDescription("Returns all of the configured discovery tokens")
            .build();

    private static final Function GetDiscoveryTokensByType =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_DISCOVERY_TOKENS_BY_TYPE)
            .setBaseName("GetDiscoveryTokensByType")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DISCOVERY_TOKEN, true))
            .addResultDescription("An array of {struct DiscoveryToken} entities")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("manager/discovery")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("TokenType")
                              .setDataDescriptor(new EnumeratorDataDescriptor(EnumeratorId.TOKEN_TYPE))
                              .addDescription("The type of tokens requested")
                              .build())
            .addDescription("Returns a subset of the configured discovery tokens, specified by the TokenType parameter")
            .build();

    private static final Function GetNetworkIPMIManagedCPU =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_IPMI_MANAGED_CPUS)
            .setBaseName("GetNetworkIPMIManagedCPU")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_MANAGED_CPU))
            .addResultDescription("A {struct NetworkManagedCPU} entity")
            .setBaseFunctionId(FunctionId.GET_NETWORK_IPMI_MANAGED_CPUS)
            .addMemberBaseName("CPUName")
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("CPUName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("Name of the CPU in question")
                              .build())
            .addDescription("Retrieves a particular network-IPMI-managed CPU given its name")
            .build();

    private static final Function GetNetworkIPMIManagedCPUS =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_IPMI_MANAGED_CPUS)
            .setBaseName("GetNetworkIPMIManagedCPUS")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_MANAGED_CPU, true))
            .addResultDescription("An array of {struct NetworkManagedCPU} entities")
            .setPartialPath("manager/network/ipmi/cpu")
            .setRestMethod(HttpMethod.GET)
            .addDescription("Retrieves a list of management entries for IPMI-via-network CPUs")
            .build();

    private static final Function GetNetworkIPMIManagedEnclosure =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURES)
            .setBaseName("GetNetworkIPMIManagedEnclosure")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_MANAGED_ENCLOSURE))
            .addResultDescription("A {struct NetworkManagedEnclosure} entity")
            .setBaseFunctionId(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURES)
            .addMemberBaseName("EnclosureName")
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("EnclosureName")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Name of the enclosure in question")
                              .build())
            .addDescription("Retrieves a particular network-IPMI-managed enclosure given its name")
            .build();

    private static final Function GetNetworkIPMIManagedEnclosures =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURES)
            .setBaseName("GetNetworkIPMIManagedEnclosures")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NETWORK_MANAGED_ENCLOSURE, true))
            .addResultDescription("An array of {struct NetworkManagedEnclosure} entities")
            .setPartialPath("manager/network/ipmi/enclosure")
            .setRestMethod(HttpMethod.GET)
            .addDescription("Retrieves a list of management entries for IPMI-via-network enclosures")
            .build();

    private static final Function GetManagedEntities =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MANAGED_ENTITIES)
            .setBaseName("GetManagedEntities")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MANAGED_ENTITY, true))
            .addResultDescription("Returns an array of {struct ManagedEntity} items describing the entries which are used for device discovery")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("manager/device")
            .addDescription("Reports all the managed entity entries used for discovering PCI devices")
            .build();

    private static final Function GetManagedEntity =
        new FilterFunction.Builder()
            .setFunctionId(FunctionId.GET_MANAGED_ENTITY)
            .setBaseName("GetManagedEntity")
            .setBaseFunctionId(FunctionId.GET_MANAGED_ENTITIES)
            .addMemberBaseName("PCIVendorId")
            .addMemberBaseName("PCIDeviceId")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MANAGED_ENTITY))
            .addResultDescription("A {struct ManagedEntity} entity describing the requested entity")
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("PCIVendorId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("PCI vendor id for the managed entity")
                              .build())
            // parameter is solely for documentation
            .addParameter(new PathParameter.Builder()
                              .setBaseName("PCIDeviceId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("PCI device id for the managed entity")
                              .build())
            .addDescription("Retrieves a particular managed entity")
            .build();

    //  Message digest functions

    private static final Function CreateMessageDigest =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.CREATE_MESSAGE_DIGEST)
            .setBaseName("CreateMessageDigest")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.DIGEST_INFO))
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("digest")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("Label")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("The label to be associated with a newly-created digest")
                              .build())
            .addDescription("Creates a new digest associated with a given label.")
            .addDescription("The digest will be returned to the client, and will not be otherwise exposed by the Director.")
            .addDescription("This digest should be used for authenticating subsequent REST API invocations.")
            .addDescription("At the end of the client session, this digest should be deleted by invoking {function DeleteMessageDigest}.")
            .addResultDescription("Contains information regarding the created digest.")
            .build();

    //  The following is hard-coded so that we can intercept attempts to delete special digests (e.g., slurm)
    private static final Function DeleteMessageDigest =
        new HardCodedFunction.Builder()
            .setFunctionId(FunctionId.DELETE_MESSAGE_DIGEST)
            .setBaseName("DeleteMessageDigest")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
            .addParameter(new PathParameter.Builder()
                              .setBaseName("Label")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING))
                              .addDescription("The label associated with the digest which is to be deleted")
                              .build())
            .addDescription("Deletes a previously-created message digest.")
            .addResultDescription("Returns the label associated with the deleted message digest")
            .build();

    private static final Function GetMessageDigestLabels =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_MESSAGE_DIGEST_LABELS)
            .setBaseName("GetMessageDigestLabels")
            .setResultDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.STRING, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("digest")
            .addDescription("Retrieves all existing message digest labels.")
            .addResultDescription("These are labels which can be used for login/logout authentication.")
            .addResultDescription("The labels are NOT authentication tokens.")
            .build();

    //  Node configuration functions
    //  TODO

    //  Node status functions

    private static final Function GetNodesStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_NODES_STATUS)
            .setBaseName("GetNodesStatus")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("node/status")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.NODE_STATUS, true))
            .addResultDescription("An array of {struct NodeStatus} entities describing the status of the nodes in the configuraiton")
            .addDescription("Retrieves status for the nodes in the configuration")
            .build();

    //	northbound application controller will not be implemented at this time

    //  Notification functions
    //  TODO

    //  Power management functions

    private final static Function RebootNode =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.REBOOT_NODE)
            .setBaseName("RebootNode")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("power/reboot")
            .addPathPreset(PresetId.CURRENT_FABRIC_ID)
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group which contains the node of interest")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique machine identifier containing the node of interest")
                              .build())
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the node")
            .addDescription("Reboots a particular node")
            .build();

    private final static Function RestartNode =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.RESTART_NODE)
            .setBaseName("RestartNode")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("power/restart")
            .addPathPreset(PresetId.CURRENT_FABRIC_ID)
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group which contains the node of interest")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique machine identifier containing the node of interest")
                              .build())
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the node")
            .addDescription("Restarts a particular node")
            .build();

    private final static Function ShutdownNode =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.SHUTDOWN_NODE)
            .setBaseName("ShutdownNode")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("power/shutdown")
            .addPathPreset(PresetId.CURRENT_FABRIC_ID)
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group which contains the node of interest")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique machine identifier containing the node of interest")
                              .build())
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the node")
            .addDescription("Shuts down a particular node")
            .build();

    private final static Function StartNode =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.START_NODE)
            .setBaseName("StartNode")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("power/start")
            .addPathPreset(PresetId.CURRENT_FABRIC_ID)
            .addParameter(new PathParameter.Builder()
                              .setBaseName("GroupId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique identifier of the group which contains the node of interest")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("MachineId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("Unique machine identifier containing the node of interest")
                              .build())
            .setResultDataDescriptor(new StructDataDescriptor(StructId.MACHINE))
            .addResultDescription("A {struct Machine} entity describing the node")
            .addDescription("Starts a particular node")
            .build();

    //	slurm management controller API will not be implemented

    //	SSH functions

    private static final Function GetSSHStatus =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_SSH_STATUS)
            .setBaseName("GetSSHStatus")
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("ssh/enable")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.SSH_STATUS))
            .addResultDescription("A {struct SSHStatus} entity describing the state of SSH")
            .addDescription("Retrieves the current state of SSH")
            .build();

    private static final Function SetSSHStatus =
        new WiredFunction.Builder()
            .setFunctionId(FunctionId.SET_SSH_STATUS)
            .setBaseName("SetSSHStatus")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("ssh/enable")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.SSH_STATUS))
            .addResultDescription("A {struct SSHStatus} entity describing the state of SSH")
            .setApiStructId(StructId.SSH_STATUS)
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Active")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.BOOLEAN))
                              .addDescription("Indicates whether SSH should be active")
                              .addTarget(new StructMemberTarget("Active"))
                              .build())
            .addParameter(new WiredParameter.Builder()
                              .setBaseName("Enabled")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.BOOLEAN))
                              .addDescription("Indicates whether SSH should be enabled")
                              .addTarget(new StructMemberTarget("Enabled"))
                              .build())
            .addDescription("Sets the state of SSH")
            .build();

    //  State functions
    //  TODO

    //  System functions

    private static final Function ResetState =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.RESET_STATE)
            .setBaseName("ResetState")
            .addDescription("Disconnects the device connections to a CPU.")
            .addDescription("Removes LiqOS state information related to machines, groups, and devices.")
            .addDescription("Forces a Liqid rediscovery of the fabric.")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.TIMESTAMP))
            .addResultDescription("The date/time at which the operation was invoked")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("system/state/reset")
            .build();

    private static final Function RestartFabric =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.RESTART_FABRIC)
            .setBaseName("RestartFabric")
            .addDescription("Restarts the entire fabric")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.TIMESTAMP))
            .addResultDescription("The date/time at which the operation was invoked")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("system/restart/fabric")
            .build();

    private static final Function RestartHierarchy =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.RESTART_HIERARCHY)
            .setBaseName("RestartHierarchy")
            .addDescription("Initiates a discovery of the fabric hierarchy")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.TIMESTAMP))
            .addResultDescription("The date/time at which the operation was invoked")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("system/restart/hierarchy")
            .build();

    private static final Function RestartSwitch =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.RESTART_SWITCH)
            .setBaseName("RestartSwitch")
            .addDescription("Restarts the switch at the default coordinates")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.TIMESTAMP))
            .addResultDescription("The date/time at which the operation was invoked")
            .setRestMethod(HttpMethod.POST)
            .setPartialPath("system/restart/switch")
            .build();

    private static final Function Shutdown =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.SHUTDOWN)
            .setBaseName("Shutdown")
            .addDescription("Gracefully powers down the director at the default coordinates")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COORDINATES))
            .addResultDescription("The coordinates for the shutdown operation")
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("system/shutdown")
            .build();

    private static final Function ShutdownAt =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.SHUTDOWN_AT)
            .setBaseName("ShutdownAt")
            .addDescription("Shuts down the director at the given coordinates")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.COORDINATES))
            .addResultDescription("The coordinates for the shutdown operation")
            .addParameter(new PathParameter.Builder()
                              .setBaseName("RackId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("The rack id component of the Liqid coordinates")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("ShelfId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("The shelf id component of the Liqid coordinates")
                              .build())
            .addParameter(new PathParameter.Builder()
                              .setBaseName("NodeId")
                              .setDataDescriptor(new IntrinsicDataDescriptor(IntrinsicTypeId.INT32))
                              .addDescription("The node id component of the Liqid coordinates")
                              .build())
            .setRestMethod(HttpMethod.PUT)
            .setPartialPath("system/shutdown")
            .build();

    //  Upgrade functions
    //  TODO

    //  Version functions

    private static final Function GetVersions =
        new RegularFunction.Builder()
            .setFunctionId(FunctionId.GET_VERSIONS)
            .setBaseName("GetVersions")
            .setResultDataDescriptor(new StructDataDescriptor(StructId.VERSION_INFO, true))
            .setRestMethod(HttpMethod.GET)
            .setPartialPath("version")
            .addDescription("Retrieves information describing the various software components which comprise the Liqid Director.")
            .addResultDescription("An array of VersionInfo entities describing the various software components")
            .build();

    //  ------------------------------------------------------------------------

    public static final Map<FunctionId, Function> CONTENT = new LinkedHashMap<>();
    static {
        //  BackupDirector
        CONTENT.put(FunctionId.BACKUP_DIRECTOR, BackupDirector);

        //  Client
        CONTENT.put(FunctionId.CLEAR_CREDENTIALS, ClearCredentials);
        CONTENT.put(FunctionId.HAS_CREDENTIALS, HasCredentials);
        CONTENT.put(FunctionId.INITIALIZE, Initialize);
        CONTENT.put(FunctionId.IS_LOGGED_IN, IsLoggedIn);
        CONTENT.put(FunctionId.LOGIN, LogIn);
        CONTENT.put(FunctionId.LOGOUT, LogOut);
        CONTENT.put(FunctionId.SET_CREDENTIALS, SetCredentials);
        CONTENT.put(FunctionId.SET_LOGGING, SetLogging);

        //  Coordinates
        CONTENT.put(FunctionId.GET_AVAILABLE_COORDINATES, GetAvailableCoordinates);
        CONTENT.put(FunctionId.GET_DEFAULT_COORDINATES, GetDefaultCoordinates);
        CONTENT.put(FunctionId.SET_DEFAULT_COORDINATES, SetDefaultCoordinates);

        //  Device
        CONTENT.put(FunctionId.CREATE_DEVICE_DESCRIPTION, CreateDeviceDescription);
        CONTENT.put(FunctionId.DELETE_DEVICE_DESCRIPTION, DeleteDeviceDescription);
        CONTENT.put(FunctionId.GET_DEVICE_ATTRIBUTES, GetDeviceAttributes);
        CONTENT.put(FunctionId.GET_SECURE_ERASE_STATUS, GetSecureEraseStatus);
        CONTENT.put(FunctionId.SECURE_ERASE, SecureErase);

        //  DeviceDiscovery
        CONTENT.put(FunctionId.GET_DEVICE_COUNTERS, GetDeviceCounters);

        //  DeviceInfo
        CONTENT.put(FunctionId.GET_COMPUTE_DEVICE_INFO, GetComputeDeviceInfo);
        CONTENT.put(FunctionId.GET_COMPUTE_DEVICE_INFO_BY_NAME, GetComputeDeviceInfoByName);
        CONTENT.put(FunctionId.GET_FPGA_DEVICE_INFO, GetFPGADeviceInfo);
        CONTENT.put(FunctionId.GET_FPGA_DEVICE_INFO_BY_NAME, GetFPGADeviceInfoByName);
        CONTENT.put(FunctionId.GET_GPU_DEVICE_INFO, GetGPUDeviceInfo);
        CONTENT.put(FunctionId.GET_GPU_DEVICE_INFO_BY_NAME, GetGPUDeviceInfoByName);
        CONTENT.put(FunctionId.GET_MEMORY_DEVICE_INFO, GetMemoryDeviceInfo);
        CONTENT.put(FunctionId.GET_MEMORY_DEVICE_INFO_BY_NAME, GetMemoryDeviceInfoByName);
        CONTENT.put(FunctionId.GET_NETWORK_DEVICE_INFO, GetNetworkDeviceInfo);
        CONTENT.put(FunctionId.GET_NETWORK_DEVICE_INFO_BY_NAME, GetNetworkDeviceInfoByName);
        CONTENT.put(FunctionId.GET_STORAGE_DEVICE_INFO, GetStorageDeviceInfo);
        CONTENT.put(FunctionId.GET_STORAGE_DEVICE_INFO_BY_NAME, GetStorageDeviceInfoByName);

        //  DeviceStatus
        CONTENT.put(FunctionId.GET_ALL_DEVICES_STATUS, GetAllDevicesStatus);
        CONTENT.put(FunctionId.GET_COMPUTE_DEVICE_STATUS, GetComputeDeviceStatus);
        CONTENT.put(FunctionId.GET_COMPUTE_DEVICES_STATUS, GetComputeDevicesStatus);
        CONTENT.put(FunctionId.GET_COMPUTE_DEVICES_WITH_MULTIPLE_PORTS_STATUS, GetComputeDevicesWithMultiplePortsStatus);
        CONTENT.put(FunctionId.GET_FPGA_DEVICE_STATUS, GetFPGADeviceStatus);
        CONTENT.put(FunctionId.GET_FPGA_DEVICES_STATUS, GetFPGADevicesStatus);
        CONTENT.put(FunctionId.GET_FREE_COMPUTE_DEVICES_STATUS, GetFreeComputeDevicesStatus);
        CONTENT.put(FunctionId.GET_FREE_FPGA_DEVICES_STATUS, GetFreeFPGADevicesStatus);
        CONTENT.put(FunctionId.GET_FREE_GPU_DEVICES_STATUS, GetFreeGPUDevicesStatus);
        CONTENT.put(FunctionId.GET_FREE_MEMORY_DEVICES_STATUS, GetFreeMemoryDevicesStatus);
        CONTENT.put(FunctionId.GET_FREE_NETWORK_DEVICES_STATUS, GetFreeNetworkDevicesStatus);
        CONTENT.put(FunctionId.GET_FREE_STORAGE_DEVICES_STATUS, GetFreeStorageDevicesStatus);
        CONTENT.put(FunctionId.GET_GPU_DEVICE_STATUS, GetGPUDeviceStatus);
        CONTENT.put(FunctionId.GET_GPU_DEVICES_STATUS, GetGPUDevicesStatus);
        CONTENT.put(FunctionId.GET_MEMORY_DEVICE_STATUS, GetMemoryDeviceStatus);
        CONTENT.put(FunctionId.GET_MEMORY_DEVICES_STATUS, GetMemoryDevicesStatus);
        CONTENT.put(FunctionId.GET_NETWORK_DEVICE_STATUS, GetNetworkDeviceStatus);
        CONTENT.put(FunctionId.GET_NETWORK_DEVICES_STATUS, GetNetworkDevicesStatus);
        CONTENT.put(FunctionId.GET_STORAGE_DEVICE_STATUS, GetStorageDeviceStatus);
        CONTENT.put(FunctionId.GET_STORAGE_DEVICES_STATUS, GetStorageDevicesStatus);
        CONTENT.put(FunctionId.GET_DEVICE_STATUS, GetDeviceStatus);

        //  Fabric
        CONTENT.put(FunctionId.APPLY_FABRIC_CHANGES, ApplyFabricChanges);
        CONTENT.put(FunctionId.CANCEL_EDIT_FABRIC, CancelEditFabric);
        CONTENT.put(FunctionId.CANCEL_REPROGRAM_FABRIC, CancelReprogramFabric);
        CONTENT.put(FunctionId.EDIT_FABRIC, EditFabric);
        CONTENT.put(FunctionId.GET_CURRENT_FABRIC_ID, GetCurrentFabricId);
        CONTENT.put(FunctionId.GET_FABRIC_TOPOLOGY, GetFabricTopology);
        CONTENT.put(FunctionId.GET_FABRIC_TYPES, GetFabricTypes);
        CONTENT.put(FunctionId.REPROGRAM_FABRIC, ReprogramFabric);

        //  Group
        CONTENT.put(FunctionId.CLEAR_GROUPS, ClearGroups);
        CONTENT.put(FunctionId.CREATE_GROUP, CreateGroup);
        CONTENT.put(FunctionId.CREATE_GROUP_WITH_ID, CreateGroupWithId);
        CONTENT.put(FunctionId.DELETE_GROUP, DeleteGroup);
        CONTENT.put(FunctionId.GET_GROUP, GetGroup);
        CONTENT.put(FunctionId.GET_GROUPS, GetGroups);
        CONTENT.put(FunctionId.GET_GROUP_DETAILS, GetGroupDetails);
        CONTENT.put(FunctionId.GET_GROUP_ID_BY_NAME, GetGroupIdByName);
        CONTENT.put(FunctionId.GET_NEXT_GROUP_ID, GetNextGroupId);

        //  Group Device Relator
        CONTENT.put(FunctionId.ADD_COMPUTE_DEVICE_TO_GROUP, AddComputeDeviceToGroup);
        CONTENT.put(FunctionId.ADD_FPGA_DEVICE_TO_GROUP, AddFPGADeviceToGroup);
        CONTENT.put(FunctionId.ADD_GPU_DEVICE_TO_GROUP, AddGPUDeviceToGroup);
        CONTENT.put(FunctionId.ADD_MEMORY_DEVICE_TO_GROUP, AddMemoryDeviceToGroup);
        CONTENT.put(FunctionId.ADD_NETWORK_DEVICE_TO_GROUP, AddNetworkDeviceToGroup);
        CONTENT.put(FunctionId.ADD_STORAGE_DEVICE_TO_GROUP, AddStorageDeviceToGroup);
        CONTENT.put(FunctionId.GET_DEVICES, GetDevices);
        CONTENT.put(FunctionId.GET_GROUP_COMPUTE_DEVICE_RELATOR, GetGroupComputeDeviceRelator);
        CONTENT.put(FunctionId.GET_GROUP_FPGA_DEVICE_RELATOR, GetGroupFPGADeviceRelator);
        CONTENT.put(FunctionId.GET_GROUP_GPU_DEVICE_RELATOR, GetGroupGPUDeviceRelator);
        CONTENT.put(FunctionId.GET_GROUP_MEMORY_DEVICE_RELATOR, GetGroupMemoryDeviceRelator);
        CONTENT.put(FunctionId.GET_GROUP_NETWORK_DEVICE_RELATOR, GetGroupNetworkDeviceRelator);
        CONTENT.put(FunctionId.GET_GROUP_STORAGE_DEVICE_RELATOR, GetGroupStorageDeviceRelator);
        CONTENT.put(FunctionId.GET_UNATTACHED_DEVICES_FOR_GROUP, GetUnattachedDevicesForGroup);
        CONTENT.put(FunctionId.REMOVE_COMPUTE_DEVICE_FROM_GROUP, RemoveComputeDeviceFromGroup);
        CONTENT.put(FunctionId.REMOVE_FPGA_DEVICE_FROM_GROUP, RemoveFPGADeviceFromGroup);
        CONTENT.put(FunctionId.REMOVE_GPU_DEVICE_FROM_GROUP, RemoveGPUDeviceFromGroup);
        CONTENT.put(FunctionId.REMOVE_MEMORY_DEVICE_FROM_GROUP, RemoveMemoryDeviceFromGroup);
        CONTENT.put(FunctionId.REMOVE_NETWORK_DEVICE_FROM_GROUP, RemoveNetworkDeviceFromGroup);
        CONTENT.put(FunctionId.REMOVE_STORAGE_DEVICE_FROM_GROUP, RemoveStorageDeviceFromGroup);

        //  Group Pool
        CONTENT.put(FunctionId.CANCEL_GROUP_POOL_EDIT, CancelGroupPoolEdit);
        CONTENT.put(FunctionId.GROUP_POOL_DONE, GroupPoolDone);
        CONTENT.put(FunctionId.GROUP_POOL_EDIT, GroupPoolEdit);

        //  Machine
        CONTENT.put(FunctionId.CREATE_MACHINE, CreateMachine);
        CONTENT.put(FunctionId.CREATE_MACHINE_WITH_ID, CreateMachineWithId);
        CONTENT.put(FunctionId.DELETE_MACHINE, DeleteMachine);
        CONTENT.put(FunctionId.GET_MACHINES, GetMachines);
        CONTENT.put(FunctionId.GET_MACHINES_AT_COORDINATES, GetMachinesAtCoordinates);
        CONTENT.put(FunctionId.GET_MACHINES_BY_GROUP_ID, GetMachinesByGroupId);
        CONTENT.put(FunctionId.GET_MACHINE, GetMachine);
        CONTENT.put(FunctionId.GET_MACHINE_BY_NAME, GetMachineByName);
        CONTENT.put(FunctionId.GET_MACHINE_DETAILS, GetMachineDetails);
        CONTENT.put(FunctionId.GET_NEXT_MACHINE_ID, GetNextMachineId);
        CONTENT.put(FunctionId.SET_P2P_ENABLED, SetP2PEnabled);

        //  MachineDeviceRelator
        CONTENT.put(FunctionId.ADD_DEVICE_TO_GROUP, AddDeviceToGroup);
        CONTENT.put(FunctionId.ADD_DEVICE_TO_MACHINE, AddDeviceToMachine);
        CONTENT.put(FunctionId.ADD_COMPUTE_DEVICE_TO_MACHINE, AddComputeDeviceToMachine);
        CONTENT.put(FunctionId.ADD_FPGA_DEVICE_TO_MACHINE, AddFPGADeviceToMachine);
        CONTENT.put(FunctionId.ADD_GPU_DEVICE_TO_MACHINE, AddGPUDeviceToMachine);
        CONTENT.put(FunctionId.ADD_MEMORY_DEVICE_TO_MACHINE, AddMemoryDeviceToMachine);
        CONTENT.put(FunctionId.ADD_NETWORK_DEVICE_TO_MACHINE, AddNetworkDeviceToMachine);
        CONTENT.put(FunctionId.ADD_STORAGE_DEVICE_TO_MACHINE, AddStorageDeviceToMachine);
        CONTENT.put(FunctionId.REMOVE_DEVICE_FROM_GROUP, RemoveDeviceFromGroup);
        CONTENT.put(FunctionId.REMOVE_DEVICE_FROM_MACHINE, RemoveDeviceFromMachine);
        CONTENT.put(FunctionId.REMOVE_COMPUTE_DEVICE_FROM_MACHINE, RemoveComputeDeviceFromMachine);
        CONTENT.put(FunctionId.REMOVE_FPGA_DEVICE_FROM_MACHINE, RemoveFPGADeviceFromMachine);
        CONTENT.put(FunctionId.REMOVE_GPU_DEVICE_FROM_MACHINE, RemoveGPUDeviceFromMachine);
        CONTENT.put(FunctionId.REMOVE_MEMORY_DEVICE_FROM_MACHINE, RemoveMemoryDeviceFromMachine);
        CONTENT.put(FunctionId.REMOVE_NETWORK_DEVICE_FROM_MACHINE, RemoveNetworkDeviceFromMachine);
        CONTENT.put(FunctionId.REMOVE_STORAGE_DEVICE_FROM_MACHINE, RemoveStorageDeviceFromMachine);

        //  Manager
        CONTENT.put(FunctionId.GET_DISCOVERY_TOKENS, GetDiscoveryTokens);
        CONTENT.put(FunctionId.GET_DISCOVERY_TOKENS_BY_TYPE, GetDiscoveryTokensByType);
        CONTENT.put(FunctionId.GET_NETWORK_IPMI_MANAGED_CPU, GetNetworkIPMIManagedCPU);
        CONTENT.put(FunctionId.GET_NETWORK_IPMI_MANAGED_CPUS, GetNetworkIPMIManagedCPUS);
        CONTENT.put(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURE, GetNetworkIPMIManagedEnclosure);
        CONTENT.put(FunctionId.GET_NETWORK_IPMI_MANAGED_ENCLOSURES, GetNetworkIPMIManagedEnclosures);
        CONTENT.put(FunctionId.GET_NODES_STATUS, GetNodesStatus);
        CONTENT.put(FunctionId.GET_MANAGED_ENTITIES, GetManagedEntities);
        CONTENT.put(FunctionId.GET_MANAGED_ENTITY, GetManagedEntity);

        //  MessageDigest
        CONTENT.put(FunctionId.CREATE_MESSAGE_DIGEST, CreateMessageDigest);
        CONTENT.put(FunctionId.DELETE_MESSAGE_DIGEST, DeleteMessageDigest);
        CONTENT.put(FunctionId.GET_MESSAGE_DIGEST_LABELS, GetMessageDigestLabels);

        //  PowerManagement
        CONTENT.put(FunctionId.REBOOT_NODE, RebootNode);
        CONTENT.put(FunctionId.RESTART_NODE, RestartNode);
        CONTENT.put(FunctionId.SHUTDOWN_NODE, ShutdownNode);
        CONTENT.put(FunctionId.START_NODE, StartNode);

        //  SSH
        CONTENT.put(FunctionId.GET_SSH_STATUS, GetSSHStatus);
        CONTENT.put(FunctionId.SET_SSH_STATUS, SetSSHStatus);

        //  System
        CONTENT.put(FunctionId.RESET_STATE, ResetState);
        CONTENT.put(FunctionId.RESTART_FABRIC, RestartFabric);
        CONTENT.put(FunctionId.RESTART_HIERARCHY, RestartHierarchy);
        CONTENT.put(FunctionId.RESTART_SWITCH, RestartSwitch);
        CONTENT.put(FunctionId.SHUTDOWN, Shutdown);
        CONTENT.put(FunctionId.SHUTDOWN_AT, ShutdownAt);

        //  Version
        CONTENT.put(FunctionId.GET_VERSIONS, GetVersions);
    }
}
