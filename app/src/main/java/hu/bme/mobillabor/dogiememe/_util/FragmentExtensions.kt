package hu.bme.mobillabor.dogiememe._util.recylerview

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.mobillabor.dogiememe._util.animateCenterTo
import hu.bme.mobillabor.dogiememe._util.animateScale
import hu.bme.mobillabor.dogiememe._util.dp

const val DEFAULT_ANIM_TIME = 300L

fun Fragment.setUpHeaderAnim(headerText: View, header: View, recyclerView: RecyclerView){
    recyclerView.addOnScrollListener(
        object : RecyclerView.OnScrollListener(){
            override fun onScrolled (recyclerView: RecyclerView, dx: Int, dy: Int){
                if(recyclerView.computeVerticalScrollOffset() == 0){
                    header.apply {
                        elevation = 0f
                        animateCenterTo(centerToY = this.pivotY)
                    }
                    headerText.apply {
                        animateScale()
                    }
                }else{
                    header.apply {
                        elevation = 10f
                        animateCenterTo(centerToY = 10.dp.toFloat())
                    }
                    headerText.apply {
                        animateScale(
                            scale = 0.8f,
                        )
                    }
                }
            }
        }
    )
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