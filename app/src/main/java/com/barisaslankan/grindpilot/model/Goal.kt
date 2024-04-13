package com.barisaslankan.grindpilot.model

data class Goal(
    val id : String = "",
    val ownerId : String = "",
    val name : String = "",
    val progressType : ProgressType = ProgressType.HOURS,
    val tasks : ArrayList<String>? = null,
    //progress çok önemli. goalları kenarda listeleyip plana drag drop yaparken progressi koruyacağız.
    val progress : Double = 0.0,
    val workTime : String = "",
    val totalWork : Double = 0.0,
)
