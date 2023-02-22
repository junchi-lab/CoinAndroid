package com.junchi.coinandroid.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.junchi.coinandroid.R
import com.junchi.coinandroid.database.entity.BookmarkEntity
import com.junchi.coinandroid.databinding.FragmentBookMarkBinding
import com.junchi.coinandroid.ui.common.DeleteClickListener
import com.junchi.coinandroid.ui.common.DetailClickListener
import com.junchi.coinandroid.ui.common.ViewModelFactory

class BookmarkFragment : Fragment(), DetailClickListener, DeleteClickListener {

    private lateinit var _binding: FragmentBookMarkBinding
    private val binding
        get() = _binding

    private val viewModel: BookmarkViewModel by viewModels { ViewModelFactory(requireContext()) }

    private lateinit var bookmarAdapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookMarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setAdapter()
    }

    private fun setAdapter(){
        bookmarAdapter = BookmarkAdapter(this,this)
        with(binding.vpBookmark) {
            adapter = bookmarAdapter.apply {
                viewModel.bookmark.observe(viewLifecycleOwner) {
                    bookmarAdapter.submitList(it)
                }
            }
        }

        val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
        val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
        val screenWidth = resources.displayMetrics.widthPixels
        val offset = screenWidth - pageWidth - pageMargin

        binding.vpBookmark.offscreenPageLimit = 3
        binding.vpBookmark.setPageTransformer { page, position ->
            page.translationX = position * -offset
        }

        TabLayoutMediator(binding.vpBookmarkIndicator, binding.vpBookmark) { _, _ ->

        }.attach()

    }

    override fun onDetailClick(coinName: String) {
        val directions = BookmarkFragmentDirections.actionNavigationBookmarkToNavigationDetailActivity(coinName)
        findNavController().navigate(directions)
    }

    override fun onDeleteClick(bookmark: BookmarkEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_bookmark))
            .setPositiveButton(getString(android.R.string.ok)) { _, _  ->
                viewModel.deleteBookmark(bookmark)
            }
            .show()
    }
}