package com.seat.mowers.infrastructure

import com.seat.mowers.domain.GrassLand
import com.seat.mowers.domain.Instruction
import com.seat.mowers.domain.Mower

data class InstructionsPage(
    val grassLand: GrassLand,
    val mowers: List<Pair<Mower, List<Instruction>>>
)
