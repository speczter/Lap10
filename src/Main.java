/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tuan An
 */
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public List<String> listStaticFunction;

    public List<String> getListStaticFunction() {
        return listStaticFunction;
    }

    public void setListStaticFunction(List<String> listStaticFunction) {
        this.listStaticFunction = listStaticFunction;
    }

    public static String readContentFromFile(String path) {

        try {
            FileInputStream fis = new FileInputStream(new File(path));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            String para = "";
            while ((line = br.readLine()) != null) {
                para += line + "\n";
            }
            br.close();
            return para;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static File findFileByName(String folderPath, String fileName) {
        Path dir = Paths.get(folderPath);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, fileName)) {
            for (Path entry : stream) {
                return entry.toFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllFunctions(File path) {
        List<String> listFunction = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(path.getPath()));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            String para = "";
            String pubInFunction = "public";
            String staticInFunction = "static";
            String protInFunction = "protect";
            String privInFunction = "private";
            int count = 0;
            boolean isStaticFunction = false;
            while ((line = br.readLine()) != null) {
                if (isStaticFunction) {
                    para += line + "\n";
                    if (line.contains("{")) {
                        count++;
                    }
                    if (line.contains("}")) {
                        count--;
                    }
                    if (count == 0) {
                        listFunction.add(para);
                        para = "";
                    }

                }
                if ((line.contains(pubInFunction) || line.contains(privInFunction))
                        && line.contains(staticInFunction)
                        && line.contains(protInFunction)
                        && !line.contains("/*")
                        && !line.contains("//")
                        && !line.contains("=")
                        && !line.contains("==")
                        && !line.contains(";")) {
                    System.out.println(line);
                    para += line + "\n";
                    isStaticFunction = true;

                    count++;
                }  else if (isStaticFunction && count == 0) {
                    isStaticFunction = false;
                }
            }

            return listFunction;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findFunctionByName(String name){
        String[] arr = name.split("\\(");
        for(String s : this.listStaticFunction){
            if(s.contains(arr[0])){
                return s;
            }
        }
        return null;
    }

    public void cau1(){
        try {
            File path = findFileByName("/home/sidz/school/ltnc/Int2204-3/Group2/17020998/lab10/src/Main", "Main");
            List<String> str = getAllFunctions(path);
            System.out.println(str);

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        // write your code here
        Main main = new Main();
        main.cau1();
//        Random random = new Random();
//        int array[] = new int[10000];
//
//        int i = 0;
//
//        while (i < 1000) {
//            boolean isDuplicate = false;
//            int randomInteger = random.nextInt(1000) + 1;
//
//            for (int j = 0; j < i; j++) {
//                if (array[j] == randomInteger) {
//                    isDuplicate = true;
//                    break;
//                }
//
//            }
//            if (isDuplicate) {
//                continue;
//            } else {
//                array[i++] = randomInteger;
//            }
//        }
//
//        for ( i =0;i< 1000; i++) {
//            for (int j = 0; j < 1000 - i; j++) {
//                if (array[j + 1] < array[j] && j + 1 < 1000) {
//                    int temp = array[j + 1];
//                    array[j + 1] = array[j];
//                    array[j] = temp;
//                }
//            }
//        }
//
//        for(i=0; i<1000; i++){
//            System.out.println(array[i]);
//        }
//    }
    }
}
