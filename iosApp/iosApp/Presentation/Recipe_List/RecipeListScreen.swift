
import SwiftUI
import shared
struct RecipeListScreen: View {
    
    private let cacheModule:CacheModule
    
    private let networkModule:NetworkModule
    
    private let searchRecipeModule:SearchRecipeModule
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    let foodCategories:[FoodCategoryEnum]
    
    init(
        cacheModule:CacheModule,
        networkModule:NetworkModule
    ) {
        self.cacheModule = cacheModule
        self.networkModule = networkModule
        self.searchRecipeModule = SearchRecipeModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        let foodcategorycopy = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipeModule.searchRecipes,
            foodCategoryUtil: foodcategorycopy
        )
        self.foodCategories = foodcategorycopy.getAllFoodCategories()
        
    }
    
    var body: some View {
        VStack{
            SearchAppBar(
                query: viewModel.state.query,
                onTriggerEvent: { event in
                    viewModel.onTriggerEvent(stateEvent:event)
                },
                foodCategories: foodCategories,
                selectedCategory: viewModel.state.selectedCategory
            )
        List {
                ForEach( viewModel.state.recipes , id:\.self.id){recipe in
                    Text(recipe.title)
                        .onAppear(perform: {
                            if viewModel.shouldQueryNextPage(recipe: recipe){
                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextRecipe())
                            }
                        })
                }
            }
        }
    }
}

