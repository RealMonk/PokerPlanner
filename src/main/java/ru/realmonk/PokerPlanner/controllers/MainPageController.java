package ru.realmonk.PokerPlanner.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ru.realmonk.PokerPlanner.models.Game;
import ru.realmonk.PokerPlanner.services.GameService;

@Controller
public class MainPageController {

    public static final Logger LOG = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String mainPage(HttpSession session) {

        LOG.info("index");
        session.setMaxInactiveInterval(24 * 60 * 60);
        printSessionInfo(session);
        LOG.info("");
        return "Main/index";
    }

    @PostMapping("/createNewGame")
    public String NewGame(HttpSession session,
                          @RequestParam(value = "gameName") String gameName,
                          @RequestParam(value = "playerName") String playerName) {
        LOG.info("New game method");
        String gameID = gameService.createGame(gameName);
        session.setAttribute("gameID",gameID);
        session.setAttribute("playerName", playerName);
        LOG.info("New game created: {}",session.getAttribute("gameID"));
//        LOG.info("New game created: " + session.getAttribute("gameID"));
        LOG.info("");
        return "redirect:/gameboard";
    }

    @GetMapping("/gameboard")
    public String gameboard(HttpSession session, Model model) {
        LOG.info("Game board method");
        printSessionInfo(session);
        Game game = gameService.getGameByID((String) session.getAttribute("gameID"));
        game.addPlayerToGame(session.getId(), (String) session.getAttribute("playerName"));
        LOG.info("Game name in gameboard method: " + game.getGameName());
        model.addAttribute("gameName", game.getGameName());
        model.addAttribute("sessionID", session.getId());
        model.addAttribute("currentGameUUID", game.getUUID());
        model.addAttribute("playerName", session.getAttribute("playerName"));
        model.addAttribute("playerList",game.getPlayersMap() );
        LOG.info("");
        return "Main/gameboard";
    }

    @GetMapping("/join/{id}")
    public String joinGame(HttpSession session, @PathVariable String id) {
        LOG.info("Join game method:");
        printSessionInfo(session);
        Game game = gameService.getGameByID(id);
        session.setAttribute("gameID", id);
        LOG.info("Joining game, name: "+game.getGameName()+" ID: " + game.getUUID());
        LOG.info("");

        String playerName = (String) session.getAttribute("playerName");
        if (!(playerName==null)){
            LOG.info(playerName);
            return "redirect:/gameboard";
        }
        return "Main/requestName";
    }

    @PostMapping("/setName")
    public String setName(HttpSession session,
                          @RequestParam(value = "playerName") String playerName) {
        LOG.info("Set name method");
        session.setAttribute("playerName", playerName);
        LOG.info("Player name set to : " + session.getAttribute("playerName"));
        LOG.info("");
        return "redirect:/gameboard";
    }

    private void printSessionInfo(HttpSession session) {
        LOG.info("Session ID = {} , Session TTL = {}", session.getId(), session.getMaxInactiveInterval() );
//        LOG.info("Session ID = " + session.getId() + ", Session TTL = " + session.getMaxInactiveInterval());
    }


    
}
