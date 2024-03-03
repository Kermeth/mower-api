package com.seat.mowers.infrastructure

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class MowerControllerTest{

    @Autowired
    lateinit var mowerController: MowerController

    @Test
    fun testController(){
        val instructions = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()
        WebTestClient
            .bindToController(mowerController)
            .build()
            .post()
            .uri("/deploy")
            .contentType(MediaType.TEXT_PLAIN)
            .bodyValue(instructions)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .isEqualTo("""
                1 3 N
                5 1 E
            """.trimIndent())
    }

}