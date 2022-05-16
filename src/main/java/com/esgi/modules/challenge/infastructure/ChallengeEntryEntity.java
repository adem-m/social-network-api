package com.esgi.modules.challenge.infastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "challenge_entry")
public class ChallengeEntryEntity {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String codeId;

    public ChallengeEntryEntity() {
    }

    public ChallengeEntryEntity(String id, String userId, String codeId) {
        this.id = id;
        this.userId = userId;
        this.codeId = codeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }
}
