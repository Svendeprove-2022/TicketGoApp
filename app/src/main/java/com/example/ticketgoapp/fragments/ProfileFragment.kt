package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ticketgoapp.databinding.FragmentProfileBinding
import com.example.ticketgoapp.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel.getUserData().observe(viewLifecycleOwner) {
            binding.tvHelloUser.text = it.data?.user?.first_name
            binding.firstnameInput.setText(it.data?.user?.first_name)
            binding.lastnameInput.setText(it.data?.user?.last_name)
            binding.emailaddressInput.setText(it.data?.user?.email)
        }

        return binding.root
    }

}