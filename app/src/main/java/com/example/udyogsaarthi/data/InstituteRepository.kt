package com.example.udyogsaarthi.data

import com.example.udyogsaarthi.model.Institute
import com.example.udyogsaarthi.model.InstituteType

/**
 * Provides institute listing data.
 * Replace with API / Room DB calls when backend is ready.
 */
class InstituteRepository {

    fun getInstitutes(): List<Institute> = listOf(
        Institute(
            id = "1",
            name = "Govt. ITI Nagpur",
            type = InstituteType.ITI,
            location = "Civil Lines, Nagpur, MH",
            contact = "+91 71224 56789",
            courses = listOf("Electrician", "Fitter", "Welder", "COPA", "Plumber"),
            affiliation = "NCVT / SCVT",
            timings = "Mon–Sat, 8:00 AM – 4:00 PM"
        ),
        Institute(
            id = "2",
            name = "Shri Polytechnic College",
            type = InstituteType.POLYTECHNIC,
            location = "Laxmi Nagar, Pune, MH",
            contact = "+91 20 2765 4321",
            courses = listOf("Civil Engineering", "Mechanical Engineering", "Computer Science", "Electronics"),
            affiliation = "MSBTE",
            timings = "Mon–Sat, 9:00 AM – 5:00 PM"
        ),
        Institute(
            id = "3",
            name = "PMKVY Skill Center",
            type = InstituteType.SKILL_CENTER,
            location = "Sector 12, New Delhi, DL",
            contact = "+91 11 4567 8901",
            courses = listOf("Retail Sales", "Beauty & Wellness", "Hospitality", "Logistics"),
            affiliation = "NSDC / PMKVY",
            timings = "Mon–Fri, 9:00 AM – 6:00 PM"
        ),
        Institute(
            id = "4",
            name = "Govt. ITI Bengaluru",
            type = InstituteType.ITI,
            location = "Rajajinagar, Bengaluru, KA",
            contact = "+91 80 2334 5678",
            courses = listOf("Machinist", "Turner", "Electrician", "Draughtsman Civil"),
            affiliation = "NCVT",
            timings = "Mon–Sat, 8:30 AM – 4:30 PM"
        ),
        Institute(
            id = "5",
            name = "Sunrise Polytechnic",
            type = InstituteType.POLYTECHNIC,
            location = "Anna Nagar, Chennai, TN",
            contact = "+91 44 2819 3344",
            courses = listOf("Automobile Engineering", "Electrical Engineering", "IT", "Chemical Engineering"),
            affiliation = "DOTE Tamil Nadu",
            timings = "Mon–Sat, 9:00 AM – 5:00 PM"
        ),
        Institute(
            id = "6",
            name = "Jan Shikshan Sansthan",
            type = InstituteType.SKILL_CENTER,
            location = "Hazratganj, Lucknow, UP",
            contact = "+91 52 2223 4455",
            courses = listOf("Tailoring", "Computer Basics", "Mobile Repair", "Spoken English"),
            affiliation = "Ministry of Education",
            timings = "Mon–Sat, 10:00 AM – 5:00 PM"
        ),
        Institute(
            id = "7",
            name = "Govt. ITI Jaipur",
            type = InstituteType.ITI,
            location = "Malviya Nagar, Jaipur, RJ",
            contact = "+91 14 1256 7890",
            courses = listOf("Carpenter", "Painter", "Electrician", "Mechanic Motor Vehicle"),
            affiliation = "NCVT / SCVT",
            timings = "Mon–Sat, 8:00 AM – 4:00 PM"
        ),
        Institute(
            id = "8",
            name = "Techno Polytechnic",
            type = InstituteType.POLYTECHNIC,
            location = "Salt Lake, Kolkata, WB",
            contact = "+91 33 2359 1122",
            courses = listOf("Architecture", "Civil Engineering", "Electronics & Comm.", "Computer Science"),
            affiliation = "WBSCTE",
            timings = "Mon–Sat, 9:00 AM – 5:00 PM"
        ),
        Institute(
            id = "9",
            name = "DDU-GKY Center",
            type = InstituteType.SKILL_CENTER,
            location = "Banjara Hills, Hyderabad, TS",
            contact = "+91 40 2354 6677",
            courses = listOf("Security Guard", "Data Entry", "Sewing Machine Operator", "Electrician Helper"),
            affiliation = "MoRD / DDU-GKY",
            timings = "Mon–Fri, 8:00 AM – 5:00 PM"
        )
    )
}
