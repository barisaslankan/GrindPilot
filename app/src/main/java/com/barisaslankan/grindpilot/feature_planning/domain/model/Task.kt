package com.barisaslankan.grindpilot.feature_planning.domain.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

data class Task(
    val id : String = "",
    val goalName : String = "",
    val taskName : String = "",
    val weight : Double = 0.0
){
    companion object {
        private val moshi = Moshi.Builder().build()
        private val adapter: JsonAdapter<List<Task>> = moshi.adapter(
            Types.newParameterizedType(List::class.java, Task::class.java)
        )
        fun toJson(tasks: List<Task>): String {
            return adapter.toJson(tasks)
        }
        fun fromJson(json: String): List<Task>? {
            return adapter.fromJson(json)
        }
    }
}
