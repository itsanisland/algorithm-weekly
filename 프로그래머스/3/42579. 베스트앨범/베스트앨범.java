import java.util.*;

class Solution {
    static class Music {
        int idx, play;
        String genre;

        Music(int idx, int play, String genre) {
            this.idx = idx;
            this.play = play;
            this.genre = genre;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        Music[] musics = new Music[n];
        Map<String, Integer> genreTotal = new HashMap<>();   // 장르별 총 재생 수

        for (int i = 0; i < n; i++) {
            musics[i] = new Music(i, plays[i], genres[i]);
            genreTotal.put(genres[i], genreTotal.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 1. 장르 총 재생 수 내림차순 → 2. 곡 재생 수 내림차순 → 3. 인덱스 오름차순
        Arrays.sort(musics, (a, b) -> {
				    if (!a.genre.equals(b.genre)) {
				        return Integer.compare(genreTotal.get(b.genre), genreTotal.get(a.genre));
				    }
				    if (a.play != b.play) {
				        return Integer.compare(b.play, a.play);
				    }
				    return Integer.compare(a.idx, b.idx);
				});

        Map<String, Integer> genreCount = new HashMap<>();   // 장르별로 뽑은 곡 수
        List<Integer> list = new ArrayList<>();

        for (Music music : musics) {
            int count = genreCount.getOrDefault(music.genre, 0);
            if (count < 2) {
                genreCount.put(music.genre, count + 1);
                list.add(music.idx);
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}