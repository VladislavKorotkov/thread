import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Triangle extends JPanel implements ActionListener, MouseListener {
    private int x1 = 100, y1 = 100, x2 = 150, y2 = 150, x3 = 200, y3 = 100;
    private int angle1 = 0, angle2 = 120, angle3 = 240;
    private boolean clockwise = true;
    private JButton startButton, stopButton;
    private Thread thread;

    private boolean isRunning;

    public Triangle() {
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        add(stopButton);

        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillPolygon(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
        g.setColor(Color.BLUE);
        g.fillArc(x1 - 50, y1 - 50, 100, 100, angle1, 60);
        g.setColor(Color.GREEN);
        g.fillArc(x2 - 50, y2 - 50, 100, 100, angle2, 60);
        g.setColor(Color.YELLOW);
        g.fillArc(x3 - 50, y3 - 50, 100, 100, angle3, 60);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (thread == null) {
                thread = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            if (clockwise) {
                                angle1 += 5;
                                angle2 += 5;
                                angle3 += 5;
                            } else {
                                angle1 -= 5;
                                angle2 -= 5;
                                angle3 -= 5;
                            }
                            repaint();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {
                                return;
                              //  ex.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        } else if (e.getSource() == stopButton) {
            if (thread != null) {
                thread.interrupt();
                thread = null;
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            clockwise = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            clockwise = true;
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Triangle());
        frame.setVisible(true);
    }
}