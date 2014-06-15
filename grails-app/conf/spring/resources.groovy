import grails.rest.render.hal.*
import grails.rest.render.json.*
import br.com.beezu.foodiez.domain.*

// Place your Spring DSL code here
beans = {		
	for (domainClass in grailsApplication.domainClasses) {
    	"json${domainClass.shortName}Renderer"(JsonRenderer, domainClass.clazz) { excludes = ['class', 'restaurant.class'] }
    	"json${domainClass.shortName}CollectionRenderer"(JsonCollectionRenderer, domainClass.clazz)  { excludes = ['class', 'restaurant.class'] }
    	"hal${domainClass.shortName}Renderer"(HalJsonRenderer, domainClass.clazz)  { excludes = ['class'] }
    	"hal${domainClass.shortName}CollectionRenderer"(HalJsonCollectionRenderer, domainClass.clazz)  { excludes = ['class'] }    	
	} 	
}	