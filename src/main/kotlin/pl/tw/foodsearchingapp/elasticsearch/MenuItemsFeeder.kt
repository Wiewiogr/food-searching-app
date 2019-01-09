package pl.tw.foodsearchingapp.foodsearch

import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.ElasticsearchClient
import pl.tw.foodsearchingapp.model.MenuItem


class MenuItemsFeeder(private val client: ElasticsearchClient,
                      private val om: ObjectMapper) {

    fun feed(item: MenuItem) {
        client.
    }
}