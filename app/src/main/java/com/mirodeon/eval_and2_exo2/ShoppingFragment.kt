package com.mirodeon.eval_and2_exo2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.mirodeon.eval_and2_exo2.article.Article
import com.mirodeon.eval_and2_exo2.article.ArticleType
import com.mirodeon.eval_and2_exo2.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {
    private var binding: FragmentShoppingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtn()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupBtn() {
        binding?.btnAdd?.setOnClickListener {
            if (sendArticleToHome()) {
                findNavController().navigateUp()
            }
        }
        binding?.btnCancel?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun sendArticleToHome(): Boolean {
        var success = false
        validateArticle()
        val savedStateHandle =
            findNavController().getBackStackEntry(R.id.homeFragment).savedStateHandle
        validateArticle()?.let {
            savedStateHandle[HomeFragment.NEW_ARTICLE] = it
            success = true
        }
        return success
    }

    private fun validateArticle(): Article? {
        val label = binding?.inputLabelArticle?.text.toString()
        val count = binding?.inputQuantity?.text.toString()
        val type = when (binding?.radioGroupType?.checkedRadioButtonId) {
            R.id.radioBtnFood -> ArticleType.ALIMENTATION
            R.id.radioBtnDrink -> ArticleType.BOISSON
            R.id.radioBtnHealth -> ArticleType.HYGIENE
            R.id.radioBtnHome -> ArticleType.MAISON
            else -> ArticleType.ALIMENTATION
        }
        return if (label.isBlank() || count.isBlank()) {
            val builder = AlertDialog.Builder(requireContext(), R.style.AppTheme_AlertDialog)
            builder
                .setTitle("ERROR")
                .setMessage("Le formulaire n'a pas été correctement rempli.")
                .setPositiveButton("OK") { dialog, which ->
                }
                .create()
                .show()
            null
        } else {
            Article(label, count, type)
        }
    }
}