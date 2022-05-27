package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentSignUpStep2Binding
import com.example.ticketgoapp.viewmodels.SignUpViewModel

class SignUpStep2Fragment : Fragment() {

    private val viewModel: SignUpViewModel by activityViewModels()
    private var _binding: FragmentSignUpStep2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpStep2Binding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpStep2FragmentBackBtn)
        }
        binding.btnContinue.setOnClickListener {
            viewModel.save2(
                binding.phonenumberInput.text.toString(),
                binding.addressInput.text.toString(),
                binding.zipcodeInput.text.toString(),
                binding.cityInput.text.toString(),
                binding.countryInput.text.toString()
            )
            findNavController().navigate(R.id.action_signUpStep2Fragment_to_signUpStep3Fragment)
        }

        return binding.root
    }

}