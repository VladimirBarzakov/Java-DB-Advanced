public class Number {
    private long number;

    public Number(){

    }

    public Number(long num){
        this.number=num;
    }

    public long getNumber() {
        return number;
    }

    public String LastDigitName (){
        long num = this.getNumber()%10;
        switch (Long.toString(num)){
            case "0": return "zero";
            case "1": return "one";
            case "2": return "two";
            case "3": return "three";
            case "4": return "four";
            case "5": return "five";
            case "6": return "six";
            case "7": return "seven";
            case "8": return "eight";
            case "9": return "nine";
            default: return "";
        }
    }
}
