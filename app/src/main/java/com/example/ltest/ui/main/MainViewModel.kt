package com.example.ltest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.ltest.domain.usecases.GetPagedSearchResultsuseCase
import com.example.ltest.domain.usecases.GetRandomGifUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val getRandomGifUseCase: GetRandomGifUseCase,
        private val getPagedSearchResultsuseCase: GetPagedSearchResultsuseCase
) : ViewModel() {

    fun getRandomGif() =
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                getRandomGifUseCase.invoke().collect {
                    emit(it)
                }
            }

    fun getGifSearchResults(query: String) = getPagedSearchResultsuseCase.invoke(query)

}