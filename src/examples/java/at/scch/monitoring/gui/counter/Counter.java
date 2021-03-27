package at.scch.monitoring.gui.counter;

public class Counter{
    private int count = 0;
    public String getCount() {
        return String.valueOf(count);
    }
    public void inc(){
        count++;
    }
}