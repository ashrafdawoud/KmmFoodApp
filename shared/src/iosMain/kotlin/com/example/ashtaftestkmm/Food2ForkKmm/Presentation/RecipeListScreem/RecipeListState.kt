package com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeListScreem

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.Queue

actual  data class RecipeListState (
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory: FoodCategoryEnum? = null,
    val recipes: List<RecipeModel> = listOf(),
    val bottomRecipe: RecipeModel? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
){
    // Need secondary zero-argument constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        recipes = listOf(),
        selectedCategory = null,
        bottomRecipe=null,
        queue = Queue(mutableListOf()),
    )
    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}
