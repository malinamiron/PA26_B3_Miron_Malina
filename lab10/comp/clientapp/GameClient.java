package org.example.clientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int PORT = 8100;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Client exiting");
                break;
            }

            sendRequest(command);

            if (command.equalsIgnoreCase("stop")) {
                break;
            }
        }
    }

    private void sendRequest(String command) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(command);

            String response = in.readLine();
            System.out.println(response);

        } catch (IOException e) {
            System.err.println("The server might be stopped.");
        }
    }

    public static void main(String[] args) {
        GameClient client = new GameClient();
        client.start();
    }
}