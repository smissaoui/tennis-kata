package com.poc.tennis.service;

import com.poc.tennis.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreServiceTest {

    private ScoreService scoreService;


    @BeforeEach
    void init(){
        scoreService = new ScoreService();
    }

    @Test
    public void update_score_first_case_test()  {
        String scoringPlayer = "Player A";
        String otherPlayer = "Player B";
        Map<String, Score> scoreBoard = new HashMap<>();
        StringBuilder scoreResult = new StringBuilder();

        String expectedResult = "Player A : 15 / Player B : 0\n";

        StringBuilder result = scoreService.updateScore(scoringPlayer, otherPlayer, scoreBoard, scoreResult);

        assertThat(result.toString()).isEqualTo(expectedResult);
    }

    @Test
    public void update_score_second_case_test()  {
        String scoringPlayer = "Player A";
        String otherPlayer = "Player B";
        Map<String, Score> scoreBoard = new HashMap<>();
        scoreBoard.put("Player A", Score.FORTY);
        StringBuilder scoreResult = new StringBuilder();
        scoreResult.append("Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n");

        String expectedResult = "Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A wins the game";

        StringBuilder result = scoreService.updateScore(scoringPlayer, otherPlayer, scoreBoard, scoreResult);

        assertThat(result.toString()).isEqualTo(expectedResult);
    }

    @Test
    public void update_score_third_case_test()  {
        String scoringPlayer = "Player A";
        String otherPlayer = "Player B";
        Map<String, Score> scoreBoard = new HashMap<>();
        scoreBoard.put("Player A", Score.FORTY);
        scoreBoard.put("Player B", Score.FORTY);
        StringBuilder scoreResult = new StringBuilder();
        scoreResult.append("Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n");

        String expectedResult = "Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : ADVANTAGE / Player B : 40\n";

        StringBuilder result = scoreService.updateScore(scoringPlayer, otherPlayer, scoreBoard, scoreResult);

        assertThat(result.toString()).isEqualTo(expectedResult);
    }

    @Test
    public void update_score_fourth_case_test()  {
        String scoringPlayer = "Player A";
        String otherPlayer = "Player B";
        Map<String, Score> scoreBoard = new HashMap<>();
        scoreBoard.put("Player A", Score.FORTY);
        scoreBoard.put("Player B", Score.ADVANTAGE);
        StringBuilder scoreResult = new StringBuilder();
        scoreResult.append("Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : 40 / Player B : ADVANTAGE\n");

        String expectedResult = "Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : 40 / Player B : ADVANTAGE\n" +
                "Player A : 40 / Player B : 40\n";

        StringBuilder result = scoreService.updateScore(scoringPlayer, otherPlayer, scoreBoard, scoreResult);

        assertThat(result.toString()).isEqualTo(expectedResult);
    }

    @Test
    public void update_score_last_case_test()  {
        String scoringPlayer = "Player A";
        String otherPlayer = "Player B";
        Map<String, Score> scoreBoard = new HashMap<>();
        scoreBoard.put("Player A", Score.ADVANTAGE);
        scoreBoard.put("Player B", Score.FORTY);
        StringBuilder scoreResult = new StringBuilder();
        scoreResult.append("Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : 40 / Player B : ADVANTAGE\n" +
                "Player A : ADVANTAGE / Player B : 40\n");

        String expectedResult = "Player A : 15 / Player B : 0\n" +
                "Player A : 30 / Player B : 0\n" +
                "Player A : 40 / Player B : 0\n" +
                "Player A : 40 / Player B : 15\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : 40 / Player B : ADVANTAGE\n" +
                "Player A : ADVANTAGE / Player B : 40\n" +
                "Player A wins the game";

        StringBuilder result = scoreService.updateScore(scoringPlayer, otherPlayer, scoreBoard, scoreResult);

        assertThat(result.toString()).isEqualTo(expectedResult);
    }
}
