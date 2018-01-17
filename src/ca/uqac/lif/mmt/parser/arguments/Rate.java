package ca.uqac.lif.mmt.parser.arguments;

public class Rate {

    private float percentage;

    public Rate(float i){
        this.percentage = i;
    }

    public Rate(String s) {
        this(Float.parseFloat(s));
    }
}
