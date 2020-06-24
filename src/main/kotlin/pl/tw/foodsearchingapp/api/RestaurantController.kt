package pl.tw.foodsearchingapp.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.tw.foodsearchingapp.foodsearch.RestaurantsService
import pl.tw.foodsearchingapp.model.Restaurant


@RestController
class RestaurantController(val restaurantsService: RestaurantsService) {

    @CrossOrigin
    @GetMapping("/restaurant/{city}/{street}/{streetNumber}/{postalCode}/{flatNumber}")
    fun getRestaurants(@PathVariable("city") city: String,
                       @PathVariable("street") street: String,
                       @PathVariable("streetNumber") streetNumber: Int,
                       @PathVariable("postalCode") postalCode: String,
                       @PathVariable("flatNumber") flatNumber: Int): List<Restaurant> {
        return restaurantsService.getRestaurants(city, street, streetNumber, postalCode, flatNumber)
    }
}