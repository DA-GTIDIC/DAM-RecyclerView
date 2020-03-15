package cat.udl.tidic.amd.dam_recyclerview.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.dao.EventDAOI;
import cat.udl.tidic.amd.dam_recyclerview.database.EventDatabase;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;


public class EventRepoImpl implements EventRepoI {

    private EventDAOI eventDAO;
    private LiveData<List<Event>> allEvents;

    public EventRepoImpl(Application application){
        EventDatabase db = EventDatabase.getInstance(application);
        eventDAO = db.eventDAO();
        allEvents = eventDAO.getAllEvents();
    }


    @Override
    public void insert(Event event) {
        eventDAO.insert(event);
    }

    @Override
    public Event get(int eventId) {
        return null;
    }

    @Override
    public void update(Event event) {
        eventDAO.update(event);
    }

    @Override
    public void delete(Event e) {
        new DeleteEventAsyncTask(eventDAO).execute(e);
    }

    @Override
    public LiveData<List<Event>> getEvents(int userId) {

        return allEvents;
    }

    @Override
    public LiveData<List<Event>> getEvents() {

        return allEvents;
    }

    @Override
    public void deleteAll() {

    }

    private static class DeleteEventAsyncTask extends AsyncTask<Event, Void, Void> {

        private EventDAOI eventDAO;

        private DeleteEventAsyncTask(EventDAOI eventDAO) {
            this.eventDAO = eventDAO;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDAO.delete(events[0]);
            return null;
        }
    }

}
