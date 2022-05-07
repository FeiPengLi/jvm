package cn.example.ch2.forkjoin.sort;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.forkjoin.sort
 * ClassName: InsertionSort
 *
 * @author: 李朋飞
 * @time: 2021/12/25 11:47
 * 简单插入排序
 **/
public class InsertionSort {

    public static int[] sort(int[] array){
        if (array.length==0){
            return array;
        }
        int currentValue;//当前待排序的数据，该元素之前的元素都已排序
        for (int i=0;i<array.length-1;i++){
            int preIndex=i;//已被排序的数据索引
            currentValue=array[preIndex+1];
            while(preIndex>=0&&currentValue<array[preIndex]){
                array[preIndex+1]=array[preIndex];
                preIndex--;
            }
            array[preIndex+1]=currentValue;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a=new int[]{2,6,7,3,0,4,8 };
        sort(a);
    }
}
