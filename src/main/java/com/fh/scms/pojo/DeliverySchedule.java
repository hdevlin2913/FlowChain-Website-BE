package com.fh.scms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.scms.enums.DeliveryMethodType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_schedule")
public class DeliverySchedule extends _BaseEntity implements Serializable {

    @NotNull(message = "{deliverySchedule.scheduledDate.notNull}")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "schedule_date", nullable = false)
    private LocalDate scheduledDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{deliverySchedule.method.notNull}")
    @Column(nullable = false)
    private DeliveryMethodType method = DeliveryMethodType.EXPRESS;

    @OneToMany(mappedBy = "deliverySchedule", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Order> orderSet;

    @OneToMany(mappedBy = "deliverySchedule", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Shipment> shipmentSet;

    @Override
    public String toString() {
        return "com.fh.scm.pojo.DeliverySchedule[ id=" + this.id + " ]";
    }
}