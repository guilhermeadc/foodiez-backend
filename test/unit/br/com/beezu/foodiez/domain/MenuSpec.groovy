package br.com.beezu.foodiez.domain

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class MenuSpec extends Specification {

	def restaurantTest
	def menu

    def setup() {
    	restaurantTest = new Restaurant()
    }

    def cleanup() {
    }

    void "test Menu constructor"() {

    	when: "Quando objeto do tipo Menu é instanciado"
    	menu = new Menu(restaurantTest)

    	then: "Então uma sessão padrão deverá ser criada"
    	menu.sections.size == 1
    	menu.sections[0].isDefault == true
    }
}
