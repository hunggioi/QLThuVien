package com.example.quanlythuvien.KHKT;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.R;
import com.example.quanlythuvien.ThuVienFragMent;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addsachkhkt extends AppCompatActivity {
    EditText edtmasacch,edttensach,edttacgia,edtnxb,edtsotrang;
    Button btnthemsach;
    private DatabaseReference mta;
    private TextView text_title;
    private LinearLayout btn_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themkhkt);
        addcontrols();
        addevent();
        mta= FirebaseDatabase.getInstance().getReference();
        text_title.setText("Thêm sách");
    }

    private void addevent() {
        btnthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luu();
//                Intent intent=new Intent(Addsachkhkt.this, Showkhkt.class);
//                startActivity(intent);
                new ThuVienFragMent();
                getSupportFragmentManager().beginTransaction().commit();
                finish();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void Luu() {
        String ma=edtmasacch.getText().toString();
        String ten=edttensach.getText().toString();
        String tacgia=edttacgia.getText().toString();
        String nxb=edtnxb.getText().toString();
        int sotrang=Integer.parseInt(edtsotrang.getText().toString());
        Book clsSach=new Book(ma,ten,tacgia,nxb,sotrang);
        mta.child("KHOA HOC-KI THUAT").push().setValue(clsSach, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError ==null){
                    Toast.makeText(Addsachkhkt.this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Addsachkhkt.this, "Thêm thất bại !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void addcontrols() {
        edtmasacch= this.<EditText>findViewById(R.id.edtmasach);
        edttensach= this.<EditText>findViewById(R.id.edttensach);
        edttacgia= this.<EditText>findViewById(R.id.edttacgia);
        edtnxb= this.<EditText>findViewById(R.id.edtnxb);
        edtsotrang= this.<EditText>findViewById(R.id.edtsotrang);
        btnthemsach= this.<Button>findViewById(R.id.btnthemsach);
        btn_back= findViewById(R.id.btn_back);
        text_title = findViewById(R.id.text_title);
    }
}
