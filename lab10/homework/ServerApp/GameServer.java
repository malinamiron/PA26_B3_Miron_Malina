package org.example;

//import lombok.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

//@Data
public class GameServer {
    private static final int PORT = 8100;
    private final Map<String, PlayerStats> scoreboard = new ConcurrentHashMap<>();
    private final List<ClientThread> activeClients = Collections.synchronizedList(new ArrayList<>());
    private List<Questions.Question> gameQuestions;
    private boolean isRunning = true;
    private ServerSocket serverSocket;
    private Phaser finishLine;

    public void start() {
        this.gameQuestions = QuestionLoader.loadQuestions("C:/Users/Milika/Desktop/JAVA/lab10_homework/ServerApp/src/main/java/org/example/questions.txt");
        System.out.println("S-au incarcat intrebarile!");

        new Thread(this::listenForAdminCommands).start();

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server Blitz pornit pe portul " + PORT);
            System.out.println("Asteptam jucatori. Comenzi admin: 'start-global'");

            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientThread client = new ClientThread(socket, this);
                    activeClients.add(client);
                    client.start();
                    System.out.println("Jucator nou conectat. Total: " + activeClients.size());
                } catch (IOException e) {
                    if (isRunning) System.err.println("Eroare acceptare client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    private void listenForAdminCommands() {
        Scanner sc = new Scanner(System.in);
        while (isRunning) {
            if (sc.hasNextLine()) {
                String cmd = sc.nextLine();

                if (cmd.equalsIgnoreCase("start-global")) {
                    System.out.println("Se porneste jocul pentru toti jucatorii!");

                    finishLine = new Phaser(activeClients.size());

                    synchronized (activeClients) {
                        for (ClientThread client : activeClients) {
                            client.triggerStart();
                        }
                    }

                    waitForGameEnd();
                }
            }
        }
    }

    private void waitForGameEnd() {
        new Thread(() -> {
            finishLine.awaitAdvance(0);

            System.out.println("Toti jucatorii au terminat!");

        }).start();
    }

    public void removeClient(String name, ClientThread thread) {
        scoreboard.remove(name);
        activeClients.remove(thread);
        System.out.println(name + " a fost sters din ranking.");
    }
    public List<Questions.Question> getQuestions() { return gameQuestions; }

    public synchronized void shutdown() {
        isRunning = false;
        System.out.println("Se inchide serverul...");
        try {
            if (serverSocket != null) serverSocket.close();
            synchronized (activeClients) {
                for (ClientThread ct : activeClients) {
                    ct.interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String displayWinner() {
        if (scoreboard.isEmpty()) {
            return "Nu exista jucatori inregistrati inca.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nClasament Final\n");

        scoreboard.entrySet().stream()
                .sorted((e1, e2) -> {
                    int scoreCompare = Integer.compare(e2.getValue().getScore(), e1.getValue().getScore());
                    if (scoreCompare != 0) return scoreCompare;
                    return Long.compare(e1.getValue().getTotalResponseTime(), e2.getValue().getTotalResponseTime());
                })
                .forEach(entry -> {
                    sb.append(entry.getKey()).append(": ")
                            .append(entry.getValue().getScore()).append(" pct | ")
                            .append(entry.getValue().getTotalResponseTime()).append("ms\n");
                });

        return sb.toString();
    }

    public Map<String, PlayerStats> getScoreboard() { return scoreboard; }

    public static void main(String[] args) {
        new GameServer().start();
    }

    public Phaser getFinishLine() {
        return finishLine;
    }
}