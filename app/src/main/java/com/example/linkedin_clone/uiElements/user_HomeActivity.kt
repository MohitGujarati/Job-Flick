package com.example.linkedin_clone.uiElements

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.linkedin_clone.MainActivity
import com.example.linkedin_clone.uiElements.ui.theme.ErrorRed
import com.example.linkedin_clone.uiElements.ui.theme.Linkedin_CloneTheme
import com.example.linkedin_clone.uiElements.ui.theme.SuccessGreen
import com.example.linkedin_clone.uiElements.ui.theme.WarningAmber
import com.example.linkedin_clone.user_ApplyInforActivity
import kotlin.math.abs

class user_HomeActivity : ComponentActivity() {

    data class JobPosting(
        val id: String,
        val title: String,
        val company: String,
        val location: String,
        val matchPercentage: Int,
        val description: String,
        val salary: String
    )

    private val sampleJobs = listOf(
        JobPosting("job001", "Android Developer", "Tech Solutions Inc.", "New York, NY", 87, "Experienced Android developer...", "$100,000 - $130,000"),
        JobPosting("job002", "Mobile App Designer", "Creative Studios", "San Francisco, CA", 92, "Create user-friendly mobile interfaces...", "$90,000 - $120,000"),
        JobPosting("job003", "Kotlin Developer", "Startup Innovations", "Austin, TX", 60, "Build next-gen mobile platform...", "$95,000 - $125,000"),
        JobPosting("job004", "Senior Android Engineer", "Global Tech", "Remote", 95, "Lead Android development...", "$130,000 - $160,000"),
        JobPosting("job005", "UI/UX Developer", "Design First", "Chicago, IL", 82, "Talented dev with strong design skills...", "$85,000 - $115,000")
    )

    private val appliedJobs = mutableStateListOf<String>()
    private val rejectedJobs = mutableStateListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Linkedin_CloneTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        val context = LocalContext.current
                        TopBar(
                            onProfileClick = {
                                Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show()
                                context.startActivity(Intent(context, MainActivity::class.java))
                            },
                            onSearchClick = {
                                Toast.makeText(context, "Search clicked", Toast.LENGTH_SHORT).show()
                            },
                            onSettingsClick = {
                                Toast.makeText(context, "Settings clicked", Toast.LENGTH_SHORT).show()
                            }
                        )
                        JobCardStack()
                    }
                }
            }
        }
    }

    @Composable
    fun TopBar(onProfileClick: () -> Unit, onSearchClick: () -> Unit, onSettingsClick: () -> Unit) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shadowElevation = 4.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable(onClick = onProfileClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = "Profile", tint = MaterialTheme.colorScheme.onPrimary)
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .clickable(onClick = onSearchClick)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Search jobs", color = MaterialTheme.colorScheme.onPrimary, fontSize = 16.sp)
                }

                IconButton(onClick = onSettingsClick) {
                    Icon(Icons.Default.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }

    @Composable
    fun JobCardStack() {
        val context= LocalContext.current
        var currentCardIndex by remember { mutableStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color(0x1481ACF6)) // Light blue background
              ,
            contentAlignment = Alignment.Center
        ) {
            if (currentCardIndex >= sampleJobs.size) {
                NoMoreJobsCard()
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (currentCardIndex + 1 < sampleJobs.size) {
                        JobCard(job = sampleJobs[currentCardIndex + 1], isTopCard = false, onSwipeLeft = {}, onSwipeRight = {})
                    }
                    JobCard(job = sampleJobs[currentCardIndex], isTopCard = true,
                        onSwipeLeft = {
                            rejectedJobs.add(sampleJobs[currentCardIndex].id)
                            currentCardIndex++
                        },
                        onSwipeRight = {
                            appliedJobs.add(sampleJobs[currentCardIndex].id)
                            currentCardIndex++
                            navigateToApplyScreen(context,
                                (sampleJobs.getOrNull(currentCardIndex - 1) ?: 0) as JobPosting
                            )

                        })
                }
            }
        }
    }

    @Composable
    fun NoMoreJobsCard() {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No more jobs", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Check back later for more opportunities!", color = MaterialTheme.colorScheme.secondary)
            }
        }
    }

    @Composable
    fun JobCard(job: JobPosting, isTopCard: Boolean, onSwipeLeft: () -> Unit, onSwipeRight: () -> Unit) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        val rotation by remember { derivedStateOf { offsetX * 0.05f } }
        val scale by animateFloatAsState(targetValue = if (isTopCard) 1f else 0.9f, label = "scale")

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(if (isTopCard) 1f else 0f),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(scale)
                    .rotate(rotation)
                    .offset {
                        if (isTopCard) IntOffset(
                            offsetX.toInt(),
                            offsetY.toInt()
                        ) else IntOffset.Zero
                    }
                    .then(if (isTopCard) Modifier.pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                if (abs(offsetX) > 200) {
                                    if (offsetX > 0) onSwipeRight() else onSwipeLeft()
                                    offsetX = 0f
                                    offsetY = 0f
                                } else {
                                    offsetX = 0f
                                    offsetY = 0f
                                }
                            },
                            onDrag = { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y / 2
                            }
                        )
                    } else Modifier),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)) {
                    CompanyTitle(job.matchPercentage, job.company)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(job.title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(job.company, color = MaterialTheme.colorScheme.secondary)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(job.location, color = MaterialTheme.colorScheme.secondary)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Salary: ${job.salary}", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Description", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(job.description, color = MaterialTheme.colorScheme.tertiary)
                    Spacer(modifier = Modifier.weight(1f))
                    if (isTopCard) {
                        Text("Swipe right to apply, left to pass", fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }

    @Composable
    fun CompanyTitle(percentage: Int, companyName: String) {
        val barColor = when {
            percentage >= 90 -> MaterialTheme.colorScheme.primary
            percentage >= 70 -> SuccessGreen
            percentage >= 50 -> WarningAmber
            else -> ErrorRed
        }
        Column(horizontalAlignment = Alignment.Start) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(companyName.first().toString(), color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(companyName, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(percentage / 100f)
                        .height(6.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    barColor.copy(alpha = 0.7f),
                                    barColor
                                )
                            )
                        )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text("$percentage% Match", fontSize = 14.sp, color = barColor)
        }
    }

    private fun navigateToApplyScreen(context: android.content.Context, job: JobPosting) {
        val intent = Intent(context, user_ApplyInforActivity::class.java).apply {
            putExtra("jobId", job.id)
            putExtra("jobTitle", job.title)
            putExtra("company", job.company)
            putExtra("location", job.location)
            putExtra("matchPercentage", job.matchPercentage)
            putExtra("description", job.description)
            putExtra("salary", job.salary)
        }
        context.startActivity(intent)
    }


}


