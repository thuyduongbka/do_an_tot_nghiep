package datn.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class BaseDomain {

    /**
     * Don't return actual id
     */
    @JsonIgnore
    protected Long id;

    protected UUID code;

    public Long getId() {
        return id;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
