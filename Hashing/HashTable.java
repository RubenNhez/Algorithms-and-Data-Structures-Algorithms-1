import java.util.Arrays;

public class HashTable {

    // public for testing purposes
    public int buckets[];


    long a, c, m;


    public HashTable(long _a, long _c, long _m) {

        this.a = _a;
        this.c = _c;
        this.m = _m;

        this.buckets = new int[(int) this.m];

        for (int i = 0; i < buckets.length; i++)
            buckets[i] = -1;
    }


    public int hash(int key) {
        return (int) ((this.a * key + this.c) % this.m);
    }

//    public void insert(int key) {
//        int i = (int)hash(key);
//
//        while(i < buckets.length && buckets[i] != 0){
//            i++;
//        }
//        if(i >= buckets.length){
//            extend();
//        }
//        buckets[i] = key;
//    }

    public void insert(int key) {

        if(key < 0) {
            return;
        }
        if(loadFactor()>=1) {
            extend();
        }
        int position = hash(key);
        m = buckets.length;
        for (int i = 0; i < m; i++) {
            if (buckets[(int) ((position + i) % m)] == -1) {
                buckets[(int) ((position + i) % m)] = key;
                break;
            }

        }
//        while (position != -1) {
//            if (buckets[position] == -1) {
//                buckets[position] = key;
//                break;
//            } else {
//                position++;
//            }
        //}
    }

    public void extend() {
        // Double the array size
        int[] temp = new int[buckets.length * 2];
        for (int i = 0; i < temp.length; i++) {
            if(i < buckets.length) {
                temp[i] = buckets[i];
            }
            else {
                temp[i] = -1;
            }
        }
        // Increase buckets size
        buckets = new int [temp.length];
        //Rehash
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = -1;
        }
        for (int i = 0; i < buckets.length; i++) {
            if(temp[i] != -1) {
                insert(temp[i]);
            }
            System.out.println("buckets: " + Arrays.toString(buckets));
            System.out.println("hash table size is " + m);
        }
    }

    public boolean find(int key) {
        if(key < 0) {
            return false;
        }
        double bucket = m;
        long k = hash(key);
        while(buckets[(int)k] != key || bucket ==0) {
            if(buckets[(int)k] == -1) {
                return false;
            }
            k++;
            bucket--;
        }
        return true;
    }

    public void remove(int key) {
        if(key < 0) {
            return;
        }
        boolean change = false;
        long k = hash(key);

        for (int i = 0; i < m; i++) {
            if(buckets[(int) ((k +i) % m)] == -1)
                break;
            else if (change) {
                int temp = buckets[(int) ((k+i) % m)];
                buckets[(int) ((k+i) % m)] = -1;
                insert(temp);
            }
            else if (buckets[(int)((k+i) % m)] == key) {
                buckets [(int) ((k+i) % m)] = -1;
                change = true;

            }

        }
    }

    public double loadFactor() {
        double filledbucket = 0;
        for(int bucket: buckets)
            if(bucket != -1)
                filledbucket++;
        return filledbucket/m;
    }
}
