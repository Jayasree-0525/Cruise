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
    int customerId;

    @Column(name = "questionId")
    int questionId;


    @Override
    public int hashCode() {
        String concatString = String.valueOf(customerId.hashCode()) + String.valueOf(questionId.hashCode());
        return concatString.hashCode();
    }
    public ResponseKey(){}

    public ResponseKey(int customerId, int questionId){
        this.setCustomerId(customerId);
        this.setQuestionId(questionId);
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
        return (customerId==(other.customerId)) && (questionId == (other.questionId));
    }

}
