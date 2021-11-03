package com.udacity.shoestore.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionFragmentBinding

class InstructionFragment : Fragment() {

    private lateinit var binding: InstructionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.instruction_fragment,
            container,
            false
        )
        binding.continueInstructionButton.setOnClickListener {
            goToShoesList()
        }
        return binding.root
    }

    private fun goToShoesList() {
        val action = InstructionFragmentDirections.actionInstructionFragmentToShoesListFragment()
        findNavController().navigate(action)
    }
}