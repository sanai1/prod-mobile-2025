package com.kotleters.mobile.feature.company.presentation.anal

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.ai.data.FIRST_KEY
import com.kotleters.mobile.common.ai.data.SECOND_KEY
import com.kotleters.mobile.common.ai.data.network.model.Message
import com.kotleters.mobile.common.ai.domain.AIRepository
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.company.domain.repository.CompanyRepository
import com.kotleters.mobile.feature.company.presentation.anal.states.AIState
import com.kotleters.mobile.feature.company.presentation.anal.states.AnalListState
import com.kotleters.mobile.feature.company.presentation.anal.states.AnalState
import com.kotleters.mobile.feature.company.presentation.anal.states.CompanyAnalyticsScreenState
import com.kotleters.mobile.feature.company.presentation.anal.states.LacunasState
import com.kotleters.mobile.feature.company.presentation.anal.states.StatsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyAnalScreenViewModel @Inject constructor(
    private val companyRepository: CompanyRepository,
    private val aiRepository: AIRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<CompanyAnalyticsScreenState>(
            CompanyAnalyticsScreenState.Content(
                statsState = StatsState(
                    analListState = AnalListState.Loading,
                    aiState = AIState.Loading
                ),
                currentState = AnalState.STATS,
                lacunasState = LacunasState.OnBoard
            )
        )
    val state = _state.asStateFlow()

    private var analState = mutableStateOf(AnalState.STATS)

    private var currentAIState: AIState = AIState.Loading
    private var currentAnalState: AnalListState = AnalListState.Loading
    private var lacunasState: LacunasState = LacunasState.OnBoard

    private var dataToAnalyze = mutableStateListOf<Lacuna>()

    init {
//        fetchAnal()
        sendMessage()
    }

    fun startGenerate(){
        viewModelScope.launch(Dispatchers.IO) {
            lacunasState = LacunasState.Loading
            updateState()
            getLacunas()
//            generateLacunas()
        }
    }

    private fun generateLacunas() {
        viewModelScope.launch {
            try {
                val result = aiRepository.ChatResponce(
                    Message(
                        "user",
                        buildPrompt(dataToAnalyze),
                    ),
                    apiKey = FIRST_KEY
                )
                val lacunas =
                    parseResponse(result.toString().removePrefix("Success(").removeSuffix(")"))
                lacunasState = LacunasState.Content(
                    lacunas = lacunas
                )
                updateState()
            } catch (e: Exception) {
                lacunasState = LacunasState.Error
                updateState()
            }
        }
    }

    private fun getLacunas() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = companyRepository.getLacunas()
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("RES", result.message)
                }

                is ResponseTemplate.Success -> {
                    dataToAnalyze.clear()
                    dataToAnalyze.addAll(result.data)
                    updateState()
                    generateLacunas()
                    Log.d("RES", result.data.toString())
                }
            }
        }
    }

    fun changeState(new: AnalState) {
        analState.value = new
        updateState()
    }

    private fun sendMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            currentAIState = AIState.Loading
            updateState()
            val result = aiRepository.ChatResponce(
                Message(
                    "user",
                    generateStatisticsPrompt(testData)
                ),
                apiKey = SECOND_KEY
            )
            currentAIState =
                AIState.Content(result.toString().removePrefix("Success(").removeSuffix(")"))
            updateState()
        }
    }

    private fun fetchAnal() {
        viewModelScope.launch(Dispatchers.IO) {
            currentAnalState = AnalListState.Loading
            updateState()
            val result = companyRepository.getStatistic()
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("ERROR", result.message)
                    currentAnalState = AnalListState.Error
                    updateState()
                }

                is ResponseTemplate.Success -> {
                    currentAnalState = AnalListState.Content(result.data)
                    updateState()
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            CompanyAnalyticsScreenState.Content(
                statsState = StatsState(
                    analListState = currentAnalState,
                    aiState = currentAIState
                ),
                currentState = analState.value,
                lacunasState = lacunasState
            )
        }
    }
}