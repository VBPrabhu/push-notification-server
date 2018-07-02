import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;

public class ApnsPush {
    private static String DEVICE_TOKEN = "your device token";
    private static String PATH_TO_P12_CERT = "certitifcate p12 file";
    private static String CERT_PASSWORD = "password";

    private static String title = "Notification title";
    private static String message = "This is your Notification message";


    public static void main(String[] args) throws FileNotFoundException {
     
    	File file = new File(PATH_TO_P12_CERT);
    	InputStream is = new FileInputStream(file);

        ApnsServiceBuilder service = APNS.newService();        
        service.withCert(is, CERT_PASSWORD).withSandboxDestination().build();

        String payload = APNS.newPayload()
                .alertBody(message).alertTitle(title)
                .sound("default")
                .actionKey(title)
                .customField("extra-data-1", "123456") 
                .customField("extra-data-2", "123456")
                .build();
        
        ApnsService apnsService = null;
        apnsService.push(DEVICE_TOKEN, payload);
        System.out.println("The message has been hopefully sent :) ...");
    }
}
