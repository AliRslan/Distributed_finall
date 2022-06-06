package com.example.servicemessagedoing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController("read")
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE)
    @GetMapping("read")
    public void listener(Message message) {
        System.out.println(message);

    }
}
