/** 
* @author  Mert Arcan
* @version 1.0
* @since   2022-05-09
*/

import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator{
        boolean checkP;
        boolean checkQ;
        BigInteger p,q;
        BigInteger e,n,d; 
        BigInteger[] PU = new BigInteger[2];
        BigInteger[] PR = new BigInteger[2];
        KeyPair keyPair;
        int pairCounter = 0;


        
        public KeyPair GeneratePair(){
            
        Random rnd = new Random();
        
        BigInteger one = new BigInteger("1");
        do{
        p = new BigInteger(1024, 1, rnd); 
        q = new BigInteger(1024, 1, rnd);
        
        //Check primes
        checkP = check(p);
        checkQ = check(q);
        
        }while(checkP != true && checkQ != true);
        n = p.multiply(q);
        PU[1] = n;
        PR[1] = n;
        BigInteger Phi = (p.subtract(one).multiply(q.subtract(one)));
        
        while(true){
        e = new BigInteger(Phi.bitLength(),rnd);
        
        if(e.compareTo(Phi) < 0 && Phi.gcd(e).compareTo(one) == 0){
            break;
        }
        
        }
        d = e.modInverse(Phi);
        PU[0] = e;
        PR[0] = d;
        String name = "Key Pair("+this.pairCounter+")";
        this.pairCounter++;
        keyPair = new KeyPair(name,PU,PR);
        return keyPair;
        }

        
    public boolean check(BigInteger p){
        BigInteger min = new BigInteger("1");
        BigInteger lim = p.subtract(min);
        int len = p.bitLength();
        Random rnd = new Random();
        for(int i = 0; i<20; i++){
        BigInteger a = new BigInteger(len, rnd);
        if (a.compareTo(min) < 0){
        a = a.add(min);
        }
        if (a.compareTo(lim) >= 0){
        a = a.mod(lim).add(min);
        }
        //System.out.println("a: "+a);
        BigInteger result = a.modPow(p.subtract(min), p);
        //System.out.println("result:"+result);
        if(result.compareTo(min) !=  0){
            return false;
        }
        }
        return true;

    }

}