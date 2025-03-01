package com.kotleters.mobile.feature.company.presentation.main

import androidx.lifecycle.ViewModel
import com.kotleters.mobile.feature.company.domain.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyMainViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
) : ViewModel() {


}