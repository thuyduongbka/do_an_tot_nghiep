package datn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datn.base.BaseEntity;
import datn.entity.user.EndUserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
    private String message;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private EndUserEntity userEntity;
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "scholarship_id")
    private Long scholarshipId;
    
    public CommentEntity(){}
    public CommentEntity(Long userId, Long scholarshipId, String message){
        this.userId = userId;
        this.scholarshipId = scholarshipId;
        this.message = message;
    }
    
}
