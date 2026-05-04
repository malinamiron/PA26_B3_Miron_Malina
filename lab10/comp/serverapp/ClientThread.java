package org.example.serverapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request = in.readLine();
            String response;

            if (request == null) {
                return;
            }

            if (request.equals("stop")) {
                response = "Server stopped";
                out.println(response);
                server.stop();
                System.exit(0);
            } else {
                response = "Server received the request: " + request;
                out.println(response);
            }

            System.out.println("Processed request: " + request);

        } catch (IOException e) {
            System.err.println("Communication error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}