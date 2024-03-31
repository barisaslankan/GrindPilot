package com.barisaslankan.grindpilot.feature.planning.presentation.plans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PlansViewModel @Inject constructor(
    private val planningRepository: PlanningRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlansScreenState())
    val state = _state.asStateFlow()

    init {
        fetchPlans()
    }

    private fun fetchPlans(){
        _state.update {
            it.copy(isLoading = true)
        }
        planningRepository.fetchPlans().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.update {
                        it.copy(isLoading = false, error = null, plans = result.data)
                    }
                }
                is Resource.Error  -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}