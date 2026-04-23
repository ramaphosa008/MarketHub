package com.ramaphosa.markethub.models

data class Product(
    var id: String? = null,
    val name: String? = null,
    val category: String? = null,
    val brand: String? = null,
    val price: String? = null,
    val description: String? = null,
    val imageUrl: String? = null      //Requires an image picker to allow user to be directed to gallery to select image of product
)
