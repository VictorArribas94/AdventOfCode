import objects.PageOrdering;

import java.util.*;

public class Logic {

    public static void logic(List<PageOrdering> rules, List<List<Integer>> updates){
        final List<Integer> middlePages = obtainMiddlePages(rules, updates);
        System.out.println("Middle Pages: " + middlePages);

        long sumMiddlePages = sumList(middlePages);
        System.out.println("Sum of middle pages for correctly ordered updates: " + sumMiddlePages);

        final List<Integer> incorrectMiddlePages = obtainIncorrectMiddlePages(rules, updates);
        System.out.println("IncorrectMiddle Pages: " + middlePages);

        long sumIncorrectMiddlePages = sumList(incorrectMiddlePages);
        System.out.println("Sum of middle pages for corrected updates: " + sumIncorrectMiddlePages);
    }

    /**
     * Get middle pages from a set of rules and the given list of updates
     *
     * @param rules rules to validate update
     * @param updates list of updates to validate
     * @return list of validated updates middlePages
     */
    public static List<Integer> obtainMiddlePages(List<PageOrdering> rules, List<List<Integer>> updates){
        List<Integer> middlePages = new ArrayList<>();
        for (List<Integer> update : updates) {
            if (isUpdateOrdered(update, rules)) {
                middlePages.add(getMiddlePage(update));
            }
        }
        return middlePages;
    }

    /**
     * Get a List of the middle pages of corrected incorrect Updates
     *
     * @param rules rules to validate update
     * @param updates list of updates
     * @return list of Middle pages of corrected updates
     */
    public static List<Integer> obtainIncorrectMiddlePages(List<PageOrdering> rules, List<List<Integer>> updates){
        List<Integer> incorrectMiddlePages = new ArrayList<>();

        for (List<Integer> update : updates) {
            if (!isUpdateOrdered(update, rules)) {
                List<Integer> correctedUpdate = topologicalSortUpdate(update, rules);
                if (correctedUpdate.isEmpty()) {
                    System.err.println("Cycle detected or unable to sort update: " + update);
                    continue;
                }
                incorrectMiddlePages.add(getMiddlePage(correctedUpdate));
            }
        }
        return incorrectMiddlePages;
    }

    /**
     * Checks if the given update follows all the specified rules.
     *
     * @param update The list of page numbers in the update.
     * @param rules  The list of rules represented as int arrays where rules[i][0] should come before rules[i][1].
     * @return True if the update is correctly ordered according to the rules, false otherwise.
     */
    private static boolean isUpdateOrdered(List<Integer> update, List<PageOrdering> rules) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        // Map key to a map containing value and position in array
        for (int i = 0; i < update.size(); i++) {
            indexMap.put(update.get(i), i);
        }

        // Get first and last for each rule
        for (PageOrdering rule : rules) {
            int first = rule.getFirst();
            int last = rule.getLast();
            // If both are present, check valid possition
            if (indexMap.containsKey(first) && indexMap.containsKey(last)) {
                // Check correct possition
                if (indexMap.get(first) > indexMap.get(last)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Performs a topological sort on the given update based on the specified rules.
     *
     * @param update The list of page numbers in the update.
     * @param rules  The list of rules represented as int arrays where rules[i][0] should come before rules[i][1].
     * @return A sorted list of page numbers that adheres to the rules. Returns an empty list if a cycle is detected.
     */
    private static List<Integer> topologicalSortUpdate(List<Integer> update, List<PageOrdering> rules) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Set<Integer> nodes = new HashSet<>(update);

        // Initialize graph and in-degree
        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
            inDegree.put(node, 0);
        }

        // Build the graph based on rules
        for (PageOrdering rule : rules) {
            int first = rule.getFirst();
            int last = rule.getLast();
            if (nodes.contains(first) && nodes.contains(last)) {
                graph.get(first).add(last);
                inDegree.put(last, inDegree.get(last) + 1);
            }
        }

        // Initialize the queue with nodes having in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int node : nodes) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }

        List<Integer> sortedUpdate = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedUpdate.add(current);

            for (int neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check possible topological sort
        if (sortedUpdate.size() != nodes.size()) {
            // Cycle detected or unable to sort
            return new ArrayList<>();
        }

        return sortedUpdate;
    }

    /**
     * Retrieves the middle page number from the update list.
     *
     * @param update The list of page numbers.
     * @return The middle page number.
     */
    private static int getMiddlePage(List<Integer> update) {
        return update.get(update.size() / 2);
    }

    /**
     * Sum all elements of a list.
     *
     * @param list list with values to sum
     * @return sum of all elements on the list
     */
    private static long sumList(List<Integer> list){
        return list.stream().mapToLong(Integer::longValue).sum();
    }
}
