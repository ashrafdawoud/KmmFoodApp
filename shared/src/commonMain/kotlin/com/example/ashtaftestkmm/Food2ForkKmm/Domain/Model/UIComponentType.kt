package com.example.ashtaftestkmm.Food2ForkKmm.Domain.Model

sealed class UIComponentType{
    // اي نوع ممكن تستخدمه للايرور
    object Dialog: UIComponentType()
    object SnackBar: UIComponentType()

    object None: UIComponentType()
}
