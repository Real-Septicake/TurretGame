public class Cash extends Counter {
    private static int cash;
    public Cash(int c){
        cash = c;
    }
    public void act(){
        updateImage("Cash: $", String.valueOf(cash));
    }
    public static int getCash(){
        return cash;
    }
    public static void alterCash(int v){
        cash += v;
    }
}
