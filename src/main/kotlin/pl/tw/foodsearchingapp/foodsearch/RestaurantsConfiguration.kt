package pl.tw.foodsearchingapp.foodsearch

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.tw.foodsearchingapp.elasticsearch.MenuItemsFeeder
import pl.tw.foodsearchingapp.model.Restaurant
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantMenuScraper
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantsScraper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@Configuration
class RestaurantsConfiguration {

    @Bean
    fun restaurantsService(restaurantsScraper: PizzaPortalRestaurantsScraper,
                           restaurantMenuService: RestaurantMenuService,
                           restaurantsCache: Cache<AddressInformation, List<Restaurant>>) =
            RestaurantsService(restaurantsScraper, restaurantMenuService, restaurantsCache)

    @Bean
    fun restaurantMenuService(menuScraper: PizzaPortalRestaurantMenuScraper,
                              menuItemsFeeder: MenuItemsFeeder,
                              executorService: ExecutorService) =
            RestaurantMenuService(menuScraper, menuItemsFeeder, executorService)

    @Bean
    fun fetchingExecutorService() = Executors.newFixedThreadPool(4)!!

    @Bean
    fun restaurantsCache(): Cache<AddressInformation, List<Restaurant>> = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build()
}