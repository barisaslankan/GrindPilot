package com.barisaslankan.grindpilot.feature.calendar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.calendar.domain.repository.CalendarRepository
import com.barisaslankan.grindpilot.core.model.Goal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val calendarRepository : CalendarRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state = _state.asStateFlow()

    init {
        fetchPlans()
    }

     private fun fetchPlans(){
         _state.update {
             it.copy(isLoading = true)
         }
         calendarRepository.getPlans().onEach {result ->
             when(result) {
                 is Resource.Error -> {
                     _state.update {
                         it.copy(isLoading = false, error = result.message)
                     }
                 }
                 is Resource.Success -> {
                     _state.update {
                         it.copy(isLoading = false, error = null, plans = result.data)
                     }
                 }
             }
         }.launchIn(viewModelScope)
    }
}