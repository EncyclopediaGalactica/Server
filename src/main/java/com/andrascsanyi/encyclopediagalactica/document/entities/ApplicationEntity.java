package com.andrascsanyi.encyclopediagalactica.document.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link ApplicationEntity} defines a domain within the Encyclopedia
 * Galactica system.
 *
 * A usage domain describes a set of usage like:
 *
 * <ul>
 * <li>Starmap</li>
 * <li>Finance</li>
 * </ul>
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class ApplicationEntity {

    /**
     * The unique identifier of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the domain.
     */
    @Column(name = "name")
    private String name;

    /**
     * The description of the domain.
     */
    @Column(name = "description")
    private String description;
}
