package com.example.linkedin_clone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.linkedin_clone.databinding.ActivityUserApplyInforBinding
import com.example.linkedin_clone.uiElements.user_HomeActivity
import com.example.linkedin_clone.utils.EditProfileBottomSheet
import com.example.linkedin_clone.utils.Navigation.navigateHandlers

class user_ApplyInforActivity : AppCompatActivity() {
    private lateinit var navigation: navigateHandlers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityUserApplyInforBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receiving job data
        val layoutFeature = intent.getStringExtra("layoutFeature")

        // Set up edit buttons for different sections
        binding.btnAboutEdit.setOnClickListener {
            showEditBottomSheet("about")
        }

        binding.btnExperienceEdit.setOnClickListener {
            showEditBottomSheet("experience")
        }

        binding.btnEducationEdit.setOnClickListener {
            showEditBottomSheet("education")
        }

        binding.btnEducationEdit.setOnClickListener {
            showEditBottomSheet("education")
        }

        binding.btnAddProject.setOnClickListener {
            showEditBottomSheet("projects")
        }

        // Conditional layout logic
        if (layoutFeature == "true") {
            showFeatureView(binding)
        } else {
            showApplyView(binding)
        }

        navigation = navigateHandlers()
        binding.btnBack.setOnClickListener {
            navigation.navigateToAnotherActivity(this, user_HomeActivity::class.java)
            finish()
        }

        // Submit button logic
        binding.btnConfirmAndSubmit.setOnClickListener {
            Toast.makeText(this, "Application Submitted!", Toast.LENGTH_SHORT).show()
            navigation.navigateToAnotherActivity(this, user_HomeActivity::class.java)
            finish()
        }
    }

    private fun showEditBottomSheet(sectionType: String) {
        val bottomSheet = EditProfileBottomSheet.newInstance(sectionType)
        bottomSheet.show(supportFragmentManager, "EditProfileBottomSheet")
    }

    private fun showFeatureView(binding: ActivityUserApplyInforBinding) {
        // Hide unnecessary views
        binding.tvApplyingTo.visibility = View.GONE
        binding.companyCard.visibility = View.GONE
        binding.tvCompanyName.visibility = View.GONE
        binding.btnConfirmAndSubmit.visibility = View.GONE

        // Make toolbar smaller
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        val params = binding.appbar.layoutParams
        // Set toolbar height to 40dp
        params.height = dpToPx(35)
        binding.appbar.layoutParams = params
    }

    // Helper function to convert dp to pixels
    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    private fun showApplyView(binding: ActivityUserApplyInforBinding) {
        // You can leave this empty or add additional UI logic for apply mode

        val jobTitle = intent.getStringExtra("jobTitle") ?: "Unknown"
        val company = intent.getStringExtra("company") ?: "Unknown"
        val location = intent.getStringExtra("location") ?: "Unknown"

        // Log the data for debugging
        Log.d("ApplyActivity", "Applying to $jobTitle at $company in $location")

        // Display data in UI
        binding.tvCompanyName.text = company
        binding.tvposition.text = jobTitle
        binding.tvCompanyLocation.text = location
    }
}