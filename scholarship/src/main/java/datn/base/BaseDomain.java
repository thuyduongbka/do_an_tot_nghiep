package datn.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class BaseDomain {

    /**
     * Don't return actual id
     */
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
