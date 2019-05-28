package it.marcomirisola.slidetorevealimageapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        before_after_slider_id.setBaseImage(getDrawable(R.drawable.image))
            .addImage(getDrawable(R.drawable.image_bw_1), getDrawable(R.drawable.thumb_image)!!)
            .addImage(getDrawable(R.drawable.image_bw_2), getDrawable(R.drawable.thumb_image)!!)
            .addImage(getDrawable(R.drawable.image_bw_3), getDrawable(R.drawable.thumb_image)!!)
            .build()
    }
}
