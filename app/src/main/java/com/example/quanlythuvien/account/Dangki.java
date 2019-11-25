package com.example.quanlythuvien.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class Dangki extends AppCompatActivity {
    ImageView toogleEye;
    EditText edtuserdangki,edtpassdangki1;
    Button dangky;
    boolean flagToogle = false;
    private LinearLayout toogle,btnBack;
    private ScrollView scroll;


    private FirebaseAuth mAthu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_dangki);
        addcontrols();
        mAthu=FirebaseAuth.getInstance();
        addevent();
    }

    private void addevent() {
        // nút quay lại
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangki.this.onBackPressed();
            }
        });

        // hiện mật khẩu
        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagToogle) {
                    flagToogle = false;
                    edtpassdangki1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    toogleEye.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_pass_inactive));
                    edtpassdangki1.setSelection(edtpassdangki1.getText().length());
                } else {
                    flagToogle = true;
                    edtpassdangki1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    toogleEye.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_pass_active));
                    edtpassdangki1.setSelection(edtpassdangki1.getText().length());

                }
            }
        });

        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (isOpen && edtuserdangki.hasFocus()|| isOpen && edtpassdangki1.hasFocus()) {
                    View lastChild = scroll.getChildAt(scroll.getChildCount() - 1);
                    int bottom = lastChild.getBottom() + scroll.getPaddingBottom();
                    int sy = scroll.getScrollY();
                    int sh = scroll.getHeight();
                    int delta = bottom - (sy + sh);
                    scroll.smoothScrollBy(0, delta);
                }
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangki();
                Dangki.this.onBackPressed();
            }
        });
    }



    private  void dangki()
    {
        String user=edtuserdangki.getText().toString();
        String pass=edtpassdangki1.getText().toString();
        mAthu.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Dangki.this,"Đăng kí thành công!",Toast.LENGTH_SHORT).show();
                            edtuserdangki.setText("");
                            edtpassdangki1.setText("");

                        } else {
                            Toast.makeText(Dangki.this,"Đăng kí thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private void addcontrols() {
        dangky= this.<Button>findViewById(R.id.btndangky);
        edtuserdangki= this.<EditText>findViewById(R.id.eduserregester);
        edtpassdangki1= this.<EditText>findViewById(R.id.edtpassregester1);
        toogle = this.<LinearLayout>findViewById(R.id.toogle);
        toogleEye = this.<ImageView>findViewById(R.id.toogle_eye);
        scroll = findViewById(R.id.scroll);
        btnBack = findViewById(R.id.btnBack);
    }
}
