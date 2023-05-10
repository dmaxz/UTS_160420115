package com.example.uts_160420115.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.uts_160420115.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url: String?){
    Picasso.get().load(url).resize(400,400).centerCrop().error(R.drawable.error).into(this, object:
        Callback {
        override fun onSuccess() {

        }

        override fun onError(e: Exception?) {
        }
    })

}