package br.com.beezu.foodiez.interfaces.rest

import grails.converters.*
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import br.com.beezu.foodiez.domain.*

@Transactional(readOnly = true)
class RestaurantResourceController extends RestfulController<Restaurant> {

    static responseFormats = ["json", "xml", "hal"]

    def restaurantRepository

    RestaurantResourceController() {
        super(Restaurant)
    }

    @Override
    def show() {
        
        JSON.use("deep") {
            respond queryForResource(params.id), [status: OK, includes: includeFields, excludes: ['class','menu']]
        }
    }
 
    @Override
    def index(final Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond listAllResources(params), [status: OK, includes: includeFields, excludes: ['class','menu']]
    }

    @Override
    protected Restaurant queryForResource(Serializable id) {
        return restaurantRepository.detail(id)
    }

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }
}
