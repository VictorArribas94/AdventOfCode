package logic;

public class Logic {

    public static int countSafeLevels(int[][] levels){

        if(levels.length==0){
            return 0;
        }

        int counter = 0;
        for (int[] level : levels) {
            if (isSafe(level)) {
                counter++;
            }
        }
        return counter;
    }

    private static boolean isSafe(int[] level) {
        if (level.length <= 1) {
            return true;
        }

        boolean increasing = level[1] > level[0];

        for (int i = 1; i < level.length; i++) {
            int previous = level[i - 1];
            int current = level[i];
            int difference = Math.abs(current - previous);

            if (difference > 3 || (increasing && current <= previous) || (!increasing && current >= previous)) {
                return false;
            }
        }
        return true;
    }



    public static int countSafeLevelsWithProblemDampener(int[][] levels){

        if(levels.length==0){
            return 0;
        }

        int counter = 0;
        for (int[] level : levels) {
            if (isSafeWithProblemDampener(level)) {
                counter++;
            }
        }
        return counter;
    }

    private static boolean problemDampener(int[] level, int problematicIndex){
        if (level == null || level.length == 0 || problematicIndex < 0 || problematicIndex >= level.length) {
            return false;
        }
        // New Array with values except problematicIndex
        if (problematicIndex == 1) {
            int[] newLevelWithoutFirst = new int[level.length - 1];
            System.arraycopy(level, 1, newLevelWithoutFirst, 0, level.length - 1);

            if (isSafe(newLevelWithoutFirst)) {
                return true;
            }
        }
        int[] newLevel = new int[level.length - 1];
        System.arraycopy(level, 0, newLevel, 0, problematicIndex);
        System.arraycopy(level, problematicIndex + 1, newLevel, problematicIndex, level.length - problematicIndex - 1);

        return isSafe(newLevel);
    }

    public static boolean isSafeWithProblemDampener(int[] level) {
        if (level.length <= 1) {
            return true;
        }

        boolean increasing = level[1] > level[0];

        for (int i = 1; i < level.length; i++) {
            int previous = level[i - 1];
            int current = level[i];
            int difference = Math.abs(current - previous);

            if (difference > 3 || (increasing && current <= previous) || (!increasing && current >= previous)) {
                return problemDampener(level, i);
            }
        }

        return true;
    }
}
