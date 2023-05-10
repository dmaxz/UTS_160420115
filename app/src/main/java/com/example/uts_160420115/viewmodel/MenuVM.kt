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
import com.example.uts_160420115.model.Food

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.*
import kotlin.coroutines.resume


class MenuVM(application: Application): AndroidViewModel(application) {
    val menuLD = MutableLiveData<ArrayList<Food>>()
    val menuLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null
    fun refresh(restoId: Int){

        loadingLD.value = true
        menuLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/food.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                loadingLD.value = false
                val sType = object : TypeToken<List<Food>>() {}.type
                val result = Gson().fromJson<List<Food>>(it,sType)

//                Log.e("filtered", restoId.toString())
                var filtered:ArrayList<Food> = ArrayList<Food>()
                result.forEach {
                    if (it.restaurant_id == restoId){
                        Log.e("filtered", it.toString())
                        filtered.add(it as Food)
                    }
                }
                menuLD.value = filtered as ArrayList<Food>?
                Log.d("showvoley", filtered.toString())
            },{
                Log.d("showvoley", it.toString())
                menuLoadErrorLD.value = true
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

    suspend fun refreshSus()= suspendCoroutine<ArrayList<Food>?>{ cont ->
        var resultt = ArrayList<Food>()
//        loadingLD.value = true
//        menuLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/food.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
//                loadingLD.value = false
                val sType = object : TypeToken<List<Food>>() {}.type
                val result = Gson().fromJson<List<Food>>(it,sType)
                cont.resume(result as ArrayList<Food>?)
//                menuLD.value = filtered as ArrayList<Food>?
                Log.d("showvoleySus_Menu", result.toString())
            },{
                Log.d("showvoleySus_Menu", it.toString())
//                menuLoadErrorLD.value = true
//                loadingLD.value = false
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


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}