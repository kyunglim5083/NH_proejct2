package com.example.kate.nh_proejct;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;
import java.util.Map;


public class TestActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    //storage
    private StorageReference mStorageRef;
    private FirebaseFirestore mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseFirestore.getInstance();

        //storage
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mDatabase.collection("board").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Map map = document.getData();
                        //Log.i("##ggggg",document.getData()+"");


                    }


                } else {
                    Log.w("FirebaseFirestore", "Error => ", task.getException());
                }


            }

        });



        StorageReference pathReference = mStorageRef.child("recipe/food4.jpg");
        ImageView img=findViewById(R.id.imgView);

        Glide.with(this)
                .load(pathReference).into(img);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //사용자가 현재 로그인 되있는지 확
    /*  @Override
  public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //사용자가 이미 로그인 되어 있다
        if(currentUser!=null){
            Toast.makeText(HomeActivity.this,currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
        //updateUI(currentUser);
    }
*/
}
