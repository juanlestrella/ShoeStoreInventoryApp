package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ViewModelApp

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: ShoeDetailFragmentBinding
    private val viewModel: ViewModelApp by activityViewModels()
    private val activity: MainActivity = MainActivity()
//    private val shoe: Shoe = Shoe("", 0.0, "", "")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShoeDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        //viewModel = ViewModelProvider().get(ViewModelApp::class.java)
        binding.shoeViewModel = viewModel as ViewModelApp

        binding.cancelButton.setOnClickListener {
            goToShoeList()
        }
        binding.submitButton.setOnClickListener {
            if (viewModel.name.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Shoe Name", Toast.LENGTH_LONG).show()
            } else if (viewModel.company.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Company Name", Toast.LENGTH_LONG).show()
            } else if (viewModel.size.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Shoe Size", Toast.LENGTH_LONG).show()
            } else if (viewModel.description.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Description", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addShoes()
                activity.hideKeyBoard()
                goToShoeList()
            }
        }

        return binding.root
    }

    private fun goToShoeList() {
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment()
        findNavController().navigate(action)
    }
}