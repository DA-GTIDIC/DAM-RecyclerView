package cat.udl.tidic.amd.dam_recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import cat.udl.tidic.amd.dam_recyclerview.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_recyclerview.viewmodel.EventViewModel;

public class EventsListActivity extends AppCompatActivity implements LifecycleOwner {

    public static final int INSERT_EVENT = 1;
    public static final int EDIT_EVENT = 2;
    public static final String TAG = "EventListActivity";
    private SharedPreferences mPreferences;


    private EventViewModel viewModel;

    private ImageButton searchButton;
    private ImageButton addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        this.mPreferences = PreferencesProvider.providePreferences();
        initViews();
    }

    private void initViews() {

        addButton = findViewById(R.id.createEvent);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsListActivity.this,
                        AddEditEventActivity.class);
                startActivityForResult(intent, INSERT_EVENT);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.activityMainRcyMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final EventAdapter adapter = new EventAdapter(new EventDiffCallback());
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                Log.d(TAG, event.getTittle());
                Intent intent = new Intent(EventsListActivity.this, AddEditEventActivity.class);
                intent.putExtra(AddEditEventActivity.EXTRA_ID, event.getId());
                intent.putExtra(AddEditEventActivity.EXTRA_TITLE, event.getTittle());
                intent.putExtra(AddEditEventActivity.EXTRA_DESCRIPTION, event.getDescription());
                intent.putExtra(AddEditEventActivity.EXTRA_START, event.getStart());
                intent.putExtra(AddEditEventActivity.EXTRA_END, event.getEnd());
                intent.putExtra(AddEditEventActivity.EXTRA_AVALUATION, event.getAvaluation());
                startActivityForResult(intent, EDIT_EVENT);
            }
        });

        viewModel = new EventViewModel(this.getApplication());
        viewModel.setUserId("");
        viewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                adapter.submitList(events);
            }
        });




        searchButton = findViewById(R.id.searchButton);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.activityMainAtcEventUserId);
                viewModel.setUserId(textView.getText().toString());
            }
        });




        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull  RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.removeEvent(adapter.getEventAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Deleted event", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (requestCode == INSERT_EVENT && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditEventActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditEventActivity.EXTRA_DESCRIPTION);
            Date dateStart = (Date)data.getSerializableExtra(AddEditEventActivity.EXTRA_START);
            //String start = formatter.format(dateStart);
            Date dateEnd = (Date)data.getSerializableExtra(AddEditEventActivity.EXTRA_END);
            //String end = formatter.format(dateEnd);
            //String end = data.getStringExtra(AddEditEventActivity.EXTRA_END);
            float avaluation = data.getIntExtra(AddEditEventActivity.EXTRA_AVALUATION, 1);

            String current_user = this.mPreferences.getString("current_user", "");
            Event event = new Event(Integer.parseInt(current_user),dateStart,dateEnd,title,description,avaluation);
            viewModel.insert(event);

            Toast.makeText(this, "Event saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_EVENT && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditEventActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Event can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditEventActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditEventActivity.EXTRA_DESCRIPTION);
            Date dateStart = (Date)data.getSerializableExtra(AddEditEventActivity.EXTRA_START);
            Date dateEnd = (Date)data.getSerializableExtra(AddEditEventActivity.EXTRA_END);
            //String start = data.getStringExtra(AddEditEventActivity.EXTRA_START);
            //String end = data.getStringExtra(AddEditEventActivity.EXTRA_END);
            float avaluation = data.getFloatExtra(AddEditEventActivity.EXTRA_AVALUATION, 1);


            String current_user = this.mPreferences.getString("current_user", "");
            Event event = new Event(Integer.parseInt(current_user),dateStart,dateEnd,title,description,avaluation);

            event.setId(id);
            viewModel.update(event);

            Toast.makeText(this, "Event updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Event not saved", Toast.LENGTH_SHORT).show();
        }
    }

    }




