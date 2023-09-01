package com.example.superheroescertificacion.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.superheroescertificacion.R
import com.example.superheroescertificacion.databinding.FragmentDetalleBinding

private const val ARG_PARAM1 = "id"
class DetalleFragment : Fragment() {

    private var param1: Int = 0
    private val heroeVM: HeroeViewModel by activityViewModels()
    lateinit var binding: FragmentDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleBinding.inflate(layoutInflater, container, false)
        heroeVM.getDetalleHeroe(param1)
        initDetalle()

        return binding.root
    }

    private fun initDetalle() {
        heroeVM.detalleLiveData(param1).observe(viewLifecycleOwner){
            if(it != null){
                binding.txtNombreDetalle.text = it.nombre
                binding.txtOrigen.text = it.origen
                binding.imgHeroeDetail.load(it.link)
                binding.txtPoder.text = it.poder
                binding.txtCreacion.text = it.creacion.toString()
                binding.txtColor.text = it.color
                if(it.traduccion){
                    binding.txtTraduccion.text = "Cuenta con Traduccion al español"
                }else{
                    binding.txtTraduccion.text = "Sin Traduccion"
                }
            }
        }
    }


}