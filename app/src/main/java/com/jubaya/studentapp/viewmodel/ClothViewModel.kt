package com.jubaya.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jubaya.studentapp.model.Cloth
import com.jubaya.studentapp.model.Student

class ClothViewModel(application: Application):AndroidViewModel(application) {
    val clothesLD = MutableLiveData<ArrayList<Cloth>>()
    val loadingLD = MutableLiveData<Boolean>()
    val clothLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        clothLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.me/native/160421021/cloth.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = false
                Log.d("show_volley ", it)

                val sType = object: TypeToken<List<Cloth>>(){}.type
                val result = Gson().fromJson<List<Cloth>>(it, sType)

                clothesLD.value = result as ArrayList<Cloth>

            }, {
                loadingLD.value = false
                clothLoadErrorLD.value = false
                Log.e("show_volley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}