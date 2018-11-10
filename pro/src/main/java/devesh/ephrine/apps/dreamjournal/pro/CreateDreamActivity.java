package devesh.ephrine.apps.dreamjournal.pro;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

import devesh.ephrine.apps.dreamjournal.pro.Data.Dream;
import devesh.ephrine.apps.dreamjournal.pro.Database.AppDatabase;

public class CreateDreamActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dream);
        getSupportActionBar().setTitle("Add Dream");

        Calendar c = Calendar.getInstance();

        int Tdt=c.get(Calendar.DATE);
        int Tmonth=c.get(Calendar.MONTH);
        Tmonth=Tmonth+1;
        int Tyear=c.get(Calendar.YEAR);
        String Datatx=String.valueOf(Tdt)+"/"+String.valueOf(Tmonth)+"/"+String.valueOf(Tyear);
        TextInputEditText DateTxt = findViewById(R.id.InputDate);
        DateTxt.setText(Datatx);

        TextView Date = findViewById(R.id.TextViewDateTx);
        Date.setText("Today's Date: "+Datatx);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dream_database")
                .allowMainThreadQueries()
                .build();
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

        TextInputEditText TitleTx = findViewById(R.id.TxTitle);
        TextInputEditText DreamTx = findViewById(R.id.DreamTxt);
        TextInputEditText DateTxt = findViewById(R.id.InputDate);

        if(DreamTx.getText().toString()=="" || DreamTx==null || DreamTx.getText().toString().equals("") || DreamTx.getText().toString().equals(" ")){

        } else{
            String Datetx=DateTxt.getText().toString();

            Dream dream = new Dream(Datetx, TitleTx.getText().toString(), DreamTx.getText().toString());
            appDatabase.dreamDao().insertDream(dream);
        }

    }
}
