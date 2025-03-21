package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SurveyKey implements Serializable {

    @Column(name = "customerId")
    int customerId;

    @Column(name = "cruiseId")
    int cruiseId;


    @Override
    public int hashCode() {
        String concatString = String.valueOf(customerId.hashCode()) + String.valueOf(cruiseId.hashCode());
        return concatString.hashCode();
    }
    public SurveyKey(){}

    public SurveyKey(int customerId, int cruiseId){
        this.setcustomerId(customerId);
        this.setcruiseId(cruiseId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (o == this)
            return true;
        if (!(o instanceof SurveyKey))
            return false;
        SurveyKey other = (SurveyKey) o;
        return customerId.equals(other.customerId) && cruiseId.equals(other.cruiseId);
    }

}
