package com.barisaslankan.grindpilot.feature.planning.presentation.create_plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRepository
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePlanViewModel @Inject constructor(
    private val planningRepository: PlanningRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CreatePlanScreenState())
    val state = _state.asStateFlow()

    init {
        fetchGoals()
    }

    private fun fetchGoals(){

        planningRepository.fetchGoals().onEach { result ->

            when(result){
                is Resource.Success -> {
                    _state.update {
                        it.copy(isLoading = false, error = null, goals = result.data ?: arrayListOf())
                    }
                }
                is Resource.Error  -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun uploadPlan(plan : Plan){

        viewModelScope.launch {

            _state.update {
                it.copy(isLoading = true)
            }

            val result = planningRepository.uploadPlan(plan)

            when(result){
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(isLoading = false, error = null)
                    }
                }
            }
        }
    }

    fun addGoalToPlan(goal : Goal){
        _state.update {
            it.copy(
                goals = ArrayList(it.selectedGoals + goal)
            )
        }
    }

    fun removeGoalFromPlan(goal : Goal){
        _state.update {
            it.copy(
                goals = ArrayList(it.selectedGoals - goal)
            )
        }
    }

    fun onPlanNameChanged(name : String){
        _state.update {
            it.copy(
                name = name
            )
        }
    }

}