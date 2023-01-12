package com.michaeldovoh.fixercurrencyconverter.domain.usecase



import com.michaeldovoh.fixercurrencyconverter.domain.entity.Currency
import com.michaeldovoh.fixercurrencyconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    configuration: Configuration,
    private val currencyRepository: CurrencyRepository,
) : UseCase<GetCurrenciesUseCase.Request,
        GetCurrenciesUseCase.Response>(configuration) {

    override fun process(request:Request): Flow<Response> =
        currencyRepository.getCurrencies()
            .map {
                Response(it)
            }


    object Request : UseCase.Request

    data class Response(
        val currencies: List<Currency>,
    ) : UseCase.Response
}