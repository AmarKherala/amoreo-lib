import java.util.List;

public class MathQuestionFormatter {

    private static final String[] NUMS = {
            "zero","one","two","three","four","five","six","seven","eight","nine",
            "ten","eleven","twelve","thirteen","fourteen","fifteen",
            "sixteen","seventeen","eighteen","nineteen"
    };

    private static final String[] TENS = {
            "", "", "twenty", "thirty", "forty", "fifty"
    };

    public static String number(int n) {
        if (n < 20) return NUMS[n];
        if (n % 10 == 0) return TENS[n / 10];
        return TENS[n / 10] + "-" + NUMS[n % 10];
    }

    public static String operator(char op) {
        return switch (op) {
            case '+' -> "plus";
            case '-' -> "minus";
            case '*' -> "times";
            default -> "";
        };
    }

    public static String expressionBuilder(List<Integer> numbers, List<Character> ops) {
        StringBuilder sb = new StringBuilder();

        sb.append(number(numbers.get(0)));

        for (int i = 0; i < ops.size(); i++) {
            sb.append(" ")
                    .append(operator(ops.get(i)))
                    .append(" ")
                    .append(number(numbers.get(i + 1)));
        }

        return sb.toString();
    }
}
