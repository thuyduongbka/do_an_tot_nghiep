package datn.entity;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "scholarship")
public class ScholarshipEntity extends BaseEntity {
    private int webId;
    private String name;
    private Date time;
    private String content;
    private String url;
    private String urlImage;
    private Boolean isExpired;
    private Float rating;

    private Long numberRating;
    private Long numberSeen;
    private Long numberShare;
    private Long numberComment;
    
    @OneToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private SchoolEntity schoolEntity;
    @OneToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private CountryEntity countryEntity;
    @OneToMany
    @JoinColumn(name = "scholarship_id")
    private List<LevelEntity> levelEntities;
    @OneToMany
    @JoinColumn(name = "scholarship_id")
    private List<MoneyEntity> moneyEntities;
    @OneToMany
    @JoinColumn(name = "scholarship_id")
    private List<RequirementEntity> requirementEntities;
    
    @Transient
    private List<CommentEntity> commentEntities;
    
    @ManyToMany
    @JoinTable(
            name = "major_scholarship",
            joinColumns = @JoinColumn(name = "scholarship_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<MajorEntity> majorEntities;
    
}
