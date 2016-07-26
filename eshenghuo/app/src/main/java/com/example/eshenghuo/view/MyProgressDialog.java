package com.example.eshenghuo.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshenghuo.R;


public class MyProgressDialog {
	
	public Dialog mDialog;
	private TextView text;
	private Handler handler;
	private ImageView loadingImage;
	private AnimationDrawable animationDrawable = null;
	private Animation animation;
	public MyProgressDialog(Context context, String message) {
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.progress_view, null);

		text = (TextView) view.findViewById(R.id.progress_message);
		text.setText(message);
		loadingImage = (ImageView) view.findViewById(R.id.progress_view);
		loadingImage.setImageResource(R.drawable.loading_animation);
		animationDrawable = (AnimationDrawable) loadingImage.getDrawable();
		if (animationDrawable != null) {
			animationDrawable.setOneShot(false);
			animationDrawable.start();
		}


		mDialog = new Dialog(context, R.style.dialog);
		mDialog.setContentView(view);
		mDialog.setCanceledOnTouchOutside(false);
		
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				text.setText(msg.obj+"");
			}
		};
		
	}
	
	public void show() {
		if(animation != null) {
			loadingImage.startAnimation(animation);
		}
		if(!isShowing()) {
			mDialog.show();
		}

	}
	
	public void setCanceledOnTouchOutside(boolean cancel) {
		mDialog.setCanceledOnTouchOutside(cancel);
	}
	
	public void dismiss() {
		if(mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}
    public boolean isShowing(){
        if(mDialog.isShowing()) {
            return true;
        }
        return false;
    }
    public void setDialogText(final String text) {
    	new Thread() {
    		public void run() {
    			Message message = new Message();
    			message.obj = text;
    			handler.sendMessage(message);
    		};
    	}.start();
    }
}
