package com.example.myapplication2;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class Myadapter extends RecyclerView.Adapter <Myadapter.Myholder> {
    private List<Map<String,Object>> mydata;
    private Context mycontext;
    public Myadapter(List<Map<String,Object>> data, Context context) {
        mydata=data;
        mycontext=context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mycontext).inflate(R.layout.song_item,parent,false);

        Myholder myholder=new Myholder(view);
        return myholder;
    }


    @Override
    public void onBindViewHolder(@NonNull Myadapter.Myholder holder, int position) {
        String name=mydata.get(position).get("i_name").toString();
        int image=Integer.parseInt(mydata.get(position).get("i_image").toString());
        String songer=mydata.get(position).get("i_songer").toString();
        String song=mydata.get(position).get("i_song").toString();
        String tag=mydata.get(position).get("i_tag").toString();
        holder.textView.setText(name);
        holder.imageView.setImageResource(image);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mycontext, BlankFragment2_Detail.class);
                intent.putExtra("details",name);
                intent.putExtra("image",image);
                intent.putExtra("songer",songer);
                intent.putExtra("song",song);
                intent.putExtra("tag",tag);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //开始跳转
                mycontext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }
    public class Myholder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        public Myholder(@NonNull View itemView){
            super(itemView);
            textView=itemView.findViewById(R.id.textViewS);
            imageView=itemView.findViewById(R.id.imageS);
        }
    }
}