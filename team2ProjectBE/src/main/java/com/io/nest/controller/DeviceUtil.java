package com.io.nest.controller;

import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.io.nest.model.DeviceDetail;
import com.io.nest.repository.DeviceRepository;
//import com.io.nest.service.ConsumerService;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.QueryIterable;
import com.microsoft.azure.iot.service.exceptions.IotHubException;
import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.DeviceStatus;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.services.servicebus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.servicebus.ServiceBusContract;
import com.microsoft.windowsazure.services.servicebus.ServiceBusService;
import com.microsoft.windowsazure.services.servicebus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveMode;
import com.microsoft.windowsazure.services.servicebus.models.ReceiveQueueMessageResult;
@RestController
public class DeviceUtil {
    @Value("${documentDBHost}")
    private String documentDBHost;

    @Value("${documentDBMasterKey}")
    private String documentDBMasterKey;
    
    @Value("${registrationConnectionString}")
    private String registrationConnectionString;

//    @Value("${sbusname}")
//    private String sbusname;
//
//    @Value("${sbuskey}")
//    private String sbuskey;

//    @Value("${sbusqueuename}")
//    private String sbusqueuename;

    @Autowired
    DeviceRepository devrepo;
    
//    @Autowired
//    TelemetryDataService telemetryservice;
    
//    @Autowired
//    ConsumerService consumerservice;
    
    
    private static final Logger LOGGER = Logger.getLogger(DeviceUtil.class.getName());
    private static final String databasename = "devices";
    private static final String collectionName = "deviceItems";

   
    private static Database db = new Database();
    private static DocumentCollection docColl = new DocumentCollection();
    private static RegistryManager registryManager = null;
    private final Gson gson = new Gson();

    private static DocumentClient documentClient;
    Configuration config = null;
    ServiceBusContract service = null;
    
    @PostConstruct
    void init() throws Exception{
        
        //  Init IOTHub
        registryManager = RegistryManager.createFromConnectionString(registrationConnectionString);
        
        //  Init DocumentDB
        documentClient = new DocumentClient(documentDBHost, documentDBMasterKey, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);      
        db.setId(databasename);
        docColl.setId(collectionName);
        
        List<Database> dbases = documentClient.queryDatabases("SELECT * FROM root r WHERE r.id='" + databasename + "'", null).getQueryIterable().toList();
        if(dbases == null || dbases.isEmpty()){
            documentClient.createDatabase(db, null);
            db = documentClient.queryDatabases("SELECT * FROM root r WHERE r.id='" + databasename + "'", null).getQueryIterable().toList().get(0);
        }else{
            db = dbases.get(0);
        }
        List<DocumentCollection> docCollz = documentClient.queryCollections(db.getSelfLink(),
                "SELECT * FROM root r WHERE r.id='" + collectionName + "'", null).getQueryIterable().toList();
        if(docCollz == null || docCollz.isEmpty()){
            documentClient.createCollection(db.getSelfLink(), docColl, null);
            docColl = documentClient.queryCollections(db.getSelfLink(),
                    "SELECT * FROM root r WHERE r.id='" + collectionName + "'", null).getQueryIterable().toList().get(0);    
        }else{
            docColl = docCollz.get(0);
        }
        
        //  Service Bus
//       config = ServiceBusConfiguration.configureWithSASAuthentication(
//                sbusname,
//                "RootManageSharedAccessKey",
//                sbuskey,
//                ".servicebus.windows.net"
//                );
//       //   Service Bus queue
//       service = ServiceBusService.create(config);
    }
    
    

//    void showSbusMessagesMessages(){
//        try
//        {
////            LOGGER.info("Getting messages from "+sbusname+" with key: "+sbuskey+" from queue: "+sbusqueuename);
//            ReceiveMessageOptions opts = ReceiveMessageOptions.DEFAULT;
//            //  Leaves messages on Queue
//            opts.setReceiveMode(ReceiveMode.PEEK_LOCK);
//
//            while(true)  {
////                 ReceiveQueueMessageResult resultQM =
////                         service.receiveQueueMessage(sbusqueuename, opts);
////                BrokeredMessage message = resultQM.getValue();
//                if (message != null && message.getMessageId() != null)
//                {
//                    System.out.println("MessageID: " + message.getMessageId());
//                    // Display the queue message.
//                    System.out.print("From queue: ");
//                    byte[] b = new byte[200];
//                    String s = null;
//                    int numRead = message.getBody().read(b);
//                    while (-1 != numRead)
//                    {
//                        s = new String(b);
//                        s = s.trim();
//                        System.out.print(s);
//                        numRead = message.getBody().read(b);
//                    }
//                    System.out.println();
//                    System.out.println("Custom Property: " +
//                        message.getProperty("MyProperty"));
//                    // Remove message from queue.
//                    System.out.println("Deleting this message.");
//                    //service.deleteMessage(message);
//                }  
//                else  
//                {
//                    System.out.println("Finishing up - no more messages.");
//                    break;
//                    // Added to handle no more messages.
//                    // Could instead wait for more messages to be added.
//                }
//            }
//        }
//        catch (ServiceException e) {
//            System.out.print("ServiceException encountered: ");
//            System.out.println(e.getMessage());
//            System.exit(-1);
//        }
//        catch (Exception e) {
//            System.out.print("Generic exception encountered: ");
//            System.out.println(e.getMessage());
//            System.exit(-1);
//        }
//    }

   

   

    
    
    boolean registerDevice(@RequestBody String deviceRequest) throws Exception {
        boolean result = false, res1  = false, res2  = false;
        try{
            JsonElement element = gson.fromJson (deviceRequest, JsonElement.class);
            JsonObject jsonObj = element.getAsJsonObject();

            res1 = registerAzureIOTDevice(jsonObj);
            res2 = persistDeviceModel(jsonObj);
            if(res1 && res2)
                result = true;
            else
                throw new Exception("One of the registration methods failed:");
        }catch(Exception ex){
            LOGGER.info("X message: "+ex.getMessage());
            LOGGER.info("- registerAzureIOTDevice "+res1);
            LOGGER.info("- persistDeviceModel "+res2);
        }
        return result;
    }
        
    private String someNamingLogic(){
        return java.util.UUID.randomUUID().toString();      
    }

    private boolean registerAzureIOTDevice(JsonObject deviceRequest) throws Exception {
        boolean result = false;
                
        String deviceId = deviceRequest.get("deviceId").toString();
        String azureIotUud = someNamingLogic();
        
        deviceRequest.addProperty("azureiot_createdate", Instant.now().toString());
        deviceRequest.addProperty("azureiot_uuid", azureIotUud);

        deviceRequest.addProperty("azureiot_createdate", Instant.now().toString());
        
        Device device = Device.createFromId( azureIotUud , null, null);
        try {
            device = registryManager.addDevice(device);
            LOGGER.info("Created Azure IOT device with ID: "+deviceId+" azureId "+azureIotUud);
            result = true;
        } catch (IotHubException iote) {
            LOGGER.log(Level.SEVERE, "Connection String used: "+registrationConnectionString);
            LOGGER.log(Level.SEVERE, "Exception creating device with ID: "+deviceId+" azureID "+azureIotUud, iote);
        }
        return result;
    }
    private boolean persistDeviceModel(JsonObject deviceRequest) throws DocumentClientException{
        boolean result = false;

        //  Adding timestamp
        deviceRequest.addProperty("azuredocdb_createdate", Instant.now().toString());
        //  Persisting in docDB
        Document deviceDocument = new Document(gson.toJson(deviceRequest));
        documentClient.createDocument(docColl.getSelfLink(), deviceDocument, null,false);
        result = true;
        return result;
    } 
    String regDevice(DeviceDetail dd) throws Exception {
     String connectionstring=null;
        devrepo.save(dd);        
       String deviceId = dd.getDeviceId();

       String ds = dd.getConnStatus().toString();
    Device device = Device.createFromId(deviceId, DeviceStatus.valueOf(ds) , null);
    try {
        
      device = registryManager.addDevice(device);
      connectionstring = "HostName=iot-team2nest-training-team2.azure-devices.net;DeviceId="+device.getDeviceId()+";SharedAccessKey="+device.getPrimaryKey();
//      System.out.print(device.getDevice)
      LOGGER.info("Created Azure IOT device with ID: "+deviceId);
//      consumerservice.iotdataGeneration(deviceId, connectionstring);
    } catch (IotHubException iote) {
      try {
          
        device = registryManager.getDevice(deviceId);
       
      } catch (IotHubException iotf) {
          LOGGER.log(Level.SEVERE, "Connection String used: "+ registrationConnectionString);
          LOGGER.log(Level.SEVERE, "Exception creating device with ID: "+deviceId, iotf);
      }
    }
    return connectionstring;
    
    }
    

}
