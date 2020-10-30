package datn.enums;

public enum TypeTime {
    HOUR("HOUR"),
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
