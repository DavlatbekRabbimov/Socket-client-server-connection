package uz.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Server {

    @Value("${app.server.port}")
    private int port;

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started with port: " + port);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Server connected to client...");

            Thread clientThread = new Thread(() -> {
                handleClientSocket(clientSocket);
            });
            clientThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                if (readClientRequest(reader) == null) break;
                writeServerResponse(writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String readClientRequest(BufferedReader reader) throws IOException{
        String clientRequest = reader.readLine();
        System.out.println("Server accepted <- client: " + clientRequest);
        return clientRequest;
    }

    private void writeServerResponse(PrintWriter writer){
        String serverResponse = "Server response to client: I'M SERVER";
        writer.println(serverResponse);
    }
}
