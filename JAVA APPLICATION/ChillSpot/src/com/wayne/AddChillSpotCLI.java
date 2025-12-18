package com.wayne;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


// CLI for entering in Chill Spot information. Opens a window where user can input
// the name, building, floor level, seat number, and image src and upload it to
// the website.

public class AddChillSpotCLI {


        public static void main(String[] args) {


            // Creates frame and names it

            JFrame frame = new JFrame("Add Chill Spot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            // creates however many rows are needed automatically and 2 columns for TextField and TextBox

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 2, 8, 8));


            // creates text boxes for each variable

            JTextField nameField = new JTextField(20);
            JTextField buildingField = new JTextField(20);
            JTextField floorField = new JTextField(10);
            JTextField seatsField = new JTextField(5);
            JTextField imageUrlField = new JTextField(30);


            // adds labels for all of the text boxes

            panel.add(new JLabel("Name:"));
            panel.add(nameField);

            panel.add(new JLabel("Building:"));
            panel.add(buildingField);

            panel.add(new JLabel("Floor:"));
            panel.add(floorField);

            panel.add(new JLabel("Seats:"));
            panel.add(seatsField);

            panel.add(new JLabel("Image URL:"));
            panel.add(imageUrlField);


            // adds submit button for uploading to website

            JButton submitButton = new JButton("Submit");
            panel.add(new JLabel()); // spacer
            panel.add(submitButton);


            // creates repository that goes to the backend of our website and service stores the
            // repository in a final field

            ChillSpotRepository repo = new HttpChillSpotRepository("http://localhost:3000/insert");
            ChillSpotService service = new ChillSpotService(repo);



            // runs the following code when the submit button is pressed

            submitButton.addActionListener(e -> {
                try {

                    // gets all variables and prints them in the command line

                    String name = nameField.getText();
                    String building = buildingField.getText();
                    String floor = floorField.getText();
                    int seats = Integer.parseInt(seatsField.getText());
                    String imgURL = imageUrlField.getText();

                    System.out.println("Name: " + name);
                    System.out.println("Building: " + building);
                    System.out.println("Floor: " + floor);
                    System.out.println("Seats: " + seats);
                    System.out.println("Image URL: " + imgURL);


                    // creates new object "spot" with the final variables as the parameters

                    ChillSpot spot = new ChillSpot(
                            name,
                            building,
                            floor,
                            seats,
                            imgURL
                    );

                    // adds spot to repository

                    service.addSpot(spot);


                    // shows popup when a spot has been successfully added to the repository

                    JOptionPane.showMessageDialog(frame,
                            "Spot submitted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);


                    // catch and throws for incorrect inputs

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Seats must be a number",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // adds panel, auto sizes the panel to the content, and makes the panel visible
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }



    }


