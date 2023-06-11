package com.batuhanb.sayitahmin.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.batuhanb.sayitahmin.model.BaseObject

class ProjectUtil {

    companion object {

        /**

         * @param mevcutActivity        İçinde bulunduğunuz Activity
         * @param gidilecekActivity     Gitmek istediğiniz Activity
         * @sınce Ağustos 2020
         */
        fun activityYonlendir(mevcutActivity:Context,gidilecekActivity:Context):Unit{
            val intent = Intent(mevcutActivity,gidilecekActivity::class.java);
            mevcutActivity.startActivity(intent);
        }

        /**

         * @param mevcutActivity        İçinde bulunduğunuz Activity
         * @param gidilecekActivity     Gitmek istediğiniz Activity
         * @param sayfaParams           Bir sonrakği activity de almak istediğiniz parametreler
         * @sınce Ağustos 2020
         */
        fun activityYonlendir(mevcutActivity:Context,gidilecekActivity:Context,sayfaParams:Map<String,String>):Unit{
            val intent = Intent(mevcutActivity,gidilecekActivity::class.java);
            sayfaParams.forEach{(key,value) ->
                intent.putExtra(key,value);
            }
            mevcutActivity.startActivity(intent);
        }

        /**

         * @param mevcutActivity        İçinde bulunduğunuz Activity
         * @param gidilecekActivity     Gitmek istediğiniz Activity
         * @param sayfaParamObject      Bir sonrakği activity de almak istediğiniz nesne bu sınıfın içine gömülür
         * @param key                   Bir sonraki activityde almak istediğiniz nesneyi bu key ile alabilirsiniz.
         * @sınce Ağustos 2020
         */
        fun activityYonlendir(mevcutActivity:Context, gidilecekActivity:Context, sayfaParamObject: BaseObject, key:String):Unit{
            val intent = Intent(mevcutActivity,gidilecekActivity::class.java);
            intent.putExtra(key, sayfaParamObject);
            mevcutActivity.startActivity(intent);
        }

        fun anaEkranaDon(c:Context):Unit{
            val yeniIntent = Intent(Intent.ACTION_MAIN);
            yeniIntent.addCategory(Intent.CATEGORY_HOME);
            yeniIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            c.startActivity(yeniIntent);
        }

        /**

         * @param mevcutActivity        İçinde bulunduğunuz Activity
         * @param donulecekActivity     Geri tuşu ile dönmek istediğiniz Activity
         * @sınce Ağustos 2020
         */
        fun backStackToWantedActivity(mevcutActivity:Context,donulecekActivity:Context):Unit{
            val yeniIntent = Intent(mevcutActivity,donulecekActivity::class.java);
            yeniIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mevcutActivity.startActivity(yeniIntent);
        }

        fun showToastMessage(c:Context,mesaj:String,uzunMesaj:Boolean):Unit{
            var mesajTip:Int = Toast.LENGTH_SHORT;
            if(uzunMesaj){
                mesajTip = Toast.LENGTH_LONG;
            }
            Toast.makeText(c,mesaj,mesajTip).show();
        }

        /**

         * @param c                     İçinde bulunduğunuz Activity
         * @param theme                 component için style'da kayıtlı olan temamız
         * @param saat                  saat
         * @param dakika                dakika
         * @param is24Hour              24 saatlik zaman dilimimi olsun?
         * @sınce Eylül 2020
         */
        fun getTimePicker(c:Context,theme:Int,saat:Int,dakika:Int,is24Hour:Boolean):TimePickerDialog{
            val timePicker = TimePickerDialog(c,
                theme,TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                },saat,dakika,is24Hour);
            return timePicker;
        }


        /**

         * @param c                     İçinde bulunduğunuz Activity
         * @param theme                 component için style'da kayıtlı olan temamız
         * @param gun                   gün
         * @param ay                    ay
         * @param yil                   yıl
         * @sınce Eylül 2020
         */
        fun getDatePicker(c:Context,theme:Int,gun:Int,ay:Int,yil:Int):DatePickerDialog{
            val datePicker = DatePickerDialog(c,theme,DatePickerDialog.OnDateSetListener { datePicker, y, a, g ->
            },yil,ay,gun);
            return datePicker;
        }
    }
}