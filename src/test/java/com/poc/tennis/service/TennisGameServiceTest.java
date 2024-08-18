package com.poc.tennis.service;

import com.poc.tennis.exception.IncorrectInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TennisGameServiceTest {

    @Mock
    private ScoreService scoreService;

    private TennisGameService tennisGameService;

    @BeforeEach
    void init(){
        tennisGameService = new TennisGameService(scoreService);
    }

    @Test
    public void play_one_ball_test() throws Exception {
        String inputGame = "A";
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append("Player A : 15 / Player B : 0");
        when(scoreService.updateScore(anyString(), anyString(), anyMap(), any())).thenReturn(expectedResult);

        String result  = tennisGameService.play(inputGame);

        verify(scoreService).updateScore(anyString(), anyString(), anyMap(), any());

        assertThat(result).isEqualTo(expectedResult.toString());
    }

    @Test
    public void play_game_player_two_wins_test() throws Exception {
        String inputGame = "ABABABBB";
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append("Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15\n" +
                "Player A : 30 / Player B : 15\n" +
                "Player A : 30 / Player B : 30\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : 40 / Player B : ADVANTAGE\n" +
                "Player B wins the game");

        when(scoreService.updateScore(anyString(), anyString(), anyMap(), any())).thenReturn(expectedResult);

        String result  = tennisGameService.play(inputGame);

        verify(scoreService).updateScore(anyString(), anyString(), anyMap(), any());

        assertThat(result).isEqualTo(expectedResult.toString());
    }

    @Test
    public void play_game_player_two_wins_add_inputs_even_after_game_finished_test() throws Exception {
        String inputGame = "ABABABBBBBBBBBBBBBBBBBBBBBBAAAAABBBBBAAA";
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append("Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15\n" +
                "Player A : 30 / Player B : 15\n" +
                "Player A : 30 / Player B : 30\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A : 40 / Player B : 40\n" +
                "Player A : 40 / Player B : ADVANTAGE\n" +
                "Player B wins the game");

        when(scoreService.updateScore(anyString(), anyString(), anyMap(), any())).thenReturn(expectedResult);

        String result  = tennisGameService.play(inputGame);

        verify(scoreService).updateScore(anyString(), anyString(), anyMap(), any());

        assertThat(result).isEqualTo(expectedResult.toString());
    }

    @Test
    public void play_game_wrong_input_test() {
        String inputGame = "HABC";

        assertThrows(IncorrectInputException.class, () -> {
            tennisGameService.play(inputGame);
        });
    }
}
