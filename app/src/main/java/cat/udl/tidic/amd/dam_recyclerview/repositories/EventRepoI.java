package cat.udl.tidic.amd.dam_recyclerview.repositories;

import androidx.lifecycle.LiveData;
import java.util.List;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;


public interface EventRepoI {

   // Create a new event
   void insert(Event event);

   // Read an event by id
   Event get(int eventId);

   // Update an existing event
   void update (Event event);

   // Delete an event using the id
   void delete(Event e);

   // Get all the events create by a user
   LiveData<List<Event>> getEvents(int userId);

   // Get all the events create by a all users
   LiveData<List<Event>> getEvents();

   // Remove all events
   void deleteAll();

}
