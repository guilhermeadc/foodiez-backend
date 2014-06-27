package br.com.beezu.foodiez.domain

class Review {

	Integer rating
	String comment
	
	static belongsTo = [dish: Dish]

    static constraints = {
    	dish nullable: false
    	rating range: 0..5
    	comment size: 0..140
    }

    Review (Dish dish) {
    	this.dish = dish
    	rating = 0
    }
}
