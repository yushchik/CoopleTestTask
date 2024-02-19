package com.example.coopletesttask.presentation.searchCity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.coopletesttask.R
import com.example.coopletesttask.databinding.FragmentSearchCityBinding
import com.example.coopletesttask.domain.network.ResponseStatus
import com.example.coopletesttask.util.Constants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchCityFragment : Fragment(R.layout.fragment_search_city) {

    private val binding: FragmentSearchCityBinding by viewBinding()
    private val viewModel: SearchCityLocationViewModel by viewModel()
    private lateinit var adapter: CityListAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.responseStatus.collect {
                when (it) {
                    is ResponseStatus.Success<*> -> {
                        if (::adapter.isInitialized) {
                            binding.rvCityList.visibility = View.VISIBLE
                            binding.textInfo.visibility = View.GONE
                            adapter.notifyDataSetChanged()
                        }
                    }

                    is ResponseStatus.Error -> {
                        binding.textInfo.visibility = View.VISIBLE
                        binding.textInfo.setTextColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.red
                            )
                        )
                        binding.textInfo.text = getString(R.string.search_city_error)
                    }

                    else -> {}
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        lifecycleScope.launch {
            viewModel.getCityList().collect {
                if (it.isNotEmpty()) {
                    binding.rvCityList.visibility = View.VISIBLE
                    binding.textInfo.visibility = View.GONE
                    adapter.submitList(it)
                }
            }
        }

        binding.searchCity.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.findCityLocation(binding.searchCity.text.toString())
                true
            } else {
                false
            }
        }

    }

    private fun initRecyclerView() {
        adapter = CityListAdapter {
            findNavController().navigate(
                R.id.weatherForecastFragment,
                bundleOf(
                    Constants.CITY_NAME_KEY to it.name,
                    Constants.CITY_LAT_KEY to it.latitude,
                    Constants.CITY_LON_KEY to it.longitude
                )
            )
        }
        binding.rvCityList.layoutManager = LinearLayoutManager(context)
        binding.rvCityList.adapter = adapter
        binding.rvCityList.itemAnimator = null
    }
}