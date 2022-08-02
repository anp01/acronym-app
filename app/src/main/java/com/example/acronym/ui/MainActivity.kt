package com.example.acronym.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronym.R
import com.example.acronym.adapter.WordAdapter
import com.example.acronym.databinding.ActivityMainBinding
import com.example.acronym.others.Resource
import com.example.acronym.others.hide
import com.example.acronym.others.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
        }

        setupRecyclerView()

        binding.searchButton.setOnClickListener {
            mainViewModel.fetchWordListResponse()
        }

        observeViewModel()
    }

    private fun setupRecyclerView() {
        wordAdapter = WordAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = wordAdapter
        }
    }

    private fun observeViewModel() {
        mainViewModel.wordListResponse.observe(this) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.apply {
                        errorTextView.show()
                        progressBar.hide()
                        recyclerview.hide()
                        errorTextView.text = resource.message
                    }
                }
                is Resource.Loading -> {
                    binding.apply {
                        errorTextView.hide()
                        progressBar.show()
                        recyclerview.hide()
                    }
                }
                is Resource.Success -> {
                    binding.apply {
                        errorTextView.hide()
                        progressBar.hide()
                        recyclerview.show()
                    }
                    if (resource.data?.isNotEmpty() == true)
                        wordAdapter.submitList(resource.data[0].lfs)
                    else {
                        wordAdapter.submitList(ArrayList())
                        binding.errorTextView.apply {
                            show()
                            text = context.getString(R.string.no_data_found)
                        }
                    }
                }
            }
        }
    }
}