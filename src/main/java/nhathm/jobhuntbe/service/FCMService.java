package nhathm.jobhuntbe.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author nhathm
 */
@Service
public class FCMService {

    @Autowired
    RestTemplate restTemplate;

    public String sendFcmMessage(String title, String message, String to) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=AAAAGK9f7D8:APA91bFKFcyJ_sgUXVw42JDG-5HP254nufq4dMA2jp6O01JaqpAXuxlcdJuc1p3d_c0O2O9RhzFEmjHff6sQUYQmXumMeyPXrubVz17PXnglgdkp__k2gTX99UXDeXzxY3vkvCKPIcmu");
        Message msg = new Message(to, new Notification(title, message));
        this.restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", new HttpEntity<>(msg, headers), String.class);
        return message;
    }

    @Data
    @AllArgsConstructor
    public class Message {
        private String to;
        private Notification notification;
    }

    @Data
    @AllArgsConstructor
    public class Notification {
        private String title;
        private String body;
    }
}
