package com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeListScreem

import com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeDetailsScreen.RecipeDetailsEvents

sealed class RecipeListEvents {
    object LoadingRecipe : RecipeListEvents()
    object NextRecipe : RecipeListEvents()
    object ExcuteSearch : RecipeListEvents()
    data class OnQueryChanged(val query:String):RecipeListEvents()
    data class OnSelectCategory(val categoryEnum: FoodCategoryEnum):RecipeListEvents()
    object OnRemoveHeadMessageFromQueue : RecipeListEvents()

}