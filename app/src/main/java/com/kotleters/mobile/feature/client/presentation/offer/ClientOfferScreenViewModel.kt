package com.kotleters.mobile.feature.client.presentation.offer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.client.domain.repository.ClientGenerateQRRepository
import com.kotleters.mobile.feature.client.presentation.offer.states.CodeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientOfferScreenViewModel @Inject constructor(
    private val clientGenerateQRRepository: ClientGenerateQRRepository
) : ViewModel() {

    private val _codeState = MutableStateFlow<CodeState>(CodeState.Lading)
    val codeState = _codeState.asStateFlow()

    fun generateQRCode(offerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = clientGenerateQRRepository.clientGenerateQRRepository(offerId)
            when(result){
                is ResponseTemplate.Error -> {
                    _codeState.update {
                        Log.d("ERROR", result.message)
                        CodeState.Error
                    }
                }
                is ResponseTemplate.Success -> {
                    Log.d("RESULT", result.data)
                    _codeState.update {
                        val QRBitmap = generateQRCodeImage(result.data)
                        CodeState.Content(QRBitmap)
                    }
                }
            }
        }
    }
}