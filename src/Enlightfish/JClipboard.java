/**
 * Aggiungi qui una descrizione della classe JClipboard
 * 
 * @author Antonio Bianco
 * @version RC
 */
import java.awt.datatransfer.*;
import java.awt.*;
 
public class JClipboard implements ClipboardOwner
{
    /**
     * Incolla nella Clipboard
     */
    public void toClipboard(String contenuto)
    {
        StringSelection st = new StringSelection(contenuto);
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        cp.setContents(st, this);
    }
     
    /**
     * Legge il testo dalla clipboard di sistema
     */
    public String fromClipboard()
    {
        String contenuto = null;
        Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = cp.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor); //Se c'è il contenuto ed è una stringa
        if (hasTransferableText)
        {
            try
            {
                contenuto = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch(Exception e){}
        }
        return contenuto;
    }
     
    public void lostOwnership(Clipboard clip, Transferable tr){}
}