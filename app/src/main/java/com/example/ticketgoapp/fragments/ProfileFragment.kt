package com.example.ticketgoapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ticketgoapp.MainActivity
import com.example.ticketgoapp.R
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogOut.setOnClickListener {
            viewModel.logOutUser()

            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent) // Start MainActivity
            activity?.finish() // Kill BottomNavigationActivity so you can't navigate back to it
        }

        viewModel.getUserData().observe(viewLifecycleOwner) {
            Log.d("response", it.data.toString())
            if (it.data?.user != null) {
                binding.tvHelloUser.text = it.data?.user?.first_name
                binding.firstnameInput.setText(it.data?.user?.first_name)
                binding.lastnameInput.setText(it.data?.user?.last_name)
                binding.emailaddressInput.setText(it.data?.user?.email)
                binding.phonenumberInput.setText(it.data?.user?.mobile)
                binding.addressInput.setText(it.data?.user?.address)
                binding.zipcodeInput.setText(it.data?.user?.zip_code)
                binding.cityInput.setText(it.data?.user?.city)
                binding.countryInput.setText(it.data?.user?.country)
            } else {
                Toast.makeText(context, "Couln't retrieve user info", Toast.LENGTH_LONG).show()
            }
        }

        var clicked = 0

        binding.btnEdit.setOnClickListener {
            clicked++
            if (clicked % 2 != 0) { // Odd
                binding.firstnameInput.isEnabled = true
                binding.lastnameInput.isEnabled = true
                binding.phonenumberInput.isEnabled = true
                binding.addressInput.isEnabled = true
                binding.zipcodeInput.isEnabled = true
                binding.cityInput.isEnabled = true
                binding.countryInput.isEnabled = true
                binding.btnEdit.text = getString(R.string.btn_save)
            } else { // Even
                binding.firstnameInput.isEnabled = false
                binding.lastnameInput.isEnabled = false
                binding.phonenumberInput.isEnabled = false
                binding.addressInput.isEnabled = false
                binding.zipcodeInput.isEnabled = false
                binding.cityInput.isEnabled = false
                binding.countryInput.isEnabled = false
                binding.btnEdit.text = getString(R.string.btn_edit)

                viewModel.saveUserData(
                    binding.addressInput.text.toString(),
                    binding.cityInput.text.toString(),
                    binding.countryInput.text.toString(),
                    binding.firstnameInput.text.toString(),
                    binding.lastnameInput.text.toString(),
                    binding.phonenumberInput.text.toString(),
                    binding.zipcodeInput.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}