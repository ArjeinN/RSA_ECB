/**
* The RSA Class, the most of the algorithms are implemented here!
*
* @author  Mert Arcan
* @version 1.0
* @since   2022-05-09
*/
import java.math.BigInteger;

public class RSA {


    public RSA(){

    }


    public  String Encrypt(String plaintext, KeyPair Keys){
        String Ciphertext = "";
        String asciiPlainText = StringToASCII(plaintext);
        String[] plainBlocks = ASCIItoBlocks(asciiPlainText);
        String[] cipherBlocks = new String[plainBlocks.length];
        BigInteger result;
        for(int i = 0; i< plainBlocks.length; i++){
        
            if(i == plainBlocks.length-1 && plainBlocks.length > 1){
                // do ciphertext stealing
            String head = cipherBlocks[i-1].substring(0,cipherBlocks[i-1].length()-1);
            String tail = cipherBlocks[i-1].substring(cipherBlocks[i-1].length()-1,cipherBlocks[i-1].length());
            String Dn = plainBlocks[i]+ tail;
            result = new BigInteger(Dn);
            cipherBlocks[i-1] = result.modPow(Keys.Public_Key[0], Keys.Public_Key[1]).toString(); 
            cipherBlocks[i] = head;
            }

            else{
            result = new BigInteger(plainBlocks[i]);
            cipherBlocks[i] = result.modPow(Keys.Public_Key[0], Keys.Public_Key[1]).toString(); 
            }


        }
        for(int i = 0;i< cipherBlocks.length;i++){
            Ciphertext += cipherBlocks[i];
            // I put a delimeter(Only way.. comes to my mind) between blocks to seperate them since i cant hold the index values of these blocks.
            if(i != cipherBlocks.length-1){
            Ciphertext += '|';
            }
        }
        return Ciphertext;
    }

    public  String Decrypt(String ciphertext, KeyPair Keys){
        String plaintext = "";
        int blocks = 0;
        for(int i = 0;i< ciphertext.length();i++){
            if(ciphertext.charAt(i) == '|'){
                blocks++;
            }
        }
        String[] cipherBlocks = new String[blocks+1];
        int ct = 0;
        for(int i = 0;i< cipherBlocks.length;i++){
            cipherBlocks[i] = "";
        }
        for(int i = 0 ;i< ciphertext.length();i++){
            if(ciphertext.charAt(i) == '|'){
                ct++;
            }
            else{
                cipherBlocks[ct] += ciphertext.charAt(i);
            }
        }
        
        String[] plainBlocks = new String[cipherBlocks.length];
        
        BigInteger result ;
        for(int i = 0;i< cipherBlocks.length; i++){

            if(i == cipherBlocks.length-1 && cipherBlocks.length > 1){
                String Dn = plainBlocks[i-1];
                String head = Dn.substring(0,Dn.length()-1);
                String tail = Dn.substring(Dn.length()-1, Dn.length());
                String En = cipherBlocks[i] +tail;
                plainBlocks[i] = head;

                result = new BigInteger(En);
                plainBlocks[i-1] = result.modPow(Keys.Private_Key[0], Keys.Private_Key[1]).toString(); 

            }
            
            else{
            result = new BigInteger(cipherBlocks[i]);
            plainBlocks[i] = result.modPow(Keys.Private_Key[0], Keys.Private_Key[1]).toString(); 
            }
        }
        
        for(int i = 0 ;i< plainBlocks.length-1;i++){
            if(plainBlocks[i].length() < 2){
                while(plainBlocks[i].length()<2 )
                plainBlocks[i] = "0" + plainBlocks[i];
            }
        }
        if(plainBlocks[plainBlocks.length-1] == ""){
            plainBlocks[plainBlocks.length-1] = "0";
        }
        
        
        

        for(int i = 0 ;i< plainBlocks.length;i++){
            plaintext += plainBlocks[i];
        }

        plaintext = asciiToString(plaintext);

        return plaintext;
    }


    public  String[] ASCIItoBlocks(String ascii){
        String blocks[];
        if(ascii.length() % 2 == 0){
        blocks = new String[ascii.length()/2];
        }
        else{
        blocks = new String[ascii.length()/2 +1];
        }
        for(int i = 0;i< blocks.length;i++){
            blocks[i] = "";
        }
        int ct = 0;
        for(int i = 0; i< ascii.length(); i++){
            if(i != 0 &&i % 2 == 0){
                ct++;
            }
            blocks[ct] += ascii.charAt(i);
            
        }

        return blocks;
    }

    public  void printBlocks(String[] blocks){
        for(int i = 0; i< blocks.length; i++){
            System.out.println("Block["+i+"]: "+blocks[i]);
        }
    }


    // Bu iki fonksiyon taşşşşşşş
    public  String StringToASCII(String plaintext){
        String binplaintext = "";
        for(int i = 0; i < plaintext.length() ; i++){
            binplaintext += (int)plaintext.charAt(i)+100;
        }

        return binplaintext;
    }

    public  String asciiToString(String plaintext){
        String text = "";
        int j = 3;
        for(int i = 0 ;i<= plaintext.length()-3;i+=3){
            text += (char)(Integer.parseInt(plaintext.substring(i, j)) -100);
            j +=3;
        }

        return text;


    }

}
