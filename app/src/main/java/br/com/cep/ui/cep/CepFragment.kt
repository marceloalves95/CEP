package br.com.cep.ui.cep

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.cep.R
import br.com.cep.databinding.FragmentCepBinding
import br.com.cep.repository.EnderecoRepository
import br.com.cep.service.ApiHelper
import br.com.cep.service.RetrofitBuilder
import br.com.cep.viewmodel.EnderecoViewModel
import br.com.cep.viewmodel.EnderecoViewModelFactory
import com.google.android.material.snackbar.Snackbar

/**
 * Created by RubioAlves on 28/04/2021
 */
class CepFragment : Fragment() {

    private var _binding: FragmentCepBinding? = null
    private val binding get() = _binding!!
    lateinit var latitude:String
    lateinit var longitude:String


    val viewModel by lazy {

       ViewModelProviders.of(this, EnderecoViewModelFactory(ApiHelper(RetrofitBuilder.apiCEP))).get(EnderecoViewModel::class.java)

   }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCepBinding.inflate(inflater, container, false)

        binding.buscar.setOnClickListener { buscarCEP() }
        binding.buttonGoogleMaps.setOnClickListener {

            enviarDados()

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun buscarCEP(){

        val cep = binding.cep.editableText.toString().trim()
        val cepNovo = cep.replace("-", "")
        binding.progressBar.visibility = View.VISIBLE
        viewModel.buscaEnderecoPelo(cepNovo).observe(viewLifecycleOwner, { resultado->

            when (resultado) {

                is EnderecoRepository.Resultado.Sucesso -> {

                    resultado.dado?.let { cep->

                        binding.address.text = cep.address
                        binding.district.text = cep.district
                        binding.city.text = cep.city
                        binding.state.text = cep.state

                        latitude = cep.lat
                        longitude = cep.lng

                        binding.group.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }

                }
                is EnderecoRepository.Resultado.Erro -> {
                    mensagemAtualizada(resultado.exception.message.toString())
                    Log.d("Resultado", resultado.exception.message.toString())
                    binding.group.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.GONE
                }


                else -> {


                }
            }

        })



    }

    private fun mensagemAtualizada(mensagem: String) {
        Snackbar.make(requireView(), mensagem, Snackbar.LENGTH_SHORT).show()
    }

    private fun enviarDados(){

        val action = CepFragmentDirections.actionCepFragmentToMapsCepFragment(latitude, longitude)
        findNavController().navigate(action)

    }


}

