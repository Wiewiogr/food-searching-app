package pl.tw.foodsearchingapp.scraper

import spock.lang.Specification


class PizzaPortalRestaurantsScraperTest extends Specification {

    def "should scrap restaurant"() {
        given:
        def scraper = new PizzaPortalRestaurantsScraper()
        print scraper.scrap()
    }
}
