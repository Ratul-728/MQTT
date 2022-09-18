package MQTT.mqtt.Controller;

import MQTT.mqtt.MqttGateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    MqttGateway mqttGateway;
    @PostMapping("/sendMessage")
    public ResponseEntity<?> publish(@RequestBody String mqttMessage){
        try {
            JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
            mqttGateway.sentToMqtt(convertObject.get("message").toString(), convertObject.get("topic").toString());
            return ResponseEntity.ok("Success");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("fail");
        }
    }
}
