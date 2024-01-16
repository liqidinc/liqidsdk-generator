//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//

package com.liqid.sdk.generator.catalog;

import com.liqid.sdk.generator.discreteDataModels.IntrinsicTypeId;
import com.liqid.sdk.generator.liqidEntityModels.*;
import com.liqid.sdk.generator.liqidEntityModels.types.EnumComponent;
import com.liqid.sdk.generator.liqidEntityModels.types.Enumerator;

import java.util.LinkedHashMap;
import java.util.Map;

class Enumerators {

    private static final Enumerator BackupDestination =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.BACKUP_DESTINATION)
                                .setBaseName("BackupDestination")
                                .setIntrinsicTypeId(IntrinsicTypeId.INT32)
                                .addComponent(new EnumComponent.Builder().setBaseName("Local")
                                                                         .setValue(0)
                                                                         .addDescription("Local backup")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Remote")
                                                                         .setValue(1)
                                                                         .addDescription("Remote backup")
                                                                         .build())
                                .addDescription("Indicates the backup destination")
                                .build();

    private static final Enumerator ConfigurationFileSection =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.CONFIGURATION_FILE_SECTION)
                                .setBaseName("ConfigurationFileSection")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Enclosure")
                                                                         .setValue("enclosure")
                                                                         .addDescription("Describes enclosure configuration")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("CPU")
                                                                         .setValue("cpu")
                                                                         .addDescription("Describes CPU configuration")
                                                                         .build())
                                .addDescription("Describes a type of configuration file section")
                                .build();

    private static final Enumerator ConfigurationFileType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.CONFIGURATION_FILE_TYPE)
                                .setBaseName("ConfigurationFileType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Slurm")
                                                                         .setValue("slurm")
                                                                         .addDescription("Slurm configuration file")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Kubernetes")
                                                                         .setValue("kubernetes")
                                                                         .addDescription("Kubernetes configuration file")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Redfish")
                                                                         .setValue("redfish")
                                                                         .addDescription("Redfish configuration file")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("VAPI")
                                                                         .setValue("vapi")
                                                                         .addDescription("VAPI configuration file")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("IPMI")
                                                                         .setValue("ipmi")
                                                                         .addDescription("IPMI configuration file")
                                                                         .build())
                                .addDescription("Describes a type of configuration file")
                                .build();

    private static final Enumerator DeviceType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.DEVICE_TYPE)
                                .setBaseName("DeviceType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addDescription("Represents a particular device type as a short string.")
                                .addDescription("These values apply only to {struct DeviceStatus} and {struct DeviceInfo}")
                                .addComponent(new EnumComponent.Builder().setBaseName("InfinibandLink")
                                                                         .setValue("infiniband_link")
                                                                         .addDescription("Represents a network link")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("EthernetLink")
                                                                         .setValue("ethernet_link")
                                                                         .addDescription("Represents a network link")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FiberChannelLink")
                                                                         .setValue("fibrechannel_link")
                                                                         .addDescription("Represents a network link")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("GPU")
                                                                         .setValue("gpu")
                                                                         .addDescription("Represents a graphics processing unit resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("SSD")
                                                                         .setValue("target/SSD")
                                                                         .addDescription("Represents a switchable SSD resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FPGA")
                                                                         .setValue("fpga")
                                                                         .addDescription("Represents an FPGA resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Compute")
                                                                         .setValue("compute")
                                                                         .addDescription("Represents a CPU resource.")
                                                                         .addDescription("Returned from CPUDeviceInfo")
                                                                         .build())
                                // TODO:The following is probably wrong, but we don't know what it should be instead.
                                //  We just sort of forgot to add the code to the CLI which shows the memory type.
                                .addComponent(new EnumComponent.Builder().setBaseName("Memory")
                                                                         .setValue("mem")
                                                                         .addDescription("Represents configurable/switchable memory")
                                                                         .build())
                                .build();

    private static final Enumerator DeviceQueryDeviceType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.QUERY_DEVICE_TYPE)
                                .setBaseName("DeviceQueryType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addDescription("Represents a particular device type as a short string.")
                                .addDescription("These values apply to function parameters accepting device types.")
                                .addComponent(new EnumComponent.Builder().setBaseName("Target")
                                                                         .setValue("targ")
                                                                         .addDescription("Represents a target, probably storage")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Link")
                                                                         .setValue("link")
                                                                         .addDescription("Represents a network link of some kind")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Compute")
                                                                         .setValue("comp")
                                                                         .addDescription("Represents a compute resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FPGA")
                                                                         .setValue("fpga")
                                                                         .addDescription("Represents an FPGA resource.")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("GPU")
                                                                         .setValue("gpu")
                                                                         .addDescription("Represents a GPU resource.")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Memory")
                                                                         .setValue("mem")
                                                                         .addDescription("Represents configurable/switchable memory")
                                                                         .build())
                                .build();

    private static final Enumerator FabricToggleCompositeOption =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.FABRIC_TOGGLE_COMPOSITE_OPTION)
                                .setBaseName("FabricToggleCompositeOption")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Add")
                                                                         .setValue("add")
                                                                         .addDescription("The accompanying information is to be added")
                                                                         .build())
                                .addDescription("TODO")//TODO
                                .build();

    private static final Enumerator FabricType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.FABRIC_TYPE)
                                .setBaseName("FabricType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Gen3")
                                                                         .setValue("gen3")
                                                                         .addDescription("Fabric is based on gen3 pci protocol and hardware")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Gen4")
                                                                         .setValue("gen4")
                                                                         .addDescription("Fabric is based on gen4 pci protocol and hardware")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("NVOverFabric")
                                                                         .setValue("nvof")
                                                                         .addDescription("NVMe over fabric")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("GPUOverFabric")
                                                                         .setValue("gpuof")
                                                                         .addDescription("GPU over fabric")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("GenZ")
                                                                         .setValue("genz")
                                                                         .addDescription("Gen Z protocol and hardware")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("NVMEOverFabric")
                                                                         .setValue("nvmeof")
                                                                         .addDescription("NVMe over fabric")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("IPBased")
                                                                         .setValue("ip_based")
                                                                         .addDescription("IP based fabric")
                                                                         .build())
                                .addDescription("Describes a particular fabric topology type")
                                .build();

    private static final Enumerator ImageType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.IMAGE_TYPE)
                                .setBaseName("ImageType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Boot")
                                                                         .setValue("boot")
                                                                         .addDescription("Boot image")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FPGA")
                                                                         .setValue("fpga")
                                                                         .addDescription("FPGA software image")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("VNIC")
                                                                         .setValue("vnic")
                                                                         .addDescription("VNIC image")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Upgrade")
                                                                         .setValue("upgrade")
                                                                         .addDescription("Liqid software upgrade image")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Application")
                                                                         .setValue("application")
                                                                         .addDescription("Application software image")
                                                                         .build())
                                .addDescription("Describes a type of software image file")
                                .build();

    private static final Enumerator LogType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.LOG_TYPE)
                                .setBaseName("LogType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("System")
                                                                         .setValue("system")
                                                                         .addDescription("General system logs")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Liqid")
                                                                         .setValue("liqid")
                                                                         .addDescription("Liqid software component logs")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Combined")
                                                                         .setValue("combined")
                                                                         .addDescription("All available logs")
                                                                         .build())
                                .addDescription("Describes a log file type")
                                .build();

    private static final Enumerator ManageableType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.MANAGEABLE_TYPE)
                                .setBaseName("ManageableType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Device")
                                                                         .setValue("ManageableDevice")
                                                                         .addDescription("Describes a manageable device")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("CpuIpmiNetwork")
                                                                         .setValue("ManageableCpuIpmiNetworkConfig")
                                                                         .addDescription("Describes an IPMI-manageable CPU")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("EnclosureIpmiNetwork")
                                                                         .setValue("ManageableDeviceIpmiNetworkConfig")
                                                                         .addDescription("Describes an IPMI-manageable enclosure")
                                                                         .build())
                                .addDescription("Describes a type of manageable entity")
                                .build();

    private static final Enumerator NorthboundApplicationType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.NORTHBOUND_APPLICATION_TYPE)
                                .setBaseName("NorthboundApplicationType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Slurm")
                                                                         .setValue("slurm")
                                                                         .addDescription("Slurm application")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Kubernetes")
                                                                         .setValue("kubernetes")
                                                                         .addDescription("Kubernetes application")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Redfish")
                                                                         .setValue("redfish")
                                                                         .addDescription("Redfish application")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("VAPI")
                                                                         .setValue("vapi")
                                                                         .addDescription("VAPI application")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("IPMI")
                                                                         .setValue("ipmi")
                                                                         .addDescription("IPMI application")
                                                                         .build())
                                .addDescription("Describes a northbound application type")
                                .build();

    private static final Enumerator OperatingSystemType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.OPERATING_SYSTEM_TYPE)
                                .setBaseName("OperatingSystemType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Linux")
                                                                         .setValue("linux")
                                                                         .addDescription("Any Linux-derived operating system")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FreeBSD")
                                                                         .setValue("FreeBSD")
                                                                         .addDescription("Any FreeBSD distribution")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Windows")
                                                                         .setValue("windows")
                                                                         .addDescription("Any Microsoft Windows(TM) operating system")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("LiqidOS")
                                                                         .setValue("liqidos")
                                                                         .addDescription("Liqid Inc. operating system")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("MaxOSX")
                                                                         .setValue("Apple Inc. Mac(TM) OSX operating system")
                                                                         .addDescription("Any darwin-based operating system")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Unknown")
                                                                         .setValue("unknown")
                                                                         .addDescription("Operating system is unrecognized or cannot be determined")
                                                                         .build())
                                .addDescription("Describes an operating system")
                                .build();

    private static final Enumerator P2PType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.P2P_TYPE)
                                .setBaseName("P2PType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Off")
                                                                         .setValue("off")
                                                                         .addDescription("Indicates that P2P is not enabled")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("On")
                                                                         .setValue("on")
                                                                         .addDescription("Indicates that P2P is enabled")
                                                                         .build())
                                .addDescription("Indicates whether P2P is enabled")
                                .build();

    private static final Enumerator PreDeviceType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.PRE_DEVICE_TYPE)
                                .setBaseName("PreDeviceType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addDescription("Represents a particular device type as a short string.")
                                .addDescription("These are values which apply to the PreDevice device type field.")
                                .addComponent(new EnumComponent.Builder().setBaseName("Compute")
                                                                         .setValue("comp")
                                                                         .addDescription("Represents a CPU resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FiberChannel")
                                                                         .setValue("fibr")
                                                                         .addDescription("Represents a fiber channel link")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FPGA")
                                                                         .setValue("fpga")
                                                                         .addDescription("Represents an FPGA resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("GPU")
                                                                         .setValue("gpu")
                                                                         .addDescription("Represents a GPU resource")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Infiniband")
                                                                         .setValue("infi")
                                                                         .addDescription("Represents an infiniband link")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Link")
                                                                         .setValue("link")
                                                                         .addDescription("Represents a generic link")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Memory")
                                                                         .setValue("mem")
                                                                         .addDescription("Represents switchable memory")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FabricChip")
                                                                         .setValue("plx")
                                                                         .addDescription("Represents a PLX fabric switch chip")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Target")
                                                                         .setValue("targ")
                                                                         .addDescription("Represents a target resource, such as an SSD")
                                                                         .build())
                                .build();

    private static final Enumerator RunType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.RUN_TYPE)
                                .setBaseName("RunType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Up")
                                                                         .setValue("up")
                                                                         .addDescription("Entity is up and running")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("On")
                                                                         .setValue("on")
                                                                         .addDescription("Entity is powered-on but not necessarily running")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Off")
                                                                         .setValue("off")
                                                                         .addDescription("Entity is not powered-on")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Unknown")
                                                                         .setValue("unknown")
                                                                         .addDescription("Entity state cannot be determined")
                                                                         .build())
                                .addDescription("Describes the various run states for liqid")
                                .build();

    private static final Enumerator ToggleFlag =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.TOGGLE_FLAG)
                                .setBaseName("ToggleFlag")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Permanent")
                                                                         .setValue("perm")
                                                                         .addDescription("Associated entity is permanent")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Disappear")
                                                                         .setValue("disappear")
                                                                         .addDescription("Associated entity should disappear")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Active")
                                                                         .setValue("active")
                                                                         .addDescription("Associated entity is active")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Hidden")
                                                                         .setValue("hidden")
                                                                         .addDescription("Associated entity is hidden")
                                                                         .build())
                                .addDescription("TODO") //  TODO
                                .build();

    private static final Enumerator ToggleState =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.TOGGLE_STATE)
                                .setBaseName("ToggleState")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Enabled")
                                                                         .setValue("enabled")
                                                                         .addDescription("Indicates that the associated entity is enabled.")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Disabled")
                                                                         .setValue("disabled")
                                                                         .addDescription("Indicates that the associated entity is disabled.")
                                                                         .build())
                                .addDescription("Describes a binary state of enablement/disablement")
                                .build();

    private static final Enumerator TokenType =
        new Enumerator.Builder().setEnumeratorId(EnumeratorId.TOKEN_TYPE)
                                .setBaseName("TokenType")
                                .setIntrinsicTypeId(IntrinsicTypeId.STRING)
                                .addComponent(new EnumComponent.Builder().setBaseName("Target")
                                                                         .setValue("targ")
                                                                         .addDescription("Indicates a target type")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("Link")
                                                                         .setValue("link")
                                                                         .addDescription("Indicates a network link type")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("GPU")
                                                                         .setValue("gpu")
                                                                         .addDescription("Indicates a GPU")
                                                                         .build())
                                .addComponent(new EnumComponent.Builder().setBaseName("FPGA")
                                                                         .setValue("fpga")
                                                                         .addDescription("Indicates an FPGA device")
                                                                         .build())
                                .addDescription("A subset of DeviceType values used exclusively with tokens")
                                .build();

    public static final Map<EnumeratorId, Enumerator> CONTENT = new LinkedHashMap<>();
    static {
        CONTENT.put(EnumeratorId.BACKUP_DESTINATION, BackupDestination);
        CONTENT.put(EnumeratorId.CONFIGURATION_FILE_SECTION, ConfigurationFileSection);
        CONTENT.put(EnumeratorId.CONFIGURATION_FILE_TYPE, ConfigurationFileType);
        CONTENT.put(EnumeratorId.DEVICE_TYPE, DeviceType);
        CONTENT.put(EnumeratorId.FABRIC_TOGGLE_COMPOSITE_OPTION, FabricToggleCompositeOption);
        CONTENT.put(EnumeratorId.FABRIC_TYPE, FabricType);
        CONTENT.put(EnumeratorId.IMAGE_TYPE, ImageType);
        CONTENT.put(EnumeratorId.LOG_TYPE, LogType);
        CONTENT.put(EnumeratorId.MANAGEABLE_TYPE, ManageableType);
        CONTENT.put(EnumeratorId.NORTHBOUND_APPLICATION_TYPE, NorthboundApplicationType);
        CONTENT.put(EnumeratorId.OPERATING_SYSTEM_TYPE, OperatingSystemType);
        CONTENT.put(EnumeratorId.P2P_TYPE, P2PType);
        CONTENT.put(EnumeratorId.PRE_DEVICE_TYPE, PreDeviceType);
        CONTENT.put(EnumeratorId.QUERY_DEVICE_TYPE, DeviceQueryDeviceType);
        CONTENT.put(EnumeratorId.RUN_TYPE, RunType);
        CONTENT.put(EnumeratorId.TOGGLE_FLAG, ToggleFlag);
        CONTENT.put(EnumeratorId.TOGGLE_STATE, ToggleState);
        CONTENT.put(EnumeratorId.TOKEN_TYPE, TokenType);
    }
}
