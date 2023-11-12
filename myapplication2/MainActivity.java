package com.example.myapplication2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    Fragment fragment1,fragment2,fragment3,fragment4;
    FragmentManager manager;
    int transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        linearLayout1=findViewById(R.id.talk);
        linearLayout2=findViewById(R.id.book);
        linearLayout3=findViewById(R.id.look);
        linearLayout4=findViewById(R.id.me);
        manager=getSupportFragmentManager();

        fragment1=new BlankFragment1();
        fragment2=new BlankFragment2();
        fragment3=new BlankFragment3();
        fragment4=new BlankFragment4();

        inital();
        fragmentHide();
        showfragment(fragment1);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);


    }
    public void onClick(View view) {
        fragmentHide();
        int id = view.getId();
        if (id == R.id.talk) {
            showfragment(fragment1);
        } else if (id == R.id.book) {
            showfragment(fragment2);
        } else if (id == R.id.look) {
            showfragment(fragment3);
        } else {
            showfragment(fragment4);
        }
    }
    private void showfragment(Fragment fragment) {
        transaction=manager.beginTransaction()
                .show(fragment)
                .commit();
    }
    public void inital(){
        transaction=manager.beginTransaction()
                .add(R.id.content,fragment1)
                .add(R.id.content,fragment2)
                .add(R.id.content,fragment3)
                .add(R.id.content,fragment4)
                .commit();
    }

    public void fragmentHide(){
        transaction=manager.beginTransaction()
                .hide(fragment1)
                .hide(fragment2)
                .hide(fragment3)
                .hide(fragment4)
                .commit();
    }
}