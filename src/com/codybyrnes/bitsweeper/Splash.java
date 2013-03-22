package com.codybyrnes.bitsweeper;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	MediaPlayer splashSong;
	@Override
	protected void onCreate(Bundle splashScreen) {
		super.onCreate(splashScreen);
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
			
			@Override
			public void run(){
				try{
					sleep(2000);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally{
					Intent i = new Intent(Splash.this, MineField.class);
					startActivity(i);
					finish();
					
				}
			}
			
		};
		
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
}
