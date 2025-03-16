package hello.core.member;

public class Member {

    private Long id; // 회원 ID
    private String name; // 회원 이름
    private Grade grade; // 회원 등급

    public Member(Long id, String name, Grade grade) { // 생성자
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() { // 회원 ID 반환
        return id;
    }

    public void setId(Long id) { // 회원 ID 설정
        this.id = id;
    }

    public String getName() { // 회원 이름 반환
        return name;
    }

    public void setName(String name) { // 회원 이름 설정
        this.name = name;
    }

    public Grade getGrade() { // 회원 등급 반환
        return grade;
    }

    public void setGrade(Grade grade) { // 회원 등급 설정
        this.grade = grade;
    }
}
