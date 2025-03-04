package com.kotleters.mobile.feature.client.presentation.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.feature.client.domain.repository.ClientRepository
import com.kotleters.mobile.feature.client.presentation.main.states.ClientMainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientMainScreenViewModel @Inject constructor(
    private val clientRepository: ClientRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ClientMainScreenState>(ClientMainScreenState.Loading)
    val state = _state.asStateFlow()

    private val companies = mutableStateListOf<Company>()

    init {
        fetchCompanies()
    }


    fun fetchCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                ClientMainScreenState.Loading
            }
            val result = clientRepository.getAllOffers()
            when (result) {
                is ResponseTemplate.Error -> {
                    _state.update {
                        Log.d("ERROR", result.message)
                        ClientMainScreenState.Error(result.message)
                    }
                }

                is ResponseTemplate.Success -> {
                    companies.clear()
                    companies.addAll(result.data)
                    updateState()
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            ClientMainScreenState.Content(
                companies = companies,
            )
        }
    }
}