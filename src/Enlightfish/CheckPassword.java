/**
 * Aggiungi qui una descrizione della classe CheckPassword
 * 
 * @author Antonio Bianco
 * @version RC
 */
public class CheckPassword
{
    /**
     * Controlla la robustezza di una password
     */
    public static String controllaPassword(String password)
    {
        // costanti
        char[] minuscole = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        boolean fgMinuscole = false;
        char[] maiuscole = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        boolean fgMaiuscole = false;
        char[] cifre = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        boolean fgCifre = false;
        char[] punteggiatura = {(char)44, (char)59, (char)46, (char)58, (char)32, (char)33, (char)63, (char)39};    // Punteggiatura e spazi
        boolean fgPunteggiatura = false;
        char[] speciali = {(char)92, (char)34, (char)163, (char)36, (char)37, (char)38, (char)47, (char)40, (char)41, (char)61, (char)94, (char)224, (char)232, (char)236, (char)242, (char)249, (char)95, (char)42, (char)45, (char)43, (char)64, (char)231, (char)35, (char)167, (char)233, (char)91, (char)93};
        boolean fgSpeciali = false;
        int ultimoASCII = 255;
        boolean fgASCII = false;
        int ultimoUNICODE = 65535;
        boolean fgUNICODE = false;
        
        // Rilevo dei caratteri utilizzati
        String caratteriSconosciuti = "";
        for (int i=0; i<password.length(); i++)
        {
            boolean carattereIdentificato = false;
            
            // Identifica le minuscole
            for (int j=0; j<minuscole.length; j++)
            {
                if (password.charAt(i)==minuscole[j])
                {
                    fgMinuscole = true;
                    carattereIdentificato = true;
                    break;
                }
            }
            
            // Identifica le maiuscole
            for (int j=0; (j<maiuscole.length)&&(!carattereIdentificato); j++)
            {
                if (password.charAt(i)==maiuscole[j])
                {
                    fgMaiuscole = true;
                    carattereIdentificato = true;
                    break;
                }
            }
            
            // Identifica le cifre
            for (int j=0; (j<cifre.length)&&(!carattereIdentificato); j++)
            {
                if (password.charAt(i)==cifre[j])
                {
                    fgCifre = true;
                    carattereIdentificato = true;
                    break;
                }
            }
            
            // Identifica la punteggiatura
            for (int j=0; (j<punteggiatura.length)&&(!carattereIdentificato); j++)
            {
                if (password.charAt(i)==punteggiatura[j])
                {
                    fgPunteggiatura = true;
                    carattereIdentificato = true;
                    break;
                }
            }
            
            // Identifica i caratteri speciali
            for (int j=0; (j<speciali.length)&&(!carattereIdentificato); j++)
            {
                if (password.charAt(i)==speciali[j])
                {
                    fgSpeciali = true;
                    carattereIdentificato = true;
                    break;
                }
            }
            
            if (!carattereIdentificato)
            {
                // È un carattere codificato in ASCII?
                int codiceCarattere = (int)password.charAt(i);
                if (codiceCarattere<=ultimoASCII)
                {
                    fgASCII = true;
                    carattereIdentificato = true;
                }
                else if (codiceCarattere<=ultimoUNICODE)
                {
                    fgUNICODE = true;
                    carattereIdentificato = true;
                }
                else
                {
                    caratteriSconosciuti += " 0x" + Integer.toHexString(codiceCarattere);
                }
            }
        }
        
        // Calcolo della robustezza della password
        int caratteri = 0;
        if (fgMinuscole) caratteri = minuscole.length;
        if (fgMaiuscole) caratteri += maiuscole.length;
        if (fgCifre) caratteri += cifre.length;
        if (fgPunteggiatura) caratteri += punteggiatura.length;
        if (fgSpeciali) caratteri += speciali.length;
        if (fgASCII) caratteri = ultimoASCII;
        if (fgUNICODE) caratteri = ultimoUNICODE;
        java.math.BigInteger base = new java.math.BigInteger("" + caratteri);
        java.math.BigInteger combinazioni = base.pow(password.length());
        
        // Preparazione dei risultati
        String risultato = "Lunghezza della password: " + password.length() + "\n";
        risultato += "Lettere minuscole: ";
        if (fgMinuscole) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Lettere maiuscole: ";
        if (fgMaiuscole) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Cifre: ";
        if (fgCifre) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Punteggiatura e spazi: ";
        if (fgPunteggiatura) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Caratteri speciali: ";
        if (fgSpeciali) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Caratteri ASCII: ";
        if (fgASCII) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Caratteri UNICODE: ";
        if (fgUNICODE) risultato += "SI\n";
        else risultato += "NO\n";
        risultato += "Combinazioni: " + combinazioni.toString();
        //System.out.println(risultato);    // Visualizza il risultato sul terminale
        return risultato;
    }
}
