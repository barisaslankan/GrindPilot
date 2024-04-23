package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.model.Day
import com.barisaslankan.grindpilot.feature_planning.domain.model.DurationType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreatePlanViewModel @Inject constructor(
    private val planningRepository: PlanningRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CreatePlanScreenState())
    val state = _state.asStateFlow()

    init {
        getGoals()
    }

    private fun getGoals(){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            planningRepository.getGoals().collect { result ->
                when(result){
                    is Resource.Success -> {
                        _state.update {
                            it.copy(isLoading = false, error = null, goals = ArrayList(result.data ?: emptyList()))
                        }
                    }
                    is Resource.Error  -> {
                        _state.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                }
            }
        }
    }

    fun createPlan(
        name: String,
        goals: ArrayList<Goal>
    ){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val result = planningRepository.createPlan(name, goals)

            when(result){
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Success -> {
                    withContext(Dispatchers.IO){
                        planningRepository.updatePlans()
                    }
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
                selectedGoals = ArrayList(it.selectedGoals + goal)
            )
        }
    }
    fun removeGoalFromPlan(goal : Goal){
        _state.update {
            it.copy(
                selectedGoals = ArrayList(it.selectedGoals - goal)
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

    fun onDisplayedDurationTypeChanged(displayedDurationType: String){
        _state.update {
            it.copy(displayedDurationType = displayedDurationType)
        }
    }

    fun onDurationTypeExpanded(isExpanded : Boolean){
        _state.update {
            it.copy(isDurationTypeExpanded = isExpanded)
        }
    }

    fun onDurationTypeChanged(durationType: DurationType){
        _state.update {
            it.copy(durationType = durationType)
        }
    }

    fun onDurationTextChanged(durationText : String){
        _state.update {
            it.copy(durationText = durationText)
        }
    }

    fun onDayPicked(day: Day) {
        _state.update { currentState ->
            val updatedSelectedDays = if (currentState.selectedDays.contains(day)) {
                currentState.selectedDays - day
            } else {
                currentState.selectedDays + day
            }
            currentState.copy(selectedDays = ArrayList(updatedSelectedDays))
        }
    }

    fun onBottomSheetExpanded(isExpanded: Boolean){
        _state.update {
            it.copy(isBottomSheetExpanded = isExpanded)
        }
    }

}