package com.example.indkoebsliste;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	private boolean isLoggedIn = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(isLoggedIn){
			Intent i = new Intent(getApplicationContext(), VaelgListe.class);
			String[] lister = {"Test","Test2"};
			i.putExtra("lister", lister);
			i.putExtra("brugernavn", "");
			startActivity(i);
		}else{
			//start login activity
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
