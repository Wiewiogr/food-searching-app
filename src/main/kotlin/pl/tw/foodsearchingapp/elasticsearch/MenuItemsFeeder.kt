package pl.tw.foodsearchingapp.elasticsearch

import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.Client
import org.elasticsearch.common.xcontent.XContentType
import pl.tw.foodsearchingapp.model.MenuItem


class MenuItemsFeeder(private val client: Client,
                      private val om: ObjectMapper) {

    fun feed(item: MenuItem) {
        val writeValueAsString = om.writeValueAsString(item)
        println(writeValueAsString)
        try {
            client.prepareIndex("menu_items", "item")
                    .setSource(writeValueAsString, XContentType.JSON)
                    .execute()
                    .actionGet(10_000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}