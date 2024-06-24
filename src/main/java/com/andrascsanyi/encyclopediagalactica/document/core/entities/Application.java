package com.andrascsanyi.encyclopediagalactica.document.core.entities;

import com.andrascsanyi.beanvalidatorextensions.LongValueMustBe;
import com.andrascsanyi.beanvalidatorextensions.TrimmedNotBlank;
import com.andrascsanyi.beanvalidatorextensions.TrimmedNotEmpty;
import com.andrascsanyi.beanvalidatorextensions.TrimmedSize;
import com.andrascsanyi.encyclopediagalactica.document.core.validation.AddApplicationEntityScenario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@link Application} defines a domain within the Encyclopedia Galactica system.
 * <p>
 * A usage domain describes a set of usage like:
 *
 * <ul>
 * <li>Starmap</li>
 * <li>Finance</li>
 * </ul>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application")
public class Application {
    
    /**
     * The unique identifier of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @LongValueMustBe(mustBe = 0L, groups = {AddApplicationEntityScenario.class})
    private Long id;
    
    /**
     * The name of the domain.
     */
    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @TrimmedNotBlank(groups = AddApplicationEntityScenario.class)
    @TrimmedNotEmpty(groups = AddApplicationEntityScenario.class)
    @TrimmedSize(min = 3, max = 255, groups = AddApplicationEntityScenario.class)
    private String name;
    
    /**
     * The description of the domain.
     */
    @Column(name = "description")
    @NotNull(message = "Description cannot be null.")
    @TrimmedNotBlank(groups = AddApplicationEntityScenario.class)
    @TrimmedNotEmpty(groups = AddApplicationEntityScenario.class)
    @TrimmedSize(min = 3, max = 255, groups = AddApplicationEntityScenario.class)
    private String description;
}
