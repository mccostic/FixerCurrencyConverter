package com.michaeldovoh.fixercurrencyconverter.domain

import com.michaeldovoh.fixercurrencyconverter.domain.usecase.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import com.michaeldovoh.fixercurrencyconverter.domain.entity.Result
import com.michaeldovoh.fixercurrencyconverter.domain.entity.UseCaseException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert

class UseCaseTest {

    @ExperimentalCoroutinesApi
    private val testScheduler = TestCoroutineScheduler()
    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    @ExperimentalCoroutinesApi
    private val testScope = TestScope(testDispatcher)


    @ExperimentalCoroutinesApi
    private val configuration = UseCase.Configuration(testDispatcher)
    private val request = mock<UseCase.Request>()
    private val response = mock<UseCase.Response>()

    @ExperimentalCoroutinesApi
    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>



    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test Success`() = testScope.runTest {
        val result = useCase.execute(request).first()
        assertEquals(Result.Success(response), result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test CurrencyException`() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.CurrencyException(Throwable())
                }
            }

        }
        testScope.runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is UseCaseException.CurrencyException)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test UnknownException`() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw Throwable()
                }
            }

        }
        testScope.runTest {
            val result = useCase.execute(request).first()
            Assert.assertTrue((result as Result.Error).exception is UseCaseException.UnknownException)
        }
    }

}