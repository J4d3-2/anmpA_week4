package com.jubaya.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jubaya.studentapp.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(nrp:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id="+nrp

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                Log.d("show_volley ", it)

                val sType = object: TypeToken<Student>(){}.type
                val result = Gson().fromJson<Student>(it, sType)

                studentLD.value = result as Student

            }, {
                Log.e("show_volley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

        //val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            //"http://dummyimage.com/75x100.jpg/cc0000/ffffff")

        //studentLD.value = student1
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}