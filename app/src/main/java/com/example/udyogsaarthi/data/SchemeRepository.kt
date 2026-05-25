package com.example.udyogsaarthi.data

import com.example.udyogsaarthi.model.Scheme

/**
 * Repository acting as the single source of truth for Scheme data.
 * Abstracts data sources (network, local DB) from the ViewModel layer.
 */
class SchemeRepository {

    /**
     * Returns a list of schemes.
     * TODO: Replace with actual network/database calls.
     */
    fun getSchemes(): List<Scheme> {
        return listOf(
            Scheme(
                id = "1",
                name = "PM Mudra Yojana",
                description = "Micro-finance scheme for small businesses",
                eligibility = "Non-corporate, non-farm small/micro enterprises",
                benefits = "Loans up to ₹10 lakh"
            ),
            Scheme(
                id = "2",
                name = "Startup India",
                description = "Support for new startups",
                eligibility = "DPIIT-recognised startups",
                benefits = "Tax exemptions, funding support, mentorship"
            ),
            Scheme(
                id = "3",
                name = "Stand-Up India",
                description = "Loans for SC/ST and women entrepreneurs",
                eligibility = "SC/ST and women entrepreneurs",
                benefits = "Bank loans between ₹10 lakh and ₹1 crore"
            )
        )
    }
}
