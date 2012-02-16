package cn.edu.hit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
	private Thread splashTimer;
	private long splashTime = 3000;
	private boolean isSplashActive = true;
	private boolean isPaused = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullScreen();
		setContentView(R.layout.splash);
		startSplashTimer();
	}

	/*
	 * set full screen feature
	 */
	private void setFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
		Window mWindow = this.getWindow();
		mWindow.setFlags(flag, flag);
	}

	/*
	 * start mutithread timer
	 */
	public void startSplashTimer() {
		splashTimer = new Thread() {
			/*
			 * sleep for (3000/100)*100ms = 3000ms = 3s
			 */
			public void run() {
				try {
					long ms = 0;
					while (isSplashActive && (ms < splashTime)) {
						sleep(100);
						if (!isPaused) {
							ms += 100;
						}
					}

					startActivity(new Intent("com.google.app.CLEARSPLASH"));
				} catch (Exception e) {
					Log.e("splash", e.getMessage());
				} finally {
					finish();
				}
			}
		};
		/* timer start */
		splashTimer.start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		super.onKeyDown(keyCode, event);
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_DOWN:
		case KeyEvent.KEYCODE_DPAD_LEFT:
		case KeyEvent.KEYCODE_DPAD_RIGHT:
		case KeyEvent.KEYCODE_DPAD_UP:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			isSplashActive = false;
			break;
		case KeyEvent.KEYCODE_BACK:
			android.os.Process.killProcess(android.os.Process.myPid());
			break;
		default:
			break;
		}
		
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isPaused = true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		isPaused = false;
	}

}
