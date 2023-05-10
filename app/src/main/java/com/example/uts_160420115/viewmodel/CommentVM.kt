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
import com.example.uts_160420115.model.Comment

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.*
import kotlin.coroutines.resume


class CommentVM(application: Application): AndroidViewModel(application) {
    val commentLD = MutableLiveData<ArrayList<Comment>>()
    val commentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null
    private lateinit var userVM: UserVM
    fun refresh(restoId: Int, fragment:Fragment){
        GlobalScope.launch {
            userVM = ViewModelProvider(fragment)[UserVM::class.java]
            val resultU = userVM.refreshSus()
            Log.e("result_cour_Sus_User", resultU.toString())
            loadingLD.postValue(true)
            commentLoadErrorLD.postValue(false)

            queue = Volley.newRequestQueue(getApplication())
            var url = "https://kenhosting.ddns.net/anmp/comment.json"

            val stringRequest = StringRequest(
                Request.Method.GET, url,{
                    loadingLD.postValue(false)
                    val sType = object : TypeToken<List<Comment>>() {}.type
                    val result = Gson().fromJson<List<Comment>>(it,sType)
                    Log.e("filtered", restoId.toString())
                    var filtered:ArrayList<Comment> = ArrayList<Comment>()
                    result.forEach { c ->
                        if (c.restaurant_id == restoId){
                            Log.e("filtered", it.toString())
                            resultU?.forEach { u ->
                                if (u.id == c.user_id){
                                    c.user = u
                                }
                            }
                            filtered.add(c as Comment)
                        }
                    }
                    commentLD.postValue(filtered as ArrayList<Comment>?)
                    Log.d("showvoley", filtered.toString())
                },{
                    Log.d("showvoley", it.toString())
                    commentLoadErrorLD.postValue(true)
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