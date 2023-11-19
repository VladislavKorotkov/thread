import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Triangles extends JPanel implements ActionListener, MouseListener {
    private int x1 = 80, y1 = 100, x2 = 80, y2 = 100, x3 = 80, y3 = 100;
    private int angle1 = 0, angle2 = 120, angle3 = 240;
    private boolean clockwise = true;
    private JButton startButton, stopButton;
    ExecutorService executorService;

    public Triangles() {
        startButton = new JButton("Полетели");
        startButton.addActionListener(this);
        add(startButton);

        stopButton = new JButton("Остановить");
        stopButton.addActionListener(this);
        add(stopButton);

        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillArc(x1, y1, 200, 200, angle1, 60);
        g.setColor(Color.GREEN);
        g.fillArc(x2, y2, 200, 200, angle2, 60);
        g.setColor(Color.YELLOW);
        g.fillArc(x3, y3, 200, 200, angle3, 60);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (executorService == null) {
                executorService = Executors.newFixedThreadPool(3);
                Runnable task1 = ()->{
                    while (true) {
                            if (clockwise)
                                angle1 += 15;
                             else
                                angle1 -= 15;
                            repaint();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {
                                return;
                            }
                        }
                };
                Runnable task2 = ()->{
                    while (true) {
                        if (clockwise)
                            angle2 += 20;
                        else
                            angle2 -= 20;
                        repaint();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            return;
                        }
                    }
                };
                Runnable task3 = ()->{
                    while (true) {
                        if (clockwise)
                            angle3 += 25;
                        else
                            angle3 -= 25;
                        repaint();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            return;
                        }
                    }
                };
                executorService.submit(task1);
                executorService.submit(task2);
                executorService.submit(task3);
            }
        } else if (e.getSource() == stopButton) {
            if (executorService != null) {
                executorService.shutdownNow();
                executorService = null;
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
        frame.setContentPane(new Triangles());
        frame.setVisible(true);
    }
}