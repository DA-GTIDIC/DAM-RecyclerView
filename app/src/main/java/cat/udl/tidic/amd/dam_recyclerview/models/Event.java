package cat.udl.tidic.amd.dam_recyclerview.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import cat.udl.tidic.amd.dam_recyclerview.database.DateConverter;


@Entity(tableName = "event_table")
@TypeConverters(DateConverter.class)
public class Event {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("start")
    private Date start;
    @SerializedName("end")
    private Date end;
    @SerializedName("tittle")
    private String tittle;
    @SerializedName("description")
    private String description;
    @SerializedName("avaluation")
    private float avaluation;


    public Event(int userId, Date start, Date end, String tittle, String description,
    float avaluation) {
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


    public int getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }


    public Date getEnd() {
        return end;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTittle() {
        return tittle;
    }


    public String getDescription() {
        return description;
    }


    public float getAvaluation() {
        return avaluation;
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
