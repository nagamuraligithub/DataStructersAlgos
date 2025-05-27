package com.example;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LinearSearch extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textArraySizeInput;
    private JTextField textArrayElementsInput;
    private JTextField textSearchValueInput;
    private JTextArea textIndex;
    private JTextArea textArrayElements;
    private JTextArea textResult;

    private JButton btnCreateArray;
    private JButton btnAddElements;
    private JButton btnSearch;
    private JButton btnReset;

    private int[] array;
    private int currentSize = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	LinearSearch frame = new LinearSearch();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LinearSearch() {
        setTitle("LinearSearch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);
        
        // Create content pane with background image
        contentPane = new BackgroundPanel("C:/Users/areva/OneDrive/Desktop/LinearSearch.png"); // Replace with your image path
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initializeComponents();
        setupButtonActions();
    }

    private void initializeComponents() {
        JLabel lblArraySize = new JLabel("Array Size:");
        lblArraySize.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblArraySize.setBounds(41, 34, 96, 27);
        contentPane.add(lblArraySize);

        textArraySizeInput = new JTextField();
        textArraySizeInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textArraySizeInput.setBounds(256, 28, 96, 27);
        contentPane.add(textArraySizeInput);
        textArraySizeInput.setColumns(10);

        btnCreateArray = new JButton("Create Array");
        btnCreateArray.setForeground(new Color(0, 255, 0));
        btnCreateArray.setBounds(430, 34, 154, 27);
        contentPane.add(btnCreateArray);

        JLabel lblEnterArrayElements = new JLabel("Enter Array Elements:");
        lblEnterArrayElements.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEnterArrayElements.setBounds(41, 86, 156, 27);
        contentPane.add(lblEnterArrayElements);

        textArrayElementsInput = new JTextField();
        textArrayElementsInput.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textArrayElementsInput.setBounds(256, 83, 96, 30);
        contentPane.add(textArrayElementsInput);
        textArrayElementsInput.setColumns(10);
        textArrayElementsInput.setEnabled(false);

        btnAddElements = new JButton("Add Elements");
        btnAddElements.setForeground(new Color(0, 0, 255));
        btnAddElements.setBounds(430, 86, 154, 27);
        contentPane.add(btnAddElements);
        btnAddElements.setEnabled(false);

        JLabel lblEnterValuetoSearch = new JLabel("Enter Value to Search:");
        lblEnterValuetoSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEnterValuetoSearch.setBounds(41, 139, 142, 27);
        contentPane.add(lblEnterValuetoSearch);

        textSearchValueInput = new JTextField();
        textSearchValueInput.setBounds(256, 145, 96, 30);
        contentPane.add(textSearchValueInput);
        textSearchValueInput.setColumns(10);
        textSearchValueInput.setEnabled(false);

        btnSearch = new JButton("Search");
        btnSearch.setForeground(new Color(255, 128, 0));
        btnSearch.setBounds(430, 145, 154, 27);
        contentPane.add(btnSearch);
        btnSearch.setEnabled(false);

        btnReset = new JButton("Reset");
        btnReset.setForeground(new Color(255, 0, 0));
        btnReset.setBounds(256, 311, 114, 42);
        contentPane.add(btnReset);

        textIndex = new JTextArea();
        textIndex.setBounds(41, 230, 114, 36);
        textIndex.setEditable(false);
        contentPane.add(textIndex);

        textArrayElements = new JTextArea();
        textArrayElements.setBounds(189, 230, 214, 36);
        textArrayElements.setEditable(false);
        contentPane.add(textArrayElements);

        textResult = new JTextArea();
        textResult.setBounds(430, 230, 114, 36);
        textResult.setEditable(false);
        contentPane.add(textResult);
    }

    private void setupButtonActions() {
        btnCreateArray.addActionListener(e -> {
            try {
                int size = Integer.parseInt(textArraySizeInput.getText().trim());
                if (size <= 0) {
                    JOptionPane.showMessageDialog(this, "Array size must be positive");
                    return;
                }
                array = new int[size];
                currentSize = 0;
                textArrayElementsInput.setEnabled(true);
                btnAddElements.setEnabled(true);
                textArrayElements.setText("Array created with size: " + size);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid array size");
            }
        });

        btnAddElements.addActionListener(e -> {
            try {
                String[] elements = textArrayElementsInput.getText().split("[,\\s]+");
                if (currentSize + elements.length > array.length) {
                    JOptionPane.showMessageDialog(this, "Too many elements for array size");
                    return;
                }

                for (String element : elements) {
                    array[currentSize++] = Integer.parseInt(element.trim());
                }

                textArrayElements.setText(arrayToString());
                textArrayElementsInput.setText(""); // ✅ Clear the input after adding
                textSearchValueInput.setEnabled(true);
                btnSearch.setEnabled(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers separated by commas or spaces");
            }
        });

        btnSearch.addActionListener(e -> {
            try {
                int searchValue = Integer.parseInt(textSearchValueInput.getText().trim());
                boolean found = false;

                for (int i = 0; i < currentSize; i++) {
                    if (array[i] == searchValue) {
                        textIndex.setText("Found at index: " + i);
                        textResult.setText("Value: " + searchValue);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    textIndex.setText("Not found");
                    textResult.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid search value");
            }
        });

        btnReset.addActionListener(e -> {
            array = null;
            currentSize = 0;
            textArraySizeInput.setText("");
            textArrayElementsInput.setText("");
            textSearchValueInput.setText("");
            textIndex.setText("");
            textArrayElements.setText("");
            textResult.setText("");
            textArrayElementsInput.setEnabled(false);
            textSearchValueInput.setEnabled(false);
            btnAddElements.setEnabled(false);
            btnSearch.setEnabled(false);
        });
    }

    private String arrayToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentSize; i++) {
            sb.append(array[i]);
            if (i < currentSize - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }


    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                // Load the image from file
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
                // Fallback to a solid color if image fails to load
                setBackground(new Color(240, 240, 240));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the background image scaled to fit the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}