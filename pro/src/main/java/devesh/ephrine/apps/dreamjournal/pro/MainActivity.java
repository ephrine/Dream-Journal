package devesh.ephrine.apps.dreamjournal.pro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public String TAG="Ephrine Apps";
    public int JTotal;
    private FirebaseAuth mAuth;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


             Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

GetTotal();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateDreamActivity.class);
                startActivity(intent);

                //     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
             //           .setAction("Action", null).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();



           getSupportActionBar().setTitle("Dream Journal");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        LinearLayout ChatViewScroll=(LinearLayout)findViewById(R.id.LLview);
ChatViewScroll.removeAllViewsInLayout();
        GetTotal();
        super.onResume();  // Always call the superclass method first



    }


    public void GetTotal(){
        Context context = getApplicationContext();
        SharedPreferences TotalVal = context.getSharedPreferences(
                getString(R.string.Data_total), Context.MODE_PRIVATE);
        if(TotalVal!=null){
            String T=TotalVal.getString(getString(R.string.Data_total),"");
            //int defaultValue = TotalVal.getInt(getString(R.string.Data_total), 0);

            if(T.equals("")){

            }else{
                JTotal=Integer.parseInt(T);
                CreateChatView();
            }

        }else {

        }

    }

    public void CreateChatView(){
        int t= 1;
        int J=JTotal;

        Context context = getApplicationContext();

        View.OnClickListener clicks = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String S = String.valueOf(v.getId());
ImageView del=(ImageView)findViewById(v.getId());

                String DataFile="File"+del.getTag().toString();
                String FTitle="FTitle"+del.getTag().toString();
                String FDate="FDate"+del.getTag().toString();

                Context context = getApplicationContext();
                SharedPreferences DreamsTxFile = context.getSharedPreferences(
                        DataFile, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = DreamsTxFile.edit();
                editor1.putString(DataFile, "0");
                editor1.apply();


                SharedPreferences TitleTxFile = context.getSharedPreferences(
                        FTitle, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = TitleTxFile.edit();
                editor2.putString(FTitle, "0");
                editor2.apply();


                SharedPreferences DateTxFile = context.getSharedPreferences(
                        FDate, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3 = DateTxFile.edit();
                editor3.putString(FDate, "0");
                editor3.apply();

                LinearLayout ChatViewScroll=(LinearLayout)findViewById(R.id.LLview);
                ChatViewScroll.removeAllViewsInLayout();
                GetTotal();

            }
        };



        for (int j = J; j >= t; j--) { // file name 5
            String DataFile="File"+String.valueOf(j);
            String FTitle="FTitle"+String.valueOf(j);
            String FDate="FDate"+String.valueOf(j);

            SharedPreferences DataFileVal = context.getSharedPreferences(
                    DataFile, Context.MODE_PRIVATE);

            SharedPreferences TimeDataFileVal = context.getSharedPreferences(
                    FDate, Context.MODE_PRIVATE);

            SharedPreferences TitleFileVal = context.getSharedPreferences(
                    FTitle, Context.MODE_PRIVATE);

            String TitleTx= TitleFileVal.getString(FTitle,"");
            String DreamTx= DataFileVal.getString(DataFile,"");
            String DateTx= TimeDataFileVal.getString(FDate,"");


            LinearLayout ChatViewScroll=(LinearLayout)findViewById(R.id.LLview);
            final CardView ChatCard = new CardView(MainActivity.this);
            ChatCard.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            ChatCard.setPadding(30, 20, 30, 20);
            ChatCard.setCardElevation(10);
            ChatCard.setRadius(10);
            ChatCard.setUseCompatPadding(true);
          //  ChatCard.setId(j);
            ChatViewScroll.addView(ChatCard);

            LinearLayout LL1 = new LinearLayout(MainActivity.this);
            LL1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            LL1.setOrientation(LinearLayout.VERTICAL);
            LL1.setPadding(10,10,10,10);
            ChatCard.addView(LL1);


            final TextView txTitle = new TextView(MainActivity.this);
            txTitle.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            txTitle.setTypeface(null, Typeface.BOLD);
            txTitle.setTextSize(20);
            txTitle.setText(TitleTx);  // Name
            //txUserName.setGravity(View.TEXT_ALIGNMENT_CENTER);
            LL1.addView(txTitle);

            final TextView txDate = new TextView(MainActivity.this);
            txDate.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            txDate.setTypeface(null, Typeface.BOLD_ITALIC);
            //txDate.setTextSize(14);
            txDate.setText(DateTx);
            txDate.setTextColor(getResources().getColor(R.color.colorAccent));// Name
            //txUserName.setGravity(View.TEXT_ALIGNMENT_CENTER);
            LL1.addView(txDate);


            final TextView txDream = new TextView(MainActivity.this);
            txDream.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            txDream.setText(DreamTx);   // msg
            LL1.addView(txDream);


            ImageView img = new ImageView(MainActivity.this);
                Drawable DOCDrawable = getResources().getDrawable(R.drawable.ic_menu_delete);
                img.setImageDrawable(DOCDrawable);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(90, 90);
            layoutParams.gravity=Gravity.RIGHT;
            img.setLayoutParams(layoutParams);
            img.setId(j);
            img.setTag(j);
            img.setOnClickListener(clicks);
            LL1.addView(img);

if(TitleTx.equals("0")){
    ChatCard.setVisibility(View.GONE);
}



        }

    }


}
