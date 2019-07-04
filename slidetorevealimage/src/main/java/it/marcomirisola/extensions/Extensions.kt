package it.marcomirisola.extensions

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.graphics.drawable.BitmapDrawable



/**
 * Created by Jemo on 12/5/16.
 */
fun ImageView.loadImage(imgUrl: String?){
    setImageDrawable(BitmapDrawable(context.resources,  BitmapFactory.decodeFile(imgUrl)))
}

fun ImageView.loadImage(imgDrawable: Drawable?){
    this.setImageDrawable(imgDrawable)
}

fun View.stayVisibleOrGone(stay: Boolean){
    this.visibility = if (stay) View.VISIBLE else View.GONE
}