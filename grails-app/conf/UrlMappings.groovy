
class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/dishes"(resources:"DishResource") {
            "/photos"(resources:"PhotoResource")
            "/reviews"(resources:"ReviewResource")
        }
        "/api/restaurants"(resources:"RestaurantResource") {
            "/menu"(resource:"MenuResource")            
        }

        "/"(view:"/index")
        "500"(view:'/error')
    }
}