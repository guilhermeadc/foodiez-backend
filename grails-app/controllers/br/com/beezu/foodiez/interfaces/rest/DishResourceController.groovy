package br.com.beezu.foodiez.interfaces.rest

import grails.converters.*
import org.codehaus.groovy.grails.web.converters.marshaller.json.*
import javax.annotation.PostConstruct
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
        JSON.use("dish-list") {
            params.max = Math.min(max ?: 10, 100)
            respond listAllResources(params), [status: OK, includes: includeFields, excludes: ['class','reviews']]
        }
    }    

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }

    @javax.annotation.PostConstruct 
    void postConstruct() {
          
        JSON.createNamedConfig("dish-list") {

            it.registerObjectMarshaller(Dish) { Dish dish, JSON json ->
                def marshaller = new DeepDomainClassMarshaller(true, grailsApplication)
                marshaller.marshalObject(dish, json)
            }

            it.registerObjectMarshaller(MenuSection) { MenuSection section, JSON json ->
                def marshaller = new DeepDomainClassMarshaller(true, grailsApplication)
                json.setExcludes(section.getClass(), ['class', 'dishes', 'menu'])
                marshaller.marshalObject(section, json)
            }

            // it.registerObjectMarshaller(MenuSection, 0) { MenuSection section, JSON json ->
            //     def output = [:]
            //     output['id'] = section.id
            //     output['name'] = section.name
            //     output['restaurantId'] = section.menu?.restaurant?.id
            //     output['restaurantName'] = section.menu?.restaurant?.name
            //     return output            
            // }
        }
    }
}