package com.example.vrg_soft_test.screens.post_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.vrg_soft_test.databinding.FragmentPostDetailsBinding
import com.example.vrg_soft_test.utils.setImage

class PostDetailsFragment : Fragment() {

    private val args: PostDetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<PostDetailsViewModel>()

    private lateinit var binding: FragmentPostDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivImage.setImage(args.image)
        binding.ivSaveImage.setOnClickListener { viewModel.saveImage(args.image) }
    }
}