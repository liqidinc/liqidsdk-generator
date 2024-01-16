// File: machine_gen.go
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.4
// This file was automatically generated - do not modify it directly.
//

package liqidsdk

import (
	"fmt"
	"strconv"
)

// Category: Machine
// General functions related to configured machines

// CreateMachineWithId Creates a new machine for a particular group
//
// Param groupId: Identifier of the group to which the machine should be attached
// Param machineId: Unique identifier to be applied to the machine
// Param machineName: Name for the newly-created machine
// Returns: A Machine entity describing the created machine
//
// Deprecated: SDK Clients should use CreateMachine instead, which does not require that the client specify a machine id.
func (client *LiqidClient) CreateMachineWithId(groupId int32, machineId int32, machineName string) (*Machine, error) {
	var fn = "CreateMachineWithId"
	client.traceLogger.Printf(LogFmtEnter3, fn, groupId, machineId, machineName)

	path := "machine"
	cliReq := NewClientRequest().SetMethod("POST").SetPartialPath(path)
	apiParam := NewMachine()
	apiParam.GroupId = groupId
	apiParam.MachineId = machineId
	apiParam.MachineName = machineName
	apiParam.ComputeName = machineName
	var presetErr error
	apiParam.FabricId, presetErr = client.getCurrentFabricId()
	if presetErr != nil {
		return nil, presetErr
	}
	cliReq.SetJSONBody(apiParam)

	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = &respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, *result, nil)
	return result, nil
}

// DeleteMachine Deletes a configured machine, returning its attached devices to the containing group free pool
//
// Param machineId: Unique identifier for the machine to be deleted
// Returns: a Machine entity describing the deleted machine
func (client *LiqidClient) DeleteMachine(machineId int32) (*Machine, error) {
	var fn = "DeleteMachine"
	client.traceLogger.Printf(LogFmtEnter1, fn, machineId)

	path := "machine"
	path += "/" + fmt.Sprintf("%v", machineId)
	cliReq := NewClientRequest()
	cliReq.SetMethod("DELETE")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = &respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, *result, nil)
	return result, nil
}

// GetMachine Retrieves information about a configured machine given the machine identifier
//
// Param machineId: Unique machine identifier
// Returns: A Machine entity
func (client *LiqidClient) GetMachine(machineId int32) (*Machine, error) {
	var fn = "GetMachine"
	client.traceLogger.Printf(LogFmtEnter1, fn, machineId)

	path := "machine"
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	queryParams := make(map[string]string)
	queryParams["mach_id"] = fmt.Sprintf("%v", machineId)
	cliReq.SetParameters(queryParams)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = &respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, *result, nil)
	return result, nil
}

// GetMachines Retrieves information about all the configured machines
// Returns: An array of Machine entities
func (client *LiqidClient) GetMachines() ([]Machine, error) {
	var fn = "GetMachines"
	client.traceLogger.Printf(LogFmtEnter0, fn)

	path := "machine"
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = respWrapper.Response.Data
	client.traceLogger.Printf(LogFmtReturn2, fn, result, nil)
	return result, nil
}

// GetMachinesAtCoordinates Retrieves information about all the configured machines
//
// Param rackId: Rack component of Liqid Coordinates of interest
// Param shelfId: Shelf component of Liqid Coordinates of interest
// Param nodeId: Node component of Liqid Coordinates of interest
// Returns: An array of Machine entities
func (client *LiqidClient) GetMachinesAtCoordinates(rackId int32, shelfId int32, nodeId int32) ([]Machine, error) {
	var fn = "GetMachinesAtCoordinates"
	client.traceLogger.Printf(LogFmtEnter3, fn, rackId, shelfId, nodeId)

	path := "machine"
	path += "/" + fmt.Sprintf("%v", rackId)
	path += "/" + fmt.Sprintf("%v", shelfId)
	path += "/" + fmt.Sprintf("%v", nodeId)
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = respWrapper.Response.Data
	client.traceLogger.Printf(LogFmtReturn2, fn, result, nil)
	return result, nil
}

// GetMachinesByGroupId Retrieves information about all the configured machines in a particular group
//
// Param groupId: Unique group identifier
// Returns: An array of Machine entities
func (client *LiqidClient) GetMachinesByGroupId(groupId int32) ([]Machine, error) {
	var fn = "GetMachinesByGroupId"
	client.traceLogger.Printf(LogFmtEnter1, fn, groupId)

	path := "machine"
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	queryParams := make(map[string]string)
	queryParams["grp_id"] = fmt.Sprintf("%v", groupId)
	cliReq.SetParameters(queryParams)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = respWrapper.Response.Data
	client.traceLogger.Printf(LogFmtReturn2, fn, result, nil)
	return result, nil
}

// GetMachineByName Retrieves information about a configured machine given the machine name
//
// Param machineName: Unique machine name
// Returns: A Machine entity
func (client *LiqidClient) GetMachineByName(machineName string) (*Machine, error) {
	var fn = "GetMachineByName"
	client.traceLogger.Printf(LogFmtEnter1, fn, machineName)

	path := "machine"
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	queryParams := make(map[string]string)
	queryParams["mach_name"] = fmt.Sprintf("%v", machineName)
	cliReq.SetParameters(queryParams)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = &respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, *result, nil)
	return result, nil
}

// GetMachineDetails Retrieves details for a particular machine
//
// Param machineId: Identifier for the machine
// Returns: A MachineDetails entity describing details for the machine
func (client *LiqidClient) GetMachineDetails(machineId int32) (*MachineDetails, error) {
	var fn = "GetMachineDetails"
	client.traceLogger.Printf(LogFmtEnter1, fn, machineId)

	path := "machine/details"
	path += "/" + fmt.Sprintf("%v", machineId)
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineDetailsWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = &respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, *result, nil)
	return result, nil
}

// GetNextMachineId Retrieves the next sequential unused machine identifier
// Returns: The next sequential unused machine identifier
//
// Deprecated: Use of this function in conjunction with CreateMachineWithId opens a potential race condition.
//             This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.
//             A future version of the Director will provide a means of creating a machine whereby the machine id is internally generated.
//             For this reason, SDK clients are encouraged to use the CreateMachine function which wraps the Get/Create mechanism in one SDK function call.
//             For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of CreateMachineWithId and GetNextMachineId.
func (client *LiqidClient) GetNextMachineId() (int32, error) {
	var fn = "GetNextMachineId"
	client.traceLogger.Printf(LogFmtEnter0, fn)

	path := "machine/nextid"
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return 0, cliErr
	}

	var respWrapper StringWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, 0, jsonErr)
		return 0, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, 0, fmtErr)
		return 0, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, 0, fmtErr)
		return 0, fmtErr
	}

	var result = respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, result, nil)
	iResult, _ := strconv.ParseInt(result, 10, 32)
	return int32(iResult), nil
}

// SetP2PEnabled Sets or clears the P2P flag for the indicated machine.
//               Only effective for powered-on machines having at least two GPUs.
//
// Param machineId: Unique id of the machine for which P2P is to be enabled or disabled.
// Param p2pEnabled: Value to be set for the P2P flag.
// Returns: a Machine entity describing the affected machine
func (client *LiqidClient) SetP2PEnabled(machineId int32, p2pEnabled P2PType) (*Machine, error) {
	var fn = "SetP2PEnabled"
	client.traceLogger.Printf(LogFmtEnter2, fn, machineId, p2pEnabled)
	object, getErr := client.GetMachine(machineId)
	if getErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, getErr)
		return nil, getErr
	}
	object.P2PEnabled = p2pEnabled

	path := "machine/p2p"
	cliReq := NewClientRequest()
	cliReq.SetMethod("POST")
	cliReq.SetPartialPath(path)
	cliReq.SetJSONBody(object)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper MachineWrapper
	jsonErr := cliReq.GetJSONResponse(&respWrapper)
	if jsonErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, jsonErr)
		return nil, jsonErr
	}

	if (respWrapper.Response.Code != 0) || (respWrapper.Response.Errors != nil) {
		fmtErr := fmt.Errorf("%v", FormatError(respWrapper.Response.Code, respWrapper.Response.Errors))
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	if len(respWrapper.Response.Data) != 1 {
		fmtErr := fmt.Errorf("malformed data encountered - we should have exactly one data entity")
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, fmtErr)
		return nil, fmtErr
	}

	var result = &respWrapper.Response.Data[0]
	client.traceLogger.Printf(LogFmtReturn2, fn, *result, nil)
	return result, nil
}
