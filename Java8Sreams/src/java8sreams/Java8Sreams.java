/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package java8sreams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.function.Function.identity;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author anuarlb
 */
public class Java8Sreams {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NumericTest isEven = (n) -> {
            return (n%2) == 0;
        };
        System.out.println(isEven.test(45));
        
        MyNumber myNum;
        
        myNum = () -> 14.675;
        
        System.out.println(myNum.getValue());
        
        myNum = () -> Math.random() * 100;
        
        System.out.println(myNum.getValue());
        
        NumericTest2 isFactor = (n,d) -> (n%d)==0;
        
        if(isFactor.test(10, 2)){
            System.out.println("2 is a factor of 10");
        }
        
        NumericFunction factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }

            return result;
        };
        
        System.out.println("The factorial of 3 is: " + factorial.func(3));
        System.out.println("The factorial of 5 is: " + factorial.func(5));
        
    }
    
    interface NumericTest {
        boolean test(int n);
    }
    
    interface NumericTest2 {
        boolean test(int n, int d);
    }
    
    interface NumericFunction {
        int func(int n);
    }
    
    interface MyNumber {
      /* This method is implicitly abstract, and it's the only method defined
       * by MyNumber. Thus, Number is a functional interface, and its function is
       * defined by the method getValue().
      */
      
      double getValue();  
    }
    
    public class Examples {
        
        public void firstExample () {
            
            List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
           List<Integer> twoEvenSquares = numbers.stream().
                   filter(n-> {
                   System.out.println("filtering " + n);
                   return n%2 == 0;
                   })
                   .map(n->{
                   System.out.println("mapping " + n);
                   return n*n;
                   })
                   .limit(2)
                   .collect(toList());
           
           System.out.println("Resultado:");
           twoEvenSquares.forEach(n-> System.out.println(n));
            
        }
        
        
        public void secondExample () {
            
            Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");
            
            Map<String, Long> leterToCount;
            leterToCount = words.map(w -> w.split("")).flatMap(Arrays::stream)
                    .collect(Collectors.groupingBy(identity(), counting()));
            
            "Hava".split("");
        }
    }
    
}
