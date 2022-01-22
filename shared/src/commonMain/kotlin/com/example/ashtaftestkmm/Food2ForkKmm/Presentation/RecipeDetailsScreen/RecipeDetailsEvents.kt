package com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeDetailsScreen

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeListScreem.RecipeListEvents

sealed class RecipeDetailsEvents {
    data class onLoadRecipe(val recipeId: Int):RecipeDetailsEvents()
    object OnRemoveHeadMessageFromQueue : RecipeDetailsEvents()
}