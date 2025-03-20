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
public class ResponseKey implements Serializable {

    @Column(name = "customerId")
    Long customerId;

    @Column(name = "questionId")
    String questionId;


    @Override
    public int hashCode() {
        String concatString = String.valueOf(customerId.hashCode()) + String.valueOf(questionId.hashCode());
        return concatString.hashCode();
    }
    public ResponseKey(){}

    public ResponseKey(int customerId, int questionId){
        this.setcustomerId(customerId);
        this.setquestionId(questionId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (o == this)
            return true;
        if (!(o instanceof ResponseKey))
            return false;
        ResponseKey other = (ResponseKey) o;
        return customerId.equals(other.customerId) && questionId.equals(other.questionId);
    }

}
