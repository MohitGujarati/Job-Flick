package com.example.linkedin_clone.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.linkedin_clone.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditProfileBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_profile_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fullName = view.findViewById<EditText>(R.id.etFullName)
        val headline = view.findViewById<EditText>(R.id.etHeadline)
        val btnSave = view.findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            // Get text values
            val name = fullName.text.toString()
            val title = headline.text.toString()

            Toast.makeText(requireContext(), "Saved: $name, $title", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}
