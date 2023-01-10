import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "product.csv";
        Scanner scanner=new Scanner(System.in);
        ProductManager productManager = new ProductManager(new Product());
        int choice = -1;
        do{
            try{
                System.out.println("M_E_N_U:");
                System.out.println("Please choose your choice:");
                System.out.println("1. Show Product");
                System.out.println("2. Create new Product");
                System.out.println("3. Edit Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Sort Product");
                System.out.println("6. Find Product");
                System.out.println("7. Read File");
                System.out.println("8. Write File");
                System.out.println("9. Exit");
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        productManager.displayProduct();
                        break;
                    case 2:
                        productManager.addProduct(scanner);
                        break;
                    case 3:
                        productManager.editProduct(scanner);
                        break;
                    case 4:
                        productManager.deleteProduct(scanner);
                        break;
                    case 5:
                        productManager.sortProduct();
                        break;
                    case 6:
                        productManager.findProduct();
                        break;
                    case 7:
                        productManager.read(path);
                        break;
                    case 8:
                        productManager.writeCsv(productManager.listProduct,path);
                        break;
                    case 9:
                        System.out.println("bye");
                        System.exit(0);
                        break;
                }
                if(choice>9){
                    System.out.println("Please choose again");
                }
            }catch (NumberFormatException a){
                a.getMessage();
                System.out.println("Please enter again");
            }
        }while (choice!=0);
    }
}