package com.example.kate.nh_proejct;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class BoardListActivity extends AppCompatActivity {


    private ListView listView;
    private FirebaseFirestore mDatabase;
    private ArrayList<Board> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardlist);

        mDatabase = FirebaseFirestore.getInstance();



        listView = (ListView)this.findViewById(R.id.lvSocketList);

        items = new ArrayList<Board>();




        mDatabase.collection("board").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Map map=document.getData();
                        Board board=new Board(map.get("title").toString(),"", map.get("image").toString(),
                        "설명설명".toString(),Integer.parseInt(map.get("heart").toString()), (Date)map.get("date"));
                        items.add(board);
                    }
                }

                else {
                    Log.w("FirebaseFirestore", "Error => ", task.getException());
                }
            }
        });

        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        listView.setAdapter(adapter);
                                                                                       }




    }


    class CustomAdapter extends ArrayAdapter<Board> {
    private ArrayList<Board> items;
    private StorageReference mStorageRef=FirebaseStorage.getInstance().getReference();;
    private StorageReference pathReference;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Board> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.b_listveiw, null);
        }

        Log.i("@@@@@@@@@","여기");
        // ImageView 인스턴스
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        // 리스트뷰의 아이템에 이미지를 변경한다.
        String img_path=items.get(position).image;
        Log.i("@@@@@@@@@",img_path);
        pathReference = mStorageRef.child(img_path);
        Glide.with(getContext()).load(pathReference).into(imageView);

        //제목변경

        TextView textView = (TextView)v.findViewById(R.id.textView);
        textView.setText(items.get(position).title);

        //하트받아오기
        ImageView imgView=(ImageView)v.findViewById(R.id.imgView);
        if(items.get(position).heart==1){
            imgView.setImageResource(R.drawable.fullh);
        }else{
            imgView.setImageResource(R.drawable.emptyh);
        }



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(BoardListActivity.this, text, Toast.LENGTH_SHORT).show();
                System.out.println("클릭됨!!!!!");
            }
        });

        return v;
    }

}









