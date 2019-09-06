package com.example.kate.nh_proejct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class BoardListActivity extends AppCompatActivity {


    //private ListView listView;
    private FirebaseFirestore mDatabase;
    private ArrayList<Board> items = new ArrayList<Board>();
    private RecyclerView mRecyclerView;
    private  BAdapter adapter;
    private StorageReference pathReference;
    private StorageReference mStorageRef=FirebaseStorage.getInstance().getReference();


    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardlist);


        mDatabase = FirebaseFirestore.getInstance();

       //listView = (ListView) this.findViewById(R.id.lvSocketList);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //final BAdapter adapter=new BAdapter();



        mDatabase.collection("board").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Map map = document.getData();
                        //Log.i("##ggggg",document.getData()+"");
                        Board board = new Board(map.get("title").toString(), "", map.get("image").toString(),
                                "설명설명".toString(), Integer.parseInt(map.get("heart").toString()), (Date) map.get("date"));

                        items.add(board);


                    }
                    adapter=new BAdapter(items);
                    mRecyclerView.setAdapter(adapter);

                } else {
                    Log.w("FirebaseFirestore", "Error => ", task.getException());
                }


            }

        });



    }
    class BAdapter extends RecyclerView.Adapter<BAdapter.ViewHolder> {
        Handler handler = new Handler();

        private ArrayList<Board> list;

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView textView;
            ImageView imgView;
            ImageView mainImg;


            public ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.title);
                imgView = (ImageView) view.findViewById(R.id.imgView);
                mainImg = (ImageView) view.findViewById(R.id.mainImg);


            }
        }

        public BAdapter(ArrayList<Board> list) {
            this.list = list;
        }

        @Override
        public BAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Log.i("####here","");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.b_listveiw, parent, false);

            BAdapter.ViewHolder viewHolder=new BAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(BAdapter.ViewHolder viewHolder, int position) {
            Log.i("boardlist", "#boardlist:" + list.get(position).image);

            viewHolder.textView.setText(items.get(position).title);

            if(items.get(position).heart==1){
                viewHolder.imgView.setImageResource(R.drawable.fullh);
            }else{
                viewHolder.imgView.setImageResource(R.drawable.emptyh);
            }

            String img_path="@drawable/"+items.get(position).image;
            int resId=getResources().getIdentifier(img_path,"",getApplication().getPackageName());
            viewHolder.mainImg.setImageResource(resId);

            //개별 게시물 화면으로 이동
            viewHolder.mainImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),BoardItem.class);
                    startActivityForResult(intent,0);//액티비티 띄우기
                }
            });










        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        /*private Drawable drawableFromUrl(String url) {
            Bitmap x=null;

            HttpURLConnection conn =
                    null;
            try {
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.connect();
                InputStream input = conn.getInputStream();
                x = BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return new BitmapDrawable(getResources(), x);
        }
*/



    }





}