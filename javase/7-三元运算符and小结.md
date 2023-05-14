# 三元运算符and小结

```
1. `public class Demo3 {`
2. `    public static void main(String[] args) {`
3. `        int a = 10;`
4. `        int b = 20;`
5. ``
6. `        a+=b;//a = a + b`
7. `        a-=b;//a = a - b`
8. `        System.out.println(a);`
9. `        //字符串连接符   +    ,String`
10. `        System.out.println(""+a+b);//输出1020`
11. `        System.out.println(a+b+"");//输出30`
12. `    }`
13. `}`
14. 
```





```
1. `public class Demo4 {`
2. `    //三元运算符`
3. `    public static void main(String[] args) {`
4. `        //x ? y : z`
5. `        //如果x == true,则结果为y，否则结果为z`
6. ``
7. `        int score = 50;`
8. `        String type = score <60 ? "不及格" : "及格";//必须掌握！！！`
9. `        System.out.println(type);//输出为不及格`
10. `    }`
11. `}`
```

