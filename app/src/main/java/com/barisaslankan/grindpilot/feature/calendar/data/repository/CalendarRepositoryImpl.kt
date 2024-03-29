package com.barisaslankan.grindpilot.feature.calendar.data.repository

import com.barisaslankan.grindpilot.core.util.DB_GOALS
import com.barisaslankan.grindpilot.core.util.DB_PLANS
import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature.calendar.domain.repository.CalendarRepository
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
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

        emit(Resource.Loading())

        try {

            val result = db.collection(DB_USERS).document(user!!.uid).collection(DB_PLANS).get().await()
            val planList = result.documents.mapNotNull {
                it.toObject(Plan::class.java)
            }

            emit(Resource.Success(ArrayList(planList)))

        }catch (e : Exception){

            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))

        }
    }

    override fun getGoals(): Flow<Resource<ArrayList<Goal>>>  = flow{

        emit(Resource.Loading())

        try {

            val result = db.collection(DB_USERS).document(user!!.uid).collection(DB_PLANS).get().await()
            val goalList = result.documents.mapNotNull {
                it.toObject(Goal::class.java)
            }

            emit(Resource.Success(ArrayList(goalList)))

        }catch (e : Exception){

            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))
        }
    }

    override suspend fun uploadGoal(goal : Goal) : Resource<Goal>{

            return try{
                db.collection(DB_USERS)
                    .document(user!!.uid)
                    .collection(DB_GOALS)
                    .document(goal.id)
                    .set(goal)
                    .await()

                Resource.Success(goal)

            }catch (e:Exception){

                Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!")

            }
        }
    }