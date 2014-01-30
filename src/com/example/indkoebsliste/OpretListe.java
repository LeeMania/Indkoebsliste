package com.example.indkoebsliste;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

public class OpretListe extends Activity {
	private Button btnTilf;
	private EditText eTNavn;
	private ListView lVTlft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opret_liste);
		btnTilf = (Button) findViewById(R.id.btnTilf);
		eTNavn = (EditText) findViewById(R.id.eTNavn);
		lVTlft = (ListView) findViewById(R.id.lVTlft);
		
		
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
		String navn = eTNavn.getText().toString();
		//Hent alle textviews
		for (int i = 0; i < lVTlft.getCount(); i++) {			
			lVTlft.getItemAtPosition(i).toString();
		}
		
		//parse data
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opret_liste, menu);
		return true;
	}

}
