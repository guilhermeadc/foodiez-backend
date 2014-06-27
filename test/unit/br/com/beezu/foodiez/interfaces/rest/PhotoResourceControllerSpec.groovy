package br.com.beezu.foodiez.interfaces.rest

import org.springframework.mock.web.MockMultipartFile
import br.com.beezu.foodiez.domain.*
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import grails.test.mixin.*
import spock.lang.*

@TestFor(PhotoResourceController)
@Mock(Photo)
class PhotoResourceControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params['name'] = 'someValidName'
        params['fileName'] = 'someValidFileName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The response is correct"
            response.status == OK.value
            response.text == ([] as JSON).toString()
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            // Make sure the domain class has at least one non-null property
            // or this test will fail.
            def photo = new Photo()
            controller.save(photo)

        then:"The response status is NOT_ACCEPTABLE"
            response.status == NOT_ACCEPTABLE.value

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            photo = new Photo(params)

            controller.save(photo)

        then:"The response status is CREATED and the instance is returned"
            response.status == CREATED.value
            response.text == (photo as JSON).toString()
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"The response status is NOT_FOUND"
            response.status == NOT_FOUND.value

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def photo = new Photo()
            controller.update(photo)

        then:"The response status is NOT_ACCEPTABLE"
            response.status == NOT_ACCEPTABLE.value

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            photo = new Photo(params).save(flush: true)
            controller.update(photo)

        then:"The response status is OK and the updated instance is returned"
            response.status == OK.value
            response.text == (photo as JSON).toString()
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A NOT_FOUND is returned"
            response.status == NOT_FOUND.value

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def photo = new Photo(params).save(flush: true)

        then:"It exists"
            Photo.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(photo)

        then:"The instance is deleted"
            Photo.count() == 0
            response.status == NO_CONTENT.value
    }

    void "Test the save action correctly persists photo image"() {
        given:
            def content = "Hello Word".getBytes();    
            def image = new MockMultipartFile("content", "test.txt", "text/plain", content);        

        when:"The save action is executed with a valid instance"
            populateValidParams(params)
            def photo = new Photo(params)
            def fileName = controller.saveImageFile(photo, image)

        then:"The response status is CREATED and the instance is returned"
            !fileName.isEmpty()
            //fileName ~= "/tmp/foodiez/testDir/.+"
    }
}
