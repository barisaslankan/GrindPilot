package com.barisaslankan.grindpilot.feature_calendar.data.repository

import com.barisaslankan.grindpilot.core.util.FIRESTORE_GOALS
import com.barisaslankan.grindpilot.core.util.FIRESTORE_PLANS
import com.barisaslankan.grindpilot.core.util.FIRESTORE_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature_calendar.domain.repository.CalendarRepository
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    val auth : FirebaseAuth,
    val db : FirebaseFirestore
) : CalendarRepository {

    val user = auth.currentUser
    override fun getPlans(): Flow<Resource<ArrayList<Plan>>>  = flow{
        try {
            val result = db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_PLANS).get().await()
            val planList = result.documents.mapNotNull {
                it.toObject(Plan::class.java)
            }
            emit(Resource.Success(ArrayList(planList)))
        }catch (e : Exception){
            emit(Resource.Error("Operation Failed: ${e.localizedMessage}"))
        }
    }

    override fun getGoals(): Flow<Resource<ArrayList<Goal>>>  = flow{
        try {
            val result = db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_PLANS).get().await()
            val goalList = result.documents.mapNotNull {
                it.toObject(Goal::class.java)
            }
            emit(Resource.Success(ArrayList(goalList)))
        }catch (e : Exception){
            emit(Resource.Error("Operation Failed: ${e.localizedMessage}"))
        }
    }

    override suspend fun uploadGoal(goal : Goal) : Resource<Goal>{
            return try{
                db.collection(FIRESTORE_USERS)
                    .document(user!!.uid)
                    .collection(FIRESTORE_GOALS)
                    .document(goal.id)
                    .set(goal)
                    .await()
                Resource.Success(goal)
            }catch (e:Exception){
                Resource.Error("Operation Failed: ${e.localizedMessage}")
            }
        }
    }