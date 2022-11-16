package ru.dest.library.obj;

public class Entry<T, A> {

    private T firstVal;
    private A secondVal;

    public Entry(T firstVal, A secondVal) {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
    }

    public T getFirstVal() {
        return firstVal;
    }

    public A getSecondVal() {
        return secondVal;
    }

    public void setFirstVal(T firstVal) {
        this.firstVal = firstVal;
    }

    public void setSecondVal(A secondVal) {
        this.secondVal = secondVal;
    }
}
