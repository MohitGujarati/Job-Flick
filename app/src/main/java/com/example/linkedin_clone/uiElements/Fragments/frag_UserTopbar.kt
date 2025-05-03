package com.example.linkedin_clone.uiElements.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.linkedin_clone.R
import com.example.linkedin_clone.uiElements.user_HomeActivity
import com.example.linkedin_clone.utils.Navigation.navigateHandlers

class frag_UserTopbar : Fragment() {

    // Initialize navigation handler at class level
    private lateinit var navigation: navigateHandlers

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_frag__user_topbar, container, false)

        // Initialize navigation
        navigation = navigateHandlers()

        // Find views
        val backButton = view.findViewById<ImageButton>(R.id.btnback) // Changed to LinearLayout if that's your layout type
        val settingsButton = view.findViewById<ImageButton>(R.id.settingsButton)

        // Set click listeners
        backButton.setOnClickListener {
            Toast.makeText(requireContext(), "frag go to home page", Toast.LENGTH_SHORT).show()
            navigateToHome()
        }

        settingsButton.setOnClickListener {
            Toast.makeText(requireContext(), "Frag go to settings", Toast.LENGTH_SHORT).show()
            navigateToHome()
        }

        return view
    }

    private fun navigateToHome() {
        // Use requireActivity() to get the activity context
        navigation.navigateToAnotherActivity(requireActivity(), user_HomeActivity::class.java)
        // Call activity's finish() method
        requireActivity().finish()
    }
}