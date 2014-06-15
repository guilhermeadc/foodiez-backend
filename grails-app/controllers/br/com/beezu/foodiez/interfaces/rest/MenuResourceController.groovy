package br.com.beezu.foodiez.interfaces.rest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import grails.converters.*
import br.com.beezu.foodiez.domain.*

@Transactional(readOnly = true)
class MenuResourceController extends RestfulController<Menu>{

    static responseFormats = ['json', 'xml']
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    //def show(Long id) {
    @Override
    def show() {
        def menu = queryForResource(params.restaurantResourceId)
        if(menu == null) {
            render status: NOT_FOUND
            return 
        }

        JSON.use("deep") {
            respond menu, [status: OK, includes: includeFields]
        }
    }

    @Override
    protected Menu queryForResource(Serializable id) {
        return Menu.find {restaurant.venueId == id}
    }

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }
}
