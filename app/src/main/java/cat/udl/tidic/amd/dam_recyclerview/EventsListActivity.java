package cat.udl.tidic.amd.dam_recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import cat.udl.tidic.amd.dam_recyclerview.viewmodel.EventViewModel;

public class EventsListActivity extends AppCompatActivity implements LifecycleOwner {


    private EventViewModel eventViewModel;
    private EventListAdapter eventListAdapter =
            new EventListAdapter(new EventDiffCallback());
    private ImageButton searchButton;
    private View.OnClickListener searchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        initViews();
    }

    @Override
    protected  void onStart() {
        super.onStart();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.activityMainAtcEventUserId);

                try {
                    int userid = Integer.parseInt(textView.getText().toString());
                    eventViewModel.getEvents(userid);
                }catch(Exception e){
                    eventViewModel.getAllEvents();
                }
            }
        });

    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.activityMainRcyMain);
        recyclerView.setAdapter(this.eventListAdapter);

        searchButton = findViewById(R.id.searchButton);

        eventViewModel = new EventViewModel(this.getApplication());
        eventViewModel.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> event) {
                eventListAdapter.submitList(event);
            }
        });
    }


//        EventsListActivity context;
//        EventViewModel viewModel;
//        RecyclerView recyclerView;
//        EventsAdapter recyclerViewAdapter;
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




