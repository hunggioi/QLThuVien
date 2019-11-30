package com.example.quanlythuvien.KHKT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlythuvien.AdapterSach;
import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Showkhkt extends Activity {
    GridView grvsach;
    ImageButton btnthem,btnthoatkhkt;
    ArrayList<Book>sachArrayList;
    private DatabaseReference mta;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showkhkt);
        addcontrols();
        addevent();
        sachArrayList = new ArrayList<>();
        final AdapterSach quyensachadapter=new AdapterSach(Showkhkt.this,R.layout.activity_sach,sachArrayList);
        grvsach.setAdapter(quyensachadapter);
        mta= FirebaseDatabase.getInstance().getReference();
        mta.child("KHOA HOC-KI THUAT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sachArrayList.clear();
                for (DataSnapshot da : dataSnapshot.getChildren()){
                    Book clsSach = da.getValue(Book.class);
                    sachArrayList.add(clsSach);
                }
                quyensachadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    private void addevent() {
        btnthoatkhkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showkhkt.this.onBackPressed();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Showkhkt.this, Addsachkhkt.class);
                startActivity(intent);

            }
        });
        grvsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Showkhkt.this, Thongtinkhkt.class);
                Bundle ten=new Bundle();
                ten.putSerializable("khau",sachArrayList.get(position));
                intent.putExtra("ahihi",ten);
                startActivity(intent);
            }
        });
        grvsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final DatabaseReference mta = FirebaseDatabase.getInstance().getReference("KHOA HOC-KI THUAT");
                final Query query = mta.orderByChild("id").equalTo(sachArrayList.get(position).getId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            for (DataSnapshot child: snapshot.getChildren()) {
                                String postkey = child.getRef().getKey();
                                mta.child(postkey).removeValue();
                            }
                            Toast.makeText(Showkhkt.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Showkhkt.this, "Lỗi không thể xóa!", Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }
        });
    }

    private void addcontrols() {
        grvsach= this.<GridView>findViewById(R.id.grvsach);
        btnthem= this.findViewById(R.id.btnthem);
        btnthoatkhkt= this.<ImageButton>findViewById(R.id.btnthoatkhkt);
    }
}
