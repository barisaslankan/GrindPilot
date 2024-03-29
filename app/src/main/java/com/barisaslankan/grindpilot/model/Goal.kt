package com.barisaslankan.grindpilot.model

data class Goal(
    val id : String,
    val ownerId : String,
    val name : String,
    val progressType : ProgressType,
    val tasks : ArrayList<String>?,
    //progress çok önemli. goalları kenarda listeleyip plana drag drop yaparken progressi koruyacağız.
    val progress : Double,
    val workTime : String,
    val totalWork : Double,
)
