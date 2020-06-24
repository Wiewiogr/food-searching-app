package pl.tw.foodsearchingapp.foodsearch

import com.github.benmanes.caffeine.cache.Cache
import pl.tw.foodsearchingapp.model.Restaurant
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantsScraper


class RestaurantsService(private val restaurantsScraper: PizzaPortalRestaurantsScraper,
                         private val restaurantMenuService: RestaurantMenuService,
                         private val restaurantsCache: Cache<AddressInformation, List<Restaurant>>) {

    fun getRestaurants(city: String, street: String, streetNumber: Int, postalCode: String, flatNumber: Int): List<Restaurant> {
        val addressInformation = AddressInformation(city, street, streetNumber, postalCode, flatNumber)
        val restaurants = restaurantsCache.get(addressInformation, { restaurantsScraper.scrap(it) })!!
        restaurantMenuService.fetchMenuItems(restaurants)
        return restaurants
    }
}

data class AddressInformation(val city: String,
                              val street: String,
                              val streetNumber: Int,
                              val postalCode: String,
                              val flatNumber: Int)