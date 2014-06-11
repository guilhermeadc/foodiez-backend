
class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/restaurants"(resources:"Restaurant")
        //"/api/menus"(resources:"MenuResource")

        "/"(view:"/index")
        "500"(view:'/error')
    }
}