import java.io.*;
/**
 * A class that encodes and decodes a text.
 * @author Derek Liu
 */
public class WesternCipher {
    private CircularArrayQueue<Character> encodingQueue;
    private CircularArrayQueue<Character> decodingQueue;
    public WesternCipher() {
        encodingQueue = new CircularArrayQueue<Character>(10);
        decodingQueue = new CircularArrayQueue<Character>(10);
    }

    public WesternCipher(int integer) {
        encodingQueue = new CircularArrayQueue<Character>(integer);
        decodingQueue = new CircularArrayQueue<Character>(integer);
    }

    /**
     * A helper method that changes letters to numbers
     * @param input the text to be changed
     * @param t if previous letter was converted, use an alternate table of numbers
     *
     */
    private char enumerate(char input, int t) {
        boolean charfound = false;
        char output = 0;

        char[] n1 = {'1','2','3','4','5','6'}; //first set of numbers
        char[] n2 = {'3','4','5','6','1','2'}; //second set, if previous value was number
        char[] c = {'A','E','I','O','U','Y'};

        for (int i=0;i<c.length;i++) { // go through c and see if match is made, and then change to value in n1 and n2
            if (charfound==false) {
                if (t==1 && input==c[i]) {
                    charfound = true;
                    output = n1[i];
                }
                if (t==2 &&input==c[i]) {
                    charfound = true;
                    output = n2[i];
                }
            }
        }
        return output;
    }

    /**
     * A helper method that changes numbers to letters
     * @param input the text to be changed
     * @param t if previous letter was converted, use an alternate table of numbers
     *
     */
    private char denumerate(char input, int t) {
        boolean charfound = false;
        char output = 0;

        char[] n1 = {'1','2','3','4','5','6'};//first set of numbers

        char[] n2 = {'3','4','5','6','1','2'};//second set, if previous value was number
        char[] c = {'A','E','I','O','U','Y'};

        for (int i=0;i<c.length;i++) { // go through n1 or n2 and see if match is made, and then change to value in c
            if (charfound==false) {
                if (t==1 && input==n1[i]) {
                    charfound = true;
                    output = c[i];
                }
                if (t==2 &&input==n2[i]) {
                    charfound = true;
                    output = c[i];
                }
            }
        }
        return output;
    }

    /**
     * A method that encodes a block of text
     * @param input the text to be encoded
     *
     */
    public String encode(String input) {
        String encoded = "";
        char[] message = input.toCharArray();
        for (int j = 0; j < message.length; j++) { //takes input into a array of chars
            encodingQueue.enqueue(message[j]);
        }
        char lastvalue =0;
        boolean islastvaluenum= false;
        for(int i=0; i<message.length;i++) {
            char character = encodingQueue.dequeue();
            if(character != ' ') { //if not a space
                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                if(islastvaluenum ==false) { //if last value was not a num, then proceed normally with first set of numbers
                char encodedvalue=enumerate(character,1);
                if(encodedvalue !=0) {
                    islastvaluenum = true;
                    lastvalue = encodedvalue;
                    encoded = encoded +encodedvalue;
                }
                else {
                    islastvaluenum = false;
                    int shiftvalue = 5+(2*i);
                    boolean charfound = false;
                    char[] chararray = (alphabet.toUpperCase()).toCharArray();
                    char output = 'n';
                    for (int j=0;j<chararray.length;j++) { // look through the alphabet and see if character matches, then shift accordingly to shiftby
                        if (charfound==false) {
                            if (character == chararray[j]) {
                                output = chararray[(j+chararray.length+shiftvalue)% chararray.length];
                                charfound = true;
                            }
                        }
                    }
                    character = output;
                    lastvalue = character;
                    encoded = encoded+ character;
                }
            }
            else {
                char encodedvalue=enumerate(character,2);//if last value was a num, then proceed with second set of numbers
                if(encodedvalue !=0) {
                    islastvaluenum = true;
                    lastvalue = encodedvalue;
                    encoded = encoded +encodedvalue;
                }
                else {
                    islastvaluenum = false;
                    int shiftvalue = (5+(2*i))+((lastvalue-'0')*(-2));
                    boolean charfound = false;
                    char[] chararray = (alphabet.toUpperCase()).toCharArray();
                    char output = 'n';
                    for (int j=0;j<chararray.length;j++) {// look through the alphabet and see if character matches, then shift accordingly to shiftby
                        if (charfound==false) {
                            if (character == chararray[j]) {
                                output = chararray[(j+chararray.length+shiftvalue)% chararray.length];
                                charfound = true;
                            }
                        }
                    }
                    character = output;
                    lastvalue = character;
                    encoded = encoded+ character;
                }
            }

        }
        else{
            encoded = encoded +  ' ';
            }
        }
        return encoded;
    }
    /**
     * A method that decodes a block of text
     * @param input the text to be decoded
     *
     */
    public String decode(String input) {
        String decoded = "";
        char[] message = input.toCharArray();
        for (int j = 0; j < message.length; j++) { //takes input into a array of chars
            decodingQueue.enqueue(message[j]);
        }
        char lastvalue =0;
        boolean islastvaluenum= false;
        for (int i = 0; i < input.length(); i++) {
            char character = decodingQueue.dequeue();
            if(character != ' ') { //if not a space
                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                if (islastvaluenum==false) { //if last value was not a num, then proceed normally with first set of numbers
                char decodedvalue = denumerate(character, 1);
                if (decodedvalue != 0) {
                    islastvaluenum = true;
                    decoded = decoded + decodedvalue;
                }
                else {
                    islastvaluenum = false;
                    int shiftvalue = -(5+(2*i));
                    boolean charfound = false;
                    char[] chararray = (alphabet.toUpperCase()).toCharArray();
                    char output = 'n';
                    for (int j=0;j<chararray.length;j++) {// look through the alphabet and see if character matches, then shift accordingly to shiftby
                        if (charfound==false) {
                            if (character == chararray[j]) {
                                output = chararray[(j+chararray.length+shiftvalue)% chararray.length];
                                charfound = true;
                            }
                        }
                    }
                    character = output;
                    decoded = decoded + character;
                }
            }
            else {
                char decodedvalue = denumerate(character, 2); //if last value was a num, then proceed with second set of numbers
                if (decodedvalue != 0) {
                    islastvaluenum = true;
                    decoded = decoded+ decodedvalue;
                }
                else {
                    islastvaluenum = false;
                    int shiftvalue1 = -5;
                    boolean charfound = false;
                    char[] chararray = (alphabet.toUpperCase()).toCharArray();
                    char output = 'n';
                    for (int j=0;j<chararray.length;j++) {// look through the alphabet and see if character matches, then shift accordingly to shiftby
                        if (charfound==false) {
                            if (character == chararray[j]) {
                                output = chararray[(j+chararray.length+shiftvalue1)% chararray.length];
                                charfound = true;
                            }
                        }
                    }
                    character = output;
                    int shiftvalue2 = (2 * (lastvalue - '0'))-(i * 2);
                    charfound = false;
                    chararray = (alphabet.toUpperCase()).toCharArray();
                    output = 'n';
                    for (int j=0;j<chararray.length;j++) {// look through the alphabet and see if character matches, then shift accordingly to shiftby
                        if (charfound==false) {
                            if (character == chararray[j]) {
                                output = chararray[(j+chararray.length+shiftvalue2)% chararray.length];
                                charfound = true;
                            }
                        }
                    }
                    character = output;

                    decoded = decoded + character;
                }
            }
            lastvalue = character;
            }
            else{
                decoded = decoded +  ' ';
            }
        }
        return decoded;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        WesternCipher westerncipher = new WesternCipher();
        System.out.println("Would you like to encode or decode?"); //asks first time for decoding
        String string = bufferedReader.readLine();
        String s = string.toLowerCase();
        if (s.equals("encode")) {
            System.out.println("Enter regular text: "); //if input is encode, program encodes, and vice versa
            String input = bufferedReader.readLine();
            System.out.println("Encoded text: ");
            System.out.println(westerncipher.encode(input));
        }
        else if (s.equals("decode")) {
            System.out.println("Enter ciphertext: ");
            String input = bufferedReader.readLine();
            System.out.println("Regular text: ");
            System.out.println(westerncipher.decode(input));
        }
        System.out.println("Would you like to enter another text? print yes or no"); //asks second time for decoding
        string = bufferedReader.readLine();
        s = string.toLowerCase();
        if (s.equals("yes")){
            System.out.println("Would you like to encode or decode?");
            string = bufferedReader.readLine();
            s = string.toLowerCase();

            if (s.equals("encode")) {
                System.out.println("Enter regular text: ");
                String input = bufferedReader.readLine();
                System.out.println("Encoded text: ");
                System.out.println(westerncipher.encode(input));
            }
            else if (s.equals("decode")) {
                System.out.println("Enter ciphertext: ");
                String input = bufferedReader.readLine();
                System.out.println("Regular text: ");
                System.out.println(westerncipher.decode(input));
            }
        }
        else {
            System.out.println("Goodbye");
            System.exit(0);
        }

    }
}