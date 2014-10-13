/**
 * La classe Pescepalla semplifica l'uso dell'algoritmo Blowfish
 * Sono consentiti tutti i tipi di caratteri e qualsiasi lunghezza sia di chiave che di informazione da criptare.
 * 
 * @author Antonio Bianco
 * @version 1.0 stable
 */
import gnu.crypto.cipher.Blowfish;
import gnu.crypto.util.Base64;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;
public class Pescepalla
{
    private static final int BLOCK_SIZE = 8;
    private static final int KEY_BLOCK_SIZE = 8;
    
    /**
     * Cripta l'informazione con una chiave tramite l'algoritmo Blowfish
     * 
     * La stringa viene convertita in esadecimale per transcodificare ogni carattere,
     * anche speciale, nel rispettivo codice ascii.
     * Il risultato viene suddiviso in blocchi da 8 bit
     * e memorizzato in un array di byte codificato in UTF8.
     * Viene criptato con una password tramite l'algoritmo Blowfish. Anche qui è possibile usare i caratteri speciali.
     * Infine viene codificato ulteriormente in Base64 per permettere di:
     * - copiarlo come testo;
     * - aggiungere un altro layer di codifica.
     */
    public static String cripta(String cookieValue, String key) throws InvalidKeyException, UnsupportedEncodingException
    {
        byte[] plainText;
        byte[] encryptedText;
        Blowfish blowfish = new Blowfish();
        
        // Nonostante la precisione scende da 16 a 8 bit, si possono usare anche i caratteri speciali
        // Prepara la chiave
        byte[] keyBytes = Conversioni.stringToBase(key, 16).getBytes();
        Object keyObject = blowfish.makeKey(keyBytes, KEY_BLOCK_SIZE);
        
        // Prepara il cookie
        cookieValue =  Conversioni.stringToBase(cookieValue, 16);
        
        // Normalizza la lunghezza del cookie
        if ((cookieValue.length() % BLOCK_SIZE) != 0)
        {
            while ((cookieValue.length() % BLOCK_SIZE) != 0)
            {
                cookieValue += " ";
            }
        }
        
        // inizializza l'array di bytes per il testo da criptare
        plainText = cookieValue.getBytes("UTF8");
        encryptedText = new byte[cookieValue.length()];
        
        // Cripta il testo in blocchi da 8 byte
        for (int i=0; i<plainText.length; i+=BLOCK_SIZE)
        {
            blowfish.encrypt(plainText, i, encryptedText, i, keyObject, BLOCK_SIZE);
        }
        
        // Converte il testo criptato in Base64 per non perdere ulteriori bit
        String encryptedString = Base64.encode(encryptedText);
        return encryptedString;
    }
    
    /**
     * Decripta l'informazione con una chiave tramite l'algoritmo Blowfish
     */
    public static String decripta (String cookieValue, String key) throws InvalidKeyException, UnsupportedEncodingException
    {
        byte[] encryptedText;
        byte[] decryptedText;
        Blowfish blowfish = new Blowfish();
        
        // Nonostante la precisione scende da 16 a 8 bit, si possono usare anche i caratteri speciali
        // Prepara la chiave
        byte[] keyBytes = Conversioni.stringToBase(key, 16).getBytes();
        Object keyObject = blowfish.makeKey(keyBytes, BLOCK_SIZE);
        
        // inizializza l'array di byte per decriptare
        encryptedText = Base64.decode(cookieValue);
        decryptedText = new byte[cookieValue.length()];
        
        for (int i=0; i<Array.getLength(encryptedText); i+=BLOCK_SIZE)
        {
            blowfish.decrypt(encryptedText, i, decryptedText, i, keyObject, BLOCK_SIZE);
        }
        String decryptedString = new String(decryptedText, "UTF8");
        String decodificata = "";
        {
            String[] tmp = decryptedString.trim().split(" ");
            for (int i=0; i<tmp.length; i++)
            {
                int ascii = new Integer(Conversioni.baseToDec(tmp[i], 16));
                char[] carattere = Character.toChars(ascii);
                decodificata += carattere[0] + "";
            }
        }
        return decodificata;
    }
}