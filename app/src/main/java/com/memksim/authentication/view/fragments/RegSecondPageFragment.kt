package com.memksim.authentication.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.memksim.authentication.EMAIL_PREFERENCES
import com.memksim.authentication.PREF_USER_EMAIL
import com.memksim.authentication.R
import com.memksim.authentication.databinding.FragmentRegisterSecondPageBinding
import com.memksim.authentication.model.User
import com.memksim.authentication.viewmodel.stateholders.RegSecondPageViewModel

class RegSecondPageFragment: Fragment(R.layout.fragment_register_second_page) {

    private var _binding: FragmentRegisterSecondPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private val args: RegSecondPageFragmentArgs by navArgs()

    private lateinit var viewModel: RegSecondPageViewModel

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterSecondPageBinding.inflate(inflater, container, false)

        preferences = requireActivity().getSharedPreferences(EMAIL_PREFERENCES, Context.MODE_PRIVATE)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        viewModel = ViewModelProvider(this)[RegSecondPageViewModel::class.java]

        if(savedInstanceState == null){
            viewModel.getStateFromFirstPage(args.state)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            navigateBack()
        }

        binding.doneButton.setOnClickListener {
            if (fieldsCorrect()){
                viewModel.setData(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
                putEmailToPref(binding.emailEditText.text.toString())
                viewModel.saveUser()
                navigateToUserPage(viewModel.makeUser())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateBack(){
        navController.navigate(R.id.action_regSecondPageFragment_to_regFirstPageFragment)
    }

    private fun navigateToUserPage(user: User){
        val action = RegSecondPageFragmentDirections.actionRegSecondPageFragmentToUserPageFragment(user)
        navController.navigate(action)
    }

    private fun putEmailToPref(email: String){
        preferences.edit()
            .putString(PREF_USER_EMAIL, email)
            .apply()
    }

    private fun fieldsCorrect(): Boolean{
        val email: Boolean
        val password: Boolean
        val repeatPassword: Boolean
        val passwordsIsEqual: Boolean

        if(binding.emailEditText.text.toString() == ""){
            binding.emailLayout.error = "Field must be filled"
            email = false
        }else{
            binding.emailLayout.error = null
            email = true
        }
        if(binding.passwordEditText.text.toString() == ""){
            binding.passwordLayout.error = "Field must be filled"
            password = false
        }else{
            binding.passwordLayout.error = null
            password = true
        }
        if(binding.repeatPasswordEditText.text.toString() == ""){
            binding.repeatPasswordLayout.error = "Field must be filled"
            repeatPassword = false
        }else{
            binding.repeatPasswordLayout.error = null
            repeatPassword = true
        }

        if(binding.repeatPasswordEditText.text.toString() != binding.passwordEditText.text.toString()){
            binding.passwordLayout.error = "Passwords do not match"
            binding.repeatPasswordLayout.error = "Passwords do not match"
            passwordsIsEqual = false
        }else{
            binding.passwordLayout.error = null
            binding.repeatPasswordLayout.error = null
            passwordsIsEqual = true
        }

        if(email && password && repeatPassword && passwordsIsEqual){
            return true
        }

        return false
    }



}