package com.example.photoapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoapp.data.remote.RetrofitInstance
import com.example.photoapp.databinding.ActivityMainBinding
import com.example.photoapp.ui.adapter.PhotoAdapter
import com.example.photoapp.ui.viewmodel.PhotoViewModel
import com.example.photoapp.ui.viewmodel.PhotoViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var viewModel: PhotoViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val api = RetrofitInstance.api
        val factory = PhotoViewModelFactory(api)

        viewModel = ViewModelProvider(this, factory)[PhotoViewModel::class.java]

        adapter = PhotoAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.photos.collectLatest {
                adapter.submitData(it)
                binding.swipeRefresh.isRefreshing = false
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.addLoadStateListener { loadStates ->

            val isLoading = loadStates.refresh is LoadState.Loading

            binding.swipeRefresh.isRefreshing = isLoading

            if(adapter.itemCount == 0 && isLoading){
                binding.centerLoader.visibility = View.VISIBLE
            }else{
                binding.centerLoader.visibility = View.GONE
            }

            val errorState = loadStates.source.refresh as? LoadState.Error

            if (errorState != null){
                binding.errorLayout.visibility = View.VISIBLE
                binding.errorText.visibility = View.VISIBLE
            }
            else{
                binding.errorLayout.visibility = View.GONE
                binding.errorText.visibility = View.GONE
            }
        }
    }
}