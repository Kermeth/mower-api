package com.seat.mowers.application

import com.seat.mowers.domain.GrassLand
import com.seat.mowers.domain.Instruction
import com.seat.mowers.domain.Mower
import com.seat.mowers.domain.Position

class MowerDeployer {

    fun deployMower(grassLand: GrassLand,
                    mower: Mower,
                    instructions: List<Instruction>
    ): Position {
        return grassLand.runMower(mower, instructions)
    }

    fun deployMowers(grassLand: GrassLand,
                     mowers: List<Pair<Mower, List<Instruction>>>
    ): List<Position> {
        return mowers.map { (mower, instructions) ->
            deployMower(grassLand, mower, instructions)
        }
    }

}