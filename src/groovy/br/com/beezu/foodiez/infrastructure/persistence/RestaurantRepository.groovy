package br.com.beezu.foodiez.infrastructure.persistence

import groovyx.net.http.RESTClient
import grails.util.Holders
import org.springframework.stereotype.Repository;

import static groovyx.net.http.ContentType.*

import br.com.beezu.foodiez.domain.IRestaurantRepository
import br.com.beezu.foodiez.domain.Restaurant

/**
 * Created by guilhermeadc on 08/06/14.
 */
@Repository("restaurantRepository")
class RestaurantRepository implements IRestaurantRepository {

    final static String VENUE_URL_RESOURCE = "https://api.foursquare.com/v2/venues/"

    def venueResource

    RestaurantRepository(){
        venueResource = new RESTClient(VENUE_URL_RESOURCE)
    }

    def detail(String venue){
        assert venue != null, "Parâmetro [venueId] não pode ser nulo"

        def restaurant = Restaurant.find { venueId == venue }

        try{
            def data = findVenueData(venue)
            restaurant = restaurant ?: new Restaurant()
            bindVenueData(restaurant, data?.response?.venue)
        }
        catch(HttpResponseException){}
        catch(Throwable ex){throw ex}

        return restaurant
    }

    def detail(Long restaurantId){
        assert restaurantId != null, "Parâmetro [restaurantId] não pode ser nulo"

        def restaurant = Restaurant.get(restaurantId)

        try{
            if(restaurant != null){
                def data = findVenueData(restaurant.venueId)
                bindVenueData(restaurant, data?.response?.venue)
            }
        }
        catch(HttpResponseException){}
        catch(Throwable ex){throw ex}

        return restaurant
    }

    def list(params){
        return Restaurant.list(params)
    }

    def count(){
        return Restaurant.count()
    }

    def save(restaurant) {
        assert restaurantInstance != null, "Parâmetro [restaurant] não pode ser nulo"
        assert !restaurant instanceof Restaurant, "Parâmentro [restaurant] inválido"

        restaurant.save(flush: true, failOnError: true)
    }

    private Restaurant bindVenueData(restaurant, venueData){
        assert restaurant != null, "Parâmetro [restaurant] não pode ser nulo"
        assert venueData != null, "Parâmetro [venueData] não pode ser nulo"

        restaurant.venueId = venueData?.id
        restaurant.name = venueData?.name

        return restaurant
    }

    private def findVenueData(venueId){
        assert venueId != null, "Parâmetro [venueId] não pode ser nulo"

        def query_params = [client_id: Holders.config.foursquare.clientId,
            client_secret: Holders.config.foursquare.clientSecret,
            v: Holders.config.foursquare.apiVersion]

        def response = venueResource.get(path: venueId, query: query_params)
        return response.data
    }
}
