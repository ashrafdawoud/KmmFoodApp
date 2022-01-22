//
//  RecipeListViewModel.swift
//  iosApp
//
//  Created by apple on 15/01/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    // Dependencies
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil

    // State
    @Published var state: RecipeListState = RecipeListState()
    
    init(
            searchRecipes: SearchRecipes,
            foodCategoryUtil: FoodCategoryUtil
        ){
            self.searchRecipes = searchRecipes
            self.foodCategoryUtil = foodCategoryUtil
            onTriggerEvent(stateEvent: RecipeListEvents.LoadingRecipe())
        
            // TODO("Perform a search")
        }
    func updateState(
        isloading:Bool? = nil ,
        page:Int? = nil ,
        query:String? = nil ,
        bottomRecipe:RecipeModel?=nil,
        queue:Queue<GenericMessageInfo>? = nil
        
    ) {
        let currentState = self.state.copy() as! RecipeListState
        self.state = self.state.doCopy(
            isLoading: isloading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)) ,
            query: (query ?? currentState.query) as! String,
            selectedCategory: currentState.selectedCategory,
            recipes: currentState.recipes,
            bottomRecipe:  bottomRecipe ?? currentState.bottomRecipe,
            queue: queue ?? currentState.queue
        )
    }
    
    func onTriggerEvent(stateEvent:RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadingRecipe:
            loadRecipes()
        case is RecipeListEvents.ExcuteSearch:
            excuteSearch()
        case is RecipeListEvents.NextRecipe:
            nextPage()
        case is RecipeListEvents.OnQueryChanged:
            onUpdateQuery(query : (stateEvent as! RecipeListEvents.OnQueryChanged).query)
        case is RecipeListEvents.OnSelectCategory:
            onSelectedCategory(selectedcategory :(stateEvent as! RecipeListEvents.OnSelectCategory).categoryEnum)
        case is RecipeListEvents.OnRemoveHeadMessageFromQueue:
            doNoThing()
        default:
            doNoThing()
        }
    }
    private func excuteSearch(){
        resetState()
        loadRecipes()
    }
    private func resetState(){
        var currentState = self.state.copy() as! RecipeListState
        var foodCategory = currentState.selectedCategory
                if(foodCategory?.value != currentState.query){
                    foodCategory = nil
                }
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1,
            query: currentState.query,
            selectedCategory: foodCategory,
            recipes: [],
            bottomRecipe: currentState.bottomRecipe,
            queue: currentState.queue)
    }
    private func onSelectedCategory(selectedcategory:FoodCategoryEnum?){
        var currentState = self.state.copy() as! RecipeListState
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            selectedCategory: selectedcategory,
            recipes:currentState.recipes,
            bottomRecipe: currentState.bottomRecipe,
            queue: currentState.queue
        )
        onUpdateQuery(query: selectedcategory?.value ?? "")
        onTriggerEvent(stateEvent: RecipeListEvents.ExcuteSearch())
    }
    private func loadRecipes(){
            let currentState = (self.state.copy() as! RecipeListState)
            do{
                try searchRecipes.excute(
                    page: Int32(currentState.page),
                    query: currentState.query
                ).collectFlow(
                    coroutineScope : nil,
                    callBack : { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        print("recipes is now1 =   \(message?.build().title)  ")
                        self.updateState(isloading: loading)

                        if(data != nil){
                            self.appendToView(recipes: data as! [RecipeModel])
                        }
                        if(message != nil){
                            //self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                })
            }catch{
                // build an error
                //self.handleMessageByUIComponentType(message!.build())
            }
        }
    func appendToView( recipes : [RecipeModel] ){
        var state = self.state.copy() as! RecipeListState
        var currentRecipes = state.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(
            isLoading: state.isLoading,
            page: state.page,
            query: state.query,
            selectedCategory: state.selectedCategory,
            recipes: currentRecipes,
            bottomRecipe: state.bottomRecipe,
            queue: state.queue)
        let currentState = (self.state.copy() as! RecipeListState)
        self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
    }
    func doNoThing(){
        
    }
    func onUpdateQuery(query : String ){
        updateState(query : query)
    }
    func shouldQueryNextPage(recipe: RecipeModel) -> Bool {
                // check if looking at the bottom recipe
                // if lookingAtBottom -> proceed
                // if PAGE_SIZE * page <= recipes.length
                // if !queryInProgress
                // else -> do nothing
                let currentState = (self.state.copy() as! RecipeListState)
                if(recipe.id == currentState.bottomRecipe?.id){
                    if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.recipes.count){
                        if(!currentState.isLoading){
                            return true
                        }
                    }
                }
                return false
            }
    private func onUpdateBottomRecipe(recipe: RecipeModel){
                updateState(bottomRecipe: recipe)
            }
    private func nextPage(){
           let currentState = (self.state.copy() as! RecipeListState)
           updateState(page: Int(currentState.page) + 1)
           loadRecipes()
       }
}
