package br.com.beezu.foodiez.domain

class Dish {

    String name
    String description
    String cuisine
    BigDecimal price

    static belongsTo = [menuSection: MenuSection]
    static hasMany   = [reviews: Review]

    static constraints = {
        menuSection nullable: false
        name nullable: false
        description nullable: true
        cuisine nullable: true
        price nullable: true 
    }

    def getRating() {
        return 0
    }

    def getRestaurant() {
        return menuSection?.menu?.restaurant
    }

    @Override
    String toString() {
        return name
    }
}
