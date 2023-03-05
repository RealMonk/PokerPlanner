package ru.realmonk.PokerPlanner.repository;

import org.springframework.stereotype.Repository;
import ru.realmonk.PokerPlanner.models.Game;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepo {
    private final ConcurrentHashMap<String, Game> gamesHashMap = new ConcurrentHashMap<>();
//    Hashtable

    private void saveGame(String gameUUID,Game gameToSave){
        gamesHashMap.put(gameUUID, gameToSave);
    }

    public Game getGameByID(String id){
        return gamesHashMap.get(id);
    }

    public String createGame(String gameName){
        Game game = new Game(gameName);
        String UUID = game.getUUID();
        saveGame(UUID,game);
        return UUID;
    }


}
