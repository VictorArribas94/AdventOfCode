package logic;

import objects.Lists;

import java.util.List;
import java.util.stream.IntStream;

public class Logic {

    public static Lists orderLists(final Lists lists){
        return Lists.builder()
                .listA(orderList(lists.getListA()))
                .listB(orderList(lists.getListB()))
                .build();
    }

    private static List<Integer> orderList(final List<Integer> list){
        list.sort(Integer::compareTo);
        return list;
    }

    public static Integer getSumOfDifferences(List<Integer> listA, List<Integer> listB) {
        if (listA.size() != listB.size()) {
            return -1;
        }

        return IntStream.range(0, listA.size()) // IntStream for big size lists
                .map(i -> Math.abs(listA.get(i) - listB.get(i)))
                .sum();
    }

    public static Integer getSumOfAppearances(List<Integer> listA, List<Integer> listB) {
        if (listA.size() != listB.size()) {
            return -1;
        }

        return listA.stream()
                .map(value -> getNumberOfAppearances(value, listB))
                .reduce(0, Integer::sum);
    }

    private static int getNumberOfAppearances(Integer value, List<Integer> list) {
        long appearances = list.stream()
                .filter(element -> element.equals(value))
                .count();
        return value * (int) appearances;
    }
}
