package com.example.ticketgoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.databinding.FragmentSignUpStep3Binding

class SignUpStep3Fragment : Fragment() {

    private lateinit var viewModel: SignUpStep3ViewModel
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

        return binding.root
    }

}