package dev.example.order.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    // @CreatedDate -> ZonedDateTime을 지원 안함
    @CreationTimestamp
    private ZonedDateTime createdAt;

    // @LastModifiedDate
    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}

// @MappedSuperclass 이기때문에 AbstractEntity를 상속받는 자식객체에서는 두 컬럼이 모두 상속됨