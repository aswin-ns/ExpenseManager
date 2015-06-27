package info.vertecs.expensemanager.adapter;

public class Debt
{
    public Debt()
    {

    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmnt() {
        return amnt;
    }

    public String getType() {
        return type;
    }

    int id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Debt(int id, String name, int amnt, String type) {
        this.id = id;
        this.name = name;
        this.amnt = amnt;
        this.type = type;
    }

    public Debt(String name, int amnt, String type) {
        this.name = name;
        this.amnt = amnt;
        this.type = type;
    }

    String name;
    int amnt;
    String type;


}