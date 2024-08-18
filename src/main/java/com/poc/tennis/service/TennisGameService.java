package com.poc.tennis.service;

import com.poc.tennis.exception.IncorrectInputException;
import com.poc.tennis.model.Score;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.poc.tennis.utils.Constant.*;

@Service
@Slf4j
@AllArgsConstructor
public class TennisGameService {

    private final ScoreService scoreService;

    /**
     * Service used to simulate a game of tennis
     * @param inputGame : input representing players winning the balls
     * @return Object representing the scoreBoard
     * @throws Exception
     */
    public String play(String inputGame) throws Exception {
        log.info("Begin of the tennis Game");
        Map<String, Score> scoreBoard = new HashMap<>();
        StringBuilder scoreResult = new StringBuilder();
        for (int i = 0; i < inputGame.length(); i++) {
            scoreResult = updateScoreBoard(inputGame.charAt(i), scoreBoard, scoreResult);
            if(isGameFinished(scoreResult)){
                break;
            }
        }
        log.info("Result of the Game : \n {}", scoreResult);
        log.info("End of the tennis Game");
        return scoreResult.toString();
    }

    private boolean isGameFinished(StringBuilder scoreResult) {
        return scoreResult.toString().contains("wins the game");
    }

    private StringBuilder updateScoreBoard(char scoringPlayer, Map<String, Score> scoreBoard, StringBuilder scoreResult) throws Exception {
        if(scoringPlayer == 'A'){
            return scoreService.updateScore(PLAYER_ONE, PLAYER_TWO, scoreBoard, scoreResult);
        } else if(scoringPlayer == 'B'){
            return scoreService.updateScore(PLAYER_TWO, PLAYER_ONE, scoreBoard, scoreResult);
        }else{
            throw new IncorrectInputException(INCORRECT_INPUT_EXCEPTION_MESSAGE);
        }
    }
}
