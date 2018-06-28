package BallGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Okno koncowe, zawiera wynik gracza i ranking.
 * @author Rafal Raczynski
 */
public class EndWindow extends JFrame {

    JLabel places[];
    JLabel titleLabel;
    JLabel scoreLabel;
    JLabel subtitleLabel;
    JButton okButton;
    int score = 0;

    EndWindow(String title){
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }

        });

        places = new JLabel[5];
        for(int i = 0; i< 5; i++){
            places[i] = new JLabel("empty", SwingConstants.CENTER);
        }

        titleLabel = new JLabel("KONIEC GRY", SwingConstants.CENTER);
        scoreLabel = new JLabel("Twoj wynik: " + score, SwingConstants.CENTER);
        subtitleLabel = new JLabel("NAJLEPSZE WYNIKI:", SwingConstants.CENTER);
        okButton = new JButton("OK");

        JPanel mainPanel = new JPanel(new GridLayout(0,1));
        mainPanel.setBackground(Color.lightGray);
        mainPanel.add(titleLabel);
        mainPanel.add(scoreLabel);
        mainPanel.add(subtitleLabel);
        mainPanel.add(places[0]);
        mainPanel.add(places[1]);
        mainPanel.add(places[2]);
        mainPanel.add(places[3]);
        mainPanel.add(places[4]);
        mainPanel.add(okButton);
        add(mainPanel);
        pack();

    }

    public Dimension getPreferredSize() {
        int h = 450;
        int w = 300;
        return new Dimension(w,h);
    }

    public void updateScore(){
        scoreLabel.setText("Twoj wynik: " + score);
    }
}
