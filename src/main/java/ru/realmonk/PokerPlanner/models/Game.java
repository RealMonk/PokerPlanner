package ru.realmonk.PokerPlanner.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Game {
    public static final Logger LOG = LoggerFactory.getLogger(Game.class);

    private final String gameName;
    private final String UUID;
    private final HashMap<String, String> playersMap = new HashMap<>();
    private boolean GameStarted = false;

    public boolean isGameStarted() {
        return GameStarted;
    }

    public Game(String gameName) {
        this.gameName = gameName;
        UUID = String.valueOf(java.util.UUID.randomUUID());
        LOG.info("Game constructor running. UUID: {} , name: {}", UUID, gameName);
    }

    public void addPlayerToGame(String playerName, String sessionID) {
        playersMap.put(sessionID, playerName);
    }

    public String getGameName() {
        return gameName;
    }

    public String getUUID() {
        return UUID;
    }

    public HashMap<String, String> getPlayersMap() {
        return playersMap;
    }

    public void startGame() {
        GameStarted = true;
    }

}