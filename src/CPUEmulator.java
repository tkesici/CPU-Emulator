import java.io.*;
import java.util.*;
/*
 * @author: Tevfik Kesici
 * @studentno: 20200808004
 * @since 23.05.2022
 */

public class CPUEmulator {
    public static void main(String[] args) throws Exception {
        int[] M = new int[256];
        int AC = 0;
        int F = 0;
        ArrayList<String> list = new ArrayList<>();
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String str = sc.nextLine().trim();
            str = str.substring(str.indexOf(" ") + 1);
            if (!str.equals("")) {
                list.add(str);
            }
        }
        boolean start = false; // If program doesn't start with "START" command, program will not start.
        int PC = 0;
        while (PC < list.size()) {
            String row = list.get(PC);
            int counter = 0;
            if (list.get(PC).contains(" ")) {
                row = list.get(PC).substring(0, list.get(PC).indexOf(" "));
                counter = Integer.parseInt(list.get(PC).substring(list.get(PC).indexOf(" ") + 1));
            }
            if (start || row.equals("START")) {
                switch (row) {
                    case "START":
                        start = true;
                        break;
                    case "LOAD":
                        AC = counter;
                        break;
                    case "STORE":
                        M[counter] = AC;
                        break;
                    case "LOADM":
                        AC = M[counter];
                        break;
                    case "CMPM":
                        F = Integer.compare(AC, M[counter]);
                        break;
                    case "CJMP":
                        if (F > 0) PC = counter - 1;
                        break;
                    case "JMP":
                        PC = counter - 1;
                        break;
                    case "ADD":
                        AC = AC + counter;
                        break;
                    case "ADDM":
                        AC = AC + M[counter];
                        break;
                    case "SUB":
                        AC -= counter;
                        break;
                    case "SUBM":
                        AC -= M[counter];
                        break;
                    case "MUL":
                        AC *= counter;
                        break;
                    case "MULM":
                        AC *= M[counter];
                        break;
                    case "DISP":
                        System.out.println(AC);
                        break;
                    case "HALT":
                        start = false;
                        break;
                }
            }
            PC++;
        }
    }
}
