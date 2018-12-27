package pl.tw.foodsearchingapp.scraper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RestaurantScraperConfiguration {

    @Bean
    fun pizzaPortalRestaurantScraper() = PizzaPortalRestaurantsScraper()

}