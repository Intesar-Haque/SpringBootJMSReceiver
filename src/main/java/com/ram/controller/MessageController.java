package com.ram.controller;

import com.ram.config.RabbitMQConfiguration;
import com.ram.model.MessageModel;
import com.ram.receiver.JMSReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JMSReceiver jmsReceiver;
    @RequestMapping("/send")
    public void sendMsg(@RequestParam String message){
        MessageModel msg = new MessageModel();
        msg.setName(message);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName, "message_routing_key", msg);
    }
    @RequestMapping("/receive")
    public void receiveMsg(){
//        String message = jmsReceiver
//        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName, "message_routing_key", msg);
    }
}
