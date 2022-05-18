package com.example.ticketgoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.databinding.FragmentSignUpStep2Binding

class SignUpStep2Fragment : Fragment() {

    private lateinit var viewModel: SignUpStep2ViewModel
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
            findNavController().navigate(R.id.action_signUpStep2Fragment_to_signUpStep3Fragment)
        }

        return binding.root
    }

}