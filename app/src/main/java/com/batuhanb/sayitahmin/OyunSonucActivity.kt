package com.batuhanb.sayitahmin

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.batuhanb.sayitahmin.model.OyunSonucModel
import com.batuhanb.sayitahmin.util.ProjectUtil
import kotlinx.android.synthetic.main.activity_oyun_sonuc.*

class OyunSonucActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun_sonuc);

        val gelenData = intent.getSerializableExtra("sonucModel") as OyunSonucModel;

        imageViewSonuc.setImageResource(gelenData.sonucResim);
        textViewSonuc.text = gelenData.sonucAciklama;

        if(gelenData.sonucResim == R.drawable.ic_tag_faces_black_24dp){
            viewKonfetti.build().addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(nl.dionsegijn.konfetti.models.Shape.Square, nl.dionsegijn.konfetti.models.Shape.Circle)
                .addSizes(nl.dionsegijn.konfetti.models.Size(12))
                .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
                .streamFor(300, 5000L);
        }

        buttonTekrarOyna.setOnClickListener {
            ProjectUtil.activityYonlendir(this, MainActivity());
        }
    }

    override fun onBackPressed() {
        ProjectUtil.activityYonlendir(this, MainActivity());
    }
}
