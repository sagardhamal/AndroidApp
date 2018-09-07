package com.pibusa.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pibusa.prefrence.SharedPrefrenceManager;
import java.util.ArrayList;


public class AutocompleteTextActivity extends AppCompatActivity implements View.OnClickListener {
   private AutoCompleteTextView actv;
   private ArrayAdapter<String> adapter;
    boolean isInDb=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete_text);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, getSaveList());
        //Getting the instance of AutoCompleteTextView
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSearch) {
            String value = actv.getText().toString();
            if (!value.isEmpty()) {
                saveInPrefrence(value);
                adapter.clear();
                adapter.addAll(getSaveList());
            }

        }


    }

    private void saveInPrefrence(String updateValue) {
        SharedPrefrenceManager prefrence = SharedPrefrenceManager.getInstance();
        String json = prefrence.getSavedPrefrenceValue(AutocompleteTextActivity.this, "", SharedPrefrenceManager.LIST_PREFRENCE_KEY);
       if(json.isEmpty()){
           json="test";
       }
        Gson gson = new Gson();
        ArrayList<String> listName=new ArrayList<String>();
        try {
            listName = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
            }.getType());
        }catch(Exception e){
            e.printStackTrace();
        }
        listName.add(updateValue);
        String res = gson.toJson(listName);
        prefrence.setPrefrenceValue(AutocompleteTextActivity.this, res, SharedPrefrenceManager.LIST_PREFRENCE_KEY);
    }

    private String[] getSaveList() {
        SharedPrefrenceManager prefrence = SharedPrefrenceManager.getInstance();
        String json = prefrence.getSavedPrefrenceValue(AutocompleteTextActivity.this, "", SharedPrefrenceManager.LIST_PREFRENCE_KEY);
        Gson gson = new Gson();
        ArrayList<String> listName = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
        }.getType());
        if(listName!=null && listName.size()>0) {
            return listName.toArray(new String[listName.size()]);
        }else{
            String[] res = {"sagar"};
            return res;
        }
    }


}
