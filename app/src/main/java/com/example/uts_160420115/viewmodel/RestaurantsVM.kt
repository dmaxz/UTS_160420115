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
import com.example.uts_160420115.model.User

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RestaurantsVM(application: Application): AndroidViewModel(application) {
    val restaurantLD = MutableLiveData<ArrayList<Restaurant>>()
    val restaurantLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null
    fun refresh(){
        loadingLD.value = true
        restaurantLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/resto.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                loadingLD.value = false
                val sType = object : TypeToken<List<Restaurant>>() {}.type
                val result = Gson().fromJson<List<Restaurant>>(it,sType)
                restaurantLD.value = result as ArrayList<Restaurant>?
                Log.d("showvoley", result.toString())
            },{
                Log.d("showvoley", it.toString())
                restaurantLoadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
//        studentsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
//                    ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )
//        studentLoadErrorLD.value = false
//        loadingLD.value = false
    }

    suspend fun refreshSus()= suspendCoroutine<ArrayList<Restaurant>?>{ cont ->
        loadingLD.postValue(true)
        restaurantLoadErrorLD.postValue(false)

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/resto.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                loadingLD.postValue(false)
                val sType = object : TypeToken<List<Restaurant>>() {}.type
                val result = Gson().fromJson<List<Restaurant>>(it,sType)
                cont.resume(result as ArrayList<Restaurant>?)
//                userLD.value = filtered as ArrayList<User>?
                Log.d("showvoleySus_Restaurant", result.toString())
            },{
                Log.d("showvoleySus_Restaurant", it.toString())
                restaurantLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}