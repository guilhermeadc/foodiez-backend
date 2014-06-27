package br.com.beezu.foodiez.interfaces.rest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import br.com.beezu.foodiez.domain.*
import grails.rest.RestfulController

@Transactional(readOnly = true)
class PhotoResourceController extends RestfulController<Photo> {

    static responseFormats = ['json']
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    PhotoResourceController() {
        super(Photo)
    }

    @Override
    def show() {                
        respond queryForResource(params.id), [status: OK, includes: includeFields, excludes: []]
    }

    @Override
    def index(final Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond listAllResources(params), [status: OK, includes: includeFields, excludes: []]
    }    

    @Override
    protected Photo queryForResource(Serializable id) {
        def dishId = params.DishResourceId
        return Photo.find { id == id && dish.id == dishId }
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


    @Transactional
    @Override
    def save(Photo photoInstance) {
        if (photoInstance == null) {
            render status: NOT_FOUND
            return
        }

        def image = request.getFile("image")
        if(!image.isEmpty()) {
            photoInstance.fileName = saveImageFile(photoInstance, image)
        }

        photoInstance.validate()
        if (photoInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }


        photoInstance.save(flush:true)

        respond photoInstance, [status: CREATED]
    }

    @Transactional
    def update(Photo photoInstance) {
        if (photoInstance == null) {
            render status: NOT_FOUND
            return
        }

        photoInstance.validate()
        if (photoInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        photoInstance.save flush:true
        respond photoInstance, [status: OK]
    }

    @Transactional
    def delete(Photo photoInstance) {

        if (photoInstance == null) {
            render status: NOT_FOUND
            return
        }

        photoInstance.delete flush:true
        render status: NO_CONTENT
    }

    def saveImageFile(photoInstance, image) { 

        log.debug "Início do Método"
        return 

        assert photoInstance != null
        assert image != null

        def fileName = null
        if(!image.isEmpty()) {
            def destinationDir = grailsApplication.config.destinationImageDir
            def storagePath = new File(destinationDir)

            if(!storagePath.exists()){
                storagePath.mkdirs()
            }

            fileName = "$destinationDir/${UUID.randomUUID()}"
            image.transferTo(new File(fileName))
        }

        return fileName
    }
}