$(document).ready(function(){

    var $name = $('#uname');
    var $pass = $('#pass');

    $("#loginbtn").on("click",function(event){
        event.preventDefault();


        const url = '/myapp/auth/login/';
        var data = {
            id: $name.val(),
            name: '',
            surname: '',
            playerId: '',
            password: $pass.val(),
            edad: 0 ,
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

        $("#signupbtn").on("click",function(event){
            event.preventDefault();


            const url = '/myapp/auth/signup/';
            var data = {
                id: $name.val(),
                name: '',
                surname: '',
                playerId: '',
                password: $pass.val(),
                edad: 0 ,
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
  });