package com.batuhanb.sayitahmin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.batuhanb.sayitahmin.model.TahminModel
import com.batuhanb.sayitahmin.util.ProjectUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tahminSecimArray:ArrayList<TahminModel> = ArrayList<TahminModel>();
    private lateinit var tahminAdaptor:ArrayAdapter<TahminModel>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        oyunBaslaButton.isEnabled = false;

        var tahminModel: TahminModel = TahminModel(0,"Tahmin Etmek İstediğiniz Aralığı Seçiniz");
        tahminSecimArray.add(tahminModel);
        tahminModel =  TahminModel(1,"1-20 arası");
        tahminSecimArray.add(tahminModel);
        tahminModel = TahminModel(2,"1-50 arası");
        tahminSecimArray.add(tahminModel);
        tahminModel = TahminModel(3,"1-100 arası")
        tahminSecimArray.add(tahminModel);

        tahminAdaptor = ArrayAdapter(this,android.R.layout.simple_list_item_1,tahminSecimArray);
        tahminSpinner.adapter = tahminAdaptor;

        var selectedTahmin: TahminModel = TahminModel(-1,"");

        tahminSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {
                selectedTahmin = tahminSecimArray.get(index);

                if(selectedTahmin.id != 0){
                    textViewSecim.text = selectedTahmin.label+" seçtiniz. Artık oyuna başlayabilirsiniz. Başarılar...";
                    oyunBaslaButton.isEnabled = true;
                }else{
                    textViewSecim.text = "";
                    oyunBaslaButton.isEnabled = false;
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        oyunBaslaButton.setOnClickListener {
            ProjectUtil.activityYonlendir(this, OyunEkranActivity(),selectedTahmin,"selectedTahmin");
        }

    }

    override fun onBackPressed() {
        ProjectUtil.anaEkranaDon(this);
    }
}
