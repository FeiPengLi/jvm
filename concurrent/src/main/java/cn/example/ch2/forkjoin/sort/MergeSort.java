package cn.example.ch2.forkjoin.sort;

import cn.example.ch2.forkjoin.sum.MakeArray;

import java.util.Arrays;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.forkjoin.sort
 * ClassName: MergeSort
 *
 * @author: 李朋飞
 * @time: 2021/12/25 13:18
 **/
public class MergeSort {

    public static int[]sort(int[] array){
        if (array.length<= MakeArray.ARRAY_LENGTH){
            return InsertionSort.sort(array);
        }else{
            //切分数组，递归调用
            int mid=array.length/2;
            int[]left= Arrays.copyOfRange(array,0,mid);
            int[]right=Arrays.copyOfRange(array,mid,array.length);
            return merge(sort(left),sort(right));

        }
    }

    public static int[] merge(int[] left,int[]right){
        int[]result=new int[left.length+right.length];
        for (int index=0,i=0,j=0; index<result.length;index++){
            if (i>=left.length){//左边的数组值取完了，完全取右边的数组值
                result[index]=right[j++];
            }else if (j>=right.length){//右边的数组取完了，完全取左边的数组值
                result[index]=left[i++];
            }else if (left[i]>right[j]){//左边的数组元素大于右边的，取右边的数组值
                result[index]=right[j++];
            }else//右边的元素大于左边的，取左边的数组值
                result[index] =left[i++];

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("===============================");

        int[] srot=MakeArray.makeArray();
        for (int i:srot){
            System.out.print(i+" ");
        }
        System.out.println();
        long start=System.currentTimeMillis();
        int[] sort=MergeSort.sort(srot);
        for (int i:sort){
            System.out.print(i+" ");
        }
        System.out.println("\r\n"+"spend time: "+(System.currentTimeMillis()-start)+"ms");
    }


}
