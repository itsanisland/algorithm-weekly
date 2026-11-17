import java.util.*;

class Solution {
    class Music {
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
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            musics[i] = new Music(i, plays[i], genres[i]);
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        Arrays.sort(musics, (a, b) -> {
            if (a.genre.equals(b.genre)) {
                if (a.genre == b.genre) {
                    return a.idx - b.idx;
                }
                return b.play - a.play;
            }
            return map.get(b.genre) - map.get(a.genre);
        });
        
        Map<String, Integer> genreMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            if (genreMap.getOrDefault(musics[i].genre, 0) < 2) {
                genreMap.put(musics[i].genre, genreMap.getOrDefault(musics[i].genre, 0) + 1);
                list.add(musics[i].idx);
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}