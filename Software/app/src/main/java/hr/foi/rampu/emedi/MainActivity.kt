package hr.foi.rampu.emedi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import hr.foi.rampu.emedi.adapters.MainPagerAdapter
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.database.AppDatabase_Impl
import hr.foi.rampu.emedi.entities.User
import hr.foi.rampu.emedi.fragments.AppointmentsFragment
import hr.foi.rampu.emedi.fragments.DoctorsFragment
import hr.foi.rampu.emedi.fragments.HomeFragment
import hr.foi.rampu.emedi.fragments.ProfileFragment
import hr.foi.rampu.emedi.fragments.SettingsFragment
import hr.foi.rampu.emedi.helpers.MockDataUser
import hr.foi.rampu.emedi.helpers.UserSession
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTabLayoutAndViewpager()
        if(UserSession.loggedIn == false){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        AppDatabase.buildInstance(applicationContext)
        MockDataUser.loadUsers()
        MockDataDoctor.loadDoctors()
    }

    private fun setTabLayoutAndViewpager() {
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager, lifecycle)
        mainPagerAdapter.addFragment(MainPagerAdapter.FragmentItem(R.string.doctors, DoctorsFragment::class))
        mainPagerAdapter.addFragment(MainPagerAdapter.FragmentItem(R.string.appointments, AppointmentsFragment::class))
        mainPagerAdapter.addFragment(MainPagerAdapter.FragmentItem(R.string.home, HomeFragment::class))
        mainPagerAdapter.addFragment(MainPagerAdapter.FragmentItem(R.string.profile, ProfileFragment::class))
        mainPagerAdapter.addFragment(MainPagerAdapter.FragmentItem(R.string.settings, SettingsFragment::class))

        tabLayout = findViewById(R.id.tl_navigation)
        viewPager2 = findViewById(R.id.vp_fragments)

        viewPager2.adapter = mainPagerAdapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.setText(mainPagerAdapter.fragmentItems[position].titleRes)
        }.attach()
    }

        companion object {
            var textSize: Float = 12f
        }



}