package org.example.serverapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 8100;
    private boolean running = true;

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is waiting on port " + PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    public void stop() {
        this.running = false;
    }

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.start();
    }
}