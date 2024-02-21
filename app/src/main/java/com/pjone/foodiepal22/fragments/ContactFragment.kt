package com.pjone.foodiepal22.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pjone.foodiepal22.R


class ContactFragment : Fragment() {

    private lateinit var callButton: Button
    private lateinit var emailButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.contact_fragment, container, false)
        callButton = view.findViewById(R.id.callButton)
        emailButton = view.findViewById(R.id.emailButton)
        callButton.setOnClickListener {
            dialPhoneNumber()
        }
        emailButton.setOnClickListener {
            sendEmail()
        }
        return view
    }

    private fun dialPhoneNumber() {
        val phoneNumber = "6412330117"
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(dialIntent)
    }

    private fun sendEmail() {
        val emailAddress = "pjaywardana@miu.edu"
        val emailIntent = Intent(Intent.ACTION_SENDTO)
            .apply {
                data = Uri.parse("mailto:$emailAddress")
            }
        if (emailIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(emailIntent)
        } else {
            Toast.makeText(requireContext(), "No email client found", Toast.LENGTH_LONG).show()
        }
    }

}