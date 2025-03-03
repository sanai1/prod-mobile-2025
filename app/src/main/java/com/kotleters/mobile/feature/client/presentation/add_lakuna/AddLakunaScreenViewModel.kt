package com.kotleters.mobile.feature.client.presentation.add_lakuna

import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.category.domain.CategoryInfo
import com.kotleters.mobile.common.category.domain.CategoryInfoRepository
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.client.domain.repository.ClientRepository
import com.kotleters.mobile.feature.client.presentation.add_lakuna.states.AddLakunaScreenState
import com.kotleters.mobile.feature.client.presentation.add_lakuna.states.CategoryState
import com.kotleters.mobile.feature.company.presentation.add_offer.states.CategoryUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLakunaScreenViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val categoryInfoRepository: CategoryInfoRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AddLakunaScreenState>(
        AddLakunaScreenState.Content(
            amount = 0.0,
            text = "",
            category = null,
            subCategory = null,
            categoryState = CategoryState.Loading
        )
    )
    private var amount = mutableStateOf("")
    private var text = mutableStateOf("")
    private var category = mutableStateOf<Long?>(null)
    private var subCategory = mutableStateOf<String?>(null)
    private var categoryState: CategoryState = CategoryState.Loading

    val state = _state.asStateFlow()

    init {
        getCategories()
    }

    fun changeText(new: String){
        text.value = new
        updateState()
    }

    fun changeCategory(new: Long) {
        category.value = new
        updateState()
    }

    fun changeAmount(new: String) {
        amount.value = new
        updateState()
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = categoryInfoRepository.getCategories()
            when (result) {
                is ResponseTemplate.Error -> {
                    categoryState = CategoryState.Error
                    updateState()
                }

                is ResponseTemplate.Success -> {
                    categoryState = CategoryState.Content(
                        categories = result.data.groupBy { it.category }.map { (category, categoryInfo)->
                            CategoryUI(
                                id = categoryInfo.first().id,
                                category = category,
                                subCategory = categoryInfo.map { it.subcategory }
                            )
                        }
                    )
                    updateState()
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            AddLakunaScreenState.Content(
                amount = if(amount.value.isEmpty()) 0.0 else amount.value.toDouble(),
                text = text.value,
                category = category.value,
                subCategory = subCategory.value,
                categoryState = categoryState
            )
        }
    }
}