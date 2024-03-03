package com.seat.mowers.domain

class Mower(
    var currentPosition: Position
) {

    fun executeInstruction(instruction: Instruction) {
        when (instruction) {
            Instruction.L -> turnLeft()
            Instruction.R -> turnRight()
            Instruction.M -> moveForward()
        }
    }

    fun nextPosition() : Position {
        return when (currentPosition.orientation) {
            Orientation.N -> {
                currentPosition.copy(
                    point = currentPosition.point.copy(y = currentPosition.point.y + 1)
                )
            }
            Orientation.E -> {
                currentPosition.copy(
                    point = currentPosition.point.copy(x = currentPosition.point.x + 1)
                )
            }
            Orientation.S -> {
                currentPosition.copy(
                    point = currentPosition.point.copy(y = currentPosition.point.y - 1)
                )
            }
            Orientation.W -> {
                currentPosition.copy(
                    point = currentPosition.point.copy(x = currentPosition.point.x - 1)
                )
            }
        }
    }

    private fun turnLeft() {
        currentPosition = when (currentPosition.orientation) {
            Orientation.N -> currentPosition.copy(orientation = Orientation.W)
            Orientation.W -> currentPosition.copy(orientation = Orientation.S)
            Orientation.S -> currentPosition.copy(orientation = Orientation.E)
            Orientation.E -> currentPosition.copy(orientation = Orientation.N)
        }
    }

    private fun turnRight() {
        currentPosition = when (currentPosition.orientation) {
            Orientation.N -> currentPosition.copy(orientation = Orientation.E)
            Orientation.E -> currentPosition.copy(orientation = Orientation.S)
            Orientation.S -> currentPosition.copy(orientation = Orientation.W)
            Orientation.W -> currentPosition.copy(orientation = Orientation.N)
        }
    }

    private fun moveForward() {
        currentPosition = nextPosition()
    }
}