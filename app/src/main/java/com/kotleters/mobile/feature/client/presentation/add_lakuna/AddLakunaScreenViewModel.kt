package com.kotleters.mobile.feature.client.presentation.add_lakuna

import android.view.View
import androidx.lifecycle.ViewModel
import com.kotleters.mobile.feature.client.domain.repository.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddLakunaScreenViewModel @Inject constructor(
    private val clientRepository: ClientRepository
) : ViewModel() {


}