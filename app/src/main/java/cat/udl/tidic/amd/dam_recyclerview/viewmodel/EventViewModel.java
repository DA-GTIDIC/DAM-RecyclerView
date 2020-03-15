package cat.udl.tidic.amd.dam_recyclerview.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import cat.udl.tidic.amd.dam_recyclerview.repositories.EventRepoI;
import cat.udl.tidic.amd.dam_recyclerview.repositories.EventRepoImpl;

public class EventViewModel extends AndroidViewModel {

    private EventRepoI repository;
    private LiveData<List<Event>> events;

    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepoImpl(application);
        events = repository.getEvents();
    }

    public void getEvents(int userId) {
        this.events = this.repository.getEvents(userId);
    }

    public void getAllEvents() {
        this.events = this.repository.getEvents();
    }

    public LiveData<List<Event>> getEvents() {
        return this.events;
    }

    public void insert(){
        this.repository.insert(null);
    }

    public void removeEvent(Event event){
        this.repository.delete(event);
    }
}
