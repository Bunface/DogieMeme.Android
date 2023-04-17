package hu.bme.mobillabor.dogiememe._util

import android.content.res.Resources
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.core.view.isVisible

const val DEFAULT_ANIM_TIME = 300L
const val REFRESH_ANIMATION_TIME = 800L

fun View.animateCenterTo(
    centerToX: Float? = null,
    centerToY: Float? = null,
    interpolator : Interpolator = AccelerateDecelerateInterpolator(),
    time: Long = DEFAULT_ANIM_TIME){
    animate().let { animate ->
        centerToX?.let{
            animate.x(centerToX - this.pivotX)
        }
        centerToY?.let{
            animate.y(centerToY - this.pivotY)
        }
        animate.duration = time
        animate.interpolator = interpolator
        animate.start()
    }
}

fun View.animateAlfa(
    alpha: Float = 1f,
    interpolator: Interpolator = DecelerateInterpolator(),
    time: Long = DEFAULT_ANIM_TIME
){
    animate()
        .alpha(alpha)
        .setDuration(time)
        .setInterpolator(interpolator)
        .start()
}

fun View.slowVisible(){
    if(!this.isVisible) {
        this.visible()
        this.animateAlfa(
            interpolator = AccelerateInterpolator()
        )
    }
}

fun View.slowInvisible(){
    if(this.isVisible) {
        this.animateAlfa(
            alpha = 0.0f,
            interpolator = AccelerateInterpolator()
        )
        this.invisible()
    }
}

fun View.animateScaleXY(
    scaleX: Float = 1f,
    scaleY: Float = 1f,
    interpolator: Interpolator = AccelerateDecelerateInterpolator(),
    time: Long = DEFAULT_ANIM_TIME
){
    animate()
        .scaleX(scaleX)
        .scaleY(scaleY)
        .setDuration(time)
        .setInterpolator(interpolator)
        .start()
}

fun View.animateScale(
    scale: Float = 1f,
    interpolator: Interpolator = AccelerateDecelerateInterpolator(),
    time: Long = DEFAULT_ANIM_TIME
){
    animate()
        .scaleX(scale)
        .scaleY(scale)
        .setDuration(time)
        .setInterpolator(interpolator)
        .start()
}

fun View.centerX(): Float{
    return this.x + this.pivotX
}

fun View.leftX(): Float{
    return this.x
}

fun View.rightX(): Float{
    return this.x + this.width
}


fun View.centerY(): Float{
    return this.y + this.pivotY
}

val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
