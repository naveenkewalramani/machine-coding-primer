import java.util.Objects;
import java.util.function.Function;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int capacity = 1000;
        int[] seeds = {17, 31, 73, 127};

        // Create Bloom Filter
        BloomFilter bloomFilter = new BloomFilter(capacity, seeds);

        Scanner sc = new Scanner(System.in);

        System.out.println("Check URL If exist");
        while (true) {
            String url = sc.nextLine();
            if (Objects.equals(url, "exit")) {
                return;
            }
            boolean isPresent = bloomFilter.mightContain(url);
            if (isPresent) {
                System.out.println("URL is present in system, " + url);
            } else {
                bloomFilter.add(url);
                System.out.println("URL is not present in system, " + url);
            }
        }
    }
}