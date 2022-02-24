package com.sudhakar.app.weatherapp.viewState

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.sudhakar.app.weatherapp.ui.search.SearchViewState
import com.sudhakar.app.weatherapp.util.interrnalServerError
import com.sudhakar.app.weatherapp.utils.domain.Status
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchViewStateTest {

    @Test
    fun `should return loading true when status is loading`() {
        // Given
        val givenViewState = SearchViewState(status = Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {
        // Given
        val givenViewState = SearchViewState(status = Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {
        // Given
        val givenViewState = SearchViewState(status = Status.ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should return correct error message when status is error`() {
        // Given
        val givenViewState =
            SearchViewState(
                status = Status.ERROR,
                error = interrnalServerError
            )

        // When
        val actualResult = givenViewState.getErrorMessage()

        // Then
        Truth.assertThat(actualResult).isEqualTo(interrnalServerError)
    }

    @Test
    fun `should return true for error placeholder visibility  when status is error`() {
        // Given
        val givenViewState =
            SearchViewState(
                status = Status.ERROR,
                error = interrnalServerError
            )

        // When
        val actualResult = givenViewState.shouldShowErrorMessage()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }
}
