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

    @Override
    String toString() {
        return name
    }
}
