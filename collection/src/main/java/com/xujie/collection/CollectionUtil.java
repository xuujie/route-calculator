package com.xujie.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectionUtil {
    public static List<String> distinctUsingStream(List<String> list) {
        return list.stream().distinct().sorted().collect(Collectors.toList());
    }

    public static List<String> distinctUsingParallelStream(List<String> list) {
        return list.parallelStream().distinct().sorted().collect(Collectors.toList());
    }

    public static List<String> distinctUsingWithTreeSet(List<String> list) {
        return new ArrayList<>(new TreeSet<>(list));
    }

    public static boolean anyMatchStream(List<String> list, String... match) {
        return list.stream().anyMatch(s -> Arrays.stream(match).anyMatch(m -> m.equals(s)));
    }

    public static boolean anyMatchPartial(List<String> list, String... match) {
        return list.stream().anyMatch(s -> {
            for (String m : match) {
                if (m.equals(s)) {
                    return true;
                }
            }
            return false;
        });
    }

    public static boolean anyMatchTraditional(List<String> list, String... match) {
        for (String s : list) {
            for (String m : match) {
                if (m.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

}
