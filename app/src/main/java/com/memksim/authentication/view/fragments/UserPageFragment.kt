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
import com.memksim.authentication.databinding.FragmentUserPageBinding
import com.memksim.authentication.view.adapters.UsesListAdapter
import com.memksim.authentication.viewmodel.stateholders.AuthPageViewModel
import com.memksim.authentication.viewmodel.stateholders.UserPageViewModel

class UserPageFragment: Fragment(R.layout.fragment_user_page) {

    private var _binding: FragmentUserPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private val args: UserPageFragmentArgs by navArgs()

    private lateinit var viewModel: UserPageViewModel

    private lateinit var adapter: UsesListAdapter

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPageBinding.inflate(inflater, container, false)

        preferences = requireActivity().getSharedPreferences(EMAIL_PREFERENCES, Context.MODE_PRIVATE)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        viewModel = ViewModelProvider(this)[UserPageViewModel::class.java]
        if(savedInstanceState == null){
            viewModel.setState(args.user)
        }

        adapter = UsesListAdapter(viewModel.getUsesList())
        binding.usesList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()

        binding.logOutButton.setOnClickListener {
            logOut()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserInfo(){
        binding.userNameText.text = viewModel.liveData.value!!.user.name
        binding.userSurnameText.text = viewModel.liveData.value!!.user.surname
        binding.phoneText.text = viewModel.liveData.value!!.user.phoneNumber
        binding.emailText.text = viewModel.liveData.value!!.user.email


    }

    private fun logOut(){
        outUser()
        navigateToAuthPage()
    }

    private fun outUser(){
        preferences.edit()
            .remove(PREF_USER_EMAIL)
            .apply()
    }

    private fun navigateToAuthPage(){
        navController.navigate(R.id.action_userPageFragment_to_authPageFragment)
    }
}