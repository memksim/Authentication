package com.memksim.authentication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memksim.authentication.R
import com.memksim.authentication.databinding.FragmentRegisterSecondPageBinding
import com.memksim.authentication.databinding.FragmentUserPageBinding

class UserPageFragment: Fragment(R.layout.fragment_user_page) {

    private var _binding: FragmentUserPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}