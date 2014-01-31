package com.example.indkoebsliste;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RedigerListe extends Activity {
	private Button btnTilf, btnGemListe, btnSort, btnAnnList;
	private EditText eTNavn;
	private ListView lVTlft;
	private Context context = this;
	private ArrayAdapter<String> arrAdap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opret_liste);
		btnTilf = (Button) findViewById(R.id.btnTilf);
		eTNavn = (EditText) findViewById(R.id.eTNavn);
		eTNavn.setText(getIntent().getStringExtra("listeNavn").toString());
		lVTlft = (ListView) findViewById(R.id.lVTlft);
		btnGemListe = (Button) findViewById(R.id.btnGemListe);
		btnSort = (Button) findViewById(R.id.btnSort);
		btnAnnList = (Button) findViewById(R.id.btnAnnList);

		arrAdap = new ArrayAdapter<String>(this, R.layout.row);

		btnTilf.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				tilfjPrompt();
			}
		});

		btnGemListe.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				opret();
			}
		});

		btnSort.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				sort();
			}
		});

		btnAnnList.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent i = new Intent(context, VaelgListe.class);
				startActivity(i);
			}
		});
		
		lVTlft.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentView, View childView,
					int position, long id) {
				rediPrompt(position);
			}
		});
	}

	private void sletObjekt(int pos) {
		ArrayList<String> temp = new ArrayList<String>();

		for (int i = 0; i < pos; i++) {
			temp.add(arrAdap.getItem(i));
		}
		
		for (int i = pos+1; i < arrAdap.getCount(); i++) {
			temp.add(arrAdap.getItem(i));
		}

		arrAdap.clear();

		for (int i = 0; i < temp.size(); i++) {
			arrAdap.add(temp.get(i));
		}

		lVTlft.setAdapter(arrAdap);
	}

	private void redigerObjekt(String nyNavn, int pos) {
		ArrayList<String> temp = new ArrayList<String>();

		for (int i = 0; i < lVTlft.getCount(); i++) {
			if (i != pos) {
				temp.add(lVTlft.getItemAtPosition(i).toString());
			} else if (i == pos) {
				temp.add(nyNavn);
			}
		}

		arrAdap.clear();

		for (int i = 0; i < temp.size(); i++) {
			arrAdap.add(temp.get(i));
		}

		lVTlft.setAdapter(arrAdap);
	}

	private void sort() {
		ArrayList<String> listData = new ArrayList<String>();

		for (int i = 0; i < lVTlft.getCount(); i++) {
			listData.add(lVTlft.getItemAtPosition(i).toString());
		}

		Collections.sort(listData);

		arrAdap.clear();

		for (int i = 0; i < listData.size(); i++) {
			arrAdap.add(listData.get(i));
		}

		lVTlft.setAdapter(arrAdap);
	}

	private void tilfjPrompt() {
		LayoutInflater layIn = LayoutInflater.from(context);
		View prompt = layIn.inflate(R.layout.tilfoj_prompt, null);
		AlertDialog.Builder alertDiaBuild = new AlertDialog.Builder(context);
		alertDiaBuild.setView(prompt);
		final EditText in = (EditText) prompt.findViewById(R.id.tilfojPromptIn);
		alertDiaBuild
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						tilfjTilListe(in.getText().toString());
					}
				})
				.setNegativeButton("Annuller",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alertD = alertDiaBuild.create();

		alertD.show();
	}

	private void rediPrompt(final int pos) {
		LayoutInflater layIn = LayoutInflater.from(context);
		View prompt = layIn.inflate(R.layout.redi_prompt, null);
		AlertDialog.Builder alertDiaBuild = new AlertDialog.Builder(context);
		alertDiaBuild.setView(prompt);
		final EditText in = (EditText) prompt.findViewById(R.id.rediPromptIn);
		in.setText(lVTlft.getItemAtPosition(pos).toString());
		alertDiaBuild
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						redigerObjekt(in.getText().toString(), pos);
					}
				})
				.setNegativeButton("Annuller",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})
				.setNeutralButton("Slet",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								sletObjekt(pos);
							}
						});
		AlertDialog alertD = alertDiaBuild.create();

		alertD.show();
	}

	protected void tilfjTilListe(String string) {
		arrAdap.add(string);
		lVTlft.setAdapter(arrAdap);

	}

	private void opret() {
//		String navn = eTNavn.getText().toString();
		ArrayList<String> listData = new ArrayList<String>();

		// Henter alle textviews
		for (int i = 0; i < lVTlft.getCount(); i++) {
			listData.add(lVTlft.getItemAtPosition(i).toString());
		}

		// parse navn og listData

		Intent intent = new Intent(context, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opret_liste, menu);
		return true;
	}

}
