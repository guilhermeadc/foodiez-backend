package br.com.beezu.foodiez.domain

class Dish {

    String name
    String description
    String cuisine
    BigDecimal price
    static hasMany = [additions: String, options: String]

    static constraints = {
        name nullable: false
        description nullable: false
        price nullable: true
        name nullable: true
        options nullable: true
        additions nullable: true
    }
}
