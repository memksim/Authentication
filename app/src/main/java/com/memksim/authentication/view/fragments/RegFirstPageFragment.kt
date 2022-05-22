package com.memksim.authentication.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.memksim.authentication.APP_TAG
import com.memksim.authentication.R
import com.memksim.authentication.databinding.FragmentAuthPageBinding
import com.memksim.authentication.databinding.FragmentRegisterFirstPageBinding
import com.memksim.authentication.viewmodel.stateholders.RegFirstPageViewModel

class RegFirstPageFragment: Fragment(R.layout.fragment_register_first_page) {

    private var _binding: FragmentRegisterFirstPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var viewModel: RegFirstPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterFirstPageBinding.inflate(inflater, container, false)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        viewModel = ViewModelProvider(this)[RegFirstPageViewModel::class.java]
        viewModel.livedata.observe(viewLifecycleOwner, Observer {

        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            navigateBack()
        }

        binding.doneButton.setOnClickListener {
            if(checkFields()){
                viewModel.setData(
                    binding.nameEditText.text.toString(),
                    binding.surnameEditText.text.toString(),
                    binding.phoneEditText.text.toString(),
                    binding.cityEditText.text.toString(),
                )
                navigateNext()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateBack(){
        navController.navigate(R.id.action_regFirstPageFragment_to_authPageFragment)
    }

    private fun navigateNext(){
        val action = RegFirstPageFragmentDirections
            .actionRegFirstPageFragmentToRegSecondPageFragment(viewModel.livedata.value!!)
        navController.navigate(action)
    }

    private fun checkFields(): Boolean{
        val name: Boolean
        val surname: Boolean
        val phone: Boolean

        if(binding.nameEditText.text.toString() == ""){
            binding.nameLayout.error = "Field must be filled"
            name = false
        }else{
            binding.nameLayout.error = null
            name = true
        }
        if(binding.surnameEditText.text.toString() == ""){
            binding.surnameLayout.error = "Field must be filled"
            surname = false
        }else{
            binding.surnameLayout.error = null
            surname = true
        }
        if(binding.phoneEditText.text.toString() == ""){
            binding.phoneLayout.error = "Field must be filled"
            phone = false
        }else{
            binding.phoneLayout.error = null
            phone = true
        }

        if(name && surname && phone){
            return true
        }

        return false
    }


}