package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.util.Log
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

        viewModel.registerResponse.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                Log.d("createflow", it.isSuccess.toString())
                viewModel.logInUser()
            } else {
                Log.d("createflow", it.error.message.toString())
                Toast.makeText(context, "${it.error.message}", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                Log.d("createflow login", it.isSuccess.toString())
                viewModel.updateUser()
            } else {
                Log.d("createflow login", "false")
                Log.d("createflow login", it.error.message.toString())
                Toast.makeText(context, "${it.error.message}", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.updateResponse.observe(viewLifecycleOwner) {
            if (!it.hasErrors()) {
                Log.d("createflow update", it.data?.updateOneUser.toString())
                Toast.makeText(context, "Account created!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signUpStep3Fragment_to_loginFragment)
            } else {
                Log.d("createflow", it.errors.toString())
                Toast.makeText(context, "Account wasn't updated.", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnCreate.setOnClickListener {
            if (binding.checkbox.isChecked) {
                // This method triggers the entire create flow
                viewModel.registerUser()
            } else {
                Toast.makeText(context, "You must accept the terms first.", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}