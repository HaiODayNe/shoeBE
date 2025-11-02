package com.Shoe.dto.request.adminRequest.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
        private String name;
        private String warehouseCode;
        private String address;
        private String location;
}
