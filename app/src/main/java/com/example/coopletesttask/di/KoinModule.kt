package com.example.coopletesttask.di

import androidx.room.Room
import com.example.coopletesttask.data.repository.CityInfoRepository
import com.example.coopletesttask.data.repository.CityLocationRepository
import com.example.coopletesttask.data.repository.CityWeatherRepository
import com.example.coopletesttask.data.repository.ICityInfoRepository
import com.example.coopletesttask.data.repository.ICityLocationRepository
import com.example.coopletesttask.data.repository.ICityWeatherRepository
import com.example.coopletesttask.data.sourse.BackendApi
import com.example.coopletesttask.data.sourse.db.AppDatabase
import com.example.coopletesttask.domain.usecase.CityLocationUseCase
import com.example.coopletesttask.domain.usecase.CityWeatherUseCase
import com.example.coopletesttask.presentation.searchCity.SearchCityLocationViewModel
import com.example.coopletesttask.presentation.weatherForecast.WeatherForecastViewModel
import com.example.coopletesttask.util.Constants.BASE_URL
import com.example.coopletesttask.util.Constants.CITY_LOCATION_DATABASE
import com.google.gson.Gson
import de.jensklingenberg.ktorfit.converter.builtin.FlowConverterFactory
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.gson.gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import timber.log.Timber

private const val LOGGER_TAG_HTTP_CLIENT = "HTTP Client"
private const val TEN_SECONDS_TIMEOUT = 30000L


val appModule = module {
    single { Gson() }
    single<Logger> {
        object : Logger {
            override fun log(message: String) {
                Timber.tag(LOGGER_TAG_HTTP_CLIENT).d(message)
            }
        }
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            CITY_LOCATION_DATABASE
        ).build()
    }

    single {
        HttpClient(Android) {
            install(ContentNegotiation) { gson() }
            install(Logging) {
                logger = get()
                level = LogLevel.ALL
            }
            install(HttpTimeout) { requestTimeoutMillis = TEN_SECONDS_TIMEOUT }
            install(ResponseObserver) {
                onResponse { response ->
                    Timber.tag("Android HttpClient").d("HTTP status: ${response.status.value}")
                }
            }
            expectSuccess = true
        }
    }

    single {
        ktorfit {
            httpClient(client = get())
            baseUrl(BASE_URL)
            converterFactories(FlowConverterFactory())
        }.create<BackendApi>()
    }

    single<ICityLocationRepository> { CityLocationRepository(get()) }
    single<ICityWeatherRepository> { CityWeatherRepository(get()) }
    single<ICityInfoRepository> { CityInfoRepository(get()) }
    single { CityLocationUseCase(get()) }
    single { CityWeatherUseCase(get()) }
    viewModelOf(::SearchCityLocationViewModel)
    viewModelOf(::WeatherForecastViewModel)
}