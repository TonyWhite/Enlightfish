/**
 * Aggiungi qui una descrizione della classe Enlightfish
 * 
 * @author Antonio Bianco
 * @version beta
 */
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
public class Enlightfish implements Runnable
{
    private FrmMain finestra;
    private IconaAnimata iconaAnimata;
    
    public Enlightfish()
    {
        iconaAnimata = new IconaAnimata("img/128/", "png", 11);
        finestra = new FrmMain();
    }
    
    public static void main(String[] args)
    {
        setTema();
        new Thread(new Enlightfish()).start();
    }
    
    private static void setTema()
    {
        try
        {
            String tema = OSValidator.getTemaIntegrazione();
            String temaClassName = "";
            String temaNimbus = "";
            boolean temaTrovato = false;
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName())) temaNimbus = info.getClassName();
                if (tema.equals(info.getName()))
                {
                    temaClassName = info.getClassName();
                    temaTrovato = true;
                    break;
                }
            }
            if (temaTrovato) UIManager.setLookAndFeel(temaClassName);
            else UIManager.setLookAndFeel(temaNimbus);
        }
        catch(Exception e){}
    }
    
    /** Thread per animare l'icona della finestra */
    public void run()
    {
        while(true)
        {
            try
            {
                finestra.setIconImage(iconaAnimata.getNext());
                Thread.sleep(100);
            }
            catch(Exception e){}
        }
    }
}
