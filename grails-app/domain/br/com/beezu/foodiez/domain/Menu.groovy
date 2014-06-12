package br.com.beezu.foodiez.domain

class Menu {

    String name
    String description

    static belongsTo = [restaurant: Restaurant]
    static hasMany = [sections: MenuSection]    

    static constraints = {
        restaurant nullable: false
        name nullable: false
        description nullable: true
    }

    @Override
    String toString(){
        return name
    }
}
