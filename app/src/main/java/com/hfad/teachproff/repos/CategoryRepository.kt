package com.hfad.teachproff.repos

import androidx.compose.runtime.mutableStateListOf
import com.hfad.teachproff.dataCalasses.CategoryList

object CategoryRepository {
    val category =
        mutableStateListOf(
            CategoryList(
                "Все"
            ),
            CategoryList(
                "Outdoor"
            ),
            CategoryList(
                "Tennis"
            ),
            CategoryList(
                "Street"
            )
        )
}
