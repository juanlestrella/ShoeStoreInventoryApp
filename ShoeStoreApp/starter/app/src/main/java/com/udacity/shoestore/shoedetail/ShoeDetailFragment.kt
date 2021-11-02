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
import com.udacity.shoestore.viewmodel.ViewModelApp

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: ShoeDetailFragmentBinding
    private val viewModel: ViewModelApp by activityViewModels()
    private val activity: MainActivity = MainActivity()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail_fragment,
            container,
            false
        )
        //viewModel = ViewModelProvider().get(ViewModelApp::class.java)
        binding.shoeViewModel = viewModel as ViewModelApp

        binding.cancelButton.setOnClickListener {
            goToShoeList()
        }
        binding.submitButton.setOnClickListener {
            val name = binding.shoeNameEditText.text.toString()
            val size = binding.shoeSizeTextView.text.toString()
            val company = binding.companyEditText.text.toString()
            val description = binding.descriptionDetailTextView.text.toString()

            if (name.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Shoe Name", Toast.LENGTH_LONG).show()
            } else if (company.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Company Name", Toast.LENGTH_LONG).show()
            } else if (size.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Shoe Size", Toast.LENGTH_LONG).show()
            } else if (description.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Description", Toast.LENGTH_LONG).show()
            } else {
                (viewModel as ViewModelApp).addShoe(name, size.toDouble(), company, description)
                goToShoeList()
            }
            activity.hideKeyBoard()
        }

        return binding.root
    }

    private fun goToShoeList() {
        val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment()
        findNavController().navigate(action)
    }
}