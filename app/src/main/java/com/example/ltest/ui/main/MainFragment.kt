package com.example.ltest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        val columns = 4
        binding.gifsRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.gifsRecycler.adapter = gifPagedListAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.getGifSearchResults("minions").collectLatest {
                gifPagedListAdapter.submitData(it)
            }
        }

    }

}