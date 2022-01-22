package com.example.ashtaftestkmm.Food2ForkKmm.DataSource.Domain.Model
//import kotlinx.datetime.*

data class RecipeModel(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
  //  val dateAdded: LocalDateTime,
  //  val dateUpdated: LocalDateTime,
)
