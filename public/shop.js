$(document).ready(function(){
    console.log(localStorage.getItem('username'));

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


let lifeBtn = document.querySelector('#life_btn'); 
let speedBtn = document.querySelector('#speed_btn'); 
let resistanceBtn = document.querySelector('#resistance_btn'); 
let data; 
const url = "http://localhost:8080/myapp/shop/buyProduct"


lifeBtn.addEventListener("click", (event) => {
    event.preventDefault(); 
    data = JSON.stringify({
        idProduct: 'vida',
        idUser: localStorage.getItem('username'), 
        amount: document.querySelector('#pociondevida').value  

    })
    setTimeout(function(){
        let buyBtn = document.querySelector('#buy_btn'); 
        buyBtn.addEventListener('click', (event) => {
            fetch(url,{
                method: 'POST', 
                body: data,
                headers: {
                 "Content-type": "application/json; charset=UTF-8"
                }
                }).then( response => {
                   if(response.status === 201 ){
                    alert("Compra completada")
                  }else { alert("no money bro ")}
        })
    },3000)
    
})
})

speedBtn.addEventListener("click", (event) => {
    event.preventDefault(); 
    data = JSON.stringify({
        idProduct: 'velocidad',
        idUser: localStorage.getItem('username'), 
        amount: document.querySelector('#pociondevelocidad').value  

    })
    setTimeout(function(){
        let buyBtn = document.querySelector('#buy_btn'); 
        buyBtn.addEventListener('click', (event) => {

        })
    },3000);
    
})

resistanceBtn.addEventListener("click", (event) => {
    event.preventDefault(); 
    data = JSON.stringify({
        idProduct: 'resistencia',
        idUser: localStorage.getItem('username'), 
        amount: document.querySelector('#pocionderesistencia').value  

    })
    setTimeout(function(){
        let buyBtn = document.querySelector('#buy_btn'); 
        buyBtn.addEventListener('click', (event) => {
        })
    },3000);

})
