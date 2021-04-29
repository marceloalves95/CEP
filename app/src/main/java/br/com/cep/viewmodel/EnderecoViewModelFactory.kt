package br.com.cep.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.cep.repository.EnderecoRepository
import br.com.cep.service.ApiHelper

/**
 * Created by RubioAlves on 28/04/2021
 */
class EnderecoViewModelFactory  (private val apiHelper: ApiHelper) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnderecoViewModel::class.java)) {
            return EnderecoViewModel(EnderecoRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}