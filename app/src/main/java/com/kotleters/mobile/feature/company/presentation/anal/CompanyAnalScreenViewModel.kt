package com.kotleters.mobile.feature.company.presentation.anal

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth
import com.kotleters.mobile.feature.company.domain.repository.StatisticRepository
import com.kotleters.mobile.feature.company.presentation.anal.states.CompanyAnalyticsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyAnalScreenViewModel @Inject constructor(
    private val statisticRepository: StatisticRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<CompanyAnalyticsScreenState>(CompanyAnalyticsScreenState.Loading)
    val state = _state.asStateFlow()

    val analList = mutableStateListOf<StatisticByMonth>()

    init {
        fetchAnal()
    }

    fun fetchAnal() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = statisticRepository.getStatisticByMonth()
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("ERROR", result.message)
                }

                is ResponseTemplate.Success -> {
                    Log.d("RESULT", result.data.toString())
                    analList.clear()
                    analList.addAll(result.data)
                }
            }
        }
    }
}