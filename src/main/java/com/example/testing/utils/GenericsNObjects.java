package com.example.testing.utils;

import lombok.*;

public class GenericsNObjects {
    public static void main(String[] args) {
        System.out.println("=========== Object Box ==============");
        //integer
        Box intBox = new Box();
        intBox.setObject(12);
        System.out.println("First Integer Box is: "+intBox.getObject());

        //String
        Box stringBox = new Box();
        stringBox.setObject("This is String Object");
        System.out.println(stringBox.getObject());

        ///////////////////////////////////////////////////
        //////////////////////////////////////////////////

        System.out.println("========== Generic Box =============");
        //generic integer
        BoxGeneric<Integer> integerBoxGeneric = new BoxGeneric<>();
        integerBoxGeneric.setT(1222);
        System.out.println(integerBoxGeneric.getT());

        //generic string
        BoxGeneric<String> stringBoxGeneric = new BoxGeneric<>();
        stringBoxGeneric.setT("generic string box");
        System.out.println(stringBoxGeneric.getT());


        //////////////////////////////////////////////////
        //////////////////////////////////////////////////

        Pair<String, Integer> p1 =new OrderPair<>("Odd", 3);
        System.out.println(p1.toString());
        Pair<String, String> p2 = new OrderPair<>("hello", "no world");
        System.out.println(p2.toString());

        /////////////////////////
        //raw type
        System.out.println("======== raw - type ===========");
        try {
            BoxGeneric rawType = new BoxGeneric();
            rawType.setT(8);
            System.out.println(rawType.getT());
        }catch (Exception e){
            System.out.println("Raw type casting error occured.");
        }
    }
}


// Since its methods accept or return an Object, you are free to pass in whatever you want,
// provided that it is not one of the primitive types. There is no way to verify, at compile time,
// how the class is used. One part of the code may place an Integer in the box and expect to get
// Integers out of it, while another part of the code may mistakenly pass in a String,
// resulting in a runtime error.

@Getter
@Setter
class Box{
    private Object object;

}
@Getter
@Setter
class BoxGeneric<T> {
    private T t;
}

interface Pair<K,V>{
    public K getKey();
    public V getValue();
}
@NoArgsConstructor
@AllArgsConstructor
@ToString
class OrderPair<K,V> implements Pair<K,V>{
    private K key;
    private V value;

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

}
