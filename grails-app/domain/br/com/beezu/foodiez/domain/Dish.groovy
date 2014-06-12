package br.com.beezu.foodiez.domain

class Dish {

    String name
    String description
    String cuisine
    BigDecimal price

    static belongsTo = [menuSection: MenuSection]
    static hasMany = [additions: String, options: String]

    static constraints = {
        menuSection nullable: false
        name nullable: false
        description nullable: true
        cuisine nullable: true
        price nullable: true 
        options nullable: true
        additions nullable: true
    }

    @Override
    String toString() {
        return name
    }
}
