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
    private int scale = 10; // échelle pour le dessin


    public ChenilleGUI(Chenille chenille, int width, int height) {
        this.chenille = chenille;
        this.width = width;
        this.height = height;
        setTitle("Chenille");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panel = new ChenillePanel();
        add(panel, BorderLayout.CENTER);
        JButton startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer == null || !timer.isRunning()) {
                    timer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            chenille.deplacer(panel.getWidth() / scale,
                                    panel.getHeight() / scale);
                            panel.repaint();
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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    // couleur des anneaux en fonction de leur position
    Color getColor(int i) {
        if (i ==0)
            return Color.RED;
        else
            return Color.GREEN;
    }


    private class ChenillePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int ringSize = scale;               // taille des anneaux sauf le 0
            int bigSize = (int) (scale * 1.3);  // taille de l'anneau 0

            g.setColor(Color.GREEN);
            for (int i = 0; i <= chenille.nbAnneaux(); ++i) {
                IAnneau anneau = chenille.anneau(i);
                g.setColor(getColor(i));
                if (i == 0) { // l'anneau 0 est plus gros
                    g.fillOval(anneau.x() * scale, anneau.y() * scale, bigSize, bigSize);
                    g.setColor(Color.BLACK);
                    g.drawOval(anneau.x() * scale, anneau.y() * scale, bigSize, bigSize);
                } else {
                    int x = anneau.x() * scale;
                    int y = anneau.y() * scale;
                    g.fillOval(x, y, ringSize, ringSize);
                    g.setColor(Color.BLACK);
                    g.drawOval(x, y, ringSize, ringSize);
                }
            }
        }
    }

    public static void main(String[] args) {
        final int width = 600, height = 400;
        final int nbAnneaux = 7;
        final int x = 10, y = 10; // position de l'anneau 0

        SwingUtilities.invokeLater(() -> {
            Chenille chenille = new Chenille(nbAnneaux, x, y);
            ChenilleGUI gui = new ChenilleGUI(chenille, width, height);
            gui.setVisible(true);
        });
    }
}