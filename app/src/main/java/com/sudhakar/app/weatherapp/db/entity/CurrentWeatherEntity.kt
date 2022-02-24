package com.sudhakar.app.weatherapp.db.entity

import android.os.Parcelable
import androidx.room.*
import com.sudhakar.app.weatherapp.domain.model.CurrentWeatherResponse
import com.sudhakar.app.weatherapp.domain.model.WeatherItem
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "CurrentWeather")
data class CurrentWeatherEntity(
    @ColumnInfo(name = "visibility")
    var visibility: Int?,
    @ColumnInfo(name = "timezone")
    var timezone: Int?,
    @Embedded
    var main: MainEntity?,
    @Embedded
    var clouds: CloudsEntity?,
    @ColumnInfo(name = "dt")
    var dt: Long?,
    @ColumnInfo(name = "weather")
    val weather: List<WeatherItem?>? = null,
    @ColumnInfo(name = "name")
    val name: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "base")
    val base: String?,
    @Embedded
    val wind: WindEntity?
) : Parcelable {
    @Ignore
    constructor(currentWeather: CurrentWeatherResponse) : this(
        visibility = currentWeather.visibility,
        timezone = currentWeather.timezone,
        main = MainEntity(currentWeather.main),
        clouds = CloudsEntity(currentWeather.clouds),
        dt = currentWeather.dt?.toLong(),
        weather = currentWeather.weather,
        name = currentWeather.name,
        id = 0,
        base = currentWeather.base,
        wind = WindEntity(currentWeather.wind)
    )

    fun getCurrentWeather(): WeatherItem? {
        return weather?.first()
    }

}
