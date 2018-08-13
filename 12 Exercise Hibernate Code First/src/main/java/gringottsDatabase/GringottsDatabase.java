package gringottsDatabase;


import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Date;


//@Entity
//@Table(name="wizard_deposits")
public class GringottsDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="first_name", length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 60)
    private String lastName;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name="age",nullable = false)
    @Check(constraints = "age>=0")
    private int age;

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column(name = "magic_wand_size")
    private short magicWandSize;

    @Column(name="deposit_group",length = 20)
    private String depositGroup;

    @Column(name="deposit_start_date")
    private Date depositStartDate;

    @Column(name = "deposit_amount")
    private Double depositAmount;

    @Column(name = "deposit_interest")
    private Double depositInterest;

    @Column(name = "deposit_charge")
    private Double depositCharge;

    @Column(name = "deposit_expiration_date")
    private Date deposit_expiration_date;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

    public GringottsDatabase() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMagicWandCreator() {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    public short getMagicWandSize() {
        return magicWandSize;
    }

    public void setMagicWandSize(short magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    public String getDepositGroup() {
        return depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    public Date getDepositStartDate() {
        return depositStartDate;
    }

    public void setDepositStartDate(Date depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Double getDepositInterest() {
        return depositInterest;
    }

    public void setDepositInterest(Double depositInterest) {
        this.depositInterest = depositInterest;
    }

    public Double getDepositCharge() {
        return depositCharge;
    }

    public void setDepositCharge(Double depositCharge) {
        this.depositCharge = depositCharge;
    }

    public Date getDeposit_expiration_date() {
        return deposit_expiration_date;
    }

    public void setDeposit_expiration_date(Date deposit_expiration_date) {
        this.deposit_expiration_date = deposit_expiration_date;
    }

    public boolean isDepositExpired() {
        return isDepositExpired;
    }

    public void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
