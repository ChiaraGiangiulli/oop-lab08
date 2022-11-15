package it.unibo.mvc;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    List<String> strings = new LinkedList<>();
    String next = null;

    @Override
    public void setNextString(String s) {
       this.next = Objects.requireNonNull(s, "null values not accepted");
    }

    @Override
    public String getNextString() {
        return next;
    }

    @Override
    public List<String> printedStrings() {
        return strings;
    }

    @Override
    public void print() {
        if(this.next == null){
            throw new IllegalStateException("no string to print");
        }
        System.out.println(next);
        strings.add(next);
        this.next = null;
    }

}
