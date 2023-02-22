package com.junchi.coinandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.junchi.coinandroid.databinding.FragmentHomeBinding
import com.junchi.coinandroid.ui.common.DetailClickListener
import com.junchi.coinandroid.ui.common.EventObserver
import com.junchi.coinandroid.ui.common.ViewModelFactory

class HomeFragment : Fragment(), DetailClickListener {
    private lateinit var _binding: FragmentHomeBinding
    private val binding
        get() = _binding

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        setAdapter()
    }

    private fun setAdapter(){
        val adapter = HomeCoinListAdapter(this,viewModel)
        binding.rvHome.adapter = adapter
        viewModel.home.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onDetailClick(coinName: String) {
        val directions = HomeFragmentDirections.actionNavigationHomeToNavigationDetailActivity(coinName)
        findNavController().navigate(directions)
    }
}