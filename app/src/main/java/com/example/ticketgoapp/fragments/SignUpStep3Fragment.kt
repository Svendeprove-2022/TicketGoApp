package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentSignUpStep3Binding
import com.example.ticketgoapp.viewmodels.SignUpViewModel

class SignUpStep3Fragment : Fragment() {

    private val viewModel: SignUpViewModel by activityViewModels()
    private var _binding: FragmentSignUpStep3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpStep3Binding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpStep3FragmentBackBtn)
        }

        viewModel.registerUser()

        binding.btnCreate2.setOnClickListener {
            viewModel.loginUser()
        }

        binding.btnCreate.setOnClickListener {
            if (binding.checkbox.isChecked) {
                viewModel.updateUser()
            } else {
                Toast.makeText(context, "You must accept the terms first.", Toast.LENGTH_LONG)
                    .show()
            }

            // NAVIGATE
        }

        return binding.root
    }
}