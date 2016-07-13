package com.example.khumalo.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        items = new ArrayList<String>();
        ListView TodoList = (ListView)findViewById(R.id.To_Do_List);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        TodoList.setAdapter(adapter);
        TodoList.setLongClickable(true);

        TodoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                handleClick(position);
                return true;

            }
        });

        Log.d(TAG, "The value of the list is");
        Log.i(TAG, "Inside onCreate Method");
        Log.e(TAG, "Inside onCreate Method");
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



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("items", items);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        items = savedInstanceState.getStringArrayList("items");
        Log.d(TAG, "The value of the list is" + items.size());

        populateAdapter();
           }

    private void populateAdapter() {
        ListView TodoList = (ListView)findViewById(R.id.To_Do_List);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        TodoList.setAdapter(adapter);
        TodoList.setLongClickable(true);

        TodoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                handleClick(position);
                return true;

            }
        });
    }

    //Adding items to the List when the user clicks the ADD button
    public void AddItem(View view) {
        EditText EnteredText = (EditText)findViewById(R.id.Entered_Value);
        String ToDoItem = EnteredText.getText().toString();
        if(!TextUtils.isEmpty(ToDoItem)) {
            items.add(ToDoItem);
            adapter.notifyDataSetChanged();
        }
   }

    public void handleClick(int position){
        items.remove(position);
        adapter.notifyDataSetChanged();
    }
}
