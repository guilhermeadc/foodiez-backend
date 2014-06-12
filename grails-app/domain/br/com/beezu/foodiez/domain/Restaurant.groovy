package br.com.beezu.foodiez.domain

class Restaurant {

    String venueId
    String name

    static hasMany = [menus: Menu]

    static transients = ['name']

    static constraints = {
        venueId nullable: false
    }

    @Override
    String toString() {
		return name ?: venueId    		
    }
}
