package com.example.ticketgoapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketgoapp.GetUsersTicketsQuery
import com.example.ticketgoapp.R
import com.example.ticketgoapp.adapters.ItemTicketsRecyclerViewAdapter
import com.example.ticketgoapp.viewmodels.QRViewModel
import com.example.ticketgoapp.viewmodels.TicketsRecyclerViewViewModel

class ItemTicketsRecyclerViewFragment : Fragment() {

    private val viewModel: TicketsRecyclerViewViewModel by viewModels()
    private val qrViewModel: QRViewModel by viewModels()
    private lateinit var recyclerViewAdapter: ItemTicketsRecyclerViewAdapter
    private var columnCount = 1
    private var tickets = mutableListOf<GetUsersTicketsQuery.Ticket?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_ticket_recyclerview, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                adapter = null

                viewModel.getUserTickets().observe(viewLifecycleOwner) {
                    try {
                        if (!it.hasErrors()) { // No errors found in reseponse
                            if (!it.data?.orders?.isEmpty()!!) { // Tickets found in orders
                                for (item in it.data?.orders!!) {
                                    if (item != null) {
                                        tickets += item.tickets!!
                                    } else {
                                        adapter = null
                                        break
                                    }
                                }

                                Log.d("rv", "adding items to adapter")
                                recyclerViewAdapter =
                                    ItemTicketsRecyclerViewAdapter(tickets, context, qrViewModel)
                                adapter = recyclerViewAdapter

                            } else {
                                Log.d("rv", "orders is empty")
                            }
                        } else {
                            Log.d("rv", it.errors.toString())
                            Toast.makeText(context, it.errors.toString(), Toast.LENGTH_LONG).show()
                        }

                    } catch (e: Exception) {
                        Log.d("tickets error", e.message.toString())
                    }
                }
            }
        }

        return view
    }

}