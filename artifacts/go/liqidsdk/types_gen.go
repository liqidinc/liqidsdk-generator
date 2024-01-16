// File: types_gen.go
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.3.0
// This file was automatically generated - do not modify it directly.
//

package liqidsdk

import (
	"fmt"
	"strconv"
)

// AsynchronousInfo
// Reports an identifier of an asynchronous operation
type AsynchronousInfo struct {
	// AsynchronousId
	// Identifier to be used for polling the state of an asynchronous task
	AsynchronousId string `json:"async_id"`

	// DeviceId
	// Device identifier which is associated with the asynchronous task
	DeviceId string `json:"device_id"`
}

// NewAsynchronousInfo initializer for AsynchronousInfo struct
func NewAsynchronousInfo() AsynchronousInfo {
	obj := AsynchronousInfo{}
	return obj
}

// GetAsynchronousId retrieves the value as the preferred SDK type
func (s *AsynchronousInfo) GetAsynchronousId() string {
    return s.AsynchronousId
}

// SetAsynchronousId sets the value given a source of the preferred SDK type
func (s *AsynchronousInfo) SetAsynchronousId(value string) {
    s.AsynchronousId = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *AsynchronousInfo) GetDeviceId() string {
    return s.DeviceId
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *AsynchronousInfo) SetDeviceId(value string) {
    s.DeviceId = value
}

// AsynchronousInfoWrapper JSON body wrapper for AsynchronousInfo
type AsynchronousInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []AsynchronousInfo `json:"data"`
	} `json:"response"`
}

// AsynchronousStatus
// Reports the status of an asynchronous operation
type AsynchronousStatus struct {
	// ExecutionState
	// execution state of the asynchronous operation
	ExecutionState string `json:"command_execution_state"`

	// DeviceId
	// Identifier of the device to which this operation applies
	DeviceId string `json:"device_id"`

	// ExecutionDateTime
	// Timestamp of when the process was initiated
	ExecutionDateTime string `json:"execution_date"`
}

// NewAsynchronousStatus initializer for AsynchronousStatus struct
func NewAsynchronousStatus() AsynchronousStatus {
	obj := AsynchronousStatus{}
	return obj
}

// GetExecutionState retrieves the value as the preferred SDK type
func (s *AsynchronousStatus) GetExecutionState() string {
    return s.ExecutionState
}

// SetExecutionState sets the value given a source of the preferred SDK type
func (s *AsynchronousStatus) SetExecutionState(value string) {
    s.ExecutionState = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *AsynchronousStatus) GetDeviceId() string {
    return s.DeviceId
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *AsynchronousStatus) SetDeviceId(value string) {
    s.DeviceId = value
}

// GetExecutionDateTime retrieves the value as the preferred SDK type
func (s *AsynchronousStatus) GetExecutionDateTime() string {
    return s.ExecutionDateTime
}

// SetExecutionDateTime sets the value given a source of the preferred SDK type
func (s *AsynchronousStatus) SetExecutionDateTime(value string) {
    s.ExecutionDateTime = value
}

// AsynchronousStatusWrapper JSON body wrapper for AsynchronousStatus
type AsynchronousStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []AsynchronousStatus `json:"data"`
	} `json:"response"`
}

// AvailableCoordinates
// A description of an available REST target including IP addressing information and Liqid Coordinates
type AvailableCoordinates struct {
	// IPAddress
	// DNS name or dotted-decimal IP address of the REST target
	IPAddress string `json:"address"`

	// PortNumber
	// UDP port number of the REST target
	PortNumber int32 `json:"port"`

	// Coordinates
	// Liqid coordinates of the REST target
	Coordinates Coordinates `json:"coordinates"`
}

// NewAvailableCoordinates initializer for AvailableCoordinates struct
func NewAvailableCoordinates() AvailableCoordinates {
	obj := AvailableCoordinates{}
	obj.Coordinates = Coordinates{}
	return obj
}

// GetIPAddress retrieves the value as the preferred SDK type
func (s *AvailableCoordinates) GetIPAddress() string {
    return s.IPAddress
}

// SetIPAddress sets the value given a source of the preferred SDK type
func (s *AvailableCoordinates) SetIPAddress(value string) {
    s.IPAddress = value
}

// GetPortNumber retrieves the value as the preferred SDK type
func (s *AvailableCoordinates) GetPortNumber() int32 {
    return s.PortNumber
}

// SetPortNumber sets the value given a source of the preferred SDK type
func (s *AvailableCoordinates) SetPortNumber(value int32) {
    s.PortNumber = value
}

// GetCoordinates retrieves the value as the preferred SDK type
func (s *AvailableCoordinates) GetCoordinates() Coordinates {
    return s.Coordinates
}

// SetCoordinates sets the value given a source of the preferred SDK type
func (s *AvailableCoordinates) SetCoordinates(value Coordinates) {
    s.Coordinates = value
}

// AvailableCoordinatesWrapper JSON body wrapper for AvailableCoordinates
type AvailableCoordinatesWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []AvailableCoordinates `json:"data"`
	} `json:"response"`
}

// BackupResult
// Backups up the Director configuration
type BackupResult struct {
	// CommandLine
	// The command line used to perform the backup operation
	CommandLine string `json:"commandLine"`

	// StandardOutput
	// Content written to stdout during the backup operation
	StandardOutput string `json:"standardOutput"`

	// StandardError
	// Content written to stderr during the backup operation
	StandardError string `json:"standardError"`

	// ExitValue
	// Resulting value of the backup operation; zero indicates success
	ExitValue int32 `json:"exitValue"`
}

// NewBackupResult initializer for BackupResult struct
func NewBackupResult() BackupResult {
	obj := BackupResult{}
	return obj
}

// GetCommandLine retrieves the value as the preferred SDK type
func (s *BackupResult) GetCommandLine() string {
    return s.CommandLine
}

// SetCommandLine sets the value given a source of the preferred SDK type
func (s *BackupResult) SetCommandLine(value string) {
    s.CommandLine = value
}

// GetStandardOutput retrieves the value as the preferred SDK type
func (s *BackupResult) GetStandardOutput() string {
    return s.StandardOutput
}

// SetStandardOutput sets the value given a source of the preferred SDK type
func (s *BackupResult) SetStandardOutput(value string) {
    s.StandardOutput = value
}

// GetStandardError retrieves the value as the preferred SDK type
func (s *BackupResult) GetStandardError() string {
    return s.StandardError
}

// SetStandardError sets the value given a source of the preferred SDK type
func (s *BackupResult) SetStandardError(value string) {
    s.StandardError = value
}

// GetExitValue retrieves the value as the preferred SDK type
func (s *BackupResult) GetExitValue() int32 {
    return s.ExitValue
}

// SetExitValue sets the value given a source of the preferred SDK type
func (s *BackupResult) SetExitValue(value int32) {
    s.ExitValue = value
}

// BackupResultWrapper JSON body wrapper for BackupResult
type BackupResultWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []BackupResult `json:"data"`
	} `json:"response"`
}

// Coordinates
// Describes a unique Liqid coordinate.
// Most of the members of this entity are obsolete.
type Coordinates struct {
	// Rack
	// Obsolete - should always be zero
	Rack int32 `json:"rack"`

	// Shelf
	// Obsolete - should always be zero
	Shelf int32 `json:"shelf"`

	// Node
	// Describes the relative position of a particular coordinate-addressable Liqid entity.
	Node int32 `json:"node"`
}

// NewCoordinates initializer for Coordinates struct
func NewCoordinates() Coordinates {
	obj := Coordinates{}
	obj.Rack = 0
	obj.Shelf = 0
	return obj
}

// GetRack retrieves the value as the preferred SDK type
func (s *Coordinates) GetRack() int32 {
    return s.Rack
}

// SetRack sets the value given a source of the preferred SDK type
func (s *Coordinates) SetRack(value int32) {
    s.Rack = value
}

// GetShelf retrieves the value as the preferred SDK type
func (s *Coordinates) GetShelf() int32 {
    return s.Shelf
}

// SetShelf sets the value given a source of the preferred SDK type
func (s *Coordinates) SetShelf(value int32) {
    s.Shelf = value
}

// GetNode retrieves the value as the preferred SDK type
func (s *Coordinates) GetNode() int32 {
    return s.Node
}

// SetNode sets the value given a source of the preferred SDK type
func (s *Coordinates) SetNode(value int32) {
    s.Node = value
}

// CoordinatesWrapper JSON body wrapper for Coordinates
type CoordinatesWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Coordinates `json:"data"`
	} `json:"response"`
}

// ComputeDeviceInfo
// Contains non-status information regarding a compute device
type ComputeDeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`

	// CoreCount
	// Number of cores for this device
	CoreCount string `json:"core_cnt"`

	// CoreMHz
	// Cycle rate for this device
	CoreMHz float64 `json:"core_mhz"`

	// DRAMSize
	// Dynamic RAM size
	DRAMSize string `json:"dram_size"`

	// DRAMType
	// Dynamic RAM type
	DRAMType string `json:"dram_type"`

	// InstructionSet
	// Instruction set of this device
	InstructionSet string `json:"inst_set"`

	// Socket
	// Socket description of this device
	Socket string `json:"socket"`

	// NumberOfThreads
	// Number of threads supported by this device
	NumberOfThreads int32 `json:"thrd_cnt"`
}

// NewComputeDeviceInfo initializer for ComputeDeviceInfo struct
func NewComputeDeviceInfo() ComputeDeviceInfo {
	obj := ComputeDeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// GetCoreCount retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetCoreCount() string {
    return s.CoreCount
}

// SetCoreCount sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetCoreCount(value string) {
    s.CoreCount = value
}

// GetCoreMHz retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetCoreMHz() float64 {
    return s.CoreMHz
}

// SetCoreMHz sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetCoreMHz(value float64) {
    s.CoreMHz = value
}

// GetDRAMSize retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetDRAMSize() string {
    return s.DRAMSize
}

// SetDRAMSize sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetDRAMSize(value string) {
    s.DRAMSize = value
}

// GetDRAMType retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetDRAMType() string {
    return s.DRAMType
}

// SetDRAMType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetDRAMType(value string) {
    s.DRAMType = value
}

// GetInstructionSet retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetInstructionSet() string {
    return s.InstructionSet
}

// SetInstructionSet sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetInstructionSet(value string) {
    s.InstructionSet = value
}

// GetSocket retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetSocket() string {
    return s.Socket
}

// SetSocket sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetSocket(value string) {
    s.Socket = value
}

// GetNumberOfThreads retrieves the value as the preferred SDK type
func (s *ComputeDeviceInfo) GetNumberOfThreads() int32 {
    return s.NumberOfThreads
}

// SetNumberOfThreads sets the value given a source of the preferred SDK type
func (s *ComputeDeviceInfo) SetNumberOfThreads(value int32) {
    s.NumberOfThreads = value
}

// ComputeDeviceInfoWrapper JSON body wrapper for ComputeDeviceInfo
type ComputeDeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []ComputeDeviceInfo `json:"data"`
	} `json:"response"`
}

// ComputeDeviceStatus
// Compute Device Status Information
type ComputeDeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`

	// HConn
	// Internal value
	HConn string `json:"hconn"`

	// Unique
	// Internal value
	Unique string `json:"unique"`
}

// NewComputeDeviceStatus initializer for ComputeDeviceStatus struct
func NewComputeDeviceStatus() ComputeDeviceStatus {
	obj := ComputeDeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetHConn retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetHConn() int32 {
    result, _ := strconv.ParseInt(s.HConn[2:], 16, 32)
    return int32(result)
}

// SetHConn sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetHConn(value int32) {
    s.HConn = fmt.Sprintf("%d", value)
}

// GetUnique retrieves the value as the preferred SDK type
func (s *ComputeDeviceStatus) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *ComputeDeviceStatus) SetUnique(value string) {
    s.Unique = value
}

// ComputeDeviceStatusWrapper JSON body wrapper for ComputeDeviceStatus
type ComputeDeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []ComputeDeviceStatus `json:"data"`
	} `json:"response"`
}

// ConnectionHistory
// Describes one connection to a machine
type ConnectionHistory struct {
	// AttachTime
	// Time which device was attached
	AttachTime int64 `json:"atime"`

	// DeviceType
	// Type of the connecting device
	DeviceType PreDeviceType `json:"dev_type"`

	// DetachTime
	// Time which device was detached
	DetachTime int64 `json:"dtime"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// Free
	// 
	Free bool `json:"free"`

	// Name
	// 
	Name string `json:"name"`

	// OwnerGlobalId
	// Owner global identifier
	OwnerGlobalId string `json:"owner_gid"`

	// UserDescription
	// User-specified description
	UserDescription string `json:"udesc"`
}

// NewConnectionHistory initializer for ConnectionHistory struct
func NewConnectionHistory() ConnectionHistory {
	obj := ConnectionHistory{}
	return obj
}

// GetAttachTime retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetAttachTime() int64 {
    return s.AttachTime
}

// SetAttachTime sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetAttachTime(value int64) {
    s.AttachTime = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetDeviceType() PreDeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetDeviceType(value PreDeviceType) {
    s.DeviceType = value
}

// GetDetachTime retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetDetachTime() int64 {
    return s.DetachTime
}

// SetDetachTime sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetDetachTime(value int64) {
    s.DetachTime = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFree retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetFree() bool {
    return s.Free
}

// SetFree sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetFree(value bool) {
    s.Free = value
}

// GetName retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetName(value string) {
    s.Name = value
}

// GetOwnerGlobalId retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetOwnerGlobalId() string {
    return s.OwnerGlobalId
}

// SetOwnerGlobalId sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetOwnerGlobalId(value string) {
    s.OwnerGlobalId = value
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *ConnectionHistory) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *ConnectionHistory) SetUserDescription(value string) {
    s.UserDescription = value
}

// ConnectionHistoryWrapper JSON body wrapper for ConnectionHistory
type ConnectionHistoryWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []ConnectionHistory `json:"data"`
	} `json:"response"`
}

// Credentials
// Contains credentials necessary for managing some managed entity within the configuration (such as via IPMI)
type Credentials struct {
	// Principal
	// Also known as username, user-id, etc
	Principal string `json:"principal"`

	// Password
	// The password associated with the given principal
	Password string `json:"password"`
}

// NewCredentials initializer for Credentials struct
func NewCredentials() Credentials {
	obj := Credentials{}
	return obj
}

// GetPrincipal retrieves the value as the preferred SDK type
func (s *Credentials) GetPrincipal() string {
    return s.Principal
}

// SetPrincipal sets the value given a source of the preferred SDK type
func (s *Credentials) SetPrincipal(value string) {
    s.Principal = value
}

// GetPassword retrieves the value as the preferred SDK type
func (s *Credentials) GetPassword() string {
    return s.Password
}

// SetPassword sets the value given a source of the preferred SDK type
func (s *Credentials) SetPassword(value string) {
    s.Password = value
}

// CredentialsWrapper JSON body wrapper for Credentials
type CredentialsWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Credentials `json:"data"`
	} `json:"response"`
}

// DeviceCounters
// Counts of discovered devices by device type
type DeviceCounters struct {
	// CPUCount
	// Number of discovered compute devices
	CPUCount int32 `json:"comp_cnt"`

	// FPGACount
	// Number of discovered FPGA devices
	FPGACount int32 `json:"fpga_cnt"`

	// GPUCount
	// Number of discovered GPU devices
	GPUCount int32 `json:"gpu_cnt"`

	// LinkCount
	// Number of discovered link (e.g., network) devices
	LinkCount int32 `json:"link_cnt"`

	// PLXCount
	// Number of discovered PLX devices
	PLXCount int32 `json:"plx_cnt"`

	// TargetCount
	// Number of discovered target devices
	TargetCount int32 `json:"targ_cnt"`
}

// NewDeviceCounters initializer for DeviceCounters struct
func NewDeviceCounters() DeviceCounters {
	obj := DeviceCounters{}
	return obj
}

// GetCPUCount retrieves the value as the preferred SDK type
func (s *DeviceCounters) GetCPUCount() int32 {
    return s.CPUCount
}

// SetCPUCount sets the value given a source of the preferred SDK type
func (s *DeviceCounters) SetCPUCount(value int32) {
    s.CPUCount = value
}

// GetFPGACount retrieves the value as the preferred SDK type
func (s *DeviceCounters) GetFPGACount() int32 {
    return s.FPGACount
}

// SetFPGACount sets the value given a source of the preferred SDK type
func (s *DeviceCounters) SetFPGACount(value int32) {
    s.FPGACount = value
}

// GetGPUCount retrieves the value as the preferred SDK type
func (s *DeviceCounters) GetGPUCount() int32 {
    return s.GPUCount
}

// SetGPUCount sets the value given a source of the preferred SDK type
func (s *DeviceCounters) SetGPUCount(value int32) {
    s.GPUCount = value
}

// GetLinkCount retrieves the value as the preferred SDK type
func (s *DeviceCounters) GetLinkCount() int32 {
    return s.LinkCount
}

// SetLinkCount sets the value given a source of the preferred SDK type
func (s *DeviceCounters) SetLinkCount(value int32) {
    s.LinkCount = value
}

// GetPLXCount retrieves the value as the preferred SDK type
func (s *DeviceCounters) GetPLXCount() int32 {
    return s.PLXCount
}

// SetPLXCount sets the value given a source of the preferred SDK type
func (s *DeviceCounters) SetPLXCount(value int32) {
    s.PLXCount = value
}

// GetTargetCount retrieves the value as the preferred SDK type
func (s *DeviceCounters) GetTargetCount() int32 {
    return s.TargetCount
}

// SetTargetCount sets the value given a source of the preferred SDK type
func (s *DeviceCounters) SetTargetCount(value int32) {
    s.TargetCount = value
}

// DeviceCountersWrapper JSON body wrapper for DeviceCounters
type DeviceCountersWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DeviceCounters `json:"data"`
	} `json:"response"`
}

// DeviceInfo
// All information other than status, for a given device
type DeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`
}

// NewDeviceInfo initializer for DeviceInfo struct
func NewDeviceInfo() DeviceInfo {
	obj := DeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *DeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *DeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// DeviceInfoWrapper JSON body wrapper for DeviceInfo
type DeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DeviceInfo `json:"data"`
	} `json:"response"`
}

// DeviceStatus
// Status of a manageable device
type DeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`
}

// NewDeviceStatus initializer for DeviceStatus struct
func NewDeviceStatus() DeviceStatus {
	obj := DeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *DeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *DeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// DeviceStatusWrapper JSON body wrapper for DeviceStatus
type DeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DeviceStatus `json:"data"`
	} `json:"response"`
}

// DeviceTypeAndAttributes
// Describes the various attributes for a particular device type
type DeviceTypeAndAttributes struct {
	// DeviceType
	// A particular data type
	DeviceType DeviceType `json:"deviceType"`

	// Attributes
	// Array of string descriptors
	Attributes map[string][]string `json:"attributes"`
}

// NewDeviceTypeAndAttributes initializer for DeviceTypeAndAttributes struct
func NewDeviceTypeAndAttributes() DeviceTypeAndAttributes {
	obj := DeviceTypeAndAttributes{}
	return obj
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *DeviceTypeAndAttributes) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *DeviceTypeAndAttributes) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetAttributes retrieves the value as the preferred SDK type
func (s *DeviceTypeAndAttributes) GetAttributes() map[string][]string {
    return s.Attributes
}

// SetAttributes sets the value given a source of the preferred SDK type
func (s *DeviceTypeAndAttributes) SetAttributes(value map[string][]string) {
    s.Attributes = value
}

// DeviceTypeAndAttributesWrapper JSON body wrapper for DeviceTypeAndAttributes
type DeviceTypeAndAttributesWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DeviceTypeAndAttributes `json:"data"`
	} `json:"response"`
}

// DeviceUserDescription
// Wraps a user-provided description for a particular device
type DeviceUserDescription struct {
	// UserDescription
	// The actual description
	UserDescription string `json:"udesc"`
}

// NewDeviceUserDescription initializer for DeviceUserDescription struct
func NewDeviceUserDescription() DeviceUserDescription {
	obj := DeviceUserDescription{}
	return obj
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *DeviceUserDescription) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *DeviceUserDescription) SetUserDescription(value string) {
    s.UserDescription = value
}

// DeviceUserDescriptionWrapper JSON body wrapper for DeviceUserDescription
type DeviceUserDescriptionWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DeviceUserDescription `json:"data"`
	} `json:"response"`
}

// DigestInfo
// Describes information related to a newly-created digest message
type DigestInfo struct {
	// DigestId
	// A unique (internal) identifier for this digest message
	DigestId int32 `json:"digest_id"`

	// DigestKey
	// The digest key generated by the director to be associated with the given label
	// This is the value which should be used for authenticating subsequent REST API invocations.
	DigestKey string `json:"digest_key"`

	// DigestLabel
	// The digest label which is associated with the generated digest key.
	// This value should be used for deleting the digest message at the end of a client REST session.
	DigestLabel string `json:"digest_label"`
}

// NewDigestInfo initializer for DigestInfo struct
func NewDigestInfo() DigestInfo {
	obj := DigestInfo{}
	return obj
}

// GetDigestId retrieves the value as the preferred SDK type
func (s *DigestInfo) GetDigestId() int32 {
    return s.DigestId
}

// SetDigestId sets the value given a source of the preferred SDK type
func (s *DigestInfo) SetDigestId(value int32) {
    s.DigestId = value
}

// GetDigestKey retrieves the value as the preferred SDK type
func (s *DigestInfo) GetDigestKey() string {
    return s.DigestKey
}

// SetDigestKey sets the value given a source of the preferred SDK type
func (s *DigestInfo) SetDigestKey(value string) {
    s.DigestKey = value
}

// GetDigestLabel retrieves the value as the preferred SDK type
func (s *DigestInfo) GetDigestLabel() string {
    return s.DigestLabel
}

// SetDigestLabel sets the value given a source of the preferred SDK type
func (s *DigestInfo) SetDigestLabel(value string) {
    s.DigestLabel = value
}

// DigestInfoWrapper JSON body wrapper for DigestInfo
type DigestInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DigestInfo `json:"data"`
	} `json:"response"`
}

// DiscoveryToken
// Describes a single discovery token
type DiscoveryToken struct {
	// Token
	// The actual token
	Token string `json:"discovery_token"`

	// Index
	// Internal index value for the token
	Index int32 `json:"index"`
}

// NewDiscoveryToken initializer for DiscoveryToken struct
func NewDiscoveryToken() DiscoveryToken {
	obj := DiscoveryToken{}
	return obj
}

// GetToken retrieves the value as the preferred SDK type
func (s *DiscoveryToken) GetToken() string {
    return s.Token
}

// SetToken sets the value given a source of the preferred SDK type
func (s *DiscoveryToken) SetToken(value string) {
    s.Token = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *DiscoveryToken) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *DiscoveryToken) SetIndex(value int32) {
    s.Index = value
}

// DiscoveryTokenWrapper JSON body wrapper for DiscoveryToken
type DiscoveryTokenWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []DiscoveryToken `json:"data"`
	} `json:"response"`
}

// FabricItem
// Describes a Liqid entity, the aggregate of which comprises the fabric.
type FabricItem struct {
	// Name
	// Name associated with this entity.
	Name string `json:"name"`

	// Id
	// Node identifier of this entity.
	Id int32 `json:"id"`

	// ParentId
	// Node identifier of the entity directly above this node in the fabric hierarchy.
	ParentId int32 `json:"parentId"`

	// DeviceType
	// Describes the particular type of this device.
	DeviceType DeviceType `json:"deviceType"`

	// HardwareComponent
	// Describes the hardware characteristics of this device.
	HardwareComponent HardwareComponent `json:"hardwareComponent"`
}

// NewFabricItem initializer for FabricItem struct
func NewFabricItem() FabricItem {
	obj := FabricItem{}
	obj.HardwareComponent = HardwareComponent{}
	return obj
}

// GetName retrieves the value as the preferred SDK type
func (s *FabricItem) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *FabricItem) SetName(value string) {
    s.Name = value
}

// GetId retrieves the value as the preferred SDK type
func (s *FabricItem) GetId() int32 {
    return s.Id
}

// SetId sets the value given a source of the preferred SDK type
func (s *FabricItem) SetId(value int32) {
    s.Id = value
}

// GetParentId retrieves the value as the preferred SDK type
func (s *FabricItem) GetParentId() int32 {
    return s.ParentId
}

// SetParentId sets the value given a source of the preferred SDK type
func (s *FabricItem) SetParentId(value int32) {
    s.ParentId = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *FabricItem) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *FabricItem) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetHardwareComponent retrieves the value as the preferred SDK type
func (s *FabricItem) GetHardwareComponent() HardwareComponent {
    return s.HardwareComponent
}

// SetHardwareComponent sets the value given a source of the preferred SDK type
func (s *FabricItem) SetHardwareComponent(value HardwareComponent) {
    s.HardwareComponent = value
}

// FabricItemWrapper JSON body wrapper for FabricItem
type FabricItemWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []FabricItem `json:"data"`
	} `json:"response"`
}

// FabricToggleComposite
// Describes the result of a fabric change operation
type FabricToggleComposite struct {
	// Coordinates
	// Describes the LIQID coordinates of the director for the updated fabric
	Coordinates Coordinates `json:"coordinates"`

	// ControlFlag
	// Describes the value added to the fabric
	ControlFlag NameValuePair `json:"flag"`

	// Options
	// Describes the operation which was requested
	Options FabricToggleCompositeOption `json:"options"`
}

// NewFabricToggleComposite initializer for FabricToggleComposite struct
func NewFabricToggleComposite() FabricToggleComposite {
	obj := FabricToggleComposite{}
	obj.Coordinates = Coordinates{}
	obj.ControlFlag = NameValuePair{}
	return obj
}

// GetCoordinates retrieves the value as the preferred SDK type
func (s *FabricToggleComposite) GetCoordinates() Coordinates {
    return s.Coordinates
}

// SetCoordinates sets the value given a source of the preferred SDK type
func (s *FabricToggleComposite) SetCoordinates(value Coordinates) {
    s.Coordinates = value
}

// GetControlFlag retrieves the value as the preferred SDK type
func (s *FabricToggleComposite) GetControlFlag() NameValuePair {
    return s.ControlFlag
}

// SetControlFlag sets the value given a source of the preferred SDK type
func (s *FabricToggleComposite) SetControlFlag(value NameValuePair) {
    s.ControlFlag = value
}

// GetOptions retrieves the value as the preferred SDK type
func (s *FabricToggleComposite) GetOptions() FabricToggleCompositeOption {
    return s.Options
}

// SetOptions sets the value given a source of the preferred SDK type
func (s *FabricToggleComposite) SetOptions(value FabricToggleCompositeOption) {
    s.Options = value
}

// FabricToggleCompositeWrapper JSON body wrapper for FabricToggleComposite
type FabricToggleCompositeWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []FabricToggleComposite `json:"data"`
	} `json:"response"`
}

// FPGADeviceInfo
// Contains non-status information regarding an FPGA device
type FPGADeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`

	// CoreCount
	// Number of cores for this device
	CoreCount string `json:"core_cnt"`

	// CoreMHz
	// Cycle rate for this device
	CoreMHz float64 `json:"core_mhz"`

	// DRAMSize
	// Dynamic RAM size
	DRAMSize string `json:"dram_size"`

	// DRAMType
	// Dynamic RAM type
	DRAMType string `json:"dram_type"`

	// FPGASpeed
	// Speed for this device
	FPGASpeed string `json:"fpga_speed"`

	// NumberOfThreads
	// Number of threads supported by this device
	NumberOfThreads int32 `json:"thrd_cnt"`
}

// NewFPGADeviceInfo initializer for FPGADeviceInfo struct
func NewFPGADeviceInfo() FPGADeviceInfo {
	obj := FPGADeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// GetCoreCount retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetCoreCount() string {
    return s.CoreCount
}

// SetCoreCount sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetCoreCount(value string) {
    s.CoreCount = value
}

// GetCoreMHz retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetCoreMHz() float64 {
    return s.CoreMHz
}

// SetCoreMHz sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetCoreMHz(value float64) {
    s.CoreMHz = value
}

// GetDRAMSize retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetDRAMSize() string {
    return s.DRAMSize
}

// SetDRAMSize sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetDRAMSize(value string) {
    s.DRAMSize = value
}

// GetDRAMType retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetDRAMType() string {
    return s.DRAMType
}

// SetDRAMType sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetDRAMType(value string) {
    s.DRAMType = value
}

// GetFPGASpeed retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetFPGASpeed() string {
    return s.FPGASpeed
}

// SetFPGASpeed sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetFPGASpeed(value string) {
    s.FPGASpeed = value
}

// GetNumberOfThreads retrieves the value as the preferred SDK type
func (s *FPGADeviceInfo) GetNumberOfThreads() int32 {
    return s.NumberOfThreads
}

// SetNumberOfThreads sets the value given a source of the preferred SDK type
func (s *FPGADeviceInfo) SetNumberOfThreads(value int32) {
    s.NumberOfThreads = value
}

// FPGADeviceInfoWrapper JSON body wrapper for FPGADeviceInfo
type FPGADeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []FPGADeviceInfo `json:"data"`
	} `json:"response"`
}

// FPGADeviceStatus
// FPGA Device Status Information
type FPGADeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`

	// Unique
	// Internal value
	Unique string `json:"unique"`
}

// NewFPGADeviceStatus initializer for FPGADeviceStatus struct
func NewFPGADeviceStatus() FPGADeviceStatus {
	obj := FPGADeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *FPGADeviceStatus) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *FPGADeviceStatus) SetUnique(value string) {
    s.Unique = value
}

// FPGADeviceStatusWrapper JSON body wrapper for FPGADeviceStatus
type FPGADeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []FPGADeviceStatus `json:"data"`
	} `json:"response"`
}

// GPUDeviceInfo
// Contains non-status information regarding a GPU device
type GPUDeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`

	// CacheSize
	// Cache size for this device
	CacheSize string `json:"cache_size"`

	// CoreCount
	// Number of cores for this device
	CoreCount string `json:"core_cnt"`

	// CoreSpeed
	// Core speed for this device
	CoreSpeed string `json:"core_speed"`

	// DRAMSize
	// Dynamic RAM size
	DRAMSize string `json:"dram_size"`

	// DRAMType
	// Dynamic RAM type
	DRAMType string `json:"dram_type"`
}

// NewGPUDeviceInfo initializer for GPUDeviceInfo struct
func NewGPUDeviceInfo() GPUDeviceInfo {
	obj := GPUDeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// GetCacheSize retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetCacheSize() string {
    return s.CacheSize
}

// SetCacheSize sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetCacheSize(value string) {
    s.CacheSize = value
}

// GetCoreCount retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetCoreCount() string {
    return s.CoreCount
}

// SetCoreCount sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetCoreCount(value string) {
    s.CoreCount = value
}

// GetCoreSpeed retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetCoreSpeed() string {
    return s.CoreSpeed
}

// SetCoreSpeed sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetCoreSpeed(value string) {
    s.CoreSpeed = value
}

// GetDRAMSize retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetDRAMSize() string {
    return s.DRAMSize
}

// SetDRAMSize sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetDRAMSize(value string) {
    s.DRAMSize = value
}

// GetDRAMType retrieves the value as the preferred SDK type
func (s *GPUDeviceInfo) GetDRAMType() string {
    return s.DRAMType
}

// SetDRAMType sets the value given a source of the preferred SDK type
func (s *GPUDeviceInfo) SetDRAMType(value string) {
    s.DRAMType = value
}

// GPUDeviceInfoWrapper JSON body wrapper for GPUDeviceInfo
type GPUDeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GPUDeviceInfo `json:"data"`
	} `json:"response"`
}

// GPUDeviceStatus
// GPU Device Status Information
type GPUDeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`

	// Unique
	// Internal value
	Unique string `json:"unique"`
}

// NewGPUDeviceStatus initializer for GPUDeviceStatus struct
func NewGPUDeviceStatus() GPUDeviceStatus {
	obj := GPUDeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *GPUDeviceStatus) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *GPUDeviceStatus) SetUnique(value string) {
    s.Unique = value
}

// GPUDeviceStatusWrapper JSON body wrapper for GPUDeviceStatus
type GPUDeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GPUDeviceStatus `json:"data"`
	} `json:"response"`
}

// Group
// Describes a configured group which contains a free device pool and some number of configured machines.
// This struct does not contain information regarding the related entities; that information must be obtained via other functions/methods.
type Group struct {
	// FabricId
	// The identifier of the fabric to which this group belongs
	FabricId int32 `json:"fabr_id"`

	// GroupId
	// The unique (among the contained fabric) identifier of this group
	GroupId int32 `json:"grp_id"`

	// GroupName
	// The unique (among the contained fabric) name of this group
	GroupName string `json:"group_name"`

	// PodId
	// Obsolete - should always be -1
	PodId int32 `json:"pod_id"`
}

// NewGroup initializer for Group struct
func NewGroup() Group {
	obj := Group{}
	obj.PodId = -1
	return obj
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *Group) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *Group) SetFabricId(value int32) {
    s.FabricId = value
}

// GetGroupId retrieves the value as the preferred SDK type
func (s *Group) GetGroupId() int32 {
    return s.GroupId
}

// SetGroupId sets the value given a source of the preferred SDK type
func (s *Group) SetGroupId(value int32) {
    s.GroupId = value
}

// GetGroupName retrieves the value as the preferred SDK type
func (s *Group) GetGroupName() string {
    return s.GroupName
}

// SetGroupName sets the value given a source of the preferred SDK type
func (s *Group) SetGroupName(value string) {
    s.GroupName = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *Group) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *Group) SetPodId(value int32) {
    s.PodId = value
}

// GroupWrapper JSON body wrapper for Group
type GroupWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Group `json:"data"`
	} `json:"response"`
}

// GroupComputeDeviceRelator
// Describes a relation between a group and a compute device
type GroupComputeDeviceRelator struct {
	// DeviceStatus
	// A ComputeDeviceStatus entity for the device in the relation
	DeviceStatus ComputeDeviceStatus `json:"deviceStatus"`

	// Group
	// Group entity for the group in the relation
	Group Group `json:"group"`
}

// NewGroupComputeDeviceRelator initializer for GroupComputeDeviceRelator struct
func NewGroupComputeDeviceRelator() GroupComputeDeviceRelator {
	obj := GroupComputeDeviceRelator{}
	obj.DeviceStatus = ComputeDeviceStatus{}
	obj.Group = Group{}
	return obj
}

// GetDeviceStatus retrieves the value as the preferred SDK type
func (s *GroupComputeDeviceRelator) GetDeviceStatus() ComputeDeviceStatus {
    return s.DeviceStatus
}

// SetDeviceStatus sets the value given a source of the preferred SDK type
func (s *GroupComputeDeviceRelator) SetDeviceStatus(value ComputeDeviceStatus) {
    s.DeviceStatus = value
}

// GetGroup retrieves the value as the preferred SDK type
func (s *GroupComputeDeviceRelator) GetGroup() Group {
    return s.Group
}

// SetGroup sets the value given a source of the preferred SDK type
func (s *GroupComputeDeviceRelator) SetGroup(value Group) {
    s.Group = value
}

// GroupComputeDeviceRelatorWrapper JSON body wrapper for GroupComputeDeviceRelator
type GroupComputeDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupComputeDeviceRelator `json:"data"`
	} `json:"response"`
}

// GroupDetails
// Contains statistical information which is accumulated for the group.
// Does not contain relations with devices; that information resides in the various device relators.
type GroupDetails struct {
	// GroupId
	// System-unique identifier for the group
	GroupId int32 `json:"grp_id"`

	// GroupName
	// System-unique human-readable name of the group
	GroupName string `json:"group_name"`

	// CpuFrequency
	// Frequency of the CPUs in the group
	CpuFrequency float64 `json:"cpu-frequency"`

	// CpuCount
	// Number of CPUs in the group
	CpuCount int32 `json:"cpu-count"`

	// CpuLanes
	// Number of PCI lanes dedicated to CPUs for this group
	CpuLanes int32 `json:"cpu-lanes"`

	// CpuCoreCount
	// Number of CPU cores in the group
	CpuCoreCount int32 `json:"cpu-core-count"`

	// TotalDRAM
	// Total amount of dynamic RAM in the group
	TotalDRAM int32 `json:"total-dram"`

	// NetworkAdapterCount
	// Number of network adapters in the group
	NetworkAdapterCount int32 `json:"network-adapter-count"`

	// TotalThroughput
	// Total throughput for this group
	TotalThroughput string `json:"total-throughput"`

	// StorageDriveCount
	// Number of SSDs in the group
	StorageDriveCount int32 `json:"storage-drive-count"`

	// TotalCapacity
	// Total capacity of SSD storage in the group
	TotalCapacity int64 `json:"total-capacity"`

	// GPUCores
	// Number of GPU cores in the group
	GPUCores int32 `json:"gpu-cores"`

	// TotalMachines
	// Number of configured machines in the group
	TotalMachines int32 `json:"total-machines"`
}

// NewGroupDetails initializer for GroupDetails struct
func NewGroupDetails() GroupDetails {
	obj := GroupDetails{}
	return obj
}

// GetGroupId retrieves the value as the preferred SDK type
func (s *GroupDetails) GetGroupId() int32 {
    return s.GroupId
}

// SetGroupId sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetGroupId(value int32) {
    s.GroupId = value
}

// GetGroupName retrieves the value as the preferred SDK type
func (s *GroupDetails) GetGroupName() string {
    return s.GroupName
}

// SetGroupName sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetGroupName(value string) {
    s.GroupName = value
}

// GetCpuFrequency retrieves the value as the preferred SDK type
func (s *GroupDetails) GetCpuFrequency() float64 {
    return s.CpuFrequency
}

// SetCpuFrequency sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetCpuFrequency(value float64) {
    s.CpuFrequency = value
}

// GetCpuCount retrieves the value as the preferred SDK type
func (s *GroupDetails) GetCpuCount() int32 {
    return s.CpuCount
}

// SetCpuCount sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetCpuCount(value int32) {
    s.CpuCount = value
}

// GetCpuLanes retrieves the value as the preferred SDK type
func (s *GroupDetails) GetCpuLanes() int32 {
    return s.CpuLanes
}

// SetCpuLanes sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetCpuLanes(value int32) {
    s.CpuLanes = value
}

// GetCpuCoreCount retrieves the value as the preferred SDK type
func (s *GroupDetails) GetCpuCoreCount() int32 {
    return s.CpuCoreCount
}

// SetCpuCoreCount sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetCpuCoreCount(value int32) {
    s.CpuCoreCount = value
}

// GetTotalDRAM retrieves the value as the preferred SDK type
func (s *GroupDetails) GetTotalDRAM() int32 {
    return s.TotalDRAM
}

// SetTotalDRAM sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetTotalDRAM(value int32) {
    s.TotalDRAM = value
}

// GetNetworkAdapterCount retrieves the value as the preferred SDK type
func (s *GroupDetails) GetNetworkAdapterCount() int32 {
    return s.NetworkAdapterCount
}

// SetNetworkAdapterCount sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetNetworkAdapterCount(value int32) {
    s.NetworkAdapterCount = value
}

// GetTotalThroughput retrieves the value as the preferred SDK type
func (s *GroupDetails) GetTotalThroughput() string {
    return s.TotalThroughput
}

// SetTotalThroughput sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetTotalThroughput(value string) {
    s.TotalThroughput = value
}

// GetStorageDriveCount retrieves the value as the preferred SDK type
func (s *GroupDetails) GetStorageDriveCount() int32 {
    return s.StorageDriveCount
}

// SetStorageDriveCount sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetStorageDriveCount(value int32) {
    s.StorageDriveCount = value
}

// GetTotalCapacity retrieves the value as the preferred SDK type
func (s *GroupDetails) GetTotalCapacity() int64 {
    return s.TotalCapacity
}

// SetTotalCapacity sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetTotalCapacity(value int64) {
    s.TotalCapacity = value
}

// GetGPUCores retrieves the value as the preferred SDK type
func (s *GroupDetails) GetGPUCores() int32 {
    return s.GPUCores
}

// SetGPUCores sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetGPUCores(value int32) {
    s.GPUCores = value
}

// GetTotalMachines retrieves the value as the preferred SDK type
func (s *GroupDetails) GetTotalMachines() int32 {
    return s.TotalMachines
}

// SetTotalMachines sets the value given a source of the preferred SDK type
func (s *GroupDetails) SetTotalMachines(value int32) {
    s.TotalMachines = value
}

// GroupDetailsWrapper JSON body wrapper for GroupDetails
type GroupDetailsWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupDetails `json:"data"`
	} `json:"response"`
}

// GroupFPGADeviceRelator
// Describes a relation between a group and an FPGA device
type GroupFPGADeviceRelator struct {
	// DeviceStatus
	// FPGADeviceStatus entity for the device in the relation
	DeviceStatus FPGADeviceStatus `json:"deviceStatus"`

	// Group
	// Group entity for the group in the relation
	Group Group `json:"group"`
}

// NewGroupFPGADeviceRelator initializer for GroupFPGADeviceRelator struct
func NewGroupFPGADeviceRelator() GroupFPGADeviceRelator {
	obj := GroupFPGADeviceRelator{}
	obj.DeviceStatus = FPGADeviceStatus{}
	obj.Group = Group{}
	return obj
}

// GetDeviceStatus retrieves the value as the preferred SDK type
func (s *GroupFPGADeviceRelator) GetDeviceStatus() FPGADeviceStatus {
    return s.DeviceStatus
}

// SetDeviceStatus sets the value given a source of the preferred SDK type
func (s *GroupFPGADeviceRelator) SetDeviceStatus(value FPGADeviceStatus) {
    s.DeviceStatus = value
}

// GetGroup retrieves the value as the preferred SDK type
func (s *GroupFPGADeviceRelator) GetGroup() Group {
    return s.Group
}

// SetGroup sets the value given a source of the preferred SDK type
func (s *GroupFPGADeviceRelator) SetGroup(value Group) {
    s.Group = value
}

// GroupFPGADeviceRelatorWrapper JSON body wrapper for GroupFPGADeviceRelator
type GroupFPGADeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupFPGADeviceRelator `json:"data"`
	} `json:"response"`
}

// GroupGPUDeviceRelator
// Describes a relation between a group and an GPU device
type GroupGPUDeviceRelator struct {
	// DeviceStatus
	// GPUDeviceStatus entity for the device in the relation
	DeviceStatus GPUDeviceStatus `json:"deviceStatus"`

	// Group
	// Group entity for the group in the relation
	Group Group `json:"group"`
}

// NewGroupGPUDeviceRelator initializer for GroupGPUDeviceRelator struct
func NewGroupGPUDeviceRelator() GroupGPUDeviceRelator {
	obj := GroupGPUDeviceRelator{}
	obj.DeviceStatus = GPUDeviceStatus{}
	obj.Group = Group{}
	return obj
}

// GetDeviceStatus retrieves the value as the preferred SDK type
func (s *GroupGPUDeviceRelator) GetDeviceStatus() GPUDeviceStatus {
    return s.DeviceStatus
}

// SetDeviceStatus sets the value given a source of the preferred SDK type
func (s *GroupGPUDeviceRelator) SetDeviceStatus(value GPUDeviceStatus) {
    s.DeviceStatus = value
}

// GetGroup retrieves the value as the preferred SDK type
func (s *GroupGPUDeviceRelator) GetGroup() Group {
    return s.Group
}

// SetGroup sets the value given a source of the preferred SDK type
func (s *GroupGPUDeviceRelator) SetGroup(value Group) {
    s.Group = value
}

// GroupGPUDeviceRelatorWrapper JSON body wrapper for GroupGPUDeviceRelator
type GroupGPUDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupGPUDeviceRelator `json:"data"`
	} `json:"response"`
}

// GroupPool
// Describes a group pool
type GroupPool struct {
	// Coordinates
	// Liqid coordinates for the switch which controls this group pool
	Coordinates Coordinates `json:"coordinates"`

	// FabricId
	// Fabric identifier for the fabric which contains the group
	FabricId int32 `json:"fabr_id"`

	// GroupId
	// Unique identifier of the group
	GroupId int32 `json:"grp_id"`
}

// NewGroupPool initializer for GroupPool struct
func NewGroupPool() GroupPool {
	obj := GroupPool{}
	obj.Coordinates = Coordinates{}
	return obj
}

// GetCoordinates retrieves the value as the preferred SDK type
func (s *GroupPool) GetCoordinates() Coordinates {
    return s.Coordinates
}

// SetCoordinates sets the value given a source of the preferred SDK type
func (s *GroupPool) SetCoordinates(value Coordinates) {
    s.Coordinates = value
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *GroupPool) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *GroupPool) SetFabricId(value int32) {
    s.FabricId = value
}

// GetGroupId retrieves the value as the preferred SDK type
func (s *GroupPool) GetGroupId() int32 {
    return s.GroupId
}

// SetGroupId sets the value given a source of the preferred SDK type
func (s *GroupPool) SetGroupId(value int32) {
    s.GroupId = value
}

// GroupPoolWrapper JSON body wrapper for GroupPool
type GroupPoolWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupPool `json:"data"`
	} `json:"response"`
}

// GroupMemoryDeviceRelator
// Describes a relation between a group and a memory device
type GroupMemoryDeviceRelator struct {
	// DeviceStatus
	// MemoryDeviceStatus entity for the device in the relation
	DeviceStatus MemoryDeviceStatus `json:"deviceStatus"`

	// Group
	// Group entity for the group in the relation
	Group Group `json:"group"`
}

// NewGroupMemoryDeviceRelator initializer for GroupMemoryDeviceRelator struct
func NewGroupMemoryDeviceRelator() GroupMemoryDeviceRelator {
	obj := GroupMemoryDeviceRelator{}
	obj.DeviceStatus = MemoryDeviceStatus{}
	obj.Group = Group{}
	return obj
}

// GetDeviceStatus retrieves the value as the preferred SDK type
func (s *GroupMemoryDeviceRelator) GetDeviceStatus() MemoryDeviceStatus {
    return s.DeviceStatus
}

// SetDeviceStatus sets the value given a source of the preferred SDK type
func (s *GroupMemoryDeviceRelator) SetDeviceStatus(value MemoryDeviceStatus) {
    s.DeviceStatus = value
}

// GetGroup retrieves the value as the preferred SDK type
func (s *GroupMemoryDeviceRelator) GetGroup() Group {
    return s.Group
}

// SetGroup sets the value given a source of the preferred SDK type
func (s *GroupMemoryDeviceRelator) SetGroup(value Group) {
    s.Group = value
}

// GroupMemoryDeviceRelatorWrapper JSON body wrapper for GroupMemoryDeviceRelator
type GroupMemoryDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupMemoryDeviceRelator `json:"data"`
	} `json:"response"`
}

// GroupNetworkDeviceRelator
// Describes a relation between a group and a network device
type GroupNetworkDeviceRelator struct {
	// DeviceStatus
	// NetworkDeviceStatus entity for the device in the relation
	DeviceStatus NetworkDeviceStatus `json:"deviceStatus"`

	// Group
	// Group entity for the group in the relation
	Group Group `json:"group"`
}

// NewGroupNetworkDeviceRelator initializer for GroupNetworkDeviceRelator struct
func NewGroupNetworkDeviceRelator() GroupNetworkDeviceRelator {
	obj := GroupNetworkDeviceRelator{}
	obj.DeviceStatus = NetworkDeviceStatus{}
	obj.Group = Group{}
	return obj
}

// GetDeviceStatus retrieves the value as the preferred SDK type
func (s *GroupNetworkDeviceRelator) GetDeviceStatus() NetworkDeviceStatus {
    return s.DeviceStatus
}

// SetDeviceStatus sets the value given a source of the preferred SDK type
func (s *GroupNetworkDeviceRelator) SetDeviceStatus(value NetworkDeviceStatus) {
    s.DeviceStatus = value
}

// GetGroup retrieves the value as the preferred SDK type
func (s *GroupNetworkDeviceRelator) GetGroup() Group {
    return s.Group
}

// SetGroup sets the value given a source of the preferred SDK type
func (s *GroupNetworkDeviceRelator) SetGroup(value Group) {
    s.Group = value
}

// GroupNetworkDeviceRelatorWrapper JSON body wrapper for GroupNetworkDeviceRelator
type GroupNetworkDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupNetworkDeviceRelator `json:"data"`
	} `json:"response"`
}

// GroupStorageDeviceRelator
// Describes a relation between a group and a storage device
type GroupStorageDeviceRelator struct {
	// DeviceStatus
	// StorageDeviceStatus entity for the device in the relation
	DeviceStatus StorageDeviceStatus `json:"deviceStatus"`

	// Group
	// Group entity for the group in the relation
	Group Group `json:"group"`
}

// NewGroupStorageDeviceRelator initializer for GroupStorageDeviceRelator struct
func NewGroupStorageDeviceRelator() GroupStorageDeviceRelator {
	obj := GroupStorageDeviceRelator{}
	obj.DeviceStatus = StorageDeviceStatus{}
	obj.Group = Group{}
	return obj
}

// GetDeviceStatus retrieves the value as the preferred SDK type
func (s *GroupStorageDeviceRelator) GetDeviceStatus() StorageDeviceStatus {
    return s.DeviceStatus
}

// SetDeviceStatus sets the value given a source of the preferred SDK type
func (s *GroupStorageDeviceRelator) SetDeviceStatus(value StorageDeviceStatus) {
    s.DeviceStatus = value
}

// GetGroup retrieves the value as the preferred SDK type
func (s *GroupStorageDeviceRelator) GetGroup() Group {
    return s.Group
}

// SetGroup sets the value given a source of the preferred SDK type
func (s *GroupStorageDeviceRelator) SetGroup(value Group) {
    s.Group = value
}

// GroupStorageDeviceRelatorWrapper JSON body wrapper for GroupStorageDeviceRelator
type GroupStorageDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []GroupStorageDeviceRelator `json:"data"`
	} `json:"response"`
}

// HardwareComponent
// Describes the hardware details of a component
type HardwareComponent struct {
	// HardwareType
	// Hardware type
	HardwareType string `json:"type"`

	// Name
	// Hardware name
	Name string `json:"name"`

	// Flags
	// Hardware flags displayed as a hex string prefixed by 'ox'
	Flags string `json:"flags"`

	// State
	// Hardware state
	State string `json:"state"`

	// FabricId
	// Fabric identifier of the fabric to which this entity is connected.
	FabricId int32 `json:"fabr_id"`

	// FabricGlobalId
	// Fabric-unique global identifier to which this entity is connected.
	FabricGlobalId string `json:"fabr_gid"`

	// SwitchGlobalId
	// TODO
	SwitchGlobalId string `json:"swit_gid"`

	// PCIVendorId
	// Unique Vendor identifier
	PCIVendorId string `json:"vid"`

	// PCIDeviceId
	// Vendor-unique device identifier
	PCIDeviceId string `json:"did"`

	// Revision
	// Hardware revision string
	Revision string `json:"rev"`

	// PortCount
	// Number of ports for this component
	PortCount int32 `json:"port_cnt"`

	// Ports
	// Descriptions of the component ports
	Ports []Port `json:"ports"`

	// PCILaneCount
	// Number of PCI lanes for this component
	PCILaneCount int32 `json:"lanes"`

	// ReceiverErrorCount
	// Running count of errors received for this component
	ReceiverErrorCount int32 `json:"rcv_errs"`

	// BadTLPCount
	// Running count of bad TLPs for this component
	BadTLPCount int32 `json:"bad_tlp"`

	// BadDLLPCount
	// Running count of bad DLLPs for this component
	BadDLLPCount int32 `json:"bad_dllp"`

	// ErrorCount
	// Running count of all errors for this component
	ErrorCount int32 `json:"errs"`

	// IngressBytes
	// Running count of incoming bytes
	IngressBytes int32 `json:"ibytes"`

	// EgressBytes
	// Running count of outgoing bytes
	EgressBytes int32 `json:"ebytes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`
}

// NewHardwareComponent initializer for HardwareComponent struct
func NewHardwareComponent() HardwareComponent {
	obj := HardwareComponent{}
	obj.Ports = []Port{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	return obj
}

// GetHardwareType retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetHardwareType() string {
    return s.HardwareType
}

// SetHardwareType sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetHardwareType(value string) {
    s.HardwareType = value
}

// GetName retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetName(value string) {
    s.Name = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetState retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetState() string {
    return s.State
}

// SetState sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetState(value string) {
    s.State = value
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetPCIVendorId() int32 {
    result, _ := strconv.ParseInt(s.PCIVendorId[2:], 16, 16)
    return int32(result)
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetPCIVendorId(value int32) {
    s.PCIVendorId = fmt.Sprintf("%d", value)
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetPCIDeviceId() int32 {
    result, _ := strconv.ParseInt(s.PCIDeviceId[2:], 16, 16)
    return int32(result)
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetPCIDeviceId(value int32) {
    s.PCIDeviceId = fmt.Sprintf("%d", value)
}

// GetRevision retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetRevision() string {
    return s.Revision
}

// SetRevision sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetRevision(value string) {
    s.Revision = value
}

// GetPortCount retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetPortCount() int32 {
    return s.PortCount
}

// SetPortCount sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetPortCount(value int32) {
    s.PortCount = value
}

// GetPorts retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetPorts() []Port {
    return s.Ports
}

// SetPorts sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetPorts(value []Port) {
    s.Ports = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetReceiverErrorCount retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetReceiverErrorCount() int32 {
    return s.ReceiverErrorCount
}

// SetReceiverErrorCount sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetReceiverErrorCount(value int32) {
    s.ReceiverErrorCount = value
}

// GetBadTLPCount retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetBadTLPCount() int32 {
    return s.BadTLPCount
}

// SetBadTLPCount sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetBadTLPCount(value int32) {
    s.BadTLPCount = value
}

// GetBadDLLPCount retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetBadDLLPCount() int32 {
    return s.BadDLLPCount
}

// SetBadDLLPCount sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetBadDLLPCount(value int32) {
    s.BadDLLPCount = value
}

// GetErrorCount retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetErrorCount() int32 {
    return s.ErrorCount
}

// SetErrorCount sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetErrorCount(value int32) {
    s.ErrorCount = value
}

// GetIngressBytes retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetIngressBytes() int32 {
    return s.IngressBytes
}

// SetIngressBytes sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetIngressBytes(value int32) {
    s.IngressBytes = value
}

// GetEgressBytes retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetEgressBytes() int32 {
    return s.EgressBytes
}

// SetEgressBytes sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetEgressBytes(value int32) {
    s.EgressBytes = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetLocation(value Coordinates) {
    s.Location = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *HardwareComponent) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *HardwareComponent) SetOwner(value Coordinates) {
    s.Owner = value
}

// HardwareComponentWrapper JSON body wrapper for HardwareComponent
type HardwareComponentWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []HardwareComponent `json:"data"`
	} `json:"response"`
}

// Machine
// Describes a configured machine
type Machine struct {
	// Index
	// Internal value
	Index int32 `json:"index"`

	// MachineId
	// Unique identifier for this particular machine
	MachineId int32 `json:"mach_id"`

	// GroupId
	// Unique identifier of the group to which this machine belongs
	GroupId int32 `json:"grp_id"`

	// FabricId
	// Unique identifier of the fabric to which this machine belongs
	FabricId int32 `json:"fabr_id"`

	// FabricGlobalId
	// Fabric global identifier expressed in hexadecimal
	FabricGlobalId string `json:"fabr_gid"`

	// PortGlobalId
	// Port global identifier expressed in hexadecimal
	PortGlobalId string `json:"port_gid"`

	// SwitchGlobalId
	// Switch global identifier expressed in hexadecimal
	SwitchGlobalId string `json:"swit_gid"`

	// ComputeName
	// Name of the compute device associated with this machine
	ComputeName string `json:"comp_name"`

	// MachineName
	// Name of this machine
	MachineName string `json:"mach_name"`

	// P2PEnabled
	// Name of this machine
	P2PEnabled P2PType `json:"p2p"`

	// CreatedTimestamp
	// Date and time that this machine was created
	CreatedTimestamp int64 `json:"mtime"`

	// ConnectionHistory
	// Connection history for this machine
	// Expressed as an array of ConnectionHistory entities
	ConnectionHistory []ConnectionHistory `json:"connection_history"`
}

// NewMachine initializer for Machine struct
func NewMachine() Machine {
	obj := Machine{}
	obj.P2PEnabled = P2PTypeOff
	obj.ConnectionHistory = []ConnectionHistory{}
	return obj
}

// GetIndex retrieves the value as the preferred SDK type
func (s *Machine) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *Machine) SetIndex(value int32) {
    s.Index = value
}

// GetMachineId retrieves the value as the preferred SDK type
func (s *Machine) GetMachineId() int32 {
    return s.MachineId
}

// SetMachineId sets the value given a source of the preferred SDK type
func (s *Machine) SetMachineId(value int32) {
    s.MachineId = value
}

// GetGroupId retrieves the value as the preferred SDK type
func (s *Machine) GetGroupId() int32 {
    return s.GroupId
}

// SetGroupId sets the value given a source of the preferred SDK type
func (s *Machine) SetGroupId(value int32) {
    s.GroupId = value
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *Machine) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *Machine) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *Machine) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *Machine) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *Machine) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *Machine) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *Machine) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *Machine) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetComputeName retrieves the value as the preferred SDK type
func (s *Machine) GetComputeName() string {
    return s.ComputeName
}

// SetComputeName sets the value given a source of the preferred SDK type
func (s *Machine) SetComputeName(value string) {
    s.ComputeName = value
}

// GetMachineName retrieves the value as the preferred SDK type
func (s *Machine) GetMachineName() string {
    return s.MachineName
}

// SetMachineName sets the value given a source of the preferred SDK type
func (s *Machine) SetMachineName(value string) {
    s.MachineName = value
}

// GetP2PEnabled retrieves the value as the preferred SDK type
func (s *Machine) GetP2PEnabled() P2PType {
    return s.P2PEnabled
}

// SetP2PEnabled sets the value given a source of the preferred SDK type
func (s *Machine) SetP2PEnabled(value P2PType) {
    s.P2PEnabled = value
}

// GetCreatedTimestamp retrieves the value as the preferred SDK type
func (s *Machine) GetCreatedTimestamp() int64 {
    return s.CreatedTimestamp
}

// SetCreatedTimestamp sets the value given a source of the preferred SDK type
func (s *Machine) SetCreatedTimestamp(value int64) {
    s.CreatedTimestamp = value
}

// GetConnectionHistory retrieves the value as the preferred SDK type
func (s *Machine) GetConnectionHistory() []ConnectionHistory {
    return s.ConnectionHistory
}

// SetConnectionHistory sets the value given a source of the preferred SDK type
func (s *Machine) SetConnectionHistory(value []ConnectionHistory) {
    s.ConnectionHistory = value
}

// MachineWrapper JSON body wrapper for Machine
type MachineWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Machine `json:"data"`
	} `json:"response"`
}

// MachineComputeDeviceRelator
// Describes a relation between a machine and a compute device
type MachineComputeDeviceRelator struct {
	// GroupDeviceRelator
	// A GroupComputeDeviceRelator entity for the device in the relation
	GroupDeviceRelator GroupComputeDeviceRelator `json:"groupDeviceRelator"`

	// Machine
	// Machine entity for the machine in the relation
	Machine Machine `json:"machine"`
}

// NewMachineComputeDeviceRelator initializer for MachineComputeDeviceRelator struct
func NewMachineComputeDeviceRelator() MachineComputeDeviceRelator {
	obj := MachineComputeDeviceRelator{}
	obj.GroupDeviceRelator = GroupComputeDeviceRelator{}
	obj.Machine = Machine{}
	return obj
}

// GetGroupDeviceRelator retrieves the value as the preferred SDK type
func (s *MachineComputeDeviceRelator) GetGroupDeviceRelator() GroupComputeDeviceRelator {
    return s.GroupDeviceRelator
}

// SetGroupDeviceRelator sets the value given a source of the preferred SDK type
func (s *MachineComputeDeviceRelator) SetGroupDeviceRelator(value GroupComputeDeviceRelator) {
    s.GroupDeviceRelator = value
}

// GetMachine retrieves the value as the preferred SDK type
func (s *MachineComputeDeviceRelator) GetMachine() Machine {
    return s.Machine
}

// SetMachine sets the value given a source of the preferred SDK type
func (s *MachineComputeDeviceRelator) SetMachine(value Machine) {
    s.Machine = value
}

// MachineComputeDeviceRelatorWrapper JSON body wrapper for MachineComputeDeviceRelator
type MachineComputeDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineComputeDeviceRelator `json:"data"`
	} `json:"response"`
}

// MachineDetails
// Additional details for a particular machine
type MachineDetails struct {
	// CPUSocketsField
	// TODO
	CPUSocketsField string `json:"cpuSocketsField"`

	// MachineId
	// Unique machine identifier
	MachineId int32 `json:"mach_id"`

	// MachineName
	// Machine name
	MachineName string `json:"mach_name"`

	// CPUThreadCount
	// Number of CPU threads for the machine
	CPUThreadCount int32 `json:"cpu-threads"`

	// CPUFrequency
	// Cycle frequency for the CPU in this machine
	CPUFrequency string `json:"cpu-frequency"`

	// CPUCoreCount
	// Number of CPU cores for the machine
	CPUCoreCount int32 `json:"cpu-cores"`

	// CPUSockets
	// Describes the CPU sockets for this machine
	CPUSockets string `json:"cpu-sockets"`

	// DynamicRAM
	// Describes the dynamic RAM for this machine
	DynamicRAM string `json:"dram-memory"`

	// FabricConnect
	// TODO
	FabricConnect string `json:"fabric-connect"`

	// NetworkAdapterCount
	// Number of network adapters in the machine
	NetworkAdapterCount int32 `json:"network-adapter-count"`

	// TotalThroughput
	// TODO
	TotalThroughput string `json:"total-throughput"`

	// StorageDriveCount
	// Number of storage drives in the machine
	StorageDriveCount int32 `json:"storage-drive-count"`

	// TotalCapacity
	// Total capacity in this machine
	TotalCapacity int64 `json:"total-capacity"`

	// GPUCount
	// Number of GPUs connected to the machine
	GPUCount int32 `json:"gpu-count"`

	// GPUCoreCount
	// Number of GPU cores for the machine
	GPUCoreCount int32 `json:"gpu-cores"`

	// OperatingSystem
	// Operating system name
	OperatingSystem string `json:"os_name"`

	// BootImage
	// Description of the boot image
	BootImage string `json:"boot_image"`

	// BootDevice
	// Boot device
	BootDevice string `json:"boot_device"`

	// IPAddress
	// IP Address (or DNS name) of the machine
	IPAddress string `json:"ip_address"`

	// IPMIAddress
	// IP Address (or DNS name) of the IPMI management port for the machine
	IPMIAddress string `json:"ipmi_address"`

	// FPGACount
	// Number of FPGAs connected to the machine
	FPGACount int32 `json:"fpga-count"`

	// FPGASpeed
	// FPGA speed
	FPGASpeed string `json:"fpga-speed"`

	// FPGADRAMSize
	// FPGA dynamic RAM size
	FPGADRAMSize int32 `json:"fpga-dram-size"`

	// CreatedAt
	// Timestamp for when the machine was created
	CreatedAt int64 `json:"created"`

	// LastModifiedAt
	// Timestamp for when the machine was last modified
	LastModifiedAt int64 `json:"modified"`
}

// NewMachineDetails initializer for MachineDetails struct
func NewMachineDetails() MachineDetails {
	obj := MachineDetails{}
	return obj
}

// GetCPUSocketsField retrieves the value as the preferred SDK type
func (s *MachineDetails) GetCPUSocketsField() string {
    return s.CPUSocketsField
}

// SetCPUSocketsField sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetCPUSocketsField(value string) {
    s.CPUSocketsField = value
}

// GetMachineId retrieves the value as the preferred SDK type
func (s *MachineDetails) GetMachineId() int32 {
    return s.MachineId
}

// SetMachineId sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetMachineId(value int32) {
    s.MachineId = value
}

// GetMachineName retrieves the value as the preferred SDK type
func (s *MachineDetails) GetMachineName() string {
    return s.MachineName
}

// SetMachineName sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetMachineName(value string) {
    s.MachineName = value
}

// GetCPUThreadCount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetCPUThreadCount() int32 {
    return s.CPUThreadCount
}

// SetCPUThreadCount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetCPUThreadCount(value int32) {
    s.CPUThreadCount = value
}

// GetCPUFrequency retrieves the value as the preferred SDK type
func (s *MachineDetails) GetCPUFrequency() string {
    return s.CPUFrequency
}

// SetCPUFrequency sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetCPUFrequency(value string) {
    s.CPUFrequency = value
}

// GetCPUCoreCount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetCPUCoreCount() int32 {
    return s.CPUCoreCount
}

// SetCPUCoreCount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetCPUCoreCount(value int32) {
    s.CPUCoreCount = value
}

// GetCPUSockets retrieves the value as the preferred SDK type
func (s *MachineDetails) GetCPUSockets() string {
    return s.CPUSockets
}

// SetCPUSockets sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetCPUSockets(value string) {
    s.CPUSockets = value
}

// GetDynamicRAM retrieves the value as the preferred SDK type
func (s *MachineDetails) GetDynamicRAM() string {
    return s.DynamicRAM
}

// SetDynamicRAM sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetDynamicRAM(value string) {
    s.DynamicRAM = value
}

// GetFabricConnect retrieves the value as the preferred SDK type
func (s *MachineDetails) GetFabricConnect() string {
    return s.FabricConnect
}

// SetFabricConnect sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetFabricConnect(value string) {
    s.FabricConnect = value
}

// GetNetworkAdapterCount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetNetworkAdapterCount() int32 {
    return s.NetworkAdapterCount
}

// SetNetworkAdapterCount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetNetworkAdapterCount(value int32) {
    s.NetworkAdapterCount = value
}

// GetTotalThroughput retrieves the value as the preferred SDK type
func (s *MachineDetails) GetTotalThroughput() string {
    return s.TotalThroughput
}

// SetTotalThroughput sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetTotalThroughput(value string) {
    s.TotalThroughput = value
}

// GetStorageDriveCount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetStorageDriveCount() int32 {
    return s.StorageDriveCount
}

// SetStorageDriveCount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetStorageDriveCount(value int32) {
    s.StorageDriveCount = value
}

// GetTotalCapacity retrieves the value as the preferred SDK type
func (s *MachineDetails) GetTotalCapacity() int64 {
    return s.TotalCapacity
}

// SetTotalCapacity sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetTotalCapacity(value int64) {
    s.TotalCapacity = value
}

// GetGPUCount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetGPUCount() int32 {
    return s.GPUCount
}

// SetGPUCount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetGPUCount(value int32) {
    s.GPUCount = value
}

// GetGPUCoreCount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetGPUCoreCount() int32 {
    return s.GPUCoreCount
}

// SetGPUCoreCount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetGPUCoreCount(value int32) {
    s.GPUCoreCount = value
}

// GetOperatingSystem retrieves the value as the preferred SDK type
func (s *MachineDetails) GetOperatingSystem() string {
    return s.OperatingSystem
}

// SetOperatingSystem sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetOperatingSystem(value string) {
    s.OperatingSystem = value
}

// GetBootImage retrieves the value as the preferred SDK type
func (s *MachineDetails) GetBootImage() string {
    return s.BootImage
}

// SetBootImage sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetBootImage(value string) {
    s.BootImage = value
}

// GetBootDevice retrieves the value as the preferred SDK type
func (s *MachineDetails) GetBootDevice() string {
    return s.BootDevice
}

// SetBootDevice sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetBootDevice(value string) {
    s.BootDevice = value
}

// GetIPAddress retrieves the value as the preferred SDK type
func (s *MachineDetails) GetIPAddress() string {
    return s.IPAddress
}

// SetIPAddress sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetIPAddress(value string) {
    s.IPAddress = value
}

// GetIPMIAddress retrieves the value as the preferred SDK type
func (s *MachineDetails) GetIPMIAddress() string {
    return s.IPMIAddress
}

// SetIPMIAddress sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetIPMIAddress(value string) {
    s.IPMIAddress = value
}

// GetFPGACount retrieves the value as the preferred SDK type
func (s *MachineDetails) GetFPGACount() int32 {
    return s.FPGACount
}

// SetFPGACount sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetFPGACount(value int32) {
    s.FPGACount = value
}

// GetFPGASpeed retrieves the value as the preferred SDK type
func (s *MachineDetails) GetFPGASpeed() string {
    return s.FPGASpeed
}

// SetFPGASpeed sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetFPGASpeed(value string) {
    s.FPGASpeed = value
}

// GetFPGADRAMSize retrieves the value as the preferred SDK type
func (s *MachineDetails) GetFPGADRAMSize() int32 {
    return s.FPGADRAMSize
}

// SetFPGADRAMSize sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetFPGADRAMSize(value int32) {
    s.FPGADRAMSize = value
}

// GetCreatedAt retrieves the value as the preferred SDK type
func (s *MachineDetails) GetCreatedAt() int64 {
    return s.CreatedAt
}

// SetCreatedAt sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetCreatedAt(value int64) {
    s.CreatedAt = value
}

// GetLastModifiedAt retrieves the value as the preferred SDK type
func (s *MachineDetails) GetLastModifiedAt() int64 {
    return s.LastModifiedAt
}

// SetLastModifiedAt sets the value given a source of the preferred SDK type
func (s *MachineDetails) SetLastModifiedAt(value int64) {
    s.LastModifiedAt = value
}

// MachineDetailsWrapper JSON body wrapper for MachineDetails
type MachineDetailsWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineDetails `json:"data"`
	} `json:"response"`
}

// MachineFPGADeviceRelator
// Describes a relation between a machine and a FPGA device
type MachineFPGADeviceRelator struct {
	// GroupDeviceRelator
	// A GroupFPGADeviceRelator entity for the device in the relation
	GroupDeviceRelator GroupFPGADeviceRelator `json:"groupDeviceRelator"`

	// Machine
	// Machine entity for the machine in the relation
	Machine Machine `json:"machine"`
}

// NewMachineFPGADeviceRelator initializer for MachineFPGADeviceRelator struct
func NewMachineFPGADeviceRelator() MachineFPGADeviceRelator {
	obj := MachineFPGADeviceRelator{}
	obj.GroupDeviceRelator = GroupFPGADeviceRelator{}
	obj.Machine = Machine{}
	return obj
}

// GetGroupDeviceRelator retrieves the value as the preferred SDK type
func (s *MachineFPGADeviceRelator) GetGroupDeviceRelator() GroupFPGADeviceRelator {
    return s.GroupDeviceRelator
}

// SetGroupDeviceRelator sets the value given a source of the preferred SDK type
func (s *MachineFPGADeviceRelator) SetGroupDeviceRelator(value GroupFPGADeviceRelator) {
    s.GroupDeviceRelator = value
}

// GetMachine retrieves the value as the preferred SDK type
func (s *MachineFPGADeviceRelator) GetMachine() Machine {
    return s.Machine
}

// SetMachine sets the value given a source of the preferred SDK type
func (s *MachineFPGADeviceRelator) SetMachine(value Machine) {
    s.Machine = value
}

// MachineFPGADeviceRelatorWrapper JSON body wrapper for MachineFPGADeviceRelator
type MachineFPGADeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineFPGADeviceRelator `json:"data"`
	} `json:"response"`
}

// MachineGPUDeviceRelator
// Describes a relation between a machine and a GPU device
type MachineGPUDeviceRelator struct {
	// GroupDeviceRelator
	// A GroupGPUDeviceRelator entity for the device in the relation
	GroupDeviceRelator GroupGPUDeviceRelator `json:"groupDeviceRelator"`

	// Machine
	// Machine entity for the machine in the relation
	Machine Machine `json:"machine"`
}

// NewMachineGPUDeviceRelator initializer for MachineGPUDeviceRelator struct
func NewMachineGPUDeviceRelator() MachineGPUDeviceRelator {
	obj := MachineGPUDeviceRelator{}
	obj.GroupDeviceRelator = GroupGPUDeviceRelator{}
	obj.Machine = Machine{}
	return obj
}

// GetGroupDeviceRelator retrieves the value as the preferred SDK type
func (s *MachineGPUDeviceRelator) GetGroupDeviceRelator() GroupGPUDeviceRelator {
    return s.GroupDeviceRelator
}

// SetGroupDeviceRelator sets the value given a source of the preferred SDK type
func (s *MachineGPUDeviceRelator) SetGroupDeviceRelator(value GroupGPUDeviceRelator) {
    s.GroupDeviceRelator = value
}

// GetMachine retrieves the value as the preferred SDK type
func (s *MachineGPUDeviceRelator) GetMachine() Machine {
    return s.Machine
}

// SetMachine sets the value given a source of the preferred SDK type
func (s *MachineGPUDeviceRelator) SetMachine(value Machine) {
    s.Machine = value
}

// MachineGPUDeviceRelatorWrapper JSON body wrapper for MachineGPUDeviceRelator
type MachineGPUDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineGPUDeviceRelator `json:"data"`
	} `json:"response"`
}

// MachineMemoryDeviceRelator
// Describes a relation between a machine and a memory device
type MachineMemoryDeviceRelator struct {
	// GroupDeviceRelator
	// A GroupMemoryDeviceRelator entity for the device in the relation
	GroupDeviceRelator GroupMemoryDeviceRelator `json:"groupDeviceRelator"`

	// Machine
	// Machine entity for the machine in the relation
	Machine Machine `json:"machine"`
}

// NewMachineMemoryDeviceRelator initializer for MachineMemoryDeviceRelator struct
func NewMachineMemoryDeviceRelator() MachineMemoryDeviceRelator {
	obj := MachineMemoryDeviceRelator{}
	obj.GroupDeviceRelator = GroupMemoryDeviceRelator{}
	obj.Machine = Machine{}
	return obj
}

// GetGroupDeviceRelator retrieves the value as the preferred SDK type
func (s *MachineMemoryDeviceRelator) GetGroupDeviceRelator() GroupMemoryDeviceRelator {
    return s.GroupDeviceRelator
}

// SetGroupDeviceRelator sets the value given a source of the preferred SDK type
func (s *MachineMemoryDeviceRelator) SetGroupDeviceRelator(value GroupMemoryDeviceRelator) {
    s.GroupDeviceRelator = value
}

// GetMachine retrieves the value as the preferred SDK type
func (s *MachineMemoryDeviceRelator) GetMachine() Machine {
    return s.Machine
}

// SetMachine sets the value given a source of the preferred SDK type
func (s *MachineMemoryDeviceRelator) SetMachine(value Machine) {
    s.Machine = value
}

// MachineMemoryDeviceRelatorWrapper JSON body wrapper for MachineMemoryDeviceRelator
type MachineMemoryDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineMemoryDeviceRelator `json:"data"`
	} `json:"response"`
}

// MachineNetworkDeviceRelator
// Describes a relation between a machine and a network device
type MachineNetworkDeviceRelator struct {
	// GroupDeviceRelator
	// A GroupNetworkDeviceRelator entity for the device in the relation
	GroupDeviceRelator GroupNetworkDeviceRelator `json:"groupDeviceRelator"`

	// Machine
	// Machine entity for the machine in the relation
	Machine Machine `json:"machine"`
}

// NewMachineNetworkDeviceRelator initializer for MachineNetworkDeviceRelator struct
func NewMachineNetworkDeviceRelator() MachineNetworkDeviceRelator {
	obj := MachineNetworkDeviceRelator{}
	obj.GroupDeviceRelator = GroupNetworkDeviceRelator{}
	obj.Machine = Machine{}
	return obj
}

// GetGroupDeviceRelator retrieves the value as the preferred SDK type
func (s *MachineNetworkDeviceRelator) GetGroupDeviceRelator() GroupNetworkDeviceRelator {
    return s.GroupDeviceRelator
}

// SetGroupDeviceRelator sets the value given a source of the preferred SDK type
func (s *MachineNetworkDeviceRelator) SetGroupDeviceRelator(value GroupNetworkDeviceRelator) {
    s.GroupDeviceRelator = value
}

// GetMachine retrieves the value as the preferred SDK type
func (s *MachineNetworkDeviceRelator) GetMachine() Machine {
    return s.Machine
}

// SetMachine sets the value given a source of the preferred SDK type
func (s *MachineNetworkDeviceRelator) SetMachine(value Machine) {
    s.Machine = value
}

// MachineNetworkDeviceRelatorWrapper JSON body wrapper for MachineNetworkDeviceRelator
type MachineNetworkDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineNetworkDeviceRelator `json:"data"`
	} `json:"response"`
}

// MachineStorageDeviceRelator
// Describes a relation between a machine and a storage device
type MachineStorageDeviceRelator struct {
	// GroupDeviceRelator
	// A GroupStorageDeviceRelator entity for the device in the relation
	GroupDeviceRelator GroupStorageDeviceRelator `json:"groupDeviceRelator"`

	// Machine
	// Machine entity for the machine in the relation
	Machine Machine `json:"machine"`
}

// NewMachineStorageDeviceRelator initializer for MachineStorageDeviceRelator struct
func NewMachineStorageDeviceRelator() MachineStorageDeviceRelator {
	obj := MachineStorageDeviceRelator{}
	obj.GroupDeviceRelator = GroupStorageDeviceRelator{}
	obj.Machine = Machine{}
	return obj
}

// GetGroupDeviceRelator retrieves the value as the preferred SDK type
func (s *MachineStorageDeviceRelator) GetGroupDeviceRelator() GroupStorageDeviceRelator {
    return s.GroupDeviceRelator
}

// SetGroupDeviceRelator sets the value given a source of the preferred SDK type
func (s *MachineStorageDeviceRelator) SetGroupDeviceRelator(value GroupStorageDeviceRelator) {
    s.GroupDeviceRelator = value
}

// GetMachine retrieves the value as the preferred SDK type
func (s *MachineStorageDeviceRelator) GetMachine() Machine {
    return s.Machine
}

// SetMachine sets the value given a source of the preferred SDK type
func (s *MachineStorageDeviceRelator) SetMachine(value Machine) {
    s.Machine = value
}

// MachineStorageDeviceRelatorWrapper JSON body wrapper for MachineStorageDeviceRelator
type MachineStorageDeviceRelatorWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MachineStorageDeviceRelator `json:"data"`
	} `json:"response"`
}

// ManagedEntity
// Describes information regarding a particular vendor and model of a manageable device
type ManagedEntity struct {
	// DeviceType
	// Type of the device
	DeviceType string `json:"device_type"`

	// Description
	// Name of the managed entity
	Description string `json:"description"`

	// PCIVendorId
	// Unique identity of the PCI vendor
	PCIVendorId string `json:"vid"`

	// PCIDeviceId
	// Vendor-unique identity of the PCI device
	PCIDeviceId string `json:"did"`

	// Model
	// Describes the model of the device
	Model string `json:"model"`

	// NumberOfCores
	// Number of cores for CPU entities
	NumberOfCores int32 `json:"cores"`

	// NumberOfThreads
	// Number of threads for CPU entities
	NumberOfThreads int32 `json:"threads"`

	// Speed
	// Cycle speed of the entity
	Speed int32 `json:"speed"`

	// Capacity
	// Capacity of the entity
	Capacity int32 `json:"capacity"`

	// SRIOVEnabled
	// Indicates whether Single-Root IO Virtualization (SRIOV) is enabled for this entity
	SRIOVEnabled bool `json:"sriov"`

	// ManagedEntityType
	// Indicates the type of this managed entity
	ManagedEntityType string `json:"type"`

	// Unique
	// TODO
	Unique string `json:"unique"`

	// CoreMHZ
	// Cycle speed of cpu entity
	CoreMHZ int32 `json:"core_mhz"`

	// DRAMSize
	// Size of Dynamic RAM
	DRAMSize int32 `json:"dram_size"`

	// DRAMType
	// Type of Dynamic RAM
	DRAMType string `json:"dram_type"`

	// Manufacturer
	// Manufacturer/vendor name
	Manufacturer string `json:"device_manufacturer"`

	// DiscoveryToken
	// A portion of the PCI device identification string which can be used to determine device type
	DiscoveryToken string `json:"discovery_token"`

	// CompanionDevice
	// Known values are 'no' and 'multi'
	CompanionDevice string `json:"companion_device"`

	// EntryDescription
	// Describes the multi-variate state of this entry
	EntryDescription ManagedEntityState `json:"entry_description"`
}

// NewManagedEntity initializer for ManagedEntity struct
func NewManagedEntity() ManagedEntity {
	obj := ManagedEntity{}
	obj.EntryDescription = ManagedEntityState{}
	return obj
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetDeviceType() string {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetDeviceType(value string) {
    s.DeviceType = value
}

// GetDescription retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetDescription() string {
    return s.Description
}

// SetDescription sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetDescription(value string) {
    s.Description = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetModel(value string) {
    s.Model = value
}

// GetNumberOfCores retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetNumberOfCores() int32 {
    return s.NumberOfCores
}

// SetNumberOfCores sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetNumberOfCores(value int32) {
    s.NumberOfCores = value
}

// GetNumberOfThreads retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetNumberOfThreads() int32 {
    return s.NumberOfThreads
}

// SetNumberOfThreads sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetNumberOfThreads(value int32) {
    s.NumberOfThreads = value
}

// GetSpeed retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetSpeed() int32 {
    return s.Speed
}

// SetSpeed sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetSpeed(value int32) {
    s.Speed = value
}

// GetCapacity retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetCapacity() int32 {
    return s.Capacity
}

// SetCapacity sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetCapacity(value int32) {
    s.Capacity = value
}

// GetSRIOVEnabled retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetSRIOVEnabled() bool {
    return s.SRIOVEnabled
}

// SetSRIOVEnabled sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetSRIOVEnabled(value bool) {
    s.SRIOVEnabled = value
}

// GetManagedEntityType retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetManagedEntityType() string {
    return s.ManagedEntityType
}

// SetManagedEntityType sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetManagedEntityType(value string) {
    s.ManagedEntityType = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetUnique(value string) {
    s.Unique = value
}

// GetCoreMHZ retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetCoreMHZ() int32 {
    return s.CoreMHZ
}

// SetCoreMHZ sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetCoreMHZ(value int32) {
    s.CoreMHZ = value
}

// GetDRAMSize retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetDRAMSize() int32 {
    return s.DRAMSize
}

// SetDRAMSize sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetDRAMSize(value int32) {
    s.DRAMSize = value
}

// GetDRAMType retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetDRAMType() string {
    return s.DRAMType
}

// SetDRAMType sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetDRAMType(value string) {
    s.DRAMType = value
}

// GetManufacturer retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetManufacturer() string {
    return s.Manufacturer
}

// SetManufacturer sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetManufacturer(value string) {
    s.Manufacturer = value
}

// GetDiscoveryToken retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetDiscoveryToken() string {
    return s.DiscoveryToken
}

// SetDiscoveryToken sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetDiscoveryToken(value string) {
    s.DiscoveryToken = value
}

// GetCompanionDevice retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetCompanionDevice() string {
    return s.CompanionDevice
}

// SetCompanionDevice sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetCompanionDevice(value string) {
    s.CompanionDevice = value
}

// GetEntryDescription retrieves the value as the preferred SDK type
func (s *ManagedEntity) GetEntryDescription() ManagedEntityState {
    return s.EntryDescription
}

// SetEntryDescription sets the value given a source of the preferred SDK type
func (s *ManagedEntity) SetEntryDescription(value ManagedEntityState) {
    s.EntryDescription = value
}

// ManagedEntityWrapper JSON body wrapper for ManagedEntity
type ManagedEntityWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []ManagedEntity `json:"data"`
	} `json:"response"`
}

// ManagedEntityState
// Describes the state of a managed entity entry
type ManagedEntityState struct {
	// Active
	// Indicates whether the entity entry is active
	Active bool `json:"active"`

	// Required
	// Indicates whether the entity entry is required
	Required bool `json:"required"`
}

// NewManagedEntityState initializer for ManagedEntityState struct
func NewManagedEntityState() ManagedEntityState {
	obj := ManagedEntityState{}
	return obj
}

// GetActive retrieves the value as the preferred SDK type
func (s *ManagedEntityState) GetActive() bool {
    return s.Active
}

// SetActive sets the value given a source of the preferred SDK type
func (s *ManagedEntityState) SetActive(value bool) {
    s.Active = value
}

// GetRequired retrieves the value as the preferred SDK type
func (s *ManagedEntityState) GetRequired() bool {
    return s.Required
}

// SetRequired sets the value given a source of the preferred SDK type
func (s *ManagedEntityState) SetRequired(value bool) {
    s.Required = value
}

// ManagedEntityStateWrapper JSON body wrapper for ManagedEntityState
type ManagedEntityStateWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []ManagedEntityState `json:"data"`
	} `json:"response"`
}

// MemoryDeviceInfo
// Contains non-status information regarding a memory device
type MemoryDeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`

	// Capacity
	// Capacity of the memory device
	Capacity string `json:"capacity"`
}

// NewMemoryDeviceInfo initializer for MemoryDeviceInfo struct
func NewMemoryDeviceInfo() MemoryDeviceInfo {
	obj := MemoryDeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// GetCapacity retrieves the value as the preferred SDK type
func (s *MemoryDeviceInfo) GetCapacity() string {
    return s.Capacity
}

// SetCapacity sets the value given a source of the preferred SDK type
func (s *MemoryDeviceInfo) SetCapacity(value string) {
    s.Capacity = value
}

// MemoryDeviceInfoWrapper JSON body wrapper for MemoryDeviceInfo
type MemoryDeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MemoryDeviceInfo `json:"data"`
	} `json:"response"`
}

// MemoryDeviceStatus
// Memory Device Status Information
type MemoryDeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`

	// CapacityMB
	// Capacity of the device expressed in MB
	CapacityMB int64 `json:"capacity(MB)"`

	// Unique
	// Internal value
	Unique string `json:"unique"`
}

// NewMemoryDeviceStatus initializer for MemoryDeviceStatus struct
func NewMemoryDeviceStatus() MemoryDeviceStatus {
	obj := MemoryDeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetCapacityMB retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetCapacityMB() int64 {
    return s.CapacityMB
}

// SetCapacityMB sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetCapacityMB(value int64) {
    s.CapacityMB = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *MemoryDeviceStatus) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *MemoryDeviceStatus) SetUnique(value string) {
    s.Unique = value
}

// MemoryDeviceStatusWrapper JSON body wrapper for MemoryDeviceStatus
type MemoryDeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []MemoryDeviceStatus `json:"data"`
	} `json:"response"`
}

// NameValuePair
// A simple tuple tying a value key or name to a value
type NameValuePair struct {
	// Name
	// The key or name associated with a value
	Name string `json:"name"`

	// ValueString
	// The value associated with the given key or name
	ValueString string `json:"valueString"`
}

// NewNameValuePair initializer for NameValuePair struct
func NewNameValuePair() NameValuePair {
	obj := NameValuePair{}
	return obj
}

// GetName retrieves the value as the preferred SDK type
func (s *NameValuePair) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *NameValuePair) SetName(value string) {
    s.Name = value
}

// GetValueString retrieves the value as the preferred SDK type
func (s *NameValuePair) GetValueString() string {
    return s.ValueString
}

// SetValueString sets the value given a source of the preferred SDK type
func (s *NameValuePair) SetValueString(value string) {
    s.ValueString = value
}

// NameValuePairWrapper JSON body wrapper for NameValuePair
type NameValuePairWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []NameValuePair `json:"data"`
	} `json:"response"`
}

// NetworkDeviceInfo
// Contains non-status information regarding a network device
type NetworkDeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`

	// LinkSpeed
	// Speed/bandwidth of the network link
	LinkSpeed string `json:"link_speed"`

	// DRAMSize
	// Dynamic RAM size
	DRAMSize string `json:"dram_sz"`

	// DRAMType
	// Dynamic RAM type
	DRAMType string `json:"dram_type"`
}

// NewNetworkDeviceInfo initializer for NetworkDeviceInfo struct
func NewNetworkDeviceInfo() NetworkDeviceInfo {
	obj := NetworkDeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// GetLinkSpeed retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetLinkSpeed() string {
    return s.LinkSpeed
}

// SetLinkSpeed sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetLinkSpeed(value string) {
    s.LinkSpeed = value
}

// GetDRAMSize retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetDRAMSize() string {
    return s.DRAMSize
}

// SetDRAMSize sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetDRAMSize(value string) {
    s.DRAMSize = value
}

// GetDRAMType retrieves the value as the preferred SDK type
func (s *NetworkDeviceInfo) GetDRAMType() string {
    return s.DRAMType
}

// SetDRAMType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceInfo) SetDRAMType(value string) {
    s.DRAMType = value
}

// NetworkDeviceInfoWrapper JSON body wrapper for NetworkDeviceInfo
type NetworkDeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []NetworkDeviceInfo `json:"data"`
	} `json:"response"`
}

// NetworkDeviceStatus
// Network Device Status Information
type NetworkDeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`

	// Unique
	// Internal value
	Unique string `json:"unique"`
}

// NewNetworkDeviceStatus initializer for NetworkDeviceStatus struct
func NewNetworkDeviceStatus() NetworkDeviceStatus {
	obj := NetworkDeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *NetworkDeviceStatus) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *NetworkDeviceStatus) SetUnique(value string) {
    s.Unique = value
}

// NetworkDeviceStatusWrapper JSON body wrapper for NetworkDeviceStatus
type NetworkDeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []NetworkDeviceStatus `json:"data"`
	} `json:"response"`
}

// NetworkManagedCPU
// Describes the access information required to manage a CPU device (e.g., via IPMI)
type NetworkManagedCPU struct {
	// Credentials
	// Credentials necessary for managing the device
	Credentials Credentials `json:"credentials"`

	// IPAddress
	// IP Address or DNS host name of the manager for the managed device
	IPAddress string `json:"ip_address"`

	// PortNumber
	// Port number for managing the device
	PortNumber int32 `json:"port"`

	// ManagerType
	// Entity management type
	ManagerType ManageableType `json:"type"`

	// CPUName
	// CPU name
	CPUName string `json:"cpu_name"`
}

// NewNetworkManagedCPU initializer for NetworkManagedCPU struct
func NewNetworkManagedCPU() NetworkManagedCPU {
	obj := NetworkManagedCPU{}
	obj.Credentials = Credentials{}
	return obj
}

// GetCredentials retrieves the value as the preferred SDK type
func (s *NetworkManagedCPU) GetCredentials() Credentials {
    return s.Credentials
}

// SetCredentials sets the value given a source of the preferred SDK type
func (s *NetworkManagedCPU) SetCredentials(value Credentials) {
    s.Credentials = value
}

// GetIPAddress retrieves the value as the preferred SDK type
func (s *NetworkManagedCPU) GetIPAddress() string {
    return s.IPAddress
}

// SetIPAddress sets the value given a source of the preferred SDK type
func (s *NetworkManagedCPU) SetIPAddress(value string) {
    s.IPAddress = value
}

// GetPortNumber retrieves the value as the preferred SDK type
func (s *NetworkManagedCPU) GetPortNumber() int32 {
    return s.PortNumber
}

// SetPortNumber sets the value given a source of the preferred SDK type
func (s *NetworkManagedCPU) SetPortNumber(value int32) {
    s.PortNumber = value
}

// GetManagerType retrieves the value as the preferred SDK type
func (s *NetworkManagedCPU) GetManagerType() ManageableType {
    return s.ManagerType
}

// SetManagerType sets the value given a source of the preferred SDK type
func (s *NetworkManagedCPU) SetManagerType(value ManageableType) {
    s.ManagerType = value
}

// GetCPUName retrieves the value as the preferred SDK type
func (s *NetworkManagedCPU) GetCPUName() string {
    return s.CPUName
}

// SetCPUName sets the value given a source of the preferred SDK type
func (s *NetworkManagedCPU) SetCPUName(value string) {
    s.CPUName = value
}

// NetworkManagedCPUWrapper JSON body wrapper for NetworkManagedCPU
type NetworkManagedCPUWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []NetworkManagedCPU `json:"data"`
	} `json:"response"`
}

// NetworkManagedEnclosure
// Describes the access information required to manage an enclosure
type NetworkManagedEnclosure struct {
	// Credentials
	// Credentials necessary for managing the device
	Credentials Credentials `json:"credentials"`

	// IPAddress
	// IP Address or DNS host name of the manager for the managed device
	IPAddress string `json:"ip_address"`

	// PortNumber
	// Port number for managing the device
	PortNumber int32 `json:"port"`

	// ManagerType
	// Entity management type
	ManagerType ManageableType `json:"type"`

	// EnclosureName
	// Enclosure name
	EnclosureName string `json:"enclosure_name"`
}

// NewNetworkManagedEnclosure initializer for NetworkManagedEnclosure struct
func NewNetworkManagedEnclosure() NetworkManagedEnclosure {
	obj := NetworkManagedEnclosure{}
	obj.Credentials = Credentials{}
	return obj
}

// GetCredentials retrieves the value as the preferred SDK type
func (s *NetworkManagedEnclosure) GetCredentials() Credentials {
    return s.Credentials
}

// SetCredentials sets the value given a source of the preferred SDK type
func (s *NetworkManagedEnclosure) SetCredentials(value Credentials) {
    s.Credentials = value
}

// GetIPAddress retrieves the value as the preferred SDK type
func (s *NetworkManagedEnclosure) GetIPAddress() string {
    return s.IPAddress
}

// SetIPAddress sets the value given a source of the preferred SDK type
func (s *NetworkManagedEnclosure) SetIPAddress(value string) {
    s.IPAddress = value
}

// GetPortNumber retrieves the value as the preferred SDK type
func (s *NetworkManagedEnclosure) GetPortNumber() int32 {
    return s.PortNumber
}

// SetPortNumber sets the value given a source of the preferred SDK type
func (s *NetworkManagedEnclosure) SetPortNumber(value int32) {
    s.PortNumber = value
}

// GetManagerType retrieves the value as the preferred SDK type
func (s *NetworkManagedEnclosure) GetManagerType() ManageableType {
    return s.ManagerType
}

// SetManagerType sets the value given a source of the preferred SDK type
func (s *NetworkManagedEnclosure) SetManagerType(value ManageableType) {
    s.ManagerType = value
}

// GetEnclosureName retrieves the value as the preferred SDK type
func (s *NetworkManagedEnclosure) GetEnclosureName() string {
    return s.EnclosureName
}

// SetEnclosureName sets the value given a source of the preferred SDK type
func (s *NetworkManagedEnclosure) SetEnclosureName(value string) {
    s.EnclosureName = value
}

// NetworkManagedEnclosureWrapper JSON body wrapper for NetworkManagedEnclosure
type NetworkManagedEnclosureWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []NetworkManagedEnclosure `json:"data"`
	} `json:"response"`
}

// NodeStatus
// Status information regarding one particular node.
// A node should be thought of as a unique host or CPU.
// The use of the word 'node' does not imply any association with a clustered system.
type NodeStatus struct {
	// ConfigVersion
	// Configuration version
	ConfigVersion int32 `json:"cfg_vers"`

	// ConfigIdentifier
	// Configuration identifier
	ConfigIdentifier int32 `json:"cid"`

	// Comps
	// TODO
	Comps int32 `json:"comps"`

	// CurrentTime
	// Current time setting of the node
	CurrentTime string `json:"currtime"`

	// FabricId
	// Identifier of the containing fabric
	FabricId int32 `json:"fabr_id"`

	// Flags
	// Flag settings expressed as a hex value prefixed by '0x'
	Flags string `json:"flags"`

	// LinkCount
	// Number of links for this node
	LinkCount int32 `json:"links"`

	// Location
	// Liqid coordinates of this node
	Location Coordinates `json:"location"`

	// OperatingSystem
	// Operating system which is running on the node
	OperatingSystem OperatingSystemType `json:"os_type"`

	// RunState
	// Current running state of the node
	RunState RunType `json:"run"`

	// SoftwareVersion
	// Software version for the node
	SoftwareVersion int32 `json:"sw_vers"`

	// TargetCount
	// Number of targets
	TargetCount int32 `json:"targs"`

	// UpTime
	// Amount of time the system has been up
	UpTime string `json:"uptime"`
}

// NewNodeStatus initializer for NodeStatus struct
func NewNodeStatus() NodeStatus {
	obj := NodeStatus{}
	obj.Location = Coordinates{}
	return obj
}

// GetConfigVersion retrieves the value as the preferred SDK type
func (s *NodeStatus) GetConfigVersion() int32 {
    return s.ConfigVersion
}

// SetConfigVersion sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetConfigVersion(value int32) {
    s.ConfigVersion = value
}

// GetConfigIdentifier retrieves the value as the preferred SDK type
func (s *NodeStatus) GetConfigIdentifier() int32 {
    return s.ConfigIdentifier
}

// SetConfigIdentifier sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetConfigIdentifier(value int32) {
    s.ConfigIdentifier = value
}

// GetComps retrieves the value as the preferred SDK type
func (s *NodeStatus) GetComps() int32 {
    return s.Comps
}

// SetComps sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetComps(value int32) {
    s.Comps = value
}

// GetCurrentTime retrieves the value as the preferred SDK type
func (s *NodeStatus) GetCurrentTime() string {
    return s.CurrentTime
}

// SetCurrentTime sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetCurrentTime(value string) {
    s.CurrentTime = value
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *NodeStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *NodeStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetLinkCount retrieves the value as the preferred SDK type
func (s *NodeStatus) GetLinkCount() int32 {
    return s.LinkCount
}

// SetLinkCount sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetLinkCount(value int32) {
    s.LinkCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *NodeStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetOperatingSystem retrieves the value as the preferred SDK type
func (s *NodeStatus) GetOperatingSystem() OperatingSystemType {
    return s.OperatingSystem
}

// SetOperatingSystem sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetOperatingSystem(value OperatingSystemType) {
    s.OperatingSystem = value
}

// GetRunState retrieves the value as the preferred SDK type
func (s *NodeStatus) GetRunState() RunType {
    return s.RunState
}

// SetRunState sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetRunState(value RunType) {
    s.RunState = value
}

// GetSoftwareVersion retrieves the value as the preferred SDK type
func (s *NodeStatus) GetSoftwareVersion() int32 {
    return s.SoftwareVersion
}

// SetSoftwareVersion sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetSoftwareVersion(value int32) {
    s.SoftwareVersion = value
}

// GetTargetCount retrieves the value as the preferred SDK type
func (s *NodeStatus) GetTargetCount() int32 {
    return s.TargetCount
}

// SetTargetCount sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetTargetCount(value int32) {
    s.TargetCount = value
}

// GetUpTime retrieves the value as the preferred SDK type
func (s *NodeStatus) GetUpTime() string {
    return s.UpTime
}

// SetUpTime sets the value given a source of the preferred SDK type
func (s *NodeStatus) SetUpTime(value string) {
    s.UpTime = value
}

// NodeStatusWrapper JSON body wrapper for NodeStatus
type NodeStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []NodeStatus `json:"data"`
	} `json:"response"`
}

// Port
// Describes a switch port
type Port struct {
	// PortType
	// Hardware type
	PortType string `json:"type"`

	// Index
	// Internal index of this port
	Index string `json:"index"`

	// Flags
	// Hardware flags displayed as a hex string prefixed by 'ox'
	Flags string `json:"flags"`

	// State
	// Hardware state
	State string `json:"state"`

	// FabricGlobalId
	// Fabric-unique global identifier to which this entity is connected.
	FabricGlobalId string `json:"fabr_gid"`

	// PCILaneCount
	// Number of PCI lanes for this component
	PCILaneCount int32 `json:"lanes"`

	// ReceiverErrorCount
	// Running count of errors received for this component
	ReceiverErrorCount int32 `json:"rcv_errs"`

	// BadTLPCount
	// Running count of bad TLPs for this component
	BadTLPCount int32 `json:"bad_tlp"`

	// BadDLLPCount
	// Running count of bad DLLPs for this component
	BadDLLPCount int32 `json:"bad_dllp"`

	// ErrorCount
	// Running count of all errors for this component
	ErrorCount int32 `json:"errs"`

	// IngressBytes
	// Running count of incoming bytes
	IngressBytes int32 `json:"ibytes"`

	// EgressBytes
	// Running count of outgoing bytes
	EgressBytes int32 `json:"ebytes"`

	// Switches
	// Array of Switch objects describing the switches which are connected to this port
	Switches []Switch `json:"switches"`

	// CPU
	// A PortCPU struct describing the CPU which is connected to this port
	CPU PortCPU `json:"cpu"`

	// DeviceState
	// Describes this entity's current state
	DeviceState string `json:"device_state"`
}

// NewPort initializer for Port struct
func NewPort() Port {
	obj := Port{}
	obj.Switches = []Switch{}
	obj.CPU = PortCPU{}
	return obj
}

// GetPortType retrieves the value as the preferred SDK type
func (s *Port) GetPortType() string {
    return s.PortType
}

// SetPortType sets the value given a source of the preferred SDK type
func (s *Port) SetPortType(value string) {
    s.PortType = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *Port) GetIndex() string {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *Port) SetIndex(value string) {
    s.Index = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *Port) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *Port) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetState retrieves the value as the preferred SDK type
func (s *Port) GetState() string {
    return s.State
}

// SetState sets the value given a source of the preferred SDK type
func (s *Port) SetState(value string) {
    s.State = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *Port) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *Port) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *Port) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *Port) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetReceiverErrorCount retrieves the value as the preferred SDK type
func (s *Port) GetReceiverErrorCount() int32 {
    return s.ReceiverErrorCount
}

// SetReceiverErrorCount sets the value given a source of the preferred SDK type
func (s *Port) SetReceiverErrorCount(value int32) {
    s.ReceiverErrorCount = value
}

// GetBadTLPCount retrieves the value as the preferred SDK type
func (s *Port) GetBadTLPCount() int32 {
    return s.BadTLPCount
}

// SetBadTLPCount sets the value given a source of the preferred SDK type
func (s *Port) SetBadTLPCount(value int32) {
    s.BadTLPCount = value
}

// GetBadDLLPCount retrieves the value as the preferred SDK type
func (s *Port) GetBadDLLPCount() int32 {
    return s.BadDLLPCount
}

// SetBadDLLPCount sets the value given a source of the preferred SDK type
func (s *Port) SetBadDLLPCount(value int32) {
    s.BadDLLPCount = value
}

// GetErrorCount retrieves the value as the preferred SDK type
func (s *Port) GetErrorCount() int32 {
    return s.ErrorCount
}

// SetErrorCount sets the value given a source of the preferred SDK type
func (s *Port) SetErrorCount(value int32) {
    s.ErrorCount = value
}

// GetIngressBytes retrieves the value as the preferred SDK type
func (s *Port) GetIngressBytes() int32 {
    return s.IngressBytes
}

// SetIngressBytes sets the value given a source of the preferred SDK type
func (s *Port) SetIngressBytes(value int32) {
    s.IngressBytes = value
}

// GetEgressBytes retrieves the value as the preferred SDK type
func (s *Port) GetEgressBytes() int32 {
    return s.EgressBytes
}

// SetEgressBytes sets the value given a source of the preferred SDK type
func (s *Port) SetEgressBytes(value int32) {
    s.EgressBytes = value
}

// GetSwitches retrieves the value as the preferred SDK type
func (s *Port) GetSwitches() []Switch {
    return s.Switches
}

// SetSwitches sets the value given a source of the preferred SDK type
func (s *Port) SetSwitches(value []Switch) {
    s.Switches = value
}

// GetCPU retrieves the value as the preferred SDK type
func (s *Port) GetCPU() PortCPU {
    return s.CPU
}

// SetCPU sets the value given a source of the preferred SDK type
func (s *Port) SetCPU(value PortCPU) {
    s.CPU = value
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *Port) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *Port) SetDeviceState(value string) {
    s.DeviceState = value
}

// PortWrapper JSON body wrapper for Port
type PortWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Port `json:"data"`
	} `json:"response"`
}

// PortCPU
// Describes a CPU switch port
type PortCPU struct {
	// Name
	// Name of this entity
	Name string `json:"name"`

	// GlobalId
	// Global identifier for this entity
	GlobalId string `json:"gid"`

	// VendorId
	// Unique identifier of the hardware vendor expressed as a hex value prefixed with '0x'
	VendorId string `json:"vendorid"`

	// DeviceId
	// Vendor-unique identifier of the device expressed as a hex value prefixed with '0x'
	DeviceId string `json:"deviceid"`

	// BusWidth
	// Bus-width for the switch
	BusWidth string `json:"buswidth"`

	// Direction
	// TODO
	Direction string `json:"direction"`

	// PortType
	// TODO
	PortType string `json:"type"`
}

// NewPortCPU initializer for PortCPU struct
func NewPortCPU() PortCPU {
	obj := PortCPU{}
	return obj
}

// GetName retrieves the value as the preferred SDK type
func (s *PortCPU) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *PortCPU) SetName(value string) {
    s.Name = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *PortCPU) GetGlobalId() string {
    return s.GlobalId
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *PortCPU) SetGlobalId(value string) {
    s.GlobalId = value
}

// GetVendorId retrieves the value as the preferred SDK type
func (s *PortCPU) GetVendorId() string {
    return s.VendorId
}

// SetVendorId sets the value given a source of the preferred SDK type
func (s *PortCPU) SetVendorId(value string) {
    s.VendorId = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *PortCPU) GetDeviceId() string {
    return s.DeviceId
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *PortCPU) SetDeviceId(value string) {
    s.DeviceId = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *PortCPU) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *PortCPU) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDirection retrieves the value as the preferred SDK type
func (s *PortCPU) GetDirection() string {
    return s.Direction
}

// SetDirection sets the value given a source of the preferred SDK type
func (s *PortCPU) SetDirection(value string) {
    s.Direction = value
}

// GetPortType retrieves the value as the preferred SDK type
func (s *PortCPU) GetPortType() string {
    return s.PortType
}

// SetPortType sets the value given a source of the preferred SDK type
func (s *PortCPU) SetPortType(value string) {
    s.PortType = value
}

// PortCPUWrapper JSON body wrapper for PortCPU
type PortCPUWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []PortCPU `json:"data"`
	} `json:"response"`
}

// PreDevice
// Describes a device before it is added to a group
type PreDevice struct {
	// PreDeviceType
	// Pre Device type (not exactly the same as the Device Status device type)
	PreDeviceType PreDeviceType `json:"dev_type"`

	// FabricGlobalId
	// Fabric global id
	FabricGlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier to which the device belongs
	FabricId int32 `json:"fabr_id"`

	// Flags
	// Flags for the device - a hex string prefixed by '0x'
	Flags string `json:"flags"`

	// GroupName
	// TODO
	GroupName string `json:"gname"`

	// GroupId
	// TODO
	GroupId int32 `json:"grp_id"`

	// Index
	// Internal index for this pre-device
	Index string `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// MachineId
	// Machine identifier
	MachineId string `json:"mach_id"`

	// MachineName
	// Machine name
	MachineName string `json:"mname"`

	// DeviceName
	// Device name
	DeviceName string `json:"name"`

	// OwnerId
	// Owner identifier
	OwnerId string `json:"owner_id"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Device port global identifier
	PortGlobalId string `json:"port_gid"`

	// SwitchGlobalId
	// Device switch global identifier
	SwitchGlobalId string `json:"swit_gid"`
}

// NewPreDevice initializer for PreDevice struct
func NewPreDevice() PreDevice {
	obj := PreDevice{}
	obj.PodId = -1
	return obj
}

// GetPreDeviceType retrieves the value as the preferred SDK type
func (s *PreDevice) GetPreDeviceType() PreDeviceType {
    return s.PreDeviceType
}

// SetPreDeviceType sets the value given a source of the preferred SDK type
func (s *PreDevice) SetPreDeviceType(value PreDeviceType) {
    s.PreDeviceType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *PreDevice) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *PreDevice) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *PreDevice) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *PreDevice) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetGroupName retrieves the value as the preferred SDK type
func (s *PreDevice) GetGroupName() string {
    return s.GroupName
}

// SetGroupName sets the value given a source of the preferred SDK type
func (s *PreDevice) SetGroupName(value string) {
    s.GroupName = value
}

// GetGroupId retrieves the value as the preferred SDK type
func (s *PreDevice) GetGroupId() int32 {
    return s.GroupId
}

// SetGroupId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetGroupId(value int32) {
    s.GroupId = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *PreDevice) GetIndex() string {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *PreDevice) SetIndex(value string) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *PreDevice) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *PreDevice) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetMachineId retrieves the value as the preferred SDK type
func (s *PreDevice) GetMachineId() int32 {
    result, _ := strconv.ParseInt(s.MachineId[2:], 16, 32)
    return int32(result)
}

// SetMachineId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetMachineId(value int32) {
    s.MachineId = fmt.Sprintf("%d", value)
}

// GetMachineName retrieves the value as the preferred SDK type
func (s *PreDevice) GetMachineName() string {
    return s.MachineName
}

// SetMachineName sets the value given a source of the preferred SDK type
func (s *PreDevice) SetMachineName(value string) {
    s.MachineName = value
}

// GetDeviceName retrieves the value as the preferred SDK type
func (s *PreDevice) GetDeviceName() string {
    return s.DeviceName
}

// SetDeviceName sets the value given a source of the preferred SDK type
func (s *PreDevice) SetDeviceName(value string) {
    s.DeviceName = value
}

// GetOwnerId retrieves the value as the preferred SDK type
func (s *PreDevice) GetOwnerId() int32 {
    result, _ := strconv.ParseInt(s.OwnerId[2:], 16, 32)
    return int32(result)
}

// SetOwnerId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetOwnerId(value int32) {
    s.OwnerId = fmt.Sprintf("%d", value)
}

// GetPodId retrieves the value as the preferred SDK type
func (s *PreDevice) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *PreDevice) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *PreDevice) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *PreDevice) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// PreDeviceWrapper JSON body wrapper for PreDevice
type PreDeviceWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []PreDevice `json:"data"`
	} `json:"response"`
}

// SSHStatus
// Describes the current state of SSH
type SSHStatus struct {
	// Active
	// Indicates whether SSH is active
	Active bool `json:"is-active"`

	// Enabled
	// Indicates whether SSH is enabled
	Enabled bool `json:"is-enabled"`
}

// NewSSHStatus initializer for SSHStatus struct
func NewSSHStatus() SSHStatus {
	obj := SSHStatus{}
	return obj
}

// GetActive retrieves the value as the preferred SDK type
func (s *SSHStatus) GetActive() bool {
    return s.Active
}

// SetActive sets the value given a source of the preferred SDK type
func (s *SSHStatus) SetActive(value bool) {
    s.Active = value
}

// GetEnabled retrieves the value as the preferred SDK type
func (s *SSHStatus) GetEnabled() bool {
    return s.Enabled
}

// SetEnabled sets the value given a source of the preferred SDK type
func (s *SSHStatus) SetEnabled(value bool) {
    s.Enabled = value
}

// SSHStatusWrapper JSON body wrapper for SSHStatus
type SSHStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []SSHStatus `json:"data"`
	} `json:"response"`
}

// StorageDeviceInfo
// Contains non-status information regarding a storage device
type StorageDeviceInfo struct {
	// BusGeneration
	// Bus Generation
	BusGeneration string `json:"busgen"`

	// BusWidth
	// Bus Width
	BusWidth string `json:"buswidth"`

	// DeviceClass
	// Device class
	DeviceClass string `json:"class"`

	// ConnectionType
	// Connection Type
	ConnectionType string `json:"conn_type"`

	// DeviceIdentifier
	// Device Identifier
	DeviceIdentifier string `json:"dev_id"`

	// DeviceState
	// Device State
	DeviceState string `json:"device_state"`

	// DeviceInfoType
	// Device Type
	DeviceInfoType DeviceType `json:"device_type"`

	// FabricGlobalId
	// Fabric global identifier
	FabricGlobalId string `json:"fabr_gid"`

	// FabricType
	// Fabric Type
	FabricType FabricType `json:"fabric_type"`

	// Family
	// Family
	Family string `json:"family"`

	// Flags
	// Flags
	Flags string `json:"flags"`

	// FirmwareRevision
	// Device firmware revision
	FirmwareRevision string `json:"fw_rev"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// Location
	// Liqid Coordinates for this device
	Location Coordinates `json:"location"`

	// Model
	// Model of this device
	Model string `json:"model"`

	// Name
	// Device name
	Name string `json:"name"`

	// Owner
	// Liqid Coordinates of the device above this one in the hierarchy
	Owner Coordinates `json:"owner"`

	// PartNumber
	// Device name
	PartNumber string `json:"part_num"`

	// PCIDeviceId
	// Vendor-unique device identifier expressed in hex as a '0x'-prefixed string
	PCIDeviceId string `json:"pci_device"`

	// PCIVendorId
	// PCI Vendor identifier expressed in hex as a '0x'-prefixed string
	PCIVendorId string `json:"pci_vendor"`

	// PodId
	// Obsolete value - should always be -1
	PodId int32 `json:"pod_id"`

	// SerialNumber
	// Device serial number
	SerialNumber string `json:"serial_num"`

	// SledId
	// Obsolete
	SledId string `json:"sled_id"`

	// UserDescription
	// User-supplied description
	UserDescription string `json:"udesc"`

	// Unique
	// Device-specific information
	Unique string `json:"unique"`

	// Vendor
	// Vendor name
	Vendor string `json:"vendor"`

	// Capacity
	// Capacity of the storage device
	Capacity string `json:"capacity"`
}

// NewStorageDeviceInfo initializer for StorageDeviceInfo struct
func NewStorageDeviceInfo() StorageDeviceInfo {
	obj := StorageDeviceInfo{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetBusGeneration retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetBusGeneration() string {
    return s.BusGeneration
}

// SetBusGeneration sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetBusGeneration(value string) {
    s.BusGeneration = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDeviceClass retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetDeviceClass() string {
    return s.DeviceClass
}

// SetDeviceClass sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetDeviceClass(value string) {
    s.DeviceClass = value
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceIdentifier retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetDeviceIdentifier() int32 {
    result, _ := strconv.ParseInt(s.DeviceIdentifier[2:], 16, 32)
    return int32(result)
}

// SetDeviceIdentifier sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetDeviceIdentifier(value int32) {
    s.DeviceIdentifier = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceInfoType retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetDeviceInfoType() DeviceType {
    return s.DeviceInfoType
}

// SetDeviceInfoType sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetDeviceInfoType(value DeviceType) {
    s.DeviceInfoType = value
}

// GetFabricGlobalId retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetFabricGlobalId() int32 {
    result, _ := strconv.ParseInt(s.FabricGlobalId[2:], 16, 32)
    return int32(result)
}

// SetFabricGlobalId sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetFabricGlobalId(value int32) {
    s.FabricGlobalId = fmt.Sprintf("%d", value)
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetFabricType() FabricType {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetFabricType(value FabricType) {
    s.FabricType = value
}

// GetFamily retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetFamily() string {
    return s.Family
}

// SetFamily sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetFamily(value string) {
    s.Family = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFirmwareRevision retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetFirmwareRevision() string {
    return s.FirmwareRevision
}

// SetFirmwareRevision sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetFirmwareRevision(value string) {
    s.FirmwareRevision = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetIndex(value int32) {
    s.Index = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetLocation(value Coordinates) {
    s.Location = value
}

// GetModel retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetModel() string {
    return s.Model
}

// SetModel sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetModel(value string) {
    s.Model = value
}

// GetName retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPartNumber retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetPartNumber() string {
    return s.PartNumber
}

// SetPartNumber sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetPartNumber(value string) {
    s.PartNumber = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetPodId(value int32) {
    s.PodId = value
}

// GetSerialNumber retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetSerialNumber() string {
    return s.SerialNumber
}

// SetSerialNumber sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetSerialNumber(value string) {
    s.SerialNumber = value
}

// GetSledId retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetUserDescription retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetUserDescription() string {
    return s.UserDescription
}

// SetUserDescription sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetUserDescription(value string) {
    s.UserDescription = value
}

// GetUnique retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetUnique() string {
    return s.Unique
}

// SetUnique sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetUnique(value string) {
    s.Unique = value
}

// GetVendor retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetVendor() string {
    return s.Vendor
}

// SetVendor sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetVendor(value string) {
    s.Vendor = value
}

// GetCapacity retrieves the value as the preferred SDK type
func (s *StorageDeviceInfo) GetCapacity() string {
    return s.Capacity
}

// SetCapacity sets the value given a source of the preferred SDK type
func (s *StorageDeviceInfo) SetCapacity(value string) {
    s.Capacity = value
}

// StorageDeviceInfoWrapper JSON body wrapper for StorageDeviceInfo
type StorageDeviceInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []StorageDeviceInfo `json:"data"`
	} `json:"response"`
}

// StorageDeviceStatus
// Storage Device Status Information
type StorageDeviceStatus struct {
	// ConnectionType
	// Connection type for the device
	ConnectionType string `json:"conn_type"`

	// DeviceId
	// Unique identifier for the device
	DeviceId string `json:"dev_id"`

	// DeviceState
	// State of this device
	DeviceState string `json:"device_state"`

	// DeviceType
	// Device type for this device
	DeviceType DeviceType `json:"device_type"`

	// PCIDeviceId
	// PCI Vendor-Unique Device identifier
	PCIDeviceId string `json:"did"`

	// GlobalId
	// Fabric global identifier
	GlobalId string `json:"fabr_gid"`

	// FabricId
	// Fabric identifier
	FabricId int32 `json:"fabr_id"`

	// FabricType
	// Fabric type
	FabricType string `json:"fabric_type"`

	// Flags
	// Hardware flags as a 64-bit bitmask
	Flags string `json:"flags"`

	// Flags2
	// Additional hardware flags displayed as a hex string prefixed by 'ox'
	Flags2 string `json:"flags2"`

	// Index
	// Internal index of this device
	Index int32 `json:"index"`

	// PCILaneCount
	// Number of PCI lanes for this device
	PCILaneCount int32 `json:"lanes"`

	// Location
	// Liqid coordinates for this component
	Location Coordinates `json:"location"`

	// Name
	// Name of this component
	Name string `json:"name"`

	// Owner
	// Liqid coordinates for the component directly above this in the containing fabric topology
	Owner Coordinates `json:"owner"`

	// PodId
	// Pod identifier - obsolete value which is generally always -1
	PodId int32 `json:"pod_id"`

	// PortGlobalId
	// Port Global Identifier
	PortGlobalId string `json:"port_gid"`

	// SledId
	// Obsolete value
	SledId string `json:"sled_id"`

	// SwitchGlobalId
	// Switch Global Identifier
	SwitchGlobalId string `json:"swit_gid"`

	// DeviceStatusType
	// Type of DeviceStatus entity
	DeviceStatusType string `json:"type"`

	// PCIVendorId
	// Unique PCI Vendor Identifier
	PCIVendorId string `json:"vid"`

	// CapacityMB
	// Capacity of the device expressed in MB
	CapacityMB int64 `json:"capacity(MB)"`
}

// NewStorageDeviceStatus initializer for StorageDeviceStatus struct
func NewStorageDeviceStatus() StorageDeviceStatus {
	obj := StorageDeviceStatus{}
	obj.Location = Coordinates{}
	obj.Owner = Coordinates{}
	obj.PodId = -1
	return obj
}

// GetConnectionType retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetConnectionType() string {
    return s.ConnectionType
}

// SetConnectionType sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetConnectionType(value string) {
    s.ConnectionType = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetDeviceId() int32 {
    result, _ := strconv.ParseInt(s.DeviceId[2:], 16, 32)
    return int32(result)
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetDeviceId(value int32) {
    s.DeviceId = fmt.Sprintf("%d", value)
}

// GetDeviceState retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetDeviceState() string {
    return s.DeviceState
}

// SetDeviceState sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetDeviceState(value string) {
    s.DeviceState = value
}

// GetDeviceType retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetDeviceType() DeviceType {
    return s.DeviceType
}

// SetDeviceType sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetDeviceType(value DeviceType) {
    s.DeviceType = value
}

// GetPCIDeviceId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetPCIDeviceId() string {
    return s.PCIDeviceId
}

// SetPCIDeviceId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetPCIDeviceId(value string) {
    s.PCIDeviceId = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetGlobalId() int32 {
    result, _ := strconv.ParseInt(s.GlobalId[2:], 16, 32)
    return int32(result)
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetGlobalId(value int32) {
    s.GlobalId = fmt.Sprintf("%d", value)
}

// GetFabricId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetFabricId() int32 {
    return s.FabricId
}

// SetFabricId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetFabricId(value int32) {
    s.FabricId = value
}

// GetFabricType retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetFabricType() string {
    return s.FabricType
}

// SetFabricType sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetFabricType(value string) {
    s.FabricType = value
}

// GetFlags retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetFlags() int64 {
    result, _ := strconv.ParseInt(s.Flags[2:], 16, 64)
    return int64(result)
}

// SetFlags sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetFlags(value int64) {
    s.Flags = fmt.Sprintf("%d", value)
}

// GetFlags2 retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetFlags2() string {
    return s.Flags2
}

// SetFlags2 sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetFlags2(value string) {
    s.Flags2 = value
}

// GetIndex retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetIndex() int32 {
    return s.Index
}

// SetIndex sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetIndex(value int32) {
    s.Index = value
}

// GetPCILaneCount retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetPCILaneCount() int32 {
    return s.PCILaneCount
}

// SetPCILaneCount sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetPCILaneCount(value int32) {
    s.PCILaneCount = value
}

// GetLocation retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetLocation() Coordinates {
    return s.Location
}

// SetLocation sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetLocation(value Coordinates) {
    s.Location = value
}

// GetName retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetName(value string) {
    s.Name = value
}

// GetOwner retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetOwner() Coordinates {
    return s.Owner
}

// SetOwner sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetOwner(value Coordinates) {
    s.Owner = value
}

// GetPodId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetPodId() int32 {
    return s.PodId
}

// SetPodId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetPodId(value int32) {
    s.PodId = value
}

// GetPortGlobalId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetPortGlobalId() int32 {
    result, _ := strconv.ParseInt(s.PortGlobalId[2:], 16, 24)
    return int32(result)
}

// SetPortGlobalId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetPortGlobalId(value int32) {
    s.PortGlobalId = fmt.Sprintf("%d", value)
}

// GetSledId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetSledId() int32 {
    result, _ := strconv.ParseInt(s.SledId[2:], 16, 32)
    return int32(result)
}

// SetSledId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetSledId(value int32) {
    s.SledId = fmt.Sprintf("%d", value)
}

// GetSwitchGlobalId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetSwitchGlobalId() int32 {
    result, _ := strconv.ParseInt(s.SwitchGlobalId[2:], 16, 24)
    return int32(result)
}

// SetSwitchGlobalId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetSwitchGlobalId(value int32) {
    s.SwitchGlobalId = fmt.Sprintf("%d", value)
}

// GetDeviceStatusType retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetDeviceStatusType() string {
    return s.DeviceStatusType
}

// SetDeviceStatusType sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetDeviceStatusType(value string) {
    s.DeviceStatusType = value
}

// GetPCIVendorId retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetPCIVendorId() string {
    return s.PCIVendorId
}

// SetPCIVendorId sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetPCIVendorId(value string) {
    s.PCIVendorId = value
}

// GetCapacityMB retrieves the value as the preferred SDK type
func (s *StorageDeviceStatus) GetCapacityMB() int64 {
    return s.CapacityMB
}

// SetCapacityMB sets the value given a source of the preferred SDK type
func (s *StorageDeviceStatus) SetCapacityMB(value int64) {
    s.CapacityMB = value
}

// StorageDeviceStatusWrapper JSON body wrapper for StorageDeviceStatus
type StorageDeviceStatusWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []StorageDeviceStatus `json:"data"`
	} `json:"response"`
}

// Switch
// Details related to the PCI switch
type Switch struct {
	// Name
	// Switch name
	Name string `json:"name"`

	// GlobalId
	// Global identifier expressed as a hex value prefixed with '0x'
	GlobalId string `json:"gid"`

	// VendorId
	// Unique identifier of the hardware vendor expressed as a hex value prefixed with '0x'
	VendorId string `json:"vendorid"`

	// DeviceId
	// Vendor-unique identifier of the device expressed as a hex value prefixed with '0x'
	DeviceId string `json:"deviceid"`

	// BusWidth
	// Bus-width for the switch
	BusWidth string `json:"buswidth"`

	// Direction
	// TODO
	Direction string `json:"direction"`

	// SwitchType
	// TODO
	SwitchType string `json:"type"`

	// SwitchDevice
	// Additional details regarding the switch
	SwitchDevice SwitchDevice `json:"device"`
}

// NewSwitch initializer for Switch struct
func NewSwitch() Switch {
	obj := Switch{}
	obj.SwitchDevice = SwitchDevice{}
	return obj
}

// GetName retrieves the value as the preferred SDK type
func (s *Switch) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *Switch) SetName(value string) {
    s.Name = value
}

// GetGlobalId retrieves the value as the preferred SDK type
func (s *Switch) GetGlobalId() string {
    return s.GlobalId
}

// SetGlobalId sets the value given a source of the preferred SDK type
func (s *Switch) SetGlobalId(value string) {
    s.GlobalId = value
}

// GetVendorId retrieves the value as the preferred SDK type
func (s *Switch) GetVendorId() string {
    return s.VendorId
}

// SetVendorId sets the value given a source of the preferred SDK type
func (s *Switch) SetVendorId(value string) {
    s.VendorId = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *Switch) GetDeviceId() string {
    return s.DeviceId
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *Switch) SetDeviceId(value string) {
    s.DeviceId = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *Switch) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *Switch) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDirection retrieves the value as the preferred SDK type
func (s *Switch) GetDirection() string {
    return s.Direction
}

// SetDirection sets the value given a source of the preferred SDK type
func (s *Switch) SetDirection(value string) {
    s.Direction = value
}

// GetSwitchType retrieves the value as the preferred SDK type
func (s *Switch) GetSwitchType() string {
    return s.SwitchType
}

// SetSwitchType sets the value given a source of the preferred SDK type
func (s *Switch) SetSwitchType(value string) {
    s.SwitchType = value
}

// GetSwitchDevice retrieves the value as the preferred SDK type
func (s *Switch) GetSwitchDevice() SwitchDevice {
    return s.SwitchDevice
}

// SetSwitchDevice sets the value given a source of the preferred SDK type
func (s *Switch) SetSwitchDevice(value SwitchDevice) {
    s.SwitchDevice = value
}

// SwitchWrapper JSON body wrapper for Switch
type SwitchWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Switch `json:"data"`
	} `json:"response"`
}

// SwitchDevice
// Additional details specific to the switch
type SwitchDevice struct {
	// Name
	// Switch name
	Name string `json:"name"`

	// GroupId
	// Unique identifier of the group expressed as a hex value prefixed with '0x'
	GroupId string `json:"gid"`

	// VendorId
	// Unique identifier of the hardware vendor expressed as a hex value prefixed with '0x'
	VendorId string `json:"vendorid"`

	// DeviceId
	// Vendor-unique identifier of the device expressed as a hex value prefixed with '0x'
	DeviceId string `json:"deviceid"`

	// BusWidth
	// Bus-width for the switch
	BusWidth string `json:"buswidth"`

	// Direction
	// TODO
	Direction string `json:"direction"`

	// SwitchDeviceType
	// TODO
	SwitchDeviceType string `json:"type"`
}

// NewSwitchDevice initializer for SwitchDevice struct
func NewSwitchDevice() SwitchDevice {
	obj := SwitchDevice{}
	return obj
}

// GetName retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetName() string {
    return s.Name
}

// SetName sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetName(value string) {
    s.Name = value
}

// GetGroupId retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetGroupId() string {
    return s.GroupId
}

// SetGroupId sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetGroupId(value string) {
    s.GroupId = value
}

// GetVendorId retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetVendorId() string {
    return s.VendorId
}

// SetVendorId sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetVendorId(value string) {
    s.VendorId = value
}

// GetDeviceId retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetDeviceId() string {
    return s.DeviceId
}

// SetDeviceId sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetDeviceId(value string) {
    s.DeviceId = value
}

// GetBusWidth retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetBusWidth() string {
    return s.BusWidth
}

// SetBusWidth sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetBusWidth(value string) {
    s.BusWidth = value
}

// GetDirection retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetDirection() string {
    return s.Direction
}

// SetDirection sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetDirection(value string) {
    s.Direction = value
}

// GetSwitchDeviceType retrieves the value as the preferred SDK type
func (s *SwitchDevice) GetSwitchDeviceType() string {
    return s.SwitchDeviceType
}

// SetSwitchDeviceType sets the value given a source of the preferred SDK type
func (s *SwitchDevice) SetSwitchDeviceType(value string) {
    s.SwitchDeviceType = value
}

// SwitchDeviceWrapper JSON body wrapper for SwitchDevice
type SwitchDeviceWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []SwitchDevice `json:"data"`
	} `json:"response"`
}

// Timestamp
// Contains a timestamp value
type Timestamp struct {
	// OldTimestamp
	// Old value with wrong size int and incorrectly-named JSON tag of 'epoch'
	OldTimestamp int32 `json:"epoch"`

	// Timestamp
	// A time and date based on the Unix epoch
	Timestamp int64 `json:"timestamp"`
}

// NewTimestamp initializer for Timestamp struct
func NewTimestamp() Timestamp {
	obj := Timestamp{}
	return obj
}

// GetOldTimestamp retrieves the value as the preferred SDK type
func (s *Timestamp) GetOldTimestamp() int32 {
    return s.OldTimestamp
}

// SetOldTimestamp sets the value given a source of the preferred SDK type
func (s *Timestamp) SetOldTimestamp(value int32) {
    s.OldTimestamp = value
}

// GetTimestamp retrieves the value as the preferred SDK type
func (s *Timestamp) GetTimestamp() int64 {
    return s.Timestamp
}

// SetTimestamp sets the value given a source of the preferred SDK type
func (s *Timestamp) SetTimestamp(value int64) {
    s.Timestamp = value
}

// TimestampWrapper JSON body wrapper for Timestamp
type TimestampWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []Timestamp `json:"data"`
	} `json:"response"`
}

// VersionInfo
// Describes the versions of the various software components which comprise the Director
type VersionInfo struct {
	// Branch
	// Development branch from which this component was built
	Branch string `json:"branch"`

	// ChangeSet
	// 
	ChangeSet string `json:"changeset"`

	// ShortChangeSet
	// 
	ShortChangeSet string `json:"changeset_short"`

	// Component
	// Name of the software component
	Component string `json:"component"`

	// Date
	// Date the component was built
	Date string `json:"date"`

	// ShortDate
	// Date the component was built
	ShortDate string `json:"date_short"`

	// Version
	// Component version string
	Version string `json:"version"`
}

// NewVersionInfo initializer for VersionInfo struct
func NewVersionInfo() VersionInfo {
	obj := VersionInfo{}
	return obj
}

// GetBranch retrieves the value as the preferred SDK type
func (s *VersionInfo) GetBranch() string {
    return s.Branch
}

// SetBranch sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetBranch(value string) {
    s.Branch = value
}

// GetChangeSet retrieves the value as the preferred SDK type
func (s *VersionInfo) GetChangeSet() string {
    return s.ChangeSet
}

// SetChangeSet sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetChangeSet(value string) {
    s.ChangeSet = value
}

// GetShortChangeSet retrieves the value as the preferred SDK type
func (s *VersionInfo) GetShortChangeSet() string {
    return s.ShortChangeSet
}

// SetShortChangeSet sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetShortChangeSet(value string) {
    s.ShortChangeSet = value
}

// GetComponent retrieves the value as the preferred SDK type
func (s *VersionInfo) GetComponent() string {
    return s.Component
}

// SetComponent sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetComponent(value string) {
    s.Component = value
}

// GetDate retrieves the value as the preferred SDK type
func (s *VersionInfo) GetDate() string {
    return s.Date
}

// SetDate sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetDate(value string) {
    s.Date = value
}

// GetShortDate retrieves the value as the preferred SDK type
func (s *VersionInfo) GetShortDate() string {
    return s.ShortDate
}

// SetShortDate sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetShortDate(value string) {
    s.ShortDate = value
}

// GetVersion retrieves the value as the preferred SDK type
func (s *VersionInfo) GetVersion() string {
    return s.Version
}

// SetVersion sets the value given a source of the preferred SDK type
func (s *VersionInfo) SetVersion(value string) {
    s.Version = value
}

// VersionInfoWrapper JSON body wrapper for VersionInfo
type VersionInfoWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []VersionInfo `json:"data"`
	} `json:"response"`
}

// FabricTopology
// An array of FabricItem entities
type FabricTopology []FabricItem

// FabricTopologyWrapper JSON body wrapper for FabricTopology
type FabricTopologyWrapper struct {
	Response struct {
		Code   int            `json:"code"`
		Errors []ErrorMessage `json:"errors"`
		Data   []FabricTopology `json:"data"`
	} `json:"response"`
}
