import br.com.beezu.foodiez.*
import br.com.beezu.foodiez.domain.Restaurant

class BootStrap {

    def init = { servletContext ->
        new Restaurant(venueId: "4caca9ce14c337041d47f23b").save()
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
}
