package br.com.beezu.foodiez.domain

import br.com.beezu.foodiez.domain.Restaurant

interface IRestaurantRepository {

    def detail(String venue)
    def list(params)
    def count()
    def save(restaurant)
}
