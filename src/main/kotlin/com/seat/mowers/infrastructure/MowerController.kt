package com.seat.mowers.infrastructure

import com.seat.mowers.application.MowerDeployer
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MowerController {

    val mowerDeployer = MowerDeployer()

    @Operation(
        summary = "Deploy all input mowers and return their final positions.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(mediaType = "text/plain", examples = [ExampleObject("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM")])]
        )
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "Mowers deployed successfully. Returns the final position of each mower.",
            content = [Content(examples = [ExampleObject("1 3 N\n5 1 E")])]
        ),
        ApiResponse(
            responseCode = "400",
            description = "Invalid input format. Please provide a valid input."
        )
    ])
    @PostMapping("/deploy", consumes = ["text/plain"], produces = ["text/plain"])
    fun deployMowers(@RequestBody instructions: String): ResponseEntity<String> {
        val instructionsPage = try {
            InstructionsDeserializer().deserialize(instructions)
        } catch (e: Exception) {
            println("Error at deserialize: ${e.localizedMessage}")
            return ResponseEntity.badRequest().body("Invalid input format. Please provide a valid input.")
        }
        return mowerDeployer.deployMowers(instructionsPage.grassLand, instructionsPage.mowers)
            .joinToString("\n") { "${it.point.x} ${it.point.y} ${it.orientation}" }
                .let { ResponseEntity.ok(it) }
    }

}