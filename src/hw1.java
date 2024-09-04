import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//By Matthew Arenivas
public class hw1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        /*This is the title and subtitle of my gui*/

        Text app_layout = new Text("Application UI Layout:");
        app_layout.setFont(Font.font("System", FontWeight.BOLD, 20));

        Text title = new Text("Joe's Deli");
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        Text subtitle = new Text("By Matthew Arenivas");
        subtitle.setFont(Font.font("System", FontWeight.SEMI_BOLD, 10));

        /*==================================================================================*/

        /*Button Design and layout for the cancel, order and confirm and the Bill text area*/
        Button orderButton = new Button("Order");
        Button cancelButton = new Button("Cancel");
        Button confirmButton = new Button("Confirm");

        Border buttonBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT));
        orderButton.setBorder(buttonBorder);
        cancelButton.setBorder(buttonBorder);
        confirmButton.setBorder(buttonBorder);

        HBox orderBox = new HBox(orderButton, cancelButton, confirmButton);
        orderBox.setSpacing(10);
        orderBox.setAlignment(Pos.CENTER);

        TextArea billArea = new TextArea();
        billArea.setEditable(false);
        billArea.setPrefHeight(200);
        billArea.setPrefWidth(300);

        billArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        /*=====================================================================================*/

        /*Food item area and design such as Eat and Drink checkboxes and buttons
          Also the layout for them as well*/

        Label eatLabel = new Label("Eat:");
        eatLabel.setFont(Font.font("System", FontWeight.BOLD,15));
        CheckBox eggSandwich = new CheckBox("Egg Sandwich");
        CheckBox chickenSandwich = new CheckBox("Chicken Sandwich");
        CheckBox bagel = new CheckBox("Bagel");
        CheckBox potatoSalad = new CheckBox("Potato salad");

        Label drinkLabel = new Label("Drink:");
        drinkLabel.setFont(Font.font("System", FontWeight.BOLD,15));
        ToggleGroup drinksGroup = new ToggleGroup();
        RadioButton blackTea = new RadioButton("Black Tea");
        blackTea.setToggleGroup(drinksGroup);
        RadioButton greenTea = new RadioButton("Green Tea");
        greenTea.setToggleGroup(drinksGroup);
        RadioButton coffee = new RadioButton("Coffee");
        coffee.setToggleGroup(drinksGroup);
        RadioButton orangeJuice = new RadioButton("Orange Juice");
        orangeJuice.setToggleGroup(drinksGroup);

        VBox foodBox = new VBox(eatLabel, eggSandwich, chickenSandwich, bagel, potatoSalad);
        foodBox.setSpacing(10);
        foodBox.setPadding(new Insets(10));

        VBox drinkBox = new VBox(drinkLabel, blackTea, greenTea, coffee, orangeJuice);
        drinkBox.setSpacing(10);
        drinkBox.setPadding(new Insets(10));

        /*=====================================================================================*/


        /*This is where I put all the Gui components together to the desired design given in the instructions*/

        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(20));

        HBox topContent = new HBox(20, foodBox, drinkBox, billArea);
        topContent.setAlignment(Pos.TOP_CENTER);

        mainBox.getChildren().addAll(title, subtitle, topContent, orderBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        VBox root = new VBox(20, app_layout, mainBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        /*=====================================================================================*/

        // Event Handling for Buttons
        orderButton.setOnAction(e -> {
            double total = 0;
            StringBuilder bill = new StringBuilder("Bill:\n");

            // Food selection
            if (eggSandwich.isSelected()) {
                total += 7.99;
                bill.append("Egg Sandwich: $7.99\n");
            }
            if (chickenSandwich.isSelected()) {
                total += 9.99;
                bill.append("Chicken Sandwich: $9.99\n");
            }
            if (bagel.isSelected()) {
                total += 2.50;
                bill.append("Bagel: $2.50\n");
            }
            if (potatoSalad.isSelected()) {
                total += 4.49;
                bill.append("Potato Salad: $4.49\n");
            }

            // Drink selection
            RadioButton selectedDrink = (RadioButton) drinksGroup.getSelectedToggle();
            if (selectedDrink != null) {
                String drink = selectedDrink.getText();
                switch (drink) {
                    case "Black Tea":
                        total += 1.25;
                        bill.append("Black Tea: $1.25\n");
                        break;
                    case "Green Tea":
                        total += 0.99;
                        bill.append("Green Tea: $0.99\n");
                        break;
                    case "Coffee":
                        total += 1.99;
                        bill.append("Coffee: $1.99\n");
                        break;
                    case "Orange Juice":
                        total += 2.25;
                        bill.append("Orange Juice: $2.25\n");
                        break;
                }
            }

            // Calculate total with tax
            double tax = total * 0.07;
            double finalTotal = total + tax;
            bill.append("\nTax: $").append(String.format("%.2f", tax));
            bill.append("\nTotal: $").append(String.format("%.2f", finalTotal));

            // Display the bill
            billArea.setText(bill.toString());
        });

        cancelButton.setOnAction(e -> {
            // Clear selections
            eggSandwich.setSelected(false);
            chickenSandwich.setSelected(false);
            bagel.setSelected(false);
            potatoSalad.setSelected(false);
            drinksGroup.selectToggle(null);
            // Clear the bill
            billArea.clear();
        });

        confirmButton.setOnAction(e -> {
            // Keep the bill but clear the selections
            eggSandwich.setSelected(false);
            chickenSandwich.setSelected(false);
            bagel.setSelected(false);
            potatoSalad.setSelected(false);
            drinksGroup.selectToggle(null);
        });

        // Creating the Scene and showing the Stage
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Joe's Deli Order System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}








