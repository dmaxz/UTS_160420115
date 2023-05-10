package com.example.uts_160420115.view
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420115.R
import com.example.uts_160420115.model.Comment


class CommentAdapter (val comments:ArrayList<Comment>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.comment_card, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val txtComment = holder.view.findViewById<TextView>(R.id.txtCommentCCard)
        val txtUser = holder.view.findViewById<TextView>(R.id.txtUserCCard)
        txtComment.text = "Komentar: "+comments[position].comment
        txtUser.text = "User: "+comments[position].user?.name.toString()
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

    fun updateRestaurants(newcomments: ArrayList<Comment>){
        comments.clear()
        comments.addAll(newcomments)
        notifyDataSetChanged()
    }
}