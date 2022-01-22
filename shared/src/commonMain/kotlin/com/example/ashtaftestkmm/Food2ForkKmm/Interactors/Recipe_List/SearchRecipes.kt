package com.example.ashtaftestkmm.Food2ForkKmm.Interactors.Recipe_List

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Cashe.RecipeCacheInterface
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.KtorInterfaces.RecipeCalls.RecipeInterface
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.UIComponentType
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.CommonFlow
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.DataState
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.asCommonFlow
import io.ktor.client.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    val recipeInterface: RecipeInterface,
    val recipeCachInterface: RecipeCacheInterface
) {
    fun excute(page:Int,query:String): CommonFlow<DataState<List<RecipeModel>>> = flow{
        emit(DataState.loading())
        try{
            val recipes = recipeInterface.search(
                page = page,
                query = query,
            )

            // delay 500ms so we can see loading
            delay(500)

            // force error for testing
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED!")
            }

            // insert into cache
            recipeCachInterface.insert(recipes)

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCachInterface.getAll(page = page)
            } else {
                recipeCachInterface.search(
                    query = query,
                    page = page,
                )
            }
            // emit List<Recipe> from cache
            emit(DataState.data<List<RecipeModel>>(message = null, data = cacheResult))
        }catch (e:Exception){
            emit(DataState.error<List<RecipeModel>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.ERROR")
                    .title(e.toString())
                    .description(e.message?:"UnKnownErroe")
                    .uiComponentType(UIComponentType.Dialog)))
        }
    }.asCommonFlow()
}