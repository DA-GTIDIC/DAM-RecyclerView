package cat.udl.tidic.amd.dam_recyclerview.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import cat.udl.tidic.amd.dam_recyclerview.repositories.EventRepoI;
import cat.udl.tidic.amd.dam_recyclerview.repositories.EventRepoImpl;

public class EventViewModel extends AndroidViewModel {

    private EventRepoI repository;
    private LiveData<List<Event>> events;
    private final MutableLiveData<String> userId = new MutableLiveData<>();


    public EventViewModel(@NonNull Application application) {
        super(application);
        repository = new EventRepoImpl(application);
        events  = Transformations.switchMap(userId, new Function<String, LiveData<List<Event>>>() {
                    @Override
                    public LiveData<List<Event>> apply(String id) {
                        if (id == null || id.equals("")) {
                            events = repository.getEvents();
                        }else{
                            events = repository.getEvents(Integer.parseInt(id));
                        }
                        return events;
                    }
                });
    }


    public LiveData<List<Event>> getEvents(){
        return events;
    }

    public void setUserId(String id) {
        userId.setValue(id);
    }


    public void insert(Event e){
        this.repository.insert(e);
    }

    public void update(Event e){
        this.repository.update(e);
    }

    public void removeEvent(Event event){
        this.repository.delete(event);
    }
}
