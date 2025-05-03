package com.example.linkedin_clone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.linkedin_clone.uiElements.FeaturedSection
import com.example.linkedin_clone.uiElements.getSampleFeaturedItems
import com.example.linkedin_clone.uiElements.user_HomeActivity
import java.lang.reflect.Modifier

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

       // setupTopBar()

        val composeView = findViewById<ComposeView>(R.id.featured_section_compose)

        // Set the Compose content
        composeView.setContent {

                FeaturedSection(
                    featuredItems = getSampleFeaturedItems(),
                    modifier = androidx.compose.ui.Modifier
                )

        }
    }

}

