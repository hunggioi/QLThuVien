package com.example.quanlythuvien;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class CustomIntent  extends Intent {
    public static final int FLAG_HATTO_INTENT=603979776;
    public CustomIntent(Context packageContext, Class<?> cls){
        super(packageContext,cls);
    }
    @NonNull
    @Override
    public Intent addFlags(int flags) {
        if(flags==FLAG_HATTO_INTENT)
            return super.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        else
            return super.addFlags(flags);
    }
}
