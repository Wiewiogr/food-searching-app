package pl.tw.foodsearchingapp.scraper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RestaurantScraperConfiguration {

    @Bean
    fun pizzaPortalRestaurantMenuScraper(pizzaPortalBaseUrl: String) =
            PizzaPortalRestaurantMenuScraper(pizzaPortalBaseUrl)

    @Bean
    fun pizzaPortalRestaurantScraper(pizzaPortalBaseUrl: String) =
            PizzaPortalRestaurantsScraper(pizzaPortalBaseUrl)

    @Bean
    fun pizzaPortalBaseUrl() = "https://pizzaportal.pl"

}