package bar.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import bar.model.Email;

@Component
public class Receiver {

	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(Email email) throws RuntimeException {
		System.out.println("Received <" + email + ">");
		throw new RuntimeException("Test error handler exception");
	}
}
