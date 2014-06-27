package br.com.beezu.foodiez.domain

class Photo {

	String name
	String fileName

	static belongsTo = [dish: Dish]

    static constraints = {
    	name nullable: false
    	fileName nullable: true
    }
}
