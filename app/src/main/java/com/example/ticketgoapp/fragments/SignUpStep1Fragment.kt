package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentSignUpStep1Binding
import com.example.ticketgoapp.ticketGoApp
import com.example.ticketgoapp.viewmodels.SignUpViewModel

class SignUpStep1Fragment : Fragment() {

    private val viewModel: SignUpViewModel by activityViewModels()
    private var _binding: FragmentSignUpStep1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpStep1Binding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

//        viewModel.loginUser(ticketGoApp)
//        viewModel.editUser()

        binding.backbtn.setOnClickListener {
//            viewModel.loginUser()
            findNavController().navigate(R.id.action_signUpStep1FragmentBackBtn)
        }

        binding.btnContinue.setOnClickListener {
            viewModel.save1(
                binding.emailaddressInput.text.toString(),
                binding.passwordInput.text.toString(),
                binding.firstnameInput.text.toString(),
                binding.lastnameInput.text.toString()
            )
            findNavController().navigate(R.id.action_signUpStep1Fragment_to_signUpStep2Fragment)
        }

        return binding.root
    }

}