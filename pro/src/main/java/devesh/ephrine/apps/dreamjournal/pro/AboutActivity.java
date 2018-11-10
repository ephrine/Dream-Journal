package devesh.ephrine.apps.dreamjournal.pro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AboutActivity extends AppCompatActivity {
    public String TAG = "Ephrine Dream Journal";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pro);

        Button UpdateButton = findViewById(R.id.buttonUpdate);
        UpdateButton.setVisibility(View.GONE);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference CheckUpdate = database.getReference("/DreamJournal/VersionCode");

        // Read from the database
        CheckUpdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Version Code Available: " + value);

                if(value!=null){
                    int myVersionCode=BuildConfig.VERSION_CODE;
                    int UpadetCode=Integer.parseInt(value);

                    if(myVersionCode==UpadetCode){
                        Button UpdateButton = findViewById(R.id.buttonUpdate);
                        UpdateButton.setVisibility(View.GONE);
                    }else {
                        Button UpdateButton = findViewById(R.id.buttonUpdate);
                        UpdateButton.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){

        }else {
            mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());

                        }

                    }
                });
        }


    }

    public void website(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.ephrine.in")); //Google play store
        startActivity(intent);
    }

    public void update(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=devesh.ephrine.apps.dreamjournal.pro")); //Google play store
        startActivity(intent);
    }

    public void buy(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=devesh.ephrine.apps.dreamjournal.pro")); //Google play store
        startActivity(intent);
    }
    public void del(View v){

        Context context=AboutActivity.this;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);

        builder.setTitle("Delete All Dreams?")
                .setMessage("This will delete all Dreams & App Data, Do you want to Continue ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        deleteAppData();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(R.drawable.warning_ico)
                .show();

    }

    private void deleteAppData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);

            currentUser.delete();
            Log.d(TAG, "App Data Cleared !!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if(currentUser!=null){
                currentUser.delete();
            }

            AboutActivity.this.finish();

            return true;
        }
        //  return true;
        return super.onKeyDown(keyCode, event);
    }


}
