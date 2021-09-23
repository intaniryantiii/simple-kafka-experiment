package com.intan.trialsimplekafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @PostMapping(path = "kirim")
    public void kirimPesan() {
        for (int i = 1; i <= 10; i++) {
            this.template.send("NewTopic", "Ini Pesan ke " + i);
        }
    }

    @KafkaListener(id = "kafka", topics = "NewTopic")
    public void kafkakonsumer(String in) {
        System.out.println("Data yang ditarik : " + in);
    }
}
