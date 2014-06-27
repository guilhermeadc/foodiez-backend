package br.com.beezu.foodiez.domain



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PhotoController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Photo.list(params), [status: OK]
    }

    @Transactional
    def save(Photo photoInstance) {
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
}
