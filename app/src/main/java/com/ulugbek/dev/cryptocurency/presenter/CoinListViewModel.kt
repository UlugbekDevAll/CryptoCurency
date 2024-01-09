package com.ulugbek.dev.cryptocurency.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulugbek.dev.cryptocurency.common.Resource
import com.ulugbek.dev.cryptocurency.common.UiEvent
import com.ulugbek.dev.cryptocurency.domain.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinRepository: CoinRepository
):ViewModel() {

    private val _coinList=MutableStateFlow<UiEvent>(UiEvent.Empty)
    val coinList:StateFlow<UiEvent> get()=_coinList

    fun getCoinList(){
        viewModelScope.launch {
            _coinList.value=UiEvent.Loading

            when(val resource= coinRepository.getCoinList()){
                is Resource.Error -> {
                    _coinList.value=UiEvent.Error(resource.message)

                }
                is Resource.Success -> {
                    _coinList.value=UiEvent.Success(resource.data)

                }
            }
        }
    }
}