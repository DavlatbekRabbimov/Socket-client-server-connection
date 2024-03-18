package uz.server;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@SpringBootApplication
public class ServerRun {

	private Server server;

	@Autowired
	public ServerRun(Server server) {
		this.server = server;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerRun.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start(){
		server.start();
	}

}
