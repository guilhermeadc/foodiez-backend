package br.com.beezu.foodiez.domain

class Menu {

    String name
    String description

    static hasMany = [sections: MenuSection]
    static belongsTo = [restaurant: Restaurant]

    Menu(Restaurant restaurant){
        this.restaurant = restaurant
    }

    static constraints = {
        restaurant nullable: false
        name nullable: false
        description nullable: true
    }
}
