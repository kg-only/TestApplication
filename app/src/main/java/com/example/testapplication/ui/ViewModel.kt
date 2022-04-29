package com.example.testapplication.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.Interactor
import com.example.testapplication.models.Rates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModel(private val interactor: Interactor) : ViewModel() {
    val data = MutableLiveData<List<Rates>>()
    val dataDb = MutableLiveData<List<Rates>>()

  init{
        viewModelScope.launch {
            try {
                data.value = interactor.loadFromInteractor()
            } catch (e: Exception) {
                Log.e("huina", "${e.message}")
            }
        }
    }

    fun getData() {
        viewModelScope.launch {
            try {
                interactor.getFromInteractor().collect {
                    dataDb.value = it
                }
            } catch (e: Exception) {
                Log.e("getdata", "${e.message}")
            }
        }
    }
}