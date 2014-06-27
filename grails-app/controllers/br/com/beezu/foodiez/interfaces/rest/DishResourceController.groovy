package br.com.beezu.foodiez.interfaces.rest

import grails.converters.*
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import br.com.beezu.foodiez.domain.*

@Transactional(readOnly = true)
class DishResourceController extends RestfulController<Dish> {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    DishResourceController() {
        super(Dish)   
    }
 
    @Override
    def show() {                
        respond queryForResource(params.id), [status: OK, includes: includeFields, excludes: ['class','reviews']]
    }

    @Override
    def index(final Integer max) {
        JSON.use("dish-list") {
            params.max = Math.min(max ?: 10, 100)
            respond listAllResources(params), [status: OK, includes: includeFields, excludes: ['class','reviews']]
        }
    }    

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }
}