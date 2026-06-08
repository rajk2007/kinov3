package com.lagradost.cloudstream3.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lagradost.cloudstream3.R
import com.lagradost.cloudstream3.databinding.FragmentSearchKinoBinding
import com.lagradost.cloudstream3.ui.BaseFragment
import com.lagradost.cloudstream3.mvvm.observe
import com.lagradost.cloudstream3.mvvm.Resource

class KinoSearchFragment : BaseFragment<FragmentSearchKinoBinding>(
    BindingCreator.Bind(FragmentSearchKinoBinding::bind)
) {
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        observeData()
    }

    private fun setupUI() {
        binding.apply {
            // Search bar logic
            // Trending searches pills
            // Filter tabs
        }
    }

    private fun observeData() {
        observe(searchViewModel.searchResponse) { resource ->
            when (resource) {
                is Resource.Success -> {
                    // Update search results with CloudStream data
                }
                is Resource.Loading -> {
                    // Show loading state
                }
                is Resource.Failure -> {
                    // Show error state
                }
            }
        }
    }
}
