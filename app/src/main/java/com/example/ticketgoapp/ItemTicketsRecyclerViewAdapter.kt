package com.example.ticketgoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketgoapp.databinding.FragmentItemTicketBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ItemTicketsRecyclerViewAdapter(
    private val tickets: List<GetUsersTicketsQuery.Ticket?>
) : RecyclerView.Adapter<ItemTicketsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FragmentItemTicketBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = tickets[position]
        if (ticket != null) {
            // Taking the data from the graphql query and assigning it to views in fragment_item_ticket.xml

            // Loading image url from graphql query into imageView using Picasso library
            Picasso.get().load(ticket.section?.event?.images?.get(0))
                .into(holder.binding.ivEventPic)
            holder.binding.tvEventName.text = ticket.section?.event?.name.toString()
            holder.binding.tvEventTime.text =
                formatTime(ticket.section?.event?.dates?.start.toString())
            holder.binding.tvEventVenueName.text = ticket.section?.event?.venue?.name.toString()
            holder.binding.tvEventVenueAddress.text =
                ticket.section?.event?.venue?.address.toString()
        }
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    // Formats the string from graphql
    private fun formatTime(timeString: String): String? {
        var time = timeString
        time = time.dropLast(1) // Remove last character from string

        // Parse string to LocalDateTime so it can be formatted
        val localdatetime = LocalDateTime.parse(time)

        // Format pattern
        val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy @ HH:mm")

        // Formatting the LocalDateTime with our own pattern and returns it
        return localdatetime.format(pattern)
    }

}