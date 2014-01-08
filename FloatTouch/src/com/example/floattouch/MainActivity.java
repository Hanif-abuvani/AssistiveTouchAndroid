package com.example.floattouch;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btnMngService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	btnMngService = (Button) findViewById(R.id.btnmngservice);
	btnMngService.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View arg0) {
		boolean getServiceState = isMyServiceRunning();
		if (getServiceState) {
		    stopService(new Intent(MainActivity.this, HUD.class));
		    btnMngService.setText(R.string.start);

		} else {
		    startService(new Intent(MainActivity.this, HUD.class));
		    btnMngService.setText(R.string.stop);

		}

	    }
	});

    }

    @Override
    protected void onResume() {
	super.onResume();

	boolean getServiceState = isMyServiceRunning();
	if (getServiceState) {
	    btnMngService.setText(R.string.stop);
	} else {
	    btnMngService.setText(R.string.start);
	}

    }

    private boolean isMyServiceRunning() {
	ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	    if (HUD.class.getName().equals(service.service.getClassName())) {
		return true;
	    }
	}
	return false;
    }

}
