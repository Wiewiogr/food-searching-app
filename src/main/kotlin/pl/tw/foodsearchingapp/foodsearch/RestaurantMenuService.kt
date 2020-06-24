package pl.tw.foodsearchingapp.foodsearch

import pl.tw.foodsearchingapp.elasticsearch.MenuItemsFeeder
import pl.tw.foodsearchingapp.model.Restaurant
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantMenuScraper
import java.util.concurrent.ExecutorService


class RestaurantMenuService(private val menuScraper: PizzaPortalRestaurantMenuScraper,
                            private val menuItemsFeeder: MenuItemsFeeder,
                            private val executorService: ExecutorService) {

    fun fetchMenuItems(restaurants: List<Restaurant>) =
            restaurants.forEach {
                val restaurant = it
                executorService.submit {
                    menuScraper.scrap(restaurant.endpoint)
                            .forEach {
                                menuItemsFeeder.feed(it)
                            }
                }
            }
}