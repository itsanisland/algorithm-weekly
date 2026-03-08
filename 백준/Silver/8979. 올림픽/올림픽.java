import java.util.*;
import java.io.*;

class Main {

    static class Country implements Comparable<Country> {
        int idx, gold, silver, bronze;

        Country(int idx, int gold, int silver, int bronze) {
            this.idx = idx;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        public int compareTo(Country o) {
            if (gold == o.gold) {
                if (silver == o.silver) return o.bronze - bronze;
                return o.silver - silver;
            }
            return o.gold - gold;
        }        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Country[] countries = new Country[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            countries[i] = new Country(idx, gold, silver, bronze);
        }

        Arrays.sort(countries);

        int rank = 1;
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (countries[i - 1].idx == K) {
                System.out.println(rank);
                break;
            }
            
            if (countries[i - 1].gold == countries[i].gold && 
               countries[i - 1].silver == countries[i].silver &&
               countries[i - 1].bronze == countries[i].bronze) {
                cnt++;
            } else {
                rank += cnt;
                cnt = 1;
            }
        }
    }
}