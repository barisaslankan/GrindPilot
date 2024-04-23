package com.barisaslankan.grindpilot.feature_planning.domain.model

data class Plan(
    val id : String = "",
    val ownerId: String = "",
    val name : String = "",
    val goals : ArrayList<Goal> = arrayListOf(),
)
