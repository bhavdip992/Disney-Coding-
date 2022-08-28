package com.example.disneycoding.Teacher

class User {
    var First_Name = ""
    var Last_Name = ""

    var email_Id = ""
    var teacher_password = ""

    constructor(
        f_name: String,
        l_name: String,

        email_id: String,
        tea_password: String
    ) {
        this.First_Name = f_name
        this.Last_Name = l_name

        this.email_Id = email_id
        this.teacher_password = tea_password
    }
}
