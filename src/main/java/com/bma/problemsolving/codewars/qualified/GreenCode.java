package com.bma.problemsolving.codewars.qualified;

import java.util.*;

/**
 * Rule #1: CPU Usage should be less than 90%.
 * <p>
 * Rule #2: CPU Usage and Used Memory should not be in their maximum value in the array at the same time. (If both array sizes are NOT equal to 1)
 * <p>
 * Rule #3: Used Memory should be less than 60% of the total available.
 * <p>
 * Rule #4: Used Memory should not increase in given 3 consecutive measure values.
 * <p>
 * Rule #5: If the number of non readable data points (Null) for CPU are more than 30%, Rule #1 and #2 are rejected
 * <p>
 * Rule #6: If the number of non readable data points (Null) for Memory are more than 25% , Rule #3 and #4 are rejected
 * <p>
 * Rule #7: If there's no readable data points in both arrays, Rules #1, #2, #3 and #4 are rejected
 */
public class GreenCode {

    private final Float[] cpuUsage;
    private final Integer[] usedMemory;
    private final List<RuleChecker> rules = List.of(
            new RuleOne(List.of(new RuleFive(), new RuleSeven())),
            new RuleTwo(List.of(new RuleFive(), new RuleSeven())),
            new RuleThree(),
            new RuleFour()
    );

    public GreenCode(Float[] cpuUsage, Integer[] usedMemory) {
        this.cpuUsage = cpuUsage;
        this.usedMemory = usedMemory;
    }

    interface RuleChecker {
        String name();

        boolean checkRule(Float[] cpuUsage, Integer[] usedMemory);
    }

    class RuleOne implements RuleChecker {

        private List<RuleChecker> dependencies;

        RuleOne(List<RuleChecker> dependencies) {
            this.dependencies = dependencies;
        }

        @Override
        public String name() {
            return "Rule One";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {
            for (RuleChecker dependentRule : dependencies) {
                if (!dependentRule.checkRule(cpuUsage, usedMemory))
                    return false;
            }

            for (Float usage : cpuUsage) {
                if (Objects.isNull(usage))
                    continue;

                if (usage >= 0.9f) {
                    return false;
                }
            }

            return true;
        }
    }

    class RuleTwo implements RuleChecker {

        private List<RuleChecker> dependencies;

        public RuleTwo(List<RuleChecker> dependencies) {
            this.dependencies = dependencies;
        }

        @Override
        public String name() {
            return "Rule Two";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {
            for (RuleChecker dependentRule : dependencies) {
                if (!dependentRule.checkRule(cpuUsage, usedMemory))
                    return false;
            }

            if (cpuUsage.length == 1 && usedMemory.length == 1)
                return true;

            return calcMaxCpuIndex(cpuUsage) != calcMaxUsedMemoryIndex(usedMemory);
        }

        private int calcMaxCpuIndex(Float[] cpuUsage) {

            var maxCpu = Float.MIN_VALUE;

            var maxCpuIndex = 0;

            for (int i = 0; i < cpuUsage.length; i++) {
                if (Objects.isNull(cpuUsage[i]))
                    continue;

                if (cpuUsage[i] > maxCpu) {
                    maxCpu = cpuUsage[i];
                    maxCpuIndex = i;
                }
            }
            return maxCpuIndex;
        }

        private int calcMaxUsedMemoryIndex(Integer[] usedMemory) {
            var maxMem = Integer.MIN_VALUE;
            var maxUsedMemoryIndex = 0;
            for (int i = 0; i < usedMemory.length; i++) {
                if (Objects.isNull(usedMemory[i]))
                    continue;

                if (usedMemory[i] > maxMem) {
                    maxMem = usedMemory[i];
                    maxUsedMemoryIndex = i;
                }
            }

            return maxUsedMemoryIndex;
        }
    }

    class RuleThree implements RuleChecker {

        private final int SIXTY_PERCENT = 19660;

        @Override
        public String name() {
            return "Rule Three";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {

            if (!new RuleSix().checkRule(cpuUsage, usedMemory)
                    || !new RuleSeven().checkRule(cpuUsage, usedMemory))
                return false;

            for (Integer mem : usedMemory) {
                if (Objects.isNull(mem)) continue;
                if (mem > SIXTY_PERCENT)
                    return false;
            }
            return true;
        }
    }

    // Rule #4: Used Memory should not increase in given 3 consecutive measure values.
    class RuleFour implements RuleChecker {

        @Override
        public String name() {
            return "Rule Four";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {


            if (!new RuleSix().checkRule(cpuUsage, usedMemory)
                    || !new RuleSeven().checkRule(cpuUsage, usedMemory))
                return false;

            for (int i = 0; i < usedMemory.length - 2; i++) {
                if (Objects.isNull(usedMemory[i + 2]) || Objects.isNull(usedMemory[i + 1]) || Objects.isNull(usedMemory[i]))
                    continue;

                if (usedMemory[i + 2] > usedMemory[i + 1]
                        && usedMemory[i + 1] > usedMemory[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    // Rule #5: If the number of non readable data points (Null) for CPU are more than 30%, Rule #1 and #2 are rejected
    class RuleFive implements RuleChecker {

        @Override
        public String name() {
            return "Rule Five";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {
            float nullCount = 0;
            for (Float cpu : cpuUsage) {
                if (Objects.isNull(cpu)) {
                    nullCount++;
                }
            }

            float percent = (nullCount * 100) / cpuUsage.length;

            return percent < 30;
        }
    }

    // Rule #6: If the number of non readable data points (Null) for Memory are more than 25% , Rule #3 and #4 are rejected
    class RuleSix implements RuleChecker {

        @Override
        public String name() {
            return "Rule Six";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {
            float nullCount = 0;
            for (Integer mem : usedMemory) {
                if (Objects.isNull(mem)) {
                    nullCount++;
                }
            }

            float percent = (nullCount * 100) / usedMemory.length;

            return percent < 25;
        }
    }

    // If there's no readable data points in both arrays, Rules #1, #2, #3 and #4 are rejected
    class RuleSeven implements RuleChecker {

        @Override
        public String name() {
            return "Rule Seven";
        }

        @Override
        public boolean checkRule(Float[] cpuUsage, Integer[] usedMemory) {
            return isUsedMemoryDataReadable(usedMemory) && isCpuUsageDateReadable(cpuUsage);
        }

        private boolean isCpuUsageDateReadable(Float[] cpuUsage) {
            boolean isDataReadable = false;
            for (Float cpu : cpuUsage) {
                if (!Objects.isNull(cpu)) {
                    isDataReadable = true;
                    break;
                }
            }

            return isDataReadable;
        }

        private boolean isUsedMemoryDataReadable(Integer[] usedMemory) {
            boolean isDataReadable = false;
            for (Integer mem : usedMemory) {
                if (!Objects.isNull(mem)) {
                    isDataReadable = true;
                    break;
                }
            }

            return isDataReadable;
        }
    }

    private Map<Integer, String> greenCodeStatus = Map.of(
            0, "green",
            1, "yellow"
    );

    public String greenCodeStatus() {

        int count = (int) rules
                .stream()
                .filter(rule -> !rule.checkRule(cpuUsage, usedMemory))
                .count();

        return greenCodeStatus.getOrDefault(count,"red");
    }


    public static String verify(Float[] cpuUsage, Integer[] usedMemory) {
        return new GreenCode(cpuUsage, usedMemory).greenCodeStatus();
    }
}
