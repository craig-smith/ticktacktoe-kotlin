package com.craig.kotlin.controller

import com.craig.kotlin.Application
import com.craig.kotlin.data.Game
import com.craig.kotlin.data.GameBoard
import com.craig.kotlin.data.Play
import com.craig.kotlin.data.Player
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Application::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JSONControllerTest {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var wac: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    fun shouldReturnHello() {
        val result = testRestTemplate.getForEntity("/hello", String::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals("{\"message\":\"hello\"}", result.body)

    }

    @Test
    fun shouldReturnGame() {
        val gameBoard = GameBoard()
        val player = Player.O
        val play = Play(1, player.name)
        val game = Game(gameBoard, play)
        val header = HttpHeaders()
        var objectMapper = ObjectMapper()
        val gameJSON = objectMapper.writeValueAsString(game)

        val requestBuilder = MockMvcRequestBuilders.post(
                "/ticktacktoe/play").accept(MediaType.APPLICATION_JSON)
                .content(gameJSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .headers(header)

        val request = mockMvc.perform(requestBuilder).andReturn()

        val result = objectMapper.readValue(request.response.contentAsString, Game::class.java)

    }

}


