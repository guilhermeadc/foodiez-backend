
class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/restaurants"(resources:"restaurantResource", version:'1.0', namespace:'v1') {
            "/menu"(resource:"menu", version:'1.0', namespace:'v1')             
        }

        "/api/menus"(resources:"menu", version:'1.0', namespace:'v1') 

        "/"(view:"/index")
        "500"(view:'/error')
    }
}