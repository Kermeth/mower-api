package com.seat.mowers.domain

class GrassLand(
    private val upperLimit: Point,
    private val lowerLimit: Point = Point(0, 0),
    private val customInvalidPoints: List<Point> = emptyList()
) {

    private fun isValidPosition(point: Point): Boolean {
        if (customInvalidPoints.contains(point)) {
            return false
        }
        return point.x in lowerLimit.x..upperLimit.x && point.y in lowerLimit.y..upperLimit.y
    }

    fun runMower(mower: Mower, instructions: List<Instruction>): Position {
        instructions.forEach {instruction ->
            println("Current position: ${mower.currentPosition} Instruction: $instruction")
            if(instruction == Instruction.M && !isValidPosition(mower.nextPosition().point)){
                println("Invalid position: ${mower.nextPosition().point}. Not moving")
            }
            mower.executeInstruction(instruction)
        }
        return mower.currentPosition
    }

}
