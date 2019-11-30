package com.example.quanlythuvien.GD;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGd extends AppCompatActivity {

    EditText edtmasacch,edttensach,edttacgia,edtnxb,edtsotrang;
    Button btnthemsach,btnthoatthem;
    private DatabaseReference mta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themgt);
        addcontrols();
        addevent();
        mta= FirebaseDatabase.getInstance().getReference();
    }

    private void addevent() {
        btnthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Luu();
                Intent intent=new Intent(AddGd.this, ShowGd.class);
                startActivity(intent);
            }
        });
        btnthoatthem.setOnClickListener(new View.OnClickListener() {
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
        mta.child("GIAO TRINH").push().setValue(clsSach, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError ==null){
                    Toast.makeText(AddGd.this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddGd.this, "Thêm thất bại !", Toast.LENGTH_SHORT).show();
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
        btnthoatthem= this.<Button>findViewById(R.id.btnthoatthem);
    }
}

