package alfianyabdullah.submission.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

const val GAME_BUNDLE_KEY = "game_key"

fun <T> Fragment.observe(data: LiveData<T>, action: (T) -> Unit) {
    data.observe(viewLifecycleOwner, Observer {
        action(it)
    })
}

fun ViewPager2.attachToViewPager(tab: TabLayout) {
    TabLayoutMediator(tab, this) { _, _ -> }.attach()
}