package br.com.cep.repository

import androidx.lifecycle.liveData
import br.com.cep.service.ApiHelper
import java.net.ConnectException

/**
 * Created by RubioAlves on 28/04/2021
 */
class EnderecoRepository(private val apiHelper: ApiHelper)  {

    sealed class Resultado<out R> {
        data class Sucesso<out T>(val dado: T?) : Resultado<T?>()
        data class Erro(val exception: Exception) : Resultado<Nothing>()
    }

    fun buscaEndereco(cep:String)  = liveData{
        try {
            val resposta = apiHelper.getEndereco(cep)

            if(resposta.isSuccessful){
                emit(Resultado.Sucesso(dado = resposta.body()))
            } else {
                emit(Resultado.Erro(exception = Exception("Endereço não encontrado")))
            }
        } catch (e: ConnectException) {
            emit(Resultado.Erro(exception = Exception("Falha na comunicação com API")))
        }
        catch (e: Exception) {
            emit(Resultado.Erro(exception = e))
        }
    }

}