package com.example;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class BinarySearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textArraySizeInput;
	private JTextField textArrayElementsInput;
	private JTextField textSearchValueInput;
	private JTextArea textArrayElements;
	private JTextArea textIndex;
	private JTextArea textResult;
	private JButton btnCreateArray;
	private JButton btnAddElements;
	private JButton btnSearch;
	private JButton btnReset;

	private int[] array;
	private int currentSize = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BinarySearch frame = new BinarySearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BinarySearch() {
		setTitle("BinarySearch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
        contentPane = new BackgroundPanel("C:/Users/areva/OneDrive/Desktop/BinarySearch.png"); // Replace with your image path 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		getContentPane().setLayout(null);
		

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
		btnCreateArray.setBounds(430, 34, 123, 27);
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
		btnAddElements.setBounds(430, 86, 174, 27);
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

		textArrayElements = new JTextArea();
		textArrayElements.setBounds(41, 230, 192, 36);
		textArrayElements.setEditable(false);
		contentPane.add(textArrayElements);

		textIndex = new JTextArea();
		textIndex.setBounds(256, 230, 156, 36);
		textIndex.setEditable(false);
		contentPane.add(textIndex);

		textResult = new JTextArea();
		textResult.setBounds(430, 230, 114, 36);
		textResult.setEditable(false);
		contentPane.add(textResult);

		btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(255, 0, 0));
		btnReset.setBounds(256, 311, 114, 42);
		contentPane.add(btnReset);
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
				textArrayElementsInput.setText(""); // âœ… Clear the input after adding
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

				int low = 0;
				int high = currentSize - 1;
				while (low <= high) {
					int mid = (low + high) / 2;
					if (array[mid] == searchValue) {
						textIndex.setText("Found at index: " + mid); 
                        textResult.setText("Value: " + searchValue);
						found = true;
						break;
					} else if (searchValue < array[mid]) {
						high = mid - 1;
					} else {
						low = mid + 1;
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
		System.out.println("Error loading background image: " + 
		e.getMessage());
		setBackground(new Color(240, 240, 240)); 
		} 
		} 
		// Fallback to a solid color if image fails to load 
		
		@Override 
		protected void paintComponent(Graphics g) { 
		super.paintComponent(g); 
		if (backgroundImage != null) { 
		// Draw the background image scaled to fit the panel 
		g.drawImage(backgroundImage, 0, 0, getWidth(), 
		getHeight(), this); 
		} 
		} 
		} 
}
