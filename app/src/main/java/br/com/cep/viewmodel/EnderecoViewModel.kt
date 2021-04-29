package br.com.cep.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.cep.domain.CEP
import br.com.cep.repository.EnderecoRepository

/**
 * Created by RubioAlves on 28/04/2021
 */
class EnderecoViewModel(private val repository: EnderecoRepository): ViewModel()  {

    fun buscaEnderecoPelo(cep: String): LiveData<EnderecoRepository.Resultado<CEP?>> = repository.buscaEndereco(cep)

}