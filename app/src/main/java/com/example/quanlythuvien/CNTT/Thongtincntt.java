package com.example.quanlythuvien.CNTT;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.R;

public class Thongtincntt extends AppCompatActivity {
    EditText edtmasachtt,edttacgiatt,edttensachtt,edtnxbtt,edtsotrangtt;
    Button btncapnhat;
    private Book sach;
    private LinearLayout btnBack;
    private TextView txt_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtincntt);
        addcontrols();
        addevent();
        loaddata();
    }

    private void loaddata() {
        Bundle ten=getIntent().getBundleExtra("ahihicntt");
        sach =(Book) ten.getSerializable("gioicntt");
        edtmasachtt.setText(sach.getId());
        edttensachtt.setText(sach.getTensach());
        edttacgiatt.setText(sach.getTacgia());
        edtnxbtt.setText(sach.getNxb());
        edtsotrangtt.setText(""+sach.getSotrang());

    }

    private void addevent() {
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Thongtincntt.this, Updatecntt.class);
                Bundle load = new Bundle();
                load.putSerializable("keycntt", sach);
                intent.putExtra("ahihicntt",load);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thongtincntt.this.onBackPressed();
            }
        });
        txt_toolbar.setText("Chi tiết sách");
 }

    private void addcontrols() {
        edtmasachtt= this.<EditText>findViewById(R.id.edtmasachtt);
        edttensachtt= this.<EditText>findViewById(R.id.edttensachtt);
        edttacgiatt= this.<EditText>findViewById(R.id.edttacgiatt);
        edtnxbtt= this.<EditText>findViewById(R.id.edtnxbtt);
        edtsotrangtt= this.<EditText>findViewById(R.id.edtsotrangtt);
        btnBack= findViewById(R.id.btnBack);
        btncapnhat= this.<Button>findViewById(R.id.btncapnhat);
        txt_toolbar = findViewById(R.id.text_title);
    }
}
