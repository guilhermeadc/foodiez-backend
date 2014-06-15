package br.com.beezu.foodiez.domain

class Restaurant {

    String venueId
    String name
    
    static hasOne = [menu: Menu]

    static constraints = {
        venueId nullable: false
        name nullable: true
    }

    Restaurant() {
        this.menu = new Menu(this)
    }

    @Override
    String toString() {
		return name ?: venueId    		
    }
}
