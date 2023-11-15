package de.hbrs.se2.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("not null")
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    private final UUID id = UUID.randomUUID();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity baseEntity = (BaseEntity) obj;
        return Objects.equals(this.id, baseEntity.id);
    }

}
