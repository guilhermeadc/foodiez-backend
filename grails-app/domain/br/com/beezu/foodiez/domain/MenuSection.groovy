package br.com.beezu.foodiez.domain

class MenuSection {

    String name
    String description
    
    static belongsTo = [menu: Menu]
    static hasMany = [dishes: Dish]

    static constraints = {
    	menu nullable: false
    	name nullable: false
    	description nullable: true
    }

    @Override
    String toString(){
    	return name
    }
}

