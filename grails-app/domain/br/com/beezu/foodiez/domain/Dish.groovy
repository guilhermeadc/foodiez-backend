package br.com.beezu.foodiez.domain

class Dish {

    String name
    String description
    String cuisine
    BigDecimal price
    Double rating

    static belongsTo = [menuSection: MenuSection]
    static hasMany   = [reviews: Review, photos: Photo]

    static constraints = {
        menuSection nullable: false
        name nullable: false
        description nullable: true
        cuisine nullable: true
        price nullable: true 
        rating nullable: true, scale: 1
    }

    Double getRating() {
        def dishId = this.id ?: 0l
        def rating = 0
        try {
            rating = Review.executeQuery(
                "select avg(rating) from Review where dish.id = :DishId", 
                [DishId: dishId])[0]
        }
        catch(Exception err) { log.error(err.message) }
    }

    def getRestaurant() {
        return menuSection?.menu?.restaurant
    }

    @Override
    String toString() {
        return name
    }
}