package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LogInFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = LoginFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.signUpButton.setOnClickListener {
            goToWelcome()
        }
        binding.logInButton.setOnClickListener {
            goToWelcome()
        }

        return binding.root
    }

    private fun goToWelcome() {
        val action = LogInFragmentDirections.actionLogInFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }
}