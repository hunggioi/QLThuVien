package com.example.quanlythuvien.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.R;
import com.example.quanlythuvien.adapter_sach;
import com.example.quanlythuvien.addBook.addsachcntt;
import com.example.quanlythuvien.information.Thongtincntt;
import com.example.quanlythuvien.showInfor.Showcntt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CNTTFragMent extends Fragment implements View.OnClickListener {
    private GridView grvsach;
    private ImageButton btnthem,btnthoatkhkt;
    private ArrayList<Book> sachArrayList;
    private DatabaseReference mta;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cntt,container,false);

        grvsach = view.findViewById(R.id.grvsach);
        btnthem = view.findViewById(R.id.btnthem);

        Addevent();
        sachArrayList = new ArrayList<>();
        final adapter_sach quyensachadapter=new adapter_sach(getActivity(),R.layout.activity_sach,sachArrayList);
        grvsach.setAdapter(quyensachadapter);
        mta= FirebaseDatabase.getInstance().getReference();
        mta.child("CONG NGHE THONG TIN").addValueEventListener(new ValueEventListener() {
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

        return view;
    }


    private void Addevent() {

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), addsachcntt.class);
                startActivity(intent);

            }
        });
        grvsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), Thongtincntt.class);
                Bundle ten=new Bundle();
                ten.putSerializable("gioicntt",sachArrayList.get(position));
                intent.putExtra("ahihicntt",ten);
                startActivity(intent);
            }
        });
        grvsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final DatabaseReference mta = FirebaseDatabase.getInstance().getReference("CONG NGHE THONG TIN");
                final Query query = mta.orderByChild("id").equalTo(sachArrayList.get(position).getId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            for (DataSnapshot child: snapshot.getChildren()) {
                                String postkey = child.getRef().getKey();
                                mta.child(postkey).removeValue();
                            }
                            Toast.makeText(getActivity(), "Xóa thành công !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), "Lỗi không thể xóa!", Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }
        });

    }



    @Override
    public void onClick(View v) {

    }
}
