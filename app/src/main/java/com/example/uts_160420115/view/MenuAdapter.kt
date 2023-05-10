package com.example.uts_160420115.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.model.Food


class MenuAdapter (val foods:ArrayList<Food>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    class MenuViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_card, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val txtNama = holder.view.findViewById<TextView>(R.id.txtFoodMCard)
        val txtDescription = holder.view.findViewById<TextView>(R.id.txtDescMCard)
        txtNama.text = "Nama: "+foods[position].name
        txtDescription.text = "Deskripsi: "+foods[position].description
//        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetailF)
//        txtID.text = restaurants[position].id.toString()
//        txtName.text = restaurants[position].name
//        btnDetail.setOnClickListener {
//            val action = MainFragmentDirections.actionGameFragment(playerName)
//            Navigation.findNavController(it).navigate(action)
//            val id = txtID.text.toString().toInt()
//            val action = MainFragmentDirections.actionMainFragmentToRestaurantFragment(id)
//            Navigation.findNavController(it).navigate(action)
//        }
//        var imageView = holder.view.findViewById<ImageView>(R.id.restoImgView)
//        imageView.loadImage("https://picsum.photos/400/400")
    }

    fun updateRestaurants(newFoods: ArrayList<Food>){
        foods.clear()
        foods.addAll(newFoods)
        notifyDataSetChanged()
    }
}