package ru.sydev.annotationdynamicparams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Slf4j
@Service
public class MyService {
    @Value("${service.property}")
    private String property;

    @Autowired
    private Environment env;

    @MyAnnotation( param = "${service.property}" )
    public void myMethod() {
        System.out.println("Property from yml file via @Value = " + property);
    }

    public void check() {
        myMethod();
        parseAnnotations();
    }

    private void parseAnnotations() {
        final Class<MyService> myServiceClass = MyService.class;

        try {
            final Method method = myServiceClass.getMethod("myMethod");
            final String param = method.getAnnotation(MyAnnotation.class).param();

            final String value = env.getProperty(resolveParam(param));

            System.out.println("Param passed to annotation from yml = " + value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private String resolveParam(final String param) {
        if (param == null)
            return param;

        if (param.startsWith("${") && param.endsWith("}"))
            return param.substring(2, param.length() - 1);
        else
            return param;
    }
}
