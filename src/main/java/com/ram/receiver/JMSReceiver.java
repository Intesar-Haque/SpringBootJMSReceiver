package com.ram.receiver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.ram.model.MessageModel;

@Component
public class JMSReceiver implements ChannelAwareMessageListener
{

	@Override
	public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception
	{

		byte[] byteArray = message.getBody();
		MessageModel msg = (MessageModel) getObject(byteArray);
		System.out.println(msg);
		//channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}

	private static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
		ObjectInput in = new ObjectInputStream(bis);
		return in.readObject();
	}
}
