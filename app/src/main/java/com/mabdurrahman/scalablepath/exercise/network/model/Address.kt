package com.mabdurrahman.scalablepath.exercise.network.model

/**
 * Created by Mahmoud Abdurrahman (ma.abdurrahman@gmail.com) on 2019-03-15.
 */
data class Address(val street: String, val suite: String, val city: String, val zipcode: String, val geo: Geolocation)