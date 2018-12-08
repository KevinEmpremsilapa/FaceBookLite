import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;

//import sun.misc.BASE64Encoder;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import static java.lang.System.out;

public class Encryption {

    private String strCipher = new String();
    private String strDecryptedText = new String();
    byte[] byteCipher;
    private Cipher cipher;
    private SecretKey secretKey;

    //ENCRYPTS A STRING AND RETURNS ENCRYPTED STRING
    public String encrypt(String data)
    {

        try{

            generateKey();//generates our secret key

            //PrintWriter writer = new PrintWriter("key.txt", "UTF-8");
            //writer.println(secretKey);
            //writer.close();

            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            //sets cipher into encrypt mode
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);

            //converts string into bytes
            byte[] byteDataToEncrypt = data.getBytes();



            //=================encrypts our byte data================
            byteCipher = cipher.doFinal(byteDataToEncrypt);
            strCipher = Base64.getEncoder().encodeToString(byteCipher);
            out.println("encrypted text is " + strCipher);


        }


       /* exceptions for writing to file
        catch(FileNotFoundException print)
        {
            out.println("ERROR FILE NOT FOUND");
        }
        catch(UnsupportedEncodingException e)
        {
            out.println("ENCODING NOT SUPPORTED");
        }

        */
        catch (NoSuchAlgorithmException noSuchAlgo)
        {
            out.println(" No Such Algorithm exists " + noSuchAlgo);
        }

        catch (NoSuchPaddingException noSuchPad)
        {
            out.println(" No Such Padding exists " + noSuchPad);
        }

        catch (InvalidKeyException invalidKey)
        {
            out.println(" Invalid Key " + invalidKey);
        }

        catch (BadPaddingException badPadding)
        {
            out.println(" Bad Padding " + badPadding);
        }

        catch (IllegalBlockSizeException illegalBlockSize)
        {
            out.println(" Illegal Block Size " + illegalBlockSize);
        }

        return strCipher;
    }

    private void generateKey(){

        try
        {
            //===================generates our secret key===============//
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            secretKey = keyGen.generateKey();
            //==========================================================//
        }
        catch (NoSuchAlgorithmException noSuchAlgo)
        {
            out.println(" No Such Algorithm exists " + noSuchAlgo);
        }

    }

    public void decrypt(String data, SecretKey key)
    {
        try
        {
            cipher.init(Cipher.DECRYPT_MODE,key,cipher.getParameters());
            byte[] byteDecryptedText = cipher.doFinal(byteCipher);
            strDecryptedText = new String(byteDecryptedText);
            out.println(" Decrypted Text message is " + strDecryptedText);

        }

        catch (InvalidAlgorithmParameterException invalidParam)
        {
            out.println(" Invalid Parameter " + invalidParam);
        }
        catch (IllegalBlockSizeException illegalBlockSize)
        {
            out.println(" Illegal Block Size " + illegalBlockSize);
        }
        catch (BadPaddingException badPadding)
        {
            out.println(" Bad Padding " + badPadding);
        }
        catch (InvalidKeyException invalidKey)
        {
            out.println(" Invalid Key " + invalidKey);
        }
    }

    public String getKey()
    {
        String keyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return keyString;
    }

    //rebuilds our secretKey from string
    public SecretKey convertKey(String key)
    {
        byte[] rebuiltKey = Base64.getDecoder().decode(key);
        SecretKey skey = new SecretKeySpec(rebuiltKey, 0, rebuiltKey.length, "DES");
        return skey;
    }

}