package info.vertecs.expensemanager.adapter;

/**
 * Created by USER on 20-06-2015.
 */
public class SumByClass {
    private String group_name;

    public String getNegative_groupname() {
        return negative_groupname;
    }

    public void setNegative_groupname(String negative_groupname) {
        this.negative_groupname = negative_groupname;
    }

    private String negative_groupname;
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
    public SumByClass(int amnt,String negative_groupname)
    {
        this.amnt = amnt;
        this.negative_groupname = negative_groupname;
    }

    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }
}



