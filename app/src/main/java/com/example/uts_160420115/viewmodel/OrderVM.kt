package com.example.uts_160420115.viewmodel

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420115.model.Order
import com.example.uts_160420115.model.Restaurant

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.*
import kotlin.coroutines.resume


class OrderVM(application: Application): AndroidViewModel(application) {
    val orderLD = MutableLiveData<ArrayList<Order>>()
    val orderLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null
    private lateinit var userVM: UserVM
    private lateinit var menuVM: MenuVM
    private lateinit var restaurantsVM: RestaurantsVM
    fun refresh(userId: Int, fragment:Fragment){
        GlobalScope.launch {
            userVM = ViewModelProvider(fragment)[UserVM::class.java]
            restaurantsVM = ViewModelProvider(fragment)[RestaurantsVM::class.java]
            menuVM = ViewModelProvider(fragment)[MenuVM::class.java]
            val resultU = userVM.refreshSus()
            val resultR = restaurantsVM.refreshSus()
            val resultM = menuVM.refreshSus()
            Log.e("result_cour_Sus_User", resultU.toString())
            loadingLD.postValue(true)
            orderLoadErrorLD.postValue(false)

            queue = Volley.newRequestQueue(getApplication())
            var url = "https://kenhosting.ddns.net/anmp/order.json"

            val stringRequest = StringRequest(
                Request.Method.GET, url,{
                    loadingLD.postValue(false)
                    val sType = object : TypeToken<List<Order>>() {}.type
                    val result = Gson().fromJson<List<Order>>(it,sType)
                    Log.e("filtered_userId", userId.toString())
                    var filtered:ArrayList<Order> = ArrayList<Order>()
                    result.forEach { o ->
                        if ( o.user_id== userId){
                            Log.e("filtered", it.toString())
                            resultU?.forEach { u ->
                                if (u.id == o.user_id){
                                    o.user = u
                                }
                            }
                            resultM?.forEach {m ->
                                if (m.id == o.food_id){
                                    o.food = m
                                    resultR?.forEach { r ->
                                        if (r.id == m.restaurant_id){
                                            o.restaurant = r
                                        }
                                    }
                                }
                            }

                            filtered.add(o as Order)
                        }
                    }
                    orderLD.postValue(filtered as ArrayList<Order>?)
                    Log.d("showvoley", filtered.toString())
                },{
                    Log.d("showvoley", it.toString())
                    orderLoadErrorLD.postValue(true)
                    loadingLD.postValue(false)
                }
            )
            stringRequest.tag = TAG
            queue?.add(stringRequest)
        }


    }


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}