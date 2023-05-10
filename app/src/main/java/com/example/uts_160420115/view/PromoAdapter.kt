package com.example.uts_160420115.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.model.Promo


class PromoAdapter (val promo:ArrayList<Promo>): RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {
    class PromoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.promo_card, parent, false)
        return PromoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promo.size
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        val txtCode = holder.view.findViewById<TextView>(R.id.txtCodePCard)
        val txtDisc = holder.view.findViewById<TextView>(R.id.txtDiscPCard)
        txtCode.text = "Kode: "+promo[position].code
        txtDisc.text = "Promo: "+promo[position].discount.toString()+"%"
    }

    fun updateRestaurants(newpromo: ArrayList<Promo>){
        promo.clear()
        promo.addAll(newpromo)
        notifyDataSetChanged()
    }
}