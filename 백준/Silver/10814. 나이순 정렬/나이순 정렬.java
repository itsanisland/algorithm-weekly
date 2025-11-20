import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	private static class User implements Comparable<User> {
		
		int id, age;
		String name;
		
		public User(int id, int age, String name) {
			this.id = id;
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(User o) {
			if (this.age == o.age) {
				return Integer.compare(this.id, o.id);
			}
			return Integer.compare(this.age, o.age);
		}
	}
	
	private static int N;
	private static User[] users;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		users = new User[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			
			users[i] = new User(i, age, name);
		}
		
		Arrays.sort(users);
		
		for (int i = 0; i < N; i++) {
			System.out.println(users[i].age + " " + users[i].name);
		}
	}

}