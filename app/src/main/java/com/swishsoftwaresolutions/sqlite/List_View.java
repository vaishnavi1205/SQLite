package com.swishsoftwaresolutions.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class List_View extends AppCompatActivity {
    ListView list;
    TextView name,number;
    ArrayList<ModuleClass> moduleClass;
    DataBase dataBase;
    EditText name1,phnum;
    AlertDialog alert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(List_View.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        list = (ListView)findViewById(R.id.listview);
        dataBase = new DataBase(List_View.this);
        moduleClass = dataBase.retriveData();

//        for(ModuleClass md: arrayList){
//            String retrieved_name = md.getName1();
//            String retrieved_number = md.getPh_number();
//            inserted_name.setText(retrieved_name);
//            inserted_number.setText(retrieved_number);
//        }
        final CustomAdapter adapter = new CustomAdapter(List_View.this,0);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                View view1 = LayoutInflater.from(List_View.this).inflate(R.layout.update_content,null);
                builder.setView(view1);
                 name1 = (EditText)view1.findViewById(R.id.upname);
                 phnum = (EditText)view1.findViewById(R.id.upnumber);
                Button del = (Button)view1.findViewById(R.id.delete);
                Button up = (Button)view1.findViewById(R.id.update);
                name1.setText(moduleClass.get(i).getName1());
                phnum.setText(moduleClass.get(i).getPh_number());


                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        builder.setMessage("Are you sure you want to delete this?");
                        alert1.cancel();
                        dataBase.deleteData(moduleClass.get(i).getId());
                                moduleClass = dataBase.retriveData();
                                adapter.notifyDataSetChanged();
                    }});
//                builder.setPositiveButton(
//                        "Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                                dataBase.deleteData(moduleClass.get(i).getId());
//                                moduleClass = dataBase.retriveData();
//                                adapter.notifyDataSetChanged();
////                                list.setAdapter(adapter);
//                            }
//                        });
//
//                builder.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
                alert1 = builder.create();
                alert1.show();



                up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert1.dismiss();
                        int id = moduleClass.get(i).getId();
                        String name2 = name1.getText().toString();
                        String  number = phnum.getText().toString();
                        dataBase.updateData(name2,number,id);
                        moduleClass = dataBase.retriveData();
                        adapter.notifyDataSetChanged();

                    }
                });


           }
       });


    }

    class CustomAdapter extends ArrayAdapter {
        public CustomAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public int getCount() {
            return moduleClass.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(List_View.this);
                convertView = inflater.inflate(R.layout.custom_list,null);
                name = (TextView) convertView.findViewById(R.id.text1);
                number = (TextView) convertView.findViewById(R.id.text);

            }
            name.setText(moduleClass.get(position).getName1());
            number.setText(moduleClass.get(position).getPh_number());

            return convertView;
        }


    }
}
