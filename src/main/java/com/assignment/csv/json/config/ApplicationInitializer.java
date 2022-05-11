package com.assignment.csv.json.config;

import com.assignment.csv.json.model.Common;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@PropertySource(value = {"classpath:application.properties"})
@Configuration
public class ApplicationInitializer implements WebApplicationInitializer {

    @Autowired
    private Environment environment;

    public static final String baseUrl= "/media/thara/tharaka/study/fidenz/customer-service-master/src/main/resources/";
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container
                .addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        File file = new File(baseUrl+"csv");
        if (!file.isDirectory()) {
            new File(environment.getRequiredProperty(baseUrl+"csv")).mkdir();
            file.mkdir();

        } else {
            File csvFile = new File(baseUrl+"csv");
            if (csvFile.exists()) {
                csvFile.delete();
            }
            File fileName = new File(baseUrl+"UserData.json");
            File jsonFile = new File(fileName.getAbsolutePath());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, Common.class);
                List<Common> customers = objectMapper.readValue(jsonFile, collectionType);

                FileWriter writer = new FileWriter(baseUrl+"/csv/customers.csv");

                String commaDelimeter = ",";
                String newLineSeperator = "\n";
                String fileHeader = "_id,index,age,eyeColor,name,gender,company,email," +
                        "phone,number,street,city,state,zipcode,about,registered,latitude," +
                        "longitude,tags";
                writer.append(fileHeader);
                writer.append(newLineSeperator);

                for (Common customer:customers) {
                    writer.append(customer.get_id()).append(commaDelimeter);
                    writer.append(customer.getIndex()).append(commaDelimeter);
                    writer.append(customer.getAge()).append(commaDelimeter);
                    writer.append(customer.getEyeColor()).append(commaDelimeter);
                    writer.append(customer.getName()).append(commaDelimeter);
                    writer.append(customer.getGender()).append(commaDelimeter);
                    writer.append(customer.getCompany()).append(commaDelimeter);
                    writer.append(customer.getEmail()).append(commaDelimeter);
                    writer.append(customer.getPhone()).append(commaDelimeter);
                    writer.append(customer.getAddress().getNumber()).append(commaDelimeter);
                    writer.append(customer.getAddress().getStreet()).append(commaDelimeter);
                    writer.append(customer.getAddress().getCity()).append(commaDelimeter);
                    writer.append(customer.getAddress().getState()).append(commaDelimeter);
                    writer.append(customer.getAddress().getZipcode()).append(commaDelimeter);
                    writer.append(customer.getAbout().replaceAll("[\n\r]","")).append(commaDelimeter);
                    writer.append(customer.getRegistered()).append(commaDelimeter);
                    writer.append(customer.getLatitude()).append(commaDelimeter);
                    writer.append(customer.getLongitude()).append(commaDelimeter);
                    writer.append("\"" + String.join(",",customer.getTags()) + "\"");
                    writer.append(newLineSeperator);
                }
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
