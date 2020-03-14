package cat.udl.tidic.amd.dam_recyclerview.dao;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IEventDAO {

    @GET("user/events")
    MutableLiveData<List<Event>> getEvents(@Query("userId") int userId);

    @GET("events")
    MutableLiveData<List<Event>> getEvents();

}
