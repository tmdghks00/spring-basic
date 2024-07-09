package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    //MyExcludeComponent가 붙은것은 컴포넌트스캔에서 제외함
}