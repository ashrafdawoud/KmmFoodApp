package com.example.ashtaftestkmm.Food2ForkKmm.Di

import com.example.ashtaftestkmm.Food2ForkKmm.Interactors.Recipe_Details.GetRecipe

class GetRecipeModule(
    val cacheModule: CacheModule
) {
    val getRecipe:GetRecipe by lazy {
        GetRecipe(
            recipeCashInterface = cacheModule.recipeCacheInterface
        )
    }
}