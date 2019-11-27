package com.example.quanlythuvien;

import android.app.Activity;
import android.content.Intent;
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

import com.example.quanlythuvien.Fragment.ThuVienFragMent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class Dangnhap extends AppCompatActivity {
    EditText edtuser,edtpass;
    Button btndangnhap,btndangki;
    private FirebaseAuth mAthu;
    private ScrollView scroll;
    private LinearLayout toogle,btnBack;
    boolean flagToogle = false;
    ImageView toogleEye,banner_hatto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        addcontrol();
        addevent();
        mAthu=FirebaseAuth.getInstance();
    }

    private void addevent() {
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtuser.length()!=0&&edtpass.length()!=0)
                {
                dangnhapht();
                }
                else
                {
                    Toast.makeText(Dangnhap.this, "Bạn chưa nhập tài khoản !", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        btndangki.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Dangnhap.this,Dangki.class);
//                startActivity(intent);
//
//            }
//        });

        KeyboardVisibilityEvent.setEventListener(this, new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (isOpen && edtuser.hasFocus()|| isOpen && edtpass.hasFocus()) {
                    View lastChild = scroll.getChildAt(scroll.getChildCount() - 1);
                    int bottom = lastChild.getBottom() + scroll.getPaddingBottom();
                    int sy = scroll.getScrollY();
                    int sh = scroll.getHeight();
                    int delta = bottom - (sy + sh);
                    scroll.smoothScrollBy(0, delta);
                }
            }
        });

        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flagToogle) {
                    flagToogle = false;
                    edtpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    toogleEye.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_pass_inactive));
                    edtpass.setSelection(edtpass.getText().length());
                } else {
                    flagToogle = true;
                    edtpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    toogleEye.setImageDrawable(getResources().getDrawable(R.drawable.ic_eye_pass_active));
                    edtpass.setSelection(edtpass.getText().length());

                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangnhap.this.onBackPressed();
            }
        });


    }
private void dangnhapht()
{
    String user=edtuser.getText().toString();
    String pass=edtpass.getText().toString();
    mAthu.signInWithEmailAndPassword(user,pass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Dangnhap.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Dangnhap.this, ThuVienFragMent.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Dangnhap.this,"User hoặc password sai!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
}

    private void addcontrol() {
        edtuser= this.<EditText>findViewById(R.id.edtuser);
        edtpass= this.<EditText>findViewById(R.id.edtpass);
        btndangnhap= this.<Button>findViewById(R.id.btndangnhap);
        toogle = this.<LinearLayout>findViewById(R.id.toogle);
        toogleEye = this.<ImageView>findViewById(R.id.toogle_eye);
        scroll = findViewById(R.id.scroll);
        btnBack = findViewById(R.id.btnBack);
    }

}
