package com.example.ltest.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState.NotLoading
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ltest.R
import com.example.ltest.core.Resource
import com.example.ltest.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MainFragmentBinding.bind(view)

        viewModel.getRandomGif().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Glide.with(view.context)
                            .load(result.data.url)
                            .centerCrop()
                            .into(binding.randomGif)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Unable to fetch data ... ", Toast.LENGTH_LONG).show()
                }
            }
        })


        val gifPagedListAdapter = GifPagedListAdapter(requireContext()) {
            Glide.with(view.context)
                    .load(it.url)
                    .centerCrop()
                    .into(binding.randomGif)
        }
        gifPagedListAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        gifPagedListAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is NotLoading) {
                binding.noResultsText.isVisible = gifPagedListAdapter.itemCount == 0
            }
        }

        val columns = 4
        binding.gifsRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.gifsRecycler.adapter = gifPagedListAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(qString: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(qString: String): Boolean {
                hideKeyboard()
                lifecycleScope.launchWhenStarted {
                    viewModel.getGifSearchResults(qString).collectLatest {
                        gifPagedListAdapter.submitData(it)
                    }
                }
                return true
            }
        })
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}