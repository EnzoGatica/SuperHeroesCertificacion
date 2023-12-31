package com.example.superheroescertificacion.vistas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroescertificacion.R
import com.example.superheroescertificacion.data.local.HeroeEntity
import com.example.superheroescertificacion.databinding.ItemHeroeBinding

class AdapterHeroes: RecyclerView.Adapter<AdapterHeroes.ItemHeroeViewHolder>() {

    lateinit var binding: ItemHeroeBinding
    private val listadoItemHeroes = mutableListOf<HeroeEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHeroeViewHolder {
        binding = ItemHeroeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemHeroeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listadoItemHeroes.size
    }

    override fun onBindViewHolder(holder: ItemHeroeViewHolder, position: Int) {
        val heroes = listadoItemHeroes[position]
        holder.bind(heroes)
    }

    fun setData(heroe: List<HeroeEntity>){
        this.listadoItemHeroes.clear()
        this.listadoItemHeroes.addAll(heroe)
        notifyDataSetChanged()
    }

    class ItemHeroeViewHolder(val heroesBinding: ItemHeroeBinding): RecyclerView.ViewHolder(heroesBinding.root) {

        fun bind(heroe: HeroeEntity){
            val bundle = Bundle()
            heroesBinding.txtNombre.text = heroe.nombre
            heroesBinding.imgHeroe.load(heroe.link)
            heroesBinding.cvItem.setOnClickListener{
                bundle.putInt("id",heroe.id)
                Navigation.findNavController(heroesBinding.root).navigate(R.id.action_listadoHeroes_to_detalleFragment,bundle)
            }

        }

    }

}