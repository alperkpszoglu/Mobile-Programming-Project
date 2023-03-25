package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {
    Button rakam_dokuz,rakam_sekiz,rakam_yedi,rakam_alti,rakam_bes,rakam_dort,
            rakam_uc,rakam_iki,rakam_bir, rakam_sifir, islem_bol, islem_carp,
            islem_cikart, islem_topla, islem_esittir, sifirla;
    TextView islemsonuc;
    String isleminstringi="";
    String isleminsonucu="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rakam_dokuz=findViewById(R.id.rakam_dokuz);
        rakam_sekiz=findViewById(R.id.rakam_sekiz);
        rakam_yedi=findViewById(R.id.rakam_yedi);
        rakam_alti=findViewById(R.id.rakam_alti);
        rakam_bes=findViewById(R.id.rakam_bes);
        rakam_dort=findViewById(R.id.rakam_dort);
        rakam_uc=findViewById(R.id.rakam_uc);
        rakam_iki=findViewById(R.id.rakam_iki);
        rakam_bir=findViewById(R.id.rakam_bir);
        rakam_sifir=findViewById(R.id.rakam_sifir);
        islem_bol=findViewById(R.id.islem_bol);
        islem_carp=findViewById(R.id.islem_carp);
        islem_cikart=findViewById(R.id.islem_cikart);
        islem_topla=findViewById(R.id.islem_topla);
        islem_esittir=findViewById(R.id.islem_esittir);
        sifirla=findViewById(R.id.sifirla);

        islemsonuc=findViewById(R.id.islemsonuc);
    }
    //örneğin "5" butonuna sonra "+" butonuna ve sonra "3" butonuna basan kullanıcıya 8 şeklinde bir sonuç vereceğimiz için
    // kullanıcının bastığı butonlardan gelen değerleri string bir değişkene peşi sıra yazdırıp
    // daha sonra math parser kütüphanesiyle stringin çözümüne yani sonuca ulaştım ve sonucu ekrana yazdırdım
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.rakam_dokuz:
                islemsonuc.setText(" "); //ekran açıldığında settext kısmında 0 değeri olduğu için başlangıçta 05 olarak yazıyordu o yüzden bütün tuşlara bastığımda ilk olarak boş ekranı set ettim
                isleminstringi=isleminstringi+"9";//bütün işlemleri tuttuğum string değişkenine 9 rakamını ekledim
                islemsonuc.setText(isleminstringi);//yazılan rakamların ekranda görünebilmesi için basılan her butondan sonra ekrana stringimi set ettim
                break;
            case R.id.rakam_sekiz:
                islemsonuc.setText(" ");
                isleminstringi=isleminstringi+"8";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_yedi:
                islemsonuc.setText(" ");
                isleminstringi=isleminstringi+"7";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_alti:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"6";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_bes:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"5";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_dort:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"4";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_uc:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"3";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_iki:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"2";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_bir:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"1";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.rakam_sifir:
                islemsonuc.setText("");
                isleminstringi=isleminstringi+"0";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.islem_bol:
                isleminstringi=isleminstringi+"/";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.islem_carp:
                isleminstringi=isleminstringi+"*";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.islem_cikart:
                isleminstringi=isleminstringi+"-";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.islem_topla:
                isleminstringi=isleminstringi+"+";
                islemsonuc.setText(isleminstringi);
                break;
            case R.id.islem_esittir:
                isleminstringi="("+isleminstringi+")";
                Expression ifade=new Expression(isleminstringi);
                isleminsonucu=String.valueOf(ifade.calculate()).toString(); //math parser kütüphanesine rakam ve aritmetik işaretlerden oluşan bir stringi verdim ve bunun sonucunu bana çözümlemesini istedim
                isleminsonucu="("+isleminsonucu+")"; //islemin sonucunun başına ve sonuna parantez ekledim ki kullanıcı bulduğu sonucun üzeirne bir işlem daha yapmak isterse işlem önceliği sorun olup hatalı bir sonuç gelmesin
                islemsonuc.setText(isleminsonucu);//işlemin sonucunu textView a yazdırdım
                break;
            case R.id.sifirla: //kullanıcının AC tuşuna bastığında ekranın sıfırlanmasını yani stringimin sıfırlanmasını istedim daha sonra da boş olan stringimi yeniden hesap makinesi ekranına yazdırdım.
                isleminstringi="0";
                islemsonuc.setText(isleminstringi);
                break;
        }
    }
}