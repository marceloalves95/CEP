package br.com.cep.service

import br.com.cep.api.ApiCEP

/**
 * Created by RubioAlves on 28/04/2021
 */
class ApiHelper (private val api:ApiCEP) {

    suspend fun getEndereco(cep:String) = api.buscaEndereco(cep)

}