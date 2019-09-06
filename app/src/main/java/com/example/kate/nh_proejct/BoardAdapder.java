package com.example.kate.nh_proejct;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/*class BoardAdapter extends ArrayAdapter<Board> {
    private ArrayList<Board> items;
    private StorageReference mStorageRef;
    private StorageReference pathReference;

    public BoardAdapter(Context context, int textViewResourceId, ArrayList<Board> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        mStorageRef= FirebaseStorage.getInstance().getReference();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("#position ",position+"번 째");
        //////
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.b_listveiw, null);
        }


        // ImageView 인스턴스
        ImageView imageView = (ImageView)v.findViewById(R.id.mainImg);
        // 리스트뷰의 아이템에 이미지를 변경한다.
        String img_path=items.get(position).image;
        //Log.i("####imgpath list",img_path);
        pathReference = mStorageRef.child(img_path);
        Glide.with(getContext()).load(pathReference).into(imageView);

        //제목변경


        TextView textView = (TextView)v.findViewById(R.id.textView);
        textView.setText(items.get(position).title);
        //Log.i("####제목들 ",items.get(position).title);

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

}*/










