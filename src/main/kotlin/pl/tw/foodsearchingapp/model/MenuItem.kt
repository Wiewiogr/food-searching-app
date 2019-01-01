package pl.tw.foodsearchingapp.model

import java.math.BigDecimal


data class MenuItem(val name: String, val description: String, val category: String, val price: BigDecimal)