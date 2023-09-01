package com.example.superheroescertificacion.vistas

import android.content.Intent
import android.net.Uri
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
                binding.txtNombreDetalle.text ="${getString(R.string.nombre_del_heroe)}${it.nombre}"
                binding.txtOrigen.text = "${getString(R.string.lugar_de_origen)}${it.origen}"
                binding.imgHeroeDetail.load(it.link)
                binding.txtPoder.text = "${getString(R.string.su_poder)}${it.poder}"
                binding.txtCreacion.text = "${getString(R.string.fecha_de_creacion)}${it.creacion}"
                binding.txtColor.text = "${getString(R.string.color_de_su_traje)}${it.color}"
                if(it.traduccion){
                    binding.txtTraduccion.text = getString(R.string.traduccion_true)
                }else{
                    binding.txtTraduccion.text = getString(R.string.traduccion_false)
                }
                val voto = it.nombre
                binding.btnCorreo.setOnClickListener {
                    enviarCorreo(voto)
                }
            }
        }


    }

    private fun enviarCorreo(voto: String) {
        //mail cliente
        val destinatario = getString(R.string.destinatario_email)
        val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(destinatario))
        intentEmail.type = "plain/text"
        //Donde llegan
        intentEmail.putExtra(Intent.EXTRA_EMAIL,arrayOf(destinatario))
        //Titulo Mail
        intentEmail.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.asunto_email,voto))
        //Body Mail
        intentEmail.putExtra(Intent.EXTRA_TEXT,getString(R.string.body_email,voto))

        startActivity(Intent.createChooser(intentEmail, "Consulta producto"))
    }


}