package cat.udl.tidic.amd.dam_recyclerview.models;

import java.util.Date;

public class Event {

    private String id;
    private Date start;
    private Date end;
    private String tittle;
    private String description;

    public Event(String id, Date start, Date end, String tittle, String description) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.tittle = tittle;
        this.description = description;
    }

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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
}
