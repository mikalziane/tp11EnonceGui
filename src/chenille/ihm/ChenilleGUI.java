package chenille.ihm;

import chenille.IAnneau;
import chenille.Chenille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChenilleGUI extends JFrame {
    private  final int width ;
    private  final int height;
    private Chenille chenille;
    private ChenillePanel panel;
    private Timer timer;
    private JLabel positionLabel; // Label pour afficher la position

    public ChenilleGUI() {
        this(600, 400);
    }

    public ChenilleGUI(int width, int height) {
        this.width = width;
        this.height = height;
        chenille = new Chenille(5, 10, 10);
        setTitle("Chenille");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panel = new ChenillePanel();
        add(panel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        positionLabel = new JLabel("Position: (10, 10)"); // Initialisation du label

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer == null || !timer.isRunning()) {
                    timer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            chenille.deplacer(panel.getWidth() / 20, panel.getHeight() / 20);
                            panel.repaint();
                            // Mise à jour de la position de la tête
                            IAnneau tete = chenille.anneau(0);
                            positionLabel.setText("Position: (" + tete.x() + ", " + tete.y() + ")");
                        }
                    });
                    timer.start();
                    startButton.setText("Stop");
                } else {
                    timer.stop();
                    startButton.setText("Start");
                }
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(startButton, BorderLayout.EAST);
        buttonPanel.add(positionLabel, BorderLayout.WEST); // Ajout du label à gauche
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ChenillePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int scale = 20;
            int ringSize = scale;         // taille des anneaux
            int headSize = (int)(scale * 1.3);  // tete plus grosse pour mieux la voir

            // on dessine d'abord les anneaux
            g.setColor(Color.GREEN);
            for (int i = 0; i <= chenille.nbAnneaux(); ++i) {
                int x = chenille.anneau(i).x() * scale;
                int y = chenille.anneau(i).y() * scale;
                g.fillOval(x, y, ringSize, ringSize);
                g.setColor(Color.BLACK);
                g.drawOval(x, y, ringSize, ringSize);
                g.setColor(Color.GREEN);  // Reset for next ring
            }

            // on dessine la tete apres pour mieux la voir
            g.setColor(Color.RED);
            int headX = chenille.anneau(0).x() * scale;
            int headY = chenille.anneau(0).y() * scale;
            // centrer la tete sur le centre de l'anneau
            headX -= (headSize - ringSize) / 2;
            headY -= (headSize - ringSize) / 2;
            g.fillOval(headX, headY, headSize, headSize);
            g.setColor(Color.BLACK);
            g.drawOval(headX, headY, headSize, headSize);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChenilleGUI gui = new ChenilleGUI();
            gui.setVisible(true);
        });
    }
}