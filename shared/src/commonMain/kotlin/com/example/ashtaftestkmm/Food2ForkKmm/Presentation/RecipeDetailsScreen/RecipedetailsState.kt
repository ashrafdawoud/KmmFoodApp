package com.example.ashtaftestkmm.Food2ForkKmm.Presentation.RecipeDetailsScreen

import com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model.RecipeModel
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils.Queue

data class RecipedetailsState (
    val isLoading:Boolean =false,
    val recipe: RecipeModel?=null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)


