import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.zip.CRC32;

public class BloomFilter {

    private final BitSet bitSet;
    private final int size;
    private final int[] seeds;

    public BloomFilter(int capacity, int[] seeds) {
        this.size = capacity;
        this.seeds = seeds;
        this.bitSet = new BitSet(size);
    }

    // Hash function using CRC32 with a seed
    private int hash(String data, int seed) {
        CRC32 crc32 = new CRC32();
        crc32.update((data + seed).getBytes(StandardCharsets.UTF_8));
        return Math.abs((int) crc32.getValue() % size);
    }

    // Add an element to the Bloom filter
    public void add(String data) {
        for (int seed : seeds) {
            int hash = hash(data, seed);
            bitSet.set(hash);
        }
    }

    // Check if an element might be in the set
    public boolean mightContain(String data) {
        for (int seed : seeds) {
            int hash = hash(data, seed);
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }
}
