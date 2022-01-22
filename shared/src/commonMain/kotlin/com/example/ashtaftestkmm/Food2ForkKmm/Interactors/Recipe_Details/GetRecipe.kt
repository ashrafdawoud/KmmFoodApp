package com.example.ashtaftestkmm.Food2ForkKmm.Interactors.Recipe_Details

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Cashe.RecipeCacheInterface
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.UIComponentType
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.CommonFlow
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.DataState
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe constructor(
    val recipeCashInterface: RecipeCacheInterface
){
    fun excute(id:Int): CommonFlow<DataState<RecipeModel>> = flow {
        emit(DataState.loading())
        try {
            val recipeModel=recipeCashInterface.get(id)
            kotlinx.coroutines.delay(500)
            emit(DataState.data(message = null,data = recipeModel))
        }catch (e:Exception){
            emit(DataState.error<RecipeModel>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.ERROR")
                    .title("Error")
                    .description(e.message?:"UnKnownErroe")
                    .uiComponentType(UIComponentType.Dialog)))
        }
    }.asCommonFlow()
}