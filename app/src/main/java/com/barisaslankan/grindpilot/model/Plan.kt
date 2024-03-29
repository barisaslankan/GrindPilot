package com.barisaslankan.grindpilot.model

data class Plan(
    val id : String,
    val ownerId: String,
    val name : String,
    val goals : ArrayList<Goal>,
)
