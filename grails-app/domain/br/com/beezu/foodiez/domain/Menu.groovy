package br.com.beezu.foodiez.domain

class Menu {

    String name
    String description

    static belongsTo = [restaurant: Restaurant]
    static hasMany = [sections: MenuSection]    

    static constraints = {
        restaurant nullable: false
        name nullable: true
        description nullable: true
    }

    Menu(Restaurant restaurant) {
        this.restaurant = restaurant
    }

    @Override
    String toString(){
        return name
    }
}