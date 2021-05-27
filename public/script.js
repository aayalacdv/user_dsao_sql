
$(document).ready(function(){

    var $name = $("#uname");
    var $pass = $("#pass");
    var $nameSignUp = $("#name");
    var $surname = $("#surname");
    var $idUser = $("#idUser");
    var $age = $("#age");
    var $password = $("#password");
    var $password2 = $("#password2");

    const loginForm = document.querySelector("#login");
    const createAccountForm = document.querySelector("#createAccount");

    document.querySelector("#linkCreateAccount").addEventListener("click", e => {
        e.preventDefault();
        loginForm.classList.add("form__hidden");
        createAccountForm.classList.remove("form__hidden");
    });

    document.querySelector("#linkLogin").addEventListener("click", e => {
        e.preventDefault();
            loginForm.classList.remove("form__hidden");
            createAccountForm.classList.add("form__hidden");
        });

    $("#logInBtn").on("click",function(event){
        event.preventDefault();

        const url = '/myapp/auth/login/';
        var data = {
            id: $name.val(),
            name: '',
            surname: '',
            playerId: '',
            password: $pass.val(),
            age: 0 ,
            money: 0,
            gamesList: [

            ],
            productoList: [

            ]
          };

        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(data),
            dataType: 'JSON',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (response) {
                console.log('succes');
            },
            error: function ( response){
                console.log(response);
            }
        });


    });

    $("#signUpBtn").on("click",function(event){
        event.preventDefault();


        const url = '/myapp/auth/signup/';
        var data = {
            id: $idUser.val(),
            name: $name.val(),
            surname: $surname.val(),
            playerId: '',
            password: $pass.val(),
            age: $age.val() ,
            money: 200,
            gamesList: [

            ],
            productoList: [

            ]
          };

        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(data),
            dataType: 'JSON',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (response) {
                console.log('succes');
            },
            error: function ( response){
                console.log(response);
            }
        });
    });
  });
