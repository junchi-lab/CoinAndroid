package com.junchi.coinandroid.ui.status

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junchi.coinandroid.databinding.FragmentStatusBinding
import com.junchi.coinandroid.ui.common.DetailClickListener
import com.junchi.coinandroid.ui.common.ViewModelFactory

class StatusFragment : Fragment(), DetailClickListener {

    private lateinit var _binding: FragmentStatusBinding
    private val binding
        get() = _binding

    private val viewModel: StatusViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        val statusAdapter = StatusAdapter(this, viewModel)
        binding.rvStatusList.adapter = statusAdapter

        viewModel.status.observe(viewLifecycleOwner){
            statusAdapter.submitList(it)
        }
    }

    override fun onDetailClick(coinName: String) {
        val directions = StatusFragmentDirections.actionNavigationStateToNavigationDetailActivity(coinName)
        findNavController().navigate(directions)
    }
}