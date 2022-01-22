package com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils

import com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model.GenericMessageInfo

class GenericMessageInfoQueueUtil {
    fun doesMessageIsAlreadyExistOnQueue(
        queue:Queue<GenericMessageInfo>,
        genericMessageInfo: GenericMessageInfo
    ):Boolean{
        for (item in queue.items){
            if (genericMessageInfo.id == item.id){
                return  true
            }
        }
        return false
    }
}