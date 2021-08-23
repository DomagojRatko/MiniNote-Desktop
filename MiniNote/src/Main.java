import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Application: Mini Note
 * Mini text editor. notes look a like.
 * Made for learning purposes. That include some of cool features
 * like transparent background and custom JFrame.
 * Application is allowed to use for educational purposes. All credits to Domagoj Ratko.
 * Your allowed to modify code and make application better for purpose of education only.
 * Special thanks Alphabet discord community for help.
 *
 * @author  Domagoj Ratko
 * @discord Domo Ratko™#0750
 * @email   domagoj.ratko@gmail.com
 * @version 1.0
 * @since   08.22.2021
 */

public class Main {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            final JFrame frame = new JFrame();

            ImageIcon exitImg = new ImageIcon(new ImageIcon(Main.class.getResource("exitPic.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            ImageIcon lineImg = new ImageIcon(Main.class.getResource("linePic.png"));
            ImageIcon minImg = new ImageIcon(new ImageIcon(Main.class.getResource("minPic.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            JLabel lineImage = new JLabel();
            JLabel exitButton = new JLabel();
            JLabel minButton = new JLabel();
            JTextArea textArea = new JTextArea();
            ImageIcon img = new ImageIcon(Main.class.getResource("icon.jpg"));

            // exitButton settings
            exitButton.setBounds(270, -1, 30, 30);
            exitButton.setIcon(exitImg);

            // lineImage settings
            lineImage.setBounds(5, 25, 290, 5);
            lineImage.setIcon(lineImg);

            // minButton settings
            minButton.setBounds(240, -1, 30, 30);
            minButton.setIcon(minImg);

            // textArea settings
            textArea.setBounds(5, 30, 295, 270);
            textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            textArea.setForeground(Color.BLACK);
            textArea.setOpaque(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            // frame settings
            frame.setBounds(300, 300, 300, 300);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);
            frame.setBackground(new Color(0.1f, 0.1f, 0.1f, 0.2f));
            frame.setTitle("MiniNote");
            frame.setIconImage(img.getImage());
            frame.setLayout(null);

            // frame add and settings
            Arrays.asList(exitButton,minButton,lineImage,textArea).forEach(frame.getContentPane()::add);
            frame.setVisible(true);

            FrameDragListener frameDragListener = new FrameDragListener(frame);
            frame.addMouseListener(frameDragListener);
            frame.addMouseMotionListener(frameDragListener);

            // exitButton on click
            exitButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.dispose(); // exits program
                }
            });

            // minButton on click
            minButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setState(Frame.ICONIFIED); // minimize program
                }
            });
        };
        SwingUtilities.invokeLater(runnable);
    }

    // Drag frame around screen with mouse
    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
}