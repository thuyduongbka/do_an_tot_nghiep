package datn.enums;

public enum Role {
    
    ADMIN("ADMIN"),
    END_USER("END_USER");
    
    private String name;
    
    public String getName() {
        return name;
    }
    
    Role(String name) {
        this.name = name;
    }
    
}