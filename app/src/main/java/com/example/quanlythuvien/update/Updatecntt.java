package com.example.quanlythuvien.update;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.Fragment.CNTTFragMent;
import com.example.quanlythuvien.R;
import com.example.quanlythuvien.information.Thongtincntt;
import com.example.quanlythuvien.showInfor.Showcntt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Updatecntt extends AppCompatActivity {
    EditText edtmasachup,edttensachup,edttacgiaup,edtnxbup,edtsotrangup;
    Button btnupdate;
    private LinearLayout btnback;
    private TextView txt_toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatecntt);
        addcontrols();
        loaddata();

        txt_toolbar.setText("Chỉnh sửa sách");
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                Updatecntt.this.onBackPressed();
                CNTTFragMent nextfrag = new CNTTFragMent();
                getSupportFragmentManager().beginTransaction().commit();
//                Intent intent=new Intent(Updatecntt.this, Showcntt.class);
//                startActivity(intent);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updatecntt.this.onBackPressed();
            }
        });
    }

    private void loaddata()
    {
        Bundle ten = getIntent().getBundleExtra("ahihicntt");
        Book sach=(Book) ten.getSerializable("keycntt");
        edtmasachup.setText(sach.getId());
        edttensachup.setText(sach.getTensach());
        edttacgiaup.setText(sach.getTacgia());
        edtnxbup.setText(sach.getNxb());
        edtsotrangup.setText(""+sach.getSotrang());
    }
    private void update()
    {   String ma=edtmasachup.getText().toString();
        final String ten=edttensachup.getText().toString();
        final String tacgia=edttacgiaup.getText().toString();
        final String ncb=edtnxbup.getText().toString();
        final int sotrang=Integer.parseInt(edtsotrangup.getText().toString());
        Book clsbook=new Book(ma,ten,tacgia,ncb,sotrang);
        final DatabaseReference mta = FirebaseDatabase.getInstance().getReference("CONG NGHE THONG TIN");
        final Query query = mta.orderByChild("id").equalTo(clsbook.getId());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for (DataSnapshot child: snapshot.getChildren()) {
                        String postkey = child.getRef().getKey();
                        mta.child(postkey).child("tensach").setValue(ten);
                        mta.child(postkey).child("tacgia").setValue(tacgia);
                        mta.child(postkey).child("sotrang").setValue(sotrang);
                        mta.child(postkey).child("nxb").setValue(ncb);
                    }
                    Toast.makeText(Updatecntt.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Updatecntt.this, "Lỗi cập nhật !", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addcontrols() {
        edtmasachup= this.<EditText>findViewById(R.id.edtmasachup);
        edttensachup= this.<EditText>findViewById(R.id.edttensachup);
        edttacgiaup= this.<EditText>findViewById(R.id.edttacgiaup);
        edtnxbup= this.<EditText>findViewById(R.id.edtnxbup);
        edtsotrangup= this.<EditText>findViewById(R.id.edtsotrangup);
        btnupdate= this.<Button>findViewById(R.id.btnupdate);
        btnback = findViewById(R.id.btnBack);
        txt_toolbar = findViewById(R.id.text_title);
    }
}
