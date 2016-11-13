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


import java.io.File;
import java.util.ArrayList;

class GetApk {

    /**
     * This function returns the list of all the apks in a directory
     */
    static ArrayList<String> getApkNames () {
        ArrayList<String> results = new ArrayList<>();
        File[] files;
        try {
            //please create an apk directory in C directory of your windows and place all the apks in it
            files = new File("C:\\apk").listFiles();
            for (File file : files) {
                if (file.isFile() && getFileExtension(file).equals("apk")) {
                    results.add("C:\\apk" + "\\" + file.getName());
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return results;
    }

    /*
    * returns the file extension of a file
    */
    private static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
