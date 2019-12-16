package com.zucitech.consoleapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class jsonHelper {
    static ObjectMapper mapper = new ObjectMapper();
    static JSONObject data;
    static JSONObject jsonObject;

    /*add employee into json file*/
    public static void write(List<Employee> emp, String filePath) throws IOException,NullPointerException{
        for(int i=0;i<emp.size();i++) {
            JSONArray jsonArray = null;
            String json;
                data = new JSONObject();
                data.put("id",emp.get(i).getId());
                data.put("firstname", emp.get(i).getFirstname());
                data.put("lastname",emp.get(i).getLastname());
                DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
                String hire_date = emp.get(i).getHired_date().format(dateFormatter);
                data.put("hired_date", hire_date);
                data.put("emptype", emp.get(i).getEmptype());
                data.put("salary", emp.get(i).getSalary());
                try {
                    jsonObject = readJsonToAddNewEmployee(filePath);
                }catch (ParseException e){
                    System.out.println("No Data");
                }
                if (jsonObject == null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonArray = new JSONArray();
                    jsonArray.add(data);
                    jsonObject.put("employee", jsonArray);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
                    mapper.writeValue(new File(filePath), jsonObject);
                } else {
                    jsonArray = (JSONArray) jsonObject.get("employee");
                    jsonArray.add(data);
                    jsonObject.put("employee", jsonArray);
                    json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
                    mapper.writeValue(new File(filePath), jsonObject);
                }
                System.out.println(json);
            }
    }

    /*update json file*/
    public static JSONObject readJsonToAddNewEmployee(String filePath) throws IOException, ParseException,NullPointerException{
            JSONObject newData=null;
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            Object obj = jsonParser.parse(reader);
            newData = (JSONObject) obj;
            return newData;
    }

    /*read the employees from json file*/
    public static List<EmployeePojo> read(String filePath) throws IOException,NullPointerException{
        Response value=null;
        ObjectMapper map = new ObjectMapper();
        List<EmployeePojo> list = new ArrayList<>();
            value = map.readValue(new File(filePath), Response.class);
            for(int i=0;i<value.getEmployee().size();i++){
                list.add(value.getEmployee().get(i));
            }
        return list;
    }

    /*remove employee from json file*/
    public static void remove( String filePath,int empId) throws IOException, ParseException,NullPointerException {
        JSONObject data = readJsonToAddNewEmployee(filePath);
        JSONArray jsonArray = (JSONArray) data.get("employee");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            long currentId = (long) obj.get("id");
            if (currentId == empId) {
                jsonArray.remove(obj);
                System.out.println("Employee has been removed");
                    mapper.writeValue(new File(filePath), data);
            }
        }
    }
}
