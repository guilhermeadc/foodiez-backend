package br.com.beezu.foodiez.infrastructure.persistence

import br.com.beezu.foodiez.domain.Restaurant
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Mock(Restaurant)
class RestaurantRepositorySpec extends Specification {

    def restaurantRepository

    def setup() {
        //TODO: Verificar a possibilidade de encapsular a pesquisa no foursquare em outra classe
        restaurantRepository = createStubRepository()
    }

    def cleanup() {
    }

    def "Test get restaurant"() {
        when:
        def restaurant = restaurantRepository.detail("40a55d80f964a52020f31ee3")

        then:
        restaurant != null
    }

    def createStubRepository() {
        return Spy(RestaurantRepository) {
            findVenueData(_) >> {
                response:
                {
                    venue:
                    {
                        id: "40a55d80f964a52020f31ee3"
                        name: "Clinton St. Baking Co. & Restaurant"
                    }
                }
            }
        }
    }
}