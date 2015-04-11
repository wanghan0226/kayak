package beans;

/**
 * Created by pianobean on 4/9/15.
 */
public class SortingBean {

    private int totalTime;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return getTotalPrice()+":"+totalTime;
    }
}
