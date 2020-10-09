$(document).ready(function() {
    $("#pro-form").validate({
        rules: {
            name: "required",
            email: {
                required: true,
                email: true
            },
            phone:{
                required: true,
                number: true
            },
            address: {
                required: true,
                minlength: 5
            },
        },
        messages: {
            name: "Please enter your name",
            email: {
                required: "Please enter your email",
                email: "Please enter the valid email"
            },
            phone: {
                required: "Please enter your phone",
                number: "Please enter the valid phone"
            },
            address: {
                required :"Please enter your address",
                minlength: "The address is too short"
            },
        }
    });
    $("#pass-form").validate({
        rules: {
            oldpass: "required",
            newpass: {
                required: true,
                minlength: 8
            },
            comfirmpass:{
                required: true,
                equalTo: "#pass-new"
            },
        },
        messages: {
            oldpass: "Please enter your old password",
            newpass: {
                required: "Please enter your new password",
                minlength: "The new password too short"
            },
            comfirmpass: {
                required: "Please enter your confirm password",
                equalTo: "Incorrect password"
            },
        }
    });
});
