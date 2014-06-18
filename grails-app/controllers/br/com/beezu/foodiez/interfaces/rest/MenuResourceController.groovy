package br.com.beezu.foodiez.interfaces.rest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.rest.RestfulController
import grails.converters.*
import br.com.beezu.foodiez.domain.*

@Transactional(readOnly = true)
class MenuResourceController extends RestfulController<Menu>{

    static responseFormats = ['json', 'xml']

    @Override
    def show() {

        JSON.use("deep") {
            respond queryForResource(params.restaurantResourceId), [status: OK, includes: includeFields, excludes: ['class']]
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
