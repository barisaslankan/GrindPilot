package com.barisaslankan.grindpilot.feature.planning.data.repository

import com.barisaslankan.grindpilot.core.util.FIRESTORE_GOALS
import com.barisaslankan.grindpilot.core.util.FIRESTORE_PLANS
import com.barisaslankan.grindpilot.core.util.FIRESTORE_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRemoteDataSource
import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.model.Plan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanningRemoteDataSourceImpl @Inject constructor(
    val auth : FirebaseAuth,
    val db : FirebaseFirestore
) : PlanningRemoteDataSource {

    val user = auth.currentUser

    override fun fetchGoals(): Flow<Resource<ArrayList<Goal>>> = flow {
        try {
           val result = db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_GOALS).get().await()

            val goals = result.documents.mapNotNull {
                it.toObject(Goal::class.java)
            }

            emit(Resource.Success(ArrayList(goals)))

        }catch (e : Exception){
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))
        }
    }

    override fun fetchPlans(): Flow<Resource<ArrayList<Plan>>> = flow{
        try {
            val result = db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_PLANS).get().await()

            val plans = result.documents.mapNotNull {
                it.toObject(Plan::class.java)
            }

            emit(Resource.Success(ArrayList(plans)))

        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!"))
        }
    }

    override suspend fun uploadPlan(plan : Plan): Resource<Plan> {
        return try {
            db.collection(FIRESTORE_USERS).document(user!!.uid).collection(FIRESTORE_PLANS).document(plan.id).set(plan).await()
            Resource.Success(plan)
        }catch (e: Exception){
            Resource.Error(e.localizedMessage?.toString() ?: "Something went wrong!")
        }
    }
}