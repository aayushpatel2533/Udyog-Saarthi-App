package com.example.udyogsaarthi.data

import com.example.udyogsaarthi.model.Job
import com.example.udyogsaarthi.model.JobType

/**
 * Repository providing job listing data.
 * Currently uses hardcoded dummy data — replace with API/DB calls later.
 */
class JobRepository {

    fun getJobs(): List<Job> = listOf(
        Job(
            id = "1",
            title = "Android Developer",
            company = "TechSoft India",
            location = "Bengaluru, KA",
            type = JobType.FULL_TIME,
            description = "Build and maintain Android applications using Kotlin and Jetpack libraries. Work with REST APIs and follow MVVM architecture.",
            salary = "₹6L – ₹10L/yr"
        ),
        Job(
            id = "2",
            title = "Data Entry Operator",
            company = "GovServe Solutions",
            location = "New Delhi, DL",
            type = JobType.FULL_TIME,
            description = "Accurate data entry and record management for government service portals. Proficiency in MS Office required.",
            salary = "₹2L – ₹3L/yr"
        ),
        Job(
            id = "3",
            title = "Field Sales Executive",
            company = "Bharat Agro Ltd.",
            location = "Pune, MH",
            type = JobType.FULL_TIME,
            description = "Drive sales of agricultural products across rural Maharashtra. Travel required. Strong communication skills needed.",
            salary = "₹3L – ₹5L/yr"
        ),
        Job(
            id = "4",
            title = "Web Design Intern",
            company = "PixelCraft Studio",
            location = "Hyderabad, TS",
            type = JobType.INTERNSHIP,
            description = "Assist in designing responsive web interfaces using Figma and HTML/CSS. Great opportunity for freshers.",
            salary = "₹8,000/mo"
        ),
        Job(
            id = "5",
            title = "Accounts Assistant",
            company = "Sunrise Traders",
            location = "Jaipur, RJ",
            type = JobType.PART_TIME,
            description = "Manage daily accounts, invoices, and GST filings. Tally ERP knowledge preferred.",
            salary = "₹15,000/mo"
        ),
        Job(
            id = "6",
            title = "Customer Support Executive",
            company = "HelpDesk Pro",
            location = "Mumbai, MH",
            type = JobType.FULL_TIME,
            description = "Handle inbound customer queries via phone and chat. Fluency in Hindi and English required.",
            salary = "₹2.5L – ₹4L/yr"
        ),
        Job(
            id = "7",
            title = "ITI Electrician",
            company = "PowerGrid Corp",
            location = "Nagpur, MH",
            type = JobType.CONTRACT,
            description = "Installation and maintenance of electrical systems at industrial sites. ITI certification mandatory.",
            salary = "₹25,000/mo"
        ),
        Job(
            id = "8",
            title = "Content Writer",
            company = "EduReach Media",
            location = "Remote",
            type = JobType.PART_TIME,
            description = "Write engaging educational content in Hindi and English for e-learning platforms. Flexible hours.",
            salary = "₹500/article"
        )
    )
}
