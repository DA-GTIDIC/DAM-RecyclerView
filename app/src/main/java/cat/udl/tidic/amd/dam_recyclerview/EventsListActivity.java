package cat.udl.tidic.amd.dam_recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import cat.udl.tidic.amd.dam_recyclerview.viewmodel.EventViewModel;

public class EventsListActivity extends AppCompatActivity implements LifecycleOwner {


    private EventViewModel viewModel;

    private ImageButton searchButton;
    private ImageButton addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.activityMainRcyMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final EventAdapter adapter = new EventAdapter(new EventDiffCallback());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                Toast.makeText(getApplicationContext(), "Update event", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel = new EventViewModel(this.getApplication());
        viewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                adapter.submitList(events);
            }
        });


        searchButton = findViewById(R.id.searchButton);
        addButton = findViewById(R.id.createButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.activityMainAtcEventUserId);

                try {
                    int userId = Integer.parseInt(textView.getText().toString());
                    viewModel.getEvents(userId);
                }catch(Exception e){
                    viewModel.getAllEvents();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.insert();
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





//        EventsListActivity context;
//        EventViewModel viewModel;
//        RecyclerView recyclerView;
//        EventAdapter recyclerViewAdapter;
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_events_list);
//            context = this;
//            recyclerView = findViewById(R.id.activityMainRcyMain);
//            viewModel = new EventViewModel(this.getApplication());
//            viewModel.getEvents().observe(this, new Observer<List<Event>>() {
//                @Override
//                public void onChanged(@Nullable List<Event> event) {
//                    recyclerViewAdapter.submitList(event);
//                }
//            });
//
//        }





    }




