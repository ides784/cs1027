public class Abo {
    public static int rabo( int n){
        if (n==1){
            return 1;
        }
        else if (n<=0){
            return 0;
        }
        else{
            if (n%2==0){
                int x = rabo (n/2);
                return x+1;
            }
            else {
                int y = rabo((n+1)/2);
                return y+2;
            }
        }
    }




    public static int iabo(int n){
        int y = n;
        int x = 0;
        int random = -1;
        while (random ==-1){
            if (y ==1){
                x=x+1;
                break;
            }
            else if(y <=0){
                break;
            }
            else{
                if (y %2==0){
                    y = (y+1)/2;
                    x=x+1;

                }
                else{
                    y = (y+1)/2;
                    x=x+2;

                }
            }
        }
        return x;
    }







    


    public static void main (String[] args){
        for (int i=0; i<20;i++){
            System.out.println(rabo(i));
        }
        System.out.println("\n");
        for (int i=0; i<20;i++){
            System.out.println(iabo(i));
        }
    }

}
