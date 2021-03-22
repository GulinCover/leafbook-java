package org.leafbook.serviceApi;

import org.apache.maven.shared.utils.io.DirectoryScanner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class TestMain {
    @Test
    void test01() throws IOException, ClassNotFoundException {
        File file = new File(System.getProperty("user.dir"));
        File projectFile = file.getParentFile();

        DirectoryScanner directoryScanner = new DirectoryScanner();
        directoryScanner.setBasedir(projectFile);
        directoryScanner.scan();

        for (String directory:directoryScanner.getIncludedDirectories()) {
            File tempFile = new File(projectFile.getAbsolutePath()+File.separator+directory);
            if ("controller".equals(tempFile.getName())) {
                if (tempFile.isDirectory()) {
                    File[] tempController = tempFile.listFiles();
                    for (File f:tempController) {
                        if (f.getName().contains(".class")) {
                            continue;
                        }
                        FileReader fileReader = new FileReader(f);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String content = bufferedReader.readLine();
                        if (!content.equals(content.replace("User",""))) {
                            while (Objects.nonNull(content)) {
                                final String s = contentMather(content);
                                if (!"".equals(s)) {
                                    sendTest(11000,s);
                                }
                                content = bufferedReader.readLine();
                            }
                        } else if (!content.equals(content.replace("Common",""))) {
                            while (Objects.nonNull(content)) {
                                final String s = contentMather(content);
                                if (!"".equals(s)) {
                                    sendTest(10000,s);
                                }
                                content = bufferedReader.readLine();
                            }
                        }
                        else if (!content.equals(content.replace("Marketplace",""))) {
                            while (Objects.nonNull(content)) {
                                final String s = contentMather(content);
                                if (!"".equals(s)) {
                                    sendTest(15000,s);
                                }
                                content = bufferedReader.readLine();
                            }
                        }
                        else if (!content.equals(content.replace("Record",""))) {
                            while (Objects.nonNull(content)) {
                                final String s = contentMather(content);
                                if (!"".equals(s)) {
                                    sendTest(13000,s);
                                }
                                content = bufferedReader.readLine();
                            }
                        }
                        else if (!content.equals(content.replace("Comment",""))) {
                            while (Objects.nonNull(content)) {
                                final String s = contentMather(content);
                                if (!"".equals(s)) {
                                    sendTest(16000,s);
                                }
                                content = bufferedReader.readLine();
                            }
                        }
                        else if (!content.equals(content.replace("Topic",""))) {
                            while (Objects.nonNull(content)) {
                                final String s = contentMather(content);
                                if (!"".equals(s)) {
                                    sendTest(12000,s);
                                }
                                content = bufferedReader.readLine();
                            }
                        }
                    }
                }
            }
        }
    }

    void sendTest(int port,String url) throws IOException {
        URL realUrl = new URL("http://localhost:"+port+url);
        System.out.println("http://localhost:"+port+url);
        URLConnection urlConnection = realUrl.openConnection();
        urlConnection.setRequestProperty("accept", "*/*");
        urlConnection.setRequestProperty("connection", "Keep-Alive");
        urlConnection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.connect();
    }

    String contentMather(String content) {
        String pattern = "@.+Mapping\\(\"(.+)\"\\)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    @Test
    void oneButtonStart() throws IOException {
        File file = new File(System.getProperty("user.dir"));
        File projectFile = file.getParentFile();

        DirectoryScanner directoryScanner = new DirectoryScanner();
        directoryScanner.setBasedir(projectFile);
        directoryScanner.scan();

        String ss = "";
        for (String directory:directoryScanner.getIncludedFiles()) {
            String pattern = "^.+service-.+-api-1\\.0-SNAPSHOT\\.jar$";
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(directory);
            if (matcher.find()) {
                ss = matcher.group() + " & ";
            }
        }
        ss = ss.substring(0, ss.length() -2);
        Runtime.getRuntime().exec("cmd /k start dir");
        Runtime.getRuntime().exec("cmd /k start ping www.baidu.com");
    }

    @Test
    void test02() {
        String str = "@PostMapping(\"/rpc/post/create/single/entryInfo\")";
//        String pattern = "@.+Mapping\\(\"(.+)\"\\)";
//        Pattern compile = Pattern.compile(pattern);
//        Matcher matcher = compile.matcher(str);
//        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }

        String str2 = "Maindsdfasfas.java";
        String pattern = "^Main.+\\.java$";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str2);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }


}
