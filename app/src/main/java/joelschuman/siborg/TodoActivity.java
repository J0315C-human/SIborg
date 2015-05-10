package joelschuman.siborg;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;


public class TodoActivity extends ActionBarActivity {

    private ListView todoListView;
    private ArrayAdapter<String> listAdapter;
    private String tempNewTask = "Type Something!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ArrayList<String> tasksList = new ArrayList<String>();
        todoListView = (ListView) findViewById( R.id.todoListView );

        String[] tasks = new String[] { "Lesson Plan", "Attendance", "Timesheet"};


        tasksList.addAll( Arrays.asList(tasks));

        listAdapter = new ArrayAdapter<String>(this, R.layout.todo_row, tasksList);

        todoListView.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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

    public void onClickEnterTask(View arg0) {
        Assert.assertNotNull(arg0);
        // Get string entered
        TextView newTask = (TextView) findViewById(R.id.enter_name);
        // Add string to underlying data structure
        tempNewTask = newTask.getText().toString();
    }


    public void onClickAddNewTask(View v){

        //Make sure the new task has text
        Assert.assertNotNull(tempNewTask);

        listAdapter.add(tempNewTask);

    }

    public void onClickRemoveLast(View v){

        Assert.assertFalse(listAdapter.isEmpty());
        listAdapter.remove(tempNewTask);
    }
}
