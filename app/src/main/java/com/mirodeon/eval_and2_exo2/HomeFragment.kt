package com.mirodeon.eval_and2_exo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mirodeon.eval_and2_exo2.article.Article
import com.mirodeon.eval_and2_exo2.article.ArticleAdapter
import com.mirodeon.eval_and2_exo2.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), ArticleAdapter.OnItemClickListener {
    private var binding: FragmentHomeBinding? = null
    private var liveData: MutableLiveData<Article>? = null
    private val listArticles = mutableListOf<Article>()

    companion object {
        const val NEW_ARTICLE = "NEW_ARTICLE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
        setupRecyclerArticle()
    }

    override fun onDestroy() {
        binding = null
        liveData?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }

    private fun setupLiveData() {
        val currentBackstackEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackstackEntry?.savedStateHandle
        liveData = savedStateHandle?.getLiveData(NEW_ARTICLE)
        liveData?.observe(viewLifecycleOwner) { result ->
            listArticles.add(result)
            setupRecyclerArticle()
        }
    }

    private fun setupRecyclerArticle() {
        binding?.txtNoArticle?.visibility = if (listArticles.isNotEmpty()) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        val recycler = binding?.containerRecyclerArticle
        recycler?.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recycler?.adapter = ArticleAdapter(listArticles, this)
    }

    override fun onItemClicked(article: Article) {
        Snackbar.make(
            requireContext(),
            requireView(),
            "${article.type.label}: \n${article.count}x ${article.label}",
            Toast.LENGTH_SHORT
        ).show()
    }
}