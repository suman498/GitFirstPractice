package pojo;

public class createUser {
    private String name;
    private String job;

    //Default Constructor
    public createUser(){

    }

    //Parameterized Constructor
    public createUser(String name,String job){
        this.name=name;
        this.job=job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
