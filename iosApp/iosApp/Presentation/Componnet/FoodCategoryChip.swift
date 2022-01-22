//
//  FoodCategoryChip.swift
//  iosApp
//
//  Created by apple on 22/01/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct FoodCategoryChip: View {
    var title:String
    var isSelected:Bool
    init(title:String,isSelected:Bool) {
        self.title = title
        self.isSelected = isSelected
    }
    var body: some View {
        HStack{
                    Text(title) // TODO("update font")
                        .padding(8)
                        .background(isSelected ? Color.gray : Color.blue) // TODO("update gray color")
                        .foregroundColor(Color.white)
                        
                }
        .cornerRadius(10).padding(2)
            }
    }



