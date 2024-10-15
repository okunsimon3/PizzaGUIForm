import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIFrame extends JFrame {
    JComboBox<String> sizeComboBox;
    JCheckBox toppingCheckBoxes;
    JRadioButton thinCrustRadioBtn, regularCrustRadioBtn, deepDishRadioBtn;
    JTextArea orderSummary;
    JButton orderBtn, clearBtn, quitBtn;
    JPanel mainPanel;
    JPanel sizePanel;
    JPanel crustPanel;
    JPanel toppingsPanel;
    JPanel controlPanel;
    //Toppings Check Boxes
    JCheckBox goblinCB;
    JCheckBox unicornCB;
    JCheckBox yetiCB;
    JCheckBox newtCB;
    JCheckBox phoenixCB;
    JCheckBox dwarfCB;
    public PizzaGUIFrame() {
        mainPanel = new JPanel();
        setTitle("Pizza Order Form");

        createCrustPanel();
        mainPanel.add(crustPanel);

        createSizePanel();
        mainPanel.add(sizePanel);

        createToppingsPanel();
        mainPanel.add(toppingsPanel);

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderSummary = new JTextArea(8, 40);
        orderSummary.setEditable(false);
        summaryPanel.add(new JScrollPane(orderSummary), BorderLayout.CENTER);
        mainPanel.add(summaryPanel);

        createControlPanel();
        mainPanel.add(controlPanel);



        add(mainPanel);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }
    private void createSizePanel() {
        sizePanel = new JPanel();
        sizePanel.setBorder(new TitledBorder(new EtchedBorder(), "Size Options"));
        sizeComboBox = new JComboBox<>();
        sizeComboBox.addItem("Small - $8.00");
        sizeComboBox.addItem("Medium - $12.00");
        sizeComboBox.addItem("Large - $16.00");
        sizeComboBox.addItem("Super - $20.00");
        sizePanel.add(sizeComboBox);
    }
    private void createCrustPanel() {
        crustPanel = new JPanel();
        crustPanel.setLayout(new GridLayout(1,3));
        crustPanel.setBorder(new TitledBorder(new EtchedBorder(),"Crust Options"));

        thinCrustRadioBtn = new JRadioButton("Thin");
        regularCrustRadioBtn = new JRadioButton("Regular");
        deepDishRadioBtn = new JRadioButton("Deep-Dish");

        crustPanel.add(thinCrustRadioBtn);
        crustPanel.add(regularCrustRadioBtn);
        crustPanel.add(deepDishRadioBtn);

        regularCrustRadioBtn.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(thinCrustRadioBtn);
        group.add(regularCrustRadioBtn);
        group.add(deepDishRadioBtn);

    }
    private void createToppingsPanel() {
        toppingsPanel = new JPanel();
        toppingsPanel.setLayout(new GridLayout(2, 3));
        toppingsPanel.setBorder(new TitledBorder(new EtchedBorder(),"Topping Options"));

        goblinCB = new JCheckBox("Goblin Ears");
        unicornCB = new JCheckBox("Unicorn Dust");
        yetiCB = new JCheckBox("Yeti Hair");
        newtCB = new JCheckBox("Eye of Newt");
        phoenixCB = new JCheckBox("Phoenix Feathers");
        dwarfCB = new JCheckBox("Dwarf Weed");

        toppingsPanel.add(goblinCB);
        toppingsPanel.add(unicornCB);
        toppingsPanel.add(yetiCB);
        toppingsPanel.add(newtCB);
        toppingsPanel.add(phoenixCB);
        toppingsPanel.add(dwarfCB);


    }
    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));

        orderBtn = new JButton("Order");
        clearBtn = new JButton("Clear");
        quitBtn = new JButton("Quit");

        double[] sizePrices = {8.00, 12.00, 16.00, 20.00};
        final double taxRate = 0.07;

        orderBtn.addActionListener((ActionEvent ae) -> {
            StringBuilder res = new StringBuilder("Your Order:\n");
            res.append("==========================================\n");

            double subtotal = sizePrices[sizeComboBox.getSelectedIndex()];

            if (thinCrustRadioBtn.isSelected())
                res.append("Thin Crust ");
            else if (regularCrustRadioBtn.isSelected())
                res.append("Regular Crust ");
            else if (deepDishRadioBtn.isSelected())
                res.append("Deep-Dish Crust ");

            res.append((String) sizeComboBox.getSelectedItem()).append("\n");

            if (goblinCB.isSelected()) {
                res.append("Goblin Ears - $1.00\n");
                subtotal += 1.00;
            }
            if (unicornCB.isSelected()) {
                res.append("Unicorn Dust - $1.00\n");
                subtotal += 1.00;
            }
            if (yetiCB.isSelected()) {
                res.append("Yeti Hair - $1.00\n");
                subtotal += 1.00;
            }
            if (newtCB.isSelected()) {
                res.append("Eye of Newt - $1.00\n");
                subtotal += 1.00;
            }
            if (phoenixCB.isSelected()) {
                res.append("Phoenix Feathers - $1.00\n");
                subtotal += 1.00;
            }
            if (dwarfCB.isSelected()) {
                res.append("Dwarf Weed - $1.00\n");
                subtotal += 1.00;
            }

            double tax = subtotal * taxRate;
            double totalCost = subtotal + tax;

            res.append("\n==========================================\n");
            res.append(String.format("Subtotal: $%.2f\n", subtotal));
            res.append(String.format("Tax (7%%): $%.2f\n", tax));
            res.append("--------------------------------------------------------\n");
            res.append(String.format("Total: $%.2f\n", totalCost));
            res.append("==========================================\n");

            orderSummary.setText(res.toString());
        });
        clearBtn.addActionListener((ActionEvent ae) -> {
            sizeComboBox.setSelectedIndex(0);
            thinCrustRadioBtn.setSelected(false);
            regularCrustRadioBtn.setSelected(false);
            deepDishRadioBtn.setSelected(false);

            goblinCB.setSelected(false);
            unicornCB.setSelected(false);
            yetiCB.setSelected(false);
            newtCB.setSelected(false);
            phoenixCB.setSelected(false);
            dwarfCB.setSelected(false);

            orderSummary.setText("");
        });
        quitBtn.addActionListener((ActionEvent ae) -> {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to quit?", "Quit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        controlPanel.add(orderBtn);
        controlPanel.add(clearBtn);
        controlPanel.add(quitBtn);
    }


}
