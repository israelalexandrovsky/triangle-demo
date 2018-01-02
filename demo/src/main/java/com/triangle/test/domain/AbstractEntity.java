package com.triangle.test.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

    @MappedSuperclass
    public class AbstractEntity implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name="CREATION_DATE", nullable=false)
        @Temporal(TemporalType.TIMESTAMP)
        private Date creationDate;

        @Column(name="UPDATE_DATE", nullable=true)
        @Temporal(TemporalType.TIMESTAMP)
        private Date updateDate;

        public Date getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
        }

        public Date getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Date updateDate) {
            this.updateDate = updateDate;
        }

        @PrePersist
        public void prePersist() {
            setCreationDate(new Date());
        }

        @PreUpdate
        public void preUpdate() {
            setUpdateDate(new Date());
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("AbstractEntity{");
            sb.append("creationDate=").append(creationDate);
            sb.append(", updateDate=").append(updateDate);
            sb.append('}');
            return sb.toString();
        }
    }
