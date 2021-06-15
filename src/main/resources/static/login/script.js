document.querySelector('.img-btn').addEventListener('click',function()
    {

        document.querySelector('.cont').classList.toggle('s-signup')
        $("#phone-number").show();
        $("#phone-verify").hide();
        $("#sign-up-form").hide();
        $("#sdt").show();
        $("#verificationCode").val('');
    }
);
window.onload = function () {
    render();
}
var appVerifier;
function render() {
    // window.recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
    // recaptchaVerifier.render();
    appVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
    appVerifier.render();
}

function phoneAuth() {
    var phone = document.getElementById('number').value;

    if(phone.length > 10)
    {
        var message = "The phone number is too long!";
        $('<p class="help-block error">'+ message +'</p>').insertAfter('#number');
    }
    else {
        phone = '+84' + phone.substring(phone.length - 9, phone.length);
        firebase.auth().signInWithPhoneNumber(phone,appVerifier).then(function(confirmationResult) {
            window.confirmationResult = confirmationResult;
            coderesult =confirmationResult;
            // alert("Please check your code in your phone");
            // $("#sent-code").click(function(){
            $("#phone-verify").show();
            $("#phone-number").hide();
            document.getElementById("ph").value=phone;

            // })
        }).catch(function( error) {
            $('.error').remove();
            if(error.message == "TOO_SHORT")
            {
                var message = "The phone number is too short!";
                $('<p class="help-block error">'+ message +'</p>').insertAfter('#number');
            }
            // error 5 times
        });
    }

}
function codeverify() {
    var code = document.getElementById('verificationCode').value;

    if( code == ''){
        var message = "Please enter your code!";
        $('<p class="help-block error">'+ message +'</p>').insertAfter('#verificationCode');
    }
    else{
        coderesult.confirm(code).then(function(result){
            $("#sign-up-form").show();
            $("#phone-verify").hide();
        }).catch(function( error) {
            $('.error').remove();
            var message = "The code is incorect!";
            $('<p class="help-block error">'+ message +'</p>').insertAfter('#verificationCode');
        });
    }
}


$('input.check-number').keyup(function(event) {
    // skip for arrow keys
    if(event.which >= 37 && event.which <= 40) return;

    // format number
    $(this).val(function(index, value) {
        return value
            .replace(/\D/g, "")
            .replace(/\B(?=(\d{3})+(?!\d))/g, "");
    });
});

$('#res').click(function(){
    $("#phone-verify").hide();
    $("#phone-number").hide();
    $("#sign-up-form").show();
})
