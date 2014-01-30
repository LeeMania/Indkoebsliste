package com.example.indkoebsliste;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class OpretListe extends Activity {
	private Button btnTilf;
	private EditText eTNavn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opret_liste);
		btnTilf = (Button) findViewById(R.id.btnTilf);
		eTNavn = (EditText) findViewById(R.id.eTNavn);
		
		
		btnTilf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				parseTilDb();
				
				startActivity(i);
			}
		});
	}

	private void parseTilDb() {
		
		//parse data
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opret_liste, menu);
		return true;
	}

}
