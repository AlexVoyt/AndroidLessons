package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.Creature

class CreatureAdapter(private val items: List<Creature>) :
    RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var lvl: TextView? = null
        var att: TextView? = null
        var def: TextView? = null
        var min_d: TextView? = null
        var max_d: TextView? = null
        var hp: TextView? = null
        var spd: TextView? = null
        var grw: TextView? = null
        var value: TextView? = null
        var special: TextView? = null

        init {
            name = itemView.findViewById(R.id.name)
            lvl = itemView.findViewById(R.id.lvl)
            att = itemView.findViewById(R.id.att)
            def = itemView.findViewById(R.id.def)
            min_d = itemView.findViewById(R.id.min_d)
            max_d = itemView.findViewById(R.id.max_d)
            hp = itemView.findViewById(R.id.hp)
            spd = itemView.findViewById(R.id.spd)
            grw = itemView.findViewById(R.id.grw)
            value = itemView.findViewById(R.id.`val`)
            special = itemView.findViewById(R.id.special)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.creature_entry, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreatureAdapter.ViewHolder, position: Int) {
        holder.name?.text = items[position].name
        holder.lvl?.text = items[position].lvl.toString()
        holder.att?.text = items[position].att.toString()
        holder.def?.text = items[position].def.toString()
        holder.min_d?.text = items[position].minD.toString()
        holder.max_d?.text = items[position].maxD.toString()
        holder.hp?.text = items[position].hp.toString()
        holder.spd?.text = items[position].spd.toString()
        holder.grw?.text = items[position].grw.toString()
        holder.value?.text = items[position].value.toString()
        holder.special?.text = items[position].special.toString()

    }

    override fun getItemCount() : Int {
        return items.size
    }

}