package br.com.beezu.foodiez.domain

class Restaurant {

    String venueId
    String name
    
    static hasOne = [menu: Menu]

    static constraints = {
        venueId nullable: false
        name nullable: true
    }

    static mapping = {
        menu lazy: "join"
    }

    Restaurant() {
        this.menu = new Menu(this)
    }

    @Override
    String toString() {
		return name ?: venueId    		
    }
}
