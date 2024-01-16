# File: constants.py
#
# Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
# 
# Redistribution and use in source and binary forms, with or without
# modification, are not permitted without prior consent.
#
# Liqid SDK - Version 3.3.0
# This file was automatically generated - do not modify it directly.
#

"""
BackupDestination enumeration constants
Indicates the backup destination
"""
BackupDestinationLocal = 0
BackupDestinationRemote = 1

"""
ConfigurationFileSection enumeration constants
Describes a type of configuration file section
"""
ConfigurationFileSectionEnclosure = "enclosure"
ConfigurationFileSectionCPU = "cpu"

"""
ConfigurationFileType enumeration constants
Describes a type of configuration file
"""
ConfigurationFileTypeSlurm = "slurm"
ConfigurationFileTypeKubernetes = "kubernetes"
ConfigurationFileTypeRedfish = "redfish"
ConfigurationFileTypeVAPI = "vapi"
ConfigurationFileTypeIPMI = "ipmi"

"""
DeviceQueryType enumeration constants
Represents a particular device type as a short string.
These values apply to function parameters accepting device types.
"""
DeviceQueryTypeTarget = "targ"
DeviceQueryTypeLink = "link"
DeviceQueryTypeCompute = "comp"
DeviceQueryTypeFPGA = "fpga"
DeviceQueryTypeGPU = "gpu"
DeviceQueryTypeMemory = "mem"

"""
DeviceType enumeration constants
Represents a particular device type as a short string.
These values apply only to DeviceStatus and DeviceInfo
"""
DeviceTypeInfinibandLink = "infiniband_link"
DeviceTypeEthernetLink = "ethernet_link"
DeviceTypeFiberChannelLink = "fibrechannel_link"
DeviceTypeGPU = "gpu"
DeviceTypeSSD = "target/SSD"
DeviceTypeFPGA = "fpga"
DeviceTypeCompute = "compute"
DeviceTypeMemory = "mem"

"""
FabricToggleCompositeOption enumeration constants
TODO
"""
FabricToggleCompositeOptionAdd = "add"

"""
FabricType enumeration constants
Describes a particular fabric topology type
"""
FabricTypeGen3 = "gen3"
FabricTypeGen4 = "gen4"
FabricTypeNVOverFabric = "nvof"
FabricTypeGPUOverFabric = "gpuof"
FabricTypeGenZ = "genz"
FabricTypeNVMEOverFabric = "nvmeof"
FabricTypeIPBased = "ip_based"

"""
ImageType enumeration constants
Describes a type of software image file
"""
ImageTypeBoot = "boot"
ImageTypeFPGA = "fpga"
ImageTypeVNIC = "vnic"
ImageTypeUpgrade = "upgrade"
ImageTypeApplication = "application"

"""
LogType enumeration constants
Describes a log file type
"""
LogTypeSystem = "system"
LogTypeLiqid = "liqid"
LogTypeCombined = "combined"

"""
ManageableType enumeration constants
Describes a type of manageable entity
"""
ManageableTypeDevice = "ManageableDevice"
ManageableTypeCpuIpmiNetwork = "ManageableCpuIpmiNetworkConfig"
ManageableTypeEnclosureIpmiNetwork = "ManageableDeviceIpmiNetworkConfig"

"""
NorthboundApplicationType enumeration constants
Describes a northbound application type
"""
NorthboundApplicationTypeSlurm = "slurm"
NorthboundApplicationTypeKubernetes = "kubernetes"
NorthboundApplicationTypeRedfish = "redfish"
NorthboundApplicationTypeVAPI = "vapi"
NorthboundApplicationTypeIPMI = "ipmi"

"""
OperatingSystemType enumeration constants
Describes an operating system
"""
OperatingSystemTypeLinux = "linux"
OperatingSystemTypeFreeBSD = "FreeBSD"
OperatingSystemTypeWindows = "windows"
OperatingSystemTypeLiqidOS = "liqidos"
OperatingSystemTypeMaxOSX = "Apple Inc. Mac(TM) OSX operating system"
OperatingSystemTypeUnknown = "unknown"

"""
P2PType enumeration constants
Indicates whether P2P is enabled
"""
P2PTypeOff = "off"
P2PTypeOn = "on"

"""
PreDeviceType enumeration constants
Represents a particular device type as a short string.
These are values which apply to the PreDevice device type field.
"""
PreDeviceTypeCompute = "comp"
PreDeviceTypeFiberChannel = "fibr"
PreDeviceTypeFPGA = "fpga"
PreDeviceTypeGPU = "gpu"
PreDeviceTypeInfiniband = "infi"
PreDeviceTypeLink = "link"
PreDeviceTypeMemory = "mem"
PreDeviceTypeFabricChip = "plx"
PreDeviceTypeTarget = "targ"

"""
RunType enumeration constants
Describes the various run states for liqid
"""
RunTypeUp = "up"
RunTypeOn = "on"
RunTypeOff = "off"
RunTypeUnknown = "unknown"

"""
ToggleFlag enumeration constants
TODO
"""
ToggleFlagPermanent = "perm"
ToggleFlagDisappear = "disappear"
ToggleFlagActive = "active"
ToggleFlagHidden = "hidden"

"""
ToggleState enumeration constants
Describes a binary state of enablement/disablement
"""
ToggleStateEnabled = "enabled"
ToggleStateDisabled = "disabled"

"""
TokenType enumeration constants
A subset of DeviceType values used exclusively with tokens
"""
TokenTypeTarget = "targ"
TokenTypeLink = "link"
TokenTypeGPU = "gpu"
TokenTypeFPGA = "fpga"
