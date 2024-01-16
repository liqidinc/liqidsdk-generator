# File: liqid_client.py
#
# Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
# 
# Redistribution and use in source and binary forms, with or without
# modification, are not permitted without prior consent.
#
# Liqid SDK - Version 3.4
# This file was automatically generated - do not modify it directly.
#
from abc import ABC
import json
from liqidsdk import base_client, exceptions
from liqidsdk.classes import *


def new_client(host_string, port_number=base_client.DefaultPort, timeout=base_client.DefaultTimeout):
    lc = LiqidClient()
    lc.SecureHTTP = False
    lc.HostAddress = host_string
    lc.PortNumber = port_number
    lc.TimeoutInSeconds = timeout
    lc.setup_connection()
    return lc


def new_secure_client(host_string, port_number=base_client.DefaultSecurePort, timeout=base_client.DefaultTimeout):
    lc = LiqidClient()
    lc.SecureHTTP = True
    lc.HostAddress = host_string
    lc.PortNumber = port_number
    lc.TimeoutInSeconds = timeout
    lc.setup_connection()
    return lc


class LiqidClient(base_client.LiqidClientBase, ABC):
    """
    This struct is necessary for managing all communication with the Liqid Director.
    For OO languages, it serves as the class on which all methods are defined.
    For non-OO languages, it is a required parameter for all function invocations.
    """

    def __str__(self):
        s = '{'
        s += 'SecureHTTP: ' + str(self.SecureHTTP)
        s += ', ' + 'HostAddress: ' + str(self.HostAddress)
        s += ', ' + 'PortNumber: ' + str(self.PortNumber)
        s += ', ' + 'IgnoreCertificates: ' + str(self.IgnoreCertificates)
        s += ', ' + 'TimeoutInSeconds: ' + str(self.TimeoutInSeconds)
        s += ', ' + 'RetryOnServerError: ' + str(self.RetryOnServerError)
        s += ', ' + 'RetryLimit: ' + str(self.RetryLimit)
        s += ', ' + 'RetryDelayInSeconds: ' + str(self.RetryDelayInSeconds)
        s += ', ' + 'WaitForAsyncCompletion: ' + str(self.WaitForAsyncCompletion)
        s += ', ' + 'MaxAsyncWaitTimeInSeconds: ' + str(self.MaxAsyncWaitTimeInSeconds)
        s += '}'
        return s

    def __repr__(self):
        return self.__str__()

    def add_compute_device_to_group(self, device_id, group_id) -> GroupComputeDeviceRelator:
        """
        Moves a device from the system free pool to the group free pool for the indicated group
        device_id: int 
            Identifier of the device to be added to a group free pool.
        group_id: int 
            Identifier of the group to which the device is to be added.
        """
        self.logger.debug('entering add_compute_device_to_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_compute_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupComputeDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/compute'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupComputeDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_compute_device_to_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_compute_device_to_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_compute_device_to_group raising ' + str(ex))
            raise exceptions.LiqidError('add_compute_device_to_group caught ' + str(ex))

    def add_compute_device_to_machine(self, device_id, group_id, machine_id) -> GroupComputeDeviceRelator:
        """
        Attaches a particular device to a machine
        device_id: int 
            Unique identifier for the device to be added
        group_id: int 
            Unique identifier for the group to which the machine belongs
        machine_id: int 
            Unique identifier for the machine to which the device is to be added
        """
        self.logger.debug('entering add_compute_device_to_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_compute_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineComputeDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/compute'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupComputeDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_compute_device_to_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_compute_device_to_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_compute_device_to_machine raising ' + str(ex))
            raise exceptions.LiqidError('add_compute_device_to_machine caught ' + str(ex))

    def add_fpga_device_to_group(self, device_id, group_id) -> GroupFPGADeviceRelator:
        """
        Moves a device from the system free pool to the group free pool for the indicated group
        device_id: int 
            Identifier of the device to be added to a group free pool.
        group_id: int 
            Identifier of the group to which the device is to be added.
        """
        self.logger.debug('entering add_fpga_device_to_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_fpga_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupFPGADeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/fpga'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupFPGADeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_fpga_device_to_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_fpga_device_to_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_fpga_device_to_group raising ' + str(ex))
            raise exceptions.LiqidError('add_fpga_device_to_group caught ' + str(ex))

    def add_fpga_device_to_machine(self, device_id, group_id, machine_id) -> GroupFPGADeviceRelator:
        """
        Attaches a particular device to a machine
        device_id: int 
            Unique identifier for the device to be added
        group_id: int 
            Unique identifier for the group to which the machine belongs
        machine_id: int 
            Unique identifier for the machine to which the device is to be added
        """
        self.logger.debug('entering add_fpga_device_to_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_fpga_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineFPGADeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/fpga'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupFPGADeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_fpga_device_to_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_fpga_device_to_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_fpga_device_to_machine raising ' + str(ex))
            raise exceptions.LiqidError('add_fpga_device_to_machine caught ' + str(ex))

    def add_gpu_device_to_group(self, device_id, group_id) -> GroupGPUDeviceRelator:
        """
        Moves a device from the system free pool to the group free pool for the indicated group
        device_id: int 
            Identifier of the device to be added to a group free pool.
        group_id: int 
            Identifier of the group to which the device is to be added.
        """
        self.logger.debug('entering add_gpu_device_to_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_gpu_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupGPUDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/gpu'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupGPUDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_gpu_device_to_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_gpu_device_to_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_gpu_device_to_group raising ' + str(ex))
            raise exceptions.LiqidError('add_gpu_device_to_group caught ' + str(ex))

    def add_gpu_device_to_machine(self, device_id, group_id, machine_id) -> GroupGPUDeviceRelator:
        """
        Attaches a particular device to a machine
        device_id: int 
            Unique identifier for the device to be added
        group_id: int 
            Unique identifier for the group to which the machine belongs
        machine_id: int 
            Unique identifier for the machine to which the device is to be added
        """
        self.logger.debug('entering add_gpu_device_to_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_gpu_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineGPUDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/gpu'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupGPUDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_gpu_device_to_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_gpu_device_to_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_gpu_device_to_machine raising ' + str(ex))
            raise exceptions.LiqidError('add_gpu_device_to_machine caught ' + str(ex))

    def add_memory_device_to_group(self, device_id, group_id) -> GroupMemoryDeviceRelator:
        """
        Moves a device from the system free pool to the group free pool for the indicated group
        device_id: int 
            Identifier of the device to be added to a group free pool.
        group_id: int 
            Identifier of the group to which the device is to be added.
        """
        self.logger.debug('entering add_memory_device_to_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_memory_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupMemoryDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/mem'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupMemoryDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_memory_device_to_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_memory_device_to_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_memory_device_to_group raising ' + str(ex))
            raise exceptions.LiqidError('add_memory_device_to_group caught ' + str(ex))

    def add_memory_device_to_machine(self, device_id, group_id, machine_id) -> GroupMemoryDeviceRelator:
        """
        Attaches a particular device to a machine
        device_id: int 
            Unique identifier for the device to be added
        group_id: int 
            Unique identifier for the group to which the machine belongs
        machine_id: int 
            Unique identifier for the machine to which the device is to be added
        """
        self.logger.debug('entering add_memory_device_to_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_memory_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineMemoryDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/memory'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupMemoryDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_memory_device_to_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_memory_device_to_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_memory_device_to_machine raising ' + str(ex))
            raise exceptions.LiqidError('add_memory_device_to_machine caught ' + str(ex))

    def add_network_device_to_group(self, device_id, group_id) -> GroupNetworkDeviceRelator:
        """
        Moves a device from the system free pool to the group free pool for the indicated group
        device_id: int 
            Identifier of the device to be added to a group free pool.
        group_id: int 
            Identifier of the group to which the device is to be added.
        """
        self.logger.debug('entering add_network_device_to_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_network_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupNetworkDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/network'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupNetworkDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_network_device_to_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_network_device_to_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_network_device_to_group raising ' + str(ex))
            raise exceptions.LiqidError('add_network_device_to_group caught ' + str(ex))

    def add_network_device_to_machine(self, device_id, group_id, machine_id) -> GroupNetworkDeviceRelator:
        """
        Attaches a particular device to a machine
        device_id: int 
            Unique identifier for the device to be added
        group_id: int 
            Unique identifier for the group to which the machine belongs
        machine_id: int 
            Unique identifier for the machine to which the device is to be added
        """
        self.logger.debug('entering add_network_device_to_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_network_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineNetworkDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/network'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupNetworkDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_network_device_to_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_network_device_to_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_network_device_to_machine raising ' + str(ex))
            raise exceptions.LiqidError('add_network_device_to_machine caught ' + str(ex))

    def add_storage_device_to_group(self, device_id, group_id) -> GroupStorageDeviceRelator:
        """
        Moves a device from the system free pool to the group free pool for the indicated group
        device_id: int 
            Identifier of the device to be added to a group free pool.
        group_id: int 
            Identifier of the group to which the device is to be added.
        """
        self.logger.debug('entering add_storage_device_to_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_storage_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupStorageDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/storage'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupStorageDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_storage_device_to_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_storage_device_to_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_storage_device_to_group raising ' + str(ex))
            raise exceptions.LiqidError('add_storage_device_to_group caught ' + str(ex))

    def add_storage_device_to_machine(self, device_id, group_id, machine_id) -> GroupStorageDeviceRelator:
        """
        Attaches a particular device to a machine
        device_id: int 
            Unique identifier for the device to be added
        group_id: int 
            Unique identifier for the group to which the machine belongs
        machine_id: int 
            Unique identifier for the machine to which the device is to be added
        """
        self.logger.debug('entering add_storage_device_to_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_storage_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineStorageDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/storage'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupStorageDeviceRelator().from_dictionary(data[0])
            self.logger.debug('add_storage_device_to_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('add_storage_device_to_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('add_storage_device_to_machine raising ' + str(ex))
            raise exceptions.LiqidError('add_storage_device_to_machine caught ' + str(ex))

    def apply_fabric_changes(self, flag_name, flag_value, operation) -> FabricToggleComposite:
        """
        applies changes to the fabric - currently one may only add a particular flag with a value
        flag_name: str 
            The name of the flag to be added
        flag_value: str 
            The value for the flag to be added
        operation: str 
            Currently, only ADD is supported
        """
        self.logger.debug('entering apply_fabric_changes(%s, %s, %s)', str(flag_name), str(flag_value), str(operation))
        self.check_parameter_type('flag_name', flag_name, 'str', False)
        self.check_parameter_type('flag_value', flag_value, 'str', False)
        self.check_parameter_type('operation', operation, 'str', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = FabricToggleComposite()
            obj.ControlFlag.Name = flag_name
            obj.ControlFlag.ValueString = flag_value
            obj.Options = operation
            obj.Coordinates.Rack = self._get_preset_rack()
            obj.Coordinates.Shelf = self._get_preset_shelf()
            obj.Coordinates.Node = self._get_preset_node()

            path = 'fabric'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPut, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = FabricToggleComposite().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('apply_fabric_changes raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('apply_fabric_changes raising ' + str(ex))
            raise exceptions.LiqidError('apply_fabric_changes caught ' + str(ex))

    def backup_director(self, destination) -> BackupResult:
        """
        Retrieves the result of the backup process
        destination: int 
            
        """
        self.logger.debug('entering backup_director(%s)', str(destination))
        self.check_parameter_type('destination', destination, 'int', False)

        try:
            path = 'backup'
            path += '/' + str(destination)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = BackupResult().from_dictionary(data[0])
            self.logger.debug('backup_director returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('backup_director raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('backup_director raising ' + str(ex))
            raise exceptions.LiqidError('backup_director caught ' + str(ex))

    def cancel_edit_fabric(self, machine_id) -> Machine:
        """
        Cancels fabric edit mode - reverts all pending changes made to device connections
        machine_id: int 
            Unique id of the machine for which edit mode is to be canceled.
        """
        self.logger.debug('entering cancel_edit_fabric(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            obj = self.get_machine(machine_id)
            path = 'fabric/edit/cancel'
            req_body = json.dumps(obj.to_dictionary())
            headers = {'content-type': 'application/json'}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('cancel_edit_fabric returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('cancel_edit_fabric raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('cancel_edit_fabric raising ' + str(ex))
            raise exceptions.LiqidError('cancel_edit_fabric caught ' + str(ex))

    def cancel_group_pool_edit(self, group_id) -> GroupPool:
        """
        Cancels a pending group pool edit operation.
        All pending device attachments or detachments will be canceled.
        group_id: int 
            Identifier of the group for which edit mode is to be canceled
        """
        self.logger.debug('entering cancel_group_pool_edit(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = GroupPool()
            obj.GroupId = group_id
            obj.Coordinates.Rack = self._get_preset_rack()
            obj.Coordinates.Shelf = self._get_preset_shelf()
            obj.Coordinates.Node = self._get_preset_node()
            obj.FabricId = self._get_preset_fabric_id()

            path = 'pool/cancel'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupPool().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('cancel_group_pool_edit raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('cancel_group_pool_edit raising ' + str(ex))
            raise exceptions.LiqidError('cancel_group_pool_edit caught ' + str(ex))

    def cancel_reprogram_fabric(self, machine_id) -> Machine:
        """
        Cancels fabric reprogram mode
        machine_id: int 
            Unique id of the machine for which fabric reprogramming is to be canceled.
        """
        self.logger.debug('entering cancel_reprogram_fabric(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            obj = self.get_machine(machine_id)
            path = 'fabric/reprogram/cancel'
            req_body = json.dumps(obj.to_dictionary())
            headers = {'content-type': 'application/json'}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('cancel_reprogram_fabric returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('cancel_reprogram_fabric raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('cancel_reprogram_fabric raising ' + str(ex))
            raise exceptions.LiqidError('cancel_reprogram_fabric caught ' + str(ex))

    def clear_groups(self) -> bool:
        """
        Deletes all groups and all machines within those groups.
        Restores all devices to the system free pool.
        This is effectively a soft configuration reset which does not rediscover devices.
        """
        self.logger.debug('entering clear_groups()')

        try:
            path = 'group/clear'
            response, resp_body = self.send(base_client.HttpPost, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            self.logger.debug('clear_groups returning %s', str(data[0]))
            return data[0]
        except exceptions.LiqidError as ex:
            self.logger.debug('clear_groups raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('clear_groups raising ' + str(ex))
            raise exceptions.LiqidError('clear_groups caught ' + str(ex))

    def create_device_description(self, query_device_type, device_id, description) -> DeviceUserDescription:
        """
        Creates a user-supplied device description for a particular device
        query_device_type: str 
            Device type of the device to which a description should be applied
        device_id: int 
            Device ID of the device
        description: str 
            Description to be applied to the device
        """
        self.logger.debug('entering create_device_description(%s, %s, %s)', str(query_device_type), str(device_id), str(description))
        self.check_parameter_type('query_device_type', query_device_type, 'str', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('description', description, 'str', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = DeviceUserDescription()
            obj.UserDescription = description

            device_id_translated = '0x{:08x}'.format(device_id)
            path = 'device/udesc'
            path += '/' + str(query_device_type)
            path += '/' + str(device_id_translated)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPut, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = DeviceUserDescription().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('create_device_description raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('create_device_description raising ' + str(ex))
            raise exceptions.LiqidError('create_device_description caught ' + str(ex))

    def create_group_with_id(self, group_id, group_name) -> Group:
        """
        Creates a new group within the current fabric.
        group_id: int 
            Unique identifier to be associated with the group
        group_name: str 
            Unique name of the group
        Deprecated:
        SDK Clients should use create_group instead, which does not require that the client specify a group id.
        """
        self.logger.debug('entering create_group_with_id(%s, %s)', str(group_id), str(group_name))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('group_name', group_name, 'str', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = Group()
            obj.GroupId = group_id
            obj.GroupName = group_name
            obj.FabricId = self._get_preset_fabric_id()

            path = 'group'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Group().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('create_group_with_id raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('create_group_with_id raising ' + str(ex))
            raise exceptions.LiqidError('create_group_with_id caught ' + str(ex))

    def create_machine_with_id(self, group_id, machine_id, machine_name) -> Machine:
        """
        Creates a new machine for a particular group
        group_id: int 
            Identifier of the group to which the machine should be attached
        machine_id: int 
            Unique identifier to be applied to the machine
        machine_name: str 
            Name for the newly-created machine
        Deprecated:
        SDK Clients should use create_machine instead, which does not require that the client specify a machine id.
        """
        self.logger.debug('entering create_machine_with_id(%s, %s, %s)', str(group_id), str(machine_id), str(machine_name))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        self.check_parameter_type('machine_name', machine_name, 'str', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = Machine()
            obj.GroupId = group_id
            obj.MachineId = machine_id
            obj.MachineName = machine_name
            obj.ComputeName = machine_name
            obj.FabricId = self._get_preset_fabric_id()

            path = 'machine'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('create_machine_with_id raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('create_machine_with_id raising ' + str(ex))
            raise exceptions.LiqidError('create_machine_with_id caught ' + str(ex))

    def create_message_digest(self, label) -> DigestInfo:
        """
        Creates a new digest associated with a given label.
        The digest will be returned to the client, and will not be otherwise exposed by the Director.
        This digest should be used for authenticating subsequent REST API invocations.
        At the end of the client session, this digest should be deleted by invoking delete_message_digest.
        label: str 
            The label to be associated with a newly-created digest
        """
        self.logger.debug('entering create_message_digest(%s)', str(label))
        self.check_parameter_type('label', label, 'str', False)

        try:
            path = 'digest'
            path += '/' + str(label)
            response, resp_body = self.send(base_client.HttpPost, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = DigestInfo().from_dictionary(data[0])
            self.logger.debug('create_message_digest returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('create_message_digest raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('create_message_digest raising ' + str(ex))
            raise exceptions.LiqidError('create_message_digest caught ' + str(ex))

    def delete_device_description(self, query_device_type, device_id) -> DeviceUserDescription:
        """
        Deletes a user-supplied device description for a particular device
        query_device_type: str 
            Device type of the device to which a description should be applied
        device_id: int 
            Device ID
        """
        self.logger.debug('entering delete_device_description(%s, %s)', str(query_device_type), str(device_id))
        self.check_parameter_type('query_device_type', query_device_type, 'str', False)
        self.check_parameter_type('device_id', device_id, 'int', False)

        try:
            device_id_translated = '0x{:08x}'.format(device_id)
            path = 'device/udesc'
            path += '/' + str(query_device_type)
            path += '/' + str(device_id_translated)
            response, resp_body = self.send(base_client.HttpDelete, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = DeviceUserDescription().from_dictionary(data[0])
            self.logger.debug('delete_device_description returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('delete_device_description raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('delete_device_description raising ' + str(ex))
            raise exceptions.LiqidError('delete_device_description caught ' + str(ex))

    def delete_group(self, group_id) -> Group:
        """
        Deletes a configured group along with all the machines in the group.
        Returns all devices related to the group or to the machines in the group to the system free pool.
        group_id: int 
            Unique identifier of the group to be deleted
        """
        self.logger.debug('entering delete_group(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        try:
            path = 'group'
            path += '/' + str(group_id)
            response, resp_body = self.send(base_client.HttpDelete, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Group().from_dictionary(data[0])
            self.logger.debug('delete_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('delete_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('delete_group raising ' + str(ex))
            raise exceptions.LiqidError('delete_group caught ' + str(ex))

    def delete_machine(self, machine_id) -> Machine:
        """
        Deletes a configured machine, returning its attached devices to the containing group free pool
        machine_id: int 
            Unique identifier for the machine to be deleted
        """
        self.logger.debug('entering delete_machine(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'machine'
            path += '/' + str(machine_id)
            response, resp_body = self.send(base_client.HttpDelete, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('delete_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('delete_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('delete_machine raising ' + str(ex))
            raise exceptions.LiqidError('delete_machine caught ' + str(ex))

    def edit_fabric(self, machine_id) -> Machine:
        """
        enters fabric edit mode which allows the fabric to be electrically connected
         - the system must be put into edit mode before a device is added to a machine
        machine_id: int 
            Unique id of the machine for which edit mode is to be established.
        """
        self.logger.debug('entering edit_fabric(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            obj = self.get_machine(machine_id)
            path = 'fabric/edit'
            req_body = json.dumps(obj.to_dictionary())
            headers = {'content-type': 'application/json'}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('edit_fabric returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('edit_fabric raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('edit_fabric raising ' + str(ex))
            raise exceptions.LiqidError('edit_fabric caught ' + str(ex))

    def get_all_devices_status(self) -> list:
        """
        Retrieves status for all devices
        """
        self.logger.debug('entering get_all_devices_status()')

        try:
            path = 'status'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(DeviceStatus().from_dictionary(item))
            self.logger.debug('get_all_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_all_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_all_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_all_devices_status caught ' + str(ex))

    def get_available_coordinates(self) -> list:
        """
        Retrieves a list of entities indicating the known values which may be employed for identifying particular Liqid entities
        within a configuration. As a general rule, there will be only one such entry.
        """
        self.logger.debug('entering get_available_coordinates()')

        try:
            path = 'coordinates/available'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(AvailableCoordinates().from_dictionary(item))
            self.logger.debug('get_available_coordinates returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_available_coordinates raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_available_coordinates raising ' + str(ex))
            raise exceptions.LiqidError('get_available_coordinates caught ' + str(ex))

    def get_compute_device_status(self, device_id) -> ComputeDeviceStatus:
        """
        Retrieves status for a particular compute device
        device_id: int 
            Identifier of the device in question
        """
        self.logger.debug('entering get_compute_device_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        for item in self.get_compute_devices_status():
            if device_id == item.get_device_id():
                self.logger.debug("get_compute_device_status returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_compute_device_status raising " + str(ex))
        raise ex

    def get_compute_devices_status(self) -> list:
        """
        Retrieves status for compute devices
        """
        self.logger.debug('entering get_compute_devices_status()')

        try:
            path = 'status/compute'
            param_list = []
            param_list.append('filter=' + str(False))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(ComputeDeviceStatus().from_dictionary(item))
            self.logger.debug('get_compute_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_compute_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_compute_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_compute_devices_status caught ' + str(ex))

    def get_compute_devices_status_with_multiple_ports_status(self) -> list:
        """
        Retrieves status for compute devices
        """
        self.logger.debug('entering get_compute_devices_status_with_multiple_ports_status()')

        try:
            path = 'status/compute/parents'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(ComputeDeviceStatus().from_dictionary(item))
            self.logger.debug('get_compute_devices_status_with_multiple_ports_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_compute_devices_status_with_multiple_ports_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_compute_devices_status_with_multiple_ports_status raising ' + str(ex))
            raise exceptions.LiqidError('get_compute_devices_status_with_multiple_ports_status caught ' + str(ex))

    def get_compute_device_info(self) -> list:
        """
        Retrieves additional information for all known compute devices
        """
        self.logger.debug('entering get_compute_device_info()')

        try:
            path = 'device/info/cpu'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(ComputeDeviceInfo().from_dictionary(item))
            self.logger.debug('get_compute_device_info returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_compute_device_info raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_compute_device_info raising ' + str(ex))
            raise exceptions.LiqidError('get_compute_device_info caught ' + str(ex))

    def get_compute_device_info_by_name(self, device_name) -> ComputeDeviceInfo:
        """
        Retrieves additional information for a particular compute device
        device_name: str 
            Name of the device in question
        """
        self.logger.debug('entering get_compute_device_info_by_name(%s)', str(device_name))
        self.check_parameter_type('device_name', device_name, 'str', False)

        try:
            path = 'device/info/cpu'
            path += '/' + str(device_name)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = ComputeDeviceInfo().from_dictionary(data[0])
            self.logger.debug('get_compute_device_info_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_compute_device_info_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_compute_device_info_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_compute_device_info_by_name caught ' + str(ex))

    def get_current_fabric_id(self) -> int:
        """
        Retrieves the fabric id associated with the current default coordinates.
        This is not generally needed for SDK interactions, but may be useful for troubleshooting.
        """
        self.logger.debug('entering get_current_fabric_id()')

        try:
            path = 'fabric/id'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            self.logger.debug('get_current_fabric_id returning %s', str(data[0]))
            return data[0]
        except exceptions.LiqidError as ex:
            self.logger.debug('get_current_fabric_id raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_current_fabric_id raising ' + str(ex))
            raise exceptions.LiqidError('get_current_fabric_id caught ' + str(ex))

    def get_default_coordinates(self) -> Coordinates:
        """
        Retrieves the current default coordinates used for the various operations which are initiated by the REST API for the SDK.
        """
        self.logger.debug('entering get_default_coordinates()')

        try:
            path = 'coordinates'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Coordinates().from_dictionary(data[0])
            self.logger.debug('get_default_coordinates returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_default_coordinates raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_default_coordinates raising ' + str(ex))
            raise exceptions.LiqidError('get_default_coordinates caught ' + str(ex))

    def get_devices(self, query_device_type, group_id, machine_id) -> list:
        """
        Returns information regarding devices which are attached to a particular group.
        query_device_type: str  (optional - can be None)
            Limits the device type of the devices to be queried.
            If not specified, all device types will be returned.
        group_id: int 
            Indicates the group for which devices are queried.
            If MachineId is specified, only those devices which are in the
            group free pool will be returned.
        machine_id: int  (optional - can be None)
            Only return devices associated with the indicated machine.
        """
        self.logger.debug('entering get_devices(%s, %s, %s)', str(query_device_type), str(group_id), str(machine_id))
        self.check_parameter_type('query_device_type', query_device_type, 'str', True)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', True)

        try:
            path = 'predevice'
            param_list = []
            if query_device_type is not None:
                param_list.append('dev_type=' + str(query_device_type))
            param_list.append('grp_id=' + str(group_id))
            if machine_id is not None:
                param_list.append('mach_id=' + str(machine_id))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(PreDevice().from_dictionary(item))
            self.logger.debug('get_devices returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_devices raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_devices raising ' + str(ex))
            raise exceptions.LiqidError('get_devices caught ' + str(ex))

    def get_device_attributes(self) -> list:
        """
        Retrieves available device attribute names for all device types
        """
        self.logger.debug('entering get_device_attributes()')

        try:
            path = 'device/attributes'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(DeviceTypeAndAttributes().from_dictionary(item))
            self.logger.debug('get_device_attributes returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_device_attributes raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_device_attributes raising ' + str(ex))
            raise exceptions.LiqidError('get_device_attributes caught ' + str(ex))

    def get_device_counters(self) -> DeviceCounters:
        """
        Retrieves counters of devices for each type of device
        """
        self.logger.debug('entering get_device_counters()')

        try:
            path = 'devices/count'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = DeviceCounters().from_dictionary(data[0])
            self.logger.debug('get_device_counters returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_device_counters raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_device_counters raising ' + str(ex))
            raise exceptions.LiqidError('get_device_counters caught ' + str(ex))

    def get_discovery_tokens(self) -> list:
        """
        Returns all of the configured discovery tokens
        """
        self.logger.debug('entering get_discovery_tokens()')

        try:
            path = 'manager/discovery'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(DiscoveryToken().from_dictionary(item))
            self.logger.debug('get_discovery_tokens returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_discovery_tokens raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_discovery_tokens raising ' + str(ex))
            raise exceptions.LiqidError('get_discovery_tokens caught ' + str(ex))

    def get_discovery_tokens_by_type(self, token_type) -> list:
        """
        Returns a subset of the configured discovery tokens, specified by the TokenType parameter
        token_type: str 
            The type of tokens requested
        """
        self.logger.debug('entering get_discovery_tokens_by_type(%s)', str(token_type))
        self.check_parameter_type('token_type', token_type, 'str', False)

        try:
            path = 'manager/discovery'
            path += '/' + str(token_type)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(DiscoveryToken().from_dictionary(item))
            self.logger.debug('get_discovery_tokens_by_type returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_discovery_tokens_by_type raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_discovery_tokens_by_type raising ' + str(ex))
            raise exceptions.LiqidError('get_discovery_tokens_by_type caught ' + str(ex))

    def get_fabric_topology(self) -> FabricTopology:
        """
        Returns the current fabric topology
        """
        self.logger.debug('entering get_fabric_topology()')

        try:
            path = 'fabric/topology'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = FabricTopology().from_dictionary(data[0])
            self.logger.debug('get_fabric_topology returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_fabric_topology raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_fabric_topology raising ' + str(ex))
            raise exceptions.LiqidError('get_fabric_topology caught ' + str(ex))

    def get_fabric_types(self) -> list:
        """
        Returns all known fabric types and associated id values
        """
        self.logger.debug('entering get_fabric_types()')

        try:
            path = 'fabric/types'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NameValuePair().from_dictionary(item))
            self.logger.debug('get_fabric_types returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_fabric_types raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_fabric_types raising ' + str(ex))
            raise exceptions.LiqidError('get_fabric_types caught ' + str(ex))

    def get_fpga_device_status(self, device_id) -> FPGADeviceStatus:
        """
        Retrieves status for a particular FPGA device
        device_id: int 
            Identifier of the device in question
        """
        self.logger.debug('entering get_fpga_device_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        for item in self.get_fpga_devices_status():
            if device_id == item.get_device_id():
                self.logger.debug("get_fpga_device_status returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_fpga_device_status raising " + str(ex))
        raise ex

    def get_fpga_devices_status(self) -> list:
        """
        Retrieves status for FPGA devices
        """
        self.logger.debug('entering get_fpga_devices_status()')

        try:
            path = 'status/fpga'
            param_list = []
            param_list.append('filter=' + str(False))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(FPGADeviceStatus().from_dictionary(item))
            self.logger.debug('get_fpga_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_fpga_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_fpga_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_fpga_devices_status caught ' + str(ex))

    def get_fpga_device_info(self) -> list:
        """
        Retrieves additional information for all known FPGA devices
        """
        self.logger.debug('entering get_fpga_device_info()')

        try:
            path = 'device/info/fpga'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(FPGADeviceInfo().from_dictionary(item))
            self.logger.debug('get_fpga_device_info returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_fpga_device_info raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_fpga_device_info raising ' + str(ex))
            raise exceptions.LiqidError('get_fpga_device_info caught ' + str(ex))

    def get_fpga_device_info_by_name(self, device_name) -> FPGADeviceInfo:
        """
        Retrieves additional information for a particular FPGA device
        device_name: str 
            Name of the device in question
        """
        self.logger.debug('entering get_fpga_device_info_by_name(%s)', str(device_name))
        self.check_parameter_type('device_name', device_name, 'str', False)

        try:
            path = 'device/info/fpga'
            path += '/' + str(device_name)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = FPGADeviceInfo().from_dictionary(data[0])
            self.logger.debug('get_fpga_device_info_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_fpga_device_info_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_fpga_device_info_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_fpga_device_info_by_name caught ' + str(ex))

    def get_free_compute_devices_status(self) -> list:
        """
        Retrieves status for compute devices which are not assigned to a group or machine
        """
        self.logger.debug('entering get_free_compute_devices_status()')

        try:
            headers = {'content-type': 'application/json'}
            obj = ComputeDeviceStatus()

            path = 'status/compute'
            param_list = []
            param_list.append('filter=' + str(True))
            path += '?' + '&'.join(param_list)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpGet, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(ComputeDeviceStatus().from_dictionary(item))
            self.logger.debug('result returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_free_compute_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_free_compute_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_free_compute_devices_status caught ' + str(ex))

    def get_free_fpga_devices_status(self) -> list:
        """
        Retrieves status for compute FPGA which are not assigned to a group or machine
        """
        self.logger.debug('entering get_free_fpga_devices_status()')

        try:
            headers = {'content-type': 'application/json'}
            obj = FPGADeviceStatus()

            path = 'status/fpga'
            param_list = []
            param_list.append('filter=' + str(True))
            path += '?' + '&'.join(param_list)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpGet, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(FPGADeviceStatus().from_dictionary(item))
            self.logger.debug('result returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_free_fpga_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_free_fpga_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_free_fpga_devices_status caught ' + str(ex))

    def get_free_gpu_devices_status(self) -> list:
        """
        Retrieves status for compute GPU which are not assigned to a group or machine
        """
        self.logger.debug('entering get_free_gpu_devices_status()')

        try:
            headers = {'content-type': 'application/json'}
            obj = GPUDeviceStatus()

            path = 'status/gpu'
            param_list = []
            param_list.append('filter=' + str(True))
            path += '?' + '&'.join(param_list)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpGet, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(GPUDeviceStatus().from_dictionary(item))
            self.logger.debug('result returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_free_gpu_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_free_gpu_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_free_gpu_devices_status caught ' + str(ex))

    def get_free_memory_devices_status(self) -> list:
        """
        Retrieves status for memory devices which are not assigned to a group or machine
        """
        self.logger.debug('entering get_free_memory_devices_status()')

        try:
            headers = {'content-type': 'application/json'}
            obj = MemoryDeviceStatus()

            path = 'status/mem'
            param_list = []
            param_list.append('filter=' + str(True))
            path += '?' + '&'.join(param_list)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpGet, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(MemoryDeviceStatus().from_dictionary(item))
            self.logger.debug('result returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_free_memory_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_free_memory_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_free_memory_devices_status caught ' + str(ex))

    def get_free_network_devices_status(self) -> list:
        """
        Retrieves status for network devices which are not assigned to a group or machine
        """
        self.logger.debug('entering get_free_network_devices_status()')

        try:
            headers = {'content-type': 'application/json'}
            obj = NetworkDeviceStatus()

            path = 'status/network'
            param_list = []
            param_list.append('filter=' + str(True))
            path += '?' + '&'.join(param_list)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpGet, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NetworkDeviceStatus().from_dictionary(item))
            self.logger.debug('result returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_free_network_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_free_network_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_free_network_devices_status caught ' + str(ex))

    def get_free_storage_devices_status(self) -> list:
        """
        Retrieves status for storage devices which are not assigned to a group or machine
        """
        self.logger.debug('entering get_free_storage_devices_status()')

        try:
            headers = {'content-type': 'application/json'}
            obj = StorageDeviceStatus()

            path = 'status/storage'
            param_list = []
            param_list.append('filter=' + str(True))
            path += '?' + '&'.join(param_list)
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpGet, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(StorageDeviceStatus().from_dictionary(item))
            self.logger.debug('result returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_free_storage_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_free_storage_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_free_storage_devices_status caught ' + str(ex))

    def get_gpu_device_status(self, device_id) -> GPUDeviceStatus:
        """
        Retrieves status for a particular GPU device
        device_id: int 
            Identifier of the device in question
        """
        self.logger.debug('entering get_gpu_device_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        for item in self.get_gpu_devices_status():
            if device_id == item.get_device_id():
                self.logger.debug("get_gpu_device_status returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_gpu_device_status raising " + str(ex))
        raise ex

    def get_gpu_devices_status(self) -> list:
        """
        Retrieves status for GPU devices
        """
        self.logger.debug('entering get_gpu_devices_status()')

        try:
            path = 'status/gpu'
            param_list = []
            param_list.append('filter=' + str(False))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(GPUDeviceStatus().from_dictionary(item))
            self.logger.debug('get_gpu_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_gpu_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_gpu_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_gpu_devices_status caught ' + str(ex))

    def get_gpu_device_info(self) -> list:
        """
        Retrieves additional information for all known GPU devices
        """
        self.logger.debug('entering get_gpu_device_info()')

        try:
            path = 'device/info/gpu'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(GPUDeviceInfo().from_dictionary(item))
            self.logger.debug('get_gpu_device_info returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_gpu_device_info raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_gpu_device_info raising ' + str(ex))
            raise exceptions.LiqidError('get_gpu_device_info caught ' + str(ex))

    def get_gpu_device_info_by_name(self, device_name) -> GPUDeviceInfo:
        """
        Retrieves additional information for a particular GPU device
        device_name: str 
            Name of the device in question
        """
        self.logger.debug('entering get_gpu_device_info_by_name(%s)', str(device_name))
        self.check_parameter_type('device_name', device_name, 'str', False)

        try:
            path = 'device/info/gpu'
            path += '/' + str(device_name)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GPUDeviceInfo().from_dictionary(data[0])
            self.logger.debug('get_gpu_device_info_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_gpu_device_info_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_gpu_device_info_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_gpu_device_info_by_name caught ' + str(ex))

    def get_group(self, group_id) -> Group:
        """
        Retrieves a particular group given its identifier
        group_id: int 
            Identifier of the group in question
        """
        self.logger.debug('entering get_group(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        for item in self.get_groups():
            if group_id == item.get_group_id():
                self.logger.debug("get_group returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_group raising " + str(ex))
        raise ex

    def get_groups(self) -> list:
        """
        Retrieves all configured groups
        """
        self.logger.debug('entering get_groups()')

        try:
            path = 'group'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(Group().from_dictionary(item))
            self.logger.debug('get_groups returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_groups raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_groups raising ' + str(ex))
            raise exceptions.LiqidError('get_groups caught ' + str(ex))

    def get_group_compute_device_relator(self, group_id, device_id) -> GroupComputeDeviceRelator:
        """
        This function is required by the SDK architecture.
        It is not intended for use by the SDK client.
        group_id: int 
            Identifier of the related group
        device_id: int 
            Identifier of the related device
        """
        self.logger.debug('entering get_group_compute_device_relator(%s, %s)', str(group_id), str(device_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        try:
            obj0 = self.get_group(group_id)
            obj1 = self.get_compute_device_status(device_id)
            composite = GroupComputeDeviceRelator()
            composite.DeviceStatus = obj1
            composite.Group = obj0
            return composite
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_compute_device_relator raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_compute_device_relator raising ' + str(ex))
            raise exceptions.LiqidError('get_group_compute_device_relator caught ' + str(ex))

    def get_group_fpga_device_relator(self, group_id, device_id) -> GroupFPGADeviceRelator:
        """
        This function is required by the SDK architecture.
        It is not intended for use by the SDK client.
        group_id: int 
            Identifier of the related group
        device_id: int 
            Identifier of the related device
        """
        self.logger.debug('entering get_group_fpga_device_relator(%s, %s)', str(group_id), str(device_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        try:
            obj0 = self.get_group(group_id)
            obj1 = self.get_fpga_device_status(device_id)
            composite = GroupFPGADeviceRelator()
            composite.DeviceStatus = obj1
            composite.Group = obj0
            return composite
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_fpga_device_relator raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_fpga_device_relator raising ' + str(ex))
            raise exceptions.LiqidError('get_group_fpga_device_relator caught ' + str(ex))

    def get_group_gpu_device_relator(self, group_id, device_id) -> GroupGPUDeviceRelator:
        """
        This function is required by the SDK architecture.
        It is not intended for use by the SDK client.
        group_id: int 
            Identifier of the related group
        device_id: int 
            Identifier of the related device
        """
        self.logger.debug('entering get_group_gpu_device_relator(%s, %s)', str(group_id), str(device_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        try:
            obj0 = self.get_group(group_id)
            obj1 = self.get_gpu_device_status(device_id)
            composite = GroupGPUDeviceRelator()
            composite.DeviceStatus = obj1
            composite.Group = obj0
            return composite
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_gpu_device_relator raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_gpu_device_relator raising ' + str(ex))
            raise exceptions.LiqidError('get_group_gpu_device_relator caught ' + str(ex))

    def get_group_memory_device_relator(self, group_id, device_id) -> GroupMemoryDeviceRelator:
        """
        This function is required by the SDK architecture.
        It is not intended for use by the SDK client.
        group_id: int 
            Identifier of the related group
        device_id: int 
            Identifier of the related device
        """
        self.logger.debug('entering get_group_memory_device_relator(%s, %s)', str(group_id), str(device_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        try:
            obj0 = self.get_group(group_id)
            obj1 = self.get_memory_device_status(device_id)
            composite = GroupMemoryDeviceRelator()
            composite.DeviceStatus = obj1
            composite.Group = obj0
            return composite
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_memory_device_relator raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_memory_device_relator raising ' + str(ex))
            raise exceptions.LiqidError('get_group_memory_device_relator caught ' + str(ex))

    def get_group_network_device_relator(self, group_id, device_id) -> GroupNetworkDeviceRelator:
        """
        This function is required by the SDK architecture.
        It is not intended for use by the SDK client.
        group_id: int 
            Identifier of the related group
        device_id: int 
            Identifier of the related device
        """
        self.logger.debug('entering get_group_network_device_relator(%s, %s)', str(group_id), str(device_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        try:
            obj0 = self.get_group(group_id)
            obj1 = self.get_network_device_status(device_id)
            composite = GroupNetworkDeviceRelator()
            composite.DeviceStatus = obj1
            composite.Group = obj0
            return composite
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_network_device_relator raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_network_device_relator raising ' + str(ex))
            raise exceptions.LiqidError('get_group_network_device_relator caught ' + str(ex))

    def get_group_storage_device_relator(self, group_id, device_id) -> GroupStorageDeviceRelator:
        """
        This function is required by the SDK architecture.
        It is not intended for use by the SDK client.
        group_id: int 
            Identifier of the related group
        device_id: int 
            Identifier of the related device
        """
        self.logger.debug('entering get_group_storage_device_relator(%s, %s)', str(group_id), str(device_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('device_id', device_id, 'int', False)
        try:
            obj0 = self.get_group(group_id)
            obj1 = self.get_storage_device_status(device_id)
            composite = GroupStorageDeviceRelator()
            composite.DeviceStatus = obj1
            composite.Group = obj0
            return composite
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_storage_device_relator raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_storage_device_relator raising ' + str(ex))
            raise exceptions.LiqidError('get_group_storage_device_relator caught ' + str(ex))

    def get_group_details(self, group_id) -> GroupDetails:
        """
        Retrieves details regarding a particular configured group
        group_id: int 
            Unique identifier of the group of interest
        """
        self.logger.debug('entering get_group_details(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        try:
            path = 'group/details'
            path += '/' + str(group_id)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupDetails().from_dictionary(data[0])
            self.logger.debug('get_group_details returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_details raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_details raising ' + str(ex))
            raise exceptions.LiqidError('get_group_details caught ' + str(ex))

    def get_group_id_by_name(self, group_name) -> int:
        """
        Retrieves the unique identifier of a configured group given its name
        group_name: str 
            Name of the group of interest
        """
        self.logger.debug('entering get_group_id_by_name(%s)', str(group_name))
        self.check_parameter_type('group_name', group_name, 'str', False)

        try:
            path = 'group/mapper'
            param_list = []
            param_list.append('group-name=' + str(group_name))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            self.logger.debug('get_group_id_by_name returning %s', str(data[0]))
            return data[0]
        except exceptions.LiqidError as ex:
            self.logger.debug('get_group_id_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_group_id_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_group_id_by_name caught ' + str(ex))

    def get_machine(self, machine_id) -> Machine:
        """
        Retrieves information about a configured machine given the machine identifier
        machine_id: int 
            Unique machine identifier
        """
        self.logger.debug('entering get_machine(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'machine'
            param_list = []
            param_list.append('mach_id=' + str(machine_id))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('get_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_machine raising ' + str(ex))
            raise exceptions.LiqidError('get_machine caught ' + str(ex))

    def get_machines(self) -> list:
        """
        Retrieves information about all the configured machines
        """
        self.logger.debug('entering get_machines()')

        try:
            path = 'machine'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(Machine().from_dictionary(item))
            self.logger.debug('get_machines returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_machines raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_machines raising ' + str(ex))
            raise exceptions.LiqidError('get_machines caught ' + str(ex))

    def get_machines_at_coordinates(self, rack_id, shelf_id, node_id) -> list:
        """
        Retrieves information about all the configured machines
        rack_id: int 
            Rack component of Liqid Coordinates of interest
        shelf_id: int 
            Shelf component of Liqid Coordinates of interest
        node_id: int 
            Node component of Liqid Coordinates of interest
        """
        self.logger.debug('entering get_machines_at_coordinates(%s, %s, %s)', str(rack_id), str(shelf_id), str(node_id))
        self.check_parameter_type('rack_id', rack_id, 'int', False)
        self.check_parameter_type('shelf_id', shelf_id, 'int', False)
        self.check_parameter_type('node_id', node_id, 'int', False)

        try:
            path = 'machine'
            path += '/' + str(rack_id)
            path += '/' + str(shelf_id)
            path += '/' + str(node_id)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(Machine().from_dictionary(item))
            self.logger.debug('get_machines_at_coordinates returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_machines_at_coordinates raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_machines_at_coordinates raising ' + str(ex))
            raise exceptions.LiqidError('get_machines_at_coordinates caught ' + str(ex))

    def get_machines_by_group_id(self, group_id) -> list:
        """
        Retrieves information about all the configured machines in a particular group
        group_id: int 
            Unique group identifier
        """
        self.logger.debug('entering get_machines_by_group_id(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        try:
            path = 'machine'
            param_list = []
            param_list.append('grp_id=' + str(group_id))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(Machine().from_dictionary(item))
            self.logger.debug('get_machines_by_group_id returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_machines_by_group_id raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_machines_by_group_id raising ' + str(ex))
            raise exceptions.LiqidError('get_machines_by_group_id caught ' + str(ex))

    def get_machine_by_name(self, machine_name) -> Machine:
        """
        Retrieves information about a configured machine given the machine name
        machine_name: str 
            Unique machine name
        """
        self.logger.debug('entering get_machine_by_name(%s)', str(machine_name))
        self.check_parameter_type('machine_name', machine_name, 'str', False)

        try:
            path = 'machine'
            param_list = []
            param_list.append('mach_name=' + str(machine_name))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('get_machine_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_machine_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_machine_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_machine_by_name caught ' + str(ex))

    def get_machine_details(self, machine_id) -> MachineDetails:
        """
        Retrieves details for a particular machine
        machine_id: int 
            Identifier for the machine
        """
        self.logger.debug('entering get_machine_details(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'machine/details'
            path += '/' + str(machine_id)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineDetails().from_dictionary(data[0])
            self.logger.debug('get_machine_details returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_machine_details raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_machine_details raising ' + str(ex))
            raise exceptions.LiqidError('get_machine_details caught ' + str(ex))

    def get_managed_entities(self) -> list:
        """
        Reports all the managed entity entries used for discovering PCI devices
        """
        self.logger.debug('entering get_managed_entities()')

        try:
            path = 'manager/device'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(ManagedEntity().from_dictionary(item))
            self.logger.debug('get_managed_entities returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_managed_entities raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_managed_entities raising ' + str(ex))
            raise exceptions.LiqidError('get_managed_entities caught ' + str(ex))

    def get_managed_entity(self, pci_vendor_id, pci_device_id) -> ManagedEntity:
        """
        Retrieves a particular managed entity
        pci_vendor_id: str 
            PCI vendor id for the managed entity
        pci_device_id: str 
            PCI device id for the managed entity
        """
        self.logger.debug('entering get_managed_entity(%s, %s)', str(pci_vendor_id), str(pci_device_id))
        self.check_parameter_type('pci_vendor_id', pci_vendor_id, 'str', False)
        self.check_parameter_type('pci_device_id', pci_device_id, 'str', False)

        for item in self.get_managed_entities():
            if pci_vendor_id == item.get_pci_vendor_id() and pci_device_id == item.get_pci_device_id():
                self.logger.debug("get_managed_entity returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_managed_entity raising " + str(ex))
        raise ex

    def get_memory_device_info(self) -> list:
        """
        Retrieves additional information for all known Memory devices
        """
        self.logger.debug('entering get_memory_device_info()')

        try:
            path = 'device/info/mem'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(MemoryDeviceInfo().from_dictionary(item))
            self.logger.debug('get_memory_device_info returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_memory_device_info raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_memory_device_info raising ' + str(ex))
            raise exceptions.LiqidError('get_memory_device_info caught ' + str(ex))

    def get_memory_device_info_by_name(self, device_name) -> MemoryDeviceInfo:
        """
        Retrieves additional information for a particular Memory device
        device_name: str 
            Name of the device in question
        """
        self.logger.debug('entering get_memory_device_info_by_name(%s)', str(device_name))
        self.check_parameter_type('device_name', device_name, 'str', False)

        try:
            path = 'device/info/mem'
            path += '/' + str(device_name)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MemoryDeviceInfo().from_dictionary(data[0])
            self.logger.debug('get_memory_device_info_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_memory_device_info_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_memory_device_info_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_memory_device_info_by_name caught ' + str(ex))

    def get_memory_device_status(self, device_id) -> MemoryDeviceStatus:
        """
        Retrieves status for a particular memory device
        device_id: int 
            Identifier of the device in question
        """
        self.logger.debug('entering get_memory_device_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        for item in self.get_memory_devices_status():
            if device_id == item.get_device_id():
                self.logger.debug("get_memory_device_status returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_memory_device_status raising " + str(ex))
        raise ex

    def get_memory_devices_status(self) -> list:
        """
        Retrieves status for memory devices
        """
        self.logger.debug('entering get_memory_devices_status()')

        try:
            path = 'status/mem'
            param_list = []
            param_list.append('filter=' + str(False))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(MemoryDeviceStatus().from_dictionary(item))
            self.logger.debug('get_memory_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_memory_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_memory_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_memory_devices_status caught ' + str(ex))

    def get_message_digest_labels(self) -> list:
        """
        Retrieves all existing message digest labels.
        """
        self.logger.debug('entering get_message_digest_labels()')

        try:
            path = 'digest'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            self.logger.debug('get_message_digest_labels returning %s', str(data))
            return data
        except exceptions.LiqidError as ex:
            self.logger.debug('get_message_digest_labels raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_message_digest_labels raising ' + str(ex))
            raise exceptions.LiqidError('get_message_digest_labels caught ' + str(ex))

    def get_network_device_info(self) -> list:
        """
        Retrieves additional information for all known Network devices
        """
        self.logger.debug('entering get_network_device_info()')

        try:
            path = 'device/info/link'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NetworkDeviceInfo().from_dictionary(item))
            self.logger.debug('get_network_device_info returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_network_device_info raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_network_device_info raising ' + str(ex))
            raise exceptions.LiqidError('get_network_device_info caught ' + str(ex))

    def get_network_device_info_by_name(self, device_name) -> NetworkDeviceInfo:
        """
        Retrieves additional information for a particular Network device
        device_name: str 
            Name of the device in question
        """
        self.logger.debug('entering get_network_device_info_by_name(%s)', str(device_name))
        self.check_parameter_type('device_name', device_name, 'str', False)

        try:
            path = 'device/info/link'
            path += '/' + str(device_name)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = NetworkDeviceInfo().from_dictionary(data[0])
            self.logger.debug('get_network_device_info_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_network_device_info_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_network_device_info_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_network_device_info_by_name caught ' + str(ex))

    def get_network_device_status(self, device_id) -> NetworkDeviceStatus:
        """
        Retrieves status for a particular network device
        device_id: int 
            Identifier of the device in question
        """
        self.logger.debug('entering get_network_device_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        for item in self.get_network_devices_status():
            if device_id == item.get_device_id():
                self.logger.debug("get_network_device_status returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_network_device_status raising " + str(ex))
        raise ex

    def get_network_devices_status(self) -> list:
        """
        Retrieves status for network devices
        """
        self.logger.debug('entering get_network_devices_status()')

        try:
            path = 'status/network'
            param_list = []
            param_list.append('filter=' + str(False))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NetworkDeviceStatus().from_dictionary(item))
            self.logger.debug('get_network_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_network_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_network_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_network_devices_status caught ' + str(ex))

    def get_network_ipmi_managed_cpu(self, cpu_name) -> NetworkManagedCPU:
        """
        Retrieves a particular network-IPMI-managed CPU given its name
        cpu_name: str 
            Name of the CPU in question
        """
        self.logger.debug('entering get_network_ipmi_managed_cpu(%s)', str(cpu_name))
        self.check_parameter_type('cpu_name', cpu_name, 'str', False)

        for item in self.get_network_ipmi_managed_cpus():
            if cpu_name == item.get_cpu_name():
                self.logger.debug("get_network_ipmi_managed_cpu returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_network_ipmi_managed_cpu raising " + str(ex))
        raise ex

    def get_network_ipmi_managed_cpus(self) -> list:
        """
        Retrieves a list of management entries for IPMI-via-network CPUs
        """
        self.logger.debug('entering get_network_ipmi_managed_cpus()')

        try:
            path = 'manager/network/ipmi/cpu'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NetworkManagedCPU().from_dictionary(item))
            self.logger.debug('get_network_ipmi_managed_cpus returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_network_ipmi_managed_cpus raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_network_ipmi_managed_cpus raising ' + str(ex))
            raise exceptions.LiqidError('get_network_ipmi_managed_cpus caught ' + str(ex))

    def get_network_ipmi_managed_enclosure(self, enclosure_name) -> NetworkManagedEnclosure:
        """
        Retrieves a particular network-IPMI-managed enclosure given its name
        enclosure_name: int 
            Name of the enclosure in question
        """
        self.logger.debug('entering get_network_ipmi_managed_enclosure(%s)', str(enclosure_name))
        self.check_parameter_type('enclosure_name', enclosure_name, 'int', False)

        for item in self.get_network_ipmi_managed_enclosures():
            if enclosure_name == item.get_enclosure_name():
                self.logger.debug("get_network_ipmi_managed_enclosure returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_network_ipmi_managed_enclosure raising " + str(ex))
        raise ex

    def get_network_ipmi_managed_enclosures(self) -> list:
        """
        Retrieves a list of management entries for IPMI-via-network enclosures
        """
        self.logger.debug('entering get_network_ipmi_managed_enclosures()')

        try:
            path = 'manager/network/ipmi/enclosure'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NetworkManagedEnclosure().from_dictionary(item))
            self.logger.debug('get_network_ipmi_managed_enclosures returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_network_ipmi_managed_enclosures raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_network_ipmi_managed_enclosures raising ' + str(ex))
            raise exceptions.LiqidError('get_network_ipmi_managed_enclosures caught ' + str(ex))

    def get_next_group_id(self) -> int:
        """
        Retrieves the next sequential unused group identifier
        Deprecated:
        Use of this function in conjunction with create_group_with_id opens a potential race condition.
        This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.
        A future version of the Director will provide a means of creating a group whereby the group id is internally generated.
        For this reason, SDK clients are encouraged to use the create_group function which wraps the Get/Create mechanism in one SDK function call.
        For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of create_group_with_id and get_next_group_id.
        """
        self.logger.debug('entering get_next_group_id()')

        try:
            path = 'group/nextid'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            self.logger.debug('get_next_group_id returning %s', str(data[0]))
            return data[0]
        except exceptions.LiqidError as ex:
            self.logger.debug('get_next_group_id raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_next_group_id raising ' + str(ex))
            raise exceptions.LiqidError('get_next_group_id caught ' + str(ex))

    def get_next_machine_id(self) -> int:
        """
        Retrieves the next sequential unused machine identifier
        Deprecated:
        Use of this function in conjunction with create_machine_with_id opens a potential race condition.
        This problem exists internally in the Director, and is reflected both in the REST API and in this SDK.
        A future version of the Director will provide a means of creating a machine whereby the machine id is internally generated.
        For this reason, SDK clients are encouraged to use the create_machine function which wraps the Get/Create mechanism in one SDK function call.
        For the time being, this does not correct the race condition; however, it protects SDK clients from the eventual removal of create_machine_with_id and get_next_machine_id.
        """
        self.logger.debug('entering get_next_machine_id()')

        try:
            path = 'machine/nextid'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            self.logger.debug('get_next_machine_id returning %s', str(data[0]))
            return int(data[0])
        except exceptions.LiqidError as ex:
            self.logger.debug('get_next_machine_id raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_next_machine_id raising ' + str(ex))
            raise exceptions.LiqidError('get_next_machine_id caught ' + str(ex))

    def get_nodes_status(self) -> list:
        """
        Retrieves status for the nodes in the configuration
        """
        self.logger.debug('entering get_nodes_status()')

        try:
            path = 'node/status'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(NodeStatus().from_dictionary(item))
            self.logger.debug('get_nodes_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_nodes_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_nodes_status raising ' + str(ex))
            raise exceptions.LiqidError('get_nodes_status caught ' + str(ex))

    def get_secure_erase_status(self, device_id) -> AsynchronousStatus:
        """
        Retrieves the status of an asynchronous secure erase operation
        device_id: int 
            Device identifier returned by secure_erase
        """
        self.logger.debug('entering get_secure_erase_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        try:
            device_id_translated = '0x{:08x}'.format(device_id)
            path = 'device/erase/'
            path += '/' + str(device_id_translated)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = AsynchronousStatus().from_dictionary(data[0])
            self.logger.debug('get_secure_erase_status returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_secure_erase_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_secure_erase_status raising ' + str(ex))
            raise exceptions.LiqidError('get_secure_erase_status caught ' + str(ex))

    def get_ssh_status(self) -> SSHStatus:
        """
        Retrieves the current state of SSH
        """
        self.logger.debug('entering get_ssh_status()')

        try:
            path = 'ssh/enable'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = SSHStatus().from_dictionary(data[0])
            self.logger.debug('get_ssh_status returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_ssh_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_ssh_status raising ' + str(ex))
            raise exceptions.LiqidError('get_ssh_status caught ' + str(ex))

    def get_storage_device_status(self, device_id) -> StorageDeviceStatus:
        """
        Retrieves status for a particular storage device
        device_id: int 
            Identifier of the device in question
        """
        self.logger.debug('entering get_storage_device_status(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        for item in self.get_storage_devices_status():
            if device_id == item.get_device_id():
                self.logger.debug("get_storage_device_status returning " + str(item))
                return item
        ex = exceptions.LiqidError("Entity not found:")
        self.logger.debug("get_storage_device_status raising " + str(ex))
        raise ex

    def get_storage_devices_status(self) -> list:
        """
        Retrieves status for all storage devices
        """
        self.logger.debug('entering get_storage_devices_status()')

        try:
            path = 'status/storage'
            param_list = []
            param_list.append('filter=' + str(False))
            path += '?' + '&'.join(param_list)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(StorageDeviceStatus().from_dictionary(item))
            self.logger.debug('get_storage_devices_status returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_storage_devices_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_storage_devices_status raising ' + str(ex))
            raise exceptions.LiqidError('get_storage_devices_status caught ' + str(ex))

    def get_storage_device_info(self) -> list:
        """
        Retrieves additional information for all known Storage devices
        """
        self.logger.debug('entering get_storage_device_info()')

        try:
            path = 'device/info/ssd'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(StorageDeviceInfo().from_dictionary(item))
            self.logger.debug('get_storage_device_info returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_storage_device_info raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_storage_device_info raising ' + str(ex))
            raise exceptions.LiqidError('get_storage_device_info caught ' + str(ex))

    def get_storage_device_info_by_name(self, device_name) -> StorageDeviceInfo:
        """
        Retrieves additional information for a particular Storage device
        device_name: str 
            Name of the device in question
        """
        self.logger.debug('entering get_storage_device_info_by_name(%s)', str(device_name))
        self.check_parameter_type('device_name', device_name, 'str', False)

        try:
            path = 'device/info/ssd'
            path += '/' + str(device_name)
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = StorageDeviceInfo().from_dictionary(data[0])
            self.logger.debug('get_storage_device_info_by_name returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_storage_device_info_by_name raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_storage_device_info_by_name raising ' + str(ex))
            raise exceptions.LiqidError('get_storage_device_info_by_name caught ' + str(ex))

    def get_versions(self) -> list:
        """
        Retrieves information describing the various software components which comprise the Liqid Director.
        """
        self.logger.debug('entering get_versions()')

        try:
            path = 'version'
            response, resp_body = self.send(base_client.HttpGet, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, True)
            data = wrapper['response']['data']
            result = []
            for item in data:
                if item is not None:
                    result.append(VersionInfo().from_dictionary(item))
            self.logger.debug('get_versions returning %s', self._list_str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('get_versions raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('get_versions raising ' + str(ex))
            raise exceptions.LiqidError('get_versions caught ' + str(ex))

    def group_pool_done(self, group_id) -> GroupPool:
        """
        Completes a pending group pool edit operation.
        All pending device attachments or detachments are finalized.
        group_id: int 
            Identifier of the group for which edit mode is to be completed
        """
        self.logger.debug('entering group_pool_done(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = GroupPool()
            obj.GroupId = group_id
            obj.Coordinates.Rack = self._get_preset_rack()
            obj.Coordinates.Shelf = self._get_preset_shelf()
            obj.Coordinates.Node = self._get_preset_node()
            obj.FabricId = self._get_preset_fabric_id()

            path = 'pool/done'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupPool().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('group_pool_done raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('group_pool_done raising ' + str(ex))
            raise exceptions.LiqidError('group_pool_done caught ' + str(ex))

    def group_pool_edit(self, group_id) -> GroupPool:
        """
        Establishes edit mode for a particular group pool.
        Must be invoked before adding devices to or removing devices from a group free pool.
        group_id: int 
            Identifier of the group for which edit mode is to be established
        """
        self.logger.debug('entering group_pool_edit(%s)', str(group_id))
        self.check_parameter_type('group_id', group_id, 'int', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = GroupPool()
            obj.GroupId = group_id
            obj.Coordinates.Rack = self._get_preset_rack()
            obj.Coordinates.Shelf = self._get_preset_shelf()
            obj.Coordinates.Node = self._get_preset_node()
            obj.FabricId = self._get_preset_fabric_id()

            path = 'pool/edit'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupPool().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('group_pool_edit raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('group_pool_edit raising ' + str(ex))
            raise exceptions.LiqidError('group_pool_edit caught ' + str(ex))

    def reboot_node(self, group_id, machine_id) -> Machine:
        """
        Reboots a particular node
        group_id: int 
            Unique identifier of the group which contains the node of interest
        machine_id: int 
            Unique machine identifier containing the node of interest
        """
        self.logger.debug('entering reboot_node(%s, %s)', str(group_id), str(machine_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'power/reboot'
            path += '/' + str(group_id)
            path += '/' + str(machine_id)
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('reboot_node returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('reboot_node raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('reboot_node raising ' + str(ex))
            raise exceptions.LiqidError('reboot_node caught ' + str(ex))

    def remove_compute_device_from_group(self, device_id, group_id) -> GroupComputeDeviceRelator:
        """
        Moves a device from the group free pool to the system free pool
        device_id: int 
            Identifier of the device to be removed from the group.
        group_id: int 
            Identifier of the group from which the device is to be removed.
        """
        self.logger.debug('entering remove_compute_device_from_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_compute_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupComputeDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/compute'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupComputeDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_compute_device_from_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_compute_device_from_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_compute_device_from_group raising ' + str(ex))
            raise exceptions.LiqidError('remove_compute_device_from_group caught ' + str(ex))

    def remove_compute_device_from_machine(self, device_id, group_id, machine_id) -> MachineComputeDeviceRelator:
        """
        Detaches a particular device from a machine, returning it to the group free pool
        device_id: int 
            Unique identifier of the device to be removed
        group_id: int 
            Unique identifier of the group to which the containing machine belongs
        machine_id: int 
            Unique identifier of the machine from which the device is to be removed
        """
        self.logger.debug('entering remove_compute_device_from_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_compute_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineComputeDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/compute'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineComputeDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_compute_device_from_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_compute_device_from_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_compute_device_from_machine raising ' + str(ex))
            raise exceptions.LiqidError('remove_compute_device_from_machine caught ' + str(ex))

    def remove_fpga_device_from_group(self, device_id, group_id) -> GroupFPGADeviceRelator:
        """
        Moves a device from the group free pool to the system free pool
        device_id: int 
            Identifier of the device to be removed from the group.
        group_id: int 
            Identifier of the group from which the device is to be removed.
        """
        self.logger.debug('entering remove_fpga_device_from_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_fpga_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupFPGADeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/fpga'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupFPGADeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_fpga_device_from_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_fpga_device_from_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_fpga_device_from_group raising ' + str(ex))
            raise exceptions.LiqidError('remove_fpga_device_from_group caught ' + str(ex))

    def remove_fpga_device_from_machine(self, device_id, group_id, machine_id) -> MachineFPGADeviceRelator:
        """
        Detaches a particular device from a machine, returning it to the group free pool
        device_id: int 
            Unique identifier of the device to be removed
        group_id: int 
            Unique identifier of the group to which the containing machine belongs
        machine_id: int 
            Unique identifier of the machine from which the device is to be removed
        """
        self.logger.debug('entering remove_fpga_device_from_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_fpga_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineFPGADeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/fpga'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineFPGADeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_fpga_device_from_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_fpga_device_from_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_fpga_device_from_machine raising ' + str(ex))
            raise exceptions.LiqidError('remove_fpga_device_from_machine caught ' + str(ex))

    def remove_gpu_device_from_group(self, device_id, group_id) -> GroupGPUDeviceRelator:
        """
        Moves a device from the group free pool to the system free pool
        device_id: int 
            Identifier of the device to be removed from the group.
        group_id: int 
            Identifier of the group from which the device is to be removed.
        """
        self.logger.debug('entering remove_gpu_device_from_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_gpu_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupGPUDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/gpu'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupGPUDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_gpu_device_from_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_gpu_device_from_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_gpu_device_from_group raising ' + str(ex))
            raise exceptions.LiqidError('remove_gpu_device_from_group caught ' + str(ex))

    def remove_gpu_device_from_machine(self, device_id, group_id, machine_id) -> MachineGPUDeviceRelator:
        """
        Detaches a particular device from a machine, returning it to the group free pool
        device_id: int 
            Unique identifier of the device to be removed
        group_id: int 
            Unique identifier of the group to which the containing machine belongs
        machine_id: int 
            Unique identifier of the machine from which the device is to be removed
        """
        self.logger.debug('entering remove_gpu_device_from_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_gpu_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineGPUDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/gpu'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineGPUDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_gpu_device_from_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_gpu_device_from_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_gpu_device_from_machine raising ' + str(ex))
            raise exceptions.LiqidError('remove_gpu_device_from_machine caught ' + str(ex))

    def remove_memory_device_from_group(self, device_id, group_id) -> GroupMemoryDeviceRelator:
        """
        Moves a device from the group free pool to the system free pool
        device_id: int 
            Identifier of the device to be removed from the group.
        group_id: int 
            Identifier of the group from which the device is to be removed.
        """
        self.logger.debug('entering remove_memory_device_from_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_memory_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupMemoryDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/mem'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupMemoryDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_memory_device_from_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_memory_device_from_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_memory_device_from_group raising ' + str(ex))
            raise exceptions.LiqidError('remove_memory_device_from_group caught ' + str(ex))

    def remove_memory_device_from_machine(self, device_id, group_id, machine_id) -> MachineMemoryDeviceRelator:
        """
        Detaches a particular device from a machine, returning it to the group free pool
        device_id: int 
            Unique identifier of the device to be removed
        group_id: int 
            Unique identifier of the group to which the containing machine belongs
        machine_id: int 
            Unique identifier of the machine from which the device is to be removed
        """
        self.logger.debug('entering remove_memory_device_from_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_memory_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineMemoryDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/memory'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineMemoryDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_memory_device_from_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_memory_device_from_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_memory_device_from_machine raising ' + str(ex))
            raise exceptions.LiqidError('remove_memory_device_from_machine caught ' + str(ex))

    def remove_network_device_from_group(self, device_id, group_id) -> GroupNetworkDeviceRelator:
        """
        Moves a device from the group free pool to the system free pool
        device_id: int 
            Identifier of the device to be removed from the group.
        group_id: int 
            Identifier of the group from which the device is to be removed.
        """
        self.logger.debug('entering remove_network_device_from_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_network_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupNetworkDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/network'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupNetworkDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_network_device_from_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_network_device_from_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_network_device_from_group raising ' + str(ex))
            raise exceptions.LiqidError('remove_network_device_from_group caught ' + str(ex))

    def remove_network_device_from_machine(self, device_id, group_id, machine_id) -> MachineNetworkDeviceRelator:
        """
        Detaches a particular device from a machine, returning it to the group free pool
        device_id: int 
            Unique identifier of the device to be removed
        group_id: int 
            Unique identifier of the group to which the containing machine belongs
        machine_id: int 
            Unique identifier of the machine from which the device is to be removed
        """
        self.logger.debug('entering remove_network_device_from_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_network_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineNetworkDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/network'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineNetworkDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_network_device_from_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_network_device_from_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_network_device_from_machine raising ' + str(ex))
            raise exceptions.LiqidError('remove_network_device_from_machine caught ' + str(ex))

    def remove_storage_device_from_group(self, device_id, group_id) -> GroupStorageDeviceRelator:
        """
        Moves a device from the group free pool to the system free pool
        device_id: int 
            Identifier of the device to be removed from the group.
        group_id: int 
            Identifier of the group from which the device is to be removed.
        """
        self.logger.debug('entering remove_storage_device_from_group(%s, %s)', str(device_id), str(group_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        try:
            obj0 = self.get_storage_device_status(device_id)
            obj1 = self.get_group(group_id)
            composite = GroupStorageDeviceRelator()
            composite.DeviceStatus = obj0
            composite.Group = obj1
            path = 'predevice/storage'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = GroupStorageDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_storage_device_from_group returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_storage_device_from_group raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_storage_device_from_group raising ' + str(ex))
            raise exceptions.LiqidError('remove_storage_device_from_group caught ' + str(ex))

    def remove_storage_device_from_machine(self, device_id, group_id, machine_id) -> MachineStorageDeviceRelator:
        """
        Detaches a particular device from a machine, returning it to the group free pool
        device_id: int 
            Unique identifier of the device to be removed
        group_id: int 
            Unique identifier of the group to which the containing machine belongs
        machine_id: int 
            Unique identifier of the machine from which the device is to be removed
        """
        self.logger.debug('entering remove_storage_device_from_machine(%s, %s, %s)', str(device_id), str(group_id), str(machine_id))
        self.check_parameter_type('device_id', device_id, 'int', False)
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        try:
            obj0 = self.get_group_storage_device_relator(group_id, device_id)
            obj1 = self.get_machine(machine_id)
            composite = MachineStorageDeviceRelator()
            composite.GroupDeviceRelator = obj0
            composite.Machine = obj1
            path = 'relate/storage'
            content_type = 'application/json'
            req_body = json.dumps(composite.to_dictionary())
            headers = {'content-type': content_type}
            response, resp_body = self.send(base_client.HttpDelete, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = MachineStorageDeviceRelator().from_dictionary(data[0])
            self.logger.debug('remove_storage_device_from_machine returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('remove_storage_device_from_machine raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('remove_storage_device_from_machine raising ' + str(ex))
            raise exceptions.LiqidError('remove_storage_device_from_machine caught ' + str(ex))

    def reprogram_fabric(self, machine_id) -> Machine:
        """
        Reprogram the fabric. This will result in devices associated with a machine being electrically connected to the machine.
        This step MUST be done in order for a device to be added to a machine.
        machine_id: int 
            Unique id of the machine for which fabric reprogramming is to be accomplished.
        """
        self.logger.debug('entering reprogram_fabric(%s)', str(machine_id))
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            obj = self.get_machine(machine_id)
            path = 'fabric/reprogram'
            req_body = json.dumps(obj.to_dictionary())
            headers = {'content-type': 'application/json'}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('reprogram_fabric returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('reprogram_fabric raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('reprogram_fabric raising ' + str(ex))
            raise exceptions.LiqidError('reprogram_fabric caught ' + str(ex))

    def reset_state(self) -> Timestamp:
        """
        Disconnects the device connections to a CPU.
        Removes LiqOS state information related to machines, groups, and devices.
        Forces a Liqid rediscovery of the fabric.
        """
        self.logger.debug('entering reset_state()')

        try:
            path = 'system/state/reset'
            response, resp_body = self.send(base_client.HttpPost, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Timestamp().from_dictionary(data[0])
            self.logger.debug('reset_state returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('reset_state raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('reset_state raising ' + str(ex))
            raise exceptions.LiqidError('reset_state caught ' + str(ex))

    def restart_fabric(self) -> Timestamp:
        """
        Restarts the entire fabric
        """
        self.logger.debug('entering restart_fabric()')

        try:
            path = 'system/restart/fabric'
            response, resp_body = self.send(base_client.HttpPost, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Timestamp().from_dictionary(data[0])
            self.logger.debug('restart_fabric returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('restart_fabric raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('restart_fabric raising ' + str(ex))
            raise exceptions.LiqidError('restart_fabric caught ' + str(ex))

    def restart_hierarchy(self) -> Timestamp:
        """
        Initiates a discovery of the fabric hierarchy
        """
        self.logger.debug('entering restart_hierarchy()')

        try:
            path = 'system/restart/hierarchy'
            response, resp_body = self.send(base_client.HttpPost, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Timestamp().from_dictionary(data[0])
            self.logger.debug('restart_hierarchy returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('restart_hierarchy raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('restart_hierarchy raising ' + str(ex))
            raise exceptions.LiqidError('restart_hierarchy caught ' + str(ex))

    def restart_node(self, group_id, machine_id) -> Machine:
        """
        Restarts a particular node
        group_id: int 
            Unique identifier of the group which contains the node of interest
        machine_id: int 
            Unique machine identifier containing the node of interest
        """
        self.logger.debug('entering restart_node(%s, %s)', str(group_id), str(machine_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'power/restart'
            path += '/' + str(group_id)
            path += '/' + str(machine_id)
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('restart_node returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('restart_node raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('restart_node raising ' + str(ex))
            raise exceptions.LiqidError('restart_node caught ' + str(ex))

    def restart_switch(self) -> Timestamp:
        """
        Restarts the switch at the default coordinates
        """
        self.logger.debug('entering restart_switch()')

        try:
            path = 'system/restart/switch'
            response, resp_body = self.send(base_client.HttpPost, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Timestamp().from_dictionary(data[0])
            self.logger.debug('restart_switch returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('restart_switch raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('restart_switch raising ' + str(ex))
            raise exceptions.LiqidError('restart_switch caught ' + str(ex))

    def secure_erase(self, device_id) -> AsynchronousInfo:
        """
        Securely erases a particular storage device.
        This is an asynchronous operation - the function returns before the process is complete.
        device_id: int 
            The identifier of the drive to be securely erased.
            This is the hexadecimal identifier prefixed with '0x'
        """
        self.logger.debug('entering secure_erase(%s)', str(device_id))
        self.check_parameter_type('device_id', device_id, 'int', False)

        try:
            device_id_translated = '0x{:08x}'.format(device_id)
            path = 'device'
            path += '/' + str(device_id_translated)
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = AsynchronousInfo().from_dictionary(data[0])
            self.logger.debug('secure_erase returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('secure_erase raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('secure_erase raising ' + str(ex))
            raise exceptions.LiqidError('secure_erase caught ' + str(ex))

    def set_default_coordinates(self, rack, shelf, node) -> Coordinates:
        """
        Sets the default coordinates used for subsequent operations initiated via the SDK.
        Specifying any set of values apart from what is available from get_available_coordinates produces undefined behavior.
        rack: int  (optional - can be None)
            Rack component of the liqid coordinates for the REST API endpoint
            Should always be zero.
        shelf: int  (optional - can be None)
            Shelf component of the liqid coordinates for the REST API endpoint
            Should always be zero.
        node: int 
            Node component of the liqid coordinates for the REST API endpoint
        """
        self.logger.debug('entering set_default_coordinates(%s, %s, %s)', str(rack), str(shelf), str(node))
        self.check_parameter_type('rack', rack, 'int', True)
        self.check_parameter_type('shelf', shelf, 'int', True)
        self.check_parameter_type('node', node, 'int', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = Coordinates()
            if rack is not None:
                obj.Rack = rack
            if shelf is not None:
                obj.Shelf = shelf
            obj.Node = node

            path = 'coordinates'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Coordinates().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('set_default_coordinates raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('set_default_coordinates raising ' + str(ex))
            raise exceptions.LiqidError('set_default_coordinates caught ' + str(ex))

    def set_ssh_status(self, active, enabled) -> SSHStatus:
        """
        Sets the state of SSH
        active: bool 
            Indicates whether SSH should be active
        enabled: bool 
            Indicates whether SSH should be enabled
        """
        self.logger.debug('entering set_ssh_status(%s, %s)', str(active), str(enabled))
        self.check_parameter_type('active', active, 'bool', False)
        self.check_parameter_type('enabled', enabled, 'bool', False)

        try:
            headers = {'content-type': 'application/json'}
            obj = SSHStatus()
            obj.Active = active
            obj.Enabled = enabled

            path = 'ssh/enable'
            req_body = json.dumps(obj.to_dictionary())
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = SSHStatus().from_dictionary(data[0])
            self.logger.debug('result returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('set_ssh_status raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('set_ssh_status raising ' + str(ex))
            raise exceptions.LiqidError('set_ssh_status caught ' + str(ex))

    def shutdown(self) -> Coordinates:
        """
        Gracefully powers down the director at the default coordinates
        """
        self.logger.debug('entering shutdown()')

        try:
            path = 'system/shutdown'
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Coordinates().from_dictionary(data[0])
            self.logger.debug('shutdown returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('shutdown raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('shutdown raising ' + str(ex))
            raise exceptions.LiqidError('shutdown caught ' + str(ex))

    def shutdown_at(self, rack_id, shelf_id, node_id) -> Coordinates:
        """
        Shuts down the director at the given coordinates
        rack_id: int 
            The rack id component of the Liqid coordinates
        shelf_id: int 
            The shelf id component of the Liqid coordinates
        node_id: int 
            The node id component of the Liqid coordinates
        """
        self.logger.debug('entering shutdown_at(%s, %s, %s)', str(rack_id), str(shelf_id), str(node_id))
        self.check_parameter_type('rack_id', rack_id, 'int', False)
        self.check_parameter_type('shelf_id', shelf_id, 'int', False)
        self.check_parameter_type('node_id', node_id, 'int', False)

        try:
            path = 'system/shutdown'
            path += '/' + str(rack_id)
            path += '/' + str(shelf_id)
            path += '/' + str(node_id)
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Coordinates().from_dictionary(data[0])
            self.logger.debug('shutdown_at returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('shutdown_at raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('shutdown_at raising ' + str(ex))
            raise exceptions.LiqidError('shutdown_at caught ' + str(ex))

    def shutdown_node(self, group_id, machine_id) -> Machine:
        """
        Shuts down a particular node
        group_id: int 
            Unique identifier of the group which contains the node of interest
        machine_id: int 
            Unique machine identifier containing the node of interest
        """
        self.logger.debug('entering shutdown_node(%s, %s)', str(group_id), str(machine_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'power/shutdown'
            path += '/' + str(group_id)
            path += '/' + str(machine_id)
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('shutdown_node returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('shutdown_node raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('shutdown_node raising ' + str(ex))
            raise exceptions.LiqidError('shutdown_node caught ' + str(ex))

    def start_node(self, group_id, machine_id) -> Machine:
        """
        Starts a particular node
        group_id: int 
            Unique identifier of the group which contains the node of interest
        machine_id: int 
            Unique machine identifier containing the node of interest
        """
        self.logger.debug('entering start_node(%s, %s)', str(group_id), str(machine_id))
        self.check_parameter_type('group_id', group_id, 'int', False)
        self.check_parameter_type('machine_id', machine_id, 'int', False)

        try:
            path = 'power/start'
            path += '/' + str(group_id)
            path += '/' + str(machine_id)
            response, resp_body = self.send(base_client.HttpPut, path)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('start_node returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('start_node raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('start_node raising ' + str(ex))
            raise exceptions.LiqidError('start_node caught ' + str(ex))

    def set_p2p_enabled(self, machine_id, p2p_enabled) -> Machine:
        """
        Sets or clears the P2P flag for the indicated machine.
        Only effective for powered-on machines having at least two GPUs.
        machine_id: int 
            Unique id of the machine for which P2P is to be enabled or disabled.
        p2p_enabled: str 
            Value to be set for the P2P flag.
        """
        self.logger.debug('entering set_p2p_enabled(%s, %s)', str(machine_id), str(p2p_enabled))
        self.check_parameter_type('machine_id', machine_id, 'int', False)
        self.check_parameter_type('p2p_enabled', p2p_enabled, 'str', False)

        try:
            obj = self.get_machine(machine_id)
            obj.P2PEnabled = p2p_enabled
            path = 'machine/p2p'
            req_body = json.dumps(obj.to_dictionary())
            headers = {'content-type': 'application/json'}
            response, resp_body = self.send(base_client.HttpPost, path, headers=headers, request_body=req_body)
            self.logger.debug('<===%s', resp_body)
            wrapper = json.loads(resp_body)
            super().check_wrapper(wrapper, False)
            data = wrapper['response']['data']
            result = Machine().from_dictionary(data[0])
            self.logger.debug('set_p2p_enabled returning %s', str(result))
            return result
        except exceptions.LiqidError as ex:
            self.logger.debug('set_p2p_enabled raising ' + str(ex))
            raise ex
        except Exception as ex:
            self.logger.debug('set_p2p_enabled raising ' + str(ex))
            raise exceptions.LiqidError('set_p2p_enabled caught ' + str(ex))

