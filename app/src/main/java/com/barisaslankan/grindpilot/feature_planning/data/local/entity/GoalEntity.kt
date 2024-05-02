package com.barisaslankan.grindpilot.feature_planning.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barisaslankan.grindpilot.core.util.GOALS_TABLE
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@Entity(tableName = GOALS_TABLE)
data class GoalEntity(
    @PrimaryKey
    val id : String = "",
    val name : String = "",
    val progressType : String = ProgressType.HOURS.name,
    val tasks : String? = "",
    val current : Double = 0.0,
    val workTime : String = "",
    val totalWork : Double = 0.0,
) {
    companion object {
        private val moshi = Moshi.Builder().build()
        private val adapter: JsonAdapter<GoalEntity> = moshi.adapter(GoalEntity::class.java)

        fun toJson(goalEntity: GoalEntity): String {
            return adapter.toJson(goalEntity)
        }

        fun fromJson(json: String): GoalEntity? {
            return adapter.fromJson(json)
        }
    }
}
