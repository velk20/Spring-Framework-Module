package bg.softuni.cache.cache.model;

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
}
