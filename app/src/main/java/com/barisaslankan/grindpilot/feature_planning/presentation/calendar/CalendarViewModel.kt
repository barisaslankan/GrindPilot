package com.barisaslankan.grindpilot.feature_planning.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_planning.domain.model.Day
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRepository
import com.kizitonwose.calendar.core.CalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val planningRepository: PlanningRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CalendarState())
    val state = _state.asStateFlow()

    init {
        getDailyPlan()
    }

    private fun getDailyPlan(){
        val today = getCurrentDay().toString().substring(0,2).uppercase(Locale.getDefault())
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            planningRepository.getPlans().collect{result ->
                when(result){
                    is Resource.Success -> {
                        val plans = result.data
                        plans?.let {
                            val todaysPlan = it.firstOrNull {plan ->
                                plan.days.contains(today)
                            }
                            _state.update {state ->
                                state.copy(isLoading = false, todaysPlan = todaysPlan, plans = ArrayList(plans))
                            }
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                }
            }
        }
    }

    fun calculateProgress(goal : Goal) : Float{
        return (goal.totalWork / goal.current).toFloat()
    }

    fun onTaskCheckedChanged(isChecked : Boolean, task : Task, goal : Goal){
        viewModelScope.launch {
            if(isChecked){
                planningRepository.updateGoalProgress(goalId = goal.id, current = goal.current + task.weight)
            }else {
                planningRepository.updateGoalProgress(goalId = goal.id, current = goal.current - task.weight)
            }
            planningRepository.updateGoals()
        }
    }

    fun onCalendarDayClicked(calendarDay : CalendarDay){
        val selectedDay = calendarDay.date.dayOfWeek.toString().uppercase(Locale.getDefault())
        _state.value.plans?.let {
            val selectedPlan = it.firstOrNull{plan ->
                plan.days.contains(selectedDay)
            }
            _state.update {state ->
                state.copy(isLoading = false, todaysPlan = selectedPlan)
            }
        }
    }

    private fun getCurrentDay(): Day {
        val today = LocalDate.now().dayOfWeek
        return when (today) {
            DayOfWeek.MONDAY -> Day.MON
            DayOfWeek.TUESDAY -> Day.TUE
            DayOfWeek.WEDNESDAY -> Day.WED
            DayOfWeek.THURSDAY -> Day.THU
            DayOfWeek.FRIDAY -> Day.FRI
            DayOfWeek.SATURDAY -> Day.SAT
            DayOfWeek.SUNDAY -> Day.SUN
        }
    }
}