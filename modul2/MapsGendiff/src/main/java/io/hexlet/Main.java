package io.hexlet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        var data1 = new HashMap<String, Object>(
                Map.of("one", "eon", "two", "two", "four", true)
        );
        System.out.println(data1); //=> {two=two, four=true, one=eon}

        var data2 = new HashMap<String, Object>(
                Map.of("two", "own", "zero", 4, "four", true)
        );
        System.out.println(data2); //=> {zero=4, two=own, four=true}

        var result = App.genDiff(data1, data2);
        System.out.println(result); //=> {four=unchanged, one=deleted, two=changed, zero=added}
    }


}