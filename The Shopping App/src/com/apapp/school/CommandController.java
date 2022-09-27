package com.apapp.school;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  you can see the purchaser of the sold items by viewing the edit screen of the individual customers
 * */

public class CommandController {
    public ArrayList<Product> products = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();
    public ArrayList<Seller> sellers = new ArrayList<>();
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public CommandController() {
        commandsHome();
    }

    public void commandsHome() {
        loadData("products.txt");
        loadData("customers.txt");
        loadData("sellers.txt");
        loadData("transactions.txt");

        System.out.println("Home Commands list:");
        System.out.println("---------------------------------------");
        System.out.println("View Inventory: inv");
        System.out.println("View Customers: cust");
        System.out.println("View Seller: sell");
        System.out.println("Edit Inventory: e_inv");
        System.out.println("Edit Customers: e_cust");
        System.out.println("Edit Sellers: e_sell");
        System.out.println("Sales Transaction: trans");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "inv" -> viewInventory();
            case "cust" -> viewCustomers();
            case "sell" -> viewSellers();
            case "e_inv" -> editInventory();
            case "e_cust" -> editCustomers();
            case "e_sell" -> editSellers();
            case "trans" -> transaction();
            default -> {
                System.out.println("Invalid command!");
                commandsHome();
            }
        }
    }

    public void editInventoryCommandsList() {
        System.out.println("Edit Inventory Commands list:");
        System.out.println("Do not include 'quotes' in commands");
        System.out.println("---------------------------------------");
        System.out.println("Add Product: add_'product name'_'seller'_'description'_'price'");
        System.out.println("Remove Product: rem_'product name'");
        System.out.println("View Inventory: inv");
        System.out.println("Edit Product: e_'product name'");
        System.out.println("Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

    }

    public void editProductCommandsList() {
        System.out.println("Edit Product Commands list:");
        System.out.println("Do not include 'quotes' in commands");
        System.out.println("---------------------------------------");
        System.out.println("View Current Info: view");
        System.out.println("Change Name: name_'new name'");
        System.out.println("Change Price: price_'new price'");
        System.out.println("Change Description: desc_'new description'");
        System.out.println("Change Seller: sell_'new seller'");
        System.out.println("Remove Product: rem");
        System.out.println("Back: back");
        System.out.println("Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

    }

    public void editCustomerCommandsList() {
        System.out.println("Edit Customer Commands list:");
        System.out.println("Do not include 'quotes' in commands");
        System.out.println("---------------------------------------");
        System.out.println("Add Customer: add_'customer name'");
        System.out.println("Remove Customer: rem_'customer name'");
        System.out.println("Edit Customer: e_'customer name'");
        System.out.println("View Customers: view");
        System.out.println("Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");
    }

    public void editSellersCommandsList() {
        System.out.println("Edit Sellers Commands list:");
        System.out.println("Do not include 'quotes' in commands");
        System.out.println("---------------------------------------");
        System.out.println("Add Seller: add_'seller name'");
        System.out.println("Remove Seller: rem_'seller name'");
        System.out.println("View Sellers: view");
        System.out.println("Edit Seller: e_'seller name'");
        System.out.println("Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");
    }

    public void editSellerCommandsList(Seller seller) {
        System.out.println("Edit Seller Commands list:");
        System.out.println("Do not include 'quotes' in commands");
        System.out.println("---------------------------------------");
        System.out.println(seller.getName() + "    |    " + seller.getProducts().size() + " Product(s) Listed    |    " + seller.getProductsSold().size() + " Product(s) Sold");
        System.out.println("---------------------------------------");
        System.out.println("Change Name: name_'seller name'");
        System.out.println("Remove Seller: rem");
        System.out.println("View Sellers: view");
        System.out.println("Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");
    }

    public void transactionCommandsList() {
        System.out.println("Transaction Commands list:");
        System.out.println("Do not include 'quotes' in commands");
        System.out.println("---------------------------------------");
        System.out.println("Sell Product: s_'product name'_'customer name'");
        System.out.println("See past Transactions: hist");
        System.out.println("Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");
    }

    public void viewInventory() {
        System.out.println("Name    |   Description     |   Price   |   Seller   |   Date Acquired");
        for (Product product : products) {
            System.out.print(product.getName());
            System.out.print("  |   ");
            System.out.print(product.getDescription());
            System.out.print("  |    ");
            System.out.print(product.getPrice());
            System.out.print("  |    ");
            System.out.print(product.getSeller().getName());
            System.out.print("  |   ");
            System.out.println(product.getDate());
        }
        System.out.println("---------------------------------------");
        System.out.println("Edit Inventory: edit");
        System.out.println("Edit Product: e_'product'");
        System.out.println("Go to Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("home")) {
            commandsHome();
        } else if (input.equals("edit")) {
            editInventory();
        } else if (input.contains("e_")) {
            String[] splitInput = input.split("_");
            boolean exists = false;
            for (Product product: products) {
                if (splitInput[1].equalsIgnoreCase(product.getName())) {
                    exists = true;
                    editProduct(product, products.indexOf(product));
                }
            }
            if (!exists) {
                System.out.println("Product does not exist!");
                viewInventory();
            }
        } else {
            System.out.println("Invalid command!");
            viewInventory();
        }
    }

    public void viewProduct(Product product, int indexInArray) {
        System.out.println("____________________________");
        System.out.print(product.getName());
        System.out.print("  | ");
        System.out.print(product.getPrice());
        System.out.print("  | ");
        System.out.println(product.getDate());

        if (product.getDescription() != null) {
            System.out.println(product.getDescription());
        } else {
            System.out.println("No Description available");
        }
        if (product.getSeller() != null) {
            System.out.print("Seller: ");
            System.out.println(product.getSeller().getName());
        }
        System.out.println("_______________________________________");
        System.out.println("Edit Product: edit");
        System.out.println("Go to Home: home");
        System.out.println("Edit Inventory: e_inv");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "home" -> commandsHome();
            case "edit" -> editProduct(product, indexInArray);
            case "e_inv" -> editInventory();
            default -> {
                System.out.println("Invalid command!");
                viewProduct(product, indexInArray);
            }
        }
    }

    public void viewTransactions() {
        System.out.println("Product   |   Customer   |   Price   |   Date");
        for (Transaction transaction : transactions) {
            System.out.print(transaction.getProductName());
            System.out.print("   |   ");
            System.out.print(transaction.getCustomerName());
            System.out.print("   |   ");
            System.out.print(transaction.getAmount());
            System.out.print("   |   ");
            System.out.println(transaction.getDate());
        }
        System.out.println("____________________________");
        System.out.println("Transaction Menu: trans");
        System.out.println("Go to Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("home")) {
            commandsHome();
        } else if (input.equals("trans")) {
            transaction();
        } else {
            System.out.println("Invalid command!");
            viewTransactions();
        }
    }

    public void viewCustomers() {
        System.out.println("Name   |   Items Purchased   |   Date Added");
        System.out.println("____________________________");

        for (Customer customer: customers) {
            System.out.print(customer.getName());
            System.out.print("   |   " + customer.getProductsPurchased().size());
            System.out.println("   |   " + customer.getDate());
        }
        System.out.println("---------------------------------------");
        System.out.println("Edit Customers: edit");
        System.out.println("Edit Customer: e_'customer'");
        System.out.println("Go to Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split("_");

        if (input.equals("home")) {
            commandsHome();
        } else if (input.contains("e_")) {
            boolean exists = false;
            Customer toPass = new Customer();
            for (Customer customer: customers) {
                if (splitInput[1].equalsIgnoreCase(customer.getName())) {
                    exists = true;
                    toPass = customer;
                    break;
                }
            }
            if (exists) {
                editCustomer(toPass);
            } else {
                System.out.println("Invalid Customer!");
                viewCustomers();
            }
        } else if (input.equals("edit")) {
            editCustomers();
        } else {
            System.out.println("Invalid command!");
            viewCustomers();
        }
    }

    public void viewCustomer(Customer customer) {
        System.out.println(customer.getName() + "                 " + customer.getDate());
        System.out.println("Products purchased:    " + customer.getProductsPurchased().size());
        System.out.println("---------------------------------------");
        System.out.println("Name    |   Description     |   Price   |   Seller   |   Date Acquired");
        for (Product product : customer.getProductsPurchased()) {
            System.out.print(product.getName());
            System.out.print("  |   ");
            System.out.print(product.getDescription());
            System.out.print("  |    ");
            System.out.print(product.getPrice());
            System.out.print("  |    ");
            System.out.print(product.getSeller().getName());
            System.out.print("  |   ");
            System.out.println(product.getDate());
        }
        System.out.println("---------------------------------------");
        System.out.println("Change Name: name_'new name'");
        System.out.println("Edit Customers: edit");
        System.out.println("View Customers: view");
        System.out.println("Go to Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");
    }

    public void viewSellers() {
        System.out.println("Name    |   Product(s) Listed    |   Product(s) Sold   |   Date Added");
        for (Seller seller : sellers) {
            System.out.print(seller.getName());
            System.out.print("  |   ");
            for (Product product: seller.getProducts()) {
                System.out.print(product.getName() + ", ");
            }
            System.out.print("  |    ");
            for (Product product: seller.getProductsSold()) {
                System.out.print(product.getName() + ", ");
            }
            System.out.print("  |    ");
            System.out.println(seller.getDateAdded());
        }
        System.out.println("---------------------------------------");
        System.out.println("Edit Sellers: edit");
        System.out.println("Edit Seller: e_'seller'");
        System.out.println("Go to Home: home");
        System.out.println("---------------------------------------");
        System.out.println("Enter a command: ");

        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splitInput = input.split("_");
        
        if (input.equals("home")) {
            commandsHome();
        } else if (input.equals("edit")) {
            editSellers();
        } else if (input.contains("e_")) {
            boolean exists = false;
            Seller toIndex = new Seller();
            for (Seller seller: sellers) {
                if (splitInput[1].equalsIgnoreCase(seller.getName())) {
                    exists = true;
                    toIndex = seller;
                    break;
                }
            }
            if (exists) {
                editSeller(toIndex);
            } else {
                System.out.println("Seller does not exist!");
                viewSellers();
            }
        } else {
            System.out.println("Invalid command!");
            viewSellers();
        }
    }

    public void editInventory() {
        editInventoryCommandsList();
        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.contains("add_")) {
            try {
                String[] splitInput = input.split("_");
                boolean exists = false;
                Seller toIndex = new Seller();
                for (Seller seller: sellers) {
                    if (splitInput[2].equalsIgnoreCase(seller.getName())) {
                        toIndex = seller;
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    double price = Double.parseDouble(splitInput[4]);
                    Product splitInputProduct = new Product(splitInput[1], toIndex, splitInput[3], price);
                    products.add(splitInputProduct);
                    toIndex.addProduct(splitInputProduct);
                    System.out.println("Product saved");
                    saveObject(products, "products.txt");
                    saveObject(sellers, "sellers.txt");
                    editInventory();
                } else {
                    System.out.println("Seller does not exist!");
                    editInventory();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Price!");
                editInventory();
            }

        } else if (input.contains("rem_")) {
            String[] splitInput = input.split("_");
            boolean exists = false;
            Product toIndex = new Product();
            for (Product product: products) { // Find product entered
                if (splitInput[1].equalsIgnoreCase(product.getName())) {
                    exists = true;
                    toIndex = product;
                    for (Seller seller: sellers) { // Find the seller who lists the product
                        for (Product prod: seller.getProducts()) {
                            if (product.getName().equals(prod.getName())) {
                                seller.removeProduct(prod);
                                saveObject(sellers, "sellers.txt");
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            if (exists) {
                products.remove(toIndex);
                saveObject(products, "products.txt");
                System.out.println("Product removed");
            } else {
                System.out.println("Invalid product entered!");
            }
            editInventory();
            
        } else if (input.equals("inv")) {
            viewInventory();

        } else if (input.contains("e_")) {
            String[] splitInput = input.split("_");
            boolean exists = false;
            Product productSend = null;
            int index = -1;

            for (Product product : products) {
                if (splitInput[1].equalsIgnoreCase(product.getName())) {
                    exists = true;
                    index = products.indexOf(product);
                    productSend = product;
                    break;
                }
            }
            if (exists) {
                editProduct(productSend, index);
            } else {
                System.out.println("Invalid product entered!");
                editInventory();
            }

        } else if (input.equals("home")) {
            commandsHome();
        } else {
            System.out.println("Invalid command!");
            editInventory();
        }
    }

    public void editProduct(Product product, int indexInArray) {
        editProductCommandsList();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splitInput = input.split("_");
        if (input.equals("view")) {
            viewProduct(product, indexInArray);
        } else if (input.contains("name_")) {
            boolean finished = false;
            for (Seller seller: sellers) { // change name in seller's listing
                for (Product prod: seller.getProducts()) {
                    if (product.getName().equals(prod.getName())) {
                        prod.setName(splitInput[1]);
                        saveObject(sellers, "sellers.txt");
                        finished = true;
                        break;
                    }
                    if(finished) {
                        break;
                    }
                }
            }
            product.setName(splitInput[1]);
            saveObject(products, "products.txt");
            editProduct(product, indexInArray);
        } else if (input.contains("price_")) {
            try {
                product.setPrice(Double.parseDouble(splitInput[1]));
                saveObject(products, "products.txt");
            } catch (NumberFormatException e) {
                System.out.println("Invalid Price!");
            }
            editProduct(product, indexInArray);
        } else if (input.contains("desc_")) {
            product.setDescription(splitInput[1]);
            saveObject(products, "products.txt");
            editProduct(product, indexInArray);
        } else if (input.equals("rem")) {
            boolean finished = false;
            for (Seller seller: sellers) { // remove from seller's listing
                for (Product prod: seller.getProducts()) {
                    if (product.getName().equals(prod.getName())) {
                        seller.removeProduct(prod);
                        saveObject(sellers, "sellers.txt");
                        finished = true;
                        break;
                    }
                    if (finished) {
                        break;
                    }
                }
            }
            products.remove(indexInArray);
            saveObject(products, "products.txt");
            viewInventory();
        } else if (input.contains("sell_")) {
            for (Seller seller: sellers) { // Find seller who is to list the product
                if (splitInput[1].equalsIgnoreCase(seller.getName())) {

                    for (Seller sell: sellers) { // Find the seller who previously listed the product
                        if (product.getSeller().getName().equalsIgnoreCase(sell.getName())) {

                            for (Product prod: sell.getProducts()) { // remove product from previous seller's listing
                                if (product.getName().equals(prod.getName())) {

                                    sell.removeProduct(prod);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    product.setSeller(seller);
                    seller.addProduct(product);
                    saveObject(products, "products.txt");
                    saveObject(sellers, "sellers.txt");
                    System.out.println("Saved.");
                    break;
                }
            }
            editProduct(product, indexInArray);
        } else if (input.equals("back")) {
            editInventory();
        } else if (input.equals("home")) {
            commandsHome();
        } else {
            System.out.println("Invalid command!");
            editProduct(product, indexInArray);
        }
    }

    public void editCustomers() {
        editCustomerCommandsList();
        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splitInput = input.split("_");

        if (input.contains("add_")) {
            customers.add(new Customer(splitInput[1]));
            saveObject(customers, "customers.txt");
            editCustomers();
        } else if (input.contains("rem_")) {
            boolean exists = false;
            for (Customer customer : customers) { // remove customer requested
                if (splitInput[1].equalsIgnoreCase(customer.getName())) {
                    exists = true;
                    customers.remove(customer);
                    saveObject(customers, "customers.txt");
                    System.out.println("Customer removed");
                    break;
                }
            }
            if (!exists) {
                System.out.println("Invalid customer entered!");
            }
            editCustomers();

        } else if (input.contains("e_")) {
            boolean exists = false;
            Customer toIndex = new Customer();
            for (Customer customer : customers) { // find customer requested
                if (splitInput[1].equalsIgnoreCase(customer.getName())) {
                    exists = true;
                    toIndex = customer;
                    break;
                }
            }
            if (exists) {
                editCustomer(customers.get(customers.indexOf(toIndex)));
                saveObject(customers, "customers.txt");
                System.out.println("Customer removed");
            } else {
                System.out.println("Invalid customer entered!");
            }
            editCustomers();
        } else if (input.equals("view")) {
            viewCustomers();
        } else if (input.contains("home")) {
            commandsHome();
        } else {
            System.out.println("Invalid command!");
            editCustomers();
        }
    }

    public void editCustomer(Customer customer) {
        viewCustomer(customer);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split("_");

        if (input.equals("home")) {
            commandsHome();

        } else if (input.equals("view")) {
            viewCustomers();

        } else if (input.equals("edit")) {
            editCustomers();

        } else if (input.contains("name_")) {
            customer.setName(splitInput[1]);
            System.out.println("Name changed!");
            saveObject(customers, "customers.txt");
            editCustomer(customer);

        } else {
            System.out.println("Invalid command!");
            editCustomer(customer);
        }
    }

    public void editSellers() {
        editSellersCommandsList();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split("_");

        if (input.equals("home")) {
            commandsHome();

        } else if (input.equals("view")) {
            viewSellers();
        } else if (input.contains("e_")) {
            boolean exists = false;
            for (Seller seller: sellers) { // find seller requested
                if (splitInput[1].equalsIgnoreCase(seller.getName())) {
                    exists = true;
                    editSeller(seller);
                    break;
                }
            }
            if (!exists) {
                System.out.println("Seller does not exist!");
                editSellers();
            }
        } else if (input.contains("add_")) {
            sellers.add(new Seller(splitInput[1]));
            saveObject(sellers, "sellers.txt");
            editSellers();
        } else if (input.contains("rem_")) {
            boolean sellerExists = false;
            boolean productsExist = false;
            for (Seller seller: sellers) { // find seller requested
                if (splitInput[1].equalsIgnoreCase(seller.getName())) {
                    for (Product product: products) { // determine if the seller has products listed
                        if (splitInput[1].equalsIgnoreCase(product.getSeller().getName())) {
                            productsExist = true;
                            break;
                        }
                    }
                    if (!productsExist) {
                        sellerExists = true;
                        sellers.remove(seller);
                        saveObject(sellers, "sellers.txt");
                        System.out.println("Seller Removed!");
                    } else {
                        System.out.println("Seller has products listed! Please remove/reassign these products before deleting the seller.");
                    }
                    break;
                }
            }
            if (sellerExists) {
                editSellers();
            } else {
                System.out.println("Seller does not exist!");
                editSellers();
            }
        } else {
            System.out.println("Invalid command!");
            editSellers();
        }
    }

    public void editSeller(Seller seller) {
        editSellerCommandsList(seller);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] splitInput = input.split("_");

        if (input.equals("home")) {
            commandsHome();
        } else if (input.equals("view")) {
            viewSellers();
        } else if (input.contains("name_")) {
            for (Product product: products) { // replace name for product in inventory
                if (product.getSeller().getName().equalsIgnoreCase(seller.getName())) {
                    product.getSeller().setName(splitInput[1]);
                    saveObject(products, "products.txt");
                }
            }
            seller.setName(splitInput[1]);
            saveObject(sellers, "sellers.txt");
            editSeller(seller);
        } else if (input.equals("rem")) {
            boolean productsExist = false;
            for (Product product: products) { // determine if the seller has products listed
                if (seller.getName().equals(product.getSeller().getName())) {
                    productsExist = true;
                    break;
                }
            }
            if (!productsExist) {
                sellers.remove(seller);
                saveObject(sellers, "sellers.txt");
            } else {
                System.out.println("Seller has products listed! Please remove/reassign these products before deleting the seller.");
            }
            viewSellers();
        } else {
            System.out.println("Invalid command!");
            editSeller(seller);
        }
    }

    public void transaction() {
        transactionCommandsList();
        Scanner scanner= new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splitInput = input.split("_");

        if (input.contains("s_")) {
            boolean customerExists = false;
            Customer customerToPass = new Customer();
            for (Customer customer: customers) { //  find the customer entered
                if (splitInput[2].equalsIgnoreCase(customer.getName())) {
                    customerExists = true;
                    customerToPass = customer;
                }
            }
            String sellerName = "";
            boolean productExists = false;
            Product forIndex = new Product();
            for (Product product: products) { // find the product entered
                if (splitInput[1].equalsIgnoreCase(product.getName())) {
                    productExists = true;
                    forIndex = product;
                    sellerName = product.getSeller().getName();
                    break;
                }
            }
            if (productExists) {
                if (customerExists) {
                    for (Seller seller: sellers) { // find seller associated with the listing
                        if (sellerName.equalsIgnoreCase(seller.getName())) {
                            for (Product prod: seller.getProducts()) { // remove product from listing
                                if (forIndex.getName().equals(prod.getName())) {
                                    seller.removeProduct(prod);
                                    break;
                                }
                            }
                            seller.addProductSold(forIndex);// add to productsSold
                            break;
                        }
                    }
                    customerToPass.addProductPurchased(forIndex);
                    transactions.add(new Transaction(forIndex, customerToPass));
                    products.remove(forIndex);
                    saveObject(transactions, "transactions.txt");
                    saveObject(products, "products.txt");
                    saveObject(sellers, "sellers.txt");
                    saveObject(customers, "customers.txt");
                    transaction();
                } else {
                    System.out.println("Customer does not exist!");
                    transaction();
                }

                System.out.println("Transaction recorded!");
            } else {
                System.out.println("Product entered does not exist");
            }
            transaction();
        } else if (input.equals("hist")) {
            viewTransactions();
        } else if (input.equals("home")) {
            commandsHome();
        } else {
            System.out.println("Invalid command!");
            transaction();
        }
    }

    public <E> void saveObject(ArrayList<E> objects, String filepath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objects);
            objectOut.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    public void loadData(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            switch (filepath) {
                case "products.txt" -> products = (ArrayList<Product>) objectIn.readObject();
                case "customers.txt" -> customers = (ArrayList<Customer>) objectIn.readObject();
                case "sellers.txt" -> sellers = (ArrayList<Seller>) objectIn.readObject();
                case "transactions.txt" -> transactions = (ArrayList<Transaction>) objectIn.readObject();
            }
            objectIn.close();

        } catch (FileNotFoundException e) {
            System.out.println("No Data.");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found error");
        }
    }
}
