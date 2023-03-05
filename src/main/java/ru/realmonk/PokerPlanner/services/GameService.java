package ru.realmonk.PokerPlanner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import ru.realmonk.PokerPlanner.models.Game;
import ru.realmonk.PokerPlanner.repository.GameRepo;




@Service
public class GameService {
    @Autowired
    GameRepo gameRepo;

    public Game getGameByID(String UUID){
        return gameRepo.getGameByID(UUID);
    }

    public String createGame(String gameName){
        return gameRepo.createGame(gameName);
    }

    public void addPlayerToGame(HttpSession session){
        String gameID = (String) session.getAttribute("gameID");
        Game game = getGameByID(gameID);

        String playerName = (String) session.getAttribute("playerName");
        String sessionID = (String) session.getAttribute("sessionID");
        game.addPlayerToGame(playerName,sessionID);
    }
}
