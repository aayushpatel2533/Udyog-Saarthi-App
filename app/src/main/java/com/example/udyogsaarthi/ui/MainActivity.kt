package com.example.udyogsaarthi.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.udyogsaarthi.R
import com.example.udyogsaarthi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    // All top-level destinations — hamburger shown, no back arrow
    private val topLevelDestinations = setOf(
        R.id.nav_home,
        R.id.nav_job_listings,
        R.id.nav_resume_builder,
        R.id.nav_institutes,
        R.id.nav_job_fairs,
        R.id.nav_document_uploads,
        R.id.nav_more
    )

    // Human-readable titles for every destination in the graph
    private val destinationTitles = mapOf(
        R.id.nav_home              to "Udyog Saarthi",
        R.id.nav_job_listings      to "Job Listings",
        R.id.nav_resume_builder    to "Resume Builder",
        R.id.nav_resume_preview    to "Resume Preview",
        R.id.nav_institutes        to "Institutes Directory",
        R.id.nav_job_fairs         to "Job Fairs",
        R.id.nav_document_uploads  to "Document Uploads",
        R.id.nav_more              to "More"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(topLevelDestinations, binding.drawerLayout)

        // Toolbar: hamburger for top-level, back arrow for nested (e.g. Resume Preview)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Both nav surfaces wired to the same NavController
        binding.navView.setupWithNavController(navController)
        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // 1. Update toolbar title
            supportActionBar?.title = destinationTitles[destination.id]
                ?: destination.label?.toString()
                ?: getString(R.string.app_name)

            val isTopLevel = destination.id in topLevelDestinations

            // 2. Show/hide bottom nav
            binding.bottomNavView.visibility = if (isTopLevel) View.VISIBLE else View.GONE

            // 3. Sync drawer checked item — only for destinations that exist in the drawer menu
            val drawerMenuIds = setOf(
                R.id.nav_home, R.id.nav_job_listings, R.id.nav_resume_builder,
                R.id.nav_institutes, R.id.nav_job_fairs, R.id.nav_document_uploads
            )
            if (destination.id in drawerMenuIds) {
                binding.navView.setCheckedItem(destination.id)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        when {
            // Close drawer first if open
            binding.drawerLayout.isDrawerOpen(GravityCompat.START) ->
                binding.drawerLayout.closeDrawer(GravityCompat.START)

            // Let NavController handle back (pops back stack or exits)
            !navController.navigateUp() -> super.onBackPressed()
        }
    }
}
