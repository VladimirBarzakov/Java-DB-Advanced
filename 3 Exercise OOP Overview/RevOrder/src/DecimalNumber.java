import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalNumber {
    private double num;

    public DecimalNumber(){};

    public double getNum() {
        return num;
    }

    public DecimalNumber(double n){
        this.num=n;
    }

    public String InReversedOrder(){
        NumberFormat formatter = new DecimalFormat("#.#######################");
        String str = formatter.format(this.num);
        StringBuilder builder = new StringBuilder();
        for (int i = str.length()-1; i >=0 ; i--) {
            builder.append(str.charAt(i));
        }
        return builder.toString();
    }
}
