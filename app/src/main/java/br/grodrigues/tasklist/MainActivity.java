package br.grodrigues.tasklist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static br.grodrigues.tasklist.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private List<String> tasks = new ArrayList<String>();
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this,
                R.layout.item_task,
                R.id.textView,
                tasks);
        listView.setAdapter(arrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_task:
                final EditText editText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add new Task")
                        .setMessage("What do you want to add")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface int i) {
                                String task = String.valueOf(editText.getText());

                                tasks.add(task);

                                Log.d("Main","Task added" + tasks);
                            }
                        })
                        .setPositiveButton("Cancel",null)
                        .create();
            dialog.show();

            default:
               return super.onOptionsItemSelected(item);
        }
    }
    public void removeTask(View view){
        View parent = (View) view.getParent();

        TextView task = (TextView) parent.findViewById(R.id.textView);
        String taskText = String.valueOf(task.getText());
        arrayAdapter.remove(taskText);
        arrayAdapter.notifyDataSetChanged();
    }
}
