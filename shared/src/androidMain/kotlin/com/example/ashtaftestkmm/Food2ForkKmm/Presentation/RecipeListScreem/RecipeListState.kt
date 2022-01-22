package com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeListScreem

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.Queue
import com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeListScreem.FoodCategoryEnum

actual  data class RecipeListState (
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategoryEnum? = null,
    val recipes: List<RecipeModel> = listOf(),
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)