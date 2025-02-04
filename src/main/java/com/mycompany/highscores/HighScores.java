package com.mycompany.highscores;

import java.io.*;
import java.util.*;

public class HighScores {
    private static final String FILE_PATH = "src/players.csv";

    public static void main(String[] args) {
        List<Player> players = loadPlayers();

        System.out.println("Original High Scores:");
        displayPlayers(players);

        Player newPlayer = new Player("ZZZ", 250000);
        players.add(newPlayer);

        players.sort(Comparator.comparingInt(Player::getHighScore).reversed());

        if (players.size() > 10) {
            players.remove(players.size() - 1);
        }

        System.out.println("\nUpdated High Scores:");
        displayPlayers(players);
    }

    private static List<Player> loadPlayers() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    players.add(new Player(data[0], Integer.parseInt(data[1])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return players;
    }

    private static void displayPlayers(List<Player> players) {
        for (int i=0; i < players.size(); i++) {
            Player p = players.get(i);
            System.out.printf("#%-2d    %-3s    %,10d%n", (i + 1), p.getInitials(), p.getHighScore());
        }
    }
}

class Player {
    private String initials;
    private int highScore;

    public Player(String initials, int highScore) {
        this.initials = initials;
        this.highScore = highScore;
    }

    public String getInitials() {
        return initials;
    }

    public int getHighScore() {
        return highScore;
    }
}
