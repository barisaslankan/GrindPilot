package com.barisaslankan.grindpilot.feature_planning.presentation.set_goal

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SetGoalViewModel @Inject constructor(
    private var planningRepository: PlanningRepository
) : ViewModel() {

    private val _state = mutableStateOf(SetGoalState())
    val state : State<SetGoalState> = _state

    fun creteGoal(
        name: String,
        progressType: ProgressType,
        tasks: List<Task>?,
        workTime: String,
        totalWork: Double
    ){
        viewModelScope.launch {
            _state.value = SetGoalState(isLoading = true)
            val result = planningRepository.createGoal(name,progressType,tasks,workTime,totalWork)
            when(result){
                is Resource.Error -> {
                    _state.value = SetGoalState(isLoading = false, error = result.message)
                }
                is Resource.Success -> {
                    withContext(Dispatchers.IO){
                        planningRepository.updateGoals()
                    }
                    _state.value = SetGoalState(isLoading = false, error = null, goal = result.data)
                }
            }
        }
    }

    fun onTaskRemoved(task : Task){
        _state.value = _state.value.copy(tasks = ArrayList(_state.value.tasks - task))
    }
    fun onTaskAdded(task : Task){
        _state.value = _state.value.copy(tasks = ArrayList(_state.value.tasks + task))
    }
    fun onProgressTypeExpandedChanged(isExpanded : Boolean){
        _state.value = _state.value.copy(isProgressTypeExpanded = isExpanded)
    }
    fun onGoalNameChanged(name : String){
        _state.value = _state.value.copy(goalName = name)
    }
    fun onProgressTypeChanged(progressType: ProgressType){
        _state.value = _state.value.copy(progressType = progressType)
    }
    fun onTaskChanged(task : Task){
        _state.value = _state.value.copy(task = task)
    }
    fun onTotalWorkChanged(totalWork : String){
        _state.value = _state.value.copy(totalWork = totalWork.toDouble())
    }
    fun onDisplayedProgressTypeChanged(progressType : String){
        _state.value = _state.value.copy(displayedProgressType = progressType)
    }
    fun onTaskDialogDisplayed(isDisplayed : Boolean){
        _state.value = _state.value.copy(displayTaskDialog = isDisplayed)
    }
    fun onTaskTextChanged(task: String){
        _state.value = _state.value.copy(taskText = task)
    }
}