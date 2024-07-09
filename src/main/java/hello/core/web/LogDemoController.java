package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody // @ResponseBody = 뷰 화면이 없이 문자를 반환
    public String logDemo(HttpServletRequest request) {

        String requestURL = request.getRequestURL().toString();
        // 위 코드로 고객이 어떤 URL로 요청했는지 알수있음
        MyLogger myLogger = myLoggerProvider.getObject();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
// 핵심은 동시에 여러요청이 오더라도 요청마다 각각 객체를 따로 관리해줌
// 리퀘스트스코프는 요청이 많아도 스프링이 빈을 각각 할당해줌
    }
}