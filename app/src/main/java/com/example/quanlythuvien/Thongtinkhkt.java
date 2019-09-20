package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Thongtinkhkt extends AppCompatActivity {
    EditText edtmasachtt,edttacgiatt,edttensachtt,edtnxbtt,edtsotrangtt;
    Button btnthoattt,btncapnhat;
    private Book sach;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtinkhkt);
        addcontrols();
        addevent();
        loaddata();
    }
    private void loaddata()
    {
        Bundle ten=getIntent().getBundleExtra("ahihi");
       sach =(Book) ten.getSerializable("khau");
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
                Intent intent=new Intent(Thongtinkhkt.this, Updatekhkt.class);
                Bundle load = new Bundle();
                load.putSerializable("key", sach);
                intent.putExtra("ahihi",load);
                startActivity(intent);
            }
        });
        btnthoattt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thongtinkhkt.this.onBackPressed();
            }
        });
    }

    private void addcontrols() {
        edtmasachtt= this.<EditText>findViewById(R.id.edtmasachtt);
        edttensachtt= this.<EditText>findViewById(R.id.edttensachtt);
        edttacgiatt= this.<EditText>findViewById(R.id.edttacgiatt);
        edtnxbtt= this.<EditText>findViewById(R.id.edtnxbtt);
        edtsotrangtt= this.<EditText>findViewById(R.id.edtsotrangtt);
        btnthoattt= this.<Button>findViewById(R.id.btnthoattt);
        btncapnhat= this.<Button>findViewById(R.id.btncapnhat);
    }
}
