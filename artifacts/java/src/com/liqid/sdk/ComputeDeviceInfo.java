// File: ComputeDeviceInfo.java
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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * ComputeDeviceInfo
 * Contains non-status information regarding a compute device
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComputeDeviceInfo extends DeviceInfo {

    public static class ComputeDeviceInfoWrapper extends Wrapper<ComputeDeviceInfo>{}

    /**
     * Number of cores for this device
     */
    @JsonProperty("core_cnt")
    private String _coreCount = null;

    public String getCoreCount() {
        return _coreCount;
    }

    public ComputeDeviceInfo setCoreCount(String value) {
        _coreCount = value;
        return this;
    }

    /**
     * Cycle rate for this device
     */
    @JsonProperty("core_mhz")
    private Double _coreMHz = null;

    public Double getCoreMHz() {
        return _coreMHz;
    }

    public ComputeDeviceInfo setCoreMHz(Double value) {
        _coreMHz = value;
        return this;
    }

    /**
     * Dynamic RAM size
     */
    @JsonProperty("dram_size")
    private String _dramSize = null;

    public String getDRAMSize() {
        return _dramSize;
    }

    public ComputeDeviceInfo setDRAMSize(String value) {
        _dramSize = value;
        return this;
    }

    /**
     * Dynamic RAM type
     */
    @JsonProperty("dram_type")
    private String _dramType = null;

    public String getDRAMType() {
        return _dramType;
    }

    public ComputeDeviceInfo setDRAMType(String value) {
        _dramType = value;
        return this;
    }

    /**
     * Instruction set of this device
     */
    @JsonProperty("inst_set")
    private String _instructionSet = null;

    public String getInstructionSet() {
        return _instructionSet;
    }

    public ComputeDeviceInfo setInstructionSet(String value) {
        _instructionSet = value;
        return this;
    }

    /**
     * Number of threads supported by this device
     */
    @JsonProperty("thrd_cnt")
    private Integer _numberOfThreads = null;

    public Integer getNumberOfThreads() {
        return _numberOfThreads;
    }

    public ComputeDeviceInfo setNumberOfThreads(Integer value) {
        _numberOfThreads = value;
        return this;
    }

    /**
     * Socket description of this device
     */
    @JsonProperty("socket")
    private String _socket = null;

    public String getSocket() {
        return _socket;
    }

    public ComputeDeviceInfo setSocket(String value) {
        _socket = value;
        return this;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public ComputeDeviceInfo() {
    }

    /**
     * Parameterized Constructor
     */
    protected ComputeDeviceInfo(String busGeneration,
                                String busWidth,
                                String deviceClass,
                                String connectionType,
                                String deviceIdentifier,
                                String deviceState,
                                DeviceType deviceInfoType,
                                String fabricGlobalId,
                                FabricType fabricType,
                                String family,
                                String flags,
                                String firmwareRevision,
                                Integer index,
                                Coordinates location,
                                String model,
                                String name,
                                Coordinates owner,
                                String partNumber,
                                String pciDeviceId,
                                String pciVendorId,
                                Integer podId,
                                String serialNumber,
                                String sledId,
                                String userDescription,
                                String unique,
                                String vendor,
                                String coreCount,
                                Double coreMHz,
                                String dramSize,
                                String dramType,
                                String instructionSet,
                                String socket,
                                Integer numberOfThreads) {
        super(busGeneration, busWidth, deviceClass, connectionType, deviceIdentifier, deviceState, deviceInfoType, fabricGlobalId, fabricType, family, flags, firmwareRevision, index, location, model, name, owner, partNumber, pciDeviceId, pciVendorId, podId, serialNumber, sledId, userDescription, unique, vendor);
        _coreCount = coreCount;
        _coreMHz = coreMHz;
        _dramSize = dramSize;
        _dramType = dramType;
        _instructionSet = instructionSet;
        _socket = socket;
        _numberOfThreads = numberOfThreads;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_busGeneration:").append(getBusGeneration());
        sb.append(", ").append("_busWidth:").append(getBusWidth());
        sb.append(", ").append("_deviceClass:").append(getDeviceClass());
        sb.append(", ").append("_connectionType:").append(getConnectionType());
        sb.append(", ").append("_deviceIdentifier:").append(getDeviceIdentifier());
        sb.append(", ").append("_deviceState:").append(getDeviceState());
        sb.append(", ").append("_deviceInfoType:").append(getDeviceInfoType());
        sb.append(", ").append("_fabricGlobalId:").append(getFabricGlobalId());
        sb.append(", ").append("_fabricType:").append(getFabricType());
        sb.append(", ").append("_family:").append(getFamily());
        sb.append(", ").append("_flags:").append(getFlags());
        sb.append(", ").append("_firmwareRevision:").append(getFirmwareRevision());
        sb.append(", ").append("_index:").append(getIndex());
        sb.append(", ").append("_location:").append(getLocation());
        sb.append(", ").append("_model:").append(getModel());
        sb.append(", ").append("_name:").append(getName());
        sb.append(", ").append("_owner:").append(getOwner());
        sb.append(", ").append("_partNumber:").append(getPartNumber());
        sb.append(", ").append("_pciDeviceId:").append(getPCIDeviceId());
        sb.append(", ").append("_pciVendorId:").append(getPCIVendorId());
        sb.append(", ").append("_podId:").append(getPodId());
        sb.append(", ").append("_serialNumber:").append(getSerialNumber());
        sb.append(", ").append("_sledId:").append(getSledId());
        sb.append(", ").append("_userDescription:").append(getUserDescription());
        sb.append(", ").append("_unique:").append(getUnique());
        sb.append(", ").append("_vendor:").append(getVendor());
        sb.append(", ").append("_coreCount:").append(getCoreCount());
        sb.append(", ").append("_coreMHz:").append(getCoreMHz());
        sb.append(", ").append("_dramSize:").append(getDRAMSize());
        sb.append(", ").append("_dramType:").append(getDRAMType());
        sb.append(", ").append("_instructionSet:").append(getInstructionSet());
        sb.append(", ").append("_socket:").append(getSocket());
        sb.append(", ").append("_numberOfThreads:").append(getNumberOfThreads());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _busGeneration = null;
        private String _busWidth = null;
        private String _deviceClass = null;
        private String _connectionType = null;
        private String _deviceIdentifier = null;
        private String _deviceState = null;
        private DeviceType _deviceInfoType = null;
        private String _fabricGlobalId = null;
        private FabricType _fabricType = null;
        private String _family = null;
        private String _flags = null;
        private String _firmwareRevision = null;
        private Integer _index = null;
        private Coordinates _location = null;
        private String _model = null;
        private String _name = null;
        private Coordinates _owner = null;
        private String _partNumber = null;
        private String _pciDeviceId = null;
        private String _pciVendorId = null;
        private Integer _podId = -1;
        private String _serialNumber = null;
        private String _sledId = null;
        private String _userDescription = null;
        private String _unique = null;
        private String _vendor = null;
        private String _coreCount = null;
        private Double _coreMHz = null;
        private String _dramSize = null;
        private String _dramType = null;
        private String _instructionSet = null;
        private String _socket = null;
        private Integer _numberOfThreads = null;

        public Builder setBusGeneration(String value) { _busGeneration = value; return this; }
        public Builder setBusWidth(String value) { _busWidth = value; return this; }
        public Builder setDeviceClass(String value) { _deviceClass = value; return this; }
        public Builder setConnectionType(String value) { _connectionType = value; return this; }
        public Builder setDeviceIdentifier(String value) { _deviceIdentifier = value; return this; }
        public Builder setDeviceState(String value) { _deviceState = value; return this; }
        public Builder setDeviceInfoType(DeviceType value) { _deviceInfoType = value; return this; }
        public Builder setFabricGlobalId(String value) { _fabricGlobalId = value; return this; }
        public Builder setFabricType(FabricType value) { _fabricType = value; return this; }
        public Builder setFamily(String value) { _family = value; return this; }
        public Builder setFlags(String value) { _flags = value; return this; }
        public Builder setFirmwareRevision(String value) { _firmwareRevision = value; return this; }
        public Builder setIndex(Integer value) { _index = value; return this; }
        public Builder setLocation(Coordinates value) { _location = value; return this; }
        public Builder setModel(String value) { _model = value; return this; }
        public Builder setName(String value) { _name = value; return this; }
        public Builder setOwner(Coordinates value) { _owner = value; return this; }
        public Builder setPartNumber(String value) { _partNumber = value; return this; }
        public Builder setPCIDeviceId(String value) { _pciDeviceId = value; return this; }
        public Builder setPCIVendorId(String value) { _pciVendorId = value; return this; }
        public Builder setPodId(Integer value) { _podId = value; return this; }
        public Builder setSerialNumber(String value) { _serialNumber = value; return this; }
        public Builder setSledId(String value) { _sledId = value; return this; }
        public Builder setUserDescription(String value) { _userDescription = value; return this; }
        public Builder setUnique(String value) { _unique = value; return this; }
        public Builder setVendor(String value) { _vendor = value; return this; }
        public Builder setCoreCount(String value) { _coreCount = value; return this; }
        public Builder setCoreMHz(Double value) { _coreMHz = value; return this; }
        public Builder setDRAMSize(String value) { _dramSize = value; return this; }
        public Builder setDRAMType(String value) { _dramType = value; return this; }
        public Builder setInstructionSet(String value) { _instructionSet = value; return this; }
        public Builder setSocket(String value) { _socket = value; return this; }
        public Builder setNumberOfThreads(Integer value) { _numberOfThreads = value; return this; }

        public ComputeDeviceInfo build() {
            if (_busGeneration == null) {
                throw new RuntimeException("setBusGeneration() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_busWidth == null) {
                throw new RuntimeException("setBusWidth() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_deviceClass == null) {
                throw new RuntimeException("setDeviceClass() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_connectionType == null) {
                throw new RuntimeException("setConnectionType() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_deviceIdentifier == null) {
                throw new RuntimeException("setDeviceIdentifier() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_deviceState == null) {
                throw new RuntimeException("setDeviceState() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_deviceInfoType == null) {
                throw new RuntimeException("setDeviceInfoType() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_fabricGlobalId == null) {
                throw new RuntimeException("setFabricGlobalId() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_fabricType == null) {
                throw new RuntimeException("setFabricType() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_family == null) {
                throw new RuntimeException("setFamily() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_flags == null) {
                throw new RuntimeException("setFlags() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_firmwareRevision == null) {
                throw new RuntimeException("setFirmwareRevision() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_index == null) {
                throw new RuntimeException("setIndex() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_location == null) {
                throw new RuntimeException("setLocation() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_model == null) {
                throw new RuntimeException("setModel() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_name == null) {
                throw new RuntimeException("setName() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_owner == null) {
                throw new RuntimeException("setOwner() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_partNumber == null) {
                throw new RuntimeException("setPartNumber() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_pciDeviceId == null) {
                throw new RuntimeException("setPCIDeviceId() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_pciVendorId == null) {
                throw new RuntimeException("setPCIVendorId() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_serialNumber == null) {
                throw new RuntimeException("setSerialNumber() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_sledId == null) {
                throw new RuntimeException("setSledId() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_userDescription == null) {
                throw new RuntimeException("setUserDescription() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_unique == null) {
                throw new RuntimeException("setUnique() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_vendor == null) {
                throw new RuntimeException("setVendor() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_coreCount == null) {
                throw new RuntimeException("setCoreCount() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_coreMHz == null) {
                throw new RuntimeException("setCoreMHz() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_dramSize == null) {
                throw new RuntimeException("setDRAMSize() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_dramType == null) {
                throw new RuntimeException("setDRAMType() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_instructionSet == null) {
                throw new RuntimeException("setInstructionSet() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_socket == null) {
                throw new RuntimeException("setSocket() was not invoked in Builder for class ComputeDeviceInfo");
            }
            if (_numberOfThreads == null) {
                throw new RuntimeException("setNumberOfThreads() was not invoked in Builder for class ComputeDeviceInfo");
            }
            return new ComputeDeviceInfo(_busGeneration,
                                         _busWidth,
                                         _deviceClass,
                                         _connectionType,
                                         _deviceIdentifier,
                                         _deviceState,
                                         _deviceInfoType,
                                         _fabricGlobalId,
                                         _fabricType,
                                         _family,
                                         _flags,
                                         _firmwareRevision,
                                         _index,
                                         _location,
                                         _model,
                                         _name,
                                         _owner,
                                         _partNumber,
                                         _pciDeviceId,
                                         _pciVendorId,
                                         _podId,
                                         _serialNumber,
                                         _sledId,
                                         _userDescription,
                                         _unique,
                                         _vendor,
                                         _coreCount,
                                         _coreMHz,
                                         _dramSize,
                                         _dramType,
                                         _instructionSet,
                                         _socket,
                                         _numberOfThreads);
        }
    }
}
