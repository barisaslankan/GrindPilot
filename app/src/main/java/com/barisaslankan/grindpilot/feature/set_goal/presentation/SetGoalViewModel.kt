package com.barisaslankan.grindpilot.feature.set_goal.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.set_goal.domain.repository.SetGoalRepository
import com.barisaslankan.grindpilot.model.ProgressType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetGoalViewModel @Inject constructor(
    private var setGoalRepository: SetGoalRepository
) : ViewModel() {

    private val _state = mutableStateOf(SetGoalState())
    val state : State<SetGoalState> = _state

    fun creteGoal(
        name: String,
        progressType: ProgressType,
        tasks: ArrayList<String>?,
        workTime: String,
        totalWork: Double
    ){
        viewModelScope.launch {
            _state.value = SetGoalState(isLoading = true)
            val result = setGoalRepository.createGoal(name,progressType,tasks,workTime,totalWork)
            when(result){
                is Resource.Error -> {
                    _state.value = SetGoalState(isLoading = false, error = result.message)
                }
                is Resource.Success -> {
                    _state.value = SetGoalState(isLoading = false, error = null, goal = result.data)
                }
            }
        }
    }

    fun onTaskRemoved(task : String){
        state.value.tasks.remove(task)
    }
    fun onTaskAdded(task : String){
        state.value.tasks.add(task)
    }
    fun onProgressTypeExpandedChanged(isExpanded : Boolean){
        _state.value = SetGoalState(isProgressTypeExpanded = isExpanded)
    }
    fun onGoalNameChanged(name : String){
        _state.value = SetGoalState(goalName = name)
    }
    fun dismissTimePicker(isExpanded : Boolean){
        _state.value = SetGoalState(isTimePickerExtended = isExpanded)
    }
    fun onProgressTypeChanged(progressType: ProgressType){
        _state.value = SetGoalState(progressType = progressType)
    }
    fun onTaskChanged(task : String){
        _state.value = SetGoalState(task = task)
    }
    fun onTimePicked(hour : String, minute : String){
        _state.value = SetGoalState(workTime = "$hour:$minute")
    }
    fun onTotalWorkChanged(totalWork : String){
        _state.value = SetGoalState(totalWork = totalWork.toDouble())
    }
}