package com.example.vrg_soft_test.screens.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.vrg_soft_test.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var postLayoutManager: LinearLayoutManager

    private val viewModel by viewModels<PostsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postAdapter = PostAdapter()
        postAdapter.callbackOnClick = {
            val action = PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postLayoutManager = LinearLayoutManager(requireContext())
        with(binding.rvPosts) {
            layoutManager = postLayoutManager
            adapter = postAdapter
            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = postLayoutManager.childCount
                    val totalItemCount = postLayoutManager.itemCount
                    val firstVisibleItemPosition = postLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && viewModel.progressLD.value == false
                    ) {
                        viewModel.loadPosts()
                    }
                }
            })
        }
        viewModel.postsLD.observe(viewLifecycleOwner) { postAdapter.updateItems(it) }
        viewModel.progressLD.observe(viewLifecycleOwner) { binding.groupProgress.isVisible = it }
    }
}