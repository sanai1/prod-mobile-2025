package com.kotleters.mobile.feature.client.presentation.add_lakuna

import android.util.Log
import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.category.domain.CategoryInfo
import com.kotleters.mobile.common.category.domain.CategoryInfoRepository
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.client.domain.entity.LacunaCreate
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
            amount = "",
            text = "",
            category = null,
            subCategory = null,
            categoryState = CategoryState.Loading
        )
    )
    private var amount = mutableStateOf("")
    private var text = mutableStateOf("")
    private var category = mutableStateOf<CategoryUI?>(null)
    private var subCategory = mutableStateOf<String?>(null)
    private var categoryState: CategoryState = CategoryState.Loading

    val state = _state.asStateFlow()

    init {
        getCategories()
    }

    fun addLacuna() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = clientRepository.createLacuna(
                LacunaCreate(
                    amount = amount.value.toDouble(),
                    text = text.value,
                    categoryId = category.value!!.id
                )
            )
            when(result){
                is ResponseTemplate.Error -> {
                    Log.d("ERR", result.message)
                }
                is ResponseTemplate.Success -> {
                    Log.d("SUC", "SUCCESS")
                }
            }
        }
    }

    fun changeText(new: String) {
        text.value = new
        updateState()
    }

    fun changeCategory(new: CategoryUI) {
        category.value = new
        updateState()
    }

    fun changeSubCategory(new: String) {
        subCategory.value = new
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
                        categories = result.data.groupBy { it.category }
                            .map { (category, categoryInfo) ->
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
                amount = amount.value,
                text = text.value,
                category = category.value,
                subCategory = subCategory.value,
                categoryState = categoryState
            )
        }
    }
}