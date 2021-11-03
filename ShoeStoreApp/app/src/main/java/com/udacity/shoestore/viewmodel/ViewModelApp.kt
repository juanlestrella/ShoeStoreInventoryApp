package com.udacity.shoestore.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ViewModelApp : ViewModel() {
    // stores the list of shoes
    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList

//    private val _name = MutableLiveData<String>()
//    val name: LiveData<String>
//        get() = _name
//
//    private val _company = MutableLiveData<String>()
//    val company: LiveData<String>
//        get() = _company
//
//    private val _size = MutableLiveData<Double>()
//    val size: LiveData<Double>
//        get() = _size
//
//    private val _description = MutableLiveData<String>()
//    val description: LiveData<String>
//        get() = _description
    var name = ""
    var company = ""
    var size = ""
    var description = ""

    init {
        _shoesList.value = mutableListOf<Shoe>()
    }

    fun addShoes(){
        _shoesList.value?.add(Shoe(name, size.toDouble(), company, description))
    }

    fun clear() {
        _shoesList.value?.clear()
    }

//    @BindingAdapter("convertToInt")
//    fun convertToInt(view: EditText, size: Int){
//        this.size = size ?: 0
//    }
}