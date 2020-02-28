import java.util.*;

public class 위장 {
	public static void main(String[] args) {
		String[][][] clothes = {
				{ { "yellow_hat", "headgear" }, { "blue_sunglasses", "eyewear" }, { "green_turban", "headgear" } },
				{ { "crow_mask", "face" }, { "blue_sunglasses", "face" }, { "smoky_makeup", "face" } } };
		Solution solve = new Solution();
		for (int i = 0; i < clothes.length; i++) {
			System.out.println(solve.solution(clothes[i]));
		}
	}

	static class Solution {
		public int solution(String[][] clothes) {
			int answer = 1;
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < clothes.length; i++) {
				if (map.containsKey(clothes[i][1]))
					map.put(clothes[i][1], map.get(clothes[i][1]) + 1);
				else
					map.put(clothes[i][1], 2);
			}
			for (String key : map.keySet())
				answer *= map.get(key);
			answer--;
			return answer;
		}
	}

	static class Solution2 {
		int answer = 0;

		public int solution(String[][] clothes) {
			Map<String, Integer> map = new HashMap<>();
			int arr[] = new int[30];
			int idx = 0;
			for (int i = 0; i < clothes.length; i++) {
				if (map.containsKey(clothes[i][1])) {
					arr[map.get(clothes[i][1])]++;
				} else {
					arr[idx]++;
					map.put(clothes[i][1], idx++);
				}
			}
			comb(arr, 1, 0, idx);
			return answer;
		}

		void comb(int[] arr, int sum, int idx, int lastIdx) {
			if (idx == lastIdx) {
				answer += sum;
				return;
			}
			comb(arr, sum * arr[idx], idx + 1, lastIdx);
			comb(arr, sum, idx + 1, lastIdx);
		}
	}
}