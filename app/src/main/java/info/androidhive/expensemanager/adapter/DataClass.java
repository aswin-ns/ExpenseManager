package info.androidhive.expensemanager.adapter;

/**
 * Created by USER on 02-06-2015.
 */
public class DataClass {
    private int id;
    private String type;
    private int amnt;
    private int neg_amnt;
    private String note;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmnt() {
        return amnt;
    }

    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }

    public int getNeg_amnt() {
        return neg_amnt;
    }

    public void setNeg_amnt(int neg_amnt) {
        this.neg_amnt = neg_amnt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public DataClass()
    {

    }
    public DataClass (int amnt)
    {
        this.amnt=amnt;
    }
    public DataClass(int id,String type,int amnt,int neg_amnt,String note,String date)
    {
        this.id = id;
        this.type = type;
        this.amnt = amnt;
        this.neg_amnt = neg_amnt;
        this.note = note;
        this.date = date;

    }
    public DataClass(int id,String type,int amnt,String note,String date)
    {
        this.id = id;
        this.type = type;
        this.amnt = amnt;
        this.note = note;
        this.date = date;

    }
    public DataClass(String type,int amnt,String note,String date)
    {

        this.type = type;
        this.amnt = amnt;
        this.note = note;
        this.date = date;

    }
    public DataClass(int id,String type,String note,int neg_amnt,String date)
    {
        this.id = id;
        this.type = type;
        this.note = note;
        this.neg_amnt = neg_amnt;

        this.date = date;

    }

}
