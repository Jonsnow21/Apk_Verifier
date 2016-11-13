/*Copyright 2016 Neeraj Jha

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    public static void main(String[] args) throws Exception {

        ArrayList<String> apkNames = GetApk.getApkNames();
        HashMap<String, String> apkNature = new HashMap<>();
        String[] nature = {"signed", "unsigned"};

        for (String apkName : apkNames) {
            ProcessBuilder builder = new ProcessBuilder(
                    //Please replace the location of jdk > bin here with the location of same on your computer
                    "cmd.exe", "/c", "cd \"C:\\Program Files\\Java\\jdk1.8.0_60\\bin\" && jarsigner -verify -verbose -certs " + apkName);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    apkNature.put(apkName, nature[0]);
                    break;
                }
                if (line.contains("CN=Android Debug")) {
                    apkNature.put(apkName, nature[1]);
                    break;
                }
            }
        }

        String path = "C:" + File.separator + "apk" + File.separator + "results.txt";
        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();

        String content = "";
        for (Map.Entry<String, String> entry : apkNature.entrySet()) {
            content += entry.getKey()+" : "+entry.getValue() + "\n";
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
