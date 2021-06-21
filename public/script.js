$(document).ready(function(){

    var $name = $("#username");
    var $pass = $("#password");
    var $nameSignUp = $("#name");
    var $lastname = $("#lastname");
    var $idUser = $("#idUser");
    var $age = $("#age");
    var $password = $("#password");
    var $password2 = $("#password2");

    const loginForm = document.querySelector("#login-form");
    const createAccountForm = document.querySelector("#signup-form");

    $("#login-button").on("click",function(event){
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

    $("#signup-button").on("click",function(event){
        event.preventDefault();

        const url = '/myapp/auth/signup/';
        console.log($nameSignUp.val());
        var data = {
            id: $idUser.val(),
            name: $nameSignUp.val(),
            lastname: $lastname.val(),
            playerId: '',
            password: $password.val(),
            age: $age.val() ,
            money: 200,
            gamesList: [

            ],
            productoList: [

            ]
          };
            console.log('11111',data);

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

    /* Sumar o restar cantidad productos tienda */
    $('.input-group').find('.quantity-plus').click(function(e) {
        e.preventDefault();

        var quantity = parseInt($(this).parent().find('.input-number').val());


        $(this).parent().find('.input-number').val(quantity + 1);
    });

    $('.input-group').find('.quantity-minus').click(function(e) {
        e.preventDefault();

        var quantity = parseInt($(this).parent().find('.input-number').val());

        if(quantity > 1) {
            $(this).parent().find('.input-number').val(quantity - 1);
        }
    });

    /* Volver al principio de la pagina */
    var back2Top = document.getElementById("backtotop");

    window.onscroll = function() {
        scrollFunction();
    }

    function scrollFunction() {
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        back2Top.style.display = "block";
      } else {
        back2Top.style.display = "none";
      }
    }

    $('.backtotop').on("click", function() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    });
 });
