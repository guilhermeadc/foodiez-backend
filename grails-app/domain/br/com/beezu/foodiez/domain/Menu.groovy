package br.com.beezu.foodiez.domain

class Menu {

    String name
    String description
    List sections = []

    static belongsTo = [restaurant: Restaurant]
    static hasMany   = [sections: MenuSection]

    static mapping = {
        sections lazy: "join"
    }

    static constraints = {
        restaurant nullable: false
        name nullable: true
        description nullable: true
    }

    Menu(Restaurant restaurant) {
        this.restaurant = restaurant
        
        if(!sections.size()) {
            MenuSection section = new MenuSection(menu: this, name: "Default", isDefault: true)
            sections.add(section)
        }
    }

    @Override
    String toString() {
        return name
    }
}