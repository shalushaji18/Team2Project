package com.io.nest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@RestController
@CrossOrigin("*")
public class DeviceController {
    @Autowired
    DeviceUtil deviceUtil;
    
    private final Gson gson = new Gson();
    private final static String TEST_DEVICENAME = "testDeviceNr";
    final static String TEST_DEVICETYPE = "testdevice";
    //  Creates x amount of testdevices
    @RequestMapping(value = "/createTestDevices", method = RequestMethod.GET)
    public void createTestDevices(@RequestParam int numberOfDevices) throws Exception {
        for(int nr=0; nr<numberOfDevices; nr++ ){
            JsonObject testObject = new JsonObject();
            testObject.addProperty("deviceId", TEST_DEVICENAME+nr);
            testObject.addProperty("deviceType", TEST_DEVICETYPE);
            deviceUtil.registerDevice(gson.toJson(testObject));
        }
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(@RequestBody String deviceRequest) throws Exception {
        JsonElement element = gson.fromJson (deviceRequest, JsonElement.class);
        JsonObject deviceRequestJ = element.getAsJsonObject();
        String payId = deviceRequestJ.getAsJsonObject("papayaModel").get("payeeId").toString();
        System.out.println("payeeID: "+payId);

    }

}
