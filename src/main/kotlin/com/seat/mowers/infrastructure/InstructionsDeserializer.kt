package com.seat.mowers.infrastructure

import com.seat.mowers.domain.*

class InstructionsDeserializer {

    fun deserialize(instructionsText: String): InstructionsPage {
        val rows = instructionsText.split("\n")
        val grassLand = getGrassLand(rows.first())
        val mowers = rows.drop(1)
            .chunked(2)
            .map {
                val mower = Mower(getPosition(it[0]))
                val instructions = getInstructions(it[1])
                Pair(mower,instructions)
            }
        return InstructionsPage(grassLand, mowers)
    }

    private fun getGrassLand(grasLandRow: String): GrassLand {
        return grasLandRow.split(" ")
            .map {it.toInt() }
            .take(2)
            .let { upperLimit ->
                val x = upperLimit[0]
                val y = upperLimit[1]
                GrassLand(upperLimit = Point(x, y))
            }
    }

    private fun getPosition(positionRow: String): Position {
        val position = positionRow.split(" ")
            .take(3)
        val x = position[0].toInt()
        val y = position[1].toInt()
        val orientation = Orientation.valueOf(position[2])
        return Position(
            Point(x, y),
            orientation
        )
    }

    private fun getInstructions(instructionsRow: String): List<Instruction> {
        return instructionsRow.map { Instruction.valueOf(it.toString()) }
    }

}