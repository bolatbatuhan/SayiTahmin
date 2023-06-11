package com.batuhanb.sayitahmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.batuhanb.sayitahmin.model.OyunSonucModel
import com.batuhanb.sayitahmin.model.TahminModel
import com.batuhanb.sayitahmin.util.ProjectUtil
import kotlinx.android.synthetic.main.activity_oyun_ekran.*

class OyunEkranActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun_ekran);

        imageDurum.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp);

        val gelenData = intent.getSerializableExtra("selectedTahmin") as TahminModel;
        var randomSayi:Int = 0;
        var hakSayisi:Int = 0;
        var maxDeger:Int = 0;

        if(gelenData.id == 1){
            randomSayi = (0 until 21).random();
            hakSayisi = 5;
            maxDeger = 20;
        }else if(gelenData.id == 2){
            randomSayi = (0 until 51).random();
            hakSayisi = 10;
            maxDeger = 50;
        }else if(gelenData.id == 3){
            randomSayi = (0 until 101).random();
            hakSayisi = 15;
            maxDeger = 100;
        }

        textViewDurum.text = "Merhaba! "+hakSayisi+" denemeniz kaldı!Bol şans...";
        Log.d("Random değer",""+randomSayi);

        var i:Int = 1;
        var oyunSonucModel: OyunSonucModel = OyunSonucModel("Tebrikler kazandınız...",R.drawable.ic_tag_faces_black_24dp);
        tahminButton.setOnClickListener {

            var tahminDeger:Int = 0;
            if(editTextTahmin.text == null || editTextTahmin.text.toString() == ""){
                ProjectUtil.showToastMessage(this,"Boş tahmin girilemez!",true);
            }else{
                tahminDeger = Integer.parseInt(editTextTahmin.text.toString());

                if(i<=hakSayisi){
                    if(tahminDeger == randomSayi){
                        ProjectUtil.activityYonlendir(this, OyunSonucActivity(),oyunSonucModel,"sonucModel");
                    }else{
                        if(i != hakSayisi){
                            if(((tahminDeger+5) == randomSayi)  || (tahminDeger-5) == randomSayi    ||
                                (tahminDeger+4) == randomSayi   || (tahminDeger-4) == randomSayi    ||
                                (tahminDeger+3) == randomSayi   || (tahminDeger-3) == randomSayi    ||
                                (tahminDeger+2) == randomSayi   || (tahminDeger-2) == randomSayi    ||
                                (tahminDeger+1) == randomSayi   || (tahminDeger-1) == randomSayi){
                                imageDurum.setImageResource(R.drawable.ic_sentiment_very_satisfied_black_24dp);
                                textViewDurum.text = "Çok yaklaştınız! "+(hakSayisi-i)+" denemeniz kaldı!Haydi devam...";
                            }else{
                                imageDurum.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
                                textViewDurum.text = "Uzak değer! "+(hakSayisi-i)+" denemeniz kaldı!Tekrar deneyiniz...";
                            }
                        }else{
                            oyunSonucModel = OyunSonucModel("Kaybettiniz...Sayı "+randomSayi+" idi. Haydi tekrar deneyelim...",R.drawable.ic_sentiment_very_dissatisfied_black_24dp);
                            ProjectUtil.activityYonlendir(this, OyunSonucActivity(),oyunSonucModel,"sonucModel");
                        }
                        i++;
                    }
                }
            }
        }

    }
}
