package com.memksim.authentication.view.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.memksim.authentication.APP_TAG
import com.memksim.authentication.EMAIL_PREFERENCES
import com.memksim.authentication.PREF_USER_EMAIL
import com.memksim.authentication.R
import com.memksim.authentication.databinding.FragmentAuthPageBinding
import com.memksim.authentication.model.User
import com.memksim.authentication.viewmodel.stateholders.AuthPageViewModel

class AuthPageFragment: Fragment(R.layout.fragment_auth_page) {

    private var _binding: FragmentAuthPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var viewModel: AuthPageViewModel

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthPageBinding.inflate(inflater, container, false)

        preferences = requireActivity().getSharedPreferences(EMAIL_PREFERENCES, Context.MODE_PRIVATE)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        viewModel = ViewModelProvider(this)[AuthPageViewModel::class.java]
        if(savedInstanceState == null){
            viewModel.loadUsers()
        }
        findUser()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getStartedButton.setOnClickListener {
            navigateBecomeMember()
        }

        binding.loginButton.setOnClickListener {
            logIn()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateBecomeMember(){
        navController.navigate(R.id.action_authPageFragment_to_regFirstPageFragment)
    }

    private fun logIn(){
        val resultObject = viewModel.compareUsers(binding.emailEditText.text.toString())

        if(resultObject.result){
            binding.emailLayout.error = null
            binding.passwordLayout.error = null
            saveUserToPref()
            navigateToUserPage(resultObject.user!!)
        }else{
            binding.emailLayout.error = "Invalid email or password"
            binding.passwordLayout.error = "Invalid email or password"
        }
    }

    private fun findUser(){
        val resultObj = viewModel.letIn(preferences.getString(PREF_USER_EMAIL, "") ?: "none")
        if(resultObj.result ){
            navigateToUserPage(resultObj.user!!)
        }
    }

    private fun navigateToUserPage(user: User) {
        val action = AuthPageFragmentDirections.actionAuthPageFragmentToUserPageFragment(user)
        navController.navigate(action)
    }

    private fun saveUserToPref(){
        preferences.edit()
            .putString(PREF_USER_EMAIL, binding.emailEditText.text.toString())
            .apply()
    }
}