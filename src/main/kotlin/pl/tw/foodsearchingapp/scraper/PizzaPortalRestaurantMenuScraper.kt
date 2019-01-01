package pl.tw.foodsearchingapp.scraper

import org.jsoup.Jsoup
import pl.tw.foodsearchingapp.model.MenuItem
import java.math.BigDecimal


class PizzaPortalRestaurantMenuScraper(val baseUrl: String) {
    fun scrap(endpoint: String): List<MenuItem> {
        val items: MutableList<MenuItem> = mutableListOf()
        val document = Jsoup.connect("$baseUrl$endpoint").get()
        val categories = document.select("li[class=restaurant-menu-section]")

        for (category in categories) {
            val categoryName = category.selectFirst("div[class=restaurant-menu-section-name]").text()
            val menuProducts = document.select("div[class=restaurant-menu-product-info-container]")

            for (menuProduct in menuProducts) {
                val name = menuProduct.selectFirst("h3[class=restaurant-menu-product-name]").text()
                val description = menuProduct.selectFirst("p[class=restaurant-menu-product-description]").text()
                val price = menuProduct.selectFirst("div[class=restaurant-menu-product-price]")
                        .text()
                        .replace(",", ".")
                        .toDouble()
                items.add(MenuItem(name, description, categoryName, BigDecimal.valueOf(price)))
            }
        }
        return items
    }
}