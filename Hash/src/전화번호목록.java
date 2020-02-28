import java.util.*;

public class 전화번호목록 {
	public static void main(String[] args) {
		String[][] phone_book = { { "119", "97674223", "1195524421" }, { "123", "456", "789" },
				{ "12", "123", "1235", "567", "88" } };
		Solution solve = new Solution();
		for (int i = 0; i < 3; i++) {
			System.out.println(solve.solution(phone_book[i]));
		}
	}
	
	static class Solution {
		public boolean solution(String[] phone_book) {
			for (int i = 0; i < phone_book.length - 1; i++) {
				String str1 = phone_book[i];
				for (int j = i + 1; j < phone_book.length; j++) {
					String str2 = phone_book[j];
					if (compareTo(str1, str2))
						return false;
				}
			}
			return true;
		}

		boolean compareTo(String str1, String str2) {
			int i = 0, j = 0;
			while (i < str1.length() && j < str2.length()) {
				if (str1.charAt(i) != str2.charAt(j))
					return false;
				i++;
				j++;
			}
			return true;
		}
	}

	static class Solution2 {
		public boolean solution(String[] phone_book) {
			boolean answer = true;
			Arrays.sort(phone_book, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.length() - o1.length();
				}
			});
			HashMap<String, Boolean> map = new HashMap<>();
			for (String str : phone_book) {
				if (map.containsKey(str)) {
					answer = false;
					break;
				}
				for (int i = 1; i < str.length(); i++) {
					String sub = str.substring(0, i);
					if (!map.containsKey(sub)) {
						map.put(sub, true);
					}
				}
			}
			return answer;
		}
	}
}
