package com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Cashe

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel

interface RecipeCacheInterface {
    fun insert(recipe: RecipeModel)

    fun insert(recipes: List<RecipeModel>)

    fun search(query: String, page: Int): List<RecipeModel>

    fun getAll(page: Int): List<RecipeModel>

    @Throws(NullPointerException::class)
    fun get(recipeId: Int): RecipeModel?
}