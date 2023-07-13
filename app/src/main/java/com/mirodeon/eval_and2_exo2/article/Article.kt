package com.mirodeon.eval_and2_exo2.article

import android.os.Parcelable
import com.mirodeon.eval_and2_exo2.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(val label: String, val count: String, val type: ArticleType) : Parcelable

enum class ArticleType(val label: String, val colorId: Int, val imgId: Int) {
    ALIMENTATION("Alimentation", R.color.blue, R.drawable.food),
    BOISSON("Boisson", R.color.purple, R.drawable.drink),
    HYGIENE("Hygiène", R.color.yellow, R.drawable.health),
    MAISON("Maison", R.color.pink, R.drawable.home)
}
