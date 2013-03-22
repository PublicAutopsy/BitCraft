package com.codybyrnes.bitsweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MineField extends Activity {
	
	Button grid1, grid2, grid3, grid4, grid5, grid6, grid7, grid8, grid9, grid10,
	grid11, grid12, grid13, grid14, grid15, grid16, grid17, grid18, grid19, grid20,
	grid21, grid22, grid23, grid24, grid25, placeBet, reset, cashOut;
	
	TextView payout, betDisplay, balance;
	Spinner setBetValue; 
	SeekBar betSlider;
	
	float winnings = (float) 1.0;
	float bet;
	int betValue;
	int r;
	int mineCount;
	int totMines = 3;
	float chance = (float) 1.02;
	float win = (float) 1.00;
	
	float lockedInOdds;
	
	Random generator = new Random();
	
	
	List<Button> gridList = new ArrayList<Button>();
	List<Integer> mineList = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine_field);
		
		initialize();
		
		reset.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MineField.this, MineField.class);
				startActivity(i);
				finish();
			}
			
		});
		
		betValue = betSlider.getProgress();
		
		setOdds(betValue);
		
		betSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			public void onStartTrackingTouch(SeekBar betSlider){
		    }
		    public void onProgressChanged(SeekBar betSlider, int paramInt, boolean paramBoolean){
		    	setOdds(paramInt);
		    }
			@Override
			public void onStopTrackingTouch(SeekBar betSlider) {
				
			}
		});
	}
	
	protected void setOdds(int modifier){
		if (modifier <= 12){
	    	   betSlider.setProgress(12);
	    	   modifier = 12;
	    	   
	       } else if (modifier == 100){
	    	   betSlider.setProgress(99);
	    	   modifier = 99;
	       }
	    	
	       totMines = modifier/4;
	       float chancePieces_1 = (float) 25 / 100 ;
	       float chancePieces_2 = (float) totMines / chancePieces_1;
	       chance = 100 - chancePieces_2;
	       
	       win = (90 / chance);
	       win = (float)Math.round(win * 100) / 100;
	        
	        betDisplay.setText("" + totMines + " Mines, payout at x" + win);
	}
	
	protected void setMines(){
		
		for (int i=0; i<25; i++){
			final Button tempButton = gridList.get(i);
			tempButton.setOnClickListener( new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					tempButton.setBackgroundResource(R.drawable.dirt);
					
					
					
					float pot = win * lockedInOdds; 
					
					payout.setText(""+pot);
					betDisplay.setText("" + totMines + " Mines, payout at x" + win);
					
					win = win +(win/10);
					
				}
			});
			
		}
		
		while (mineCount < totMines){
			r = generator.nextInt(25);
			
			if (!mineList.contains(r)){
			
				final Button tempButton = gridList.get(r);
				tempButton.setOnClickListener(null);
				tempButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						
						
						for (int x=0; x < totMines; x++){
							final Integer btnSet = mineList.get(x);
							gridList.get(btnSet).setBackgroundResource(R.drawable.unexploded);
							gridList.get(btnSet).setText("");
						}
						
						tempButton.setText("");
						tempButton.setBackgroundResource(R.drawable.tnt);
						bet = (float) 0.0;
						payout.setText(""+bet);
						
						for (int i=0; i<25; i++){
							final Button tempButton = gridList.get(i);
							tempButton.setOnClickListener(null);
						}
					}
				});
				mineList.add(r);
				mineCount++;
			}
		}
		
		System.out.println(mineList);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_mine_field, menu);
		return true;
	}
	
	
	protected void initialize(){
		
		Typeface type = Typeface.createFromAsset(getAssets(),"minecrafter.ttf");
		
		
		//Attack variable names to view IDs
		grid1 = (Button) findViewById(R.id.bGrid1);
		grid2 = (Button) findViewById(R.id.bGrid2);
		grid3 = (Button) findViewById(R.id.bGrid3);
		grid4 = (Button) findViewById(R.id.bGrid4);
		grid5 = (Button) findViewById(R.id.bGrid5);
		grid6 = (Button) findViewById(R.id.bGrid6);
		grid7 = (Button) findViewById(R.id.bGrid7);
		grid8 = (Button) findViewById(R.id.bGrid8);
		grid9 = (Button) findViewById(R.id.bGrid9);
		grid10 = (Button) findViewById(R.id.bGrid10);
		grid11 = (Button) findViewById(R.id.bGrid11);
		grid12 = (Button) findViewById(R.id.bGrid12);
		grid13 = (Button) findViewById(R.id.bGrid13);
		grid14 = (Button) findViewById(R.id.bGrid14);
		grid15 = (Button) findViewById(R.id.bGrid15);
		grid16 = (Button) findViewById(R.id.bGrid16);
		grid17 = (Button) findViewById(R.id.bGrid17);
		grid18 = (Button) findViewById(R.id.bGrid18);
		grid19 = (Button) findViewById(R.id.bGrid19);
		grid20 = (Button) findViewById(R.id.bGrid20);
		grid21 = (Button) findViewById(R.id.bGrid21);
		grid22 = (Button) findViewById(R.id.bGrid22);
		grid23 = (Button) findViewById(R.id.bGrid23);
		grid24 = (Button) findViewById(R.id.bGrid24);
		grid25 = (Button) findViewById(R.id.bGrid25);
		
		placeBet = (Button) findViewById(R.id.bPlaceBet);
		reset = (Button) findViewById(R.id.bReset);
		
		placeBet.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				setMines();
				placeBet.setEnabled(false);
				betSlider.setEnabled(false);
				setBetValue.setEnabled(false);
				reset.setEnabled(false);
				cashOut.setEnabled(true);
				
				lockedInOdds = bet;
				
			}
			
		});
		
		
		//Add buttons to list array
		gridList.add(grid1);
		gridList.add(grid2);
		gridList.add(grid3);
		gridList.add(grid4);
		gridList.add(grid5);
		gridList.add(grid6);
		gridList.add(grid7);
		gridList.add(grid8);
		gridList.add(grid9);
		gridList.add(grid10);
		gridList.add(grid11);
		gridList.add(grid12);
		gridList.add(grid13);
		gridList.add(grid14);
		gridList.add(grid15);
		gridList.add(grid16);
		gridList.add(grid17);
		gridList.add(grid18);
		gridList.add(grid19);
		gridList.add(grid20);
		gridList.add(grid21);
		gridList.add(grid22);
		gridList.add(grid23);
		gridList.add(grid24);
		gridList.add(grid25);
		
		
		
		//Sets text to grass on reset
		for (int i=0; i<25; i++){
		final Button tempButton = gridList.get(i);
			tempButton.setText("");
		}
		 
		setBetValue = (Spinner) findViewById(R.id.sBTCBet);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.betAmounts,
				android.R.layout.simple_spinner_item);
		 adapter.setDropDownViewResource(R.layout.spinner_style);
		 
		 setBetValue.setAdapter(adapter);
		
		payout = (TextView) findViewById(R.id.tvPayout);
		betDisplay = (TextView) findViewById(R.id.tvBetValue);
		betSlider = (SeekBar) findViewById(R.id.sbBet);
		cashOut = (Button) findViewById(R.id.bCashOut);
		balance = (TextView) findViewById(R.id.tvBalance);
		
		
		cashOut.setEnabled(false);
		cashOut.setTypeface(type);
		placeBet.setTypeface(type);
		reset.setTypeface(type);
		betDisplay.setTypeface(type);
		payout.setTypeface(type);
		balance.setTypeface(type);
		
		setBetValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        Object item = parent.getItemAtPosition(pos);
		        String text = setBetValue.getSelectedItem().toString();
		        bet = Float.parseFloat(text);
		        payout.setText(""+bet);
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.exit:
			Intent i = new Intent(MineField.this, MineField.class);
			startActivity(i);
			finish();
			break;
		}
		return false;
	}
}
