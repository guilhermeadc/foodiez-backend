import br.com.beezu.foodiez.domain.*
import grails.converters.*

class BootStrap {

    def init = { servletContext ->

        createRestaurant("4caca9ce14c337041d47f23b").save()
        createRestaurant("4f885571e4b0e81f666d9662").save()            
        createRestaurant("4d19f3da1356a093c7b6ed82").save()
        createRestaurant("50d37b3ae4b04d6aedfd5bba").save()
        createRestaurant("50aff159e4b07ba2ce458242").save()
        createRestaurant("4bad2d03f964a520df353be3").save()
        createRestaurant("51b66034498e86980c7c3cac").save()
        createRestaurant("4b9245a8f964a52057ef33e3").save()
        createRestaurant("4bd394efa8b3a593aa666a5f").save()
    }

    def destroy = {
    }

    def createRestaurant(venueId) {
        def restaurant = new Restaurant(venueId: venueId)
        restaurant.menu.name = "Menu Principal"
        restaurant.menu.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi a sagittis magna."
        populateMenu(restaurant.menu)
        return restaurant
    }

    def populateMenu(menu) {        
        def menuSection
        def sectionNames = ["Aperitivos", "Refeição", "Carnes", "Saladas", "Bebidas", "Sobremesas"]
        for(element in sectionNames) {
            menuSection = new MenuSection(name: element)
            populateDishes(menuSection)            
            menu.addToSections(menuSection)
        }
    }

    def populateDishes(menuSection) {        
        def dishesNames = ["Prato 01", "Prato 02", "Prato 03", "Prato 04", "Prato 05", "Prato 06"]
        def random = new Random()
        def price = 0
        def description
        for(element in dishesNames) {
            price = Math.abs(random.nextInt() % 100 + 1)
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            menuSection.addToDishes(name: element, price: price, cuisine: menuSection.name, description: description)
        }
    }    
}
