package it.marcomirisola.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Jemo on 12/5/16.
 */
fun ImageView.loadImage(imgUrl: String?){
    Glide.with(context).load(imgUrl).apply(RequestOptions().fitCenter()).into(this)
}

fun ImageView.loadImage(imgDrawable: Drawable?){
    this.setImageDrawable(imgDrawable)
}

fun View.stayVisibleOrGone(stay: Boolean){
    this.visibility = if (stay) View.VISIBLE else View.GONE
}