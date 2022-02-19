package com.sudhakar.app.weatherapp.utils



interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
