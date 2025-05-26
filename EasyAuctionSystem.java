import javax.swing.*;
import java.util.ArrayList;

class Item {
    int id;
    String name;
    int highestBid;
    String highestBidder;
    ArrayList<String> bidders = new ArrayList<>();

    public Item(int id, String name, int startingBid, String firstBidder) {
        this.id = id;
        this.name = name;
        this.highestBid = startingBid;
        this.highestBidder = firstBidder;
        bidders.add(firstBidder);
    }

    public void placeBid(String bidder, int amount) {
        highestBid = amount;
        highestBidder = bidder;
        bidders.add(bidder);
    }

    public String getInfo() {
        return "ID: " + id + " | " + name + " | Bid: " + highestBid + " by " + highestBidder;
    }

    public String getBiddersList() {
        StringBuilder sb = new StringBuilder("Bidders for " + name + ":\n");
        for (String bidder : bidders) {
            sb.append("- ").append(bidder).append("\n");
        }
        return sb.toString();
    }
}

public class EasyAuctionSystem {
    ArrayList<Item> items = new ArrayList<>();

    public EasyAuctionSystem() {
        // Initial items
        items.add(new Item(1, "Laptop", 5000, "Ali"));
        items.add(new Item(2, "Phone", 3000, "Sara"));
        items.add(new Item(3, "Headphones", 1500, "Usman"));

        showMenu();
    }

    void showMenu() {
        while (true) {
            String[] options = {"View Items", "Place a Bid", "View Bidders", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Select an option:",
                    "Simple Auction", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            switch (choice) {
                case 0 -> viewItems();
                case 1 -> placeBid();
                case 2 -> viewBidders();
                case 3, -1 -> {
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Invalid choice!");
            }
        }
    }

    void viewItems() {
        StringBuilder sb = new StringBuilder("Auction Items:\n\n");
        for (Item item : items) {
            sb.append(item.getInfo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Items", JOptionPane.INFORMATION_MESSAGE);
    }

    void placeBid() {
        viewItems(); // Show items before bidding

        String inputId = JOptionPane.showInputDialog("Enter item ID to bid on:");
        if (inputId == null) return;

        int id;
        try {
            id = Integer.parseInt(inputId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID!");
            return;
        }

        Item item = getItemById(id);
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item not found.");
            return;
        }

        String name = JOptionPane.showInputDialog("Your name:");
        if (name == null || name.isEmpty()) return;

        String bidInput = JOptionPane.showInputDialog("Current bid: " + item.highestBid +
                "\nEnter your bid (must be higher):");
        if (bidInput == null) return;

        int bid;
        try {
            bid = Integer.parseInt(bidInput);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid bid.");
            return;
        }

        if (bid > item.highestBid) {
            item.placeBid(name, bid);
            JOptionPane.showMessageDialog(null, "Bid placed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Your bid must be higher than " + item.highestBid);
        }
    }

    void viewBidders() {
        String inputId = JOptionPane.showInputDialog("Enter item ID to view bidders:");
        if (inputId == null) return;

        int id;
        try {
            id = Integer.parseInt(inputId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid ID!");
            return;
        }

        Item item = getItemById(id);
        if (item == null) {
            JOptionPane.showMessageDialog(null, "Item not found.");
            return;
        }

        JOptionPane.showMessageDialog(null, item.getBiddersList(), "Bidders", JOptionPane.INFORMATION_MESSAGE);
    }

    Item getItemById(int id) {
        for (Item item : items) {
            if (item.id == id) return item;
        }
        return null;
    }

    public static void main(String[] args) {
        new EasyAuctionSystem();
    }
}
