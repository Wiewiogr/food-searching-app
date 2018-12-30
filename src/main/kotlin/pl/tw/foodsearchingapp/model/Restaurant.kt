package pl.tw.foodsearchingapp.model


data class Restaurant(val name: String,
                      val minimumOrderValue: Float,
                      val distance: Float,
                      val deliveryFee: Float,
                      val link: String,
                      val categories: List<String>)