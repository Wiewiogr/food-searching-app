package pl.tw.foodsearchingapp.restaurant

import com.github.benmanes.caffeine.cache.Cache
import pl.tw.foodsearchingapp.model.Restaurant
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantsScraper


class RestaurantsService(private val restaurantsScraper: PizzaPortalRestaurantsScraper,
                         private val restaurantsCache: Cache<AddressInformation, List<Restaurant>>) {

    fun getRestaurants(city: String, street: String, streetNumber: Int, postalCode: String, flatNumber: Int): List<Restaurant> {
        val addressInformation = AddressInformation(city, street, streetNumber, postalCode, flatNumber)
        return restaurantsCache.get(addressInformation, { restaurantsScraper.scrap(it) })!!
    }
}

data class AddressInformation(val city: String,
                              val street: String,
                              val streetNumber: Int,
                              val postalCode: String,
                              val flatNumber: Int)