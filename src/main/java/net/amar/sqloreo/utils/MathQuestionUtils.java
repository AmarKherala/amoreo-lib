package net.amar.sqloreo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathQuestionUtils {

  public static class Question {
    List<Integer> numbers;
    List<Character> ops;

    public Question(List<Integer> numbers, List<Character> ops) {
      this.numbers = numbers;
      this.ops = ops;
    }

    public List<Integer> getNumbers() {
      return numbers;
    }

    public List<Character> getOps() {
      return ops;
    }
  }

  public static Question generateQ(int level) {
    Random ran = new Random();
    int arraySize;

    switch (level) {
      default:
      case 1:
        arraySize = 2;
        break;
      case 2:
        arraySize = ran.nextInt(2) + 3;
        break;
      case 3:
        arraySize = ran.nextInt(3) + 5;
        break;
    }

    List<Integer> numbers = new ArrayList<>();
    List<Character> ops = new ArrayList<>();

    char[] opsPool = { '+', '-', '*' };

    for (int i = 0; i < arraySize; i++) {
      numbers.add(ran.nextInt(50));
    }

    for (int i = 0; i < arraySize - 1; i++) {
      ops.add(opsPool[ran.nextInt(opsPool.length)]);
    }

    return new Question(numbers, ops);
  }

  public static int solveQ(Question q) {

    List<Integer> nums = new ArrayList<>(q.numbers);
    List<Character> ops = new ArrayList<>(q.ops);

    for (int i = 0; i < ops.size(); i++) {
      if (ops.get(i) == '*') {
        int multiplied = nums.get(i) * nums.get(i + 1);
        nums.set(i, multiplied);

        nums.remove(i + 1);
        ops.remove(i);

        i--;
      }
    }

    int result = nums.get(0);
    for (int i = 0; i < ops.size(); i++) {
      char op = ops.get(i);
      int num = nums.get(i + 1);

      if (op == '+')
        result += num;
      else if (op == '-')
        result -= num;
    }

    return result;
  }
}
