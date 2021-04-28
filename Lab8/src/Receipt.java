public class Receipt {
    public Receipt(Product[] cart){
        double finalcost = 0;
        System.out.printf("%11s%11s%11s%16s\n","item","code","costs","after tax");

        System.out.println("------------------------------------------------------");

        for(int i=0; i< cart.length; i++){
            double afterTax=cart[i].getCost()+cart[i].getCost()* Product.getTax();
            System.out.printf("%11s%11s%11.2f%16.2f\n",cart[i].getName(),cart[i].getCode(),cart[i].getCost(),afterTax);
            finalcost+=afterTax;
        }
        System.out.println("------------------------------------------------------");

        System.out.printf("%49.2f\n",finalcost);
    }
    public static void main(String[] args) {

        Product[] objects = new Product[3];
        objects[0]= new Product("hot dogs","hd 1234",1.99);
        objects[1]= new Product("buns","bu 1234",2.99);
        objects[2]= new Product("coke","co 1234",3.99);
        Receipt myreceipt = new Receipt(objects);
    }
}
