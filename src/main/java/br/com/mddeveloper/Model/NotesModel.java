package br.com.mddeveloper.Model;

public class NotesModel {
    private int id;
    private String text;
    private String date;
    private String time;

    public NotesModel(int id, String text, String date, String time) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public NotesModel(String text, String date, String time) {
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Nota [id=" + id + ", texto='" + text + "', data=" + date + ", hora=" + time + "]";
    }

}

