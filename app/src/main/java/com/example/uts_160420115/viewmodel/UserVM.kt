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
import com.example.uts_160420115.model.User

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.*
import java.util.Objects
import kotlin.coroutines.resume


class UserVM(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<ArrayList<Food>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    suspend fun refreshSus()= suspendCoroutine<ArrayList<User>?>{ cont ->
        loadingLD.postValue(true)
        userLoadErrorLD.postValue(false)

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/user.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                loadingLD.postValue(false)
                val sType = object : TypeToken<List<User>>() {}.type
                val result = Gson().fromJson<List<User>>(it,sType)
                cont.resume(result as ArrayList<User>?)
//                userLD.value = filtered as ArrayList<User>?
                Log.d("showvoleySus_User", result.toString())
            },{
                Log.d("showvoleySus_User", it.toString())
                userLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    suspend fun refreshOneSus(username: String, password:String? = "")= suspendCoroutine<ArrayList<Object>>{ cont ->

        queue = Volley.newRequestQueue(getApplication())
        var url = "https://kenhosting.ddns.net/anmp/user.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                loadingLD.postValue(false)
                val sType = object : TypeToken<List<User>>() {}.type
                val result = Gson().fromJson<List<User>>(it,sType)
                var found = false
                var user = User(0, "", "","","", 0, "")
                result.forEach { u ->
                    if (u.username == username){
                        Log.e("u.password = ", (password.toString() != "").toString())
                        if (password.toString() != ""){
//                            Log.e("u.password = ", "not empty")
                            if (u.password == password){
                                user = u
                                found = true
                            }
                        }
                        else{
//                            Log.e("u.password = ", "empty")
                            user = u
                            found = true
                        }
                    }
                }
                val new = arrayListOf<Object>(user as Object, found as Object)
                cont.resume(new)
//                userLD.value = filtered as ArrayList<User>?
                Log.d("showvoleySus_User", result.toString())
            },{
                Log.d("showvoleySus_User", it.toString())
                userLoadErrorLD.postValue(true)
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