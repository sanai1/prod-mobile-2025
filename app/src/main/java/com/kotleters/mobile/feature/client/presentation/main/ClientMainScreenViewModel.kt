package com.kotleters.mobile.feature.client.presentation.main

import androidx.lifecycle.ViewModel
import com.kotleters.mobile.feature.client.domain.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClientMainScreenViewModel @Inject constructor(
    private val clientRepository: ClientRepository
) : ViewModel() {
}