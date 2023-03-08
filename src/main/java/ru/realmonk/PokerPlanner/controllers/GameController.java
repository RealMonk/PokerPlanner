package ru.realmonk.PokerPlanner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ru.realmonk.PokerPlanner.services.GameService;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/gameboard/startgame")
    public void startGame() {

    }
}
