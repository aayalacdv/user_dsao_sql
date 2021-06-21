let name = document.querySelector('#username');
let password = document.querySelector('#password');
let button = document.querySelector('#login-button');
const url = "http://localhost:8080/myapp/auth/login"

button.addEventListener("click", (event) => {
    event.preventDefault();
    const json = JSON.stringify({
        id: name.value,
        name: '',
        surname: '',
        playerId: '',
        password: password.value,
        age: 0 ,
        money: 0,
        gamesList: [],
        productoList: []

    })

   fetch(url , {
     method: 'POST',
     body: json,
     headers: {
       "Content-type": "application/json; charset=UTF-8"
     }
   }).then( response => response.json())
     .then(json => console.log(json));

})