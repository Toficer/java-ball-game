package BallGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Okno rankingu. Zawiera 5 najlepszych wynikow gier z przeszlosci. TODO: metoda pozwalajaca edytowac wyniki.
 * @author Rafal Raczynski
 */
public class TopScoresWindow extends Frame{
	
	JButton okButton;
	JLabel places[];
	String[] scores;
	
	TopScoresWindow(String title) throws IOException{
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
			
		});

		places = new JLabel[5];
		for(int i = 0; i< 5; i++){
			places[i] = new JLabel("empty");
		}
		scores = new String[5];

		updateScoreText();
		
		JPanel mainPanel = new JPanel(new GridLayout(0,1));
		mainPanel.setBackground(Color.lightGray);
		mainPanel.add(places[0]);
		mainPanel.add(places[1]);
		mainPanel.add(places[2]);
		mainPanel.add(places[3]);
		mainPanel.add(places[4]);

		okButton = new JButton("OK");
		
		JPanel footerPanel = new JPanel(new GridLayout(1,0));
		footerPanel.add(okButton);
		mainPanel.add(footerPanel);
		
		add(mainPanel);
		pack();
	}
	
	public Dimension getPreferredSize() {
		int h = 250;
		int w = 300;
		return new Dimension(w,h);
	}
	/**
	 * Aktualizuje tekstowa reprezentacje rankingu w oknie.
	 */
	public void updateScoreText() throws IOException {
		FileInputStream input = new FileInputStream("topscores.txt");
		BufferedInputStream scoreInput = new BufferedInputStream(input);
		InputStreamReader scoreReader = new InputStreamReader(scoreInput);
		LineNumberReader scoreFile = new LineNumberReader(scoreReader);
		String reading;
		String temp[];

		for(int i = 0; i<5; i++){
			reading = scoreFile.readLine();
			if(reading != null){
				temp = reading.split(" ");
				scores[i] = ((i + 1) + ". " + temp[0] + ": " + temp[1] + ";");
				places[i].setText(scores[i]);
			}
			else {
				places[i].setText( (i+1) + ". ERROR: 0;");
				scores[i] = ((i + 1) + ". ERROR: 0;");
			}
		}
	}
	/**
	 * Pozwala na wstawienie nowego wyniku do rankingu.
	 * @param score wynik
	 * @param name nazwa gracza
	 */
	void insertNewScore(int score, String name) throws IOException{
		updateScoreText();
		String localName = name;
		int localScore = score;
		String temp2[];
		//File fout = new File("topscores2.txt");
		FileOutputStream fos = new FileOutputStream("topscores.txt");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		for(int i = 0; i<5; i++){
			temp2 = scores[i].split(" ");
			temp2[1] = temp2[1].replaceAll(":", "");
			temp2[2] = temp2[2].replaceAll("\\D+", "");
			if(localScore>Integer.parseInt(temp2[2])) {
				scores[i] = ((i + 1) + ". " + localName + ": " + localScore + ";");
				bw.write(localName + " " + localScore);
				localName = temp2[1];
				localScore = Integer.parseInt(temp2[2]);
			}
			else bw.write((temp2[1] + " " + temp2[2]));
			System.out.println("DEBUG_SCOREWRITTEN");
			bw.newLine();
		}
		bw.close();
	}

}
