package com.example.linkedin_clone.uiElements.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.linkedin_clone.R

class frag_UserInformationSection : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_frag__user_information_section, container, false)
        // Inflate the layout for this fragment


        return view
    }


}