package com.hfad.teachproff.repos

import androidx.compose.runtime.mutableStateListOf
import com.hfad.teachproff.R
import com.hfad.teachproff.dataCalasses.Cardss

object CardsRepository {
    // Создаем изменяемый список состояния
    val cards = mutableStateListOf(
        Cardss(
            R.drawable.crossnike,
            "BEST SELLER",
            "Nike Air Max",
            "₽752.00"
        ),
        Cardss(
            R.drawable.crossnike,
            "BEST SELLER",
            "Nike Air Max",
            "₽752.00"
        ),
        Cardss(
            R.drawable.crossnike,
            "BEST SELLER",
            "Nike Air Max",
            "₽752.00"
        ),
        Cardss(
            R.drawable.crossnike,
            "BEST SELLER",
            "Nike Air Max",
            "₽752.00"
        ),
        Cardss(
            R.drawable.crossnike,
            "BEST SELLER",
            "Nike Air Max",
            "₽752.00"
        ),
        Cardss(
            R.drawable.crossnike,
            "BEST SELLER",
            "Nike Air Max",
            "₽752.00"
        ),
    )
}