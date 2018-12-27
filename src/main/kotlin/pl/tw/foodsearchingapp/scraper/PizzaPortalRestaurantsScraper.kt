package pl.tw.foodsearchingapp.scraper

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import pl.tw.foodsearchingapp.model.Restaurant


class PizzaPortalRestaurantsScraper {

    fun scrap(city: String, street: String, streetNumber: Int, postalCode: String, flatNumber: Int): List<Restaurant> {
        val document = Jsoup.connect("https://pizzaportal.pl/$city/restauracje/ul-${street.replace(" ", "-")}/$streetNumber/$postalCode/$flatNumber/").get()
        val elements = document.select("div[class=restaurant-list-item-row-1]")

        val restaurants: MutableList<Restaurant> = mutableListOf()
        for (element in elements) {
            if (isRestaurantClosed(element)) {
                break
            }

            val deliveryFee = getRestaurantInfoElement(element, "div[class=restaurant-payment-infos-delivery-fee]")
            val minimumOrderValue = getRestaurantInfoElement(element, "div[class=restaurant-payment-infos-minimum-order-value]")
            val distance = getRestaurantInfoElement(element, "div[class=restaurant-distance-info]")
            val name = element.select("h2[class=restaurant-list-item-name]").text()
            val categories = element.select("p[class=restaurant-list-item-food-types]").text()
                    .split(",")
                    .map { it.trim() }

            restaurants.add(Restaurant(name, minimumOrderValue, distance, deliveryFee, categories))
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