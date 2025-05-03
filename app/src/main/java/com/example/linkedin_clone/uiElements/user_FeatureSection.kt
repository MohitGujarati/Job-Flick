package com.example.linkedin_clone.uiElements

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.linkedin_clone.uiElements.ui.theme.Linkedin_CloneTheme
import com.example.linkedin_clone.user_ApplyInforActivity
import com.example.linkedin_clone.utils.Navigation.navigateHandlers

class UserFeatureSection : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Linkedin_CloneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FeaturedSection(
                        featuredItems = getSampleFeaturedItems(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class FeaturedItem(
    val id: String,
    val type: String,  // "Document", "Link", etc.
    val title: String,
    val subtitle: String = ""
)

@Composable
fun FeaturedSection(
    featuredItems: List<FeaturedItem>,
    modifier: Modifier = Modifier
) {
    var  context= LocalContext.current
    Column(modifier = modifier.padding(16.dp)) {
        // Header with title and action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // android:textColor="?attr/colorOnBackground"
            Text(
                text = "Featured",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Row {



                IconButton(onClick = { NavigateToActivity(context) }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit featured section"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable list of featured items
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(end = 8.dp)
        ) {
            items(featuredItems) { item ->
                FeaturedItemCard(item)
            }
        }
    }
}

fun NavigateToActivity(context: Context) {

    val navigation = navigateHandlers()

    navigation.navigateMsgToAnotherActivity(context,"layoutFeature","true",user_ApplyInforActivity::class.java)
    // Finish current activity safely
    (context as? Activity)?.finish()
}


fun addFeaturedItem(context: Context) {

    Toast.makeText(context, "Navigate to add ", Toast.LENGTH_SHORT).show()

}


@Composable
fun FeaturedItemCard(item: FeaturedItem) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(200.dp)
            .clickable { /* Navigate to item */ },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = item.type,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                if (item.subtitle.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            // Item type indicator at the bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.3f))
                    .padding(vertical = 6.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = item.type,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// Sample data function
fun getSampleFeaturedItems(): List<FeaturedItem> {
    return listOf(
        FeaturedItem(
            id = "1",
            type = "Document",
            title = "MohitResume_2025.pdf",
            subtitle = "Resume"
        ),
        FeaturedItem(
            id = "2",
            type = "Link",
            title = "Mohit Gujarati - Github",
            subtitle = "GitHub"
        ),
        FeaturedItem(
            id = "3",
            type = "Link",
            title = "Mohit Gujarati | Software Engineer | Android Developer",
            subtitle = "mohitgujarati.github.io"
        ),
        FeaturedItem(
            id = "4",
            type = "Project",
            title = "LinkedIn Clone",
            subtitle = "Android App"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FeaturedSectionPreview() {
    Linkedin_CloneTheme {
        FeaturedSection(
            featuredItems = getSampleFeaturedItems(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}