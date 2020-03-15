package cat.udl.tidic.amd.dam_recyclerview.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "event_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private String start;
    private String end;
    private String tittle;
    private String description;
    private int avaluation;


    public Event(int userId, String start, String end, String tittle, String description,
    int avaluation) {
        this.userId = userId;
        this.start = start;
        this.end = end;
        this.tittle = tittle;
        this.description = description;
        this.avaluation = avaluation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvaluation() {
        return avaluation;
    }

    public void setAvaluation(int avaluation) {
        this.avaluation = avaluation;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Event)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Event e = (Event) o;

        // Compare the data members and return accordingly
        return this.id == e.getId()
                && this.userId == e.getUserId()
                && this.start.equals(e.getStart())
                && this.end.equals(e.getEnd())
                && this.avaluation == e. getAvaluation()
                && this.tittle.equals(e.getTittle())
                && this.description.equals(e.getDescription());

    }

}
