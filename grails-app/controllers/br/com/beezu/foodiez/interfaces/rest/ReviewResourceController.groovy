package br.com.beezu.foodiez.interfaces.rest

import br.com.beezu.foodiez.domain.*
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReviewResourceController extends RestfulController<Review> {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ReviewResourceController() {
        super(Review)
    }

    @Override
    def show() {                
        respond queryForResource(params.id), [status: OK, includes: includeFields, excludes: ['class','dish']]
    }

    @Override
    def index(final Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond listAllResources(params), [includes: includeFields, excludes: ['class','dish']]
    }    

    @Override
    protected Review queryForResource(Serializable id) {
        def dishId = params.DishResourceId
        return Review.find { id == id && dish.id == dishId }
    }

    @Override
    protected List<Review> listAllResources(Map params) {
        resource.findAll(params) {
            dish.id == params.DishResourceId
        }
    }

    private getIncludeFields() {
        params.fields?.tokenize(',')
    }
}
