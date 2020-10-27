package datn.enums;

public enum  UserType {
    ADMIN("ADMIN"),
    STUDENT("STUDENT");
    
    private String name;
    
    
    UserType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
