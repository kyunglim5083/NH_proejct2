package com.example.kate.nh_proejct;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class FundingListActivity extends AppCompatActivity {

    private FirebaseFirestore mDatabase;
    private ArrayList<Funding> items = new ArrayList<Funding>();
    private RecyclerView mRecyclerView;
    private FundingListActivity.FAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowdfunding_list);


        mDatabase = FirebaseFirestore.getInstance();

        //listView = (ListView) this.findViewById(R.id.lvSocketList);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //final BAdapter adapter=new BAdapter();



        mDatabase.collection("funding").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Map map = document.getData();
                        Log.i("##ggggg",document.getData()+"");
                        Funding funding = new Funding(map.get("title").toString(), map.get("image").toString(),"설명"
                                ,Integer.parseInt(map.get("situation").toString()), Integer.parseInt(map.get("goal").toString()),(Date) map.get("date"),
                                map.get("progress").toString());

                        items.add(funding);


                    }
                    adapter=new FundingListActivity.FAdapter(items);
                    mRecyclerView.setAdapter(adapter);

                } else {
                    Log.w("FirebaseFirestore", "Error => ", task.getException());
                }


            }

        });



    }
    class FAdapter extends RecyclerView.Adapter<FundingListActivity.FAdapter.ViewHolder> {

        private ArrayList<Funding> list;

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView textView;
            ImageView mainImg;
            ImageView progress;


            public ViewHolder(View view) {
                super(view);
                textView = (TextView) view.findViewById(R.id.title);
                mainImg = (ImageView) view.findViewById(R.id.mainImg);
                progress=(ImageView)view.findViewById(R.id.progress);



            }
        }

        public FAdapter(ArrayList<Funding> list) {

            this.list = list;
        }

        @Override
        public FundingListActivity.FAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Log.i("####here","");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_crowdfunding_item, parent, false);

            FundingListActivity.FAdapter.ViewHolder viewHolder=new FundingListActivity.FAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FundingListActivity.FAdapter.ViewHolder viewHolder, int position) {
            Log.i("fundinglist", "#boardlist:" + list.get(position).progress);

            viewHolder.textView.setText(list.get(position).title);

            String img_path="@drawable/"+list.get(position).image;
            int resId=getResources().getIdentifier(img_path,"",getApplication().getPackageName());
            viewHolder.mainImg.setImageResource(resId);

            //개별 게시물 화면으로 이동
            viewHolder.mainImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),FundingItem.class);
                    startActivityForResult(intent,0);//액티비티 띄우기
                }
            });
            String pro_path="@drawable/"+list.get(position).progress;
            resId=getResources().getIdentifier(pro_path,"",getApplication().getPackageName());
            viewHolder.progress.setImageResource(resId);





        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar_menu, menu);
        return true;

    }






}