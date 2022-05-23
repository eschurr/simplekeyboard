import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.String;

public class tweetbot extends JFrame implements ActionListener {

    String buffer = "";  // create buffer that is the string in the messageBar
    JTextField messageBar; // create the message bar and some JButtons
    JButton backspace, spaceBar, emoji, send;
    JPanel keys = new JPanel();    // create the panel for my keys to be put into JFrame

    JButton[] first = new JButton[10];    // initialize array of JButtons
    JButton[] second = new JButton[9];
    JButton[] third = new JButton[7];

    String[] firstRow = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"}; // initialize string arrays for each row
    String[] secondRow = {"A", "S", "D", "F", "G", "H", "J", "K", "L"};
    String[] thirdRow = {"Z", "X", "C", "V", "B", "N", "M"};

    public JPanel key() { // method that returns a JPanel

        //-----------------------FIRST ROW OF BUTTONS--------------------------------------

        GridBagConstraints gbc = new GridBagConstraints();
        keys.setLayout(new GridBagLayout());                // initialize grid bag layout

        gbc.weightx = .01;
        gbc.weighty = .01;
        gbc.gridheight = 1;

        JButton attachment = new JButton("+");
        attachment.setForeground(Color.blue);
        gbc.gridx = 0;
        gbc.gridy = 0;
        keys.add(attachment, gbc);

        messageBar = new JTextField("Type a message... ");
        messageBar.setActionCommand("" + buffer);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        keys.add(messageBar, gbc);

        send = new JButton("Send");
        send.setForeground(Color.white);
        send.setBackground(Color.blue);
        send.setOpaque(true);
        send.setBorderPainted(false);
        gbc.gridx = 5;
        gbc.gridy = 0;
        keys.add(send);
        send.addActionListener(this);    // adding an action listener to every button

        //-------------------SECOND ROW OF BUTTONS------------------------------------------

        for (int i = 0; i < 10; i++) {    // iterate through array of characters and add buttons for each
            first[i] = new JButton(firstRow[i]);
            gbc.gridx = i + 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            first[i].setBackground(Color.white);
            first[i].setForeground(Color.blue);
            keys.add(first[i], gbc);
            first[i].addActionListener(this);
        }

        //----------------------THIRD----------------------

        for (int i = 0; i < 9; i++) {
            second[i] = new JButton(secondRow[i]);
            gbc.gridx = i + 2;
            gbc.gridy = 2;
            second[i].setBackground(Color.white);
            second[i].setForeground(Color.blue);
            keys.add(second[i], gbc);
            second[i].addActionListener(this);
        }

        //-------------------FOURTH-------------------------


        JButton shift = new JButton("\u21E7");
        shift.setForeground(Color.blue);
        shift.setBackground(Color.blue);
        gbc.gridx = 2;
        gbc.gridy = 3;
        keys.add(shift, gbc);

        for (int i = 0; i < 7; i++) {
            third[i] = new JButton(thirdRow[i]);
            gbc.gridx = i + 3;
            third[i].setBackground(Color.white);
            third[i].setForeground(Color.blue);
            keys.add(third[i], gbc);
            third[i].addActionListener(this);
        }

        backspace = new JButton("⌫");
        backspace.setForeground(Color.blue);
        gbc.gridx = 10;
        gbc.gridy = 3;
        keys.add(backspace, gbc);
        backspace.addActionListener(this);

        //-----------------------FIFTH------------------------


        JButton numbers = new JButton("123");
        numbers.setForeground(Color.blue);
        gbc.gridx = 2;
        gbc.gridy = 4;
        keys.add(numbers, gbc);

        emoji = new JButton("\u263A");
        emoji.setForeground(Color.blue);
        gbc.gridx = 3;
        keys.add(emoji, gbc);
        emoji.addActionListener(this);

        JButton microphone = new JButton("\uD83C\uDF99");
        microphone.setForeground(Color.blue);
        gbc.gridx = 4;
        keys.add(microphone, gbc);

        spaceBar = new JButton("space");
        spaceBar.setForeground(Color.blue);
        gbc.gridx = 5;
        gbc.gridwidth = 3;
        keys.add(spaceBar, gbc);
        spaceBar.addActionListener(this);

        JButton enter = new JButton("Return");
        enter.setForeground(Color.blue);
        gbc.gridx = 8;
        gbc.gridwidth = 2;
        keys.add(enter, gbc);
        return keys;   // return the created panel
    }


    public void actionPerformed(ActionEvent e) {    // used to add correct character/action to message bar
        if (e.getSource() == backspace) {
            buffer = buffer.substring(0, buffer.length()-1);
            messageBar.setText("" + buffer);
        }
        else if (e.getSource() == spaceBar) {
            buffer += " ";
        }
        else if (e.getSource() == emoji) {
            buffer += ":-)";
        }
        else if (e.getSource() == send) {
            System.out.println(buffer);  // if send is hit print buffer to terminal
        }
        else {   // iterate through buttons to find a match, if it matches print the corresponding button from the list
            for (int i = 0; i < 10; i ++) {
                if (e.getSource() == first[i]) {
                    buffer += firstRow[i];
                    messageBar.setText("" + buffer);
                    break; // break loop if it is found
                }
            }
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == second[j]) {
                    buffer += secondRow[j];
                    messageBar.setText(""+buffer);
                    break;
                }
            }
            for (int k = 0; k < 7; k++) {
                if (e.getSource() == third[k]) {
                    buffer += thirdRow[k];
                    messageBar.setText("" + buffer);
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        JFrame keyboard = new JFrame("Match the Super Bowl MVPs!"); // create JFrame
        keyboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close operation
        keyboard.setSize(500, 200); // set width of JFrame
        tweetbot instance = new tweetbot(); // create instance of tweetbot class
        JPanel board = instance.key();   // create keyboard
        keyboard.getContentPane().add(board);  // add panel to JFrame
        keyboard.setVisible(true); // make sure window is visible
    }
}