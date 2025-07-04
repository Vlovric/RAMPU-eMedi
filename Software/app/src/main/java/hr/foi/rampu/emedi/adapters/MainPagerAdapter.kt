package hr.foi.rampu.emedi.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.reflect.KClass

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    data class FragmentItem(val titleRes: Int, val fragmentClass: KClass<*>)
    val fragmentItems = ArrayList<FragmentItem>()

    fun addFragment(newFragment: FragmentItem) {
        fragmentItems.add(newFragment)
    }

    override fun getItemCount(): Int = fragmentItems.size
    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position].fragmentClass.java.newInstance() as Fragment
    }
}