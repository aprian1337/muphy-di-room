package com.aprian1337.movie_catalogue.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {
    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tvshows
        )
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = SectionPagerAdapter(requireActivity())
        val viewPager : ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs : TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}