package com.zash.goco;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
   private Activity mActivity;
    private AlertDialog mDialog;
    LoadingDialog(Activity myActivity){
        mActivity = myActivity;

    }

    void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_anim, null));
        builder.setCancelable(true);


        mDialog = builder.create();
        mDialog.show();

    }
    void  endLoading()
    {
        mDialog.dismiss();
    }
}
