package com.dicoding.gotrip.utils

fun countStar(rating: Double): Int {
    return when (rating) {
        in 0.0..2.0 -> 1
        in 2.0..4.0 -> 2
        in 4.0..6.0 -> 3
        in 6.0..8.0 -> 4
        in 8.0..10.0 -> 5
        else -> 0
    }
}