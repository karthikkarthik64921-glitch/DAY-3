
import java.util.LinkedList;

public class RecentlyViewedProducts {
    public static void main(String[] args) {

        LinkedList<String> products = new LinkedList<>();

        // 1. Store Laptop, Mobile, Headphone, Smartwatch
        products.add("Laptop");
        products.add("Mobile");
        products.add("Headphone");
        products.add("Smartwatch");

        System.out.println("1. Products Added: " + products);

        // 2. Add Laptop again (avoid duplicates)
        if (!products.contains("Laptop")) {
            products.add("Laptop");
        } else {
            System.out.println("2. Laptop already exists. Duplicate not added.");
        }

        System.out.println("   Products: " + products);

        // 3. Display all products
        System.out.println("3. All Products: " + products);

        // 4. Remove Headphone
        products.remove("Headphone");
        System.out.println("4. After Removing Headphone: " + products);

        // 5. Add Tablet
        if (!products.contains("Tablet")) {
            products.add("Tablet");
        }
        System.out.println("5. After Adding Tablet: " + products);

        // 6. Check whether Mobile exists
        System.out.println("6. Does Mobile Exist? "
                + products.contains("Mobile"));

        // 7. Display final product list
        System.out.println("7. Final Product List: " + products);
    }
}
