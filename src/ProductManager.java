
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class ProductManager {
    Product product;
    public ArrayList<Product> listProduct;

    int id;

    public ProductManager(Product product) {
        listProduct = new ArrayList<>();
        this.product = product;
        if (!listProduct.isEmpty()) {
            id = listProduct.get(listProduct.size() - 1).getId();
        } else {
            id = 0;
        }
    }

    public Product create(Scanner scanner) {
        Product product1 = null;
        String name = null;
        double price = 0;
        int quantity = 0;
        String description = null;
        boolean check = false;
        id++;
        do {
            System.out.println("Enter name of product:");
            name = scanner.nextLine();
            if (!(name.equals(""))) {
                check = true;
            } else {
                System.out.println("Please enter again");
            }
        } while (!check);
        check = false;
        do {
            try {
                System.out.println("Enter price of product:");
                price = Double.parseDouble(scanner.nextLine());
                check = true;
            } catch (NumberFormatException e) {
                e.getMessage();
                System.out.println("----- Please enter again ------");
            }
        } while (!check);
        check = false;
        do {
            try {
                System.out.println("Enter quantity of product:");
                quantity = Integer.parseInt(scanner.nextLine());
                check = true;
            } catch (NumberFormatException a) {
                a.getMessage();
                System.out.println("----- Please enter again ------");
            }
        } while (!check);
        check = false;
        do {

            System.out.println("Enter description of product:");
            description = scanner.nextLine();
            if (!(description.equals(""))) {
                check = true;
            } else {
                System.out.println("Please enter again");
            }
            product1 = new Product(id, name, price, quantity, description);
        } while (!check);
        return product1;
    }

    public void addProduct(Scanner scanner) {
        Product addProduct = create(scanner);
        listProduct.add(addProduct);
    }

    public void displayProduct() {
        System.out.println("All product:");
        for (Product a : listProduct) {
            System.out.println(a);
        }
        if (listProduct.isEmpty()) {
            System.out.println("There is no product in your shop!");
        }
    }

    public void deleteProduct(Scanner scanner) {
        displayProduct();
        Product product2 = null;
        boolean check = false;
        do {
            try {
                System.out.println("Enter id of product you want to delete:");
                int productId = Integer.parseInt(scanner.nextLine());
                for (Product a : listProduct) {
                    if (productId == a.getId()) {
                        product2 = a;
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    System.out.println("ID can not found! Re-enter!");
                    return;
                }
                listProduct.remove(product2);
                System.out.println("Delete successful");
            } catch (NumberFormatException a) {
                a.getMessage();
                System.out.println("Please enter again");
            }
        } while (!check);
    }

    public void editProduct(Scanner scanner) {
        displayProduct();
        Product product3 = null;
        boolean check = false;
        do {
            try {
                System.out.println("Enter id of product you want to edit");
                int productId = Integer.parseInt(scanner.nextLine());
                for (Product e : listProduct) {
                    if (e.getId() == productId) {
                        product3 = e;
                        String name;
                        double price = 0.0;
                        int quantity = 0;
                        String description;
                        boolean frag = false;
                        do {
                            System.out.println("Enter new name of product:");
                            name = scanner.nextLine();
                            if (name.equals("")) {
                                e.setName(name);
                                frag = true;
                            }
                        } while (!frag);
                        frag = false;
                        do {
                            System.out.println("Enter new price of product:");
                            price = Double.parseDouble(scanner.nextLine());
                            e.setPrice(price);
                            frag = true;
                        } while (!frag);
                        frag = false;
                        do {
                            System.out.println("Enter new quantity of product:");
                            quantity = Integer.parseInt(scanner.nextLine());
                            e.setQuantity(quantity);
                            frag = true;
                        } while (!frag);
                        frag = false;
                        do {
                            System.out.println("Enter new description of product:");
                            description = scanner.nextLine();
                            if (description.equals("")) {
                                e.setDescription(description);
                                frag = true;
                            }
                        } while (!frag);
                    }
                }
                if (!check) {
                    System.out.println("Cannot found id");
                }
            } catch (Exception a) {
                a.getMessage();
                System.out.println("Please re-enter");
            }
            check = true;
        } while (!check);


    }

    public void findProduct() {
        boolean check = false;
        System.out.println("Product have highest price is:");
        double maxPrice = 0.0;
        for (Product e : listProduct) {
            if (e.getPrice() > maxPrice) {
                maxPrice = e.getPrice();
                check = true;
            }

        }
        for (Product a : listProduct) {
            if (a.getPrice() == maxPrice) {
                System.out.println(a);
                check = true;
            }

        }
        if (!check) {
            System.out.println("There is no product have highest price!!");
        }
    }

    public void sortProduct() {
        System.out.println("List product by up price: ");
        listProduct.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() > o2.getPrice() ? 1 : -1;
            }
        });
    }

    public void writeCsv(ArrayList<Product> listProduct, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Product a : listProduct) {
                bw.write(product.toString());
                bw.newLine();
            }
            bw.newLine();
            bw.close();
        } catch (IOException a) {
            throw new RuntimeException(a);
        }
    }

    public ArrayList<Product> read(String path) {
        ArrayList<Product> listProduct = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);
            String a = "";
            while ((a = br.readLine()) != null) {
                String[] b = a.split(";");
                String name = b[1];
                double price = Double.parseDouble(b[2]);
                int quantity = Integer.parseInt(b[3]);
                String description = b[4];
                listProduct.add(new Product(id, name, price, quantity, description));
            }
            br.close();
        } catch (Exception a) {
            a.getStackTrace();
        }
        return listProduct;
    }
}