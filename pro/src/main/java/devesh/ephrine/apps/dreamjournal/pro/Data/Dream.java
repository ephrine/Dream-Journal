package devesh.ephrine.apps.dreamjournal.pro.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Dream {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "dream_date")
    private String date;

    @ColumnInfo(name = "dream_title")
    private String title;

    @ColumnInfo(name = "dream")
    private String dream;

    //Constructors
    public Dream(String date, String title, String dream) {
        this.date = date;
        this.title = title;
        this.dream = dream;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }
}
