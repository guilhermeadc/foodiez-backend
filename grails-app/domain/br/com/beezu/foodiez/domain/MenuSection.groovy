package br.com.beezu.foodiez.domain

class MenuSection {

    String name
    String description
    static hasMany = [dishes: Dish]

    static constraints = {
        name nullable: false
        name nullable: true
    }
}
