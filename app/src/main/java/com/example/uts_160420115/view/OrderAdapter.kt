package com.example.uts_160420115.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.model.Order

class OrderAdapter (val orders:ArrayList<Order>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_card, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val txtFood = holder.view.findViewById<TextView>(R.id.txtFoodOCard)
        val txtRestaurant = holder.view.findViewById<TextView>(R.id.txtRestaurantOCard)
        val txtNote = holder.view.findViewById<TextView>(R.id.txtNoteOCard)
//        Log.e("order at ${position}",orders[position].notes)
        txtFood.text = "Makanan: ${orders[position].food?.name} (${orders[position].quantity})"
        txtRestaurant.text = "Restaurant: "+orders[position].restaurant?.name
        txtNote.text = "Note: "+orders[position].notes

    }

    fun updateOrders(newOrders: ArrayList<Order>){
        orders.clear()
        orders.addAll(newOrders)
        notifyDataSetChanged()
    }
}