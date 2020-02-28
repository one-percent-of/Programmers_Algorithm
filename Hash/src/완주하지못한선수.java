import java.util.*;

public class 완주하지못한선수 {
	public static void main(String[] args) {
		String[][] participant = { { "leo", "kiki", "eden" }, { "marina", "josipa", "nikola", "vinko", "filipa" },
				{ "mislav", "stanko", "mislav", "ana" } };
		String[][] completion = { { "eden", "kiki" }, { "josipa", "filipa", "marina", "nikola" },
				{ "stanko", "ana", "mislav" } };
		Solution solve = new Solution();
		for (int i = 0; i < 3; i++) {
			System.out.println(solve.solution(participant[i], completion[i]));
		}
	}
	static class Solution {
		public String solution(String[] participant, String[] completion) {
			String answer = "";
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < participant.length; i++) {
				if (map.containsKey(participant[i]))
					map.put(participant[i], map.get(participant[i]) + 1);
				else
					map.put(participant[i], 1);
			}
			for (int i = 0; i < completion.length; i++)
				map.replace(completion[i], map.get(completion[i]) - 1);
			for (String key : map.keySet())
				if (map.get(key) != 0)
					answer = key;
			return answer;
		}
	}
}
