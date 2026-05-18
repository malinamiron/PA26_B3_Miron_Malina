package org.example;

import java.io.*;
import java.net.Socket;


public class ClientThread extends Thread {
    private final Object startLock = new Object();
    private boolean gameStarted = false;
    private Socket socket;
    private GameServer server;
    private PrintWriter out;
    private BufferedReader in;
    private String playerName;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
        this.playerName = "Player-" + socket.getPort();
    }

    public void triggerStart() {
        synchronized (startLock) {
            gameStarted = true;
            startLock.notify();
        }
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            server.getScoreboard().put(playerName, new PlayerStats());

            out.println("Te-ai conectat cu succes.");
            out.println("Asteapta semnalul de START de la server pentru a incepe jocul.");

            synchronized (startLock) {
                while (!gameStarted) {
                    startLock.wait();
                }
            }

            runGameLoop();

        } catch (IOException | InterruptedException e) {
            System.err.println("Conexiune intrerupta cu " + playerName);
        } finally {
            server.removeClient(playerName, this);
            closeConnection();
        }
    }

    private void runGameLoop() throws IOException {
        out.println("\n JOCUL A INCEPUT ACUM!");

        for (Questions.Question q : server.getQuestions()) {
            out.println("\nINTREBARE: " + q.text());
            out.println("VARIANTE: " + String.join(", ", q.options()));

            long startTime = System.currentTimeMillis();
            String response = in.readLine();
            long endTime = System.currentTimeMillis();

            if (response == null) return;

            if (response.equalsIgnoreCase("exit")) {
                out.println("Ai parasit jocul.");
                System.out.println("Client deconectat!");
                return;
            }

            if (response.equalsIgnoreCase("ranking")) {
                out.println("Nu poti vedea clasamentul in timpul jocului!");
                out.println("Reintrodu raspunsul pentru: " + q.text());
                response = in.readLine();
                endTime = System.currentTimeMillis();
            }

            if (response.equalsIgnoreCase("stop")) {
                out.println("Serverul este oprit!");
                server.shutdown();
                System.exit(0);
            }

            long timeTaken = endTime - startTime;
            boolean inTime = timeTaken <= (q.timeLimitSeconds() * 1000);
            boolean correct = response.equals(String.valueOf(q.correctIndex()));

            if (correct && inTime) {
                server.getScoreboard().get(playerName).addResult(true, timeTaken);
                out.println("CORECT! (" + timeTaken + "ms)");
            } else {
                server.getScoreboard().get(playerName).addResult(false, timeTaken);
                out.println(inTime ? "GRESIT!" : "TIMP EXPIRAT!");
            }
        }

        out.println("\nAi terminat setul de intrebari!");
        out.println("\nAsteapta finalul jocului!");

        server.getFinishLine().arriveAndAwaitAdvance();

        out.println(server.displayWinner());

        out.println("Scrie 'exit' pentru a parasi serverul.");

        String finalCmd;
        while ((finalCmd = in.readLine()) != null) {
            if (finalCmd.equalsIgnoreCase("exit")) {
                out.println("Te-ai deconectat!");
                System.out.println("Client deconectat!");
                break;
            }
        }
    }

    private void closeConnection() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}