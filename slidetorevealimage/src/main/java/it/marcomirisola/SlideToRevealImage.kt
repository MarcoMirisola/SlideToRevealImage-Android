package it.marcomirisola

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.github.developer__.R
import it.marcomirisola.async.ClipDrawableProcessorTask
import it.marcomirisola.extensions.loadImage
import it.marcomirisola.extensions.stayVisibleOrGone
import kotlinx.android.synthetic.main.slider_layout.view.*

/**
 * Created by Jemo on 12/5/16.
 */

class SlideToRevealImage : RelativeLayout, ClipDrawableProcessorTask.OnAfterImageLoaded{

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val attr = context.theme.obtainStyledAttributes(attrs, R.styleable.SlideToRevealImage,0,0)
        try {
            val thumbDrawable1 = attr.getDrawable(R.styleable.SlideToRevealImage_slider_thumb1)
            val thumbDrawable2 = attr.getDrawable(R.styleable.SlideToRevealImage_slider_thumb2)
            val thumbDrawable3 = attr.getDrawable(R.styleable.SlideToRevealImage_slider_thumb3)

            val beforeImage = attr.getDrawable(R.styleable.SlideToRevealImage_before_image)
            val afterImageUrl1 = attr.getDrawable(R.styleable.SlideToRevealImage_after_image1)
            val afterImageUrl2 = attr.getDrawable(R.styleable.SlideToRevealImage_after_image2)
            val afterImageUrl3 = attr.getDrawable(R.styleable.SlideToRevealImage_after_image3)

            setSliderThumb1(thumbDrawable1)
            setSliderThumb2(thumbDrawable2)
            setSliderThumb3(thumbDrawable3)
            setBeforeImage(beforeImage)
            setAfterImage1(afterImageUrl1)
            setAfterImage2(afterImageUrl2)
            setAfterImage3(afterImageUrl3)
        }finally {
            attr.recycle()
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.slider_layout, this)
    }

    /**
     * set original image
     */
    fun setBeforeImage(imageUri: String): SlideToRevealImage {
        before_image_view_id.loadImage(imageUri)
        return this
    }

    fun setBeforeImage(imgDrawable: Drawable?): SlideToRevealImage {
        before_image_view_id.loadImage(imgDrawable)
        return this
    }

    /**
     * set changed image
     */
    fun setAfterImage1(imageUri: String) {
        ClipDrawableProcessorTask<String>(after_image_1_view_id, seekbar_1_id, context, this).execute(imageUri)
    }

    /**
     * set changed image
     */
    fun setAfterImage1(imageDrawable: Drawable?): SlideToRevealImage {
        ClipDrawableProcessorTask<Drawable>(after_image_1_view_id, seekbar_1_id, context, this).execute(imageDrawable)
        return this
    }

    /**
     * set changed image
     */
    fun setAfterImage2(imageUri: String) {
        ClipDrawableProcessorTask<String>(after_image_2_view_id, seekbar_1_id, context, this).execute(imageUri)
    }

    /**
     * set changed image
     */
    fun setAfterImage2(imageDrawable: Drawable?): SlideToRevealImage  {
        ClipDrawableProcessorTask<Drawable>(after_image_2_view_id, seekbar_2_id, context, this).execute(imageDrawable)
        return this
    }

    /**
     * set changed image
     */
    fun setAfterImage3(imageDrawable: Drawable?): SlideToRevealImage  {
        ClipDrawableProcessorTask<Drawable>(after_image_3_view_id, seekbar_3_id, context, this).execute(imageDrawable)
        return this
    }


    /**
     * set thumb
     */
    fun setSliderThumb1(thumb: Drawable?){
        thumb?.let {
            seekbar_1_id.thumb = thumb
        }
    }

    /**
     * set thumb
     */
    fun setSliderThumb2(thumb: Drawable?){
        thumb?.let {
            seekbar_2_id.thumb = thumb
        }
    }

    /**
     * set thumb
     */
    fun setSliderThumb3(thumb: Drawable?){
        thumb?.let {
            seekbar_3_id.thumb = thumb
        }
    }

    /**
     * fired up after second image loading will be finished
     */
    override fun onLoadedFinished(loadedSuccess: Boolean) {
        seekbar_1_id.stayVisibleOrGone(loadedSuccess)
        seekbar_2_id.stayVisibleOrGone(loadedSuccess)
        seekbar_3_id.stayVisibleOrGone(loadedSuccess)
    }

}
