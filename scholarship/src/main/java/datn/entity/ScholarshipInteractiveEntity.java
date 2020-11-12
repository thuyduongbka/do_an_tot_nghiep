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
@Table(name = "scholarship_interactive")
public class ScholarshipInteractiveEntity extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private EndUserEntity userEntity;
    @Column(name = "user_id")
    private Long userId;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "scholarship_id", insertable = false, updatable = false)
    private ScholarshipEntity scholarshipEntity;
    @Column(name = "scholarship_id")
    private Long scholarshipId;
    
    private Boolean isLiked;
    private Integer rating;
    private Boolean isInListFavorite;
    private int numberSeen ;
    private int numberShare ;
    private int numberComment;
    private int numberClickContact;
    private int numberCompare;
    
    public ScholarshipInteractiveEntity(){}
    public ScholarshipInteractiveEntity(Long scholarshipId, Long userId){
        this.scholarshipId = scholarshipId;
        this.userId = userId;
        
    }
    
    
}
