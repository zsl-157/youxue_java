package com.youxue.project.shreal.entity;


public class Course{

  private String courseId;
  private String userId;
  private String couresName;
  private String courseAuthor;
  private String courseIcon;
  private String courseTitle;
  private String courseContent;
  private String courseVideoUrl;
  private long starNumber;
  private String vipCourse;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getCouresName() {
    return couresName;
  }

  public void setCouresName(String couresName) {
    this.couresName = couresName;
  }


  public String getCourseAuthor() {
    return courseAuthor;
  }

  public void setCourseAuthor(String courseAuthor) {
    this.courseAuthor = courseAuthor;
  }


  public String getCourseIcon() {
    return courseIcon;
  }

  public void setCourseIcon(String courseIcon) {
    this.courseIcon = courseIcon;
  }


  public String getCourseTitle() {
    return courseTitle;
  }

  public void setCourseTitle(String courseTitle) {
    this.courseTitle = courseTitle;
  }


  public String getCourseContent() {
    return courseContent;
  }

  public void setCourseContent(String courseContent) {
    this.courseContent = courseContent;
  }


  public String getCourseVideoUrl() {
    return courseVideoUrl;
  }

  public void setCourseVideoUrl(String courseVideoUrl) {
    this.courseVideoUrl = courseVideoUrl;
  }


  public long getStarNumber() {
    return starNumber;
  }

  public void setStarNumber(long starNumber) {
    this.starNumber = starNumber;
  }


  public String getVipCourse() {
    return vipCourse;
  }

  public void setVipCourse(String vipCourse) {
    this.vipCourse = vipCourse;
  }

}
