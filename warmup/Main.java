package warmup;

public class Main {

    public static void main(String[] args) {
        int sum1 =0;
        for(int i = 1; i<1000;i++){
            if(i%3==0 || i % 5 ==0) sum1+=i;
        }
        System.out.println(sum1);

        int sum2 = 0;
        int a = 1;
        int b = 2;
        for (int j = 0;a<=4000000;j++){
            if (a%2==0){
                sum2 = sum2 + a;
            }
            int c = a+b;
              a = b;
              b = c;
        }
        System.out.println(sum2);
    }

}
