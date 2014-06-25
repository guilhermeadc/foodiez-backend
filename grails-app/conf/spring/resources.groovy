import grails.rest.render.hal.*
import grails.rest.render.json.*
import br.com.beezu.foodiez.domain.*
import br.com.beezu.foodiez.interfaces.rest.*

// Place your Spring DSL code here
beans = {		

	for (domainClass in grailsApplication.domainClasses) {
    	"json${domainClass.shortName}Renderer"(JsonRenderer, domainClass.clazz)
    	"json${domainClass.shortName}CollectionRenderer"(JsonCollectionRenderer, domainClass.clazz) { 
    		excludes = null // Correção de Bug do JsonCollectionRenderer - excludes = []
    	}

    	"hal${domainClass.shortName}Renderer"(HalJsonRenderer, domainClass.clazz) 
    	"hal${domainClass.shortName}CollectionRenderer"(HalJsonCollectionRenderer, domainClass.clazz)
	} 		
}	