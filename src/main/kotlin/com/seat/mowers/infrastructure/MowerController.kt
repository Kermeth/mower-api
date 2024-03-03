package com.seat.mowers.infrastructure

import com.seat.mowers.application.MowerDeployer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MowerController {

    val mowerDeployer = MowerDeployer()

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