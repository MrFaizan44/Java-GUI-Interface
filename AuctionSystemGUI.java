import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Item {
    int id;
    String name;
    int highestBid;
    String highestBidder;
    ArrayList<String> bidders;

    public Item(int id, String name, int startingBid, String initialBidder) {
        this.id = id;
        this.name = name;
        this.highestBid = startingBid;
        this.highestBidder = initialBidder;
        this.bidders = new ArrayList<>();
        if (!initialBidder.equals("None")) {
            this.bidders.add(initialBidder);
        }
    }

    public void placeBid(String bidderName, int bidAmount) {
        highestBid = bidAmount;
        highestBidder = bidderName;
        bidders.add(bidderName);
    }

    public String getBidderList() {
        if (bidders.isEmpty()) return "No bidders yet.";
        StringBuilder sb = new StringBuilder();
        for (String b : bidders) {
            sb.append("- ").append(b).append("\n");
        }
        return sb.toString();
    }

    public String getDetails() {
        return "ID: " + id +
                " | " + name +
                " | Current Bid: " + highestBid +
                " | Top Bidder: " + highestBidder +
                "\nBidders:\n" + getBidderList();
    }

    public String getSummary() {
        return id + ". " + name + " | Current Bid: " + highestBid + " | Top Bidder: " + highestBidder;
    }
}

public class AuctionSystemGUI {
    private JFrame frame;
    private ArrayList<Item> items = new ArrayList<>();
    private int itemIdCounter = 4;

    public AuctionSystemGUI() {
        items.add(new Item(1, "Laptop", 5000, "Ali"));
        items.add(new Item(2, "Smartphone", 3000, "Sara"));
        items.add(new Item(3, "Headphones", 1500, "Usman"));
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Online Auction System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] options = {
                "View Items (with Bidders)",
                "Place Bid",
                "Admin - Add Item",
                "Contact Us",
                "Exit"
        };

        JList<String> menuList = new JList<>(options);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setFont(new Font("Arial", Font.PLAIN, 16));
        menuList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = menuList.locationToIndex(e.getPoint());
                switch (index) {
                    case 0: viewItemsWithBidders(); break;
                    case 1: placeBid(); break;
                    case 2: adminAddItem(); break;
                    case 3: contactUs(); break;
                    case 4: System.exit(0); break;
                }
            }
        });

        frame.add(new JLabel("Select an Option:", SwingConstants.CENTER), BorderLayout.NORTH);
        frame.add(new JScrollPane(menuList), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void viewItemsWithBidders() {
        StringBuilder sb = new StringBuilder("Auction Items (with Bidders):\n\n");
        for (Item item : items) {
            sb.append(item.getDetails()).append("\n-----------------------------\n");
        }
        showMessage(sb.toString());
    }

    private void placeBid() {
        if (items.isEmpty()) {
            showMessage("No items available to bid.");
            return;
        }

        StringBuilder list = new StringBuilder("Available Items:\n\n");
        for (Item item : items) {
            list.append(item.getSummary()).append("\n");
        }

        String input = JOptionPane.showInputDialog(frame, list + "\nEnter Item ID to bid:");
        if (input == null) return;

        try {
            int id = Integer.parseInt(input);
            Item item = getItemById(id);
            if (item == null) {
                showMessage("Invalid Item ID.");
                return;
            }

            String name = JOptionPane.showInputDialog(frame, "Enter your name:");
            if (name == null || name.isEmpty()) return;

            int minBid = item.highestBid + 1;
            String bidInput = JOptionPane.showInputDialog(frame,
                    "Current highest bid: " + item.highestBid +
                            "\nEnter your bid (must be > " + item.highestBid + "):");
            if (bidInput == null) return;

            int bid = Integer.parseInt(bidInput);
            if (bid > item.highestBid) {
                item.placeBid(name, bid);
                showMessage("✅ Bid placed successfully!\nNew highest bid is " + bid + " by " + name + ".");
            } else {
                showMessage("❌ Bid must be higher than current highest bid.");
            }

        } catch (NumberFormatException e) {
            showMessage("Invalid input. Please enter numeric values where required.");
        }
    }

    private void adminAddItem() {
        String password = JOptionPane.showInputDialog(frame, "Enter Admin Password:");
        if (!"admin123".equals(password)) {
            showMessage("Incorrect password.");
            return;
        }

        String name = JOptionPane.showInputDialog(frame, "Enter Item Name:");
        if (name == null || name.isEmpty()) return;

        try {
            int price = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter Starting Price:"));
            items.add(new Item(itemIdCounter++, name, price, "None"));
            showMessage("✅ Item added successfully!");
        } catch (NumberFormatException e) {
            showMessage("Invalid price. Please enter a valid number.");
        }
    }

    private void contactUs() {
        showMessage("📞 Contact Us:\n" +
                "Email: listyouritem@auction.com\n" +
                "Phone: +92 (123) 456-7890\n" +
                "Website: www.onlineauction.com");
    }

    private Item getItemById(int id) {
        return items.stream().filter(i -> i.id == id).findFirst().orElse(null);
    }

    private void showMessage(String message) {
        JTextArea area = new JTextArea(message);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        JOptionPane.showMessageDialog(frame, scrollPane);
    }

    public static void main(String[] args) {
        new AuctionSystemGUI();
    }
}
