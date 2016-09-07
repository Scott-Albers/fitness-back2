package com.chyld.entities;

import com.chyld.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "devices")
@Data
public class Device {
    private int id;
    private String serialNumber;
    private String product;
    private Category category;
    private int version;
    private Date created;
    private Date modified;
    private User user;

    public Device() {
        this.created = new Date();
        this.modified = new Date();
    }

    @Id
    @GeneratedValue
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Version
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }

    @Column(name = "serial_no")
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    @Column(name = "product")
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('Running', 'Swimming', 'Biking')")
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Column(nullable = false, updatable = false)
    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    @Column(nullable = false)
    public Date getModified() { return modified; }
    public void setModified(Date modified) { this.modified = modified; }

    @PreUpdate
    protected void onUpdate() { this.modified = new Date(); }

}
