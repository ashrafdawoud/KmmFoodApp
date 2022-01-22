package com.example.ashtaftestkmm.Food2ForkKmm.Di

import com.example.ashtaftestkmm.Food2ForkKmm.Interactors.Recipe_List.SearchRecipes

class SearchRecipeModule (
    val networkModule: NetworkModule,
    val cacheModule: CacheModule
        ){

    val searchRecipes:SearchRecipes by lazy {
        SearchRecipes(
            recipeInterface = networkModule.recipeInterface,
            recipeCachInterface = cacheModule.recipeCacheInterface
        )
    }
}