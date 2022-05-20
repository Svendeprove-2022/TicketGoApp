package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ticketgoapp.viewmodels.TicketsViewModel
import com.example.ticketgoapp.databinding.FragmentTicketsBinding

class TicketsFragment : Fragment() {

    private lateinit var viewModel: TicketsViewModel
    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)


        return binding.root
    }

}