// File: device_gen.go
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
)

// Category: Device
// Function generally related to devices

// CreateDeviceDescription Creates a user-supplied device description for a particular device
//
// Param queryDeviceType: Device type of the device to which a description should be applied
// Param deviceId: Device ID of the device
// Param description: Description to be applied to the device
// Returns: User-supplied device description
func (client *LiqidClient) CreateDeviceDescription(queryDeviceType DeviceQueryType, deviceId int32, description string) (*DeviceUserDescription, error) {
	var fn = "CreateDeviceDescription"
	client.traceLogger.Printf(LogFmtEnter3, fn, queryDeviceType, deviceId, description)
    var deviceIdTranslated = fmt.Sprintf("0x%08x", deviceId)

	path := "device/udesc"
	path += "/" + fmt.Sprintf("%v", queryDeviceType)
	path += "/" + fmt.Sprintf("%v", deviceIdTranslated)
	cliReq := NewClientRequest().SetMethod("PUT").SetPartialPath(path)
	apiParam := NewDeviceUserDescription()
	apiParam.UserDescription = description
	cliReq.SetJSONBody(apiParam)

	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper DeviceUserDescriptionWrapper
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

// DeleteDeviceDescription Deletes a user-supplied device description for a particular device
//
// Param queryDeviceType: Device type of the device to which a description should be applied
// Param deviceId: Device ID
// Returns: Deleted user-supplied device description
func (client *LiqidClient) DeleteDeviceDescription(queryDeviceType DeviceQueryType, deviceId int32) (*DeviceUserDescription, error) {
	var fn = "DeleteDeviceDescription"
	client.traceLogger.Printf(LogFmtEnter2, fn, queryDeviceType, deviceId)
    var deviceIdTranslated = fmt.Sprintf("0x%08x", deviceId)

	path := "device/udesc"
	path += "/" + fmt.Sprintf("%v", queryDeviceType)
	path += "/" + fmt.Sprintf("%v", deviceIdTranslated)
	cliReq := NewClientRequest()
	cliReq.SetMethod("DELETE")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper DeviceUserDescriptionWrapper
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

// GetDeviceAttributes Retrieves available device attribute names for all device types
// Returns: An array of DeviceTypeAndAttributes entities
func (client *LiqidClient) GetDeviceAttributes() ([]DeviceTypeAndAttributes, error) {
	var fn = "GetDeviceAttributes"
	client.traceLogger.Printf(LogFmtEnter0, fn)

	path := "device/attributes"
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper DeviceTypeAndAttributesWrapper
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

// GetSecureEraseStatus Retrieves the status of an asynchronous secure erase operation
//
// Param deviceId: Device identifier returned by SecureErase
// Returns: A AsynchronousStatus entity describing the state of the indicated operation
func (client *LiqidClient) GetSecureEraseStatus(deviceId int32) (*AsynchronousStatus, error) {
	var fn = "GetSecureEraseStatus"
	client.traceLogger.Printf(LogFmtEnter1, fn, deviceId)
    var deviceIdTranslated = fmt.Sprintf("0x%08x", deviceId)

	path := "device/erase/"
	path += "/" + fmt.Sprintf("%v", deviceIdTranslated)
	cliReq := NewClientRequest()
	cliReq.SetMethod("GET")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper AsynchronousStatusWrapper
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

// SecureErase Securely erases a particular storage device.
//             This is an asynchronous operation - the function returns before the process is complete.
//
// Param deviceId: The identifier of the drive to be securely erased.
//                 This is the hexadecimal identifier prefixed with '0x'
// Returns: Describes the asynchronous identifier associated with this operation
func (client *LiqidClient) SecureErase(deviceId int32) (*AsynchronousInfo, error) {
	var fn = "SecureErase"
	client.traceLogger.Printf(LogFmtEnter1, fn, deviceId)
    var deviceIdTranslated = fmt.Sprintf("0x%08x", deviceId)

	path := "device"
	path += "/" + fmt.Sprintf("%v", deviceIdTranslated)
	cliReq := NewClientRequest()
	cliReq.SetMethod("PUT")
	cliReq.SetPartialPath(path)
	cliErr := cliReq.Invoke(client)
	if cliErr != nil {
		client.traceLogger.Printf(LogFmtReturn2, fn, nil, cliErr)
		return nil, cliErr
	}

	var respWrapper AsynchronousInfoWrapper
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
