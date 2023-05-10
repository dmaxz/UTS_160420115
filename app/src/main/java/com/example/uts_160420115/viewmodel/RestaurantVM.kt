package com.example.uts_160420115.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420115.model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RestaurantVM(application: Application): AndroidViewModel(application) {
    val restaurantLD = MutableLiveData<Restaurant>()
    private var queue: RequestQueue? = null
    fun fetch(restoId: Int){

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/resto.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{ it ->
                val sType = object : TypeToken<List<Restaurant>>() {}.type
                val result = Gson().fromJson<List<Restaurant>>(it,sType)
                result.forEach {
                    if (it.id == restoId){
                        restaurantLD.value = it as Restaurant?
                    }

                }

//                studentLD.value = result as Restaurant?
//                Log.d("showvoley", result.toString())
            },{
//                Log.d("showvoley", it.toString())
//                studentLoadErrorLD.value = true
//                loadingLD.value = false
            }
        )

        queue?.add(stringRequest)
//        println(studentLD)
//        val student1 = Student("16055", "Nonie", "1998/03/28","5718444778","http://dummyimage.com/75x100" +
//                ".jpg/cc0000/ffffff")
//        studentLD.value = student1
    }
}