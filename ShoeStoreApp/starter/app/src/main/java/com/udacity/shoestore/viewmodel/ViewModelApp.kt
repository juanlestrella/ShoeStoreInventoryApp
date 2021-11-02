package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ViewModelApp : ViewModel() {
    // stores the list of shoes
    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    init {
        _shoesList.value = mutableListOf<Shoe>()
    }

    // add a new shoe to the shoe list
    fun addShoe(
        name: String, size: Double, company: String, description: String,
        images: List<String> = mutableListOf()
    ) {
        _shoesList.value?.add(Shoe(name, size, company, description, images))
    }

    fun clear() {
        _shoesList.value?.clear()
    }
}