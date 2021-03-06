package com.example.testapplication.currencyapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.currencyapp.Interactor
import com.example.testapplication.currencyapp.models.Rates
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModel(
    private val interactor: Interactor
) : ViewModel() {
    private val data = MutableLiveData<List<Rates>>()


    init {
        viewModelScope.launch {
            try {
                interactor.loadFromInteractor()

                Log.e("###", "init bloc")

            } catch (e: Exception) {
                Log.e("huina", "${e.message}")
            }
        }
    }

    fun loadData(map :Map<String,String>): MutableLiveData<List<Rates>> {
        viewModelScope.launch {
            try {
                interactor.loadTest(map)

                interactor.getFromInteractor().collect {
                    data.value = it
                    Log.e("###", "load data")
                }
            } catch (e: Exception) {

                Log.e("getdata", "${e.message}")
            }
        }
        return data
    }


}