package com.swishsoftwaresolutions.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sqlite extends AppCompatActivity {
    Button add,show;
    EditText name,number;
    TextView inserted_name,inserted_number;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        add = (Button)findViewById(R.id.addData);
        show = (Button)findViewById(R.id.showData);
        name = (EditText)findViewById(R.id.name);
        number = (EditText)findViewById(R.id.number);
        inserted_name = (TextView)findViewById(R.id.name1);
        inserted_number = (TextView)findViewById(R.id.num);
        dataBase = new DataBase(Sqlite.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1 = name.getText().toString();
                String num = number.getText().toString();
                if(name1.equals("") || num.equals("")){

                    Toast.makeText(Sqlite.this,"Enter the values",Toast.LENGTH_LONG).show();

                }else{
                    dataBase.addData(name1,num);
                    Toast.makeText(Sqlite.this,"Added successfully",Toast.LENGTH_LONG).show();
                }


            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sqlite.this,List_View.class);
                startActivity(intent);

            }
        });

    }
}
