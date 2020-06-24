package pl.tw.foodsearchingapp.model


data class Restaurant(val name: String,
                      val minimumOrderValue: Float,
                      val distance: Float,
                      val deliveryFee: Float,
                      val endpoint: String,
                      val categories: List<String>)