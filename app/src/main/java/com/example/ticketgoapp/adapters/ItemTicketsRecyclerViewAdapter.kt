package com.example.ticketgoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketgoapp.GetUsersTicketsQuery
import com.example.ticketgoapp.R
import com.example.ticketgoapp.databinding.FragmentItemTicketBinding
import com.example.ticketgoapp.viewmodels.QRViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ItemTicketsRecyclerViewAdapter(
    private val tickets: List<GetUsersTicketsQuery.Ticket?>,
    private val context: Context,
    private val qrViewModel: QRViewModel
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

        // RecyclerView item clicklistener that creates an AlertDialog that displays the QR code
        holder.itemView.setOnClickListener {
            val ticketClicked = tickets[position]
            if (ticketClicked != null) {

                // Inflater to inflate qr_layout
                val inflater = LayoutInflater.from(context)
                // Using the inflater
                val qrView = inflater.inflate(R.layout.qr_layout, null)
                // Setting the bitmap in the imageview with the returned bitmap from encodeQR method
                // Takes the ticketId as parameter
                qrView.findViewById<ImageView>(R.id.ivQr)
                    .setImageBitmap(qrViewModel.encodeQR(ticketClicked._id.toString()))

                // Building and showing the AlertDialog
                MaterialAlertDialogBuilder(context)
                    .setTitle(context.getString(R.string.qr_title))
                    .setMessage(context.getString(R.string.ticket_id) + " ${ticketClicked._id}")
                    .setPositiveButton(context.getString(R.string.btn_ok)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setView(qrView)
                    .show()
            }
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