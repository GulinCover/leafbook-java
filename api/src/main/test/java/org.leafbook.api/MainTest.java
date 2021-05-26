package org.leafbook.api;

import org.junit.jupiter.api.Test;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.IdGenerator;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class MainTest {
    ClassLoader classLoader = MainApi.class.getClassLoader();
    Map<String, Map<String, String>> tableMap = new HashMap<>();

    private Long id = IdGeneratorTools.nextId();

    @Test
    void test01() throws ClassNotFoundException, IOException {

        File file = new File("F:\\vue\\leafbook-java\\api\\src\\main\\java\\org\\leafbook\\api\\modelApi");

        _analysisFile(file);

        _generateSQL();
    }

    void _analysisFile(File file) throws ClassNotFoundException, IOException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                _analysisFile(f);
            }
        } else {
            //生成表名
            String tableName = _tableNameReplace(file.getName());
            BufferedReader br = new BufferedReader(new FileReader(file));

            //解析字段
            Map<String, String> map = _analysisFile(br);
            tableMap.put(tableName, map);
        }
    }

    void _generateSQL() throws IOException {
        String sqls = "";
        String insertSqls = "";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("C:\\Users\\12086\\Desktop\\creator.sql")));
        BufferedWriter bwI = new BufferedWriter(new FileWriter(new File("C:\\Users\\12086\\Desktop\\insert.sql")));
        for (String tableName : tableMap.keySet()) {
            if (!tableName.equals("model")) {
                String sql = _sql(tableName);
                String insertSql = _clazzSql(tableName);
//                System.out.println(_clazzSql(tableName));
                sqls += "\n";
                sqls += sql;

                insertSqls += "\n";
                insertSqls += insertSql;
            }
        }

//        System.out.println(sqls);
        bw.write(sqls);
        bwI.write(insertSqls);
        bw.flush();
        bwI.flush();
    }

    String _sql(String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName + ";" +
                "CREATE TABLE "+ tableName +" (";
        String sqlEnd = "update_time bigint,create_time bigint,version int,is_black int) ENGINE = InnoDB CHARACTER SET = UTF8;";

        String fields = "";
        for (String field : tableMap.get(tableName).keySet()) {
            if (field.contains("content")) {
                fields += field + " text,";
            } else {
                if (tableMap.get(tableName).get(field).equals("Long")) {
                    fields += field + " bigint,";
                } else if (tableMap.get(tableName).get(field).equals("Integer")) {
                    fields += field + " int,";
                } else {
                    fields += field + " varchar(255),";
                }
            }
        }

        return sql + fields + sqlEnd;
    }

    String _clazzSql(String tableName) {
        String sql = "insert into "+ tableName + "(";

        for (String field : tableMap.get(tableName).keySet()) {
            sql += field + ',';
        }
        sql += "create_time,update_time,version,is_black";
        sql += ") values";

        String fields = "";
        for (int i = 0; i < 20; i++) {
            fields += _insertValues(tableName,id + i);
        }
        fields = fields.substring(0,fields.length()-1);
        fields += ";";
        return sql + fields;
    }

    String _insertValues(String tableName,Long id) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(31,secureRandom);

        String fields = "(";
        int index = 0;
        for (String field : tableMap.get(tableName).keySet()) {
            if (field.toLowerCase().contains("content")) {
                fields += "'" + TestModel.randomWord(50) + "'" + ",";
            } else {
                if (tableMap.get(tableName).get(field).equals("Long")) {
                    if (field.toLowerCase().contains("id")) {
                        fields += id + ",";
                    } else if(field.toLowerCase().contains("time")) {
                        fields += new Date().getTime() + ",";
                    } else if(field.toLowerCase().contains("price")) {
                        fields += new Random().nextInt(5000) + ",";
                    } else if(field.toLowerCase().contains("amount")) {
                        fields += new Random().nextInt(5000) + ",";
                    } else if(field.toLowerCase().contains("number")) {
                        fields += new Random().nextInt(10) + ",";
                    } else {
                        fields += new Random().nextInt(10) + ",";
                    }
                } else if (tableMap.get(tableName).get(field).equals("Integer")) {
                    if (field.toLowerCase().contains("type")) {
                        fields += new Random().nextInt(3) + ",";
                    } else if (field.toLowerCase().contains("status")) {
                        fields += new Random().nextInt(3) + ",";
                    } else if (field.toLowerCase().contains("position")) {
                        fields += new Random().nextInt(2) + ",";
                    } else if (field.toLowerCase().contains("sex")) {
                        fields += new Random().nextInt(2) + ",";
                    } else if (field.toLowerCase().contains("behavior")) {
                        fields += new Random().nextInt(2) + ",";
                    } else {
                        fields += new Random().nextInt(10) + ",";
                    }
                } else {
                    if (field.toLowerCase().contains("uuid")) {
                        fields += "'" + UUID.randomUUID() + "'" + ",";
                    } else if (field.toLowerCase().contains("nickname")) {
                        fields += "'" + TestModel.randomWord() + "'" + ",";
                    } else if (field.toLowerCase().contains("email")) {
                        fields += "'958803486" + index + "@qq.com'" + ",";
                    } else if (field.toLowerCase().contains("phone")) {
                        fields += "'13188886666'" + ",";
                    } else if (field.toLowerCase().contains("code") && tableName.equals("c_s_r_f_code_model")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(IdGeneratorTools.nextId())
                                .append(IdGeneratorTools.nextId())
                                .append(IdGeneratorTools.nextId())
                                .append(IdGeneratorTools.nextId())
                                .append(IdGeneratorTools.nextId())
                                .append(IdGeneratorTools.nextId());
                        fields += "'" + sb.toString() + "'" + ",";
                    } else if (field.toLowerCase().contains("avatar")) {
                        fields += "'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=387264399,2368088084&fm=26&gp=0.jpg'" + ",";
                    } else if (field.toLowerCase().contains("ip")) {
                        fields += "'1.1.1.1'" + ",";
                    } else if (field.toLowerCase().contains("location")) {
                        fields += "'shanghai'" + ",";
                    } else if (field.toLowerCase().contains("usedname")) {
                        fields += "';alex;babo;'" + ",";
                    } else if (field.toLowerCase().contains("password")) {
                        fields += "'" + bCryptPasswordEncoder.encode("12345678") + "'" + ",";
                    } else if (field.toLowerCase().contains("loginmark")) {
                        fields += "';1;2;3;4;'" + ",";
                    } else if (field.equals("username")) {
                        fields += "'" + TestModel.randomWord(1) + "'" + ",";
                    } else if (field.equals("entryType")) {
                        String[] sl = {"hot","official","nonofficial"};
                        int ret = new Random().nextInt(3);
                        fields += "'" + sl[ret] + "'" + ",";
                    } else {
                        fields += "'" + TestModel.randomWord(2) + "'" + ",";
                    }
                }
            }
            ++index;
        }

        Long time = new Date().getTime();
        fields += time + "," + time + ", 1, 0";
        fields += "),";
        return fields;
    }

    String _tableNameReplace(String tableName) {
        String name = "";
        for (char c : tableName.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                name += "_" + String.valueOf(c).toLowerCase();
            } else if ('0' <= c && c <= '9') {
                name += "_" + c;
            } else {
                name += c;
            }
        }

        return name.replace(".java", "").replaceFirst("_", "");
    }

    String _fieldNameReplace(String tableName) {
        String name = "";
        for (char c : tableName.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                name += "_" + String.valueOf(c).toLowerCase();
            } else if ('0' <= c && c <= '9') {
                name += "_" + c;
            } else {
                name += c;
            }
        }

        return name;
    }

    Map<String, String> _analysisFile(BufferedReader br) throws IOException {
        Map<String, String> fieldMap = new HashMap<>();
        String s = br.readLine();
        while (s != null) {
            String pattern = "private\\s(Long|String|Integer)\\s(.+?)(\\s=.+)?;";
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(s);
            if (matcher.find()) {
                fieldMap.put(_fieldNameReplace(matcher.group(2)), matcher.group(1));
            }

            s = "";
            s = br.readLine();
        }

        return fieldMap;
    }


}