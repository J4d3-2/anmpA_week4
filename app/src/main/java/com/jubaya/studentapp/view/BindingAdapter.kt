package com.jubaya.studentapp.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.jubaya.studentapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.math.log

@BindingAdapter("android:imageUrl")
fun loadPhoto(imageView: ImageView, url:String) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception ->
        exception.printStackTrace()
    }

    if (url.isNullOrEmpty()) {
        // Set a placeholder or a default image if the URL is null or empty
        imageView.setImageResource(R.drawable.ic_launcher_foreground)
    } else {
        picasso.build().load(url).into(imageView, object:Callback{
            override fun onSuccess() {
                var v = imageView.parent as View
                var pb = v.findViewById<ProgressBar>(R.id.progressImage)
                pb.visibility = View.INVISIBLE
                imageView.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso", e?.message.toString())
            }
        })
        //picasso.build().load(url).into(imageView)
    }
    Log.d("bindingadapter", "cek "+url)
}
