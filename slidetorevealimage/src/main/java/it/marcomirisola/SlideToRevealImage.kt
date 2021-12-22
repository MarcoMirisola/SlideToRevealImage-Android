package it.marcomirisola

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import it.marcomirisola.async.ClipDrawableProcessorTask
import it.marcomirisola.extensions.loadImage
import it.marcomirisola.extensions.stayVisibleOrGone
import it.marcomirisola.slidetorevealimage.R
import kotlinx.android.synthetic.main.slider_layout.view.*


/**
 * Created by Jemo on 12/5/16.
 */

class SlideToRevealImage : RelativeLayout, ClipDrawableProcessorTask.OnAfterImageLoaded {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.slider_layout, this)
    }

    private var mThumbAtBottom = false

    /**
     * set original image
     */
    fun setBaseImage(imageUri: String): SlideToRevealImage {
        base_image_view_id.loadImage(imageUri)
        return this
    }

    fun setBaseImage(imgDrawable: Drawable?): SlideToRevealImage {
        base_image_view_id.loadImage(imgDrawable)
        return this
    }

    fun addImage(imageUri: String?, thumb: Drawable): SlideToRevealImage {
        return addImage(imageUri, null, thumb)
    }

    fun addImage(imageDrawable: Drawable?, thumb: Drawable): SlideToRevealImage {
        return addImage(null, imageDrawable, thumb)
    }

    fun thumbAtBottom(thumbAtBottom: Boolean) {
        mThumbAtBottom = thumbAtBottom
    }


    val imageViews: ArrayList<ImageView> = ArrayList()
    val thumbDrawables: ArrayList<Drawable> = ArrayList()
    val seekBars: ArrayList<SeekBar> = ArrayList()
    val images: ArrayList<Any> = ArrayList()

    private fun addImage(imageUri: String?, imageDrawable: Drawable?, thumb: Drawable): SlideToRevealImage {
        val imageView = ImageView(context)
        imageView.adjustViewBounds = true
        imageView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        rootLayout.addView(imageView)
        imageViews.add(imageView)
        thumbDrawables.add(thumb)

        imageDrawable?.let { images.add(it) }
        imageUri?.let { images.add(it) }

        return this
    }

    fun build() {
        if (mThumbAtBottom) {
            val lp = sliderContainer.layoutParams as LayoutParams
            lp.removeRule(ALIGN_TOP)
        }
        for ((index, thumb) in thumbDrawables.withIndex()) {
            val seekBar = SeekBar(context)
            seekBar.layoutParams = LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                if(mThumbAtBottom) thumb.intrinsicHeight else 0,
                1f
            )
            seekBar.setPadding(0, 0, 0, 0)
            seekBar.visibility = View.VISIBLE
            seekBar.max = 10000
            seekBar.progress = 10000 / (thumbDrawables.size + 1) * (thumbDrawables.size - index)
            seekBar.thumb = thumb
            seekBar.progressDrawable = null

            seekBars.add(seekBar)

            sliderContainer.addView(seekBar)
        }

        for (index in 0 until imageViews.size) {
            val imageView = imageViews[index]
            val seekBar = seekBars[index]
            val image = images[index]

            if (image is Drawable) {
                ClipDrawableProcessorTask<Drawable>(imageView, seekBar, context, this).execute(image)
            } else {
                ClipDrawableProcessorTask<String>(imageView, seekBar, context, this).execute(image as String?)
            }
        }


    }

    /**
     * fired up after second image loading will be finished
     */
    override fun onLoadedFinished(loadedSuccess: Boolean) {
        sliderContainer.bringToFront()
        for (x in 0 until sliderContainer.childCount) {
            val view = sliderContainer.getChildAt(x)
            view.stayVisibleOrGone(loadedSuccess)
        }
    }
}