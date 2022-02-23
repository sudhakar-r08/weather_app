package com.sudhakar.app.weatherapp.viewState

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.sudhakar.app.weatherapp.ui.dashboard.ForecastViewState
import com.sudhakar.app.weatherapp.utils.domain.Status
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class ForecastViewStateTest {

    @Test
    fun `should return loading true when status is loading`() {
        // Given
        val givenViewState = ForecastViewState(status = Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {
        // Given
        val givenViewState = ForecastViewState(status = Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {
        // Given
        val givenViewState = ForecastViewState(status = Status.ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should return correct error message when status is error`() {
        // Given
        val givenViewState =
            ForecastViewState(
                status = Status.ERROR,
                error = "500 Internal Server Error"
            )

        // When
        val actualResult = givenViewState.getErrorMessage()

        // Then
        Truth.assertThat(actualResult).isEqualTo("500 Internal Server Error")
    }

    @Test
    fun `should return true for error placeholder visibility  when status is error`() {
        // Given
        val givenViewState =
            ForecastViewState(
                status = Status.ERROR,
                error = "500 Internal Server Error"
            )

        // When
        val actualResult = givenViewState.shouldShowErrorMessage()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }
}
