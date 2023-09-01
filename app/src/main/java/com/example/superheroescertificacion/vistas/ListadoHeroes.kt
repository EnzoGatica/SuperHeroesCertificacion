package com.example.superheroescertificacion.vistas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.superheroescertificacion.R
import com.example.superheroescertificacion.databinding.FragmentListadoHeroesBinding

class ListadoHeroes : Fragment() {

    lateinit var binding: FragmentListadoHeroesBinding
    private val heroeVM: HeroeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        binding = FragmentListadoHeroesBinding.inflate(layoutInflater,container,false)
        initAdapter()
        heroeVM.getAllHeroes()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterHeroes()
        binding.recyclerListado.adapter = adapter
        heroeVM.heroesLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }

    }


}