package com.example.uts_160420115.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.model.Restaurant
import com.example.uts_160420115.util.loadImage


class RestaurantsAdapter (val restaurants:ArrayList<Restaurant>): RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {
    class RestaurantViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.restaurant_card, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val txtName = holder.view.findViewById<TextView>(R.id.txtNameRsCard)
        val txtAddress = holder.view.findViewById<TextView>(R.id.txtAddressRsCard)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetailR)
        txtName.text = restaurants[position].name.toString()
        txtAddress.text = restaurants[position].address.toString()
        btnDetail.setOnClickListener {
//            val action = MainFragmentDirections.actionGameFragment(playerName)
//            Navigation.findNavController(it).navigate(action)
            val id = restaurants[position].id!!
            val action = MainFragmentDirections.actionMainFragmentToRestaurantFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.restoImgView)
        imageView.loadImage("https://picsum.photos/400/400")
    }

    fun updateRestaurants(newRestaurants: ArrayList<Restaurant>){
        restaurants.clear()
        restaurants.addAll(newRestaurants)
        notifyDataSetChanged()
    }
}