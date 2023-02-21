package streams.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Homework {
    public static final List <Integer> numbers = new ArrayList<>(List.of(5, 10, 3, 4, 1, 6, 9, 8, 7, 2));

    public static void main(String[] args) {
        Stream<Integer> stream = numbers.stream();
        IntegerComparator integerComparator = new IntegerComparator();
        BiConsumer<Integer, Integer> biConsumer = (integer, integer2) -> System.out.println("Что то произошло c " + integer + " и " + integer2);

        findMinMax(stream,integerComparator,biConsumer);

        doOddCounter(numbers);
    }
    // Первый варинат
    public static void findMinMax(Stream<Integer> stream, Comparator<Integer> order, BiConsumer<Integer,Integer> minMaxConsumer) {
        List<Integer> list = stream.sorted(order)
                .collect(Collectors.toList());
        minMaxConsumer.accept(list.size() == 0 ? null : list.get(0), list.size() == 0 ? null : list.get(list.size() - 1));
    }
    //Второй вариант (похуже)
    public static void findMinMaxSecond(Stream<Integer> stream, Comparator<Integer> order, BiConsumer<Integer,Integer> minMaxConsumer) {
        List<Integer> resultList = stream.filter((x) -> Objects.equals(x, Collections.min(numbers)) || Objects.equals(x, Collections.max(numbers)))
                .collect(Collectors.toList());
        minMaxConsumer.accept(resultList.isEmpty() ? null : Collections.min(resultList), resultList.isEmpty() ? null : Collections.max(resultList));
    }
    public static void doOddCounter(List<Integer> numbers){
        System.out.println(numbers.stream().filter((x) -> x % 2 == 0).count());
        //System.out.println(numbers.stream().filter((x) -> Integer.lowestOneBit(x) != 1).count());
    }
    private static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) {
                return 1;
            } else if (o2 > o1) {
                return -1;
            }
            return 0;
        }
    }
}
