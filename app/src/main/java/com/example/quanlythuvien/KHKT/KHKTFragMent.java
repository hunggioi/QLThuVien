package com.example.quanlythuvien.KHKT;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import com.example.quanlythuvien.AdapterSach;
import com.example.quanlythuvien.Book;
import com.example.quanlythuvien.CNTT.Thongtincntt;
import com.example.quanlythuvien.CNTT.addsachcntt;
import com.example.quanlythuvien.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KHKTFragMent extends Fragment implements View.OnClickListener {

    private GridView grvsach;
    private ImageButton btnthem;
    private ArrayList<Book> sachArrayList;
    private DatabaseReference mta;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khkt,container,false);

        grvsach = view.findViewById(R.id.grvsach);
        btnthem = view.findViewById(R.id.btnthem);

        Addevent();
        sachArrayList = new ArrayList<>();
        final AdapterSach quyensachadapter=new AdapterSach(getActivity(),R.layout.activity_sach,sachArrayList);
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
                Intent intent = new Intent(getActivity(), Thongtincntt.class);
                Bundle ten = new Bundle();
                ten.putSerializable("khau",sachArrayList.get(position));
                intent.putExtra("ahihi",ten);
                startActivity(intent);
            }
        });
        grvsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return false;
            }
        });
    }

    public void showAlertDialog(final int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Bạn có chắc muốn xoá");
        builder.setCancelable(true);
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final DatabaseReference mta = FirebaseDatabase.getInstance().getReference("KHOA HOC-KI THUAT");
                final Query query = mta.orderByChild("id").equalTo(sachArrayList.get(pos).getId());
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

            }
        });

        builder.setNeutralButton("Không", new DialogInterface.OnClickListener() {
            final Dialog dialog = new Dialog(getActivity());
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View v) {

    }


}
