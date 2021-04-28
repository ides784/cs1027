public class ReversibleArray <T> {
    private T[] array;
    private int count;

    public ReversibleArray(T[] tarray){
    this.array = tarray;
    count = array.length;
    }
    public String toString(){
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i] + ", ");
        if(i == array.length-2){
            System.out.print(array[array.length-1]);
            return "";
        }
        }

        return "";
    }
    public void reverse (){
        int j = 1;
        for (int i = 0; i < (array.length)/2; i++){
        T temp = array[i];
        array[i] = array[array.length - j];
        array[array.length - j] = temp;
        j++;
        }

    }

}
