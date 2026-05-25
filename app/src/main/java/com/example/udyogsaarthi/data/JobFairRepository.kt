package com.example.udyogsaarthi.data

import com.example.udyogsaarthi.model.JobFair

/**
 * Provides job fair data.
 * Replace with API / Room DB calls when backend is ready.
 *
 * dateMillis values are set relative to a fixed reference so the
 * "Upcoming / Completed" badge logic works consistently with dummy data.
 * Reference point used: 1 Jun 2025 00:00 UTC = 1748736000000L
 */
class JobFairRepository {

    fun getJobFairs(): List<JobFair> = listOf(
        JobFair(
            id = "1",
            title = "Mega Job Fair – Bengaluru 2025",
            organizer = "Karnataka Skill Development Corporation",
            date = "15 Jun 2025",
            dateMillis = 1750032000000L,   // 15 Jun 2025
            location = "Palace Grounds, Bengaluru, KA",
            registrationLink = "https://kaushalkar.com/jobfair",
            description = "Over 200 companies hiring across IT, manufacturing, retail, and logistics sectors.",
            targetAudience = "Freshers & Experienced (0–5 yrs)",
            entryFee = "Free"
        ),
        JobFair(
            id = "2",
            title = "National Rozgar Mela – Delhi",
            organizer = "Ministry of Labour & Employment",
            date = "22 Jun 2025",
            dateMillis = 1750636800000L,   // 22 Jun 2025
            location = "Talkatora Stadium, New Delhi, DL",
            registrationLink = "https://ncs.gov.in/rozgarmela",
            description = "Government-organised mega recruitment drive with 150+ PSU and private employers.",
            targetAudience = "ITI / Diploma / Graduate",
            entryFee = "Free"
        ),
        JobFair(
            id = "3",
            title = "Skill India Job Connect – Pune",
            organizer = "NSDC & MSDE",
            date = "5 Jul 2025",
            dateMillis = 1751760000000L,   // 5 Jul 2025
            location = "Balewadi Sports Complex, Pune, MH",
            registrationLink = "https://skillindia.gov.in/jobconnect",
            description = "Focused on PMKVY-certified candidates. On-the-spot offer letters for eligible candidates.",
            targetAudience = "PMKVY / Skill-certified candidates",
            entryFee = "Free"
        ),
        JobFair(
            id = "4",
            title = "ITI Placement Drive – Nagpur",
            organizer = "Govt. ITI Nagpur & MSME",
            date = "12 Jul 2025",
            dateMillis = 1752364800000L,   // 12 Jul 2025
            location = "Govt. ITI Campus, Civil Lines, Nagpur, MH",
            registrationLink = "https://msme.gov.in/iti-placement-nagpur",
            description = "Exclusive placement drive for ITI pass-outs in Electrician, Fitter, Welder, and COPA trades.",
            targetAudience = "ITI pass-outs (2023–2025)",
            entryFee = "Free"
        ),
        JobFair(
            id = "5",
            title = "Women Empowerment Job Fair",
            organizer = "National Commission for Women",
            date = "20 Jul 2025",
            dateMillis = 1753056000000L,   // 20 Jul 2025
            location = "FICCI Auditorium, New Delhi, DL",
            registrationLink = "https://ncw.nic.in/jobfair2025",
            description = "Dedicated job fair for women candidates across sectors including healthcare, education, and finance.",
            targetAudience = "Women candidates (all qualifications)",
            entryFee = "Free"
        ),
        JobFair(
            id = "6",
            title = "Rural Youth Employment Mela",
            organizer = "DDU-GKY & State Rural Livelihood Mission",
            date = "2 Aug 2025",
            dateMillis = 1754092800000L,   // 2 Aug 2025
            location = "District Collectorate, Jaipur, RJ",
            registrationLink = "https://ddugky.gov.in/employment-mela",
            description = "Targeting rural youth with 10th/12th pass qualification for entry-level roles in hospitality, security, and retail.",
            targetAudience = "Rural youth, 10th–12th pass",
            entryFee = "Free"
        ),
        JobFair(
            id = "7",
            title = "Tech Talent Hunt – Hyderabad",
            organizer = "NASSCOM Foundation",
            date = "10 Aug 2025",
            dateMillis = 1754784000000L,   // 10 Aug 2025
            location = "HICC, Madhapur, Hyderabad, TS",
            registrationLink = "https://nasscom.in/talent-hunt-hyd",
            description = "IT and tech-focused hiring event with 80+ companies looking for developers, testers, and support engineers.",
            targetAudience = "B.Tech / BCA / Diploma (CS/IT)",
            entryFee = "Free"
        )
    ).sortedBy { it.dateMillis }
}
