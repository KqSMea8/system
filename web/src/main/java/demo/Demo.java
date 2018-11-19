package demo;

import java.text.SimpleDateFormat;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-09-12
 */
public class Demo {

//static  int b;


    public      Demo(){

        System.out.println(3);
        System.out.println(a);
        System.out.println(this.b);
        System.out.println(aa);
    }
    private static int aa = 11;

    public  static    void aa(){
        System.out.println("qq");
        System.out.println(b);
//        System.out.println(a);
    }
    {
        System.out.println(2);
    }
    static {
        System.out.println(1);
    }




    public static void main (String[] args){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat();
//        String a  = simpleDateFormat.format(new Date());
//        System.out.println("ddmndm"+a);
//        Demo demo = new Demo();
//        System.out.println(a);
//        System.out.println(b);
        aa();

    }
    static   Demo demo1 = new Demo();


    int a =5;
    static int b = 6;
}
