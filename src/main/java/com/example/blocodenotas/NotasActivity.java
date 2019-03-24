package com.example.blocodenotas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotasActivity extends AppCompatActivity {

    EditText edttext;
    Button btncriar;
    ListView listView;

    private ArrayList <String> notas = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);

        //notas
        edttext = (EditText) findViewById(R.id.edttext);
        btncriar = (Button) findViewById(R.id.btncriar);
        listView = (ListView) findViewById(R.id.list_notes);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notas);
        listView.setAdapter(adapter);

        btncriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNota = edttext.getText().toString();
                if (txtNota.length()>0){
                    edttext.setText("");
                    edttext.findFocus();
                    notas.add(txtNota);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(NotasActivity.this);
                adb.setTitle("Nota");
                adb.setMessage("Você deseja apagar esta nota?");
                final int positionToRemove = position;
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        notas.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }

                });
                adb.show();
            }
        });
    }
}
