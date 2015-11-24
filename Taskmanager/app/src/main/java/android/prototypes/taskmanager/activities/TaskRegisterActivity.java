package android.prototypes.taskmanager.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.prototypes.taskmanager.R;
import android.prototypes.taskmanager.dao.TaskDAO;
import android.prototypes.taskmanager.entities.TaskEntity;
import android.prototypes.taskmanager.services.SearchCEPService;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class TaskRegisterActivity extends AppCompatActivity {

    private DatePickerFragment datePickerFragment;
    private TaskDAO taskDAO;
    private SearchCEPService searchCEPService;
    private int taskId = 0;

    public TaskRegisterActivity() {
        taskDAO = new TaskDAO();
        taskDAO.setContext(this);
    }

    public void saveTask(View view) {

        TaskEntity task = new TaskEntity();
        EditText inputTitle = (EditText) findViewById(R.id.inputTitle);
        EditText inputDescription = (EditText) findViewById(R.id.inputDescription);

        task.setDueDate(datePickerFragment.getDueDate());
        task.setTitle(inputTitle.getText().toString());
        task.setDescription(inputDescription.getText().toString());
        task.setLocality(searchCEPService.getLocation().getLocality());

        if (taskId == 0) {
            taskDAO.create(task);
        } else {
            task.setId(taskId);
            taskDAO.update(task);
        }

        finish();
    }

    public void searchLocation(View view) {

        EditText cep = (EditText) findViewById(R.id.inputLocation);

        searchCEPService = new SearchCEPService(getApplicationContext());
        searchCEPService.execute("GET", "https://viacep.com.br/ws/", cep.getText().toString(), "/json");
    }

    public void showDatePickerDueDate(View view) {
        datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_register);

        String taskIdStr = getIntent().getExtras().getString("taskId");

        if (!taskIdStr.equals("new")) {
            taskId = Integer.parseInt(taskIdStr);
            TaskEntity task = taskDAO.get(taskId);
            EditText title = (EditText) findViewById(R.id.inputTitle);
            EditText description = (EditText) findViewById(R.id.inputDescription);
            title.setText(task.getTitle());
            description.setText(task.getDescription());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_register, menu);
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

    // Subclass that generate date picker to due date:
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Date dueDate;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            dueDate = calendar.getTime();
        }

        public Date getDueDate() {
            return dueDate;
        }
    }

}
