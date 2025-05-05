package com.example.linkedin_clone.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.example.linkedin_clone.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class EditProfileBottomSheet : BottomSheetDialogFragment() {

    // Profile section
    private lateinit var etName: TextInputEditText
    private lateinit var etTitle: TextInputEditText
    private lateinit var etLocation: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var btnSave: MaterialButton

    // Work experience section
    private lateinit var etJobTitle: TextInputEditText
    private lateinit var etCompany: TextInputEditText
    private lateinit var etWorkLocation: TextInputEditText
    private lateinit var etDuration: TextInputEditText
    private lateinit var etWorkType: TextInputEditText
    private lateinit var etDescription: TextInputEditText
    private lateinit var btnSaveWork: MaterialButton
    private lateinit var btnAddAnotherExperience: MaterialButton

    // Education section
    private lateinit var etSchool: TextInputEditText
    private lateinit var etDegree: TextInputEditText
    private lateinit var etFieldOfStudy: TextInputEditText
    private lateinit var etEduDuration: TextInputEditText
    private lateinit var etGPA: TextInputEditText
    private lateinit var btnSaveEducation: MaterialButton
    private lateinit var btnAddAnotherEducation: MaterialButton

    // Project section
    private lateinit var etProjectTitle: TextInputEditText
    private lateinit var etRole: TextInputEditText
    private lateinit var etProjectType: TextInputEditText
    private lateinit var etProjectDuration: TextInputEditText
    private lateinit var etProjectDescription: TextInputEditText
    private lateinit var btnSaveProject: MaterialButton
    private lateinit var btnAddAnotherProject: MaterialButton

    private var sectionType: String = "about" // default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(ARG_SECTION_TYPE)?.let {
            sectionType = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use the correct layout file that matches your XML
        return inflater.inflate(R.layout.edit_profile_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Make all sections GONE by default
        view.findViewById<View>(R.id.UserProfile).visibility = View.GONE
        view.findViewById<View>(R.id.Bottom_WorkExp).visibility = View.GONE
        view.findViewById<View>(R.id.Bottom_Education).visibility = View.GONE
        view.findViewById<View>(R.id.Bottom_Project).visibility = View.GONE

        // Show and initialize only the selected section
        when (sectionType) {
            "about" -> initializeAboutSection(view)
            "experience" -> initializeExperienceSection(view)
            "education" -> initializeEducationSection(view)
            "projects" -> initializeProjectSection(view)
            "all"-> initializeAllSections(view)
        }
    }

    private fun initializeAllSections(view: View) {
        view.findViewById<View>(R.id.UserProfile).visibility = View.VISIBLE
        view.findViewById<View>(R.id.Bottom_WorkExp).visibility = View.VISIBLE
        view.findViewById<View>(R.id.Bottom_Education).visibility = View.VISIBLE
        view.findViewById<View>(R.id.Bottom_Project).visibility = View.VISIBLE


    }

    private fun initializeAboutSection(view: View) {
        view.findViewById<View>(R.id.UserProfile).visibility = View.VISIBLE

        // Initialize about section views
        etName = view.findViewById(R.id.etName)
        etTitle = view.findViewById(R.id.etTitle)
        etLocation = view.findViewById(R.id.etProfileLocation)
        etEmail = view.findViewById(R.id.etEmail)
        etPhone = view.findViewById(R.id.etPhone)
        btnSave = view.findViewById(R.id.btnSave)

        // Set up save button for about section
        btnSave.setOnClickListener {
            saveProfile()
        }
    }

    private fun initializeExperienceSection(view: View) {
        view.findViewById<View>(R.id.Bottom_WorkExp).visibility = View.VISIBLE

        // Initialize experience section views
        etJobTitle = view.findViewById(R.id.etJobTitle)
        etCompany = view.findViewById(R.id.etCompany)
        etWorkLocation = view.findViewById(R.id.etLocation)
        etDuration = view.findViewById(R.id.etDuration)
        etWorkType = view.findViewById(R.id.etWorkType)
        etDescription = view.findViewById(R.id.etDescription)
        btnSaveWork = view.findViewById(R.id.btnSaveWork)
        btnAddAnotherExperience = view.findViewById(R.id.btnAddAnotherExperience)

        // Set up save button for experience section
        btnSaveWork.setOnClickListener {
            saveWorkExperience()
        }

        // Set up add another button
        btnAddAnotherExperience.setOnClickListener {
            saveWorkExperience(addAnother = true)
        }
    }

    private fun initializeEducationSection(view: View) {
        view.findViewById<View>(R.id.Bottom_Education).visibility = View.VISIBLE

        // Initialize education section views
        etSchool = view.findViewById(R.id.etSchool)
        etDegree = view.findViewById(R.id.etDegree)
        etFieldOfStudy = view.findViewById(R.id.etFieldOfStudy)
        etEduDuration = view.findViewById(R.id.etEduDuration)
        etGPA = view.findViewById(R.id.etGPA)
        btnSaveEducation = view.findViewById(R.id.btnSaveEducation)
        btnAddAnotherEducation = view.findViewById(R.id.btnAddAnotherEducation)

        // Set up save button for education section
        btnSaveEducation.setOnClickListener {
            saveEducation()
        }

        // Set up add another button
        btnAddAnotherEducation.setOnClickListener {
            saveEducation(addAnother = true)
        }
    }

    private fun initializeProjectSection(view: View) {
        view.findViewById<View>(R.id.Bottom_Project).visibility = View.VISIBLE

        // Initialize project section views
        etProjectTitle = view.findViewById(R.id.etProjectTitle)
        etRole = view.findViewById(R.id.etRole)
        etProjectType = view.findViewById(R.id.etProjectType)
        etProjectDuration = view.findViewById(R.id.etProjectDuration)
        etProjectDescription = view.findViewById(R.id.etProjectDescription)
        btnSaveProject = view.findViewById(R.id.btnSaveProject)
        btnAddAnotherProject = view.findViewById(R.id.btnAddAnotherProject)

        // Set up save button for project section
        btnSaveProject.setOnClickListener {
            saveProject()
        }

        // Set up add another button
        btnAddAnotherProject.setOnClickListener {
            saveProject(addAnother = true)
        }
    }

    private fun saveProfile() {
        val name = etName.text.toString().trim()
        val title = etTitle.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()

        if (name.isEmpty() || title.isEmpty() || location.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Save profile info to database or shared preferences
        Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
        dismiss()
    }

    private fun saveWorkExperience(addAnother: Boolean = false) {
        val jobTitle = etJobTitle.text.toString().trim()
        val company = etCompany.text.toString().trim()
        val location = etWorkLocation.text.toString().trim()
        val duration = etDuration.text.toString().trim()
        val workType = etWorkType.text.toString().trim()
        val description = etDescription.text.toString().trim()

        if (jobTitle.isEmpty() || company.isEmpty() || location.isEmpty() ||
            duration.isEmpty() || workType.isEmpty()
        ) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Create a work experience object
        val workExperience = WorkExperience(
            jobTitle = jobTitle,
            company = company,
            location = location,
            duration = duration,
            workType = workType,
            description = description
        )

        // TODO: Save work experience to database
        Toast.makeText(requireContext(), "Work experience added successfully", Toast.LENGTH_SHORT)
            .show()

        if (addAnother) {
            // Clear the fields for new entry
            etJobTitle.text?.clear()
            etCompany.text?.clear()
            etWorkLocation.text?.clear()
            etDuration.text?.clear()
            etWorkType.text?.clear()
            etDescription.text?.clear()
            etJobTitle.requestFocus()
        } else {
            dismiss()
        }
    }

    private fun saveEducation(addAnother: Boolean = false) {
        val school = etSchool.text.toString().trim()
        val degree = etDegree.text.toString().trim()
        val fieldOfStudy = etFieldOfStudy.text.toString().trim()
        val duration = etEduDuration.text.toString().trim()
        val gpa = etGPA.text.toString().trim()

        if (school.isEmpty() || degree.isEmpty() || fieldOfStudy.isEmpty() || duration.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Create an education object
        val education = Education(
            school = school,
            degree = degree,
            fieldOfStudy = fieldOfStudy,
            duration = duration,
            gpa = gpa
        )

        // TODO: Save education to database
        Toast.makeText(requireContext(), "Education added successfully", Toast.LENGTH_SHORT).show()

        if (addAnother) {
            // Clear the fields for new entry
            etSchool.text?.clear()
            etDegree.text?.clear()
            etFieldOfStudy.text?.clear()
            etEduDuration.text?.clear()
            etGPA.text?.clear()
            etSchool.requestFocus()
        } else {
            dismiss()
        }
    }

    private fun saveProject(addAnother: Boolean = false) {
        val projectTitle = etProjectTitle.text.toString().trim()
        val role = etRole.text.toString().trim()
        val projectType = etProjectType.text.toString().trim()
        val duration = etProjectDuration.text.toString().trim()
        val description = etProjectDescription.text.toString().trim()

        if (projectTitle.isEmpty() || role.isEmpty() || projectType.isEmpty() || duration.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Create a project object
        val project = Project(
            title = projectTitle,
            role = role,
            type = projectType,
            duration = duration,
            description = description
        )

        // TODO: Save project to database
        Toast.makeText(requireContext(), "Project added successfully", Toast.LENGTH_SHORT).show()

        if (addAnother) {
            // Clear the fields for new entry
            etProjectTitle.text?.clear()
            etRole.text?.clear()
            etProjectType.text?.clear()
            etProjectDuration.text?.clear()
            etProjectDescription.text?.clear()
            etProjectTitle.requestFocus()
        } else {
            dismiss()
        }
    }




    // Data classes for storing profile information
    data class WorkExperience(
        val jobTitle: String,
        val company: String,
        val location: String,
        val duration: String,
        val workType: String,
        val description: String
    )

    data class Education(
        val school: String,
        val degree: String,
        val fieldOfStudy: String,
        val duration: String,
        val gpa: String
    )

    data class Project(
        val title: String,
        val role: String,
        val type: String,
        val duration: String,
        val description: String
    )

    companion object {
        private const val ARG_SECTION_TYPE = "section_type"

        fun newInstance(sectionType: String): EditProfileBottomSheet {
            val fragment = EditProfileBottomSheet()
            val args = Bundle()
            args.putString(ARG_SECTION_TYPE, sectionType)
            fragment.arguments = args
            return fragment
        }
    }

}