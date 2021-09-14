package com.example.kareemramadan.forquran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kareemramadan.R;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterSora adapter;
    List<ModelSora> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        recyclerView=findViewById(R.id.rec);

        list=new ArrayList();
        list.add(new ModelSora( "الفَاتِحَة",1));
        list.add(new ModelSora("البَقَرَة",2));
        list.add(new ModelSora("آل عِمرَان",45));
        list.add(new ModelSora("النِّسَاء" ,69));
        list.add(new ModelSora("المَائدة" ,95));
        list.add(new ModelSora("الأنعَام",115));
        list.add(new ModelSora("الأعرَاف",136));
        list.add(new ModelSora("الأنفَال",160));
        list.add(new ModelSora("التوبَة",169));
        list.add(new ModelSora("يُونس",187));
        list.add(new ModelSora("هُود",199));
        list.add(new ModelSora("يُوسُف",212));
        list.add(new ModelSora("الرَّعْد",225));
        list.add(new ModelSora("إبراهِيم",231));
        list.add(new ModelSora("الحِجْر",237));
        list.add(new ModelSora("النَّحْل",242));
        list.add(new ModelSora("الإسْرَاء",255));
        list.add(new ModelSora("الكهْف",266));
        list.add(new ModelSora("مَريَم",277));
        list.add(new ModelSora("طه",284));
        list.add(new ModelSora("الأنبيَاء",294));
        list.add(new ModelSora("الحَج",302));
        list.add(new ModelSora("المُؤمنون",311));
        list.add(new ModelSora("النُّور",319));
        list.add(new ModelSora("الفُرْقان",329));
        list.add(new ModelSora("الشُّعَرَاء",335));
        list.add(new ModelSora("النَّمْل",345));
        list.add(new ModelSora("القَصَص",354));
        list.add(new ModelSora( "العَنكبوت",364 ));
        list.add(new ModelSora( "الرُّوم" ,371));
        list.add(new ModelSora(  "لقمَان",377));
        list.add(new ModelSora("السَّجدَة",381)); //
        list.add(new ModelSora("الأحزَاب",383));
        list.add(new ModelSora("سَبَأ",393));
        list.add(new ModelSora("فَاطِر",399));
        list.add(new ModelSora("يس",404));
        list.add(new ModelSora("الصَّافات",410));
        list.add(new ModelSora("ص",417));
        list.add(new ModelSora("الزُّمَر",422));
        list.add(new ModelSora("غَافِر",431));
        list.add(new ModelSora("فُصِّلَتْ",439));
        list.add(new ModelSora("الشُّورَى",445));
        list.add(new ModelSora("الزُّخْرُف",451));
        list.add(new ModelSora("الدخَان",457));
        list.add(new ModelSora("الجَاثيَة",460));
        list.add(new ModelSora("الأحْقاف",464));
        list.add(new ModelSora("محَمَّد",468));
        list.add(new ModelSora("الفَتْح",473));
        list.add(new ModelSora("الحُجرَات",477));
        list.add(new ModelSora("ق",479));
        list.add(new ModelSora("الذَّاريَات",482));
        list.add(new ModelSora("الطُّور",485));
        list.add(new ModelSora("النَّجْم",487));
        list.add(new ModelSora("القَمَر",490));
        list.add(new ModelSora("الرَّحمن",493));
        list.add(new ModelSora("الوَاقِعَة",496));
        list.add(new ModelSora("الحَديد",499));
        list.add(new ModelSora("المجَادلة",504));
        list.add(new ModelSora("الحَشر",507));
        list.add(new ModelSora("المُمتَحنَة",510));
        list.add(new ModelSora("الصَّف",513));
        list.add(new ModelSora("الجُمُعَة",515));
        list.add(new ModelSora("المنَافِقون",516));
        list.add(new ModelSora("التغَابُن",518));
        list.add(new ModelSora("الطلَاق",520));
        list.add(new ModelSora("التحْريم",522));
        list.add(new ModelSora("المُلْك",524));
        list.add(new ModelSora("القَلَم",527));
        list.add(new ModelSora("الحَاقَّة",529));
        list.add(new ModelSora("المعَارج",531));
        list.add(new ModelSora("نُوح",533));
        list.add(new ModelSora("الجِن",534));
        list.add(new ModelSora("المُزَّمِّل",537));
        list.add(new ModelSora("المُدَّثِّر",538));
        list.add(new ModelSora("القِيَامَة",540));
        list.add(new ModelSora("الإنسَان",542));
        list.add(new ModelSora("المُرسَلات",544));
        list.add(new ModelSora("النَّبَأ",545));
        list.add(new ModelSora("النّازعَات",547));
        list.add(new ModelSora("عَبَس",548));
        list.add(new ModelSora("التَّكوير",550));
        list.add(new ModelSora("الانفِطار",551));
        list.add(new ModelSora("المطفِّفِين",552));
        list.add(new ModelSora("الانْشِقَاق",553));
        list.add(new ModelSora("البرُوج",554));
        list.add(new ModelSora("الطَّارِق",555));
        list.add(new ModelSora("الأَعْلى",556));
        list.add(new ModelSora("الغَاشِية",556));
        list.add(new ModelSora("الفَجْر",557));
        list.add(new ModelSora("البَلَد",559));
        list.add(new ModelSora("الشَّمْس",559));
        list.add(new ModelSora("الليْل",560));
        list.add(new ModelSora("الضُّحَى",561));
        list.add(new ModelSora("الشَّرْح",561));
        list.add(new ModelSora("التِّين",562));
        list.add(new ModelSora("العَلَق",562));
        list.add(new ModelSora("القَدْر",563));
        list.add(new ModelSora("البَينَة",563));
        list.add(new ModelSora("الزلزَلة",564));
        list.add(new ModelSora("العَادِيات",564));
        list.add(new ModelSora("القَارِعة",565));
        list.add(new ModelSora("التَّكَاثر",565));
        list.add(new ModelSora("العَصْر",566));
        list.add(new ModelSora("الهُمَزَة",566));
        list.add(new ModelSora("الفِيل",566));
        list.add(new ModelSora("قُرَيْش",567));
        list.add(new ModelSora("المَاعُون",567));
        list.add(new ModelSora("الكَوْثَر",567));
        list.add(new ModelSora("الكَافِرُون",568));
        list.add(new ModelSora("النَّصر",568));
        list.add(new ModelSora("المَسَد",568));
        list.add(new ModelSora("الإخْلَاص",569));
        list.add(new ModelSora("الفَلَق",569));
        list.add(new ModelSora("النَّاس",569));








        adapter=new AdapterSora(list,this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AdapterSora(list,this);

        recyclerView.setAdapter(adapter);




    }

}