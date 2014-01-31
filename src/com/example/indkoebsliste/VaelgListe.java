package com.example.indkoebsliste;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class VaelgListe extends Activity {
	private Button btnOpret;
	private ListView lVLister;
	private ArrayAdapter<String> arrAdap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vaelg_liste);
		btnOpret = (Button) findViewById(R.id.btnOpret);
		lVLister = (ListView) findViewById(R.id.lVLister);
		arrAdap = new ArrayAdapter<String>(this, R.layout.row);

		btnOpret.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), OpretListe.class);
				startActivity(i);
			}
		});

		fyldLister();
	}

	private void fyldLister() {
		String[] lister = getIntent().getStringArrayExtra("lister");
		if (lister != null) {
			for (int i = 0; i < lister.length; i++) {
				arrAdap.add(lister[i]);
			}

			lVLister.setAdapter(arrAdap);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vaelg_liste, menu);
		return true;
	}

}
