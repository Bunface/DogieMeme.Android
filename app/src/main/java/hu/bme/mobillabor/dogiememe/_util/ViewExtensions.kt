package hu.bme.mobillabor.dogiememe._util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.isInvisible() = this.visibility == View.INVISIBLE

fun View.isGone() = this.visibility == View.GONE

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    if (!isVisible()) {
        this.visibility = View.VISIBLE
    }
}

fun View.gone() {
    if (!isGone()) {
        this.visibility = View.GONE
    }
}

fun View.invisible() {
    if (!isInvisible()) {
        this.visibility = View.INVISIBLE
    }
}

fun ImageView.loadUrlCropCenter(url: String?){
    url?.let{
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .into(this)
    }
}

fun ImageView.loadUrl(url: String?){
    url?.let{
        Glide
            .with(context)
            .load(url)
            .into(this)
    }
}