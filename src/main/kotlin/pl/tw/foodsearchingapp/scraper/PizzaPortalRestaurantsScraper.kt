package pl.tw.foodsearchingapp.scraper

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import pl.tw.foodsearchingapp.model.Restaurant


class PizzaPortalRestaurantsScraper {

    private val baseUrl = "https://pizzaportal.pl"

    fun scrap(city: String, street: String, streetNumber: Int, postalCode: String, flatNumber: Int): List<Restaurant> {
        val document = Jsoup.connect("$baseUrl/$city/restauracje/ul-${street.replace(" ", "-")}/$streetNumber/$postalCode/$flatNumber/").get()
        val restaurantList = document.select("div[class=restaurant-list]")
        val elements = restaurantList.select("a")

        val restaurants: MutableList<Restaurant> = mutableListOf()
        for (element in elements) {

            val endpoint = element.attr("href")
            val restaurantListItem = element.selectFirst("div[class=restaurant-list-item-row-1]")

            if (isRestaurantClosed(element)) {
                break
            }

            val deliveryFee = getRestaurantInfoElement(restaurantListItem, "div[class=restaurant-payment-infos-delivery-fee]")
            val minimumOrderValue = getRestaurantInfoElement(restaurantListItem, "div[class=restaurant-payment-infos-minimum-order-value]")
            val distance = getRestaurantInfoElement(restaurantListItem, "div[class=restaurant-distance-info]")
            val name = restaurantListItem.select("h2[class=restaurant-list-item-name]").text()
            val categories = restaurantListItem.select("p[class=restaurant-list-item-food-types]").text()
                    .split(",")
                    .map { it.trim() }

            restaurants.add(Restaurant(name, minimumOrderValue, distance, deliveryFee, baseUrl + endpoint, categories))
        }
        return restaurants
    }

    private fun isRestaurantClosed(element: Element) =
            element.select("div[class=restaurant-closed-info-overlay]").size > 0

    private fun getRestaurantInfoElement(element: Element, cssQuery: String) = element
            .select(cssQuery)
            .select("span")
            .not("span[class=restaurant-payment-infos-suffix]")
            .text()
            .replace(",", ".")
            .toFloat()
}