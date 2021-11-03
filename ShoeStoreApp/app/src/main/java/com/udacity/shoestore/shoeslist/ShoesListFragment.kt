package com.udacity.shoestore.shoeslist

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoesListFragmentBinding
import com.udacity.shoestore.viewmodel.ViewModelApp

class ShoesListFragment : Fragment() {

    private lateinit var binding: ShoesListFragmentBinding

    // needs lifescycleowner to be activity scope, and not fragment scope
    private val viewModel: ViewModelApp by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShoesListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        binding.floatingActionButton.setOnClickListener {
            goToShoeDetail()
        }
        //viewModel = ViewModelProvider(this).get(ViewModelApp::class.java)
        binding.shoeViewModel = viewModel as ViewModelApp

        // add new textview for each new shoes
        (viewModel as ViewModelApp).shoesList.observe(viewLifecycleOwner, Observer {
            it.forEach { shoe ->
                val myTextView = TextView(context)
                val content = "Shoe Name: ${shoe.name}" +
                        "\nShoe Size: ${shoe.size.toString()}" +
                        "\nCompany: ${shoe.company}" +
                        "\nDescription: ${shoe.description}" +
                        "\n"
                myTextView.text = content
                myTextView.textSize = 24F
                binding.linearLayoutShoeList.addView(myTextView)
            }
        })

        // to make the menu visible
        setHasOptionsMenu(true)
        // Back button
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            goBackToLogIn()
        }

        return binding.root
    }

    // Navigation
    private fun goToShoeDetail() {
        val action = ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment()
        findNavController().navigate(action)
    }

    // Menu
    private fun goBackToLogIn() {
        viewModel.clear()
        // clear backstack and navigate to login screen
        findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToLogInFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // inflate with the log out menu
        inflater.inflate(R.menu.log_out_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out_menu -> goBackToLogIn()
        }
        return super.onOptionsItemSelected(item)
    }
}
