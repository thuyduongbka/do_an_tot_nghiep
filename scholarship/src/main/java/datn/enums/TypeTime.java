package datn.enums;

public enum TypeTime {
    DAY("DAY"),
    MONTH("MONTH");
    
    TypeTime(String name) {
        this.name = name;
    }
    private String name;
    
    public String getName() {
        return name;
    }
}
