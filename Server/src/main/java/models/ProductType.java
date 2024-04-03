/*
 * @ (#) .java   1.0    03/04/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package models;/*
 * @description:
 * @author:     Hoang Le
 * @date:       03/04/2024
 * @version:    1.0
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "ProductType")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
    @Id
    private String productTypeId;
    private String productTypeName;
    @OneToMany(mappedBy = "productTypeId")
    private List<Product> products;
}
