/** 
* @author  Mert Arcan
* @version 1.0
* @since   2022-05-09
*/

import java.math.BigInteger;

public class KeyPair {
    String Name;
    BigInteger[] Public_Key = new BigInteger[2];
    BigInteger[] Private_Key = new BigInteger[2];
    BigInteger Public_Key_all;
    BigInteger Private_Key_all;
    
    public KeyPair(String name,BigInteger[] publickey,BigInteger[] privatekey){
        this.Name = name;
        this.Public_Key[0] = publickey[0];
        this.Public_Key[1] = publickey[1];
        this.Private_Key[0] = privatekey[0];
        this.Private_Key[1] = privatekey[1];
        this.Public_Key_all = new BigInteger(this.Public_Key[0].toString()+this.Public_Key[1].toString());
        this.Private_Key_all = new BigInteger(this.Private_Key[0].toString()+this.Private_Key[1].toString());
    }
    public KeyPair(){
        
    }

    public void printPublicKey(){
        //System.out.println("Public_Key[0]: "+ Public_Key[0].toString());
        //System.out.println("Public_Key[1]: "+ Public_Key[1].toString());
        System.out.println("Public_Key: "+ Public_Key_all.toString());
        System.out.println("Public_Key BitLength: "+Public_Key_all.bitLength());
    }

    public void printPrivateKey(){
        //System.out.println("Private_Key[0]: "+ Private_Key[0].toString());
        //System.out.println("Private_Key[1]: "+ Private_Key[1].toString());
        System.out.println("Private_Key:"+ Private_Key_all.toString());
        System.out.println("Private_Key.Length: "+Private_Key_all.bitLength());
    }


    @Override 
    public String toString(){
        return Name;
    }
    

}
