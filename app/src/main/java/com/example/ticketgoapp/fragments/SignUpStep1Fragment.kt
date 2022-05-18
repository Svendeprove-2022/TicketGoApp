package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentSignUpStep1Binding
import com.example.ticketgoapp.viewmodels.SignUpStep1ViewModel

class SignUpStep1Fragment : Fragment() {

    private lateinit var viewModel: SignUpStep1ViewModel
    private var _binding: FragmentSignUpStep1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpStep1Binding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_signUpStep1Fragment_to_signUpStep2Fragment)
        }

        return binding.root
    }

}