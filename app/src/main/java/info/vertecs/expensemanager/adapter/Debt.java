package info.vertecs.expensemanager.adapter;

public class Debt
{

    public Debt(int id, String name, String type, int leasedamnt, int borrowedamnt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.leasedamnt = leasedamnt;
        this.borrowedamnt = borrowedamnt;
    }

    int id;

    public Debt(String name, String type, int borrowedamnt) {
        this.name = name;
        this.type = type;
        this.borrowedamnt = borrowedamnt;
    }
public Debt(String name, int leasedamnt,String type)
{
    this.name = name;
    this.leasedamnt = leasedamnt;
    this.type = type;
}
    String name;
    String type;

    int leasedamnt;
    int borrowedamnt;
    //TODO EMPTY CONSTRUCTOR//

 public Debt()
 {

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBorrowedamnt() {
        return borrowedamnt;
    }

    public void setBorrowedamnt(int borrowedamnt) {
        this.borrowedamnt = borrowedamnt;
    }

    public int getLeasedamnt() {
        return leasedamnt;
    }

    public void setLeasedamnt(int leasedamnt) {
        this.leasedamnt = leasedamnt;
    }


}