package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    // 컨테이너가 내려가서 종료하기 전에 싱글톤 빈들이 하나씩 죽는데 그때 destroy()가 호출되고
    // 그러면서 disconnect()를 안전하게 호출해준다 
    // 이렇게 안전하게 호출이되면 스프링이 내려간다

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}