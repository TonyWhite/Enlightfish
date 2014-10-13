/**
 * Aggiungi qui una descrizione della classe FrmMain
 * 
 * @author Antonio Bianco
 * @version alpha
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.text.DefaultEditorKit;
import java.util.HashMap;
public class FrmMain extends JFrame implements ActionListener, WindowListener
{
    // variabili d'istanza
    private JClipboard clipboard;
    
    // Componenti della prima tab
    private JButton btnCode;
    private JButton btnDecode;
    private JTextArea txtDa;
    private JTextArea txtA;
    private JPopupMenu menuDa, menuA;
    private JPasswordField txtPassword;
    
    // Componenti della seconda tab
    private JPasswordField txtPasswordToCheck;
    private JButton btnCheckPassword;
    private JButton btnCopyPassword;
    private JTextArea txtInfoPassword;
    private JPopupMenu menuInfoPassword;
    
    /**
     * Costruttore degli oggetti di classe  FrmMain
     */
    public FrmMain()
    {
        // inizializza le variabili d'istanza
        super(CampiStatici.APPLICATION_NAME);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
        
        clipboard = new JClipboard();
        
        // Inizializzazione dei font
        //font = new Font(Opzioni.FONT_NAME, Opzioni.FONT_STILE, Opzioni.FONT_SIZE);
        Font font = new Font("Monospace", Font.PLAIN, 12);
        
        // Crea il sistema di tab
        JTabbedPane pannelloTab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT); // Crea il pannello delle tab
        pannelloTab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)); // Elimina l'artefatto grafico con un artefatto grafico. ESTIQAATZI.
        this.add(pannelloTab); // Aggiunge il pannello delle tab nella finestra principale
        
        /***********************************
         * Crea i componenti del primo tab *
         ***********************************/
        
        // Inizializzazione dei bottoni
        btnCode = new JButton("Codifica");
        btnCode.setToolTipText("Codifica il testo");
        btnCode.addActionListener(this);
        
        btnDecode = new JButton("Decodifica");
        btnDecode.setToolTipText("Decodifica il testo");
        btnDecode.addActionListener(this);
        
        // Aree di testo con scrolling e testo monospace
        txtDa = new JTextArea();
        txtDa.setFont(font);
        txtDa.setEditable(true);
        txtDa.setAutoscrolls(true);
        txtDa.setTabSize(2);
        txtDa.setLineWrap(true);
        txtDa.setWrapStyleWord(true);
        txtDa.setBackground(java.awt.Color.BLACK);
        txtDa.setForeground(java.awt.Color.GREEN);
        txtDa.setCaretColor(new Color(0, 255, 128));
        {
            /**
             * Crea le azioni copia/taglia/incolla per il componente
             * Crea il menu delle azioni
             * http://docs.oracle.com/javase/tutorial/uiswing/components/generaltext.html#commands
             */
            HashMap<Object, Action> mappaAzioni = new HashMap<Object, Action>();
            Action[] azioni = txtDa.getActions();
            for (int i = 0; i < azioni.length; i++) mappaAzioni.put(azioni[i].getValue(Action.NAME), azioni[i]);
            menuDa = new JPopupMenu(); // Crea il menu per questo componente
            menuDa.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY)); // Crea un bordo nero intorno al menu
            {
                JMenuItem menuTaglia = new JMenuItem(mappaAzioni.get(DefaultEditorKit.cutAction));
                menuTaglia.setText("Taglia");
                menuDa.add(menuTaglia);
                
                JMenuItem menuCopia = new JMenuItem(mappaAzioni.get(DefaultEditorKit.copyAction));
                menuCopia.setText("Copia");
                menuDa.add(menuCopia);
                
                JMenuItem menuIncolla = new JMenuItem(mappaAzioni.get(DefaultEditorKit.pasteAction));
                menuIncolla.setText("Incolla");
                menuDa.add(menuIncolla);
                
                menuDa.addSeparator();
                
                JMenuItem menuSelezionaTutto = new JMenuItem(mappaAzioni.get(DefaultEditorKit.selectAllAction));
                menuSelezionaTutto.setText("Seleziona tutto");
                menuDa.add(menuSelezionaTutto);
            }
            txtDa.setComponentPopupMenu(menuDa);
        }
        JScrollPane scrollDa = new JScrollPane(txtDa);
        
        // Inizializzazione dell'area di testo per la password
        txtPassword = new JPasswordField(10);
        txtPassword.setEchoChar(' ');
        txtPassword.setToolTipText("Password nascosta");
        
        txtA = new JTextArea();
        txtA.setFont(font);
        txtA.setEditable(true);
        txtA.setAutoscrolls(true);
        txtA.setTabSize(2);
        txtA.setLineWrap(true);
        txtA.setWrapStyleWord(true);
        txtA.setBackground(java.awt.Color.BLACK);
        txtA.setForeground(java.awt.Color.GREEN);
        txtA.setCaretColor(new Color(0, 255, 128));
        {
            /**
             * Crea le azioni copia/taglia/incolla per il componente
             * Crea il menu delle azioni
             * http://docs.oracle.com/javase/tutorial/uiswing/components/generaltext.html#commands
             */
            HashMap<Object, Action> mappaAzioni = new HashMap<Object, Action>();
            Action[] azioni = txtA.getActions();
            for (int i = 0; i < azioni.length; i++) mappaAzioni.put(azioni[i].getValue(Action.NAME), azioni[i]);
            menuA = new JPopupMenu(); // Crea il menu per questo componente
            menuA.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY)); // Crea un bordo nero intorno al menu
            {
                JMenuItem menuTaglia = new JMenuItem(mappaAzioni.get(DefaultEditorKit.cutAction));
                menuTaglia.setText("Taglia");
                menuA.add(menuTaglia);
                JMenuItem menuCopia = new JMenuItem(mappaAzioni.get(DefaultEditorKit.copyAction));
                menuCopia.setText("Copia");
                menuA.add(menuCopia);
                JMenuItem menuIncolla = new JMenuItem(mappaAzioni.get(DefaultEditorKit.pasteAction));
                menuIncolla.setText("Incolla");
                menuA.add(menuIncolla);
                menuA.addSeparator();
                JMenuItem menuSelezionaTutto = new JMenuItem(mappaAzioni.get(DefaultEditorKit.selectAllAction));
                menuSelezionaTutto.setText("Seleziona tutto");
                menuA.add(menuSelezionaTutto);
            }
            txtA.setComponentPopupMenu(menuA);
        }
        JScrollPane scrollA = new JScrollPane(txtA);
        
        /*************************************************
         * Posizionamento dei componenti nella prima tab *
         *************************************************/
        JPanel pnlTabCipher = new JPanel(new BorderLayout(3, 3));
        pannelloTab.addTab("Cipher", pnlTabCipher);
        
        // Posizione dei componenti al centro
        JPanel pnlCentrale = new JPanel(new GridLayout(1, 2, 3, 3)); // Pannello generale: comandi e barra di avanzamento
        pnlTabCipher.add(pnlCentrale, BorderLayout.CENTER);
        
        JPanel pnlClean = new JPanel(new BorderLayout());
        pnlClean.add(new JLabel("Testo in chiaro"), BorderLayout.NORTH);
        pnlClean.add(scrollDa, BorderLayout.CENTER);
        pnlCentrale.add(pnlClean);
        
        JPanel pnlCoded = new JPanel(new BorderLayout());
        pnlCoded.add(new JLabel("Testo codificato"), BorderLayout.NORTH);
        pnlCoded.add(scrollA, BorderLayout.CENTER);
        pnlCentrale.add(pnlCoded);
        
        // Posizione dei componenti in basso
        JPanel pnlPassword = new JPanel(new BorderLayout());
        pnlPassword.add(new JLabel("Password"), BorderLayout.WEST);
        //pnlPassword.add(txtPassword, BorderLayout.CENTER);
        {
            JPanel pnlTxtPassword = new JPanel(new GridLayout(1,1));
            pnlTxtPassword.add(txtPassword);
            pnlPassword.add(pnlTxtPassword, BorderLayout.CENTER);
        }
        {
            JPanel pnlEncode = new JPanel(new GridLayout(1, 2, 0, 0));
            pnlEncode.add(btnCode);
            pnlEncode.add(btnDecode);
            pnlPassword.add(pnlEncode, BorderLayout.EAST);
        }
        pnlTabCipher.add(pnlPassword, BorderLayout.SOUTH);
        
        /**********************************************
         * Inizializza i componenti della seconda tab *
         **********************************************/
        txtPasswordToCheck = new JPasswordField();
        txtPasswordToCheck.setEchoChar(' ');
        txtPasswordToCheck.setToolTipText("Password da verificare");
        
        btnCheckPassword = new JButton("Verifica");
        btnCheckPassword.setToolTipText("Calcola la complessità della password");
        btnCheckPassword.addActionListener(this);
        
        btnCopyPassword = new JButton("Copia");
        btnCopyPassword.setToolTipText("Copia la password nella clipboard");
        btnCopyPassword.addActionListener(this);
        
        txtInfoPassword = new JTextArea();
        txtInfoPassword.setToolTipText("Rapporto sulla complessità della password");
        txtInfoPassword.setBackground(java.awt.Color.BLACK);
        txtInfoPassword.setForeground(java.awt.Color.GREEN);
        txtInfoPassword.setCaretColor(new Color(0, 255, 128));
        txtInfoPassword.setEditable(false);
        {
            /**
             * Crea le azioni copia/taglia/incolla per il componente
             * Crea il menu delle azioni
             * http://docs.oracle.com/javase/tutorial/uiswing/components/generaltext.html#commands
             */
            HashMap<Object, Action> mappaAzioni = new HashMap<Object, Action>();
            Action[] azioni = txtInfoPassword.getActions();
            for (int i = 0; i < azioni.length; i++) mappaAzioni.put(azioni[i].getValue(Action.NAME), azioni[i]);
            menuInfoPassword = new JPopupMenu(); // Crea il menu per questo componente
            menuInfoPassword.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY)); // Crea un bordo nero intorno al menu
            {
                JMenuItem menuTaglia = new JMenuItem(mappaAzioni.get(DefaultEditorKit.cutAction));
                menuTaglia.setText("Taglia");
                menuInfoPassword.add(menuTaglia);
                
                JMenuItem menuCopia = new JMenuItem(mappaAzioni.get(DefaultEditorKit.copyAction));
                menuCopia.setText("Copia");
                menuInfoPassword.add(menuCopia);
                
                JMenuItem menuIncolla = new JMenuItem(mappaAzioni.get(DefaultEditorKit.pasteAction));
                menuIncolla.setText("Incolla");
                menuInfoPassword.add(menuIncolla);
                
                menuInfoPassword.addSeparator();
                
                JMenuItem menuSelezionaTutto = new JMenuItem(mappaAzioni.get(DefaultEditorKit.selectAllAction));
                menuSelezionaTutto.setText("Seleziona tutto");
                menuInfoPassword.add(menuSelezionaTutto);
            }
            txtInfoPassword.setComponentPopupMenu(menuInfoPassword);
        }
        
        /********************************************
         * Posiziona i componenti nella seconda tab *
         ********************************************/
        JPanel pnlTabPassword = new JPanel(new BorderLayout(3, 3));
        pannelloTab.addTab("Password", pnlTabPassword);
        
        {
            JPanel pnlTxtPassword = new JPanel(new BorderLayout(3, 3));
            pnlTxtPassword.add(txtPasswordToCheck, BorderLayout.CENTER);
            // Posizionamento dei pulsanti
            {
                JPanel pnlPulsanti = new JPanel(new GridLayout(1, 2, 0, 0));
                pnlPulsanti.add(btnCheckPassword);
                pnlPulsanti.add(btnCopyPassword);
                pnlTxtPassword.add(pnlPulsanti, BorderLayout.EAST);
            }
            
            pnlTabPassword.add(pnlTxtPassword, BorderLayout.NORTH);
        }
        pnlTabPassword.add(txtInfoPassword, BorderLayout.CENTER);
        
        /*************************************************
         * Crea la tab per le informazioni del programma *
         *************************************************/
        JPanel pnlTabInfo = new JPanel(new BorderLayout());
        pannelloTab.addTab("Info", pnlTabInfo);
        {
            JPanel pnlCentrato = new JPanel(); // Pannello centrale
            pnlCentrato.setLayout(new BoxLayout(pnlCentrato, BoxLayout.Y_AXIS));
            JLabel lblLogoProgramma = new JLabel(creaIcona(CampiStatici.APPLICATION_ICON), JLabel.CENTER);
            JPanel pnlLogo = new JPanel();
            pnlLogo.add(lblLogoProgramma);
            JLabel lblTitolo = new JLabel(CampiStatici.APPLICATION_NAME, JLabel.CENTER);
            lblTitolo.setFont(lblTitolo.getFont().deriveFont(Font.BOLD)); // Applica lo stile grassetto
            JPanel pnlTitolo = new JPanel();
            pnlTitolo.add(lblTitolo);
            JLabel lblVersione = new JLabel(CampiStatici.APPLICATION_VERSION, JLabel.CENTER);
            JPanel pnlVersione = new JPanel();
            pnlVersione.add(lblVersione);
            JLabel lblDescrizione_1 = new JLabel(CampiStatici.APPLICATION_DESCRIPTION, JLabel.CENTER);
            JPanel pnlDescrizione_1 = new JPanel();
            pnlDescrizione_1.add(lblDescrizione_1);
            URLJLabel lblWebPage = new URLJLabel(CampiStatici.APPLICATION_HOMEPAGE, JLabel.CENTER, CampiStatici.APPLICATION_HOMEPAGE);
            JPanel pnlWebPage = new JPanel();
            pnlWebPage.add(lblWebPage);
            JLabel lblDiritti = new JLabel(CampiStatici.APPLICATION_COPYRIGHT + " " + CampiStatici.DEVELOPER, JLabel.CENTER);
            JPanel pnlDiritti = new JPanel();
            pnlDiritti.add(lblDiritti);
            URLJLabel lblLicenza = new URLJLabel(creaIcona("img/128/gplv3.png"), JLabel.CENTER, "http://www.gnu.org/licenses/gpl.html");
            JPanel pnlLicenza = new JPanel();
            pnlLicenza.add(lblLicenza);
            pnlCentrato.add(pnlLogo);
            pnlCentrato.add(pnlTitolo);
            pnlCentrato.add(pnlVersione);
            pnlCentrato.add(pnlDescrizione_1);
            pnlCentrato.add(pnlWebPage);
            pnlCentrato.add(pnlDiritti);
            pnlCentrato.add(pnlLicenza);
            JPanel pnlCentratoPack = new JPanel(); // Pannello di convenienza per contenere le dimensioni
            pnlCentratoPack.add(pnlCentrato);
            pnlTabInfo.add(pnlCentratoPack, BorderLayout.CENTER);
        }
        
        /**************************
         * Visualizza la finestra *
         **************************/
        this.setSize(600, 450);
        Dimension dimensioni = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(dimensioni.getWidth()/2-getWidth()/2), (int)(dimensioni.getHeight()/2-getHeight()/2));
        this.setVisible(true);
        
        txtDa.requestFocus();
    }
    
    /**
     * Codifica il testo
     */
    private void codifica()
    {
        String from = txtDa.getText();
        String password = new String(txtPassword.getPassword());
        
        if (from.length()==0)
        {
            JOptionPane.showMessageDialog(this, "I tuoi neuroni oppongono resistenza?\nDevi inserire un messaggio da criptare.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (password.length()==0)
        {
            JOptionPane.showMessageDialog(this, "Non pensare che sia tutto puffmagico.\nDevi inserire una password per criptare il messaggio.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try
        {
            String risultato = Pescepalla.cripta(from, password);
            txtA.setText(risultato);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "La tua password non è valida.\nPer risolvere il problema, prova a gridare \"IO AMO IL VERDE!!!\" 3 volte di fronte la Chiesa Madre più vicina. Se non funziona, potrai rimediare comunque degli spicci dai passanti.", this.getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Decodifica il testo
     */
    private void decodifica()
    {
        String from = txtA.getText();
        String password = new String(txtPassword.getPassword());
        
        if (from.length()==0)
        {
            JOptionPane.showMessageDialog(this, "I tuoi neuroni oppongono resistenza?\nDevi inserire un messaggio da decriptare.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (password.length()==0)
        {
            JOptionPane.showMessageDialog(this, "Potresti anche essere riuscito ad accendere una lampadina, ma ciò non vuole dire che tu sia un illuminato.\nDevi inserire una password per decriptare il messaggio.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try
        {
            String risultato = Pescepalla.decripta(from, password);
            txtDa.setText(risultato);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "La password è sbagliata: fatti illuminare dal mittente.", this.getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Copia la password nella clipboard
     */
    private void passwordToClipboard()
    {
        if (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(this,"ATTENZIONE!\n\nCon questo comando copierai la tua password negli appunti di sistema\ne sarà disponibile con CONTROL+C su qualunque applicazione.\n\nSei sicuro di voler continuare?", this.getTitle(), JOptionPane.YES_NO_OPTION))
        {
            String password = new String(txtPassword.getPassword());
            clipboard.toClipboard(password);
            JOptionPane.showMessageDialog(this, "La password è stata copiata negli appunti di sistema.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Operazione annullata.", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Crea un'icona generica per qualsiasi componente
     */
    private ImageIcon creaIcona(String path)
    {
        URL imgURL = FrmMain.class.getResource(path);
        if (imgURL != null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            System.err.println("Impossibile trovare l'immagine " + path);
            return null;
        }
    }
    
    /**
     * Esce dal programma
     */
    private void esci()
    {
        if (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(this,"Vuoi uscire dall'applicazione?", this.getTitle(), JOptionPane.YES_NO_OPTION))
        {
            System.exit(0);
        }
    }
    
    /**
     * Ascoltatore della finestra
     */
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e)
    {
        esci();
    }
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    
    /**
     * Ascoltatore dei bottoni
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            // Controlla se il pulsante si trova nel pannello degli anni
            String[] lista = {"1", "2"};
            for(int i=0; i<lista.length; i++)
            {
                if (e.getSource().equals((Object)btnCode))
                {
                    codifica();
                    break;
                }
                else if (e.getSource().equals((Object)btnDecode))
                {
                    decodifica();
                    break;
                }
                else if (e.getSource().equals((Object)btnCheckPassword))
                {
                    txtInfoPassword.setText(CheckPassword.controllaPassword(new String(txtPasswordToCheck.getPassword())));
                    break;
                }
                else if (e.getSource().equals((Object)btnCopyPassword))
                {
                    passwordToClipboard();
                    break;
                }
            }
        }
    }
}
