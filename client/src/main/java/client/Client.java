package client;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class Client {

    @Value("${app.server.host}")
    private String host;

    @Value("${app.server.port}")
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @PostConstruct
    public void init() {
        try {
            this.socket = new Socket(host, port);
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printClientMessage(String message){
        writer.println(message);
    }

    private void readServerResponse(){
        try {
            String serverResponse = reader.readLine();
            System.out.println("Client accepted: <- " + serverResponse);
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void send() {
        System.out.println("Client connected to server...");
        while (true) {;
            printClientMessage("Client send data to server -> : " + "I'M CLIENT");
            readServerResponse();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
