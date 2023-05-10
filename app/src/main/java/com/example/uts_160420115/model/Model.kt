package com.example.uts_160420115.model

import com.google.gson.annotations.SerializedName
import java.util.Date

//{"id":1,"name":"A7PU4XZrvjnWRMg","alamat":"v271Tgzf5Qt3KxlX9uj8GhScUwaFERYMHsq","phone":"64291603081"}
data class Restaurant(
    val id: Int?,
    val name: String?,
    @SerializedName("alamat")
    val address: String?,
    val phone: String?,
)

//{"id":1,"name":"5dfAi3UP9X","description":"AVwb6UeS3zxWy1Hj9LOPFi8tlvBRr7","restaurant_id":3}
data class Food(
    val id: Int?,
    val name: String?,
    val description: String?,
    val restaurant_id: Int?,
)

//{"id":1,"comment":"HYKwcpqUAh8bjrT71VuJBzmZLDaW24","user_id":5,"restaurant_id":4}
data class Comment(
    val id: Int?,
    val comment: String?,
    val user_id: Int?,
    val restaurant_id: Int?,
    var user: User? = null,
)

//{"id":1,"username":"ken","password":"ken","email":"ken@mail.com","name":"kenda","balance":275127,"phone":"91136357657"}
data class User(
    val id: Int?,
    val username: String?,
    val password: String?,
    val email: String?,
    val name: String?,
    val balance: Int?,
    val phone: String?,
//    val user_id: Int?,
//    val restaurant_id: Int?,
)

//{"id":1,"quantity":2,"notes":"TzOYIW43UjZlM985pyJBeHArDcSPax","user_id":6,"food_id":12,"date":"2030-11-27 22:49:06"}
data class Order(
    val id: Int?,
    val quantity: Int?,
    val notes: String?,
    val user_id: Int?,
    val food_id: Int?,
    val date: String?,
    var user: User? = null,
    var food: Food? = null,
    var restaurant: Restaurant? = null,
//    val user_id: Int?,
//    val restaurant_id: Int?,
)
//{"id":1,"code":"Yu1cO","discount":7,"restaurant_id":1}
data class Promo(
    val id: Int?,
    val code: String?,
    val discount: Int?,
    val restaurant_id: Int?,
//    val user_id: Int?,
//    val restaurant_id: Int?,
)