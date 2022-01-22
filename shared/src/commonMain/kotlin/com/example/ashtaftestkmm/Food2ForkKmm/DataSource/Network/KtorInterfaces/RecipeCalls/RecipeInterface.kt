package com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.EntityDto.RecipeDTO

interface RecipeInterface {
    suspend fun search(page:Int,query:String):List<RecipeModel>
    suspend fun get(id:Int):RecipeModel
}