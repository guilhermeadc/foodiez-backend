package br.com.beezu.foodiez.interfaces.rest

import grails.converters.*
import javax.annotation.PostConstruct;
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import br.com.beezu.foodiez.domain.*    

@Transactional(readOnly = true)
class DishResourceController extends RestfulController<Dish>{

    static responseFormats = ['json', 'xml']
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
        JSON.use("list") {
            params.max = Math.min(max ?: 10, 100)
            respond listAllResources(params), [status: OK, includes: includeFields, excludes: ['class','reviews']]
        }
    }    

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }

    @javax.annotation.PostConstruct 
    void postConstruct() {
        
        JSON.createNamedConfig("list"){
            it.registerObjectMarshaller(Dish) { Dish dish, JSON json ->
                def output = [:]
                output['restaurant'] = ['id': dish.restaurant.id, 'name': dish.restaurant.name]
                output['id'] = dish.id
                output['name'] = dish.name
                output['cuisine'] = dish.cuisine
                output['price'] = dish.price
                output['rating'] = 0                
                //return json.excludes
                return output
            }        
        }
    }
}