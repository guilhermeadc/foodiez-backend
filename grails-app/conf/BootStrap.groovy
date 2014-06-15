import br.com.beezu.foodiez.*
import br.com.beezu.foodiez.domain.Restaurant

class BootStrap {

    def init = { servletContext ->
        //Restaurant: 
        def restaurant = new Restaurant(venueId: "4caca9ce14c337041d47f23b")
        restaurant.menu.name = "Menu Principal"
        restaurant.menu.description = "O Outback Steakhouse é um restaurante informal, construído e decorado em estilo que remete ao interior da Austrália."
        insertMenuSection(restaurant.menu, ["APERITIVOS", "SALADAS ESPECIAIS", "FAVORITOS OUTBACK", "STEAKS OUTBACK", "NOSSA PESCARIA", "MASSAS", "PREMIUM BURGERS AND SANDWICHES", "SOBREMESAS IRRESISTÍVEIS", "LUNCH MENU", "KID'S MENU", "BEBIDAS", "VINHOS"])
        restaurant.save()        


        new Restaurant(venueId: "4f885571e4b0e81f666d9662").save()
        new Restaurant(venueId: "4d19f3da1356a093c7b6ed82").save()
        new Restaurant(venueId: "50d37b3ae4b04d6aedfd5bba").save()
        new Restaurant(venueId: "50aff159e4b07ba2ce458242").save()
        new Restaurant(venueId: "4bad2d03f964a520df353be3").save()
        new Restaurant(venueId: "51b66034498e86980c7c3cac").save()
        new Restaurant(venueId: "4b9245a8f964a52057ef33e3").save()
        new Restaurant(venueId: "4bd394efa8b3a593aa666a5f").save()
        new Restaurant(venueId: "4bd394efa8b3a593aa666a5f").save()
    }

    def destroy = {
    }

    def insertMenuSection(menu, sectionNames) {
        assert menu != null, "Parâmetro [menu] não pode ser nulo"
        assert sectionNames instanceof List , "Parâmetro [sectionNames] inválido"
        

        for(element in sectionNames) {
            menu.addToSections(name: element)
        }
    }
}
