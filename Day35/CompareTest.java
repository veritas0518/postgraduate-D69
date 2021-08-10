package Day35;

import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName: CompareTest
 * @Description:
 * @Author: TianXing.Xue
 * @Date: 2021/7/31 18:09
 * @Version: 1.0
 *
 * 一、说明：Java中的对象，正常情况下，只能进行比较：== 或 != 。不能使用 > 或 < 的
 *      但是在开发的场景中，我们需要对多个对象进行排序，言外之意，就需要比较对象的大小
 *      如何实现？使用两个接口中的任何一个：Comparable 或 Comparator
 *
 * 二、Comparable接口与Comparator的使用的对比
 *      Comparable接口的方式一旦制定，保证Comparable接口实现类的对象在任何位置都可以比较大小
 *      Comparable接口属于临时性的比较
 *
 **/

public class CompareTest {
    /*
    Comparable接口的使用举例：
    1.像String、包装类等实现了Comparable接口，重写了compareTo()方法，给出了比较两个对象大小的方式
    2.像String、包装类重写compareTo()方法以后，进行了从小到大的排序
    3.重写compareTo()的规则：
        如果当前对象this大于形参对象obj，则返回正整数
        如果当前对象this小于形参对象obj，则返回正整数
        如果当前对象this等与形参对象obj，则返回零
    4.对于自定义类来说，如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo(obj)方法
       在compareTo(obj)方法中指明如何排序


     */
    @Test
    public void test1() {
        String[] arr = new String[]{"AA", "CC", "MM", "KK", "ZZ", "GG"};
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2() {
        Goods[] arr = new Goods[5];
        arr[0] = new Goods("联想鼠标", 34);
        arr[1] = new Goods("戴尔鼠标", 43);
        arr[2] = new Goods("小米鼠标", 12);
        arr[3] = new Goods("华为鼠标", 65);
        arr[4] = new Goods("微软鼠标", 43);

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    Comparator接口的使用：定制排序
    1.背景：
    当元素的类型没有实现java.lang.Comparable接口而又不方便修改代码，
    或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，
    那么可以考虑使用Comparator的对象来排序
    2.重写compare(Object o1,Object o2)方法，比较o1和o2的大小:
        如果方法返回正整数，表示o1大于o2
        如果返回0，表示相等
        返回负整数，表示o1小于o2
     */

    @Test
    public void test3() {
        String[] arr = new String[]{"AA", "CC", "MM", "KK", "ZZ", "GG"};
        Arrays.sort(arr, new Comparator() {

            //按照字符串从大到小的排序
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String str1 = (String) o1;
                    String str2 = (String) o2;
                    return -str1.compareTo(str2);
                }
                throw new RuntimeException("输入的类型不一致");
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4() {
        Goods[] arr = new Goods[6];
        arr[0] = new Goods("lenovo鼠标", 34);
        arr[1] = new Goods("dell鼠标", 43);
        arr[2] = new Goods("xiaomi鼠标", 12);
        arr[3] = new Goods("huawei鼠标", 65);
        arr[4] = new Goods("huawei鼠标", 224);
        arr[5] = new Goods("microsoft鼠标", 43);

        Arrays.sort(arr, new Comparator() {

            //指明商品比较大小的方式：按照产品名称从低到高排序，再按照价格从高到低排序
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Goods && o2 instanceof Goods) {
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                    if (g1.getName().equals(g2.getName())) {
                        return -Double.compare(g1.getPrice(), g2.getPrice());
                    } else {
                        return g1.getName().compareTo(g2.getName());
                    }

                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
