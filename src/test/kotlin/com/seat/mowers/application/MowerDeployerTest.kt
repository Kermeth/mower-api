package com.seat.mowers.application

import com.seat.mowers.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import reactor.core.publisher.MonoSink

class MowerDeployerTest{


    private val mowerDeployer = MowerDeployer()

    @Test
    fun testDeployMower(){
        val grassLand = GrassLand(Point(5, 5))
        val mower = Mower(Position(Point(1, 2), Orientation.N))
        val instructions = listOf(
            Instruction.L,
            Instruction.M,
            Instruction.L,
            Instruction.M,
            Instruction.L,
            Instruction.M,
            Instruction.L,
            Instruction.M,
            Instruction.M,
        )
        val lastPosition = mowerDeployer.deployMower(grassLand, mower, instructions)
        assertEquals(Position(Point(1,3),Orientation.N), lastPosition)
    }

    @Test
    fun testDeployMower2(){
        val grassLand = GrassLand(Point(5, 5))
        val mower = Mower(Position(Point(3, 3), Orientation.E))
        val instructions = listOf(
            Instruction.M,
            Instruction.M,
            Instruction.R,
            Instruction.M,
            Instruction.M,
            Instruction.R,
            Instruction.M,
            Instruction.R,
            Instruction.R,
            Instruction.M,
        )
        val lastPosition = mowerDeployer.deployMower(grassLand, mower, instructions)
        assertEquals(Position(Point(5,1),Orientation.E), lastPosition)
    }

}