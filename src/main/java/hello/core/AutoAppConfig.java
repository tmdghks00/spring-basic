package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
//        basePackages = "hello.core", // 탐색할 패키지의 시작 위치를 지정한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                Configuration.class))
public class AutoAppConfig {

}
