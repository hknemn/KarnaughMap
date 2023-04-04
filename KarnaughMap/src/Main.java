import java.io.*;
import java.util.*;

public class Main {
	private static ArrayList<String> list;

	public static void main(String[] args) {
		read();
		new Table(list);
	}

	static void read() {
		list = new ArrayList<>();
		File file = new File("dogruluk_tablosu.txt");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String line2 = line.replace(" ", "");
				String line3 = line2.replace("|", "");
				list.add(line3);
			}
			reader.close();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
}
