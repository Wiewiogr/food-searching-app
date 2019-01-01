package pl.tw.foodsearchingapp.restaurant

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.tw.foodsearchingapp.model.Restaurant
import pl.tw.foodsearchingapp.scraper.PizzaPortalRestaurantsScraper
import java.util.concurrent.TimeUnit


@Configuration
class RestaurantsConfiguration {

    @Bean
    fun restaurantsService(restaurantsScraper: PizzaPortalRestaurantsScraper,
                           restaurantsCache: Cache<AddressInformation, List<Restaurant>>) =
            RestaurantsService(restaurantsScraper, restaurantsCache)

    @Bean
    fun restaurantsCache(): Cache<AddressInformation, List<Restaurant>> = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build()
}