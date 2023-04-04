import java.util.*;

public class Table {
	private int fonksiyonSayisi = 0;
	private int degiskenSayisi = 0;

	public Table(ArrayList<String> list) {
		this.degiskenSayisi = (int) (Math.log(list.size() - 1) / Math.log(2));
		this.fonksiyonSayisi = list.get(0).length() - degiskenSayisi;
		diziOlustur(list, degiskenSayisi, fonksiyonSayisi);
	}

	static void diziOlustur(ArrayList<String> liste, int degiskenSayisi, int fonksiyonSayisi) {
		String[][] dizi = new String[liste.size()][liste.get(0).length()];

		for (int i = 0; i < liste.size(); i++) {
			for (int j = 0; j < liste.get(0).length(); j++) {
				dizi[i][j] = liste.get(i).substring(j, j + 1);
			}
		}

		minterm(liste, dizi, degiskenSayisi, fonksiyonSayisi);
		maksterm(liste, dizi, degiskenSayisi, fonksiyonSayisi);
		kullanici(dizi, degiskenSayisi, fonksiyonSayisi);

	}

	static void minterm(ArrayList<String> liste, String[][] dizi, int degiskenSayisi, int fonksiyonSayisi) {
		for (int i = 0; i < fonksiyonSayisi; i++) {
			int count = 0;
			System.out.print(dizi[0][degiskenSayisi + i] + " = Î£(");
			for (int j = 1; j < liste.size(); j++) {
				if (dizi[j][degiskenSayisi + i].equals("1")) {
					count++;
				}
			}
			int comma = count;
			for (int j = 1; j < liste.size(); j++) {
				if (dizi[j][degiskenSayisi + i].equals("1")) {
					System.out.print(j - 1);
					if (comma != 1) {
						System.out.print(",");
						comma--;
					}
				}

			}

			System.out.print(")= ");
			for (int j = 1; j < liste.size(); j++) {

				if (dizi[j][degiskenSayisi + i].equals("1")) {
					System.out.print("m" + (j - 1));
					if (j < liste.size() - 1) {
						System.out.print("+");
					}
				}
			}
			System.out.print("=");
			for (int j = 1; j < liste.size(); j++) {
				if (dizi[j][degiskenSayisi + i].equals("1")) {
					for (int k = 0; k < degiskenSayisi; k++) {
						if (dizi[j][k].equals("1")) {
							System.out.print(dizi[0][k] + "");
						} else {
							System.out.print(dizi[0][k] + "'");
						}
					}
					if (j < liste.size() - 1) {
						System.out.print("+");
					}
				}
			}
			System.out.println();

		}
	}

	static void maksterm(ArrayList<String> liste, String[][] dizi, int degiskenSayisi, int fonksiyonSayisi) {
		for (int i = 0; i < fonksiyonSayisi; i++) {
			int count = 0;
			for (int k = 1; k < liste.size(); k++) {
				if (dizi[k][degiskenSayisi + i].equals("0")) {
					count++;
				}
			}
			int comma = count;
			System.out.print(dizi[0][degiskenSayisi + i] + " = âˆ�(");
			for (int j = 1; j < liste.size(); j++) {
				if (dizi[j][degiskenSayisi + i].equals("0")) {
					System.out.print(j - 1);
					if (comma != 1) {
						System.out.print(",");
						comma--;
					}
				}

			}

			System.out.print(")= ");
			int dot = count;
			for (int j = 1; j < liste.size(); j++) {

				if (dizi[j][degiskenSayisi + i].equals("0")) {
					System.out.print("M" + (j - 1));
					if (dot != 1) {
						System.out.print(".");
						dot--;

					}
				}

			}

			System.out.print("=");
			int dot2 = count;
			for (int j = 0; j < liste.size(); j++) {
				int plus = degiskenSayisi;
				if (dizi[j][degiskenSayisi + i].equals("0")) {
					System.out.print("(");
					for (int k = 0; k < degiskenSayisi; k++) {

						if (dizi[j][k].equals("0")) {
							System.out.print(dizi[0][k]);
							if (plus != 1) {
								System.out.print("+");
								plus--;
							}

						} else {
							System.out.print(dizi[0][k] + "'");
							if (plus != 1) {
								System.out.print("+");
								plus--;
							}

						}
					}

					System.out.print(")");
					if (dot2 != 1) {
						System.out.print(".");
						dot2--;
					}
				}
			}
			System.out.println();
		}

	}

	static void kullanici(String[][] dizi, int degiskenSayisi, int fonksiyonSayisi) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < fonksiyonSayisi; i++) {
			String ikilik = "";
			for (int j = 0; j < degiskenSayisi; j++) {
				System.out.println("Lutfen " + dizi[0][degiskenSayisi + i] + " fonksiyonu icin " + dizi[0][j]
						+ " degiskenin degerini giriniz: ");
				ikilik = ikilik.concat(input.next());
			}
			System.out.println("girilen degerler gore " + dizi[0][degiskenSayisi + i] + " fonksiyonun cikisi "
					+ dizi[binary(ikilik) + 1][degiskenSayisi + i]);
		}
		input.close();
	}

	static int binary(String ikilik) {
		return Integer.parseInt(ikilik, 2);
	}
}
