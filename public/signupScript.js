let username = document.querySelector('#username'); 
let name = document.querySelector('#name'); 
let lastname = document.querySelector('#lastname'); 
let age = document.querySelector('#age'); 
let password = document.querySelector('#password'); 
const signupbtn = document.querySelector('#signup-button'); 
const url = 'http://localhost:8080/myapp/auth/signup'


signupbtn.addEventListener("click", (event) => {
    event.preventDefault(); 
    const json = JSON.stringify({
        id: username.value,
        name: name.value,  
        surname: lastname.value,
        playerId: '',   
        password: password.value,
        age: age.value,
        money: 2000,
        gamesList: [],
        productoList: []
    
    })
    
    
   fetch(url , {
    method: 'POST',
    body: json,
    headers: {
      "Content-type": "application/json; charset=UTF-8"
    }
  } ).then( response => {response.text()})
    .then(json => console.log(json));
});