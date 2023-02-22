package com.junchi.coinandroid.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.junchi.coinandroid.R
import com.junchi.coinandroid.databinding.FragmentDetailBinding
import com.junchi.coinandroid.ui.common.EventObserver
import com.junchi.coinandroid.ui.common.ViewModelFactory
import timber.log.Timber

class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private val binding
        get() = _binding

    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val coinName = args.coinName
        viewModel.loadDetail(coinName)

        setTitle(coinName)
        setAdapter()
        setNavigation()
        setAddBookmark()
    }

    private fun setTitle(coinName: String){
        binding.data = coinName
    }

    private fun setAdapter(){
        val coinInfoAdapter = CoinInfoAdapter()
        val coinHistoryAdapter = CoinHistoryAdapter()
        binding.rvDetail.adapter = ConcatAdapter(coinInfoAdapter, coinHistoryAdapter)

        viewModel.info.observe(viewLifecycleOwner) {
            coinInfoAdapter.submitList(listOf(it))
        }

        viewModel.history.observe(viewLifecycleOwner) {
            coinHistoryAdapter.submitList(it)
        }
    }

    private fun setNavigation() {
        binding.tbDetail.setNavigationOnClickListener {
            if (parentFragmentManager.backStackEntryCount == 0) {
                activity?.finish()
            } else {
                findNavController().navigateUp()
            }
        }
    }

    private fun setAddBookmark() {
        viewModel.addBookmarkEvent.observe(viewLifecycleOwner, EventObserver{
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.add_bookmark))
                .setPositiveButton(getString(android.R.string.ok)) { _, _  ->

                }
                .show()
        })
    }
}
