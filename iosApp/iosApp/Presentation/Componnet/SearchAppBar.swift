//
//  SearchAppBar.swift
//  iosApp
//
//  Created by apple on 22/01/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
struct SearchAppBar: View {
    @State var query: String
    let onTriggerEvent: (RecipeListEvents) -> Void
    var foodCategories : [FoodCategoryEnum]
    var selcetdcategory:FoodCategoryEnum?

        init(
            query: String,
            onTriggerEvent: @escaping (RecipeListEvents) -> Void,
            foodCategories : [FoodCategoryEnum],
            selectedCategory : FoodCategoryEnum?

        ) {
            self.onTriggerEvent = onTriggerEvent
            self._query = State(initialValue: query) // set initial value for query
            self.foodCategories = foodCategories
            self.selcetdcategory = selectedCategory
        }
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField(
                    "search",
                    text:$query  ,
                    onCommit:{
                        onTriggerEvent(RecipeListEvents.ExcuteSearch())
                    }).onChange(of: query, perform: {value in
                        onTriggerEvent(RecipeListEvents.OnQueryChanged(query: value))
                    }).padding(.bottom,8).padding(.top,8).padding(.leading,8).padding(.trailing,8)
                    .background(Color.white.opacity(0))
            }.padding(12)
            ScrollView(.horizontal){
                HStack(spacing : 6){
                    ForEach(foodCategories, id: \.self){category in
                        FoodCategoryChip(title: category.value, isSelected: category == selcetdcategory)
                            .onTapGesture {
                                query = category.value
                                onTriggerEvent(RecipeListEvents.OnSelectCategory(categoryEnum: category))
                            }
                    }
                }
            }
        }
    }
}

 //struct SearchAppBar_Previews: PreviewProvider {
 //   static var previews: some View {
//        SearchAppBar()
//    }
//}
