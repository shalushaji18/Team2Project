package com.io.nest;

import java.io.IOException;
import java.net.URISyntaxException;

import com.microsoft.azure.iot.service.exceptions.IotHubException;
import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

public class CreateDeviceIdentity {
    private static final String connectionString = "HostName=iot-team2nest-training-team2.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=h6X3A4KLmpX3D5ymrbMMft4x3fiAQCsC93oLxZm/iXY=";
    private static final String deviceId = "BP-Monitor-001";

       public static void main( String[] args ) throws IOException, URISyntaxException, Exception
        {
            RegistryManager registryManager = RegistryManager.createFromConnectionString(connectionString);

    Device device = Device.createFromId(deviceId, null, null);
    try {
      device = registryManager.addDevice(device);
    } catch (IotHubException iote) {
      try {
        device = registryManager.getDevice(deviceId);
      } catch (IotHubException iotf) {
        iotf.printStackTrace();
      }
    }
    System.out.println("Device id: " + device.getDeviceId());
    System.out.println("Device key: " + device.getPrimaryKey());
        }
}
