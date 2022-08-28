package com.example.disneycoding.DBhelper

class UserData {

    var FNAME = ""
    var LNAME = ""
    var USERNAME = ""
    var EMAIL = ""
    var PASSWORD = ""

    constructor(FNAME:String,LNAME:String,USERNAME:String,EMAIL:String,PASSWORD:String)
    {
        this.FNAME=FNAME
        this.LNAME=LNAME
        this.USERNAME=USERNAME
        this.EMAIL=EMAIL
        this.PASSWORD=PASSWORD
    }
    constructor(EMAIL: String, PASSWORD: String)
    {
        this.EMAIL=EMAIL
        this.PASSWORD=PASSWORD

    }
}