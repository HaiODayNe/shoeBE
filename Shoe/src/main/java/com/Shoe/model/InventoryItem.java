package com.Shoe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "InventoryItems")
public class InventoryItem {
    public enum Status {
        HANG_MOI,
        DANG_BAN,
        HET_HANG,
        DANG_KIEM_KE,
        DANG_NHAP_KHO,
        DANG_XUAT_KHO,
        HANG_LOI,
        HANG_HOAN,
        HANG_CU,
        HET_HAN,
        CAN_XU_LY,
        DAT_TRUOC,
        DANG_CHUYEN_KHO,
        KHUYEN_MAI,
        HANG_MAU
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "warehouse")
    private Inventory warehouse;
}
