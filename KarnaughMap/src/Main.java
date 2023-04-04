import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		executeFile("dogruluk_tablosu.txt");
	}

	static void executeFile(String file) throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<>();
		Scanner reader = new Scanner(new File(file));
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			list.add(line);
		}
		reader.close();
		new Table(list);
	}
}
