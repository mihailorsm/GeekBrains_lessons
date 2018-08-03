import java.util.Random;

public class Algoritms implements Comparable {
    public static void main(String[] args) {
        int len = mas.length;
        Random rand = new Random();
        int mas = new int[10000000];
        for (int e : mas)
            e = rand.nextInt(50);
        boobleSort();
    }
    public boolean linaryFind(int item){
        int i;
        for(i=0; i < len; i++){
            if (mas[i] == item) break;
        }
        if (i == len)
            return false;
        else
            return true;
    }

    public boolean insert (int item){
        mas[len] = item;
        len++;
    }

    public boolean remove(int item){
        for(i=0;i<len;i++){
            if (mas[i] == search) break;
        }
        for (int j = i; j<len-1; j++){
            mas[j] = mas[j+1];
        }
        len--;
    }

    public void boobleSort(){
        int out, in;
        for (out = len - 1; out > 1; out--){
            for(in=0; in < out; in++){
                if (mas[in] > mas[in+1]){
                    change(in, in+1);
                }
            }
        }

    }

    public void selectedSort(){
        int out, in, mark;
        for(out=0;out<len;out++){
            mark = out;
            for(in = out+1;in<len;in++){
                if (mas[in] < mas[mark]){
                    mark = in;
                }
            }
            change(out, mark);
        }
    }

    public boolean binaryFind(int item){
        int low = 0;
        int high = len-1;
        int mid;
        while(low < high){
            mid = (low+high)/2;
            if (item == mas[mid]){
                return true;
            }
            else {
                if (item < mas[mid]){
                    high = mid;
                } else {
                    low = mid+1;
                }
            }
        }
        return false;

    }

    private void change(int a, int b){
        int tmp = mas[a];
        mas[a] = mas[b];
        mas[b] = tmp;
    }

    public void printMas(){
        for(int e: mas)
            System.out.println(e);
    }
}