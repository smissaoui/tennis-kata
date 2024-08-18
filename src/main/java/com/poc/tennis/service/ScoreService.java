package com.poc.tennis.service;

import com.poc.tennis.model.Score;
import com.poc.tennis.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.poc.tennis.utils.Constant.*;

@Service
public class ScoreService {

    /**
     * Service used to update the score of a ball in the game, and determine the winner
     * @param scoringPlayer : input representing the scoring player
     * @param otherPlayer : input representing the other player
     * @param scoreBoard : input representing the score board
     * @param scoreResult : input representing the actual score result
     *
     * @return Object representing the score result returned to the end user
     * @throws Exception
     */
    public StringBuilder updateScore(String scoringPlayer, String otherPlayer, Map<String, Score> scoreBoard, StringBuilder scoreResult) {
        Score scoringPlayerActualPoints = getPlayerActualScore(scoringPlayer, scoreBoard);
        Score otherPlayerActualPoints = getPlayerActualScore(otherPlayer, scoreBoard);

        if(scoringPlayerActualPoints.getPoint()<40){
            //score
            score(scoringPlayer, scoringPlayerActualPoints, scoreBoard);
            logActualGameState(scoreBoard, scoreResult);
        }else if(scoringPlayerActualPoints.getPoint() == 40 && otherPlayerActualPoints.getPoint() < 40){
            //win game
            logWinner(scoringPlayer, scoreResult);
        }else if(scoringPlayerActualPoints.getPoint() == 40 && otherPlayerActualPoints.getPoint() == 40){
            //advantage
            score(scoringPlayer, scoringPlayerActualPoints, scoreBoard);
            logActualGameState(scoreBoard, scoreResult);
        }else if(scoringPlayerActualPoints.getPoint() == 40 && otherPlayerActualPoints.getPoint() == 45){
            //return to deuce
            previousScore(otherPlayer, otherPlayerActualPoints, scoreBoard);
            logActualGameState(scoreBoard, scoreResult);
        }else if(scoringPlayerActualPoints.getPoint()  == 45 && otherPlayerActualPoints.getPoint() == 40){
            //win game
            logWinner(scoringPlayer, scoreResult);
        }
        return scoreResult;
    }

    private void logActualGameState(Map<String, Score> scoreBoard, StringBuilder scoreResult){
        String scorePlayerOne = Utils.transformScoreToString(getPlayerActualScore(PLAYER_ONE, scoreBoard).getPoint());
        String scorePlayerTwo = Utils.transformScoreToString(getPlayerActualScore(PLAYER_TWO, scoreBoard).getPoint());

        scoreResult.append(PLAYER_ONE)
                .append(" : ")
                .append(scorePlayerOne)
                .append(" / ")
                .append(PLAYER_TWO)
                .append(" : ")
                .append(scorePlayerTwo)
                .append("\n");
    }

    private void logWinner(String player, StringBuilder scoreResult){
        scoreResult.append(player)
                .append(" wins the game");
    }

    private Score getPlayerActualScore(String player , Map<String, Score> scoreBoard) {
        return scoreBoard.getOrDefault(player, Score.ZERO);
    }

    private void score(String player, Score score, Map<String, Score> scoreBoard){
        scoreBoard.put(player, score.next());
    }

    private void previousScore(String player, Score score, Map<String, Score> scoreBoard){
        scoreBoard.put(player, score.previous());
    }
}
