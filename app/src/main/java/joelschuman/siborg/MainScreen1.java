package joelschuman.siborg;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainScreen1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen1, menu);
        return true;
    }

    //Main Screen buttons are selected:
    public void buttonPush(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.forum_button:
                intent = new Intent(this, ForumActivity.class);
                break;
            case R.id.resources_button:
                intent = new Intent(this, ResourcesActivity.class);
                break;
            case R.id.todo_button:
                intent = new Intent(this, TodoActivity.class);
                break;
            default:
                intent = new Intent(this, MainScreen1.class);
        }
        startActivity(intent);
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
