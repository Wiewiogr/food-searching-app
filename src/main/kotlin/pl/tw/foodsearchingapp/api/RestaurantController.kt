package pl.tw.foodsearchingapp.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.tw.foodsearchingapp.model.Restaurant
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantsScraper


@RestController
class RestaurantController(val restaurantsScraper: PizzaPortalRestaurantsScraper) {

    @CrossOrigin
    @GetMapping("/restaurant/{city}/{street}/{streetNumber}/{postalCode}/{flatNumber}")
    fun getRestaurants(@PathVariable("city") city: String,
                       @PathVariable("street") street: String,
                       @PathVariable("streetNumber") streetNumber: Int,
                       @PathVariable("postalCode") postalCode: String,
                       @PathVariable("flatNumber") flatNumber: Int): List<Restaurant> {
        return restaurantsScraper.scrap(city, street, streetNumber, postalCode, flatNumber)
    }
}