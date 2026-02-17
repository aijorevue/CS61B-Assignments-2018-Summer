package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        int N = oomages.size();
        double max = N / 2.5;
        double min = (double) N / 50;
        int[] buckets = new int[M];
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum]++;
        }
        for (int num : buckets) {
            if (num < min || num > max) {
                return false;
            }
        }
        return true;
    }
}
