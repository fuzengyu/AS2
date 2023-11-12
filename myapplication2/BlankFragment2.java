package com.example.myapplication2;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlankFragment2 extends Fragment {
    private RecyclerView recyclerView;
    private Myadapter myadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        recyclerView=view.findViewById(R.id.recycleview);
        //创建数据
        String[] names={"稻香" ,"江南","生日快乐","小霞","生活因你而火热","李白","公路之歌","斑马斑马"};
        int[] images={R.drawable.no1, R.drawable.no2, R.drawable.no3, R.drawable.no4,R.drawable.no5,
                R.drawable.no6,R.drawable.no7,R.drawable.no8};
        String[] composer={"周杰伦","林俊杰","金玟岐","黄绮珊","痛仰", "李荣浩","新裤子","宋东野"};
        String[] album={"魔杰座","第二天堂","生日快乐","小霞","生活因你而火热","模特","不要停止我的音乐",
                "安和桥北"};
        String[] tags={"华语","华语","治愈","流行","流行",
                "乡村摇滚","摇滚","民谣"};
        List<Map<String,Object>> items=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++){
            Map<String,Object> item=new HashMap<String, Object>();
            item.put("i_name",names[i]);
            item.put("i_image",images[i]);
            item.put("i_songer",composer[i]);
            item.put("i_song",album[i]);
            item.put("i_tag",tags[i]);
            items.add(item);
        }
        //创建RecycleView实例和设置Adapter
        Context context=getContext();
        myadapter=new Myadapter(items,context);
        LinearLayoutManager manager=new LinearLayoutManager(context);
        manager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myadapter);

        //实现拖拽和左滑删除效果
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int swiped=ItemTouchHelper.LEFT;
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags,swiped);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int oldPosition = viewHolder.getAdapterPosition();
                int newPosition = target.getAdapterPosition();
                if (oldPosition < newPosition) {
                    for (int i = oldPosition; i < newPosition; i++) {
                        // 改变数据集
                        Collections.swap(items, i, i +1);
                    }
                } else {
                    for (int i = oldPosition; i > newPosition; i--) {
                        // 改变数据集
                        Collections.swap(items, i, i - 1);
                    }
                }
                myadapter.notifyItemMoved(oldPosition, newPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                items.remove(position);
                myadapter.notifyItemRemoved(position);
            }
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState!= ItemTouchHelper.ACTION_STATE_IDLE){
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#04BE02"));
                }
            }
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        //关联recycleView
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
    }

