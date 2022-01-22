package com.example.ashtaftestkmm.Food2ForkKmm.Di

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.KtorClientFactory
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterfaceImp

class NetworkModule {

    val recipeInterface:RecipeInterface by lazy {
        RecipeInterfaceImp(
            client = KtorClientFactory().build(),
            baselink = RecipeInterfaceImp.BASEURL
        )
    }
}