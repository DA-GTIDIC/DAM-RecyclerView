package cat.udl.tidic.amd.dam_recyclerview.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;

public class EventDAOStubImpl implements IEventDAO {

    private MutableLiveData<List<Event>> mutableLiveData = new MutableLiveData<>();
    private List<Event> events = new ArrayList<Event>();

    public EventDAOStubImpl() {


        // Mock events
        Event e1 = new Event(1, 1, "20/01/2020", "22/01/2020",
                "Event 1", "Wow this is event 1",3);
        Event e2 = new Event(2, 1, "21/01/2020", "22/01/2020",
                "Event 2", "Wow this is event 2",4);
        Event e3 = new Event(3, 1, "22/01/2020", "24/01/2020",
                "Event 3", "Wow this is event 3",1);
        Event e4 = new Event(4, 1, "23/01/2020", "25/01/2020",
                "Event 4", "Wow this is event 4",2);
        Event e5 = new Event(5, 2, "20/01/2020", "22/01/2020",
                "Event 5", "Wow this is event 5",4);
        Event e6 = new Event(6, 2, "21/01/2020", "22/01/2020",
                "Event 6", "Wow this is event 6",5);

        // Created events by user1
        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);
        // Created events by user2
        events.add(e5);
        events.add(e6);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public MutableLiveData<List<Event>> getEvents(int userId) {


        List<Event> userEvents = new ArrayList<Event>();
        for ( Event e : events){
            if (e.getUserId() == userId){
                userEvents.add(e);
            }
        }

        mutableLiveData.setValue(userEvents);
        return mutableLiveData;
    }

    @Override
    public MutableLiveData<List<Event>> getEvents() {
        mutableLiveData.setValue(events);
        return mutableLiveData;
    }
}