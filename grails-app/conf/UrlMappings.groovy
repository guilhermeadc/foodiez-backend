
class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/dishes"(resources:"dishResource")
        "/api/restaurants"(resources:"restaurantResource") {
            "/menu"(resource:"menuResource")            
        }

        "/"(view:"/index")
        "500"(view:'/error')
    }
}