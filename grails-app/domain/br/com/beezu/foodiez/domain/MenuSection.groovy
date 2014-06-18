package br.com.beezu.foodiez.domain

class MenuSection {

    String name
    String description
    Boolean isDefault = false
    
    static belongsTo = [menu: Menu]
    static hasMany = [dishes: Dish]

    static constraints = {
    	menu nullable: false
    	name nullable: false
    	description nullable: true
    }

    static mapping = {
        dishes lazy: "join"
    }

    @Override
    String toString(){
    	return name
    }
}

