package it.marcomirisola.slidetorevealimageapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgUrl1 = "https://raw.githubusercontent.com/MarcoMirisola/SlideToRevealImage/master/image1.png"
        val imgUrl2 = "https://raw.githubusercontent.com/MarcoMirisola/SlideToRevealImage/master/image2.png"

//        before_after_slider_id.setBeforeImage(imgUrl1).setAfterImage(imgUrl2)
        before_after_slider_id.setBeforeImage(getDrawable(R.drawable.image)).setAfterImage1(getDrawable(R.drawable.image_bw_1)).setAfterImage2(getDrawable(R.drawable.image_bw_2)).setAfterImage3(getDrawable(R.drawable.image_bw_3))
//        before_after_slider_id.setBeforeImage(getDrawable(R.drawable.image)).setAfterImage2(getDrawable(R.drawable.image_bw_2))
    }
}
