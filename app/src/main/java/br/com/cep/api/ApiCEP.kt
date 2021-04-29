package br.com.cep.api

import br.com.cep.domain.CEP
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by RubioAlves on 28/04/2021
 */
interface ApiCEP {

    @GET("{CEP}")
    suspend fun buscaEndereco(@Path("CEP") cep: String): Response<CEP>
}