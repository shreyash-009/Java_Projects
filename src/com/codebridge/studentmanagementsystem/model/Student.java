package com.codebridge.studentmanagementsystem.model;

public class Student {



        private int id;
        private String name, address, gender, faculty, year;
        private long contact;

        public int getId() {

            return id;
        }

        public void setId(int id) {

            this.id = id;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {

            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getFaculty() {
            return faculty;
        }

        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public long getContact() {
            return contact;
        }

        public void setContact(long contact) {
            this.contact = contact;
        }
    }



