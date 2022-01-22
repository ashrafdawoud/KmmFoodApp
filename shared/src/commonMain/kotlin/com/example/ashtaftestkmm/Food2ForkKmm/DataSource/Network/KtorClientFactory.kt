package com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network

import io.ktor.client.*
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Network.EntityDto.RecipeDTO
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Utils.DatetimeUtil

expect class KtorClientFactory (){
    fun build():HttpClient
}
fun RecipeDTO.toRecipe(): RecipeModel {
    val datetimeUtil = DatetimeUtil()
    return RecipeModel(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        //dateAdded = DatetimeUtil().toLocalDate(longDateAdded.toDouble()),
       // dateUpdated = DatetimeUtil().toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDTO>.toRecipeList(): List<RecipeModel>{
    return map{it.toRecipe()}
}