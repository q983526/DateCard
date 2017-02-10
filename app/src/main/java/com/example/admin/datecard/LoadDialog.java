package com.example.admin.datecard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.Window;
import android.widget.ProgressBar;

public class LoadDialog extends Dialog{
	public LoadDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = getWindow();
		window.setBackgroundDrawable(new BitmapDrawable());

		window.setGravity(Gravity.CENTER);

		setCancelable(true);
		ProgressBar bar = new ProgressBar(context);

		setContentView(bar);
	}
	
	

}
