package info.androidhive.expensemanager.adapter;

/**
 * Created by USER on 25-06-2015.
 */
public class Debt {
    int id;
    int name;

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getLease() {
        return lease;
    }

    public void setLease(int lease) {
        this.lease = lease;
    }

    public int getAmnt() {
        return amnt;
    }

    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }

    public int getLeaseamnt() {
        return leaseamnt;
    }

    public void setLeaseamnt(int leaseamnt) {
        this.leaseamnt = leaseamnt;
    }

    int debt;
    int lease;
    int amnt;
    int leaseamnt;
}
