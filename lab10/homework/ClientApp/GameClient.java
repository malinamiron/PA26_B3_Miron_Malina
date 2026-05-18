package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8100;

    public void start() {
        try (Socket socket = new Socket(HOST, PORT)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread listenerThread = new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println("\n[SERVER]: " + serverResponse);
                    }
                } catch (IOException e) {
                    System.out.println("Conexiune inchisa de server.");
                }
            });


            Thread inputThread = new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    if (scanner.hasNextLine()) {
                        String userInput = scanner.nextLine();
                        out.println(userInput);
                        if (userInput.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }
                }
            });

            listenerThread.start();
            inputThread.start();

            inputThread.join();
        } catch (IOException | InterruptedException e) {
            System.err.println("Eroare client: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new GameClient().start();
    }
}