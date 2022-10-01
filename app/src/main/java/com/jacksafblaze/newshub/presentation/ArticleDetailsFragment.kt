package com.jacksafblaze.newshub.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import com.jacksafblaze.newshub.R
import com.jacksafblaze.newshub.databinding.FragmentArticleDetailsBinding
import com.jacksafblaze.newshub.presentation.viewmodel.ArticleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {
    val args: ArticleDetailsFragmentArgs by navArgs()
    val viewModel: ArticleDetailsViewModel by viewModels()
    private var _binding: FragmentArticleDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment_container
            duration = resources.getInteger(com.google.android.material.R.integer.material_motion_duration_long_1).toLong()
            scrimColor = Color.TRANSPARENT
        }
        viewModel.article = args.selectedArticle
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articleDetailsTitle.text = viewModel.article.title
        Glide.with(requireContext()).load(viewModel.article.url).into(binding.articleDetailsImage)
        binding.articleDetailsContent.text = viewModel.article.content
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}