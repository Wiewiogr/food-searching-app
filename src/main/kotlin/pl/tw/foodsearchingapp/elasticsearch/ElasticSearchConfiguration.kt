package pl.tw.foodsearchingapp.elasticsearch

import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.TransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetAddress


@Configuration
class ElasticSearchConfiguration {

    @Bean
    fun elasticClient(): Client {

        val settings = Settings.builder()
                .put("cluster.name", "docker-cluster")
                .build()

        return PreBuiltTransportClient(settings)
                .addTransportAddress(TransportAddress(InetAddress.getByName("localhost"), 9300))
    }

    @Bean
    fun menuItemsFeeder(client: Client) = MenuItemsFeeder(client, ObjectMapper())

}