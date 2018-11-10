package devesh.ephrine.apps.dreamjournal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class CreateDreamActivity extends AppCompatActivity {
int JTotal;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dream);
        getSupportActionBar().setTitle("Add Dream");




        Context context = getApplicationContext();
        SharedPreferences TotalVal = context.getSharedPreferences(
                getString(R.string.Data_total), Context.MODE_PRIVATE);
        if(TotalVal!=null){
            String T=TotalVal.getString(getString(R.string.Data_total),"");
            if(T.equals("")){

            }else{
                JTotal=Integer.parseInt(T);
            }

        }else {
            JTotal=1;

        }

        Calendar c = Calendar.getInstance();

        int Tdt=c.get(Calendar.DATE);
        int Tmonth=c.get(Calendar.MONTH);
        Tmonth=Tmonth+1;
        int Tyear=c.get(Calendar.YEAR);
        String Datatx=String.valueOf(Tdt)+"/"+String.valueOf(Tmonth)+"/"+String.valueOf(Tyear);
TextInputEditText DateTxt=(TextInputEditText)findViewById(R.id.InputDate);
DateTxt.setText(Datatx);

        TextView Date=(TextView)findViewById(R.id.TextViewDateTx);
        Date.setText("Today's Date: "+Datatx);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
Save();
            CreateDreamActivity.this.finish();

            return true;
        }
      //  return true;
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dream_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {

            Save();
            CreateDreamActivity.this.finish();

            return true;
        }
        if (id == R.id.cancel) {
            CreateDreamActivity.this.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Save(){

        TextInputEditText TitleTx=(TextInputEditText)findViewById(R.id.TxTitle);
        TextInputEditText DreamTx=(TextInputEditText)findViewById(R.id.DreamTxt);
        TextInputEditText DateTxt=(TextInputEditText)findViewById(R.id.InputDate);

if(DreamTx.getText().toString()=="" || DreamTx==null || DreamTx.getText().toString().equals("") || DreamTx.getText().toString().equals(" ")){

}else{
    JTotal=JTotal+1;
    Calendar c = Calendar.getInstance();

    int Tdt=c.get(Calendar.DATE);
    int Tmonth=c.get(Calendar.MONTH);
    Tmonth=Tmonth+1;
    int Tyear=c.get(Calendar.YEAR);
    String Datetx=DateTxt.getText().toString();


    String DataFile="File"+String.valueOf(JTotal);
    String FTitle="FTitle"+String.valueOf(JTotal);
    String FDate="FDate"+String.valueOf(JTotal);


    Context context = getApplicationContext();

    SharedPreferences TotalDreams = context.getSharedPreferences(
            getString(R.string.Data_total), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = TotalDreams.edit();
    editor.putString(getString(R.string.Data_total), String.valueOf(JTotal));
    editor.apply();


    SharedPreferences DreamsTxFile = context.getSharedPreferences(
            DataFile, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor1 = DreamsTxFile.edit();
    editor1.putString(DataFile, DreamTx.getText().toString());
    editor1.apply();


    SharedPreferences TitleTxFile = context.getSharedPreferences(
            FTitle, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor2 = TitleTxFile.edit();
    editor2.putString(FTitle, TitleTx.getText().toString());
    editor2.apply();


    SharedPreferences DateTxFile = context.getSharedPreferences(
            FDate, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor3 = DateTxFile.edit();
    editor3.putString(FDate, Datetx);
    editor3.apply();


}

    }
}
