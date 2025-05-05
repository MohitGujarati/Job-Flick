package com.example.linkedin_clone.uiElements.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.linkedin_clone.R
import com.example.linkedin_clone.uiElements.user_HomeActivity
import com.example.linkedin_clone.user_ApplyInforActivity
import com.example.linkedin_clone.utils.EditProfileBottomSheet
import com.example.linkedin_clone.utils.Navigation.navigateHandlers
import com.google.android.material.floatingactionbutton.FloatingActionButton


class frag_UserProfileSection : Fragment() {

    private lateinit var navigation: navigateHandlers

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_frag__user_profile_section, container, false)
        // Inflate the layout for this fragment
        val editProfileButton=view.findViewById<FloatingActionButton>(R.id.editProfileButton)

        editProfileButton.setOnClickListener {
            Toast.makeText(requireContext(), "showing the bottom sheet", Toast.LENGTH_SHORT).show()
            showEditBottomSheet("all")

        }
        return view
    }

    private fun showEditBottomSheet(sectionType: String) {

        navigation.navigateToAnotherActivity(requireContext(), user_HomeActivity::class.java)

    }


}