package org.example.proxies;

public class StudentDTO {
    private final String name;
    private final Integer avgScore;
    private final Integer age;


    public StudentDTO(String name, Integer avgScore, Integer age) {
        this.name = name;
        this.avgScore = avgScore;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", avgScore=" + avgScore +
                ", age=" + age +
                '}';
    }
}
