import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.Scanner;

public class WordsCounter extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private TreeMap<String, Integer> words = new TreeMap<>();
    private WordView wordsView;
    private JFileChooser chooser;
    private String path;

    private JButton buttonStart = new JButton("Dodaj");
    private JButton buttonClear = new JButton("Wyczyść");
    private JButton buttonAbout = new JButton("Autor");


    public WordsCounter() {
        super("Czytanie książki");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 700);

        JPanel panel = new JPanel();
        buttonStart.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonAbout.addActionListener(this);
        chooser= new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe","txt"));
        menuBar = new JMenuBar();
        menuBar.add(buttonStart);
        menuBar.add(buttonClear);
        menuBar.add(buttonAbout);
        setJMenuBar(menuBar);
        wordsView = new WordView(words, 300, 500, "words:");
        panel.add(wordsView);

        setContentPane(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonStart) {

            chooser.setCurrentDirectory(new File("."));
            int result = chooser.showOpenDialog(WordsCounter.this);
            if(result==JFileChooser.APPROVE_OPTION){
                this.path = chooser.getSelectedFile().getPath();
                pack();
            }
            downloadBook();

        } else if (source == buttonClear) {
            words.clear();
        } else if (source == buttonAbout) {
            About about;
            try {
                about = new About(this);
                about.setVisible(true);
            } catch (Exception event) {
                System.err.println(event.getMessage());
            }
        }
        wordsView.refresh();
    }

    private class About extends JDialog {
        About(JFrame owner) throws MalformedURLException {
            super(owner, "O Autorze", true);
            URL url = null;
            try {
                url = new URL("https://media.giphy.com/media/l0HlIKdi4DIEDk92g/giphy.gif");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            Icon icon = new ImageIcon(url);
            JLabel label = new JLabel(icon);
            add(new JLabel("Autor:\t Tobiasz Rumian\t Indeks: 226131"), BorderLayout.NORTH);
            add(label, BorderLayout.CENTER);
            JButton ok = new JButton("ok");
            ok.addActionListener(e -> setVisible(false));
            add(ok, BorderLayout.SOUTH);
            setSize(400, 400);
        }
    }
    private void downloadBook(){
        Scanner in = null;
        try {
            in = new Scanner(Paths.get(this.path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(in.hasNext()){
            String word = in.next().replaceAll("[^\\w\\s]","");
            if (word!=""&&word!=" ") {
                if(words.get(word)==null) {
                    if (!words.containsKey(word)) {
                    words.put(word,1);
                }
                } else if(words.get(word)>0){
                    int o = words.get(word)+1;
                    words.put(word, o);
                }
            }
        }
    }



    public static void main(String[] args) {
        new WordsCounter();
    }
}