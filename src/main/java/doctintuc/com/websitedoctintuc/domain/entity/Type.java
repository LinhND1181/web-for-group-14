package doctintuc.com.websitedoctintuc.domain.entity;

import doctintuc.com.websitedoctintuc.application.constants.CommonConstant;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "types")
@Entity
public class Type extends AbstractBase {

    @Column(name = "type_name")
    private String typeName;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = CommonConstant.COLUMN_CATEGORY_ID)
    private Category category;

    @OneToMany(mappedBy = "type",
            fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE,
            CascadeType.PERSIST})
    private List<News> news;
}
