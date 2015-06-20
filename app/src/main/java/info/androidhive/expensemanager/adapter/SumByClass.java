package info.androidhive.expensemanager.adapter;

/**
 * Created by USER on 20-06-2015.
 */
public class SumByClass {
    private String group_name;
    private int amnt;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getAmnt() {
        return amnt;
    }
    public SumByClass()
    {
        //Empty Constructor//
    }
    public SumByClass(String group_name,int amnt)
    {
        this.group_name = group_name;
        this.amnt = amnt;
    }

    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }
}

