package client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class ClientRun {

	private Client client;

	@Autowired
	public ClientRun(Client client) {
		this.client = client;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientRun.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendToServer(){
		client.send();
	}

}
