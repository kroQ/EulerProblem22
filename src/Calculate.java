import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate {
	public static void main(String[] args) { //answer is 871198282
		String fileName = "names.txt";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				System.out.println(tmp);

				List<String> names = new ArrayList<String>();
				// Taking names
				for (int i = 0; i < tmp.length();) {
					// "MARY","PATRICIA","LINDA","BARBARA"
					Calculate c = new Calculate();
					Word w1 = new Word();
					w1 = c.findWord(tmp, i);
					names.add(w1.getWordName());
					i = w1.getEnd() +2;
				}

				long sumOfValuables = 0;
				long sumTmp = 0;
				int numberInRow=0;

				Collections.sort(names);
				for (String s : names) {
					sumTmp = 0;
						for(int i=0; i<s.length(); i++){
							sumTmp += s.codePointAt(i)-64;
						}
					numberInRow++;
					sumOfValuables += numberInRow * sumTmp;
				}
				System.out.println(sumOfValuables);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	Word findWord(String text, int position) {
		Word w = new Word();
		for (int i = position; i < text.length(); i++) {
			if (text.charAt(i) == '"') {
				w.setStart(i + 1);
				// System.out.print("PIERWSZA: " + text.charAt(w.getStart()));
				position = i + 1;
				break;
			}
		}
		for (int i = position; i < text.length(); i++) {
			if (text.charAt(i) == '"') {
				w.setEnd(i - 1);
				// System.out.print(" OSTATNIA: " + text.charAt(w.getEnd()));
				break;
			}
		}
		w.setLength();
		w.setWordName(text.substring(w.start, w.end + 1));
		return w;
	}

}
