package com.wedevol.linkedinmarketing.core.entity;

public class Person {

  private String id;
  private String name;
  private String lastName;
  private String currentRole;
  private String country;
  private String industry;
  private int recommendationsQty;
  private int connectionsQty;

  public Person(String id, String name, String lastName, String currentRole, String country, String industry,
      int recommendationsQty, int connectionsQty) {
    super();
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.currentRole = currentRole;
    this.country = country;
    this.industry = industry;
    this.recommendationsQty = recommendationsQty;
    this.connectionsQty = connectionsQty;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCurrentRole() {
    return currentRole;
  }

  public void setCurrentRole(String currentRole) {
    this.currentRole = currentRole;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public int getRecommendationsQty() {
    return recommendationsQty;
  }

  public void setRecommendationsQty(int recommendationsQty) {
    this.recommendationsQty = recommendationsQty;
  }

  public int getConnectionsQty() {
    return connectionsQty;
  }

  public void setConnectionsQty(int connectionsQty) {
    this.connectionsQty = connectionsQty;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", currentRole=" + currentRole
        + ", country=" + country + ", industry=" + industry + ", recommendationsQty=" + recommendationsQty
        + ", connectionsQty=" + connectionsQty + "]";
  }

}
