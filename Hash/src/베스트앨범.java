import java.util.*;

public class 베스트앨범 {
	public static void main(String[] args) {
		Solution solve = new Solution();
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		int[] answer = solve.solution(genres, plays);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	static class Solution {

		public int[] solution(String[] genres, int[] plays) {
			List<PlayList> playList = new ArrayList<>();// playList를 ArrayList의 클래스를 만들어 담아서 index에 대한 고민을 없앤다.
			List<Integer> indexList = new ArrayList<>();// answer에 담을 indexList를 사용
			Map<String, Integer> hashMap = new HashMap<>();// 재생횟수를 축적
			for (int i = 0; i < genres.length; i++) {
				playList.add(new PlayList(genres[i], plays[i], i));// 여러개의 배열을 하나로 합치기, 여기서 필요한 배열은 장르, 플레이횟수, 인덱스
				if (hashMap.get(genres[i]) == null)
					hashMap.put(genres[i], plays[i]);
				else
					hashMap.put(genres[i], hashMap.get(genres[i]) + plays[i]);
			}
			List<Integer> sortedArrayList = new ArrayList<>(hashMap.values());// hashMap의 value는 플레이횟수의 누적, hashMap.values를 arrayList에 담아서 arrayList화 시키기, 이 arrayList는 play횟수를 sort하기 위해서 사용
			List<String> sortedGenresArrayList = new ArrayList<>();// 장르별로 나열된 arrayList, 이 arrayList는 genre를 sort하기 위해서 사용
			Collections.sort(sortedArrayList);
			Collections.reverse(sortedArrayList);// 정렬시키고 reverse시켜서 내림차순화 시키기
			sortedArrayList.forEach(value -> {
				hashMap.entrySet().forEach(entry -> {// sortedArray는 축적된 arrayList의 내림차순과 관련된 arrayList, sortedArrayList는 play횟수를 내림차순, sortedGenresArrayList는 genre를 내림차순하여 담고 있는 arrayList
					if (value == entry.getValue()) {
						if (!sortedGenresArrayList.contains(entry.getKey()))
							sortedGenresArrayList.add(entry.getKey());
					}
				});
			});
			Collections.sort(playList, new Comparator<PlayList>() { // playList는 index, genre, plays를 담고있음, plays를 기준으로 내림차순을 갖고 있음
				@Override
				public int compare(PlayList o1, PlayList o2) {
					if (o1.plays > o2.plays)
						return -1;
					else if (o1.plays == o2.plays)
						return 0;
					else
						return 1;
				}
			});

			// 많이 사용된 장르가 내림차순화 되었기 때문에
			// key는 가장 많이 재생된 장르가 먼저 나옵니다.
			// 해당 for문에서는 나열된 재생횟수가 나열된 playList와 비교할 것이고
			// 가장 많은 순서대로 나열된 playList를 가장 많이 재생된 장르로 비교할 것이기 때문에
			// 가장 많은 순서로, 가장 많이 재생된 장르부터 나오게 되며
			// 2개씩만 뽑아서 베스트앨범으로 만들것이기 때문에
			// count의 값이 2라면 break시키고
			// 그 외에는 indexList에 playList의 index를 담아서 출력합니다.
			sortedGenresArrayList.forEach(key -> {
				int count = 0;
				for (int i = 0; i < playList.size(); i++) {
					if (key.equals(playList.get(i).getGenres())) {
						indexList.add(playList.get(i).getIndex());
						count++;
					}
					if (count == 2) {
						break;
					}
				}
			});
			int[] answer = new int[indexList.size()];// bestAlbum에 담길 음악의 갯수
			for (int i = 0; i < indexList.size(); i++)// index를 삽입
				answer[i] = indexList.get(i);
			return answer;
		}

		class PlayList {
			String genres;
			int plays;
			Integer index;

			PlayList(String genres, int plays, Integer index) {
				this.genres = genres;
				this.plays = plays;
				this.index = index;
			}

			public String getGenres() {
				return genres;
			}

			public Integer getIndex() {
				return index;
			}

			public int getPlays() {
				return plays;
			}
		}
	}
}

/* 다른 풀이
 * import java.util.ArrayList; import java.util.Arrays; import
 * java.util.HashMap; import java.util.PriorityQueue; class Solution { public
 * int[] solution(String[] genres, int[] plays) { HashMap<String, Integer> hm =
 * new HashMap<>();
 * 
 * for (int i = 0; i < genres.length; i++) { if(hm.containsKey(genres[i])) {
 * hm.put(genres[i], hm.get(genres[i])+plays[i]); }else { hm.put(genres[i],
 * plays[i]); } } PriorityQueue<Music> pq = new PriorityQueue<>();
 * HashMap<String,Integer> cntMap = new HashMap<>(); for (int i = 0; i <
 * plays.length; i++) { pq.add(new Music(genres[i], plays[i],i,
 * hm.get(genres[i]))); }
 * 
 * ArrayList<Integer>list = new ArrayList<>(); for (int i = 0; i <
 * genres.length; i++) { Music music = pq.poll();
 * if(!cntMap.containsKey(music.ganres)) { cntMap.put(music.ganres,1);
 * 
 * } else if(cntMap.get(music.ganres)<2) { cntMap.put(music.ganres, 2); }else
 * continue; list.add(music.idx); } int[] answer = new int[list.size()]; for
 * (int i = 0; i < answer.length; i++) { answer[i]=list.get(i); }
 * 
 * return answer; }
 * 
 * public static class Music implements Comparable<Music>{ String ganres; int
 * plays; int idx; int cnt; public Music(String ganres, int plays, int idx, int
 * cnt) { this.ganres = ganres; this.plays = plays; this.idx = idx; this.cnt =
 * cnt; }
 * 
 * @Override public int compareTo(Music o) { if(o.cnt== this.cnt) {
 * if(o.plays==this.plays) { return this.idx-o.idx; }else { return
 * o.plays-this.plays; } } return o.cnt-this.cnt; } } }
*/