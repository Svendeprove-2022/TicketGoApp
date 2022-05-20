package com.example.ticketgoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.BottomNavigationActivity
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentLoginBinding
import com.example.ticketgoapp.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpStep1Fragment)
        }

        binding.btnLogIn.setOnClickListener {
            val intent = Intent(activity, BottomNavigationActivity::class.java)
            startActivity(intent) // Start BottomNavigationActivity
            activity?.finish() // Kill MainActivity so you can't backnavigate to it
        }

        return binding.root
    }

}