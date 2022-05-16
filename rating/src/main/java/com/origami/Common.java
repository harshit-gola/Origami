package com.origami;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Scanner;

public class Common {

    public static void run(){
        int[] sheetNo = {0,4};
        String[] sheetName = {"","MI"};
        int[] RRI = {1,0};
        String[] fileNames = {"Job-Classification-Rates (17).xlsx","C1 Class Table by State with Available 2022 Rates - RK 3-25-22 (1).xlsx"};
        String[] colIndex = {"0","1,9"};
        // Scanner sc = new Scanner(System.in);
        Map<String, Ops> instances = new HashMap<>();
        System.out.println("Enter number of files to read: ");
        // int noOfFiles = sc.nextInt();
        int noOfFiles = 2;
        for(int i = 0; i < noOfFiles; i++){
            System.out.print("Enter sheet number (leave blank if not required): ");
            // int sheetNo = sc.nextInt();
            System.out.print("Enter sheet name (leave blank if not required): ");
            // String sheetName; 
            // sheetName = sc.nextLine();
            // sc.nextLine();
            System.out.print("Enter index of row to start reading: ");
            // int rowIndex = sc.nextInt();
            System.out.print("Enter file name to read: ");
            // String fileName;
            // fileName = sc.nextLine();
            // fileName = sc.nextLine();
            
            System.out.print("Enter column index to read (comma separated): ");
            // String columns;
            // columns = sc.nextLine();

            String path = Constants.FILEPATH + fileNames[i];
            System.out.println(path);
            System.out.println(path);
            instances.put("instance"+ i, new Ops(sheetNo[i], sheetName[i], RRI[i], path));
            instances.get("instance"+i).getColumnIndexesFromExcelAndSet(decode(colIndex[i]));
            instances.get("instance"+i).getFromExcelAndSet();
        }

        // instances.get("instance0").getData();
        Map<Integer, List<String>> mapped = Ops.mapData(instances.get("instance0").getData(), instances.get("instance1").getData());
        Ops.writeToExcel(mapped, sheetName[1]+"21");        
        System.out.println("Done");
    }

    private static List<Integer> decode(String s) {
        String[] stringSplitted = s.split(",");
        List<Integer> columnIndexes = new ArrayList<>();
        for (String string : stringSplitted) {
            columnIndexes.add(Integer.valueOf(string));
        }
        return columnIndexes;
    }
}
