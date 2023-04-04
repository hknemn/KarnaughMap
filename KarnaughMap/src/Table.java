
import java.util.*;

public final class Table {

	private static ArrayList<String> list = null;
	private static int variableCount = 0;
	private static int functionCount = 0;
	private static char[][] truthTable = null;
	private static char[][] functionTable = null;

	public Table(ArrayList<String> list) {
		Table.list = list;
		variableCount = getVariableCount();
		functionCount = getFunctionCount();
		truthTable = createTruthTable();
		functionTable = createFunctionTable();
		run();
	}

	static void run() {
		System.out.println("dogruluk_tablosu.txt dosyası okundu.");
		System.out.println();
		printMinterm();
		System.out.println();
		printMaksterm();
		System.out.println();
		userInput();
	}

	static char[][] createTruthTable() {
		System.out.println();
		truthTable = new char[(int) Math.pow(2, variableCount) + 1][variableCount + functionCount];

		int indexFunctionName = variableCount * 2 + 3;
		for (int i = 0; i < functionCount; i++) {
			truthTable[0][variableCount + i] = list.get(0).charAt(indexFunctionName - 1);
			indexFunctionName += 2;
		}
		for (int i = 1; i < list.size(); i++) {
			int indexFunction = variableCount * 2 + 3;
			for (int j = 0; j < functionCount; j++) {
				truthTable[i][variableCount + j] = list.get(i).charAt(indexFunction - 1);
				indexFunction += 2;
			}
		}
		int indexVariableName = 0;
		for (int i = 0; i < variableCount; i++) {
			truthTable[0][i] = list.get(0).charAt(indexVariableName);
			indexVariableName += 2;
		}
		for (int i = 1; i < list.size(); i++) {
			int indexVariable = 0;
			for (int j = 1; j <= variableCount; j++) {
				truthTable[i][j - 1] = list.get(i).charAt(indexVariable);
				indexVariable += 2;
			}
		}
		return truthTable;
	}

	static char[][] createFunctionTable() {
		functionTable = new char[functionCount][(int) Math.pow(2, variableCount) + 1];
		for (int k = 0; k < functionCount; k++) {
			functionTable[k][0] = truthTable[0][variableCount + k];
		}
		for (int i = 1; i < list.size(); i++) {
			for (int j = 0; j < functionCount; j++) {
				functionTable[j][i] = truthTable[i][variableCount + j];
			}
		}
		return functionTable;
	}

	static void printMinterm() {
		int[] mintermIndex = new int[list.size() - 1];
		fillMinus(mintermIndex);
		for (int i = 0; i < functionCount; i++) {
			int mintermCount = 0;
			for (int j = 1; j < list.size(); j++) {
				if (functionTable[i][j] == '1') {
					mintermCount++;
				}
			}
			int mintermCountComma = mintermCount;
			System.out.print(functionTable[i][0] + " = " + "Σ(");
			for (int j = 1; j < list.size(); j++) {
				if (functionTable[i][j] == '1') {
					System.out.print(j - 1);
					mintermIndex[j - 1] = j - 1;
					if (mintermCountComma != 1) {
						System.out.print(",");
						mintermCountComma--;
					}
				}
			}
			System.out.print(") = ");
			int trim = mintermCount;
			for (int k = 0; k < mintermIndex.length; k++) {
				if (mintermIndex[k] != -1) {
					System.out.print("m" + mintermIndex[k]);
					if (trim != 1) {
						System.out.print(" + ");
						trim--;
					}
				}
			}
			System.out.print(" = ");
			int trim2 = mintermCount;
			for (int l = 0; l < mintermIndex.length; l++) {
				if (mintermIndex[l] != -1) {
					for (int m = 0; m < variableCount; m++) {
						if (truthTable[l + 1][m] == '0') {
							System.out.print(truthTable[0][m] + "'");
						} else {
							System.out.print(truthTable[0][m]);
						}
					}
					if (trim2 != 1) {
						System.out.print(" + ");
						trim2--;
					}

				}
			}
			fillMinus(mintermIndex);
			System.out.println();
		}
	}

	static void printMaksterm() {
		int makstermCount;
		int[] makstermIndex = new int[list.size() - 1];
		fillMinus(makstermIndex);
		for (int i = 0; i < functionCount; i++) {
			makstermCount = 0;
			for (int j = 1; j < list.size(); j++) {
				if (functionTable[i][j] == '0') {
					makstermCount++;
				}
			}
			int makstermCountComma2 = 0;
			makstermCountComma2 = makstermCount;
			System.out.print(functionTable[i][0] + " = " + "∏(");
			for (int j = 1; j < list.size(); j++) {
				if (functionTable[i][j] == '0') {
					System.out.print(j - 1);
					makstermIndex[j - 1] = j - 1;
					if (makstermCountComma2 != 1) {
						System.out.print(",");
						makstermCountComma2--;
					}
				}
			}

			System.out.print(") = ");
			int trim3 = makstermCount;
			for (int k = 0; k < makstermIndex.length; k++) {
				if (makstermIndex[k] != -1) {
					System.out.print("M" + makstermIndex[k]);
					if (trim3 != 1) {
						System.out.print(" . ");
						trim3--;
					}
				}
			}
			System.out.print(" = ");
			int trim4 = variableCount;
			int trim5 = makstermCount;
			for (int l = 0; l < makstermIndex.length; l++) {
				if (makstermIndex[l] != -1) {
					System.out.print("(");
				}
				if (makstermIndex[l] != -1) {
					for (int m = 0; m < variableCount; m++) {
						if (truthTable[l + 1][m] == '0') {
							System.out.print(truthTable[0][m]);
							if (variableCount - m != 1) {
								System.out.print("+");
							}
						} else {
							System.out.print(truthTable[0][m] + "'");
							if (variableCount - m != 1) {
								System.out.print("+");
							}
						}
					}
					if (trim4 != -2) {
						System.out.print(")");
					}
					if (trim5 != 1) {
						System.out.print(" . ");
						trim5--;
					}
				}
			}
			fillMinus(makstermIndex);
			System.out.println();
		}
	}

	static void userInput() {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < functionCount; i++) {
			String binary = "";
			for (int j = 0; j < variableCount; j++) {
				System.out.println("Lütfen " + truthTable[0][variableCount + i] + " fonksiyonu için " + truthTable[0][j]
						+ " değişkeninin değerini giriniz:");
				String value = input.next();
				binary = binary.concat(value);
			}
			System.err.println("Girilen değerlere göre " + truthTable[0][variableCount + i] + " fonksiyonunun çıkışı "
					+ truthTable[binaryToDecimal(binary) + 1][variableCount + i] + " dir");
		}
		input.close();
	}

	static int binaryToDecimal(String binary) {
		return Integer.parseInt(binary, 2);
	}

	static void fillMinus(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
	}

	static int getVariableCount() {
		return (int) (Math.log(list.size() - 1) / Math.log(2));
	}

	static int getFunctionCount() {
		int _functionCount = 0;
		for (int i = variableCount * 2 + 3; i <= list.get(0).length(); i += 2) {
			_functionCount++;
		}
		return _functionCount;
	}
}
