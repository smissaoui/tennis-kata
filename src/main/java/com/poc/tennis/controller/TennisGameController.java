package com.poc.tennis.controller;

import com.poc.tennis.service.TennisGameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/game")
public class TennisGameController {

    private TennisGameService tennisGameService;

    /**
     * Controller used to simulate a game of tennis
     * @param inputGame : input representing players winning the balls
     * @return an object representing the scoreBoard
     * @throws Exception
     */
    @PostMapping("/launch")
    public String launchGame(@RequestBody String inputGame) throws Exception {
        return tennisGameService.play(inputGame);
    }
}
