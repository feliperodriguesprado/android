package android.prototypes.taskmanager.activities;

import android.content.Intent;
import android.prototypes.taskmanager.R;
import android.prototypes.taskmanager.dao.TaskDAO;
import android.prototypes.taskmanager.entities.TaskEntity;
import android.prototypes.taskmanager.services.SearchCEPService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private ListView listView;
    private List<TaskEntity> taskList = new ArrayList<>();
    private TaskDAO taskDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        setTaskList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTaskList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
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

    public void setTaskList() {

        // Get ListView object from xml "activity_task_list.xml":
        listView = (ListView) findViewById(R.id.listView);

        // Get task list in database and define array values to show in ListView:
        taskDAO = new TaskDAO();
        taskDAO.setContext(this);
        taskList = taskDAO.getAll();

        // Define new ArrayAdapter:
        ArrayAdapter<TaskEntity> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, taskList);

        // Set adapter in List View:
        listView.setAdapter(adapter);

        // Set click listener to item in List View:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskEntity taskInList = (TaskEntity) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), Integer.toString(taskInList.getId()), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TaskListActivity.this, TaskRegisterActivity.class);
                intent.putExtra("taskId", Integer.toString(taskInList.getId()));
                startActivity(intent);

            }
        });
    }

    public void newTask(View view) {
        Intent intent = new Intent(TaskListActivity.this, TaskRegisterActivity.class);
        intent.putExtra("taskId", "new");
        startActivity(intent);
    }
}
