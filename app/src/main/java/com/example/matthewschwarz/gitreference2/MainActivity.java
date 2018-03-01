package com.example.matthewschwarz.gitreference2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView rowView;
    private Adapter adapter;
    ArrayList<GitReference> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rowView = findViewById(R.id.MainListView);
        itemList = populateData(getString(R.string.gitReference_json));
        adapter = new Adapter(getApplicationContext(), itemList);

        rowView.setAdapter(adapter);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public ArrayList<GitReference> populateData(String fileName){
        String jsonString = processData(fileName);
        Log.i(getString(R.string.JSON), jsonString);
        ArrayList<GitReference> references = JsonUtils.populateGitReferences(jsonString);
        return references;
        //return JsonUtils.populateGitReferences(fileName);
    }

    public String processData(String filename){
        String jsonstring = "";
        boolean isFilePresent = JsonUtils.isFilePresent(this, filename);

        if(isFilePresent) {
            jsonstring = JsonUtils.read(this, filename);
            InputStream inputStream = null;
            try{
                inputStream = getApplicationContext().getAssets().open(getString(R.string.gitReference_json));

            }catch(Exception ex){
                System.out.println(getString(R.string.gitRefNotFound));
            }
            String example = JsonUtils.parseJsonToString(inputStream);
            Log.i(getString(R.string.JSON), getString(R.string.JSONFilePresent));
            //Log.i("JSON", "JSON file not present. Creating.......");
        }else{
            //create file since one is not found
            Log.i(getString(R.string.JSON), getString(R.string.JSONFileNotPresent));
            InputStream inputStream = null;
            try{
                inputStream = getApplicationContext().getAssets().open(getString(R.string.gitReference_json));

            }catch(Exception ex){
                System.out.println(getString(R.string.gitRefNotFound));
            }
            jsonstring = JsonUtils.parseJsonToString(inputStream);
            boolean isFileCreated = JsonUtils.create(this, filename, jsonstring);
            if(isFileCreated){
                Log.i(getString(R.string.JSON), getString(R.string.CreatedJSON));
            }else{
                Log.i(getString(R.string.JSON), getString(R.string.FileNotCreatedError));

            }

        }
        return jsonstring;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
