package pl.tw.foodsearchingapp.api

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
class HomeController {

    @RequestMapping("/")
    fun home(): ResponseEntity<*> {
        val headers = HttpHeaders()
        headers.location = URI.create("swagger-ui.html")
        return ResponseEntity<Any>(headers, HttpStatus.MOVED_PERMANENTLY)
    }
}