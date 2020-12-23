package com.defensepoint.whitesourcetestcasesjava.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleEntity{
    @Id
    private String field;
}
