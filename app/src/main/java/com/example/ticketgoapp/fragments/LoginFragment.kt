package com.example.ticketgoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ticketgoapp.BottomNavigationActivity
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentLoginBinding
import com.example.ticketgoapp.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
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

        viewModel.loginReponse.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(activity, BottomNavigationActivity::class.java)
                startActivity(intent) // Start BottomNavigationActivity
                activity?.finish() // Kill MainActivity so you can't navigate back to it
            } else {
                Toast.makeText(context, "Email or password was incorrect", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnLogIn.setOnClickListener {
            if (binding.emailaddressInput.text.isNotBlank() && binding.passwordInput.text.isNotBlank()) {
                viewModel.loginUser(
                    binding.emailaddressInput.text.toString(),
                    binding.passwordInput.text.toString()
                )
            } else {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}