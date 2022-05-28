package com.example.ticketgoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemTicketsFragment : Fragment() {

    private val viewModel: TicketsRecyclerViewViewModel by viewModels()
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

                viewModel.getUserTickets().observe(viewLifecycleOwner) {
                    try {
                        for (item in it.data?.orders!!) {
                            if (item != null) {
                                tickets += item.tickets!!
                            }
//                            tickets = it.data?.orders?.get(0)?.tickets!!
                        }

                        if (!it.hasErrors()) {
                            recyclerViewAdapter = ItemTicketsRecyclerViewAdapter(tickets)
                            adapter = recyclerViewAdapter
                        } else {
                            adapter = null
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