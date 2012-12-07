function registerUser() {
    var login = $('#login').val();
    var pwd1 = $('#pwd1').val();
    var pwd2 = $('#pwd2').val();
    $.ajax({
        url : 'newuser.html',
        data : {
            name : name, password : pwd1, confirmPassword : pwd2
        },
        success : function() {
            $("#back").trigger('click');
            console.log($('#back').val());
        }
    });
}