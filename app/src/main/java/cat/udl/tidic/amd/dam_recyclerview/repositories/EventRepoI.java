package cat.udl.tidic.amd.dam_recyclerview.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import retrofit2.http.Query;

public interface EventRepoI {

   MutableLiveData<List<Event>> getEvents(@Query("userId") int userId);
   MutableLiveData<List<Event>> getEvents();

}
