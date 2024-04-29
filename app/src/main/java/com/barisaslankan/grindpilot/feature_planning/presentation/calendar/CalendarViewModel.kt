package com.barisaslankan.grindpilot.feature_planning.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_planning.domain.model.Day
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.repository.PlanningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
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
        val today = getCurrentDay()
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
                            _state.update {
                                it.copy(isLoading = false, todaysPlan = todaysPlan)
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