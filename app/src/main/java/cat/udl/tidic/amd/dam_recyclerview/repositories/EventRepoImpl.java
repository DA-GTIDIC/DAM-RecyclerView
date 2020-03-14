package cat.udl.tidic.amd.dam_recyclerview.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.dao.EventDAOStubImpl;
import cat.udl.tidic.amd.dam_recyclerview.dao.IEventDAO;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;


public class EventRepoImpl implements EventRepoI {

    private IEventDAO eventDAO = new EventDAOStubImpl();


    @Override
    public MutableLiveData<List<Event>> getEvents(int userId) {
        return eventDAO.getEvents(userId);
    }

    @Override
    public MutableLiveData<List<Event>> getEvents() {
        return eventDAO.getEvents();
    }
}
