import grails.rest.render.hal.*
import grails.rest.render.json.*
import br.com.beezu.foodiez.domain.*

// Place your Spring DSL code here
beans = {	
	jsonRestaurantRenderer(JsonRenderer, Restaurant)  { excludes = ['class']}
	jsonRestaurantCollectionRenderer(JsonCollectionRenderer, Restaurant) { excludes = ['class']}
	halRestaurantCollectionRenderer(HalJsonCollectionRenderer, Restaurant)
	halRestaurantRenderer(HalJsonRenderer, Restaurant) 

	//jsonMenuRenderer(JsonRenderer, Menu)  { excludes = ['class']}
	//jsonMenuCollectionRenderer(JsonCollectionRenderer, Menu)  { excludes = ['class']}
}	