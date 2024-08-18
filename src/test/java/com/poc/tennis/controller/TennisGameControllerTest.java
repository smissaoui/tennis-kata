package com.poc.tennis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.tennis.service.TennisGameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TennisGameController.class)
@AutoConfigureMockMvc
public class TennisGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TennisGameService tennisGameService;

    @Test
    public void play_one_ball_test() throws Exception {
        String result = "Player A : 15 / Player B : 0";
        String inputGame = "A";


        when(tennisGameService.play(anyString())).thenReturn(result);

        MvcResult mvcResult = mockMvc.perform(post("/game/launch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputGame)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();

        assertThat(result).isEqualTo(resultJson);
    }

    @Test
    public void play_two_balls_test() throws Exception {
        String result = "Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15";
        String inputGame = "AB";


        when(tennisGameService.play(anyString())).thenReturn(result);

        MvcResult mvcResult = mockMvc.perform(post("/game/launch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputGame)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();

        assertThat(result).isEqualTo(resultJson);
    }

    @Test
    public void play_game_player_one_wins_test() throws Exception {
        String result = "Player A : 15 / Player B : 0\n" +
                "Player A : 15 / Player B : 15\n" +
                "Player A : 30 / Player B : 15\n" +
                "Player A : 30 / Player B : 30\n" +
                "Player A : 40 / Player B : 30\n" +
                "Player A wins the game";
        String inputGame = "ABABAA";


        when(tennisGameService.play(anyString())).thenReturn(result);

        MvcResult mvcResult = mockMvc.perform(post("/game/launch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputGame)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resultJson = mvcResult.getResponse().getContentAsString();

        assertThat(result).isEqualTo(resultJson);
    }


}
